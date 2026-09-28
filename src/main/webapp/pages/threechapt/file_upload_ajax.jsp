<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>ajax上传文件</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <style type="text/css">img.preview { width:168px; height:126px; object-fit:cover; border:solid 1px #ccc; border-radius:10px; }</style>
</head>
<body>
<div class="formbody">
    <div class="formtitle"><span>ajax上传文件</span></div>
    <form id="imageForm" enctype="multipart/form-data" method="post">
        <ul class="forminfo">
            <li><label>姓名</label><input name="name" type="text" value="西门吹雪" class="dfinput" /></li>
            <li><label>学号</label><input name="num" type="text" value="12345" class="dfinput" /></li>
            <li><label>上传照片</label><input name="photo" type="file" accept="image/*" class="dfinput" /><input type="range" min="0" max="100" value="0" id="range" /></li>
            <li><img class="preview" alt="照片" src="" id="photoPath" style="display:none" /></li>
            <li><img class="preview" alt="照片" src="" id="photoUrl" style="display:none" /></li>
        </ul>
    </form>
</div>
<script type="text/javascript">
(function(){
    var form = document.getElementById('imageForm');
    var file = form.querySelector('input[type=file]');
    var range = document.getElementById('range');
    var photoPath = document.getElementById('photoPath');
    var photoUrl = document.getElementById('photoUrl');
    file.addEventListener('change', function(){
        if (!file.files || !file.files.length) return;
        var request = new XMLHttpRequest();
        request.onreadystatechange = function(){
            if (request.readyState !== 4) return;
            if (request.status !== 200) { alert('文件上传失败'); return; }
            var result;
            try { result = JSON.parse(request.responseText); } catch (e) { alert('服务器返回内容不是JSON'); return; }
            if (result.code === 200) {
                photoPath.src = '${pageContext.request.contextPath}/ShowImageServlet?imgPath=' + encodeURIComponent(result.object.photo) + '&pathType=2';
                photoUrl.src = result.object.photoUrl;
                photoPath.style.display = 'block';
                photoUrl.style.display = 'block';
            } else alert(result.message);
        };
        request.upload.onprogress = function(event){ if (event.lengthComputable) range.value = (event.loaded / event.total) * 100; };
        request.open('POST', '${pageContext.request.contextPath}/FileUploadAjaxServlet', true);
        request.send(new FormData(form));
    });
})();
</script>
</body>
</html>
