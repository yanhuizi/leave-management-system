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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** 第三章 3.8.6：使用 XMLHttpRequest 异步上传照片并返回 JSON。 */
@WebServlet("/FileUploadAjaxServlet")
@MultipartConfig(maxFileSize = 50 * 1024 * 1024, maxRequestSize = 1024 * 1024 * 1024)
public class FileUploadAjaxServlet extends HttpServlet {
    private static final Path UPLOAD_DIRECTORY = Paths.get("D:\\upload\\temp");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getParameter("file");
        if (fileName == null || fileName.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Path file = UPLOAD_DIRECTORY.resolve(Paths.get(fileName).getFileName().toString()).normalize();
        if (!file.startsWith(UPLOAD_DIRECTORY) || !Files.isRegularFile(file)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String contentType = getServletContext().getMimeType(file.getFileName().toString());
        response.setContentType(contentType == null ? "application/octet-stream" : contentType);
        try (InputStream input = Files.newInputStream(file); OutputStream output = response.getOutputStream()) {
            byte[] buffer = new byte[8192];
            int length;
            while ((length = input.read(buffer)) != -1) output.write(buffer, 0, length);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            Files.createDirectories(UPLOAD_DIRECTORY);
            String name = request.getParameter("name");
            if (name == null || name.trim().isEmpty()) name = "photo";
            Part photo = request.getPart("photo");
            if (photo == null || photo.getSubmittedFileName() == null || photo.getSubmittedFileName().trim().isEmpty()) {
                writeError(response, "请选择图片文件");
                return;
            }
            String original = Paths.get(photo.getSubmittedFileName()).getFileName().toString();
            String suffix = "";
            int dot = original.lastIndexOf('.');
            if (dot >= 0) suffix = original.substring(dot).toLowerCase();
            String safeName = name.replaceAll("[^\\p{L}\\p{N}_-]", "_");
            String fileName = safeName + suffix;
            Path target = UPLOAD_DIRECTORY.resolve(fileName).normalize();
            if (!target.getParent().equals(UPLOAD_DIRECTORY)) {
                writeError(response, "文件名不合法");
                return;
            }
            try (InputStream input = photo.getInputStream(); OutputStream output = Files.newOutputStream(target)) {
                byte[] buffer = new byte[8192];
                int length;
                while ((length = input.read(buffer)) != -1) output.write(buffer, 0, length);
            }
            String encoded = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()).replace("+", "%20");
            String url = request.getContextPath() + "/FileUploadAjaxServlet?file=" + encoded;
            String absolute = target.toString().replace("\\", "\\\\").replace("\"", "\\\"");
            response.getWriter().write("{\"code\":200,\"message\":\"照片上传成功\",\"object\":{\"photo\":\"" + absolute + "\",\"photoUrl\":\"" + url + "\"}}");
        } catch (Exception ex) {
            writeError(response, "文件上传失败：" + ex.getMessage());
        }
    }

    private void writeError(HttpServletResponse response, String message) throws IOException {
        String escaped = message == null ? "文件上传失败" : message.replace("\\", "\\\\").replace("\"", "\\\"");
        response.getWriter().write("{\"code\":500,\"message\":\"" + escaped + "\",\"object\":null}");
    }
}
