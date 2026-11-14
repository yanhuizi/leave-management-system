<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 根路径入口：已登录直接进工作台，未登录回登录页，避免刷新/直接访问时又停在登录页。
	if (session.getAttribute("qSession") != null) {
		response.sendRedirect(request.getContextPath() + "/public/main.html");
	} else {
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
%>
