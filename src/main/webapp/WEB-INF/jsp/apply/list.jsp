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
    document.queryClass.submit();
}
</script>


</head>


<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="LeaveApplicationServlet?option=1&pageNo=1&who=1">我的申请</a></li>
    <li><a href="LeaveApplicationServlet?option=1&pageNo=1&who=1">列表页</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <c:if test="${!requestScope.shenpi}">
    <c:if test="${sessionScope.qSession.hasPermission('applicaton.myapplication.query')}">    
    <form action="LeaveApplicationServlet" name="queryClass">
    <input type="hidden" name="option" value="${requestScope.option }">
    <input type="hidden" name="pageNo" value="1">
    <div class="tools">
    	<ul class="toolbar">
        <li>
        	<label>申请状态</label>
    		<cite>
    			<select name="type" style="width=200px;height:25px;">
    				<option value ="0">全部</option>   				
  						<option value ="1" ${requestScope.type == 1?'selected=selected':''  }>审批中</option>
  						<option value ="2" ${requestScope.type == 2?'selected=selected':''  }>审批通过</option>
  						<option value ="3" ${requestScope.type == 3?'selected=selected':''  }>结束</option>
				</select>
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
        <li ><span><img src="images/t01.png" /></span>
        	<a href="LeaveApplicationServlet?option=2" >添加</a>
        </li>
        </ul>
    </div>
    </c:if>
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>姓名</th>
        <th>学号</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>状态</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        	<c:forEach items="${requestScope.appliations }" var="applicaton" varStatus="status">
        		<tr>
        			<td>${status.index + 1 }</td>
        			<td>
        				<a href="StudentServlet?option=8&id=${applicaton.studentId }"> ${applicaton.studentName }</a>
        			</td>
        			<td>${applicaton.num }</td>
        			<td>
        				${applicaton.begintime }
        			</td>
        			<td>
        				${applicaton.endtime }
        			</td>
        			<td>${applicaton.statusName }</td>
        			<td>
        				<c:if test="${sessionScope.qSession.hasPermission('applicaton.myapplication.update') && applicaton.status==1}">
        			    	<a href="LeaveApplicationServlet?option=4&id=${applicaton.id }" class="tablelink"> 修改</a>
        			    </c:if>
        			    <c:if test="${sessionScope.qSession.hasPermission('applicaton.myapplication.update') && applicaton.status==4 }">
        			    <a href="LeaveApplicationServlet?option=6&id=${applicaton.id }" class="tablelink">销假</a>
        			    </c:if>
        			    <c:if test="${sessionScope.qSession.hasPermission('applicaton.myapplication.detail')}">
        			    <a href="LeaveApplicationServlet?option=8&id=${applicaton.id }" class="tablelink"> 详情</a>
        			    </c:if>
        			    <c:if test="${sessionScope.qSession.hasPermission('applicaton.shenpi') && applicaton.status<4}">
        			    <a href="LeaveApplicationServlet?option=10&id=${applicaton.id }" class="tablelink">审批</a>
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
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
