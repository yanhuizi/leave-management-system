package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

/** 第三章 3.6.2-3.6.4：使用四种方法读取表单参数。 */
@WebServlet("/GetPerameterServlet")
public class GetPerameterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        writeParameters(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        writeParameters(request, response);
    }

    private void writeParameters(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("getParameter / getParameterValues / getParameterNames / getParameterMap");
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            for (String value : values) writer.println(name + "---->" + value);
        }
        writer.println("--- parameterMap ---");
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            writer.println(entry.getKey() + "=" + String.join(",", entry.getValue()));
        }
    }
}
