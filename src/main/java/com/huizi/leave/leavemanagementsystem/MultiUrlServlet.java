package com.huizi.leave.leavemanagementsystem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/** 1.3.12：同一个 Servlet 映射到多个 URL。 */
@WebServlet({"/multi/one", "/multi/two"})
public class MultiUrlServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().println("MultiUrlServlet 当前路径：" + req.getServletPath());
    }
}
