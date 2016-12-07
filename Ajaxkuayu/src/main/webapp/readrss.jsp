<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	<button id="btn">read rss</button>
	<script>
	(function(){
		document.querySelector("#btn").onclick = function(){
			var xmlHttp = new XMLHttpRequest();
			xmlHttp.open("get","/rss.xml");
			xmlHttp.send();
		};
	})();
	
	</script>
	
</body>
</html>