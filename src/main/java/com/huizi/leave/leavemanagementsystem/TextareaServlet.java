package com.huizi.leave.leavemanagementsystem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 第三章 3.6.7：读取 textarea。 */
@WebServlet("/TextareaServlet")
public class TextareaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().println("textarea content:\n" + request.getParameter("content"));
    }
}
