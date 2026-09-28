package com.huizi.leave.leavemanagementsystem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** 第三章 3.6.6：读取 input 表单元素。 */
@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        for (String name : new String[]{"text", "password", "number", "tel", "email", "url", "hidden", "time", "month", "date", "datetime-local", "datetime", "week", "file", "range"}) {
            writer.println(name + "=" + request.getParameter(name));
        }
    }
}
