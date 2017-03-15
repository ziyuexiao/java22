<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	 <%
        pageContext.setAttribute("name","123");
        String version = (String)application.getAttribute("version");
        //User user = (User)request.getAttribute("user");
    %>

    <h2>Hello,${requestScope.name},Address:${sessionScope.address},Page:${page},Version:${applicationScope.version}</h2>
    <hr>
    <h1>FistName: ${user.firstName},LastName: ${user.lastName} Name: ${user.name}</h1>
    <h1>${1+2}</h1>
    <h1>${"abc" eq "abc"}</h1>
    <h1>${not empty names}</h1>
    <hr>
    <c:if test="${requestScope.name == 'Rose'}">
        <h1>Hello,Jack!!!</h1>
    </c:if>
    <hr>
    <c:choose>
        <c:when test="${requestScope.name == 'Rose'}">
            <h1>Hello,Rose</h1>
        </c:when>
        <c:when test="${requestScope.name == 'jack'}">
            <h1>Hello,jack</h1>
        </c:when>
        <c:otherwise>
            <h1>Hello,Other</h1>
        </c:otherwise>
    </c:choose>
    <hr>

    <ul>
        <c:forEach items="${names}" var="n">
            <li>${n}</li>
        </c:forEach>
    </ul>
	
	  ${fn:startsWith(message, "He")}
    <h2>${fn:replace(message, "Hello", "Hi")}</h2>
    <h2>${fn:join(array, ":")}</h2>
    <h2>${fn:length(array)}</h2>
    <h2>${fn:length(list)}</h2>
    <h2>${fn:escapeXml(message)}</h2>
<hr>
    <h1>${empty messages ? 'other' : messages}</h1>
    <h1><c:out value="${messages}" escapeXml="false" default="other"/></h1>

    <ul>
        <c:forEach items="${list}" var="item" varStatus="vs">
            <li>${vs.index} -> ${vs.count} -> ${vs.first} -> ${vs.last} -> ${item}</li>
        </c:forEach>
    </ul>


    <ul>
        <c:forEach items="${array}" var="item">
            <li>${item}</li>
        </c:forEach>
    </ul>

    <ul>
        <c:forEach items="${map}" var="item">
            <li>${item.key} -> ${item.value}</li>
        </c:forEach>
    </ul>
	
</body>
</html>