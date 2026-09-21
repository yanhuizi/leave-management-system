package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/** 第二章 2.5.11：把文件系统中的图片写入响应正文。 */
@WebServlet("/ShowImageServlet")
public class ShowImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File file;
        try {
            file = Chapter2FileSupport.resolve(getServletContext(), request.getParameter("imgPath"),
                    parsePathType(request.getParameter("pathType")));
        } catch (IOException ex) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
            return;
        }
        if (!file.isFile()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String type = getServletContext().getMimeType(file.getName());
        response.setContentType(type == null ? "application/octet-stream" : type);
        try (FileInputStream input = new FileInputStream(file)) {
            byte[] buffer = new byte[8192];
            int length;
            while ((length = input.read(buffer)) != -1) response.getOutputStream().write(buffer, 0, length);
        }
    }

    private int parsePathType(String value) {
        try { return Integer.parseInt(value); } catch (Exception ignored) { return 1; }
    }
}
