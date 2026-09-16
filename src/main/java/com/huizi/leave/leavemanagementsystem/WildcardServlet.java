package com.huizi.leave.leavemanagementsystem;

import javax.servlet.http.*;
import java.io.IOException;

/** 1.3.13：通过 /* 通配符匹配一组 URL。 */
public class WildcardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().println("WildcardServlet 匹配路径：" + req.getPathInfo());
    }
}
