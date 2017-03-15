<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kaishengit.entity.Book"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="/static/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		
		<div class="row">
			<div class="col-md-6">
				
				<form action="/edit" method="post">
					<legend>修改书籍</legend>
					<div class="form-group">
						<label>书籍名称</label>
						<input type="hidden" name="id" value="${book.id }" class="form-control"/>
						<input type="text" name="name" value="${book.bookname }" class="form-control"/>
					</div>
					<div class="form-group">
						<label>作者</label>
						<input type="text" name="author" value="${book.author }"  class="form-control"/>
					</div>
					<div class="form-group">
						<label>数量</label>
						<input type="text" name="total" value="${book.total }"  class="form-control"/>
					</div>
					<div class="form-group">
						<label>当前数量</label>
						<input type="text" name="nownumber" value="${book.nownumber }"  class="form-control"/>
					</div>
					<div class="form-group">
						<label>ISBN</label>
						<input type="text" name="isbn" value="${book.isbn }"  class="form-control"/>
					</div>
					<div>
						<button class="btn btn-success">保存</button>
					</div>
				</form>
			
			</div>
		</div>
	
	</div>
	
</body>
</html>