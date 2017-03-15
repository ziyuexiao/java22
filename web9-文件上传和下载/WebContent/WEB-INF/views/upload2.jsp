<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	<form action="/upload2" method="post" enctype="multipart/form-data">
	<input type="text" name="desc"/><br>
	<input type="file" name="file"/><br>
	<button>上传</button>
	</form>
	<a href="/download?file=2b05af09-2d9d-42cc-831e-6ef03447ae2b.jpg&name=you.jpg" >下载照片</a>
	<a href="/download?file=my.pdf&name=5.pdf" >下载PDF文件</a>
	 <a href="/download?file=jsp_servlet-16.rar" >下载rar文件</a>
</body>
</html>