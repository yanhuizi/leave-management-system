package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 第三章 3.3：获取网络连接信息。 */
@WebServlet("/NetInformationServlet")
public class NetInformationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("getRemoteAddr", request.getRemoteAddr());
        request.setAttribute("getRemoteHost", request.getRemoteHost());
        request.setAttribute("getRemotePort", request.getRemotePort());
        request.setAttribute("getLocalAddr", request.getLocalAddr());
        request.setAttribute("getLocalName", request.getLocalName());
        request.setAttribute("getLocalPort", request.getLocalPort());
        request.setAttribute("getServerName", request.getServerName());
        request.setAttribute("getServerPort", request.getServerPort());
        request.setAttribute("getScheme", request.getScheme());
        request.setAttribute("getRequestURL", request.getRequestURL().toString());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/threeChapt/net_information.jsp")
                .forward(request, response);
    }
}
