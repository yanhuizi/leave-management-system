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
            LoginUser user = findStudent(connection, username, password);
            if (user == null) {
                user = findTeacher(connection, username, password);
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
            showError(request, response, "系统暂时无法登录，请检查 qingjia 数据库连接");
        }
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
