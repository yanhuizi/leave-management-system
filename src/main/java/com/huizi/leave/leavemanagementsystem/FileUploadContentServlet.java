package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** 第三章 3.8：使用 Servlet 文件上传组件保存上传文件。 */
@WebServlet("/FileUploadContentServlet")
@MultipartConfig
public class FileUploadContentServlet extends HttpServlet {
    private static final Path UPLOAD_DIRECTORY = Paths.get("D:\\upload\\temp");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Files.createDirectories(UPLOAD_DIRECTORY);
        response.setContentType("text/plain;charset=UTF-8");
        StringBuilder result = new StringBuilder();
        for (Part part : request.getParts()) {
            String submittedFileName = part.getSubmittedFileName();
            if (submittedFileName == null || submittedFileName.trim().isEmpty()) {
                result.append("普通字段").append(part.getName()).append("---->>")
                        .append(new String(readAll(part.getInputStream()), "UTF-8")).append('\n');
                continue;
            }
            String fileName = Paths.get(submittedFileName).getFileName().toString();
            if (fileName.isEmpty()) continue;
            Path target = UPLOAD_DIRECTORY.resolve(fileName).normalize();
            if (!target.getParent().equals(UPLOAD_DIRECTORY)) throw new IOException("非法文件名");
            try (InputStream input = part.getInputStream(); OutputStream output = Files.newOutputStream(target)) {
                byte[] buffer = new byte[8192];
                int length;
                while ((length = input.read(buffer)) != -1) output.write(buffer, 0, length);
            }
            result.append("文件名:").append(fileName).append("---->>文件类型:")
                    .append(part.getContentType()).append('\n');
        }
        response.getWriter().write(result.toString());
    }

    private byte[] readAll(InputStream input) throws IOException {
        try (InputStream in = input; java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) output.write(buffer, 0, length);
            return output.toByteArray();
        }
    }
}
