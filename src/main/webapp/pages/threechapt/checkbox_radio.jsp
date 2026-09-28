<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>获取页面提交的参数</title><link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"></head>
<body><div class="formbody"><div class="formtitle"><span>获取页面提交的参数</span></div>
<form action="${pageContext.request.contextPath}/CheckboxRadioServlet" method="post"><ul class="forminfo">
<li><label>食材</label><input type="checkbox" name="shicai" value="猪肉" checked="checked" />猪肉　<input type="checkbox" name="shicai" value="白菜" />白菜　<input type="checkbox" name="shicai" value="粉条" />粉条</li>
<li><label>匿名</label><input type="checkbox" name="anonymouscheckbox" />匿名checkbox</li>
<li><label>烹饪方式</label><input type="radio" name="penrengfangshi" value="炖" checked="checked" />炖　<input type="radio" name="penrengfangshi" value="蒸" />蒸　<input type="radio" name="penrengfangshi" value="炒" />炒</li>
<li><label>匿名</label><input type="radio" name="anonymousradio" />匿名radio</li>
<li><label>&nbsp;</label><input type="submit" name="save" value="保存" class="btn" /></li>
</ul></form></div></body></html>
