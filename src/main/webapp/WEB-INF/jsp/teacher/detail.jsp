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
<title>教师详情</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li><a href="TeacherServlet?option=1&pageNo=1">教师信息</a></li>
    <li>教师详情</li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
   
    	<ul class="forminfo">
    		<li>
    			<table class="tablelist">
    	<thead>
    	<tr>
        	<th>教师姓名</th>
        	<th>教师编号</th>
        	<th>所属学院</th>
        	<th>电话号码</th>
        </tr>
        </thead>
        <tbody>	
        	<tr>
        		<td>${requestScope.teacher.name }</td>
        		<td>${requestScope.teacher.num}</td>
        		<td>${requestScope.teacher.collegeName}</td>
        		<td>${requestScope.teacher.phone}</td>
        	</tr> 
        </tbody>
    </table>
    
    		</li>
    		<li><label>&nbsp;</label><input type="button" onclick="history.go(-1);" class="btn" value="返回"/></li>
    	</ul>
    
    </div>


</body>

</html>
