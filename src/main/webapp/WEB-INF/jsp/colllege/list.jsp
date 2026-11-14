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
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li><a href="CollegeServlet?option=1">角色</a></li>
    <li><a href="CollegeServlet?option=1">列表页</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <c:if test="${sessionScope.qSession.hasPermission('baseinfo.college.add')}">
    <div class="tools">
    	<ul class="toolbar">
        <li class="click"><span><img src="images/t01.png" /></span>
        	<a href="CollegeServlet?option=2" >添加</a>
        </li>
        </ul>
    </div>
    </c:if>
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>学院编号</th>
        <th>学院名称</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        	<c:forEach items="${requestScope.colleges }" var="college" varStatus="status">
        		<tr>
        			<td>${status.index + 1 }</td>
        			<td>${college.num }</td>
        			<td>${college.name }</td>
        			<td>
        				<c:if test="${sessionScope.qSession.hasPermission('baseinfo.college.update')}">
        			    	<a href="CollegeServlet?option=4&id=${college.id }" class="tablelink"> 修改</a>
        				</c:if>
        				<c:if test="${sessionScope.qSession.hasPermission('baseinfo.college.detal')}">
        					<a href="CollegeServlet?option=9&id=${college.id }" class="tablelink"> 详情</a>
        				</c:if>
        			</td>
        		</tr> 
        	</c:forEach>
        </tbody>
    </table>
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input type="button"  class="sure" value="确定" />&nbsp;
        <input type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
