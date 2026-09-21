package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/** 第二章 2.5：查询和删除文件的简化文件管理系统。 */
@WebServlet("/FileServlet")
public class FileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String option = request.getParameter("option");
        if ("3".equals(option)) {
            delete(request, response);
        } else {
            list(request, response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pathType = parsePathType(request.getParameter("pathType"));
        File root = Chapter2FileSupport.root(getServletContext());
        File directory;
        try {
            directory = Chapter2FileSupport.resolve(getServletContext(), request.getParameter("filePath"), pathType);
        } catch (IOException ex) {
            request.setAttribute("errormsg", ex.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/chapter2/error.jsp").forward(request, response);
            return;
        }
        if (!directory.isDirectory()) {
            request.setAttribute("errormsg", "目录不存在：" + directory.getPath());
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/chapter2/error.jsp").forward(request, response);
            return;
        }
        File[] entries = directory.listFiles();
        List<MyFile> files = new ArrayList<>();
        if (entries != null) {
            Arrays.sort(entries, Comparator.comparing(File::isFile).thenComparing(File::getName, String.CASE_INSENSITIVE_ORDER));
            for (File file : entries) {
                String ext = extension(file.getName());
                boolean image = isImage(ext);
                String icon = image ? "/images/file_images/" + ext + ".png" : "/images/file_images/file.png";
                if (file.isDirectory()) icon = "/images/file_images/dir.png";
                files.add(new MyFile(file, icon, image, Chapter2FileSupport.relative(root, file)));
            }
        }
        request.setAttribute("files", files);
        request.setAttribute("currentPath", Chapter2FileSupport.relative(root, directory));
        request.setAttribute("pathType", 1);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/chapter2/detail.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File file;
        try {
            file = Chapter2FileSupport.resolve(getServletContext(), request.getParameter("filePath"),
                    parsePathType(request.getParameter("pathType")));
        } catch (IOException ex) {
            request.setAttribute("errormsg", ex.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/chapter2/error.jsp").forward(request, response);
            return;
        }
        if (!file.exists() || !file.delete()) request.setAttribute("errormsg", "删除失败，目录必须为空：" + file.getName());
        response.sendRedirect(request.getContextPath() + "/FileServlet?option=1&pathType=1&filePath=");
    }

    private String extension(String name) {
        int dot = name.lastIndexOf('.');
        return dot < 0 ? "file" : name.substring(dot + 1).toLowerCase();
    }

    private boolean isImage(String ext) {
        return "png".equals(ext) || "jpg".equals(ext) || "jpeg".equals(ext) || "gif".equals(ext) || "bmp".equals(ext);
    }

    private int parsePathType(String value) {
        try { return Integer.parseInt(value); } catch (Exception ignored) { return 1; }
    }
}
