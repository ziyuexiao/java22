<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/17
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<%@ include file="../include/navbar.jsp"%>
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title" style="font-size: 24px"><i class="fa fa-sign-in"></i> 系统提示:</span>
        </div>
        <div class="box-padding">
            <h4 style="font-size: 18px">${requestScope.message}</h4>
        </div>


    </div>
    <!--box end-->
</div>
<!--container end-->
</body>
</html>
