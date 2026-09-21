package com.huizi.leave.leavemanagementsystem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/IncludedServlet")
public class IncludedServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("forward中：这是最终响应内容。");
    }
}
