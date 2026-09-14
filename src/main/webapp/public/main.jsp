<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>请假管理系统</title>
</head>
<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="<%= contextPath %>/public/top.jsp" name="topFrame"
           scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
    <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
        <frame src="<%= contextPath %>/public/left.jsp" name="leftFrame"
               scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
        <frame src="<%= contextPath %>/public/index.jsp" name="rightFrame"
               id="rightFrame" title="rightFrame" />
    </frameset>
</frameset>
<noframes>
    <body>浏览器不支持 frameset，请使用支持框架的浏览器访问。</body>
</noframes>
</html>
