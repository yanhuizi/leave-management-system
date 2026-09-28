<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>请求行信息</title><link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"></head>
<body><div class="formbody"><div class="formtitle"><span>请求行信息</span></div><ul class="forminfo">
<li>getMethod：${requestScope.getMethod}</li><li>getRequestURI：${requestScope.getRequestURI}</li><li>getQueryString：${requestScope.getQueryString}</li><li>getProtocol：${requestScope.getProtocol}</li><li>getContextPath：${requestScope.getContextPath}</li>
</ul></div></body></html>
