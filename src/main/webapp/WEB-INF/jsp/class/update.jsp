<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改班级</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li><a href="ClassServlet?option=1&pageNo=1">班级信息</a></li>
    <li>修改班级</li></ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>修改班级</span></div>
    <form action="ClassServlet">
    	<input type="hidden" name="option" value="${requestScope.option }">
    	<input type="hidden" name="id" value="${requestScope.clazz.id }">
    	
    	<ul class="forminfo">
    	<li><label>班级编号</label><input name="num" type="text" class="dfinput" value="${clazz.num }" /><i>编号不能超过30个字符</i></li>
    	<li><label>所属年级</label>
    	<cite>
    		<select name="gradeId" style="width=200px;">
    			<c:forEach items="${requestScope.grades }" var="grade">
  					<option value ="${grade.id }" ${clazz.gradeId == grade.id?'selected=selected':''}>${grade.grade }</option>
  				</c:forEach>
			</select>
    	</cite>
    	</li>
    	<li><label>所属学院</label>
    	<cite>
    		<select name="belongToCollegeId" style="width=200px;">
    			<c:forEach items="${requestScope.colleges }" var="college">
  					<option value ="${college.id }" ${clazz.belongToCollegeId == college.id?'selected=selected':''}>${college.name }</option>
  				</c:forEach>
			</select>
    	</cite>
    	</li>
    	<li><label>&nbsp;</label><input type="submit" class="btn" value="确认保存"/></li>
    </ul>
    </form>
    
    </div>


</body>

</html>
