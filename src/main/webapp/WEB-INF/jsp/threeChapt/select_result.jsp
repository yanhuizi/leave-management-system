<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>列表框结果</title><link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"></head>
<body><div class="formbody"><div class="formtitle"><span>列表框提交结果</span></div><ul class="forminfo"><li>学院：${requestScope.college}</li><li>系部：<c:forEach items="${requestScope.departments}" var="x">${x}　</c:forEach></li></ul></div></body></html>
