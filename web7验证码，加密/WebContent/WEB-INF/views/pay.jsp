<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	<h3>${message}</h3>
	<form action="/pay" method="post">
		金额：<input type="text" name="money"  value="${money }"/>
		<br />
		验证码：<input type="text" name="code"/>
		<br />
		<a href="javascript:;" id="change">
		<img id="img" src="/yanzheng.png" alt="" />
		</a>
		<br />
		<button>支付</button>
	
	</form>
	<script src="/static/js/jquery-1.11.3.min.js"></script>
	<script>
	$(function(){
		$("#change").click(function(){
			$("#img").removeAttr("src").attr("src","/yanzheng.png?_="+new Date().getTime())
		});
	});
	
	</script>
</body>
</html>