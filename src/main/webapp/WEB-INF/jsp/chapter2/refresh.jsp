<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Refresh 定时刷新</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"></head>
<body><div class="formbody"><div class="formtitle"><span>Refresh 响应头示例</span></div>
<ul class="forminfo"><li>页面已刷新 ${requestScope.refreshCount} 次</li><li>响应头 Refresh 设置为 1 秒后再次请求本 Servlet。</li></ul>
</div></body></html>
