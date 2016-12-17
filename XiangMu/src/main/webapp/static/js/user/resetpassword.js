/**
 * Created by lenovo on 2016/12/17.
 */
$(function () {
    $("#resetBtn").click(function () {
        $("#resetForm").submit();
    });
    $("#resetForm").validate({
        errorElement:"span",
        errorClass:"text-error",
        rules:{
            password:{
                required:true,
                rangelength:[5,8]
            },
            repassword:{
                required:true,
                rangelength:[5,8],
                equalTo:"#password"
            }
        },
        messages:{
            password:{
                required:"请填写密码",
                rangelength:"密码长度为5~~8位"
            },
            repassword:{
                required:"请填写密码",
                rangelength:"密码长度为5~~8位",
                equalTo:"两次密码不一致"
            }
        },
        submitHandler:function () {
            $.ajax({
                url:"/foundpassword/newpassword",
                type:"post",
                data:$("#resetForm").serialize(),
                beforeSend:function () {
                    $("#resetBtn").text("正在保存。。。").attr("disabled","disabled");
                },
                success:function (data) {
                    if(data.state=="success"){
                        alert("重置密码成功");
                        window.location.href="/login";
                    }else {
                        alert(data.message);
                    }
                },
                error:function () {
                    alert("服务器错误，重置密码失败");
                },
                complete:function () {
                    $("#resetBtn").text("保存").removeAttr("disabled");
                }
            });
        }

    });
});