<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/29
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="../include/adminnavbar.jsp"%>
<!--header-bar end-->
<div class="container-fluid" style="margin-top:20px">
    <table class="table">
        <thead>
        <tr>
            <th>账号</th>
            <th>注册时间</th>
            <th>最后登录时间</th>
            <th>最后登录IP</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.items}" var="uservo">
            <tr>
                <td>${uservo.username}</td>
                <td>${uservo.createtime}</td>
                <td>${uservo.lastlogintime}</td>
                <td>${uservo.loginip}</td>
                <td>
                    <a href="javascript:;" id="update" rel="${uservo.userid},${uservo.userstate}">${uservo.userstate == '1'?'禁用':'恢复'}</a>
                </td>
               <%-- <td>
                    <a href="javascript:;" class="update" onClick="update(${uservo.userid},${uservo.userstate})"
                       rel="${uservo.userstate},${uservo.userid}">${uservo.userstate == '1'?'禁用':'恢复'}</a>
                </td>--%>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <div class="pagination pagination-mini pagination-centered">
        <ul id="pagination" style="margin-bottom:20px;"></ul>
    </div>
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.1.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function () {
        $("#pagination").twbsPagination({
            totalPages:${page.totalPage},
            visiblePages:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href: '?p={{number}}'
        });
    });
    document.querySelector("#update").onclick=function () {
        $.post("/admin/user",{"userid":userid,"userstate":userstate},function (json) {
            if(json.state=='success'){
                alert("修改成功");
                window.history.go(0);
            }else{
                alert(json.message);
            }

        });
    }
    /*function update(userid,userstate){
        $.post("/admin/user",{"userid":userid,"userstate":userstate},function(json){
            if(json.state=='success'){
                alert("修改成功");
                window.history.go(0);
            }else{
                alert(json.message)
            }
        });
    }*/
</script>
</body>
</html>

