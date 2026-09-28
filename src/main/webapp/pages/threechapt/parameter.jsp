<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>获取页面提交的参数</title><link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"></head>
<body><div class="formbody"><div class="formtitle"><span>获取页面提交的参数</span></div>
<form action="${pageContext.request.contextPath}/GetPerameterServlet" method="get"><ul class="forminfo">
<li><label>姓名</label><input name="name" type="text" value="西门吹雪" class="dfinput" /></li>
<li><label>姓名</label><input name="name" type="text" value="令狐冲" class="dfinput" /></li>
<li><label>姓名</label><input name="name" type="text" value="花满楼" class="dfinput" /></li>
<li><label>口令</label><input name="password" type="text" value="天王盖地虎" class="dfinput" /></li>
<li><label>口令</label><input name="password" type="text" value="宝塔镇河妖" class="dfinput" /></li>
<li><label>&nbsp;</label><input type="submit" name="submit" value="提交" class="btn" /></li>
</ul></form></div></body></html>
