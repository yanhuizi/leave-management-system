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
<title>班级信息</title>
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
    document.queryClass.submit();
}
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="public/index.jsp">首页</a></li>
    <li><a href="ClassServlet?option=1&pageNo=1">班级信息</a></li>
    <li><a href="ClassServlet?option=1&pageNo=1">列表页</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <form action="ClassServlet" name="queryClass">
    <input type="hidden" name="option" value="1">
    <input type="hidden" name="pageNo" value="1">
    <div class="tools">
    	<ul class="toolbar">
        <li>
        	<label>所属学院</label>
    		<cite>
    			<select name="belongToCollegeId" style="width=200px;">
    				<option value ="0">请选择学院</option>
    				<c:forEach items="${requestScope.colleges }" var="college">
  						<option value ="${college.id }" ${requestScope.clazz.belongToCollegeId == college.id?'selected=selected':''  }>${college.name }</option>
  					</c:forEach>
				</select>
    		</cite>
        </li>
        <li>
        	<label>所属年级</label>
    		<cite>
    		<select name="gradeId" style="width=200px;">
    			<option value ="0">请选择年级</option>
    			<c:forEach items="${requestScope.grades }" var="grade">
  					<option value ="${grade.id }" ${requestScope.clazz.gradeId == grade.id?'selected=selected':''}>${grade.grade }</option>
  				</c:forEach>
			</select>
    		</cite>
        </li>
        <li>
        	<span><img src="images/ico06.png" onclick="formSubmit();"/></span>
        </li>
        </ul>
    </div>
    </form>
    <div class="tools">
    	<ul class="toolbar">
    	<c:if test="${sessionScope.qSession.hasPermission('baseinfo.class.add')}">
        <li class="click"><span><img src="images/t01.png" /></span>
        	<a href="ClassServlet?option=2" class="tablelink">添加</a>
        </li>
        </c:if>
        </ul>
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>班级编号</th>
        <th>所属年级</th>
        <th>所属学院</th>
        <th>班主任</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        	<c:forEach items="${requestScope.classes }" var="clazz" varStatus="status">
        		<tr>
        			<td>${status.index + 1 }</td>
        			<td>${clazz.num }</td>
        			<td>${clazz.grade }</td>
        			<td>
        				<a href="CollegeServlet?option=9&id=${clazz.belongToCollegeId }" class="tablelink"> ${clazz.belongToCollege }</a>
        			</td>
        			<td>${clazz.banZhuRen }</td>
        			<td>
        				<c:if test="${sessionScope.qSession.hasPermission('baseinfo.class.update')}">
        			    	<a href="ClassServlet?option=4&id=${clazz.id }" class="tablelink"> 修改</a>
        			    </c:if>
        			    <c:if test="${sessionScope.qSession.hasPermission('baseinfo.class.detail')}">
        			    	<a href="ClassServlet?option=9&id=${clazz.id }" class="tablelink"> 详情</a>
        			    </c:if>
        			    <c:if test="${sessionScope.qSession.hasPermission('baseinfo.class.banzhuren')}">
        			    	<a href="ClassServlet?option=10&id=${clazz.id }" class="tablelink"> 指定班主任</a>
        				</c:if>
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
