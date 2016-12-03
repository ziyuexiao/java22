<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	<h2>文件上传</h2>
	<form action="/upload" method="post" enctype="multipart/form-data">
	<input type="text" name="desc"/><br>
	<input type="file" name="file"/><br>
	<button>文件上传</button>
	</form>
	
</body>
</html>