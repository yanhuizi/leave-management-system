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
<title>角色详情</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li><a href="RoleServlet?option=1">角色管理</a></li>
    <li>角色详情</li>
    </ul>
    </div>

    <div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>

    <ul class="forminfo">
    	<li><label>角色名称</label><cite>${requestScope.role.name }</cite></li>
    	<li><label>备注</label><cite>${requestScope.role.remark }</cite></li>
    	<li><label>管理域</label><cite>${requestScope.role.getTypeName(applicationScope.roleTypes,requestScope.role.eareType) }</cite></li>
    	<li><label>&nbsp;</label>
    		<input type="button" class="btn" value="返回" onclick="history.go(-1);" />
    	</li>
    </ul>

    </div>

</body>

</html>
