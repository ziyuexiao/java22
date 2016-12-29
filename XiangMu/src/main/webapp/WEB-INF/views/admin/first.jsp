<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/28
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理系统首页</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="../include/adminnavbar.jsp"%>
<!--header-bar end-->

<div>


</div>
<div class="container-fluid" style="margin-top:20px">
    <table class="table">
        <thead>
        <tr>
            <th>日期</th>
            <th>新主题数</th>
            <th>新回复数</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.items}" var="count">
            <tr>
                <td>${TopicAndReplyCount.time}</td>
                <td>${TopicAndReplyCount.topicnum}</td>
                <td>${TopicAndReplyCount.replynum}</td>
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
    $(function(){
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
</script>
</body>
</html>
