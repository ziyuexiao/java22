<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>设备租赁合同显示</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper" id="app">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_device_rent"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <h3 style="text-align: center" class="visible-print-block">凯盛软件租赁合同清单</h3>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">租赁合同详情</h3>

                    <div class="box-tools pull-right hidden-print">
                        <button id="print" class="btn btn-default btn-sm"><i class="fa fa-print"></i>打印</button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>公司名称</label>
                                ${deviceRent.companyname}
                            </div>
                            <div class="form-group">
                                <label>联系电话</label>
                                ${deviceRent.telnum}
                            </div>
                            <div class="form-group">
                                <label>租赁日期</label>
                                ${deviceRent.rentdate}
                            </div>
                            <div class="form-group">
                                <label>总金额</label>
                                ${deviceRent.totalprice}
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>法人代表</label>
                                ${deviceRent.linkman}
                            </div>
                            <div class="form-group">
                                <label>地址</label>
                                ${deviceRent.address}
                            </div>
                            <div class="form-group">
                                <label>归还日期</label>
                                ${deviceRent.backdate}
                            </div>
                            <div class="form-group">
                                <label>预付款</label>
                                ${deviceRent.precost}
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>身份证号</label>
                                ${deviceRent.cardnum}
                            </div>
                            <div class="form-group">
                                <label>传真</label>
                                ${deviceRent.fax}
                            </div>
                            <div class="form-group">
                                <label>总天数</label>
                                ${deviceRent.totalday}
                            </div>
                            <div class="form-group">
                                <label>尾款</label>
                                ${deviceRent.lastcost}
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">设备列表</h3>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>设备名称</th>
                                <th>单位</th>
                                <th>租赁单价</th>
                                <th>数量</th>
                                <th>总价</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${detailList}" var="device">
                            <tr>
                                <td>${device.dename}</td>
                                <td>${device.deunit}</td>
                                <td>${device.deprice}</td>
                                <td>${device.denum}</td>
                                <td>${device.totalprice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="box hidden-print">
                <div class="box-header">
                    <h3 class="box-title">合同扫描件</h3>
                    <div class="box-tools pull-right">
                        <a href="/device/rent/doc/zip?id=${deviceRent.id}" class="btn btn-sm btn-default">
                            <i class="fa fa-file-zip"></i> 打包下载
                        </a>
                    </div>
                </div>
                <div class="box-body">
                    <ul id="fileList">
                        <c:forEach items="${deviceRentDocsList}" var="docs">
                            <li><a href="/device/rent/doc?id=${docs.id}">${docs.sourcename}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->


</div>

<%@include file="../../include/js.jsp"%>
<script>
    $(function () {
        $("#print").click(function () {
            window.print();//自带的打印方法
        });
    });
</script>
</body>
</html>
