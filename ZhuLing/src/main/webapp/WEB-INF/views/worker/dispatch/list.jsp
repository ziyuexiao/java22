<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/2/16
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>劳务派遣</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.css">
    <link rel="stylesheet" href="/static/plugins/datatables/extensions/FixedHeader/css/dataTables.fixedHeader.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_worker_dispatch"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box box-primary box-solid">
                <div class="box-header with-border">
                    <h3 class="box-title">劳务派遣合同列表</h3>

                    <div class="box-tools pull-right">
                        <a href="/worker/dispatch/add" class="btn btn-primary"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>流水号</th>
                            <th>外包公司</th>
                            <th>公司地址</th>
                            <th>公司电话</th>
                            <th>开始日期</th>
                            <th>结束日期</th>
                            <th>法人代表</th>
                            <th>电话号码</th>
                            <th>身份证号码</th>
                            <th>创建时间</th>
                            <th>状态</th>
                            <th>总佣金</th>
                            <th>#</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/extensions/FixedHeader/js/dataTables.fixedHeader.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        var table = $(".table").DataTable({
            /*让每页显示固定的数据*/
            "lengthChange":false,
            "pageLength":20,
            /*指定要去服务端处理*/
            "serverSide": true,
            /*数据使用ajax的方式去哪个服务端处理*/
            "ajax":{
                "url":"/worker/dispatch/loading",
                "type":"get"
            },
            "searching":false,//不使用自带的搜索
            "order":[[0,'desc']],//默认第几列排序方式
            "ordering": false,//默认排序方式不管用
            "autoWidth": false,//
            /*传入的data的对照关系*/
            "columns":[
                {"data":"id","name":"id"},
                {"data":function (row) {
                    if (row.serialnumber){
                        return "<a href='/device/rent/"+row.serialnumber+"'>"+row.serialnumber+"</a>"
                    }else {
                        return "";//让页面上该栏不显示，否者会显示null
                    }
                }},
                {"data":"companyname"},
                {"data":"address"},
                {"data":"companytel"},
                {"data":"startdate"},
                {"data":"enddate"},
                {"data":"linkman"},
                {"data":"persontel"},
                {"data":"cardnum"},
                {"data":"state"},
                {"data":"createtime"},
                {"data":"totalprice"},
                {"data": function (row) {
                        if (row.state) {
                            return "";
                        } else {
                            return "<a href='javascript:;' rel='" + row.id + "' class='btn btn-xs btn-default checkBtn'> <i class='fa fa-check'></i> 完成</a>";
                        }
                    }
                }
            ],
            /*定义列的特征*/
            "columnDefs":[
                {targets:[0],visible: false},//第0列不显示
            ],
            /*定义中文*/
            "language":{
                "search": "搜索:",
                "zeroRecords":    "没有匹配的数据",
                "lengthMenu":     "显示 _MENU_ 条数据",
                "info":           "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
               /* "infoFiltered":   "(从 _MAX_ 条数据中过滤得来)",*/
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
       /* new $.fn.dataTable.FixedHeader(table);*///固定网页头
        //将合同变为完成状态，页面上的值都是后来添上的，对其进行操作时要使用事件委托
        $(document).delegate(".checkBtn","click",function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要将合同修改为已完成吗",function (index) {
                $.post("/worker/dispatch/state/change",{"id":id}).done(function (resp) {
                    if (resp.state == "success"){
                        table.ajax.reload();
                    }
                }).error(function () {
                    layer.msg("服务器忙，请稍后再试");
                });
                layer.close(index);
            });

        });


    });
</script>
</body>
</html>

