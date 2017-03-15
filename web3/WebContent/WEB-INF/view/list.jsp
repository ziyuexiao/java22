<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kaishengit.entity.Book"%>
<%@page import="java.util.List"%>
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
		
		<a href="/add" class="btn btn-primary">添加书籍</a>
		<a href="/logou" class="btn btn-default">安全退出</a>
	
		<table class="table">
			<thead>
				<tr>
					<th>书籍名称</th>
					<th>作者</th>
					<th>ISBN</th>
					<th>总数量</th>
					<th>当前数量</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty page.items }">
				<tr>
				<td colspan="6">暂无数据</td>
				</tr>
				</c:if>
				
				<c:forEach items="${page.items}" var="book">
				<tr>
					<td>${book.bookname}</td>
					<td>${book.author }</td>
					<td>${book.isbn }</td>
					<td>${book.total }</td>
					<td>${book.nownumber }</td>
					<td>
						<a href="/edit?id=${book.id }">修改</a>
						<a href="javascript:;" rel="${book.id }" class="del">删除</a>
					</td>
					
				</tr>
				</c:forEach>	
			
			</tbody>
		</table>
	
	 <nav>
	  <ul class="pagination pull-right">
	  <c:choose>
	  	<c:when test="${page.pageNo==1 }">
	  	<li class = "disabled"><a href="javascript:;">首页</a></li>
	    <li class = "disabled"><a href="javascript:;">上一页</a></li>
	  	</c:when>
	    <c:otherwise>
	     <li><a href="/list">首页</a></li>
	    <li><a href="/list?p=${page.pageNo-1}">上一页</a></li>
	    </c:otherwise>
	  </c:choose>
	   <c:choose>
	  	<c:when test="${page.pageNo==page.totalPage}">
	  	<li class = "disabled"><a  href="javascript:;">下一页</a></li>
	    <li class = "disabled"><a href="javascript:;">末页</a></li>
	  	</c:when>
	    <c:otherwise>
	    <li><a href="/list?p=${page.pageNo+1}">下一页</a></li>
	    <li><a href="/list?p=${page.totalPage}">末页</a></li>
	    </c:otherwise>
	  </c:choose>
	    
	  </ul>
	</nav>
	 <nav>
        <ul id="pagination" class="pagination pull-right"></ul>
     </nav>
	</div>
	<script src="/static/js/jquery-1.11.3.min.js"></script>
	<script src="/static/js/jquery.twbsPagination.min.js"></script>
	<script>
	
		$(function(){
			
			/* 分页插件的使用 */
			
			$("#pagination").twbsPagination({
				totalPages:${page.totalPage},
				 
				
				visiblePages:5,
				//href:"/list?p={{number}}",
				first:"首页",
                prev:"上一页",
                next:"下一页",
                last:"末页"
			});
			
			
			
			
			
			$(".del").click(function(){
				
				if(confirm("确定要删除吗?")) {
					var id = $(this).attr("rel");
					window.location.href = "/del?id=" + id;
				}
				
			});
			
			
		});
	
	
	
	
	</script>
	
</body>
</html>