package com.huizi.leave.leavemanagementsystem;

import javax.servlet.*;
import java.io.IOException;

/** 1.8：GenericServlet 生命周期和自定义 service。 */
public class GenericDemoServlet extends GenericServlet {
    public void init() throws ServletException { log("GenericDemoServlet init"); }
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().println("GenericServlet service 已执行");
    }
    public void destroy() { log("GenericDemoServlet destroy"); }
}
