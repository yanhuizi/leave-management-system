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
<title>添加角色</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li><a href="RoleServlet?option=1">角色</a></li>
    <li><a href="RoleServlet?option=2">添加角色</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    <form action="TeacherServlet">
    	<input type="hidden" name="option" value="${requestScope.option }">
    	<ul class="forminfo">
    	<li><label>教师姓名</label><input name="name" type="text" class="dfinput" /><i>标题不能超过30个字符</i></li>
    	<li><label>教师编号</label><input name="num" type="text" class="dfinput" /><i>标题不能超过30个字符</i></li>
    	<li><label>所属学院</label>
    	<cite>
    		<select name="collegeId" style="width=200px;">
    			<option value ="">请选择学院</option>
    			<c:forEach items="${requestScope.colleges }" var="college">
  					<option value ="${college.id }">${college.name }</option>
  				</c:forEach>
			</select>
    	</cite>
    	</li>
    	<li><label>联系电话</label><input name="phone" type="text" class="dfinput" /><i>请输入电话</i></li>
    	
    	<li><label>&nbsp;</label><input type="submit" class="btn" value="确认保存"/></li>
    </ul>
    </form>
    
    </div>


</body>

</html>
