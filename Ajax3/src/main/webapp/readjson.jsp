<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/7
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <button id="btn">load json</button>

    <script>
        (function () {
            document.querySelector("#btn").onclick = function () {
                var xmlHttp = new XMLHttpRequest();
                xmlHttp.open("get","/data.json");
                xmlHttp.onreadystatechange = function () {
                    if(xmlHttp.readyState==4){
                        if(xmlHttp.status==200){
                            //获取服务端返回的字符串
                            var result = xmlHttp.responseText;
                            //将字符串转换为json
                            var json = JSON.parse(result);
                            for(var i = 0;i<json.length;i++){
                                var user = json[i];
                                alert(user.id + " -> " + user.username + " -> " + user.address);
                            }
                        }
                    }
                }
                xmlHttp.send();
            }
        })();
        
        
    </script>

</body>
</html>
