package com.huizi.leave.leavemanagementsystem;

import javax.servlet.*;
import java.util.logging.Logger;

/** 演示 Servlet 生命周期，并在容器启动时初始化应用。 */
public class AppStartupServlet implements Servlet {
    private static final Logger LOG = Logger.getLogger(AppStartupServlet.class.getName());
    private ServletConfig config;
    public void init(ServletConfig config) throws ServletException { this.config=config; config.getServletContext().setAttribute("appStartTime", new java.util.Date()); LOG.info("请假系统 Servlet 已初始化"); }
    public ServletConfig getServletConfig(){ return config; }
    public void service(ServletRequest req, ServletResponse res) throws ServletException { res.setContentType("text/plain;charset=UTF-8"); try { res.getWriter().println("应用已启动"); } catch(Exception e){ throw new ServletException(e); } }
    public String getServletInfo(){ return "Leave management application startup servlet"; }
    public void destroy(){ LOG.info("请假系统 Servlet 已销毁"); }
}
