<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/17
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置密码</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>

<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-sign-in"></i> 重置密码</span>
        </div>

        <form action="" class="form-horizontal" id="resetForm">

            <input type="hidden" name="id" value="${requestScope.user.id}">
            <input type="hidden" name="token" value="${requestScope.token}">

            <div class="control-group">
                <label class="control-label">新密码</label>
                <div class="controls">
                    <input type="password" name="password" id="password">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">请重复密码</label>
                <div class="controls">
                    <input type="password" name="repassword">
                </div>
            </div>

            <div class="form-actions">
                <button class="btn btn-primary" type="button" id="resetBtn">保存</button>
            </div>

        </form>



    </div>
    <!--box end-->
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.1.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/user/resetpassword.js"></script>

</body>
</html>
