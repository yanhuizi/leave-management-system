<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加学院</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="RoleServlet?option=1">基本信息</a></li>
    <li><a href="RoleServlet?option=2">添加学院</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span>添加学院</span></div>
    <form action="CollegeServlet">
    	<input type="hidden" name="option" value="${requestScope.option }">
    	<ul class="forminfo">
    	<li><label>学院名称</label><input name="name" type="text" class="dfinput" /><i>学院名称</i></li>
    	<li><label>学院编号</label><input name="num" type="text" class="dfinput" /><i>学院编号</i></li>   	
    	<li><label>&nbsp;</label><input type="submit" class="btn" value="确认保存"/></li>
    </ul>
    </form>
    
    </div>


</body>

</html>
