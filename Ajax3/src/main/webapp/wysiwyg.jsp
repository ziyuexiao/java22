<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/12
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/js/simditor/styles/simditor.css">
</head>
<body>

<div class="container">

    <form action="/send" method="post">

    <textarea id="editor" placeholder="请输入内容。。。" name="message" autofocus></textarea>
     <button class="btn btn-primary">发布</button>
    </form>
</div>

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/simditor/scripts/module.min.js"></script>
<script src="/static/js/simditor/scripts/hotkeys.min.js"></script>
<script src="/static/js/simditor/scripts/uploader.min.js"></script>
<script src="/static/js/simditor/scripts/simditor.min.js"></script>

<script>

    $(function () {

        var edit = new Simditor({

            textarea:$("#editor"),
            toolbar:true,//隐藏工具条false
            upload:{
                url:"http://up-z1.qiniu.com/",
                fileKey:"file",
                params:{"token":"${token}"}
            }

        });
    });

</script>

</body>
</html>
