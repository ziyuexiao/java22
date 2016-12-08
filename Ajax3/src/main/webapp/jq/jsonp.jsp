<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/8
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<button id="btn">JSONP</button>
<script src="/static/js/jquery-1.11.3.min.js"></script>

<script>
    $(function () {
        $("#btn").click(function () {
            $.getJSON("/jsonp?method=?").done(function (data) {
                alert(data.username + " -> " + data.address);
            }).error(function () {
                alert("服务器异常");
            });
        });
    });

</script>



<%--<script>
    function callback(user) {
        alert("hello," + user.username);
    }
</script>
<script src="/jsonp?method=callback"></script>--%>
</body>
</html>
