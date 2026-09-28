<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>文件上传</title><link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"></head>
<body><div class="formbody"><div class="formtitle"><span>使用组件上传文件</span></div>
<form action="${pageContext.request.contextPath}/FileUploadContentServlet" method="post" enctype="multipart/form-data"><ul class="forminfo">
<li><label>姓名</label><input name="name" type="text" class="dfinput" /></li>
<li><label>学号</label><input name="num" type="text" class="dfinput" /></li>
<li><label>文件1</label><input name="file1" type="file" class="dfinput" /></li>
<li><label>文件2</label><input name="file2" type="file" class="dfinput" /></li>
<li><label>&nbsp;</label><button class="btn">提交</button></li>
</ul></form></div></body></html>
