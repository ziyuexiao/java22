/**
 * Created by lenovo on 2016/12/19.
 */
$(function () {
    //基本信息设置
    $("#basicBtn").click(function () {
        $("#basicForm").submit();
    });
    $("#basicForm").validate({
        errorElement:"span",
        errorClass:"text-error",
        rules:{
            email:{
                required:true,
                email:true,
                remote:"/validate/email?type=1"

            }
        },
        messages:{
            required:"请填写邮件地址",
            email:"邮件格式不正确",
            remote:"邮件已经被占用"
        },
        submitHandler:function (form) {
            $.ajax({
                url:"/setting?action=profile",
                type:"post",
                data:$(form).serialize(),
                beforeSend:function () {
                    $("#basicBtn").text("正在保存。。。").attr("disabled","disabled");
                },
                success:function (data) {
                    if(data.state=="success"){
                        alert("修改成功");
                        //swal("修改成功!", "You clicked the button!", "success");
                    }
                },
                error:function () {
                    alert("服务器错误");
                    //sweetAlert("Oops...", "服务器错误!", "error");
                },
                complete:function () {
                    $("#basicBtn").text("保存").removeAttr("disabled");
                }


            });
        }

    });



    //密码修改
    $("#passwordBtn").click(function () {
        $("#passwordForm").submit();
    });

    $("#passwordForm").validate({
        errorElement:"span",
        errorClass:"text-error",
        rules:{
            oldpassword:{
                required:true,
                rangelength:[5,8]
            },
            newpassword:{
                required:true,
                rangelength:[5,8]
            },
            repassword:{
                required:true,
                rangelength:[5,8],
                equalTo:"#newpassword"
            }
        },
        messages:{
            oldpassword:{
                required:"请输入原始密码",
                rangelength:"密码长度5-8个字符"
            },
            newpassword:{
                required:"请输入新密码",
                rangelength:"密码长度5-8个字符"
            },
            repassword:{
                required:"请输入确认密码",
                rangelength:"密码长度5-8个字符",
                equalTo:"两次密码不一致"
            }
        },
        submitHandler:function(form){
            $.ajax({
                url:"/setting?action=password",
                /*/setting?action=*/
                type:"post",
                data:$(form).serialize(),
                beforeSend:function(){
                    $("#passwordBtn").text("正保存。。。").attr("disabled","disabled");
                },
                success:function(data){
                    if(data.state=="success") {
                        alert("密码修改成功，重新登录");
                        //swal("密码修改成功，重新登录!", "You clicked the button!", "success");
                        window.location.href = "/login";
                    } else {
                        alert(data.message);
                        //sweetAlert(data.message);
                    }
                },
                error:function(){
                    alert("服务器错误");
                    //sweetAlert("Oops...", "服务器错误!", "error");
                },
                complete:function(){
                    $("#passwordBtn").text("保存").removeAttr("disabled");
                }
            });
        }
    });
});