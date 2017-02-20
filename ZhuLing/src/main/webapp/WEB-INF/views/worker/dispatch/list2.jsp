<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/2/18
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>劳务外包流水查询</title>
    <!-- Tell tde browser to be responsive to screen widtd -->
    <meta content="widtd=device-widtd, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/bootstrap/css/font-awesome.min.css">
    <!-- tdeme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from tde css/skins
         folder instead of downloading all of tdem to reduce tde load. -->
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="css/style.css">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="#" class="logo">
            河南功成企业管理系统
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">

                    <li>
                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- =============================================== -->

    <!-- Left side column. contains tde sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">

                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-pie-chart"></i>
                        <span>财务报表</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="../charts/chartjs.html"><i class="fa fa-circle-o"></i> 日报</a></li>
                        <li><a href="../charts/morris.html"><i class="fa fa-circle-o"></i> 月报</a></li>
                        <li><a href="../charts/flot.html"><i class="fa fa-circle-o"></i> 年报</a></li>
                    </ul>
                </li>

                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-edit"></i> <span>业务</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#"><i class="fa fa-circle-o"></i> 设备租赁<i class="fa fa-angle-left pull-right"></i></a>
                            <ul class="treeview-menu">
                                <li><a href="#"><i class="fa fa-circle-o"></i> 业务流水</a></li>
                                <li>
                                    <a href="#"><i class="fa fa-plus"></i> 新增流水 </a>

                                </li>
                            </ul>
                        </li>

                        <li><a href="#"><i class="fa fa-circle-o"></i> 设备管理<i class="fa fa-angle-left pull-right"></i></a>
                            <ul class="treeview-menu">
                                <li><a href="#"><i class="fa fa-circle-o"></i> 设备库存</a></li>
                                <li>
                                    <a href="#"><i class="fa fa-plus"></i> 新增设备 </a>

                                </li>
                            </ul>
                        </li>

                        <li>
                            <a href="#"><i class="fa fa-circle-o"></i> 劳务外包 <i class="fa fa-angle-left pull-right"></i></a>
                            <ul class="treeview-menu">
                                <li><a href="#"><i class="fa fa-circle-o"></i> 业务流水</a></li>
                                <li>
                                    <a href="#"><i class="fa fa-plus"></i> 新增流水 <i class="fa fa-angle-left pull-right"></i></a>

                                </li>
                            </ul>
                        </li>

                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-folder"></i>
                        <span>公司网盘</span>
                    </a>

                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>

                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 业务</a></li>
                <li><a href="#">劳务外包</a></li>
                <li class="active">业务流水</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header witd-border">
                    <h3 class="box-title">劳务外包流水</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="box">
                        <div id="filtrate-box" class="screen-condition scd01"><!-- 筛选开始 -->
                            <form action="" class="form-inline">
                                <div class="form-group form-marginR">
                                    <label for="exampleInputName2">流水号:</label>
                                    <input type="text" class="form-control form-angle input-sm" id="exampleInputName2" placeholder="">
                                </div>
                                <div class="form-group form-marginR">
                                    <label for="exampleInputEmail2">用人单位:</label>
                                    <input type="text" class="form-control form-angle input-sm" id="exampleInputName2" placeholder="">
                                </div>
                                <div class="form-group form-marginR">
                                    <label for="exampleInputName2">状态:</label>
                                    <!-- <div class="input-group"> -->
                                    <select class="form-control form-angle input-sm" id="select_Type">
                                        <option value="1">完成</option>
                                        <option value="2">未完成</option>
                                    </select>
                                    <input type="hidden" name="workFlowType" id="workFlowType">
                                    <!-- </div> -->
                                </div>
                                <div class="form-group form-marginR">
                                    <label for="exampleInputName2">起止时间:</label>
                                    <div class="input-group">
                                        <div class="input-group-addon form-angle input-sm"><i class="fa fa-calendar"></i></div>
                                        <input type="text" class="form-control form-angle form_datetime input-sm" name="createDate" id="exampleInputName2" >
                                    </div> -
                                    <div class="input-group">
                                        <div class="input-group-addon form-angle input-sm"><i class="fa fa-calendar"></i></div>
                                        <input type="text" class="form-control form-angle form_datetime input-sm" name="createDate" id="exampleInputName2" >
                                    </div>
                                </div>
                                <a type="submit" class="btn btn-default btn-sm">查询</a>
                            </form>
                        </div><!-- 筛选结束 -->
                        <div class="box-body">
                            <table class="table table-bordered">
                                <tr>
                                    <th>流水号</th>
                                    <th>需求公司</th>
                                    <th>公司地址</th>
                                    <th>公司电话</th>
                                    <th>法人代表</th>
                                    <th>电话号码</th>
                                    <th>身份证号</th>
                                    <th>创建时间</th>
                                    <th>状态</th>
                                    <th>总佣金</th>
                                    <th>合同</th>
                                    <th>操作</th>
                                </tr>
                                <tr>
                                    <td><a href="#" class="detail">3001</a></td>
                                    <td>河南建业</td>
                                    <td>河南郑州金水区81号</td>
                                    <td>0371-89456321</td>
                                    <td>胡八一</td>
                                    <td>15026458956</td>
                                    <td>410523195806024536</td>
                                    <td>2016-10-10</td>
                                    <td>未完成</td>
                                    <td>10000.00</td>
                                    <td><a href="#">下载</a></td>
                                    <td><a href="#" class="detail">详情</a>&nbsp<a href="#">完成</a></td>
                                </tr>
                                <tr>
                                    <td><a href="#" class="detail">3001</a></td>
                                    <td>河南建业</td>
                                    <td>河南郑州金水区81号</td>
                                    <td>0371-89456321</td>
                                    <td>胡八一</td>
                                    <td>15026458956</td>
                                    <td>410523195806024536</td>
                                    <td>2016-10-10</td>
                                    <td>完成</td>
                                    <td>10000.00</td>
                                    <td><a href="#">下载</a></td>
                                    <td><a href="#" class="detail">详情</a></td>
                                </tr>
                            </table>
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer clearfix">
                            <ul class="pagination pagination-sm no-margin pull-right">
                                <li><a href="#">&laquo;</a></li>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">&raquo;</a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.box-body -->

            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 2.3.3
        </div>
        <strong>Copyright &copy; 2017 <a href="http://hngc.com">河南功成</a>.</strong> All rights
        reserved.
    </footer>


</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.0 -->
<script src="/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script>
    $(function(){
        $(".detail").click(function(){
            alert("");
        });
    });
</script>
</body>
</html>

