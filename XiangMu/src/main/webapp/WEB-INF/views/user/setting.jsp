<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/19
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/js/uploader/webuploader.css">
</head>
<body>
<%@include file="../include/navbar.jsp"%>
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-cog"></i> 基本设置</span>
        </div>

        <form action="" class="form-horizontal" id="basicForm">
            <div class="control-group">
                <label class="control-label">账号</label>
                <div class="controls">
                    <input type="text" value="${sessionScope.curr_user.username}" readonly>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">电子邮件</label>
                <div class="controls">
                    <input type="text" name="email" value="${sessionScope.curr_user.email}">
                </div>
            </div>
            <div class="form-actions">
                <button type="button" class="btn btn-primary" id="basicBtn">保存</button>
            </div>

        </form>

    </div>
    <!--box end-->
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-key"></i> 密码设置</span>
            <span class="pull-right muted" style="font-size: 12px">如果你不打算更改密码，请留空以下区域</span>
        </div>

        <form action="" class="form-horizontal" id="passwordForm">
            <div class="control-group">
                <label class="control-label">原始密码</label>
                <div class="controls">
                    <input type="password" name="oldpassword">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">新密码</label>
                <div class="controls">
                    <input type="password" name="newpassword" id="newpassword">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">重复密码</label>
                <div class="controls">
                    <input type="password" name="repassword">
                </div>
            </div>
            <div class="form-actions">
                <button type="button" id="passwordBtn" class="btn btn-primary">保存</button>
            </div>

        </form>

    </div>
    <!--box end-->

    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-user"></i> 头像设置</span>
        </div>

        <form action="" class="form-horizontal">
            <div class="control-group">
                <label class="control-label">当前头像</label>
                <div class="controls">
                    <img id="avatar" src="http://oi0x10ek3.bkt.clouddn.com/${sessionScope.curr_user.avatar}?imageView2/1/w/40/h/40" class="img-circle" alt="">
                </div>
            </div>
            <hr>
            <p style="padding-left: 20px">关于头像的规则</p>
            <ul>
                <li>禁止使用任何低俗或者敏感图片作为头像</li>
                <li>如果你是男的，请不要用女人的照片作为头像，这样可能会对其他会员产生误导</li>
            </ul>
            <div class="form-actions">
                <div id="picker">上传新头像</div>
            </div>


        </form>

    </div>
    <!--box end-->

</div>
<!--container end-->
<script src="/static/js/jquery-1.11.1.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/user/setting.js"></script>
<script src="/static/js/uploader/webuploader.js"></script>

<script type="text/template" id="bar">
    <div class="progress progress-striped active">
        <div class="bar" id="{{id}}" style="width: 40%;"></div>
    </div>
</script>

<script>
    $(function () {
        //头像上传
        var uploader = WebUploader.create({
            // swf文件路径
            swf: "/static/js/uploader/Uploader.swf",

            // 文件接收服务端。
            server: "http://up-z1.qiniu.com/",

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker',
            //自动上传
            auto:true,
            fileVal:"file",
            formData:{"token":"${token}"},
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }

        });

        //进度条
        uploader.on("uploadProgress",function (file,percentage) {

           /* var $li = $( '#'+file.id ),
                $percent = $li.find('.progress .progress-bar');*/

            var $bar = $("#"+file.id).find("#bar_"+file.id);
            // 避免重复创建
            /*if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                    '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                    '</div>' +
                    '</div>').appendTo( $li ).find('.progress-bar');
            }
            $percent.css( 'width', percentage * 100 + '%' );*/
            if(!$bar[0]) {
                var template = $("#bar").html();
                template = template.replace("{{id}}","bar_"+file.id);
                $("#"+file.id).append($(template));
            }

            $bar.css( 'width', percentage * 100 + '%' );

        });

        //上传成功
        uploader.on("uploadSuccess",function (file,data) {
            var fileKey = data.key;
            alert(fileKey);
            //修改数据库里的头像
            $.post("/setting?action=avatar",{"fileKey":fileKey})
                .done(function (data) {
                    if(data.state=="success"){
                        var url = "http://oi0x10ek3.bkt.clouddn.com/"+fileKey;
                        $("#avatar").attr("src",url+"?imageView2/1/w/40/h/40");
                        $("#navbar_avatar").attr("src",url+"?imageView2/1/w/20/h/20");
                    }
                }).error(function () {
                alert("头像设置失败");
            });
        });
        //上传失败
        uploader.on('uploadError',function(){
            alert("上传头像失败");
        });
    });
    
</script>


</body>
</html>
