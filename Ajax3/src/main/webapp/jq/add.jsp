<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/8
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/animate.css/3.5.2/animate.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form action="" id="loginForm" class="animated bounce">
        <div class="form-group">
            <lable>电子邮件</lable>
            <input type="text" class="form-control" name="mail" id="username">
        </div>
        <div class="form-group">
            <lable>密码</lable>
            <input type="password" class="form-control" name="password" id="password">
        </div>
        <div class="form-group">
            <button  class="btn btn-primary" type="button" id="loginBtn">保存</button>
        </div>
    </form>
</div>

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>

<script>
    $(function () {
        $("#loginBtn").click(function () {
            $("#loginForm").submit();
        });
        $("#loginForm").validate({
            errorElement:"span",
            errorClass:"test-danger",
            rules:{
                mail:{
                    required:true,
                    email:true,
                    remote:"/checkemail"//表单元素失去焦点的时候触发
                },
                password:{
                    required:true
                }

            },
            messages:{
                mail:{
                    required:"请输入账号",
                    email:"邮件格式错误",
                    remote:"电子邮件已被占用"
                },
                password:{
                    required:"请输入密码"
                }
            },

            submitHandler:function () {//不用刷新进行提交
                $.ajax({
                    url:"/login",
                    type:"post",
                    data:$("#loginForm").serialize(),
                    beforeSend:function () {
                        $("#loginBtn").append($("<li class='fa fa-spinner fa-spin'></li>")).attr("disabled","disabled");//登陆时不能再点第二次
                    },
                    complete:function () {
                        $("#loginBtn").html("登录").removeAttr("disabled");
                    },
                    success:function (data) {
                        if(data.state=="error"){
                            alert(data.message);
                        }else{
                            window.location.href="/jq/demo1.jsp";
                        }
                    },
                    error:function () {
                        alert("服务器错误");
                    }
                })
            }
        });
    });


</script>

</body>
</html>
