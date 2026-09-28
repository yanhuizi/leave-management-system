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
                if (menu.is(':visible')) {
                    menu.slideUp();
                } else {
                    menu.slideDown();
                }
            });
        });
    </script>
</head>
<body style="background:#f0f9fd;">

<div class="lefttop"><span></span>系统菜单</div>

<dl class="leftmenu">
    <dd>
        <div class="title"><span><img src="<%= contextPath %>/images/leftico01.png" alt="" /></span>第一章 servlet开发基础</div>
        <ul class="menuson">
            <li><cite></cite><a href="<%= contextPath %>/public/index.jsp" target="rightFrame">首页模版</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/ReadFileServlet" target="rightFrame">1.6.6读取资源文件</a><i></i></li>
        </ul>
    </dd>

    <dd>
        <div class="title"><span><img src="<%= contextPath %>/images/leftico02.png" alt="" /></span>第二章</div>
        <ul class="menuson">
            <li><cite></cite><a href="<%= contextPath %>/SetResponseStatus" target="rightFrame">设置状态行之setStatus</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/pages/file_download/download.jsp" target="rightFrame">文件下载</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/FileServlet?option=1&amp;pathType=1" target="rightFrame">文件管理系统</a><i></i></li>
        </ul>
    </dd>

    <dd>
        <div class="title"><span><img src="<%= contextPath %>/images/leftico03.png" alt="" /></span>第三章</div>
        <ul class="menuson">
            <li><cite></cite><a href="<%= contextPath %>/pages/threechapt/add.jsp" target="rightFrame">请求行参数</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/NetInformationServlet" target="rightFrame">网络连接信息</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/pages/threechapt/parameter.jsp" target="rightFrame">获取表单参数</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/pages/threechapt/button.jsp" target="rightFrame">按钮</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/pages/threechapt/input.jsp" target="rightFrame">input 标签</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/pages/threechapt/textarea.jsp" target="rightFrame">textarea标签</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/pages/threechapt/checkbox_radio.jsp" target="rightFrame">复选框与单选框</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/pages/threechapt/select.jsp" target="rightFrame">列表框</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/pages/threechapt/post_request_content.jsp" target="rightFrame">消息响应正文</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/pages/threechapt/file_upload_content.jsp" target="rightFrame">文件上传组件</a><i></i></li>
            <li><cite></cite><a href="<%= contextPath %>/pages/threechapt/file_upload_ajax.jsp" target="rightFrame">ajax文件上传</a><i></i></li>
        </ul>
    </dd>

    <dd>
        <div class="title">
            <span><img src="<%= contextPath %>/images/leftico04.png" alt="" /></span>日期管理
        </div>
    </dd>
</dl>

</body>
</html>
