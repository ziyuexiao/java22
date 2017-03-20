<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/20
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/file/upload" method="post" enctype="multipart/form-data">
    <input type="text" name="uName">
    <input type="file" name="doc">
    <input type="file" name="doc">
    <button>Upload</button>
</form>
<a href="/file/download">点此下载</a>
</body>
</html>
