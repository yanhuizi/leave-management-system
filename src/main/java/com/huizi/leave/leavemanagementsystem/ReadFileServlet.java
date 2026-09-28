package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** 第一章 1.6.6：使用 ServletContext 读取资源文件。 */
@WebServlet("/ReadFileServlet")
public class ReadFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Properties properties = new Properties();
        try (InputStream input = getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties")) {
            if (input != null) properties.load(input);
        }
        request.setAttribute("driver", properties.getProperty("driver", ""));
        request.setAttribute("url", properties.getProperty("url", ""));
        request.setAttribute("username", properties.getProperty("username", ""));
        request.setAttribute("password", properties.getProperty("password", ""));
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/readFile.jsp").forward(request, response);
    }
}
