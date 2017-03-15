<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/11
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--//引入资源--%>
    <!--引入CSS-->
    <link rel="stylesheet" href="/static/js/webuploader/webuploader.css">
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div id="picker">选择文件</div> <button id="startBtn">开始上传</button>
<ul id="fileList"></ul>

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/webuploader/webuploader.min.js"></script>
<script type="text/template" id="bar">
    <div class="progress">
        <div class="progress-bar" id="{{id}}" style="width: 0%;">
            <span class="sr-only"></span>
        </div>
    </div>
</script>
<script>
    $(function () {

        //1,初始化Web Uploader
        var uploader = WebUploader.create({
            // swf文件路径
            swf:"/static/js/webuploader/Uploader.swf",
            // 文件接收服务端。
            server:"/upload",
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick:"#picker",

            fileVal:"file",

           /* accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/!*'
            }*/
            auto:true//配置项，选择后自动上传
        });

        //2,显示用户选择,选择文件放入上传队列,调用一次
        uploader.on("fileQueued",function (file) {

            var html = "<li id='"+file.id+"'>"+file.name+"</li>";
            $("#fileList").append($(html));
            alert(file);
        });
        //3,文件上传进度,上传过程中调用多次
        uploader.on("uploadProgress",function(file,percentage) {

            var num = parseInt(percentage * 100);
            var $bar = $("#"+file.id).find("#bar_"+file.id);

            if(!$bar[0]) {
                var template = $("#bar").html();
                template = template.replace("{{id}}","bar_"+file.id);
                $("#"+file.id).append($(template));
            } else {
                $bar.css("width",num+"%");
            }

        });

        //文件上传成功
        uploader.on("uploadSuccess",function(file,data){
            $("#"+file.id).css("color","green");
        });
        //文件上传失败
        uploader.on("uploadError",function(file){
            $("#"+file.id).css("color","darkred");
        });
        //文件上传成功或失败均调用
        uploader.on("uploadComplete",function(file){
            $("#"+file.id).find("#bar_"+file.id).parent().remove();
        });

        $("#startBtn").click(function(){
            uploader.upload();
        });

    });
</script>
</body>
</html>
