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
<title>请假详情</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="public/index.jsp">首页</a></li>
			<li><a href="RoleServlet?option=1">请假</a></li>
			<li><a href="RoleServlet?option=2">请假详情</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>请假基本信息</span>
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
							<td><a href="StudentServlet?option=8&id=${application.studentId }"
								class="tablelink"> ${application.studentName }</a></td>							
							<td>${requestScope.application.num }</td>
							<td>${requestScope.application.typeName }</td>
							<td>${requestScope.application.path}</td>
							<td>${requestScope.application.reason}</td>
							<td>${requestScope.application.begintime}</td>
							<td>${requestScope.application.endtime}</td>
							<td>${requestScope.application.statusName}</td>							
						</tr>
					</tbody>
				</table>
			</li>
		</ul>

	</div>

<div class="formtitle">
	<span>审批情况</span>
</div>
		<ul class="forminfo">
			<li>
				<table class="tablelist">
					<thead>
						<tr>
							<th>审批人</th>
							<th>审批结果</th>
							<th>审批意见</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.shenPies }" var="shenpi" varStatus="status">
						<tr>
							<td><a href="TeacherServlet?option=8&id=${shenpi.teacherId }" 
								class="tablelink"> ${shenpi.teacherName }</a></td>							
							<td>${shenpi.aproveTypeName }</td>
							<td>${shenpi.opinion }</td>							
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</li>
			<li><label>&nbsp;</label><input type="button"
				onclick="history.go(-1);" class="btn" value="返回" /></li>
		</ul>
	<c:if test="${requestScope.shenpi}">
	<div class="formtitle">
		<span>审批</span>
	</div>
	<div>
		<div class="formtext">
			Hi，<b>${sessionScope.qSession.teacher.name}</b>，请你审批！
		</div>
		<form action="LeaveApplicationServlet">
			<input type="hidden" name="option" value="${requestScope.option}">
			<input type="hidden" name="id"   value="${requestScope.application.id}">
			<input type="hidden" name="type" value="${requestScope.application.type}">
			<input type="hidden" name="status" value="${requestScope.application.status}">
			<ul class="forminfo">
				<li><label>是否同意<b>*</b></label> 
					<input name="result" type="radio"  value="1" checked="checked"/>同意 
					 <input name="result" type="radio" value="2"/>不同意
				</li>
				<li>
					<label>审批意见<b>*</b></label> 
					<textarea name="opinion" rows="14" cols="120" style="border:5px solid red;" >
					</textarea>
				</li>
				<li><label>&nbsp;</label>
					<input type="submit" class="btn" value="保存" />
				</li>
			</ul>
		</form>

	</div>
	</c:if>
</body>

</html>
