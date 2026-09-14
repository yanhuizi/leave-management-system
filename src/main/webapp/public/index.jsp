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
    <title>工作台</title>
    <link href="<%= contextPath %>/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%= contextPath %>/js/jquery.js"></script>
</head>
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul"><li><a href="#">首页</a></li></ul>
</div>

<div class="mainindex">
    <div class="welinfo">
        <span><img src="<%= contextPath %>/images/sun.png" alt="天气" /></span>
        <b><c:out value="${not empty sessionScope.realName ? sessionScope.realName : sessionScope.username}" />，欢迎使用请假管理系统</b>
        <a href="#">帐号设置</a>
    </div>

    <div class="welinfo">
        <span><img src="<%= contextPath %>/images/time.png" alt="时间" /></span>
        <i>欢迎按照老师的 Servlet、JSP、JDBC 流程继续完成系统。</i>
    </div>

    <div class="xline"></div>

    <ul class="iconlist">
        <li><img src="<%= contextPath %>/images/ico01.png" alt="管理设置" /><p><a href="#">管理设置</a></p></li>
        <li><img src="<%= contextPath %>/images/ico02.png" alt="请假申请" /><p><a href="#">请假申请</a></p></li>
        <li><img src="<%= contextPath %>/images/ico03.png" alt="审批管理" /><p><a href="#">审批管理</a></p></li>
        <li><img src="<%= contextPath %>/images/ico04.png" alt="文件上传" /><p><a href="#">文件上传</a></p></li>
        <li><img src="<%= contextPath %>/images/ico05.png" alt="目录管理" /><p><a href="#">目录管理</a></p></li>
        <li><img src="<%= contextPath %>/images/ico06.png" alt="查询" /><p><a href="#">查询</a></p></li>
    </ul>

    <div class="ibox"><a class="ibtn" href="#"><img src="<%= contextPath %>/images/iadd.png" alt="" />添加新的快捷功能</a></div>
    <div class="xline"></div>

    <div class="welinfo">
        <span><img src="<%= contextPath %>/images/dp.png" alt="提醒" /></span>
        <b>老师课程路线</b>
    </div>
    <ul class="infolist">
        <li><span>Servlet 接收表单参数</span><a class="ibtn" href="#">开始学习</a></li>
        <li><span>JSP 与 RequestDispatcher 请求转发</span><a class="ibtn" href="#">开始学习</a></li>
        <li><span>JDBC 连接 qingjia 数据库</span><a class="ibtn" href="#">开始学习</a></li>
    </ul>
</div>

</body>
</html>
