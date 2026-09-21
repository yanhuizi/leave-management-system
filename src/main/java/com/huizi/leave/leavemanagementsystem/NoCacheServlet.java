package com.huizi.leave.leavemanagementsystem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 第二章 2.3.5：设置响应头禁止浏览器缓存。 */
@WebServlet("/NoCacheServlet")
public class NoCacheServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().println("此响应已设置 Expires、Pragma 和 Cache-Control，不应被浏览器缓存。");
    }
}
