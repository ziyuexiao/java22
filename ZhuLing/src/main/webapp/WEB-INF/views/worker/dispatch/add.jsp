<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/2/18
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>新增劳务派遣</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper" id="app">

    <%@include file="../../include/header.jsp"%>
    <%--点击选项后有变亮的功能--%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_worker_dispatch"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增劳务外包</h3>

                    <div class="box-tools pull-right">
                        <a href="/worker/dispatch" class="btn btn-default btn-sm"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>外包公司名称</label>
                                <input type="text" class="form-control" id="companyName" tabindex="1"><%--tabindex="1" 指定tab键的功能--%>
                            </div>
                            <div class="form-group">
                                <label>法人代表</label>
                                <input type="text" class="form-control" id="linkMan" tabindex="4">
                            </div>
                            <div class="form-group">
                                <label>开始日期</label>
                                <input type="text" class="form-control" id="startDate" readonly>
                            </div>

                            <div class="form-group">
                                <label>佣金金额:</label>
                                {{totalprice}}
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>地址</label>
                                <input type="text" class="form-control" id="address" tabindex="2">
                            </div>
                            <div class="form-group">
                                <label>电话</label>
                                <input type="text" class="form-control" id="personTel" tabindex="5">
                            </div>
                            <div class="form-group">
                                <label>结束日期</label>
                                <input type="text" class="form-control" id="endDate" tabindex="7">
                            </div>
                            <div class="form-group">
                                <label>预付款:</label>
                                {{preCost}}
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>公司电话</label>
                                <input type="text" class="form-control" id="companyTel" tabindex="3">
                            </div>
                            <div class="form-group">
                                <label>身份证号</label>
                                <input type="text" class="form-control" id="cardNum" tabindex="6">
                            </div>
                            <div class="form-group">
                                <label>总天数</label>
                                <input type="text" class="form-control" id="totalDays" readonly>
                            </div>
                            <div class="form-group">
                                <label>尾款:</label>
                                {{lastCost}}
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->

            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">工作类别</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">选择工种<i class="fa fa-plus"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>工种名称</th>
                            <th>工种单位佣金</th>
                            <th>工种数量</th>
                            <th>总价</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-if="workerArray.length==0">
                            <td colspan="6">暂无数据</td>
                        </tr>
                        <tr v-for="worker in workerArray">
                            <td>{{worker.workername}}</td>
                            <td>{{worker.workerprice}}</td>
                            <td>{{worker.workernum}}</td>
                            <td>{{worker.totalprice}}</td>
                            <td><a href="javascript:;" v-on:click="remove(worker)"><li class="fa fa-trash text-danger"></li></a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- /.box -->



            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">合同扫描件</h3>
                </div>
                <div class="box-body">
                    <div id="picker">选择文件</div>
                    注意：上传合同扫描件要求清晰可见 合同必须公司法人签字盖章
                    <ul id="fileList">
                    </ul>
                    <button class="btn btn-primary pull-right" v-on:click="saveContract">保存合同</button>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">选择工种</h4>
                </div>
                <div class="modal-body">
                    <form action="">
                        <div class="form-group">
                            <input type="hidden" id="workerName">
                            <label>工种名称</label>
                            <select id="workerid" style="width: 300px;" class="form-control">
                                <option value="">选择工种</option>
                                <c:forEach items="${workerList}" var="worker">
                                    <option value="${worker.id}">${worker.workername}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>工种当前数</label>
                            <input type="text" class="form-control" id="workerCurrNum" readonly>
                        </div>
                        <div class="form-group">
                            <label>工种单位佣金</label>
                            <input type="text" class="form-control" id="workerPrice" readonly>
                        </div>
                        <div class="form-group">
                            <label>派遣数量</label>
                            <input type="text" class="form-control" id="dispatchNum">
                        </div>
                    </form>
                </div>
                <div class="modal-footer"><%--v-on:click代表vue里面的点击事件--%>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="addWorker">加入列表</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</div>

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/uploader/webuploader.min.js"></script>
<script src="/static/plugins/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/select2/select2.full.min.js"></script>
<script src="/static/plugins/vue.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    var fileArray = [];
    $(function () {
        $("#workerid").select2();
        $("#workerid").change(function () {
            var id = $(this).val();
            if(id){
                $.ajax({
                    url:"/worker/dispatch/worker.json",
                    type:"get",
                    data:{"id":id},
                    success:function (response) {
                        if(response.status=="success"){
                            var worker = response.data;//获取根据id查询后返回的对象
                            $("#workerName").val(worker.workername);
                            $("#workerCurrNum").val(worker.workercurrnum);
                            $("#workerPrice").val(worker.workerprice);

                        }else {
                            alert(response.message);
                        }
                    },
                    error:function () {
                        layer.msg("服务器异常，稍后再试！");
                    }
                });
            }
        });
        //租赁日期，默认今天
        $("#startDate").val(moment().format("YYYY-MM-DD"));//moment插件
        //归还日期
        $("#endDate").datepicker({//datepicker插件帮忙出现日历选择
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true,//选择后日历自动消失
            startDate:moment().add(1,'days').format("YYYY-MM-DD")
        }).on("changeDate",function (e) {//datepicker中带有的计算时间间隔的方法
            var startDay = moment();
            var endDay = moment(e.format(0,'yyyy-mm-dd'));
            var days = endDay.diff(startDay,'days')+1;
            $("#totalDays").val(days);
        });

        var uploder = WebUploader.create({
            swf : "/static/plugins/uploader/Uploader.swf",
            server: "/worker/dispatch/upload",
            pick: '#picker',
            auto : true,
            fileVal:'file'
        });
        uploder.on("uploadSuccess",function(file,response){
            layer.msg("文件上传成功");
            var html = "<li>"+response.data.sourceFileName+"</li>";//服务端返回的是json，因此用response.data
            $("#fileList").append(html);
            var json = {
                sourcename : response.data.sourceFileName,
                newname : response.data.newFileName
            };
            fileArray.push(json);
        });
        uploder.on("uploadError",function () {
            layer.msg("服务器错误，稍后再试！！");
        });
    });
    var app = new Vue({
        el:"#app",
        data: {
            workerArray:[]
        },
        methods:{
            addWorker:function () {
                var id = $("#workerid").val();
                //判断数组中是否存在当前的工种，有则数量累加
                var flag = false;
                for (var i=0;i<this.$data.workerArray.length;i++){
                    var item = this.$data.workerArray[i];
                    if(item.id == id){
                        item.workernum = parseFloat(item.workernum) + parseFloat($("#dispatchNum").val());
                        item.totalprice = parseFloat(item.workernum)*parseFloat($("#workerPrice").val());
                        flag = true;
                        break;
                    }
                }
                //如果没有则添加新JSON对象
                if(!flag){
                    var json = {};
                    json.id = id;
                    json.workername=$("#workerName").val();
                    json.workerprice=$("#workerPrice").val();
                    json.workernum=$("#dispatchNum").val();
                    json.totalprice = parseFloat(json.workerprice) * parseFloat(json.workernum);

                    this.$data.workerArray.push(json);
                }
            },
            saveContract : function () {
                var json = {
                    workerArray: app.$data.workerArray,
                    fileArray: fileArray,
                    companyName: $("#companyName").val(),
                    companyTel: $("#companyTel").val(),
                    address: $("#address").val(),
                    linkMan: $("#linkMan").val(),
                    personTel: $("#personTel").val(),
                    cardNum: $("#cardNum").val(),
                    startDate : $("#startDate").val(),
                    endDate : $("#endDate").val(),
                    totalDays : $("#totalDays").val()
                };
                $.ajax({
                    url:"/worker/dispatch/new",
                    type:"post",
                    data:JSON.stringify(json),//将json转为字符串
                    contentType:"application/json;charset=UTF-8",
                    success:function (data) {
                        if(data.status == 'success') {
                            layer.confirm("保存成功",{btn:['继续添加','打印合同']},function(){
                                /*点击  继续添加  执行该方法*/
                                window.history.go(0);
                            },function(){
                                /*点击  打印合同  执行该方法*/
                                window.location.href = "/worker/dispatch/"+data.data;//data从服务端传过来的对象，是流水号
                            });
                        }else {
                            layer.msg(data.message);
                        }
                    },
                    error:function () {
                        layer.msg("服务器异常，稍后再试");
                    }
                });
            }

        },
        computed:{
            totalprice : function(){
                var result = 0;
                for(var i = 0;i < this.$data.workerArray.length;i++) {
                    var item = this.$data.workerArray[i];
                    result += item.totalprice;
                }
                return result;
            },
            preCost : function () {
                return this.totalprice*0.3;
            },

            lastCost : function () {
                return this.totalprice-this.preCost;
            }

        }
    });

</script>
</body>
</html>

