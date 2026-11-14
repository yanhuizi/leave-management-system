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
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>


</head>

<body style="background:url(images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="public/main.html" target="_parent"><img src="images/logo.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">
    <li><a href="public/index.jsp" target="rightFrame" class="selected"><img src="images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
    <li><a href="javascript:void(0);" target="rightFrame" title="模板占位模块，本系统无此功能"><img src="images/icon02.png" title="模型管理" /><h2>模型管理</h2></a></li>
    <li><a href="javascript:void(0);"  target="rightFrame" title="模板占位模块，本系统无此功能"><img src="images/icon03.png" title="模块设计" /><h2>模块设计</h2></a></li>
    <li><a href="javascript:void(0);"  target="rightFrame" title="模板占位模块，本系统无此功能"><img src="images/icon04.png" title="常用工具" /><h2>常用工具</h2></a></li>
    <li><a href="javascript:void(0);" target="rightFrame" title="模板占位模块，本系统无此功能"><img src="images/icon05.png" title="文件管理" /><h2>文件管理</h2></a></li>
    <li><a href="javascript:void(0);"  target="rightFrame" title="模板占位模块，本系统无此功能"><img src="images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="images/help.png" title="帮助"  class="helpimg"/></span><a href="javascript:void(0);">帮助</a></li>
    <li><a href="LoginServlet?option=3" target="rightFrame">修改密码</a></li>
    <li><a href="LoginServlet?option=2" target="_parent">退出</a></li>
    </ul>
     
    <div class="user">
    <c:if test="${sessionScope.qSession.userType == 1}">
    	<span>欢迎你:${sessionScope.qSession.teacher.name}</span>
    </c:if>
    <c:if test="${sessionScope.qSession.userType == 2}">
    	<span>欢迎你:${sessionScope.qSession.student.name}</span>
    </c:if>
    </div>    
    
    </div>

</body>
</html>