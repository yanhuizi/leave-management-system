package com.huizi.leave.leavemanagementsystem;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/context-info")
public class ContextInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext context=getServletContext();
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().printf("应用路径：%s%n服务器：%s%n启动时间：%s%n", context.getContextPath(), context.getServerInfo(), context.getAttribute("appStartTime"));
    }
}
