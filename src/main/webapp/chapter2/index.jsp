<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>第二章 HttpServletResponse</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"></head>
<body><div class="formbody"><div class="formtitle"><span>第二章 HttpServletResponse 示例</span></div>
<ul class="forminfo">
<li><a href="${pageContext.request.contextPath}/SetResponseStatus">setStatus：返回 404 状态码</a></li>
<li><a href="${pageContext.request.contextPath}/sendErrorServlet">sendError：自定义错误</a></li>
<li><a href="${pageContext.request.contextPath}/ResponseHeaderServlet">addHeader / setHeader / 中文编码</a></li>
<li><a href="${pageContext.request.contextPath}/EncodingServlet?mode=bad">乱码对比</a> / <a href="${pageContext.request.contextPath}/EncodingServlet">UTF-8 修复</a></li>
<li><a href="${pageContext.request.contextPath}/RefreshServlet">Refresh：每秒刷新计数</a></li>
<li><a href="${pageContext.request.contextPath}/NoCacheServlet">禁止浏览器缓存</a></li>
<li><a href="${pageContext.request.contextPath}/TestOutServlet">多次 getWriter</a></li>
<li><a href="${pageContext.request.contextPath}/OutputBufferServlet">响应缓冲区</a></li>
<li><a href="${pageContext.request.contextPath}/ReadHtmlFileServlet">字符流模拟默认 Servlet</a></li>
<li><a href="${pageContext.request.contextPath}/pages/file_download/download.jsp">静态/动态文件下载</a></li>
<li><a href="${pageContext.request.contextPath}/FileServlet?option=1&pathType=1">文件管理器</a></li>
<li><a href="${pageContext.request.contextPath}/IncludeServlet">include</a> / <a href="${pageContext.request.contextPath}/IncludingServlet">forward</a> / <a href="${pageContext.request.contextPath}/RedirectServlet">redirect</a></li>
</ul></div></body></html>
