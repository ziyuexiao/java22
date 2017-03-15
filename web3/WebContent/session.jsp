<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Date"%>
    <%@page import="java.text.DateFormat"%>
   <%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	<%
		String id = session.getId();
		//session.setMaxInactiveInterval(2);
		long createTime = session.getCreationTime();
		
		Date date = new Date(createTime);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = dateFormat.format(date);
		
	%>
	<h1>SessionID: <%= id %></h1>
	<h1>创建时间：<%=result %></h1>
	
</body>
</html>