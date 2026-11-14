package com.huizi.leave.leavemanagementsystem.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageUtil {
	public static void  showMessage(String title,String message,String url,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", title);
		request.setAttribute("message", message);
		request.setAttribute("url", url);
		request.getRequestDispatcher("public/message.jsp").forward(request, response);
	}
}
