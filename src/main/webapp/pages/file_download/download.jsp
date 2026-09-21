<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>文件下载</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"></head>
<body><div class="formbody"><div class="formtitle"><span>文件下载</span></div>
<ul class="forminfo">
<li><a href="${pageContext.request.contextPath}/download/chapter2-sample.txt" type="text/plain">静态文件下载示例</a></li>
<li><a href="${pageContext.request.contextPath}/FileDownloadServlet?filepath=chapter2-sample.txt&pathType=1">动态文件下载示例（D:\upload）</a></li>
<li><a href="${pageContext.request.contextPath}/FileServlet?option=1&pathType=1">打开文件管理器</a></li>
</ul></div></body></html>
