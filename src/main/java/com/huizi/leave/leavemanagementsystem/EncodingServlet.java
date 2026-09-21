package com.huizi.leave.leavemanagementsystem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 第二章练习 2：对比未指定编码和显式 UTF-8 编码的响应。 */
@WebServlet("/EncodingServlet")
public class EncodingServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean correct = !"bad".equalsIgnoreCase(request.getParameter("mode"));
        if (correct) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
        } else {
            response.setContentType("text/plain");
        }
        response.getWriter().println(correct
                ? "正确：setCharacterEncoding(UTF-8) 后中文可以正常显示。"
                : "对比：这里故意没有指定字符集，浏览器可能按默认编码显示乱码。\n中华人民共和国万岁");
    }
}
