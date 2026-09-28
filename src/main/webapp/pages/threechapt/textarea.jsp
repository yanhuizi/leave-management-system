<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>获取页面提交的参数</title><link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"></head>
<body><div class="formbody"><div class="formtitle"><span>获取页面提交的参数</span></div>
<form action="${pageContext.request.contextPath}/TextareaServlet" method="post"><ul class="forminfo">
<li><label>textarea</label><textarea name="content" rows="15" cols="80" class="textinput">请输入多行文本</textarea></li>
<li><label>&nbsp;</label><input type="submit" value="提交" class="btn" /> <input type="reset" name="reset" value="重置" class="btn" /></li>
</ul></form></div></body></html>
