<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/28
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>管理员登录</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<div class="header-bar">
    <div class="container">
        <a href="#" class="brand">
            <i class="fa fa-reddit-alien"></i>
        </a>
    </div>
</div>
<!--header-bar end-->
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-sign-in"></i>管理员登录</span>
        </div>

        <form action="" class="form-horizontal" id="loginForm">
            <c:if test="${param.state=='logout'}">
                <div class="alert alert-success">
                    安全退出
                </div>
            </c:if>
            <div class="control-group">
                <label class="control-label">账号</label>
                <div class="controls">
                    <input type="text" name="adminname">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">密码</label>
                <div class="controls">
                    <input type="password" name="password">
                </div>
            </div>

            <div class="form-actions">
                <button id="loginBtn" class="btn btn-primary">登录</button>
            </div>

        </form>



    </div>
    <!--box end-->
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.1.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/admin/login.js"></script>

</body>
</html>

