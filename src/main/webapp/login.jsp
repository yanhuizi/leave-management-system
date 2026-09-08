<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>请假管理系统 - 登录</title>
    <style>
        * { box-sizing: border-box; }
        body { margin: 0; min-height: 100vh; display: flex; align-items: center; justify-content: center;
            font-family: "Microsoft YaHei", sans-serif; background: linear-gradient(135deg, #e8f3ff, #f7f9fc); color: #243447; }
        .login-card { width: min(420px, calc(100% - 32px)); padding: 38px 40px 34px; background: #fff;
            border-radius: 16px; box-shadow: 0 14px 45px rgba(35, 85, 130, .15); }
        h1 { margin: 0 0 8px; font-size: 26px; text-align: center; }
        .subtitle { margin: 0 0 28px; color: #7b8a9a; text-align: center; font-size: 14px; }
        label { display: block; margin: 16px 0 7px; font-size: 14px; font-weight: 600; }
        input { width: 100%; padding: 12px 13px; border: 1px solid #d8e0e8; border-radius: 8px; font-size: 15px; }
        input:focus { outline: none; border-color: #2f80ed; box-shadow: 0 0 0 3px rgba(47, 128, 237, .12); }
        button { width: 100%; margin-top: 25px; padding: 12px; border: 0; border-radius: 8px; color: #fff;
            background: #2f80ed; font-size: 16px; cursor: pointer; }
        button:hover { background: #216dcc; }
        .error { margin: 0 0 8px; padding: 10px 12px; border-radius: 7px; color: #b42318; background: #fff0ef; font-size: 14px; }
        .success { margin: 0 0 8px; padding: 10px 12px; border-radius: 7px; color: #16794c; background: #ecfdf3; font-size: 14px; }
    </style>
</head>
<body>
<main class="login-card">
    <h1>请假管理系统</h1>
    <p class="subtitle">登录后开始使用系统</p>
    <c:if test="${param.registered == '1'}">
        <p class="success">注册成功，请登录</p>
    </c:if>
    <c:if test="${not empty requestScope.error}">
        <p class="error"><c:out value="${requestScope.error}"/></p>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <label for="username">账号</label>
        <input id="username" name="username" type="text" autocomplete="username" required
               value="<c:out value='${param.username}'/>">
        <label for="password">密码</label>
        <input id="password" name="password" type="password" autocomplete="current-password" required>
        <button type="submit">登 录</button>
    </form>
    <p style="margin: 18px 0 0; text-align: center; font-size: 14px;">还没有账号？
        <a href="${pageContext.request.contextPath}/register" style="color:#2f80ed; text-decoration:none;">立即注册</a>
    </p>
</main>
</body>
</html>
