<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>文件错误</title></head>
<body><h2>文件操作失败</h2><p style="color:red">${requestScope.errormsg}</p>
<a href="${pageContext.request.contextPath}/FileServlet?option=1&pathType=1">返回文件管理</a></body></html>
