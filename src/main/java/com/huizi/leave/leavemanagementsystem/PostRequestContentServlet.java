package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;

/** 第三章 3.7.2：使用 ServletInputStream 读取 URL 编码的请求正文。 */
@WebServlet("/PostRequestContentServlet")
public class PostRequestContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream input = request.getInputStream();
        response.setContentType("text/plain;charset=UTF-8");
        ServletOutputStream responseOutput = response.getOutputStream();
        try (FileOutputStream output = new FileOutputStream("D:\\postContent.dat")) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) != -1) {
                output.write(buffer, 0, length);
                responseOutput.write(buffer, 0, length);
            }
        }
        input.close();
        responseOutput.close();
    }
}
