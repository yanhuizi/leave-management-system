package com.huizi.leave.leavemanagementsystem;

import com.huizi.leave.leavemanagementsystem.util.DBUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/leave")
public class LeaveRequestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!loggedIn(req)) { resp.sendRedirect(req.getContextPath()+"/login"); return; }
        req.getRequestDispatcher("/public/leave.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!loggedIn(req)) { resp.sendRedirect(req.getContextPath()+"/login"); return; }
        req.setCharacterEncoding("UTF-8");
        String type = value(req.getParameter("type")), reason = value(req.getParameter("reason"));
        String start = value(req.getParameter("startTime")), end = value(req.getParameter("endTime"));
        if (type.isEmpty() || reason.isEmpty() || start.isEmpty() || end.isEmpty()) { fail(req,resp,"请完整填写请假信息"); return; }
        try (Connection c=DBUtil.getConnection(); PreparedStatement p=c.prepareStatement(
                "INSERT INTO leave_request(student_id,student_num,student_name,type,start_time,end_time,reason,status) VALUES(?,?,?,?,?,?,?,'待审批')")) {
            p.setLong(1,(Long)req.getSession().getAttribute("userId")); p.setString(2,(String)req.getSession().getAttribute("username"));
            p.setString(3,(String)req.getSession().getAttribute("realName")); p.setString(4,type); p.setTimestamp(5,Timestamp.valueOf(start.replace('T',' ')+":00")); p.setTimestamp(6,Timestamp.valueOf(end.replace('T',' ')+":00")); p.setString(7,reason); p.executeUpdate();
            resp.sendRedirect(req.getContextPath()+"/leave/list");
        } catch (SQLException e) { log("提交请假失败",e); fail(req,resp,"提交失败，请先执行 database/schema.sql"); }
    }
    private boolean loggedIn(HttpServletRequest r){return r.getSession(false)!=null&&r.getSession().getAttribute("userId")!=null;}
    private String value(String s){return s==null?"":s.trim();}
    private void fail(HttpServletRequest r,HttpServletResponse s,String m)throws ServletException,IOException{r.setAttribute("error",m);r.getRequestDispatcher("/public/leave.jsp").forward(r,s);}
}
