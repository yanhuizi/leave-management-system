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
    <form action="LeaveApplicationServlet">
    	<input type="hidden" name="option" value="${requestScope.option }">
    	<input type="hidden" name="id" value="${requestScope.application.id }">
    	
    	<ul class="forminfo">
    	<li><label>请假类型</label>
    	<cite>
    		<select name="type" style="width=200px;">
    			<c:forEach items="${applicationScope.qingJiaTypes }" var="type">
  					<option value ="${type.id }" 
  						${requestScope.application.type == type.id?'selected=selected':''  }
  					>${type.name}</option>
  				</c:forEach>
			</select>
    	</cite>
    	</li>
    	<li><label>预定路线</label><input name="path" value="${requestScope.application.path }" type="text" class="dfinput" /><i>标题不能超过30个字符</i></li>    	
    	<li><label>请假原因</label><input name="reason" value="${requestScope.application.reason }" type="text" class="dfinput" /><i>请输入电话</i></li>
    	<li><label>开始时间</label><input name="begintime" value="${requestScope.application.h5Begintime }" type="datetime-local" class="dfinput" /><i>请输入电话</i></li>
    	<li><label>结束时间</label><input name="endtime" value="${requestScope.application.h5Endtime }" type="datetime-local" class="dfinput" /><i>请输入电话</i></li>
    	
    	<li><label>&nbsp;</label><input type="submit" class="btn" value="确认保存"/></li>
    </ul>
    </form>
    
    </div>

</body>

</html>
