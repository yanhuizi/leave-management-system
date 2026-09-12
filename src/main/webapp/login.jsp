<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
    String loginAction = contextPath + "/login";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>欢迎登录后台管理系统</title>
    <link href="<%= contextPath %>/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%= contextPath %>/js/jquery.js"></script>
    <script type="text/javascript" src="<%= contextPath %>/js/cloud.js"></script>
    <script type="text/javascript">
        $(function () {
            function centerLoginBox() {
                $('.loginbox').css({
                    position: 'absolute',
                    left: ($(window).width() - 692) / 2
                });
            }

            centerLoginBox();
            $(window).resize(centerLoginBox);
        });
    </script>
</head>

<body style="background-color:#1c77ac; background-image:url('<%= contextPath %>/images/light.png'); background-repeat:no-repeat; background-position:center top; overflow:hidden;">

<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>

<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
    <ul>
        <li><a href="#">回首页</a></li>
        <li><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
    </ul>
</div>

<div class="loginbody">
    <span class="systemlogo"></span>

    <div class="loginbox">
        <c:if test="${not empty requestScope.error}">
            <div style="margin-bottom:10px; color:#fff; text-align:center;">
                <c:out value="${requestScope.error}" />
            </div>
        </c:if>

        <!-- 登录必须交给 LoginServlet 处理，不能用 onclick 直接跳 main.html。 -->
        <form action="<%= loginAction %>" method="post">
            <ul>
                <li>
                    <input name="username" type="text" class="loginuser"
                           value="<c:out value='${param.username}' />"
                           placeholder="学号或工号" autocomplete="username" required />
                </li>
                <li>
                    <input name="password" type="password" class="loginpwd"
                           placeholder="密码" autocomplete="current-password" required />
                </li>
                <li>
                    <input name="rememberMe" type="checkbox" value="true" checked="checked" />
                    <label>记住密码</label>
                    <label><a href="#">忘记密码？</a></label>
                    <button type="submit" class="loginbtn">登录</button>
                </li>
            </ul>
        </form>
    </div>
</div>

<div class="loginbm">
    版权所有 2013
    <a href="http://www.uimaker.com">uimaker.com</a>
    仅供学习交流，勿用于任何商业用途
</div>

</body>
</html>
