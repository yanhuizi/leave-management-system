package com.huizi.leave.leavemanagementsystem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/** 1.9.3：使用 getLastModified 提供资源最后修改时间。 */
@WebServlet("/last-modified")
public class LastModifiedServlet extends HttpServlet {
    protected long getLastModified(HttpServletRequest req) { return 0L; }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().println("LastModifiedServlet 已执行");
    }
}
