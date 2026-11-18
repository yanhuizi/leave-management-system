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
<script type="text/javascript" src="js/jquery.js"></script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
    
    
    <div class="welinfo">
    <span><img src="images/sun.png" alt="天气" /></span>
    <b>
    <c:if test="${sessionScope.qSession.userType == 1}">${sessionScope.qSession.teacher.name}</c:if>
    <c:if test="${sessionScope.qSession.userType == 2}">${sessionScope.qSession.student.name}</c:if>
    你好，欢迎使用请假管理系统</b>
    <a href="SettingServlet" target="rightFrame">个人中心</a>
    </div>
    
    <div class="welinfo">
    <span><img src="images/time.png" alt="时间" /></span>
    <i>今天是 <%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>，祝您工作学习愉快</i>
    </div>
    
    <div class="xline"></div>
    
    <ul class="iconlist">
    
    <li><img src="images/ico01.png" /><p><a href="LeaveApplicationServlet?option=2" target="rightFrame">请假申请</a></p></li>
    <li><img src="images/ico02.png" /><p><a href="LeaveApplicationServlet?option=1&pageNo=1&type=1" target="rightFrame">我的申请</a></p></li>
    <li><img src="images/ico03.png" /><p><a href="StatServlet" target="rightFrame">数据统计</a></p></li>
    <li><img src="images/ico04.png" /><p><a href="SettingServlet" target="rightFrame">个人中心</a></p></li>
    <li><img src="images/ico05.png" /><p><a href="LoginServlet?option=3" target="rightFrame">修改密码</a></p></li>
    <li><img src="images/ico06.png" /><p><a href="help.jsp" target="rightFrame">使用帮助</a></p></li> 
            
    </ul>
    
    <div class="ibox"><a class="ibtn" href="LeaveApplicationServlet?option=2" target="rightFrame"><img src="images/iadd.png" />发起新的请假申请</a></div>
    
    <div class="xline"></div>
    <div class="box"></div>
    
    <div class="welinfo">
    <span><img src="images/dp.png" alt="提醒" /></span>
    <b>请假管理系统使用指南</b>
    </div>
    
    <ul class="infolist">
    <li><span>学生可在“我的申请”中查看申请进度，审批通过后返校及时销假</span><a class="ibtn" href="LeaveApplicationServlet?option=1&pageNo=1&type=1" target="rightFrame">查看我的申请</a></li>
    <li><span>教师可查看所辖班级学生的请假申请并完成审批</span><a class="ibtn" href="LeaveApplicationServlet?option=9&pageNo=1&who=1" target="rightFrame">进入审批</a></li>
    <li><span>可修改个人密码，忘记密码请联系管理员重置</span><a class="ibtn" href="LoginServlet?option=3" target="rightFrame">修改密码</a></li>
    </ul>
    
    <div class="xline"></div>
    
    <div class="uimakerinfo"><b>请假管理系统</b>：面向学院学生与教师，覆盖请假申请、多级审批、销假及基础信息维护的全流程管理。</div>
    
    </div>
    
    

</body>

</html>
