<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>使用帮助 - 请假管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="login.jsp">登录</a></li>
    <li>使用帮助</li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>请假管理系统使用帮助</span></div>
    
    	<ul class="forminfo">
    		<li><label>登录</label><cite>学生使用学号登录，教师使用工号登录；初始密码为学号/工号，登录后可在“修改密码”中自行修改。</cite></li>
    		<li><label>请假流程</label><cite>学生提交请假申请 → 班主任审批 → 辅导员审批（如需）→ 院领导审批（如需）→ 审批通过 → 学生返校后销假。</cite></li>
    		<li><label>请假类型</label><cite>一天以内、一至三天、三天以上、出恩施州四种类型，审批环节按类型自动流转。</cite></li>
    		<li><label>角色权限</label><cite>班主任、辅导员、院领导、管理员分别拥有不同的审批与数据查看范围，由管理员在“角色管理”中授权。</cite></li>
    		<li><label>忘记密码</label><cite>请联系系统管理员，由管理员在“教师/学生信息”中执行“密码重置”，重置后密码恢复为学号/工号。</cite></li>
    	</ul>
    
    </div>

</body>

</html>
