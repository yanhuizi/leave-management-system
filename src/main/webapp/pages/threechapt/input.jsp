<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>获取页面提交的参数</title><link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"></head>
<body><div class="formbody"><div class="formtitle"><span>获取页面提交的参数</span></div>
<form action="${pageContext.request.contextPath}/InputServlet" method="get"><ul class="forminfo">
<li><label>text</label><input name="text" type="text" value="西门吹雪" class="dfinput" /></li>
<li><label>password</label><input name="password" type="password" value="12345" class="dfinput" /></li>
<li><label>number</label><input name="number" type="number" min="10" max="150" value="91" class="dfinput" /></li>
<li><label>tel</label><input name="tel" type="tel" value="13477229167" class="dfinput" /></li>
<li><label>email</label><input name="email" type="email" value="q123@qq.com" class="dfinput" /></li>
<li><label>url</label><input name="url" type="url" value="https://www.qq.com" class="dfinput" /></li>
<li><label>hidden</label><input name="hidden" type="hidden" value="我是隐藏字段" /></li>
<li><label>time</label><input name="time" type="time" value="13:45:56" class="dfinput" /></li>
<li><label>month</label><input name="month" type="month" value="2022-12" class="dfinput" /></li>
<li><label>date</label><input name="date" type="date" value="2022-12-13" class="dfinput" /></li>
<li><label>datetime-local</label><input name="datetime-local" type="datetime-local" class="dfinput" /></li>
<li><label>datetime</label><input name="datetime" type="text" value="2022-12-13 13:23" class="dfinput" /></li>
<li><label>week</label><input name="week" type="week" value="2022-W12" class="dfinput" /></li>
<li><label>file</label><input name="file" type="file" class="dfinput" /></li>
<li><label>range</label><input name="range" type="range" min="0" max="100" value="30" /></li>
<li><label>&nbsp;</label><input type="submit" name="submit" value="提交" class="btn" /></li>
</ul></form></div></body></html>
