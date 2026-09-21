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
    <title>系统菜单</title>
    <link href="<%= contextPath %>/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%= contextPath %>/js/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $('.menuson li').click(function () {
                $('.menuson li.active').removeClass('active');
                $(this).addClass('active');
            });

            $('.title').click(function () {
                var menu = $(this).next('ul');
                $('dd').find('ul').slideUp();
                menu.toggle();
            });
        });
    </script>
</head>
<body style="background:#f0f9fd;">

<div class="lefttop"><span></span>系统菜单</div>

<dl class="leftmenu">
    <dd>
        <div class="title">
            <span><img src="<%= contextPath %>/images/leftico01.png" alt="" /></span>管理信息
        </div>
        <ul class="menuson">
            <li class="active"><cite></cite><a href="<%= contextPath %>/public/index.jsp" target="rightFrame">首页</a><i></i></li>
            <li><cite></cite><a href="#" target="rightFrame">数据列表</a><i></i></li>
            <c:if test="${sessionScope.userType == 'student'}"><li><cite></cite><a href="<%= contextPath %>/leave" target="rightFrame">请假申请</a><i></i></li><li><cite></cite><a href="<%= contextPath %>/leave/list" target="rightFrame">我的申请</a><i></i></li></c:if>
            <c:if test="${sessionScope.userType == 'teacher' || sessionScope.userType == 'admin'}"><li><cite></cite><a href="<%= contextPath %>/approval" target="rightFrame">审批管理</a><i></i></li></c:if>
            <li><cite></cite><a href="#" target="rightFrame">用户管理</a><i></i></li>
        </ul>
    </dd>

    <dd>
        <div class="title"><span><img src="<%= contextPath %>/images/leftico02.png" alt="" /></span>第二章 HttpServletResponse</div>
        <ul class="menuson">
            <li><cite></cite><a href="<%= contextPath %>/chapter2/index.jsp" target="rightFrame">第二章示例总览</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/SetResponseStatus" target="rightFrame">setStatus 状态码</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/ResponseHeaderServlet" target="rightFrame">响应头与中文编码</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/EncodingServlet" target="rightFrame">setCharacterEncoding 编码</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/RefreshServlet" target="rightFrame">Refresh 定时刷新</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/pages/file_download/download.jsp" target="rightFrame">文件下载</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/FileServlet?option=1&pathType=1" target="rightFrame">文件管理系统</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/IncludeServlet" target="rightFrame">include 包含</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/IncludingServlet" target="rightFrame">forward 转发</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/RedirectServlet" target="rightFrame">redirect 重定向</a><i></i></li>
        </ul>
    </dd>

    <dd>
        <div class="title">
            <span><img src="<%= contextPath %>/images/leftico02.png" alt="" /></span>其他设置
        </div>
        <ul class="menuson">
            <li><cite></cite><a href="#">编辑内容</a><i></i></li>
            <li><cite></cite><a href="#">发布信息</a><i></i></li>
            <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>
    </dd>

    <dd>
        <div class="title">
            <span><img src="<%= contextPath %>/images/leftico03.png" alt="" /></span>编辑器
        </div>
        <ul class="menuson">
            <li><cite></cite><a href="#">自定义</a><i></i></li>
            <li><cite></cite><a href="#">常用资料</a><i></i></li>
            <li><cite></cite><a href="#">信息列表</a><i></i></li>
            <li><cite></cite><a href="#">其他</a><i></i></li>
        </ul>
    </dd>

    <dd>
        <div class="title">
            <span><img src="<%= contextPath %>/images/leftico04.png" alt="" /></span>日期管理
        </div>
        <ul class="menuson">
            <li><cite></cite><a href="#">自定义</a><i></i></li>
            <li><cite></cite><a href="#">常用资料</a><i></i></li>
            <li><cite></cite><a href="#">信息列表</a><i></i></li>
            <li><cite></cite><a href="#">其他</a><i></i></li>
        </ul>
    </dd>
</dl>

</body>
</html>
