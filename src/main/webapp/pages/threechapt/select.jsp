<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>获取页面提交的参数</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <style type="text/css">
        select { width:170px; height:28px; border:solid 1px #e6e6e6; cursor:pointer; padding-right:14px; }
        select[name="department"] { height:128px; }
    </style>
</head>
<body>
<div class="formbody">
    <div class="formtitle"><span>获取页面提交的参数</span></div>
    <form action="${pageContext.request.contextPath}/SelectServlet?option=1" method="post">
        <ul class="forminfo">
            <li>
                <label>学院</label>
                <select name="college" url="${pageContext.request.contextPath}/SelectServlet?option=2" id="college">
                    <option value="" <c:if test="${empty cs}">selected="selected"</c:if>>---请选择学院---</option>
                    <option value="01" <c:if test="${cs == '01'}">selected="selected"</c:if>>文传学院</option>
                    <option value="02" <c:if test="${cs == '02'}">selected="selected"</c:if>>信息学院</option>
                    <option value="03" <c:if test="${cs == '03'}">selected="selected"</c:if>>理学院</option>
                    <option value="04" <c:if test="${cs == '04'}">selected="selected"</c:if>>化环学院</option>
                    <option value="05" <c:if test="${cs == '05'}">selected="selected"</c:if>>医学院</option>
                </select>
            </li>
            <li>
                <label>系部</label>
                <select name="department" id="department" multiple="multiple" size="3">
                    <option value="">---请选择系部---</option>
                    <c:forEach items="${dl}" var="d"><option value="${d.code}">${d.name}</option></c:forEach>
                </select>
            </li>
            <c:forEach items="${selected}" var="s"><li>${s}</li></c:forEach>
            <li style="margin-right:auto;"><input type="submit" name="save" value="保存" class="btn" /></li>
        </ul>
    </form>
</div>
<script type="text/javascript">
$(function(){
    $('#college').change(function(){
        var url = $('#college').attr('url') + '&college=' + encodeURIComponent($('#college').val());
        $.ajax({type:'POST', url:url, data:$('form').serializeArray(), contentType:'application/x-www-form-urlencoded;charset=UTF-8',
            success:function(msg){
                var result = $.parseJSON(msg);
                if(result.code === 200){
                    $('#department').empty().append('<option value="">---请选择系部---</option>');
                    $.each(result.object, function(idx, d){ $('#department').append('<option value="'+d.code+'">'+d.name+'</option>'); });
                }
            }
        });
    });
});
</script>
</body>
</html>
