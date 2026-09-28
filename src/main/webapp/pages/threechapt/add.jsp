<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>添加学生</title><link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"></head>
<body><div class="formbody"><div class="formtitle"><span>添加学生</span></div>
<form action="${pageContext.request.contextPath}/RequestLineServlet" method="get"><ul class="forminfo">
<li><label>姓名</label><input name="name" type="text" class="dfinput" /></li>
<li><label>学号</label><input name="num" type="text" class="dfinput" /></li>
<li><label>&nbsp;</label><button class="btn">添加</button></li>
</ul></form></div></body></html>
