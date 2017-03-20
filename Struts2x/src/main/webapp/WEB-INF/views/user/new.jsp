<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/17
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>USER  NEW ~~~</h1>
    <form action="/user/save" method="post">
        <input type="text" name="user.userName">
        <input type="text" name="user.address">
        <button>Save</button>
    </form>
    <ul>
        <c:forEach items="${names}" var="name">
            <li>${name}</li>
        </c:forEach>
    </ul>
</body>
</html>
