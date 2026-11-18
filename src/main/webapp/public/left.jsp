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
<title>请假管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>菜单</div>
    
    <dl class="leftmenu">
    <c:if test="${sessionScope.qSession.hasPermission('baseinfo')}">
	<dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>基础信息
    </div>
    	<ul class="menuson">
    		<c:if test="${sessionScope.qSession.hasPermission('baseinfo.college')}">
        		<li><cite></cite><a href="CollegeServlet?option=1" target="rightFrame">学院信息</a><i></i></li>
        	</c:if>
        	<c:if test="${sessionScope.qSession.hasPermission('baseinfo.class')}">
        		<li class="active"><cite></cite><a href="ClassServlet?option=1&pageNo=1" target="rightFrame">班级信息</a><i></i></li>
        	</c:if>
        	<c:if test="${sessionScope.qSession.hasPermission('baseinfo.student')}">
        		<li><cite></cite><a href="StudentServlet?option=1&pageNo=1" target="rightFrame">学生信息</a><i></i></li>
        	</c:if>
        	<c:if test="${sessionScope.qSession.hasPermission('baseinfo.teacher')}">
        		<li><cite></cite><a href="TeacherServlet?option=1&pageNo=1" target="rightFrame">教师信息</a><i></i></li>
        	</c:if>
        </ul>    
    </dd>
    </c:if>
    <c:if test="${sessionScope.qSession.hasPermission('system')}">	
    <dd>
    <div class="title">
    <span><img src="images/leftico02.png" /></span>系统管理
    </div>
    <ul class="menuson">
    	<c:if test="${sessionScope.qSession.hasPermission('system.role')}">
        	<li><cite></cite><a href="RoleServlet?option=1" target="rightFrame">角色管理</a><i></i></li> 
        </c:if>      
     </ul>     
    </dd> 
    </c:if>
    <c:if test="${sessionScope.qSession.hasPermission('applicaton')}">	
    <dd><div class="title"><span><img src="images/leftico03.png" /></span>请假管理</div>
    <ul class="menuson">
    	<c:if test="${sessionScope.qSession.hasPermission('applicaton.myapplication')}">	
        	<li><cite></cite><a href="LeaveApplicationServlet?option=1&pageNo=1&type=1" target="rightFrame">我的申请</a><i></i></li>
        </c:if>
        <c:if test="${sessionScope.qSession.hasPermission('applicaton.banzhurenshenpi')}">	
        	<li><cite></cite><a href="LeaveApplicationServlet?option=9&pageNo=1&who=1" target="rightFrame">班主任审批</a><i></i></li>
        </c:if>
        <c:if test="${sessionScope.qSession.hasPermission('applicaton.fudaoyuanshenpi')}">	
        	<li><cite></cite><a href="LeaveApplicationServlet?option=9&pageNo=1&who=2" target="rightFrame">辅导员审批</a><i></i></li>
        </c:if>
        <c:if test="${sessionScope.qSession.hasPermission('applicaton.yuanlingdaoshenpi')}">	
        	<li><cite></cite><a href="LeaveApplicationServlet?option=9&pageNo=1&who=3" target="rightFrame">院领导审批</a><i></i></li>
    	</c:if>
    </ul>    
    </dd>  
    </c:if>
    
    <dd><div class="title"><span><img src="images/leftico04.png" /></span>快捷功能</div>
    <ul class="menuson">
        <li><cite></cite><a href="StatServlet" target="rightFrame">数据统计</a><i></i></li>
        <li><cite></cite><a href="SettingServlet" target="rightFrame">个人中心</a><i></i></li>
        <li><cite></cite><a href="LoginServlet?option=3" target="rightFrame">修改密码</a><i></i></li>
        <li><cite></cite><a href="help.jsp" target="rightFrame">使用帮助</a><i></i></li>
    </ul>
    
    </dd>   
    
    </dl>
    
</body>
</html>
