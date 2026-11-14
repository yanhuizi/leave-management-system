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
<script type="text/javascript" src="js/student.js"></script>

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
	$("#college").change(function(){
		getClasses();
	});
	$("#grade").change(function(){
		getClasses();
	});
});



function formSubmit(){ 
    document.queryClass.submit();
}
</script>


</head>


<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li><a href="RoleServlet?option=1">角色</a></li>
    <li><a href="RoleServlet?option=1">列表页</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <c:if test="${sessionScope.qSession.hasPermission('baseinfo.student.query')}">
    <form action="StudentServlet" name="queryClass">
    <input type="hidden" name="option" value="1">
    <input type="hidden" name="pageNo" value="1">
    <div class="tools">
    	<ul class="toolbar">
        <li>
        	<label>所属学院</label>
    		<cite>
    			<select name="collegeId" id="college" style="width=200px;height:25px;">
    				<option value ="0">全部</option>
    				
    				<c:forEach items="${requestScope.colleges }" var="college">
  						<option value ="${college.id }" ${requestScope.condition.collegeId == college.id?'selected=selected':''  }>${college.name }</option>
  					</c:forEach>
				</select>
    		</cite>
        </li>
        <li>
        	<label>所属年级</label>
    		<cite>
    			<select name="gradeId" id="grade" style="width=200px;height:25px;">
    				<option value ="0">全部</option>
    				<c:forEach items="${requestScope.grades }" var="grade">
  						<option value ="${grade.id }" ${requestScope.condition.gradeId == grade.id?'selected=selected':''  }>${grade.grade }</option>
  					</c:forEach>
				</select>
    		</cite>
        </li>
        <li>
        	<label>所属班级</label>
    		<cite>
    			<select name="classId" id="clazz" style="width=200px;height:25px;">
    				<option value ="0">全部</option>
    				<c:forEach items="${requestScope.clazzs }" var="clazz">
  						<option value ="${clazz.id }" ${requestScope.condition.classId == clazz.id?'selected=selected':''  }>${clazz.num }</option>
  					</c:forEach>
				</select>
    		</cite>
        </li>
        <li>
        	<label>学生姓名</label>
    		<cite>
    			<input type="text" name="name" value="${requestScope.condition.name }" style="height:25px;">
    		</cite>
        </li>
        <li>
        	<label>学生学号</label>
    		<cite>
    			<input type="text" name="num" value="${requestScope.condition.num }" style="height:25px;">
    		</cite>
        </li>
        <li>
        	<span><img src="images/ico06.png" onclick="formSubmit();"/></span>
        </li>
        </ul>
    </div>
    </form>
    </c:if>
    <div class="tools">
    	<ul class="toolbar">
        <li><span><img src="images/t01.png" /></span>
        	<a href="StudentServlet?option=2" >添加</a>
        </li>
        </ul>
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>姓名</th>
        <th>学号</th>
        <th>班级</th>
        <th>性别</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        	<c:forEach items="${requestScope.students }" var="student" varStatus="status">
        		<tr>
        			<td>${status.index + 1 }</td>
        			<td>${student.name }</td>
        			<td>${student.num }</td>
        			<td>
        				<a href="ClassServlet?option=9&id=${student.classId }" class="tablelink"> ${student.classNum }</a>
        			</td>
        			<td>${student.sexName }</td>
        			<td>
        				<c:if test="${sessionScope.qSession.hasPermission('baseinfo.student.update')}">
        			    	<a href="StudentServlet?option=4&id=${student.id }" class="tablelink"> 修改</a>
        			    </c:if>
        			    <a href="StudentServlet?option=8&id=${student.id }" class="tablelink"> 详情</a>
        			    <a href="StudentServlet?option=9&id=${student.id }" class="tablelink"> 密码重置</a>
        			</td>
        		</tr> 
        	</c:forEach>
        </tbody>
    </table>
    
    <%@include file="/public/show_page.jsp" %>
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
