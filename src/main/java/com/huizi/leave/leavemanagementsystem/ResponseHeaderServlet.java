package com.huizi.leave.leavemanagementsystem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** 第二章 2.3.1/2.3.2：演示 addHeader、setHeader 和中文响应编码。 */
@WebServlet("/ResponseHeaderServlet")
public class ResponseHeaderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("name", "taoyong");
        response.addHeader("name", "fengqinyang");
        response.setHeader("Server", "Apache");
        response.setHeader("Server", "Tomcat");
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("中华人民共和国万岁");
        writer.println("伟大的中国共产党万岁");
        writer.println("响应头 name 使用 addHeader 添加了两个值，Server 使用 setHeader 保留最后一个值。");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
