<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% if (session.getAttribute("userId") == null) {
       response.sendRedirect(request.getContextPath() + "/login");
       return;
   }
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head><meta charset="UTF-8"><title>请假管理系统</title></head>
<body>
<h2>登录成功</h2>
<p>你好，<%= session.getAttribute("realName") == null ? session.getAttribute("username") : session.getAttribute("realName") %>！</p>
<p><a href="<%= request.getContextPath() %>/logout">退出登录</a></p>
</body>
</html>
