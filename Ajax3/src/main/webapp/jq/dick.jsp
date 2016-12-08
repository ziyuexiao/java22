<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/8
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="text" id="word"> <button id="btn">翻译</button>
<div id="result"></div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
    $(function () {
        $("#btn").click(function () {

            $("#result").html("");

            var word = $("#word").val();
            var url = "http://fanyi.youdao.com/openapi.do?keyfrom=kaishengit&key=1587754017&type=data&doctype=jsonp&callback=?&version=1.1&q="+word;

            $.getJSON(url).done(function (data) {
                var array = data.basic.explains;
                for(var i =0;i< array.length;i++){
                    $("<p></p>").text(array[i]).appendTo($("#result"));
                }
            }).error(function () {
                alert("服务器异常");
            });
        }) ;
    });
</script>
</body>
</html>
