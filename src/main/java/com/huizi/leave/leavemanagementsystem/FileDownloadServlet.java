package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/** 第二章 2.3.6/2.5.9：动态文件下载。 */
@WebServlet("/FileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pathType = parsePathType(request.getParameter("pathType"));
        File file;
        try {
            file = Chapter2FileSupport.resolve(getServletContext(), request.getParameter("filepath"), pathType);
        } catch (IOException ex) {
            request.setAttribute("errormsg", ex.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/chapter2/error.jsp").forward(request, response);
            return;
        }
        if (!file.isFile()) {
            request.setAttribute("errormsg", "文件不存在：" + file.getName());
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/chapter2/error.jsp").forward(request, response);
            return;
        }
        String fileName = file.getName();
        String encoded = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()).replace("+", "%20");
        String contentType = getServletContext().getMimeType(fileName);
        response.setContentType(contentType == null ? "application/octet-stream" : contentType);
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encoded);
        response.setContentLengthLong(file.length());
        try (FileInputStream input = new FileInputStream(file)) {
            byte[] buffer = new byte[8192];
            int length;
            while ((length = input.read(buffer)) != -1) response.getOutputStream().write(buffer, 0, length);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private int parsePathType(String value) {
        try { return Integer.parseInt(value); } catch (Exception ignored) { return 1; }
    }
}
