<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/31
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/sweetalert.css" rel="stylesheet">
</head>
<body>
<%@include file="../include/adminnavbar.jsp"%>
<!--header-bar end-->
<div class="container-fluid" style="margin-top:20px">
    <form action="" id="addForm">
        <legend>新增节点</legend>
        <label>节点名称</label>

        <input type="text" name="nodename" >
        <div class="form-actions">
            <button class="btn btn-primary" id="saveBtn">保存</button>
        </div>
    </form>
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.1.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/sweetalert.min.js"></script>

<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#addForm").submit();
        });
        $("#addForm").validate({
            errorElement:"span",
            errorClass:"text-error",
            rules:{
                nodename:{
                    required:true,

                }
            },
            messages:{
                nodename:{
                    required:"请输入节点名称",

                }
            },
            submitHandler:function () {
                $.ajax({
                    url:"/admin/nodeadd",
                    type:'post',
                    data:$("#addForm").serialize(),
                    success:function (json) {
                        if (json.state == "success"){
                            swal({title:"增加成功"},function () {
                                window.location.href = "/admin/node";
                            });
                        }else{
                            swal(json.message);
                        }

                    },error:function () {
                        swal("新增失败,服务器异常");
                    }
                });
            }
        });

    });
</script>
</body>
</html>
