package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** 第二章 2.6.2：演示 forward 前后的输出只有被转发资源可见。 */
@WebServlet("/IncludingServlet")
public class IncludingServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("forward前（缓冲区内容会被转发覆盖）");
        getServletContext().getRequestDispatcher("/IncludedServlet").forward(request, response);
        out.println("forward后");
    }
}
