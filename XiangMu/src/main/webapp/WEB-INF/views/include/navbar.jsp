<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/15
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--导航条--%>
<div class="header-bar">
    <div class="container">

        <a href="/home" class="brand">
            <i class="fa fa-reddit-alien"></i>
        </a>

        <ul class="unstyled inline pull-right">
            <c:choose>
                <c:when test="${not empty sessionScope.curr_user}">
                    <li>
                        <a href="setting">
                            <img id="navbar_avatar" src="http://oi0x10ek3.bkt.clouddn.com/${sessionScope.curr_user.avatar}?imageView2/1/w/20/h/20" class="img-circle" alt="">
                        </a>
                    </li>
                    <li>
                        <a href=""><i class="fa fa-plus"></i></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-bell"></i></a>
                    </li>
                    <li>
                        <a href="/setting"><i class="fa fa-cog"></i></a>
                    </li>
                    <li>
                        <a href="logout"><i class="fa fa-sign-out"></i></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="/login"><i class="fa fa-sign-in"></i></a>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>
</div>
<!--header-bar end-->

</body>
</html>
