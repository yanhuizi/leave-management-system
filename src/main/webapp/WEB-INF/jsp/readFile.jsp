<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>读取资源文件</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <style>li { font-size: 28px; }</style>
</head>
<body>
<div class="formbody">
    <div class="formtitle"><span>读取资源文件</span></div>
    <ul class="forminfo">
        <li>driver：${requestScope.driver}</li>
        <li>url：${requestScope.url}</li>
        <li>username：${requestScope.username}</li>
        <li>password：${requestScope.password}</li>
    </ul>
</div>
</body>
</html>
