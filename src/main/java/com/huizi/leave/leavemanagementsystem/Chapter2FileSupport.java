package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/** 文件下载/文件管理示例共用的安全路径解析。 */
final class Chapter2FileSupport {
    private Chapter2FileSupport() { }

    static File root(ServletContext context) throws IOException {
        String configured = context.getInitParameter("file_download_path");
        File root = configured == null || configured.trim().isEmpty()
                ? new File(System.getProperty("java.io.tmpdir"), "leave-management-files")
                : new File(configured.trim());
        if (!root.exists() && !root.mkdirs()) {
            throw new IOException("无法创建文件示例根目录：" + root);
        }
        File sample = new File(root, "chapter2-sample.txt");
        if (!sample.exists()) {
            Files.write(sample.toPath(), "第二章动态文件下载示例。\r\n".getBytes(StandardCharsets.UTF_8));
        }
        return root.getCanonicalFile();
    }

    static File resolve(ServletContext context, String value, int pathType) throws IOException {
        File root = root(context);
        if (value == null || value.trim().isEmpty()) return root;
        File candidate = pathType == 2 ? new File(value) : new File(root, value);
        candidate = candidate.getCanonicalFile();
        Path rootPath = root.toPath();
        if (!candidate.toPath().startsWith(rootPath)) {
            throw new IOException("文件路径超出示例根目录");
        }
        return candidate;
    }

    static String relative(File root, File file) throws IOException {
        return root.toPath().relativize(file.getCanonicalFile().toPath()).toString().replace(File.separatorChar, '/');
    }
}
