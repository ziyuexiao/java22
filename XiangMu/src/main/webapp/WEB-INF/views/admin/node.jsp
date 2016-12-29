<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/28
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/sweetalert.css" rel="stylesheet">
    <style>
        .mt20 {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<%@include file="../include/adminnavbar.jsp"%>
<!--header-bar end-->
<div class="container-fluid mt20">
    <a href="newnode.html" class="btn btn-success">添加新节点</a>
    <table class="table">
        <thead>
        <tr>
            <th>节点名称</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${nodeList}" var="node">
            <tr>
                <td>${node.nodename}</td>
                <td>
                    <a href="/admin/nodeUpdate?nodeid=${node.id}">修改</a>
                    <a href="javascript:;" rel="${node.id}" class="delNode">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.1.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script src="/static/js/sweetalert.min.js"></script>
<script>
    $(function () {
        $(".delNode").click(function () {
            var id = $(this).attr("rel");
            /*swal({
                    title: "确定要删除该节点?",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定",
                    closeOnConfirm: false},
                function () {



            });*/
            swal("确定删除？");
            $.ajax({
                url: "/admin/nodeDel",
                type: "post",
                data: {"id": id},
                success: function (data) {
                    if (data.state == "success") {
                        swal({title: "删除成功"}, function () {
                            window.history.go(0);
                        });
                    } else {
                        swal(data.message);
                    }
                },
                error: function () {
                    swal("服务器错误，无法删除");
                }
            });

        });
    });
</script>
</body>
</html>
