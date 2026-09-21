package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/** 第二章 2.3.4：使用字符流模拟默认 Servlet 输出 HTML 文件。 */
@WebServlet("/ReadHtmlFileServlet")
public class ReadHtmlFileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filePath = getServletContext().getRealPath("/WEB-INF/html/ImageCarousel.html");
        if (filePath == null) {
            throw new ServletException("无法解析 HTML 示例文件路径");
        }
        response.setContentType("text/html;charset=UTF-8");
        try (Reader reader = new FileReader(filePath); Writer writer = response.getWriter()) {
            char[] buffer = new char[8192];
            int length;
            while ((length = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, length);
            }
        }
    }
}
