<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
<button class="setCookie">SetCookie</button>
<button class="getCookie">getCookie</button>
<button class="removeCookie">removeCookie</button>

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/jquery.cookie.js"></script>	
<script>

$(function(){
	$(".setCookie").click(function(){
		$.cookie('playListId', '10005', { expires: 7, path: '/' });
	});
	$(".getCookie").click(function(){
        alert("PlayListID:" + $.cookie("playListId"));
        alert("SESSIONID: " + $.cookie("JSESSIONID"));
          
    });
	$(".removeCookie").click(function(){
        $.removeCookie("playListId");
    });
})


</script>	
</body>
</html>