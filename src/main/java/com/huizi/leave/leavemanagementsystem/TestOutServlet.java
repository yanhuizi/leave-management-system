package com.huizi.leave.leavemanagementsystem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** 第二章 2.3.2：同一响应中多次 getWriter 返回同一个对象。 */
@WebServlet("/TestOutServlet")
public class TestOutServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter first = response.getWriter();
        PrintWriter second = response.getWriter();
        first.println(first == second ? "两次 getWriter 返回的是同一个 PrintWriter。" : "返回了不同的 PrintWriter。");
        second.println("第二次调用也可以继续输出。");
    }
}
