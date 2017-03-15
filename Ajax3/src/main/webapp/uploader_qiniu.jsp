<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/12/12
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/js/webuploader/webuploader.css">
</head>
<body>

<div id="picker">请选择文件</div>
<div id="result">

</div>

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/webuploader/webuploader.min.js"></script>

<script>
    $(function () {

        var uploader = WebUploader.create({
            swf:"static/js/Uploader.swf",
            server:"http://up-z1.qiniu.com/",
            pick:"#picker",
            fileVal:"file",
            formData:{"token":"${token}","x:id":"${id}"},
            auto:"true"
        });

        uploader.on("uploadSuccess",function (file,data) {

            var img = $("#result").find("img");
            if(img[0]){
                img.remove();
            }

          var url = "http://oi0x10ek3.bkt.clouddn.com/"+data.key;
          $("<img>").attr("src",url).addClass("img-rounded").appendTo($("#result"));

          alert(data["x:id"]);
            //session  {hash:"",key:"",x:id:123}
          //$.post("/updateavatar",{"id":data[x:id],"key":data.key}).done().error();//在服务端进行修改


        });

        uploader.on("uploadError",function (file) {
            alert("上传错误");
        });
    });

</script>

</body>
</html>
