<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>请假管理系统 - 注册</title>
    <style>
        * { box-sizing: border-box; }
        body { margin: 0; min-height: 100vh; display: flex; align-items: center; justify-content: center;
            font-family: "Microsoft YaHei", sans-serif; background: linear-gradient(135deg, #e8f3ff, #f7f9fc); color: #243447; }
        .card { width: min(440px, calc(100% - 32px)); padding: 34px 40px; background: #fff; border-radius: 16px;
            box-shadow: 0 14px 45px rgba(35, 85, 130, .15); }
        h1 { margin: 0 0 8px; font-size: 26px; text-align: center; }
        .subtitle { margin: 0 0 24px; color: #7b8a9a; text-align: center; font-size: 14px; }
        label { display: block; margin: 14px 0 7px; font-size: 14px; font-weight: 600; }
        input { width: 100%; padding: 11px 13px; border: 1px solid #d8e0e8; border-radius: 8px; font-size: 15px; }
        input:focus { outline: none; border-color: #2f80ed; box-shadow: 0 0 0 3px rgba(47, 128, 237, .12); }
        button { width: 100%; margin-top: 23px; padding: 12px; border: 0; border-radius: 8px; color: #fff;
            background: #2f80ed; font-size: 16px; cursor: pointer; }
        button:hover { background: #216dcc; }
        .error { margin: 0 0 8px; padding: 10px 12px; border-radius: 7px; color: #b42318; background: #fff0ef; font-size: 14px; }
        .back { margin: 18px 0 0; text-align: center; font-size: 14px; }
        a { color: #2f80ed; text-decoration: none; }
    </style>
</head>
<body>
<main class="card">
    <h1>创建账号</h1>
    <p class="subtitle">注册后即可登录请假管理系统</p>
    <c:if test="${not empty requestScope.error}">
        <p class="error"><c:out value="${requestScope.error}"/></p>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/register">
        <label for="username">账号</label>
        <input id="username" name="username" type="text" minlength="3" maxlength="20"
               pattern="[A-Za-z0-9_]+" required value="<c:out value='${param.username}'/>">
        <label for="realName">姓名（可选）</label>
        <input id="realName" name="realName" type="text" maxlength="50" value="<c:out value='${param.realName}'/>">
        <label for="password">密码</label>
        <input id="password" name="password" type="password" minlength="6" maxlength="50" autocomplete="new-password" required>
        <label for="confirmPassword">确认密码</label>
        <input id="confirmPassword" name="confirmPassword" type="password" minlength="6" maxlength="50" autocomplete="new-password" required>
        <button type="submit">注 册</button>
    </form>
    <p class="back">已有账号？<a href="${pageContext.request.contextPath}/login">返回登录</a></p>
</main>
</body>
</html>
