package com.huizi.leave.leavemanagementsystem;
import com.huizi.leave.leavemanagementsystem.util.DBUtil;
import javax.servlet.ServletException; import javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import java.io.IOException; import java.sql.*;
@WebServlet("/leave/list") public class LeaveListServlet extends HttpServlet {
 protected void doGet(HttpServletRequest r,HttpServletResponse s)throws ServletException,IOException{if(r.getSession(false)==null||r.getSession().getAttribute("userId")==null){s.sendRedirect(r.getContextPath()+"/login");return;} try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM leave_request WHERE student_id=? ORDER BY created_at DESC")){p.setLong(1,(Long)r.getSession().getAttribute("userId"));try(ResultSet x=p.executeQuery()){r.setAttribute("requests",x);r.getRequestDispatcher("/public/leave-list.jsp").forward(r,s);}}catch(SQLException e){throw new ServletException("读取请假记录失败",e);}}
}
