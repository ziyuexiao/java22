<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/2/21
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>网盘系统</title>
    <%@include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
    <style>
        #picker{
            float: left;
            margin-right:20px;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <%@include file="../include/sider.jsp"%>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="business_pan"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">网盘系统</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <div id="picker">上传文件</div>
                    <button class="btn btn-primary" id="newFolder">新增文件夹</button>
                    <table class="table">
                        <thead>
                            <tr>
                                <th></th>
                                <th>名称</th>
                                <th>大小</th>
                                <th>创建时间</th>
                                <th>创建人</th>
                                <th>#</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty diskList}">
                            <tr>
                                <td colspan="6">暂无资源</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${diskList}" var="disk">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${disk.type == 'file'}">
                                            <i class="fa fa-file-o"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fa fa-folder-o"></i>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${disk.type == 'file'}">
                                            <a href="/pan/download?id=${disk.id}">${disk.sourcename}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/pan?path=${disk.id}">${disk.sourcename}</a>
                                        </c:otherwise>
                                    </c:choose>

                                </td>
                                <td>${disk.size}</td>
                                <td>${disk.createtime}</td>
                                <td>${disk.createuser}</td>
                                <td>
                                    <a href="javascript:;" id="remove" rel="${disk.id}"><i class="fa fa-trash text-danger"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->

                <!-- /.box-footer-->
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/uploader/webuploader.min.js"></script>
<script>
    $(function () {
        var uploader = WebUploader.create({
            swf:"/static/plugins/uploader/Uploader.swf",
            pick:"#picker",
            server:"/pan/load",
            auto:true,
            fileVal:"file",
            formData:{"fid":${fid}}//传递一些无意义的参数

        });
        uploader.on("uploadSuccess",function (file,resp) {
            if (resp.status == "success"){
                window.history.go(0);//当前页面进行刷新
            }else {
                layer.msg(resp.message);
            }
        });
        uploader.on("uploadError",function () {
            layer.msg("服务器异常");
        });


        $("#newFolder").click(function () {
            var fid = ${fid};
            layer.prompt({title:"输入文件夹名字"},function (text,index) {
                layer.close(index);
                $.post("/pan/folder/new",{"fid":fid,"sourcename":text}).done(function (resp) {
                    if (resp.status == "success"){
                        window.history.go(0);
                    }else {
                        layer.msg(resp.message);
                    }
                }).error(function () {
                    layer.msg("服务器异常");
                });
            });
        });
        $("#remove").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除吗",function (index) {
                layer.close(index);
                $.get("/pan/del/"+id).done(function (resp) {
                    if(resp.status == "success"){
                        layer.msg("删除成功");
                        window.history.go(0);
                    }else {
                        layer.msg(resp.message);
                    }
                }).error(function () {
                   layer.msg("服务器异常");
                });
            });
        });

    });
</script>
</body>
</html>

