<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/8
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button id="btn">Click</button>
<div id="result"></div>

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script type="text/template" id="template">
    <div id="num-{{id}}">
        <h3>{{title}}</h3>
        <p>{{content}}</p>
    </div>
</script>
<script>
    $(function () {
        $("#btn").click(function () {
            var html = $("#template").html();
            var html = $("#template").html();
            html = html.replace("{{id}}",Math.random());
            html = html.replace("{{title}}","Hello," + new Date().getTime());
            html = html.replace("{{content}}","你好," + new Date().getTime());
            $(html).appendTo($("#result"));

        });
    });
</script>
</body>
</html>
