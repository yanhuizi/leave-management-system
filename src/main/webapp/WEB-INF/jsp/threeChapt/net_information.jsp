<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>网络连接信息</title><link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"></head>
<body><div class="formbody"><div class="formtitle"><span>网络连接信息</span></div><ul class="forminfo">
<li>getRemoteAddr：${requestScope.getRemoteAddr}</li><li>getRemoteHost：${requestScope.getRemoteHost}</li><li>getRemotePort：${requestScope.getRemotePort}</li><li>getLocalAddr：${requestScope.getLocalAddr}</li><li>getLocalName：${requestScope.getLocalName}</li><li>getLocalPort：${requestScope.getLocalPort}</li><li>getServerName：${requestScope.getServerName}</li><li>getServerPort：${requestScope.getServerPort}</li><li>getScheme：${requestScope.getScheme}</li><li>getRequestURL：${requestScope.getRequestURL}</li>
</ul></div></body></html>
