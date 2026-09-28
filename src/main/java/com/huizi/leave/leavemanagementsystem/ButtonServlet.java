package com.huizi.leave.leavemanagementsystem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/** 第三章 3.6.5：观察 submit/reset/button 的提交行为。 */
@WebServlet("/ButtonServlet")
public class ButtonServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            writer.println(entry.getKey() + "---->" + String.join(",", entry.getValue()));
        }
    }
}
