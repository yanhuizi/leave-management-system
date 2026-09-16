package com.huizi.leave.leavemanagementsystem;

import com.huizi.leave.leavemanagementsystem.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** 老师 qingjia 数据库的登录处理。username 对应 student/teacher 表中的 num。 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null) {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("访问servlet");
            System.out.println("用户名" + username);
            System.out.println("密码" + password);
            response.getWriter().println(username + "登录成功");
            return;
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = trim(request.getParameter("username"));
        String password = request.getParameter("password");
        if (username.isEmpty() || password == null || password.trim().isEmpty()) {
            showError(request, response, "请输入学号/工号和密码");
            return;
        }

        try (Connection connection = DBUtil.getConnection()) {
            LoginUser user = safeStudent(connection, username, password);
            if (user == null) user = safeTeacher(connection, username, password);
            if (user == null) user = safeSystemUser(connection, username, password);
            if (user == null) user = safeAdmin(connection, username, password);
            // 课程 1.3.11 使用的演示账号；老师 qingjia.sql 未提供管理员表时仍可完成 Servlet 流程。
            if (user == null && "admin".equals(username) && "123456".equals(password)) {
                user = new LoginUser(0L, "管理员", "admin", "admin", 2);
            }
            if (user == null) {
                showError(request, response, "账号或密码错误");
                return;
            }

            request.changeSessionId();
            request.getSession().setAttribute("userId", user.id);
            request.getSession().setAttribute("username", user.num);
            request.getSession().setAttribute("realName", user.name);
            request.getSession().setAttribute("userType", user.userType);
            request.getSession().setAttribute("roleId", user.roleId);
            response.sendRedirect(request.getContextPath() + "/public/main.jsp");
        } catch (SQLException e) {
            log("登录查询失败", e);
            showError(request, response, "数据库登录失败：" + e.getMessage());
        }
    }

    private LoginUser safeStudent(Connection c,String u,String p){try{return findStudent(c,u,p);}catch(SQLException e){log("student 表不可用",e);return null;}}
    private LoginUser safeTeacher(Connection c,String u,String p){try{return findTeacher(c,u,p);}catch(SQLException e){log("teacher 表不可用",e);return null;}}
    private LoginUser safeSystemUser(Connection c,String u,String p){try{return findSystemUser(c,u,p);}catch(SQLException e){log("t_user 表不可用",e);return null;}}
    private LoginUser safeAdmin(Connection c,String u,String p){
        try (PreparedStatement s=c.prepareStatement("SELECT id, username, real_name FROM admin WHERE username=? AND password=?")) {
            s.setString(1,u); s.setString(2,p); try(ResultSet r=s.executeQuery()){if(r.next()) return new LoginUser(r.getLong("id"),r.getString("real_name"),r.getString("username"),"admin",2);}
        } catch(SQLException e){log("admin 表不可用",e);} return null;
    }

    private LoginUser findStudent(Connection connection, String num, String password)
            throws SQLException {
        String sql = "SELECT id, name, num FROM student WHERE num = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, num);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new LoginUser(resultSet.getLong("id"), resultSet.getString("name"),
                            resultSet.getString("num"), "student", 1);
                }
            }
        }
        return null;
    }

    private LoginUser findTeacher(Connection connection, String num, String password)
            throws SQLException {
        String sql = "SELECT id, name, num FROM teacher WHERE num = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, num);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    long teacherId = resultSet.getLong("id");
                    return new LoginUser(teacherId, resultSet.getString("name"),
                            resultSet.getString("num"), "teacher",
                            findTeacherRoleId(connection, teacherId));
                }
            }
        }
        return null;
    }

    private int findTeacherRoleId(Connection connection, long teacherId) throws SQLException {
        String sql = "SELECT roleid FROM user_role WHERE userid = ? ORDER BY roleid LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, teacherId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt("roleid") : 0;
            }
        }
    }

    /** 兼容项目注册功能使用的 t_user 表（例如 admin/123456）。 */
    private LoginUser findSystemUser(Connection connection, String username, String password)
            throws SQLException {
        String sql = "SELECT id, username, real_name, role FROM t_user WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int role = resultSet.getInt("role");
                    return new LoginUser(resultSet.getLong("id"), resultSet.getString("real_name"),
                            resultSet.getString("username"), "admin", role);
                }
            }
        }
        return null;
    }

    private void showError(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("error", message);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }

    private static class LoginUser {
        private final long id;
        private final String name;
        private final String num;
        private final String userType;
        private final int roleId;

        private LoginUser(long id, String name, String num, String userType, int roleId) {
            this.id = id;
            this.name = name;
            this.num = num;
            this.userType = userType;
            this.roleId = roleId;
        }
    }
}
