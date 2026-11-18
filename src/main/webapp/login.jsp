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
<script src="js/cloud.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function(){
    	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    });  
});  
</script>
 

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">

<script type="text/javascript">
	//被登录拦截器重定向到登录页时，如果登录页出现在框架内，跳转到顶层窗口，避免界面显示不全。
	if (window.top !== window.self) {
		window.top.location.href = window.location.href;
	}
</script>

    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录请假管理系统</span>    
    <ul>
    <li><a href="login.jsp">回首页</a></li>
    <li><a href="help.jsp">帮助</a></li>
    <li><a href="help.jsp">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    <form action="LoginServlet">
    	<ul>
			<li><input name="option" type="hidden" class="loginuser" value="1"/></li>
   		 	<li><input name="num" type="text" class="loginuser" placeholder="请输入学号 / 工号" /></li>
    	 	<li><input name="password" type="password" class="loginpwd" placeholder="请输入密码" /></li>
    	 	<li><input type="submit" class="loginbtn" value="登录"  /><label><input type="checkbox" value="" checked="checked" />记住密码</label><label><a href="help.jsp">忘记密码？</a></label></li>   
		</ul>
    </form>
    </div>
    </div>
    <div class="loginbm">请假管理系统  仅供学习交流使用</div>
	
</body>

</html>
