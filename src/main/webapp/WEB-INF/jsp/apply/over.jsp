<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销假</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="public/index.jsp">首页</a></li>
			<li><a href="LeaveApplicationServlet?option=1&pageNo=1&type=2">我的申请</a></li>
			<li>销假</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>销假确认</span>
		</div>

		<ul class="forminfo">
			<li>
				<table class="tablelist">
					<thead>
						<tr>
							<th>学生姓名</th>
							<th>学生学号</th>
							<th>请假类型</th>
							<th>预定路线</th>
							<th>请假原因</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${requestScope.application.studentName }</td>
							<td>${requestScope.application.num }</td>
							<td>${requestScope.application.typeName }</td>
							<td>${requestScope.application.path }</td>
							<td>${requestScope.application.reason }</td>
							<td>${requestScope.application.begintime }</td>
							<td>${requestScope.application.endtime }</td>
							<td>${requestScope.application.statusName }</td>
						</tr>
					</tbody>
				</table>
			</li>
		</ul>

		<div class="formtext">
			确认销假后，本次请假申请将置为<b>结束</b>，不能再修改。是否继续？
		</div>

		<form action="LeaveApplicationServlet">
			<input type="hidden" name="option" value="${requestScope.option }">
			<input type="hidden" name="id" value="${requestScope.application.id }">
			<ul class="forminfo">
				<li><label>&nbsp;</label>
					<input type="submit" class="btn" value="确认销假" />
					<input type="button" class="btn" value="返回" onclick="history.go(-1);" />
				</li>
			</ul>
		</form>

	</div>

</body>

</html>
