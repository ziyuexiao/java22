<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="/static/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		
		<c:if test="${not empty param.callback}">
			<div class="alert alert-danger">请登录再继续</div>
		</c:if>
		
		<c:if test="${not empty message }">
		
		<div class="alert alert-danger">${message }</div>
		
		</c:if>
			
		<form method="post" id="loginForm">
			<div class="form-group">
				<label>账号</label>
				<input type="text" name="name" id="name" class="form-control" value="${name }"/>
			</div>
			
			<div class="form-group">
				<label>密码</label>
				<input type="password" name="password" id="pwd" class="form-control"/>
			</div>
			<div class="checkbox">
		     	<label >
		     		<input type="checkbox" name="remeberme" value="remeberme" id="remeberme"/>记住账号
		     
		     	</label>
			</div>
			<div>
				<button type="button" id="logbtn" class="btn btn-success">登录</button>
			</div>
		</form>
	
	</div>
	<script src="static/js/jquery-1.11.3.min.js"></script>
	<script src="/static/js/jquery.cookie.js"></script>
	<script src="static/js/cryptojs/rollups/md5.js"></script>
	<script>
	 $(function(){
		//$("#name").val($.cookie("name"));
		$("#logbtn").click(function(){
			/* if($("#remeberme")[0].checked){
				$.cookie("name",$("#name").val(),{expires: 7, path: '/'});
			} */
			
			var password = $("#pwd").val();
			password=CryptoJS.MD5(password);
			$("#pwd").val(password);
			$("#loginForm").submit();
		});
	}); 
	
	</script>
</body>
</html>