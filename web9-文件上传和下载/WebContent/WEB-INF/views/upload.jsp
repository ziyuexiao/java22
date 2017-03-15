<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>

	<!-- <input type=“file” />这是表单中用来选择上传文件用的，在表单中有这个元素后，
	会会自动生成一个输入框和一个按钮，作用和awt中的FileDialog类的作用蛮像的 -->
	<h3>文件上传</h3>
    <ul>
        <li>文件上传的表单method属性值必须为post</li>
        <li>文件上传控件使用的是input元素type属性值为file</li>
        <li>将文件上传的表单form元素的enctype属性值设置为multipart/form-data</li>
    </ul>
    
	<form action="/upload" method="post" enctype="multipart/form-data">
	<input type="text" name="desc"/><br>
	<input type="file" name="file"/><br>
	<button>上传</button>
	</form>
	
</body>
</html>