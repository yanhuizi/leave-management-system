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
<title>添加角色</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />


</head>

<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="RoleServlet?option=1">角色</a></li>
    <li><a href="RoleServlet?option=2">添加角色</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    <form action="StudentServlet">
    	<input type="hidden" name="option" value="${requestScope.option }">
    	<input type="hidden" name="id" value="${requestScope.student.id }">
    	
    	<ul class="forminfo">
    	 <li>
        	<label>所属学院</label>
    		<cite>
    			<select name="belongToCollegeId" id="college" style="width=200px;height:25px;">
    				<option value ="0">全部</option>
    				
    				<c:forEach items="${requestScope.colleges }" var="college">
  						<option value ="${college.id }" ${requestScope.student.belongToCollegeId == college.id?'selected=selected':''  }>${college.name }</option>
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
  						<option value ="${grade.id }" ${requestScope.student.gradeId == grade.id?'selected=selected':''  }>${grade.grade }</option>
  					</c:forEach>
				</select>
    		</cite>
        </li>
        <li>
        	<label>所属班级</label>
    		<cite>
    			<select name="classId" id="clazz" style="width=200px;height:25px;">
    				<option value ="0">请选择</option>
    				<c:forEach items="${requestScope.clazzs }" var="clazz">
  						<option value ="${clazz.id }" ${requestScope.student.classId == clazz.id?'selected=selected':''  }>${clazz.num }</option>
  					</c:forEach>
				</select>
    		</cite>
        </li>
        <li>
        	<label>学生姓名</label>
    		<cite>
    			<input type="text" name="name" value="${requestScope.student.name}" class="dfinput">
    		</cite>
        </li>
        <li>
        	<label>学生学号</label>
    		<cite>
    			<input type="text" name="num" id="num" value="${requestScope.student.num}" class="dfinput" >
    		</cite>
        </li>
        <li>
        	<label>性别</label>
    		<cite>	
    			<c:forEach items="${applicationScope.sexTypes }" var="sex">
  					<input type="radio" name="sex" value ="${sex.id }" ${requestScope.student.sex == sex.id?'checked=checked':''  }>${sex.name }
  				</c:forEach>
    		</cite>
        </li>
    	<li><label>&nbsp;</label><input type="submit" class="btn" value="确认保存"/>
    	    <label>&nbsp;</label><input type="button"  class="btn" value="返回"/></li>
    </ul>
    </form>
    
    </div>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/student.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#college").change(function(){
		getClasses();
	});
	$("#grade").change(function(){
		getClasses();
	});
	
	$oldNum = $("#num").val();
	
	$("#clazz").change(function(){
		var val = $("#clazz").val();
		var num = $("#clazz > option[value='"+val+"']").text();	
		$("#num").val(num)
	});
	
	$("input[type='submit']").click(function(){
		$name = $("input[name='name']").val();
		$num = $("input[name='num']").val();
		$class = $("input[name='classId']").val();
		$sexId = $("input[name='sex']").val();
		if($name == null ||"" == $name){
			alert("姓名不能为空");
			return false;
		}
		if($num == null ||"" == $num){
			alert("学号不能为空");
			return false;
		}else if($oldNum != $num){
			var postData = "option=13"+"&num="+$num;
			$result = "";
			$.ajax({
				   type: "POST",
				   url: "StudentServlet",
				   data: postData,
				   async:false,
				   success: function(msg){
					   $result = $.parseJSON(msg);
				   }
			});
			if($result.code == 200){
				alert($result.message);
				
				return false;
			}
		}
		
		if($class == 0){
			alert("班级不能为空");
			return false;
		}
		if(!($sexId ==0 ||$sexId ==1||$sexId ==2)){
			alert("性别不能为空");
			return false;
		}
		$url = $("form").attr("action");
		$.ajax({
			   type: "POST",
			   url: "StudentServlet",
			   data: $("form").serializeArray(),
			   contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			   success: function(msg){
				   var $result = $.parseJSON(msg);
				   alert($result.message);
			   }
		});
		return false;
	});
	$("input[type='button']").click(function(){
		window.location.href = "StudentServlet?option=1&pageNo=1";
	})
});


</script>
</body>

</html>
