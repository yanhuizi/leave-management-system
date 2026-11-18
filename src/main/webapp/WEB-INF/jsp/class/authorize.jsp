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
<title>班级授权</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
	<style>
		.menu_module{
			padding-left:20px;
			margin-botton:5px;
		}
		
		.menu_item{
			padding-left:20px;
			margin-botton:5px;
		}
		.menu_button{
			padding-left:20px;
			margin-botton:5px;
		}
	</style>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li><a href="ClassServlet?option=1&pageNo=1">班级信息</a></li>
    <li>班级授权</li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    <form action="RoleServlet">
    	<input type="hidden" name="option" value="${requestScope.option }"/>
    	<input type="hidden" name="id" value="${requestScope.role.id}"/>
    	<c:forEach items="${requestScope.menus }" var="module" varStatus="status">
    		<div class="menu_module">
    			<input type="checkbox" name="pemission" value="${module.id }" ${requestScope.role.hasPermission(module.permission)?"checked='checked'":"" }/>${module.name }
    			<c:forEach items="${module.subMenus }" var="menuItem" varStatus="status">
    				<div class="menu_item">
    					<input type="checkbox" name="pemission" value="${menuItem.id }" ${requestScope.role.hasPermission(menuItem.permission)?"checked='checked'":""}/>${menuItem.name }
    					<c:forEach items="${menuItem.subMenus }" var="button" varStatus="status">
    						<div class="menu_button">
    							<input type="checkbox" name="pemission" value="${button.id }" ${requestScope.role.hasPermission(button.permission)?"checked='checked'":""}>${button.name }
    						</div>
    					</c:forEach>
    				</div>
    			</c:forEach>
    		</div>
    	</c:forEach>
    	<ul>
    		<li><label>&nbsp;</label><input type="submit" class="btn" value="确认保存"/></li>
    	</ul>
    </form>
    
    </div>


</body>

</html>
