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

/** 处理新用户注册。 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = value(request.getParameter("username"));
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String realName = value(request.getParameter("realName"));

        if (username.length() < 3 || username.length() > 20) {
            returnError(request, response, "账号长度必须为 3-20 个字符");
            return;
        }
        if (!username.matches("[A-Za-z0-9_]+")) {
            returnError(request, response, "账号只能包含字母、数字和下划线");
            return;
        }
        if (password == null || password.length() < 6 || password.length() > 50) {
            returnError(request, response, "密码长度必须为 6-50 个字符");
            return;
        }
        if (!password.equals(confirmPassword)) {
            returnError(request, response, "两次输入的密码不一致");
            return;
        }
        if (realName.length() > 50) {
            returnError(request, response, "姓名不能超过 50 个字符");
            return;
        }

        String existsSql = "SELECT 1 FROM t_user WHERE username = ?";
        String insertSql = "INSERT INTO t_user(username, password, real_name, role) VALUES (?, ?, ?, 0)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement exists = connection.prepareStatement(existsSql)) {
            exists.setString(1, username);
            try (ResultSet resultSet = exists.executeQuery()) {
                if (resultSet.next()) {
                    returnError(request, response, "该账号已注册，请直接登录");
                    return;
                }
            }

            try (PreparedStatement insert = connection.prepareStatement(insertSql)) {
                insert.setString(1, username);
                insert.setString(2, password);
                insert.setString(3, realName.isEmpty() ? username : realName);
                insert.executeUpdate();
            }
            response.sendRedirect(request.getContextPath() + "/login?registered=1");
        } catch (SQLException e) {
            log("注册失败", e);
            returnError(request, response, "注册失败，请稍后重试");
        }
    }

    private void returnError(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("error", message);
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    private String value(String value) {
        return value == null ? "" : value.trim();
    }
}
