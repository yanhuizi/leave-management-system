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

/** 处理登录页的展示和登录提交。 */
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

        if (username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "请输入账号和密码");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        String sql = "SELECT id, username, real_name, role "
                + "FROM t_user WHERE username = ? AND password = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // 登录成功后只保存必要信息，不把 ResultSet 放进 Session。
                    request.changeSessionId();
                    request.getSession().setAttribute("userId", resultSet.getLong("id"));
                    request.getSession().setAttribute("username", resultSet.getString("username"));
                    request.getSession().setAttribute("realName", resultSet.getString("real_name"));
                    request.getSession().setAttribute("role", resultSet.getInt("role"));
                    response.sendRedirect(request.getContextPath() + "/home.jsp");
                    return;
                }
            }
            request.setAttribute("error", "账号或密码错误");
        } catch (SQLException e) {
            log("登录查询失败", e);
            request.setAttribute("error", "系统暂时无法登录，请检查数据库连接");
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }
}
