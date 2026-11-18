<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li>个人中心</li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>我的信息</span></div>
    
    	<ul class="forminfo">
    		<li><label>身份</label><cite><c:if test="${requestScope.userType == 1}">教师</c:if><c:if test="${requestScope.userType == 2}">学生</c:if></cite></li>
    		<li><label>姓名</label><cite>${requestScope.user.name }</cite></li>
    		<li><label>编号</label><cite>${requestScope.user.num }</cite></li>
    		<c:if test="${requestScope.userType == 1}">
    		<li><label>所属学院</label><cite>${requestScope.user.collegeName }</cite></li>
    		<li><label>联系电话</label><cite>${requestScope.user.phone }</cite></li>
    		</c:if>
    		<c:if test="${requestScope.userType == 2}">
    		<li><label>所在班级</label><cite>${requestScope.user.classNum }</cite></li>
    		<li><label>性别</label><cite>${requestScope.user.sexName }</cite></li>
    		</c:if>
    		<li><label>&nbsp;</label><input type="button" class="btn" value="修改密码" onclick="window.location.href='LoginServlet?option=3';" /></li>
    	</ul>
    
    </div>

</body>

</html>
