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
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.min.css">
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
                    <h3 class="box-title">搜索</h3>
                </div>
                <div class="box-body">
                    <form class="form-inline">
                        <div class="form-group">
                            <input type="text" id="q_name"  value="${queryName}" class="form-control" placeholder="设备名称">
                        <%--value="${queryName}"使选择框里面一直有值--%>
                        </div>
                        <button type="button" id="searchBtn" class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>

            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">设备管理</h3>
                    <div class="box-tools pull-right">
                        <a href="/setting/device/adddevice" class="btn"><i class="fa fa-plus"></i></a>
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
                            <th>id</th>
                            <th>名称</th>
                            <th>单位</th>
                            <th>总数量</th>
                            <th>当前数</th>
                            <th>价格</th>
                            <th width="100">#</th>
                        </tr>
                        </thead>
                        <tbody>
                            <%--<c:forEach items="${deviceList}" var="device">
                                <tr>
                                    <th>${device.id}</th>
                                    <th>${device.devicename}</th>
                                    <th>${device.deviceunit}</th>
                                    <th>${device.devicetotal}</th>
                                    <th>${device.devicenum}</th>
                                    <th>${device.deviceprice}</th>
                                </tr>
                            </c:forEach>--%>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script>

    $(function () {
        /*DataTable()新版api，dataTable()老版本*/
       var table = $(".table").DataTable({//table为操作完后返回的对象
           /*设置每页显示数据*/
           "lengthMenu": [ 5,10, 25, 50, 75, 100 ],
           /*指定要去服务端处理*/
           "serverSide": true,
           /*数据使用ajax的方式去哪个服务端处理*/
           "ajax":{
               "url":"/setting/device/load",
               "type":"post",//此时不用转码，springmvc自带过滤器
               "data":function(obj){//deviceName要发送到服务端的键，
                   obj.deviceName= $("#q_name").val();
               }
           },
           "searching":false,//不使用自带的搜索
           "order":[[0,'desc']],//默认第几列排序方式

           /*传入的data的对照关系*/
           "columns":[
               {"data":"id","name":"id"},
               {"data":"devicename"},
               {"data":"deviceunit"},
               {"data":"devicetotal","name":"devicetotal"},
               {"data":"devicenum","name":"devicenum"},
               {"data":"deviceprice","name":"deviceprice"},
               /*显示的最后一列 # */
               {"data":function(obj){//function中传入的是对象
                   return "<a href='javascript:;' rel='"+obj.id+"' class='delLink'>删除</a>";
               }}

           ],
           /*定义列的特征*/
           "columnDefs":[
               {targets:[0],visible: false},//第0列不显示
               {targets:[1,2,6],orderable:false}//1~5列不排序

           ],
           /*定义中文*/
           "language":{
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

        /*内容是后来添加上的，进行删除时使用事件委托*/
        $(document).delegate(".delLink","click",function(){
            if(confirm("确定要删除吗?")) {
                var id = $(this).attr("rel");//取值，rel为自己设置的一个标签
                $.get("/setting/device/"+id+"/del").done(function(data){
                    if(data == "success") {
                        alert("删除成功!");

                        //dataTables重新加载，页面进行刷新
                        table.ajax.reload();//window.history.go(0);

                    }
                }).error(function(){
                    alert("服务器异常!");
                });
            }
        });

        //自定义搜索
        $("#searchBtn").click(function () {
            table.draw(); //dataTables发出请求
        });

    });


</script>
</body>
</html>
