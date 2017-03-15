<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="static/css/bootstrap.min.css" />
</head>
<body>
	
	<div class="container">
		<div class="col-md-6">
		
			<c:if test="${not empty mesage }">
				<div class="alert alert-danger">${message }</div>
			</c:if>
					
			<form action="/add" method="post">
				<div class="form-group">
						<label>书籍名称</label>
						<input type="text" name="bookname" value="${bookname}" class="form-control"/>
					</div>
					<div class="form-group">
						<label>作者</label>
						<input type="text" name="author" value="${author}"  class="form-control"/>
					</div>
					<div class="form-group">
						<label>数量</label>
						<input type="text" name="total" value="${total}"  class="form-control"/>
					</div>
					<div class="form-group">
						<label>ISBN</label>
						<input type="text" name="isbn" value="${isbn}"  class="form-control"/>
					</div>
					<div>
						<button class="btn btn-success">保存</button>
					</div>
			
			</form>
		
		</div>
	
	</div>
	
	
</body>
</html>