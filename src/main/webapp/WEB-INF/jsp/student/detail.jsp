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
<title>学生详情</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li><a href="StudentServlet?option=1&pageNo=1">学生信息</a></li>
    <li>学生详情</li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
   
    	<ul class="forminfo">
    		<li>
    	<table class="tablelist">
    	<thead>
    	<tr>
        	<th>姓名</th>
        	<th>学号</th>
        	<th>班级</th>
        	<th>性别</th>
        </tr>
        </thead>
        <tbody>	
        	<tr>
        		<td>${requestScope.student.name }</td>
        		<td>${requestScope.student.num}</td>
        		<td>
        			<a href="ClassServlet?option=9&id=${student.classId }" class="tablelink"> ${student.classNum }</a>
        		</td>
        		<td>${requestScope.student.sexName}</td>
        	</tr> 
        </tbody>
    </table>
    
    		</li>
    		<li><label>&nbsp;</label><input type="button" onclick="history.go(-1);" class="btn" value="返回"/></li>
    	</ul>
    
    </div>


</body>

</html>
