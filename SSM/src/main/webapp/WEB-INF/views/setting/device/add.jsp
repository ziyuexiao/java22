<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/1/14
  Time: 11:29
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
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="sys_device"/>
    </jsp:include>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">新增设备</h3>
                </div>
                <div class="box-body">
                    <form method="post">
                        <div class="form-group">
                            <label>设备名称</label>
                            <input type="text" name="devicename" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>设备单位</label>
                            <input type="text" name="deviceunit" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>设备总数</label>
                            <input type="text" name="devicetotal" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>设备当前数量</label>
                            <input type="text" name="devicenum" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>设备单价</label>
                            <input type="text" name="deviceprice" class="form-control">
                        </div>

                        <div class="form-group">
                            <button class="btn btn-success">保存</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../../include/js.jsp"%>
</body>
</html>
