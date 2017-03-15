<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/9
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="result"></div>
<script src="/static/js/jquery-1.11.3.min.js"></script>

<script>

    $(function () {
        var MaxId = 0;

        function call() {
            $.get("/timeline",{"MaxId":MaxId}).done(function (data) {
               if(data.length){
                   MaxId = data[0].id;

                   for(var i=0;i<data.length;i++){
                       var item = data[i];
                       var html = "<h3>"+item.message+"</h3>";

                       $(html).appendTo($("#result"));
                   }
                   maxId = data[0].id;
               }
            }).error(function () {
                alert("服务器异常");
                clearInterval(st);
            });
        }
        call();//用户一进来立即就做，没延迟
        var st = setInterval(call,5000);


    });

</script>
</body>
</html>
