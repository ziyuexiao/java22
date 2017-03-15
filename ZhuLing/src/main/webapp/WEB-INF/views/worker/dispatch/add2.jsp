<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/2/18
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>劳务外包新增</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">

    <link rel="stylesheet" href="css/bootstrap-datepicker.min.css">
    <!-- 文件上传 -->
    <link rel="stylesheet" href="js/uploader/webuploader.css">
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

    <!-- Left side column. contains the sidebar -->
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
                <li class="active">新增流水</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增劳务外包流水</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="box">

                        <div class="box-body">
                            <form role="form" >
                                <div class="box-body" class="form-group">

                                    <!--公司 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="company">租赁公司：&nbsp</label>
                                                <input type="text"  class="" id="daynumber" placeholder="">

                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label for="representative">地 &nbsp;&nbsp址：&nbsp</label>
                                                <input type="text" class="" name="representative" placeholder="" >
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="telephone">公司电话：&nbsp</label>
                                                <input type="text" class="" name="telephone" placeholder="" >
                                            </div>
                                        </div>



                                    </div>


                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="company">法人代表：&nbsp</label>
                                                <input type="text"  class="" id="daynumber" placeholder="">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="telephone">电 &nbsp;&nbsp话：&nbsp</label>
                                                <input type="text" class="" name="telephone" placeholder="" >
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div>
                                                <label for="idNum">身份证号：&nbsp</label>
                                                <input type="text" class="" id="idNum" placeholder="">
                                            </div>
                                        </div>
                                    </div>

                                    <!--金额 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="sumMoney">佣金金额：&nbsp</label>
                                                <input type="text" disabled="disabled" class="" id="sumMoney" placeholder=""value="10000.00">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="firstMoney">预付款：&nbsp</label>
                                                <input type="text" class="" disabled="disabled" name="firstMoney" placeholder="" value="2000.00">
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div >
                                                <label for="lastMoney">尾&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;款：&nbsp</label>
                                                <input type="text" class="" disabled="disabled" name="lastMoney" placeholder="" value="8000.00">
                                            </div>
                                        </div>
                                    </div>


                                    <!--工种 -->
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="" style="margin:20px;border:1px solid #bfbfbf;">
                                                <table class="table table-bordered" >
                                                    <tr>
                                                        <th><a href="#">添加工种<span><i class="fa fa-plus"></span></a></th>
                                                        <th>工种单位佣金</th>
                                                        <th>工种数量</th>
                                                        <th>小计</th>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <select name="" id="" style="height:25px;width:160px">
                                                                <option value="">挖掘工</option>
                                                                <option value="">塔吊组装工</option>
                                                                <option value="">货车司机</option>
                                                                <option value="">水泥工</option>
                                                            </select>
                                                        </td>
                                                        <td><span>100.00</span></td>
                                                        <td><input type="text" class="" style="width:50px" id="idNum" placeholder=""></td>
                                                        <td><span>10000.00</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <select name="" id="" style="height:25px;width:160px">
                                                                <option value="">挖掘工</option>
                                                                <option value="">塔吊组装工</option>
                                                                <option value="">货车司机</option>
                                                                <option value="">水泥工</option>
                                                            </select>
                                                        </td>
                                                        <td><span>100.00</span></td>
                                                        <td><input type="text" class="" style="width:50px" id="idNum" placeholder=""></td>
                                                        <td><span>10000.00</span></td>
                                                    </tr>

                                                </table>

                                            </div>
                                        </div>




                                    </div>
                                    <div> <br/></div>



                                    <div class="box" style="padding-left: 20px">
                                        <div class="box-header">
                                            <span class="title"><i class="fa fa-user"></i> 合同上传</span>
                                        </div>
                                        <form action="" class="form-horizontal">
                                            <hr>
                                            <p style="padding-left: 20px">注意事项</p>
                                            <ul>
                                                <li>上传合同扫描件要求清晰可见</li>
                                                <li>合同必须公司法人签字盖章</li>
                                            </ul>
                                            <div class="form-actions">
                                                <div id="picker">上传合同</div>

                                            </div>
                                        </form>

                                    </div>


                                    <div class="row">

                                        <div class="col-lg-3">

                                        </div>
                                        <div class="col-lg-3">
                                            <div class="box-footer">
                                                <button type="submit" class="btn btn-primary">提交</button>
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <button type="submit" class="btn btn-primary">重置</button>
                                            </div>
                                        </div>

                                    </div>

                            </form>
                        </div>
                        <!-- /.box-body -->

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
<script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>
<!-- datepicker -->
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="js/uploader/webuploader.min.js"></script>
<script>
    <!-- $(function() {		$( "#datepicker").datepicker();	});
    -->
    $(function () {
        $("#datepicker").datepicker({
            language: "zh-CN",
            autoclose: true,//选中之后自动隐藏日期选择框
            //clearBtn: true,//清除按钮
            //todayBtn: true,//今日按钮
            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });
        //文件上传
        var uploder = WebUploader.create({
            swf : "js/uploader/Uploader.swf",
            server: "#",
            pick: '#picker',
            auto : true,
            fileVal:'file',
            /*accept: {
             title: 'Images',
             extensions: 'gif,jpg,jpeg,bmp,png',
             mimeTypes: 'image/!*'
             }*/
        });

    });

</script>
</body>
</html>

