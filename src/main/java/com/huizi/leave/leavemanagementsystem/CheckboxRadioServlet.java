package com.huizi.leave.leavemanagementsystem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 第三章 3.6.8：读取同名复选框和单选框。 */
@WebServlet("/CheckboxRadioServlet")
public class CheckboxRadioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().println("shicai=" + String.join(",", safe(request.getParameterValues("shicai"))));
        response.getWriter().println("penrengfangshi=" + request.getParameter("penrengfangshi"));
        response.getWriter().println("anonymouscheckbox=" + request.getParameter("anonymouscheckbox"));
        response.getWriter().println("anonymousradio=" + request.getParameter("anonymousradio"));
    }

    private String[] safe(String[] values) { return values == null ? new String[0] : values; }
}
