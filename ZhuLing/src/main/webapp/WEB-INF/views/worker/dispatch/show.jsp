<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>劳务外包详情</title>
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
            <h3 style="text-align: center" class="visible-print-block">凯盛软件劳务外包清单</h3>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">劳务外包流水详情</h3>

                    <div class="box-tools pull-right hidden-print">
                        <button id="print" class="btn btn-default btn-sm"><i class="fa fa-print"></i>打印</button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>用人公司名称:</label>
                                ${workerDispatch.companyname}
                            </div>
                            <div class="form-group">
                                <label>法人代表:</label>
                                ${workerDispatch.linkman}
                            </div>
                            <div class="form-group">
                                <label>开始日期:</label>
                                ${workerDispatch.startdate}
                            </div>

                            <div class="form-group">
                                <label>佣金金额:</label>
                                ${workerDispatch.totalprice}
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>地址:</label>
                                ${workerDispatch.address}
                            </div>
                            <div class="form-group">
                                <label>电话:</label>
                                ${workerDispatch.persontel}
                            </div>
                            <div class="form-group">
                                <label>结束日期:</label>
                                ${workerDispatch.enddate}
                            </div>
                            <div class="form-group">
                                <label>预付款:</label>
                                ${workerDispatch.precost}
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>公司电话:</label>
                                ${workerDispatch.companytel}
                            </div>
                            <div class="form-group">
                                <label>身份证号:</label>
                                ${workerDispatch.cardnum}
                            </div>
                            <div class="form-group">
                                <label>总天数:</label>
                                ${workerDispatch.totalday}
                            </div>
                            <div class="form-group">
                                <label>尾款:</label>
                                ${workerDispatch.lastcost}
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->

            </div>
            <!-- /.box -->

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">工种列表</h3>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>工种种类</th>
                                <th>工种单位租金</th>
                                <th>数量</th>
                                <th>总价</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${detailList}" var="worker">
                            <tr>
                                <td>${worker.woname}</td>
                                <td>${worker.woprice}</td>
                                <td>${worker.wonum}</td>
                                <td>${worker.totalprice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary">返回</button> &nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="submit" class="btn btn-primary">修改</button>
                    <!-- 注意：已完成的业务隐藏修改按钮，未完成的订单可修改工种信息 -->
                </div>
            </div>

            <div class="box hidden-print">
                <div class="box-header">
                    <h3 class="box-title">合同扫描件</h3>
                    <div class="box-tools pull-right">
                        <a href="/worker/dispatch/doc/zip?id=${workerDispatch.id}" class="btn btn-sm btn-default">
                            <i class="fa fa-file-zip"></i> 打包下载
                        </a>
                    </div>
                </div>
                <div class="box-body">
                    <ul id="fileList">
                        <c:forEach items="${docsList}" var="docs">
                            <li><a href="/worker/dispatch/doc?id=${docs.id}">${docs.sourcename}</a></li>
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
