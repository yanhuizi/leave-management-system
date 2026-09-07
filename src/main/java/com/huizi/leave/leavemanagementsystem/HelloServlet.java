package com.huizi.leave.leavemanagementsystem;

import com.huizi.leave.leavemanagementsystem.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("SELECT id, username, real_name, role FROM t_user");
            rs = ps.executeQuery();
            out.println("<h2>数据库连接成功，用户列表：</h2>");
            out.println("<table border='1' cellpadding='6'>");
            out.println("<tr><th>ID</th><th>账号</th><th>姓名</th><th>角色</th></tr>");
            while (rs.next()) {
                int role = rs.getInt("role");
                String roleName = role == 0 ? "学生" : (role == 1 ? "教师" : "管理员");
                out.println("<tr><td>" + rs.getLong("id")
                        + "</td><td>" + rs.getString("username")
                        + "</td><td>" + rs.getString("real_name")
                        + "</td><td>" + roleName + "</td></tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            out.println("<h3 style='color:red'>数据库出错：" + e.getMessage() + "</h3>");
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }
}
