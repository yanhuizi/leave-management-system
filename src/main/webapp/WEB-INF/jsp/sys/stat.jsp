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
<title>数据统计</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li>数据统计</li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基础数据统计</span></div>
    <table class="tablelist">
    	<thead>
    	<tr>
        	<th>统计项</th>
        	<th>数量</th>
        </tr>
        </thead>
        <tbody>
        	<tr><td>学院数量</td><td>${requestScope.overview.collegeCount }</td></tr>
        	<tr><td>班级数量</td><td>${requestScope.overview.classCount }</td></tr>
        	<tr><td>学生数量</td><td>${requestScope.overview.studentCount }</td></tr>
        	<tr><td>教师数量</td><td>${requestScope.overview.teacherCount }</td></tr>
        	<tr><td>角色数量</td><td>${requestScope.overview.roleCount }</td></tr>
        </tbody>
    </table>
    
    <div class="formtitle"><span>请假申请状态统计</span></div>
    <table class="tablelist">
    	<thead>
    	<tr>
        	<th>状态</th>
        	<th>数量</th>
        </tr>
        </thead>
        <tbody>
        	<tr><td>待审批</td><td>${requestScope.overview.pendingCount }</td></tr>
        	<tr><td>审批通过</td><td>${requestScope.overview.passCount }</td></tr>
        	<tr><td>已结束</td><td>${requestScope.overview.overCount }</td></tr>
        </tbody>
    </table>
    
    </div>

</body>

</html>
