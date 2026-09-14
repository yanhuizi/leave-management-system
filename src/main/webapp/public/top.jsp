<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>请假管理系统</title>
    <link href="<%= contextPath %>/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%= contextPath %>/js/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $('.nav li a').click(function () {
                $('.nav li a.selected').removeClass('selected');
                $(this).addClass('selected');
            });
        });
    </script>
</head>
<body style="background:url('<%= contextPath %>/images/topbg.gif') repeat-x;">

<div class="topleft">
    <a href="<%= contextPath %>/public/index.jsp" target="rightFrame">
        <img src="<%= contextPath %>/images/logo.png" title="系统首页" alt="系统首页" />
    </a>
</div>

<ul class="nav">
    <li><a href="<%= contextPath %>/public/index.jsp" target="rightFrame" class="selected">
        <img src="<%= contextPath %>/images/icon01.png" title="工作台" alt="工作台" /><h2>工作台</h2>
    </a></li>
    <li><a href="#" target="rightFrame">
        <img src="<%= contextPath %>/images/icon02.png" title="模型管理" alt="模型管理" /><h2>模型管理</h2>
    </a></li>
    <li><a href="#" target="rightFrame">
        <img src="<%= contextPath %>/images/icon03.png" title="模块设计" alt="模块设计" /><h2>模块设计</h2>
    </a></li>
    <li><a href="#" target="rightFrame">
        <img src="<%= contextPath %>/images/icon04.png" title="常用工具" alt="常用工具" /><h2>常用工具</h2>
    </a></li>
    <li><a href="#" target="rightFrame">
        <img src="<%= contextPath %>/images/icon05.png" title="文件管理" alt="文件管理" /><h2>文件管理</h2>
    </a></li>
    <li><a href="#" target="rightFrame">
        <img src="<%= contextPath %>/images/icon06.png" title="系统设置" alt="系统设置" /><h2>系统设置</h2>
    </a></li>
</ul>

<div class="topright">
    <ul>
        <li><span><img src="<%= contextPath %>/images/help.png" title="帮助" alt="帮助" class="helpimg" /></span><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
        <li><a href="<%= contextPath %>/logout" target="_parent">退出</a></li>
    </ul>
    <div class="user">
        <span><c:out value="${not empty sessionScope.realName ? sessionScope.realName : sessionScope.username}" /></span>
        <i>消息</i>
        <b>0</b>
    </div>
</div>

</body>
</html>
