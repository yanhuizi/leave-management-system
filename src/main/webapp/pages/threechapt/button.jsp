<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>获取页面提交的参数</title><link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"></head>
<body><div class="formbody"><div class="formtitle"><span>获取页面提交的参数</span></div>
<form action="${pageContext.request.contextPath}/ButtonServlet" method="get"><ul class="forminfo">
<li><label>姓名</label><input name="name" type="text" value="西门吹雪" class="dfinput" /></li>
<li><label>口令</label><input name="password" type="text" value="天王盖地虎" class="dfinput" /></li>
<li><label>input按钮</label><input type="submit" name="save" value="保存" class="btn" /></li>
<li><label>&nbsp;</label><input type="submit" name="submit" value="提交" class="btn" /></li>
<li><label>&nbsp;</label><input type="reset" name="reset" value="重置" class="btn" /></li>
<li><label>&nbsp;</label><input type="button" name="button" value="普通按钮" class="btn" onclick="alert('普通按钮不会提交表单')" /></li>
</ul></form></div></body></html>
