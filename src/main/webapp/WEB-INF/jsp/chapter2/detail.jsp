<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>文件管理</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
<style>.filegrid{display:flex;flex-wrap:wrap;gap:14px;padding:20px}.file{width:190px;padding:12px;border:1px solid #ddd;background:#fff}.file img{width:64px;height:64px;object-fit:contain}.file a{word-break:break-all}</style></head>
<body><div class="place"><span>当前位置：</span>${empty currentPath ? '/' : currentPath}</div>
<div class="rightinfo"><div class="formtitle"><span>文件管理系统</span></div>
<p><a href="${pageContext.request.contextPath}/FileServlet?option=1&pathType=1&filePath=">返回根目录</a></p>
<div class="filegrid"><c:forEach items="${requestScope.files}" var="item">
<div class="file"><c:choose><c:when test="${item.imageType}"><a target="_blank" href="${pageContext.request.contextPath}/ShowImageServlet?imgPath=${item.relativePath}&pathType=1"><img src="${pageContext.request.contextPath}/ShowImageServlet?imgPath=${item.relativePath}&pathType=1" alt="图片"></a></c:when><c:otherwise><img src="${pageContext.request.contextPath}${item.iconUrl}" alt="文件"></c:otherwise></c:choose>
<p><strong>${item.file.name}</strong></p><c:choose>
<c:when test="${item.file.directory}"><a href="${pageContext.request.contextPath}/FileServlet?option=1&pathType=1&filePath=${item.relativePath}">打开</a></c:when>
<c:otherwise><a href="${pageContext.request.contextPath}/FileDownloadServlet?filepath=${item.relativePath}&pathType=1">下载</a></c:otherwise></c:choose>
<c:if test="${!item.file.directory}"> | <a href="${pageContext.request.contextPath}/FileServlet?option=3&pathType=1&filePath=${item.relativePath}">删除</a></c:if>
</div></c:forEach></div></div></body></html>
