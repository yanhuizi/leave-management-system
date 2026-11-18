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
<title>修改学院</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li><a href="CollegeServlet?option=1">学院信息</a></li>
    <li>修改学院</li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    <form action="CollegeServlet">
    	<input type="hidden" name="option" value="${requestScope.option }">
    	<input type="hidden" name="id" value="${requestScope.college.id }">
    	
    	<ul class="forminfo">
    	<li><label>学院名称</label><input name="name" type="text" value="${requestScope.college.name }" class="dfinput" /><i></i></li>
    	<li><label>学院编号</label><input name="num" type="text" value="${requestScope.college.num }" class="dfinput" /><i></i></li>
    	
    	<li><label>&nbsp;</label><input type="submit" class="btn" value="确认保存"/></li>
    </ul>
    </form>
    
    </div>


</body>

</html>
