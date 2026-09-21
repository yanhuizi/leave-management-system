package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 第二章 2.6.2：使用 RequestDispatcher.include 包含公共页面片段。 */
@WebServlet("/IncludeServlet")
public class IncludeServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        getServletContext().getRequestDispatcher("/chapter2/head.html").include(request, response);
        response.getWriter().println("<main style='padding:20px'><h2>IncludeServlet 主体内容</h2><p>上方导航来自另一个资源。</p></main>");
    }
}
