<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/2/24
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>财务报表</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.css">
    <link rel="stylesheet" href="/static/plugins/datatables/extensions/FixedHeader/css/dataTables.fixedHeader.min.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper" id="app">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="finance_month"/>
    </jsp:include>

    <!-- Content Header (Page header) -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-body">
                    <form class="form-inline">
                        <input type="text" class="form-control" id="date">
                    </form>
                </div>
            </div>
            <!-- Default box -->
            <div class="box box-primary box-solid">
                <div class="box-header with-border">
                    <h3 class="box-title">财务月报</h3>

                    <div class="box-tools pull-right">
                        <a href="javascript:;" id="exportXls" class="btn btn-default"><i class="fa fa-file-o"></i> 导出Excel</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>日期</th>
                            <th>当日收入</th>
                            <th>当日支出</th>
                            <th>备注</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
            <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main content -->


    <!-- /.content -->

    <!-- /.content-wrapper -->

    <!-- /.content-wrapper -->



</div>

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/extensions/FixedHeader/js/dataTables.fixedHeader.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/echarts.min.js"></script>
<script>

    $(function () {
        $("#date").val(moment().format("YYYY-MM"));//当没有选择时间时，文本框默认显示当前时间
        $("#date").datepicker({
            format:"yyyy-mm",//大写时会出现别的格式
            language: "zh-CN",
            autoclose: true,
            endDate:moment().format("YYYY-MM")//当前日期的后面日期不能在选择
        }).on("changeDate",function (e) {
            var today = e.format(0,'yyyy-mm');//获得选中的日期
            //alert(today);
            table.ajax.reload();
        });

        var table = $(".table").DataTable({
            "lengthChange": false,
            "pageLength": 25,
            "serverSide": true,
            "ajax":{
                "url":"/finance/month/load",
                "type":"get",
                "data":function(obj){
                    obj.month = $("#date").val()
                }
            },
            "searching":false,//不使用自带的搜索
            "order":[[0,'desc']],//默认排序方式,
            "ordering": false,
            "columns":[
                {"data":"id","name":"id"},
                {"data":"createdate"},
                {"data":"typein"},
                {"data":"typeout"},
                {"data":"content"},
                {"data":function (row) {
                        return "<a href='/finance/day/{type}/{date}/pie' class='confirmbtn' rel='"+row.id+"'>详情</a>";

                }}
            ],
            "columnDefs":[
                {targets:[0],visible: false}
            ],
            "language":{ //定义中文
                "search": "搜索:",
                "zeroRecords":    "没有匹配的数据",
                "lengthMenu":     "显示 _MENU_ 条数据",
                "info":           "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered":   "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载中...",
                "processing":     "处理中...",
                "paginate": {
                    "first":      "首页",
                    "last":       "末页",
                    "next":       "下一页",
                    "previous":   "上一页"
                }
            }
        });

        //导出成excelf
        $("#exportXls").click(function () {
            var day = $("#date").val();
            window.location.href="/finance/month/"+month+"/data.xls";
        });

    });

</script>
</body>
</html>
