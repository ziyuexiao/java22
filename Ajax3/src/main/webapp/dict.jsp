<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/8
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="text" id="keyword">
<button id="btn">Query</button>
<p id="result"></p>
<script>
    (function(){
        document.querySelector("#btn").onclick = function () {
            var keyword = document.querySelector("#keyword").value;

            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open("get","/dict?query="+keyword);
            xmlHttp.onreadystatechange = function(){
                if(xmlHttp.readyState == 4) {
                    if(xmlHttp.status == 200) {
                        var xmlDoc = xmlHttp.responseXML;

                        var errorCode = xmlDoc.getElementsByTagName("errorCode")[0].childNodes[0].nodeValue;
                        if(errorCode == 0) {
                            var ex = xmlDoc.getElementsByTagName("ex")[0].childNodes[0].nodeValue;
                            document.querySelector("#result").innerText = ex;
                        } else {
                            alert("ERRORCODE:" + errorCode);
                        }


                    } else {
                        alert("服务器异常:" + xmlHttp.status);
                    }
                }
            };
            xmlHttp.send();
        };
    })();
</script>
</body>
</html>
