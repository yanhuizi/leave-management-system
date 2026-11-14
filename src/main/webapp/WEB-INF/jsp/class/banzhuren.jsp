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

function formSubmit(){ 
    document.queryTeacher.submit();
}
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li><a href="javascript:void(0);">数据表</a></li>
    <li><a href="javascript:void(0);">基本内容</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <form action="ClassServlet" name="queryTeacher">
    <input type="hidden" name="option" value="11">
    <input type="hidden" name="id" value="${requestScope.id }">
    <div class="tools">
    	<ul class="toolbar">
        <li>
        	<span>教师姓名</span>
        	<input type="text" name="teacher" value="${requestScope.name }" style="height:30px;margin-left:10px">
        </li>
        <li>
        	<span><img src="images/ico06.png" onclick="formSubmit();"/></span>
        </li>
        </ul>
    </div>
    </form>
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>姓名<i class="sort"><img src="images/px.gif" /></i></th>
        <th>所属学院</th>
        <th>电话</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.teachers }" var="teacher" varStatus="status">
        <tr>
        <td>${teacher.name }</td>
        <td>${teacher.collegeName }</td>
        <td>${teacher.phone }</td>
        <td>
        	<a href="ClassServlet?option=12&classid=${requestScope.id }&teacherid=${teacher.id }" class="tablelink">指定为班主任</a>
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
