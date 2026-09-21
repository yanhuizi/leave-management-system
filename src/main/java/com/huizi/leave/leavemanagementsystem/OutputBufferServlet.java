package com.huizi.leave.leavemanagementsystem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 第二章 2.3.3：演示响应缓冲区管理方法。 */
@WebServlet("/OutputBufferServlet")
public class OutputBufferServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        response.setBufferSize(4096);
        response.getWriter().println("bufferSize=" + response.getBufferSize());
        response.getWriter().println("committed(before flush)=" + response.isCommitted());
        response.flushBuffer();
    }
}
