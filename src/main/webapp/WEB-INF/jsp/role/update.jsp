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
    <li><a href="#">首页</a></li>
    <li><a href="RoleServlet?option=1">角色</a></li>
    <li><a href="RoleServlet?option=2">添加角色</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    <form action="RoleServlet">
    	<input type="hidden" name="option" value="${requestScope.option }">
    	<input type="hidden" name="id" value="${requestScope.role.id }">
    	
    	<ul class="forminfo">
    	<li><label>角色名</label><input name="name" type="text" value="${requestScope.role.name }" class="dfinput" /><i></i></li>
    	<li><label>备注</label><input name="remark" type="text" value="${requestScope.role.remark }" class="dfinput" /><i></i></li>
    	<li><label>管理域</label>
    	<cite>
    		<select name="eareType" style="width=200px;">
    			<c:forEach items="${applicationScope.roleTypes }" var="type">
  					<option value ="${type.id }" ${type.id == requestScope.role.eareType?'selected=selected':'' }>${type.name }</option>
  				</c:forEach>
			</select>
    	</cite>
    	</li>
    	<li><label>&nbsp;</label><input type="submit" class="btn" value="确认保存"/></li>
    </ul>
    </form>
    
    </div>


</body>

</html>
