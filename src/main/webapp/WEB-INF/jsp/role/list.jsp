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
<title>角色管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li><a href="RoleServlet?option=1">角色管理</a></li>
    <li><a href="RoleServlet?option=1">列表页</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <c:if test="${sessionScope.qSession.hasPermission('system.role.add')}">
    <div class="tools">
    	<ul class="toolbar">
        <li class="click"><span><img src="images/t01.png" /></span>
        	<a href="RoleServlet?option=2" class="tablelink">添加</a>
        </li>
        </ul>
    </div>
    </c:if>
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>角色名</th>
        <th>备注</th>
        <th>管理域</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        	<c:forEach items="${requestScope.roles }" var="role" varStatus="status">
        		<tr>
        			<td>${status.index + 1 }</td>
        			<td>${role.name }</td>
        			<td>${role.remark }</td>
        			<td>${role.getTypeName(applicationScope.roleTypes,role.eareType) }</td>
        			<td>
        				<c:if test="${sessionScope.qSession.hasPermission('system.role.update')}">
        			    	<a href="RoleServlet?option=4&id=${role.id }" class="tablelink"> 修改</a>
        			    </c:if>
        			    <c:if test="${sessionScope.qSession.hasPermission('system.role.menu')}">
        			    	<a href="RoleServlet?option=7&id=${role.id }" class="tablelink">授权</a>
        			    </c:if>
        			    	<c:if test="${sessionScope.qSession.hasPermission('system.role.delete')}">
        			    <a href="RoleServlet?option=6&id=${role.id }" class="tablelink"> 删除</a>
        				</c:if>
        			</td>
        		</tr> 
        	</c:forEach>
        </tbody>
    </table>
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input type="button"  class="sure" value="确定" />&nbsp;
        <input type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
