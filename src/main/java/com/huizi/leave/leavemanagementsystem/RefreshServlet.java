package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 第二章 2.3.4：使用 Refresh 响应头实现定时刷新。 */
@WebServlet("/RefreshServlet")
public class RefreshServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer count = (Integer) getServletContext().getAttribute("chapter2RefreshCount");
        count = count == null ? 1 : count + 1;
        getServletContext().setAttribute("chapter2RefreshCount", count);
        response.setHeader("Refresh", "1;URL=" + request.getContextPath() + "/RefreshServlet");
        response.setContentType("text/plain;charset=UTF-8");
        request.setAttribute("refreshCount", count);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/chapter2/refresh.jsp")
                .forward(request, response);
    }
}
