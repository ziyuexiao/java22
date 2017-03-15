<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/2/16
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>新增设备租赁合同</title>
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
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增租赁合同</h3>

                    <div class="box-tools pull-right">
                        <a href="/device/rent" class="btn btn-default btn-sm"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>公司名称</label>
                                <input type="text" class="form-control" id="companyName" tabindex="1"><%--tabindex="1" 指定tab键的功能--%>
                            </div>
                            <div class="form-group">
                                <label>联系电话</label>
                                <input type="text" class="form-control" id="telNum" tabindex="4">
                            </div>
                            <div class="form-group">
                                <label>租赁日期</label>
                                <input type="text" class="form-control" id="rentDate" readonly>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>法人代表</label>
                                <input type="text" class="form-control" id="linkMan" tabindex="2">
                            </div>
                            <div class="form-group">
                                <label>地址</label>
                                <input type="text" class="form-control" id="address" tabindex="5">
                            </div>
                            <div class="form-group">
                                <label>归还日期</label>
                                <input type="text" class="form-control" id="backDate" tabindex="7">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>身份证号</label>
                                <input type="text" class="form-control" id="cardNum" tabindex="3">
                            </div>
                            <div class="form-group">
                                <label>传真</label>
                                <input type="text" class="form-control" id="fax" tabindex="6">
                            </div>
                            <div class="form-group">
                                <label>总天数</label>
                                <input type="text" class="form-control" id="totalDays" readonly>
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
                    <div class="box-tools pull-right">
                        <button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i></button>
                    </div>
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
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-if="deviceArray.length==0">
                            <td colspan="6">暂无数据</td>
                        </tr>
                        <tr v-for="device in deviceArray">
                            <td>{{device.devicename}}</td>
                            <td>{{device.deviceunit}}</td>
                            <td>{{device.deviceprice}}</td>
                            <td>{{device.devicenum}}</td>
                            <td>{{device.totalprice}}</td>
                            <td><a href="javascript:;" v-on:click="remove(device)"><li class="fa fa-trash text-danger"></li></a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="box-footer" style="text-align: right">
                    总租赁费 {{totalprice}} 元 预付款 {{preCost}} 元 尾款 {{lastCost}}   元
                </div>
            </div>

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
    <!-- Content Wrapper. Contains page content -->

    <!-- /.content-wrapper -->

    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">选择设备</h4>
                </div>
                <div class="modal-body">
                    <form action="">
                        <div class="form-group">
                            <input type="hidden" id="deviceName">
                            <label>设备名称</label>
                            <select id="deviceId" style="width: 300px;" class="form-control">
                                <option value="">选择设备</option>
                                <c:forEach items="${deviceList}" var="device">
                                    <option value="${device.id}">${device.devicename}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>库存数量</label>
                            <input type="text" class="form-control" id="currNum" readonly>
                        </div>
                        <div class="form-group">
                            <label>单位</label>
                            <input type="text" class="form-control" id="unit" readonly>
                        </div>
                        <div class="form-group">
                            <label>租赁单价</label>
                            <input type="text" class="form-control" id="rentPrice" readonly>
                        </div>
                        <div class="form-group">
                            <label>租赁数量</label>
                            <input type="text" class="form-control" id="rentNum">
                        </div>
                    </form>
                </div>
                <div class="modal-footer"><%--v-on:click代表vue里面的点击事件--%>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="addDevice">加入列表</button>
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
        $("#deviceId").select2();//该插件帮助输入关键字时出现下拉框查询的内容，没有经过本地数据库
        $("#deviceId").change(function () {
            var id = $(this).val();
            if(id) {
                $.get("/device/rent/device.json",{'id':id}).done(function(resp){
                    if(resp.status == 'success') {
                        var device = resp.data;//获取服务端返回的对象
                        $("#deviceName").val(device.devicename);
                        $("#currNum").val(device.devicenum);
                        $("#unit").val(device.deviceunit);
                        $("#rentPrice").val(device.deviceprice);
                    } else {
                        alert(resp.message);
                    }
                }).error(function () {
                    alert("服务器异常，请稍后再试");
                });
            }
        })

        //租赁日期，默认今天
        $("#rentDate").val(moment().format("YYYY-MM-DD"));//moment插件
        //归还日期
        $("#backDate").datepicker({//datepicker插件帮忙出现日历选择
            /*var day1 = moment('2017-09-21');
             var day2 = moment('2017-09-22');
             var result = day2.diff(day1,'days');
             console.log(result);*/
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true,//选择后日历自动消失
            startDate:moment().add(1,'days').format("YYYY-MM-DD")
        }).on("changeDate",function (e) {//datepicker中带有的计算时间间隔的方法
            var rentDay = moment();
            var backDay = moment(e.format(0,'yyyy-mm-dd'));
            var days = backDay.diff(rentDay,'days')+1;
            $("#totalDays").val(days);
        });

        /*上传*/
        var uploder = WebUploader.create({
            swf:"/static/plugins/uploader/Uploader.swf",
            server: "/file/upload",
            pick: '#picker',
            auto : true,
            fileVal:'file'
        });
        uploder.on("uploadSuccess",function(file,response){
            layer.msg("文件上传成功");
            var html = "<li>"+response.data.sourceFileName+"</li>";//服务端返回的是json，因此用response.data
            $("#fileList").append(html);

            /*fileArray.push(response.data.newFileName);*///将上传的文件push到数组中，这样在保存合同时就知道传了什么文件
            /*response.data.newFileName.css("color","green");*/
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
    
    

    var app = new Vue({//vue插件，将一个页面数据（设备选择页）不经过数据库显示在设备列表页，javascript中的todolist例子
        el:"#app",
        data: {
            deviceArray:[]
        },
        methods: {
            addDevice:function(){
                var id = $("#deviceId").val();
                //判断数组中是否存在当前的设备，如果有则数量累加，更新总价
                var flag = false;
                for(var i = 0;i < this.$data.deviceArray.length;i++) {
                    var item = this.$data.deviceArray[i];
                    if(item.id == id) {
                        this.$data.deviceArray[i].devicenum = parseFloat(this.$data.deviceArray[i].devicenum) + parseFloat($("#rentNum").val());
                        this.$data.deviceArray[i].totalprice = parseFloat(this.$data.deviceArray[i].devicenum) * parseFloat($("#rentPrice").val());
                        flag = true;
                        break;
                    }
                }
                //如果没有则添加新JSON对象
                if(!flag) {
                    var json = {};
                    json.id = id;
                    json.devicename = $("#deviceName").val();
                    json.deviceunit = $("#unit").val();
                    json.deviceprice = $("#rentPrice").val();
                    json.devicenum = $("#rentNum").val();
                    json.totalprice = parseFloat(json.deviceprice) * parseFloat(json.devicenum);

                    this.$data.deviceArray.push(json);
                }
            },
            remove : function (device) {
                layer.confirm("确定删除吗",function (index) {
                   app.$data.deviceArray.splice(app.$data.deviceArray.indexOf(device),1);//从该位置开始删除一个
                    layer.close(index);//点击确定后进行删除
                });
            },
            saveContract : function () {
                var json = {
                    deviceArray : app.$data.deviceArray,
                    fileArray : fileArray,
                    companyName : $("#companyName").val(),
                    tel : $("#telNum").val(),
                    linkMan : $("#linkMan").val(),
                    cardNum : $("#cardNum").val(),
                    address : $("#address").val(),
                    fax : $("#fax").val(),
                    rentDate : $("#rentDate").val(),
                    backDate : $("#backDate").val(),
                    totalDate : $("#totalDays").val()
                };
                $.ajax({
                    url:"/device/rent/new",
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
                                window.location.href = "/device/rent/"+data.data;//data从服务端传过来的对象，是流水号
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
                for(var i = 0;i < this.$data.deviceArray.length;i++) {
                    var item = this.$data.deviceArray[i];
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
