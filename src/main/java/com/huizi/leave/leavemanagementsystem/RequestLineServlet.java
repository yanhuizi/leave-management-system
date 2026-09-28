package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 第三章 3.2：获取 HTTP 请求行信息。 */
@WebServlet("/RequestLineServlet")
public class RequestLineServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("getMethod", request.getMethod());
        request.setAttribute("getRequestURI", request.getRequestURI());
        request.setAttribute("getQueryString", request.getQueryString());
        request.setAttribute("getProtocol", request.getProtocol());
        request.setAttribute("getContextPath", request.getContextPath());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/threeChapt/requestline.jsp")
                .forward(request, response);
    }
}
