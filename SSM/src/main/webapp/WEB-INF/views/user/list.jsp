<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/1/12
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Blank Page</title>
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="sys_accounts"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">搜索</h3>
                </div>
                <div class="box-body">
                    <form class="form-inline">
                        <div class="form-group">
                            <input type="text" name="q_name" value="${queryName}" class="form-control" placeholder="用户名">
                        <%--value="${queryName}"使选择框里面一直有值--%>
                        </div>

                        <div class="form-group">
                           <select class="form-control" name="q_role">
                               <option value="">角色</option>
                               <c:forEach items="${roleList}" var="role">
                                   <option value="${role.id}" ${role.id == queryRole ? 'selected' : ''}>${role.viewname}</option>
                               </c:forEach>
                           </select>
                        </div>
                        <button class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>

            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">账户管理</h3>
                    <div class="box-tools pull-right">
                        <a href="/user/new" class="btn"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">
                                ${message}
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        </div>
                    </c:if>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>角色</th>
                            <th width="100">#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.items}" var="user">
                            <tr>
                                <td>${user.name}</td>
                                <td>${user.roleNames}</td>
                                <td>
                                    <a href="/user/${user.id}/edit">编辑</a>
                                    <a href="/user/${user.id}/del">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="box-footer ">
                    <ul id="pagination"  class="pagination pull-right" style="margin-bottom:20px;"></ul>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/jquery.twbsPagination.min.js"></script>
<script>

    $(function () {
        $("#pagination").twbsPagination({
            totalPages:${page.totalPage},
            visiblePages:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href: '/user?q_name=${queryName}&q_role=${queryRole}&p={{number}}'
            /*{{number}}分页插件中的固定写法*/
        });
    });


</script>
</body>
</html>
