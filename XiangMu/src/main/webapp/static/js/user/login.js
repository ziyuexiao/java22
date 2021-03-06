/**
 * Created by lenovo on 2016/12/16.
 */
$(function () {

    function getParameterByName(name, url) {
        if (!url) {
            url = window.location.href;
        }
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }




    $("#loginBtn").click(function () {
        $("#loginForm").submit();
    });
    $("#password").keydown(function () {
        if(event.keyCode=="13"){
            $("#loginBtn").click();
        }
    });
    $("#loginForm").validate({
        errorElement:"span",
        errorClass:"text-error",
        rules:{
            username:{
                required:true
            },
            password:{
                required:true
            }
        },
        messages:{
            username:{
                required:"请输入账号"
            },
            password:{
                required:"请输入密码"
            }
        },
        submitHandler:function (form) {
            $.ajax({
                url:"/login",
                type:"post",
                data:$(form).serialize(),
                beforeSend:function () {
                    $("#loginBtn").text("登陆中。。。。").attr("disabled","disabled");
                },
                success:function (data) {
                    if(data.state=="success"){
                        //alert("登陆成功");
                        swal("登陆成功!", "You clicked the button!", "success");
                        var url = getParameterByName("redirect");
                        if(url) {
                            var hash = location.hash;
                            if(hash){
                                window.location.href = url+hash;
                            }else {
                                window.location.href = url;
                            }

                        } else {
                            window.location.href = "/home";
                        }
                    }else {
                        //alert(data.message);
                        sweetAlert(data.message);
                    }
                },
                error:function () {
                    //alert("服务器错误");
                    sweetAlert("Oops...", "服务器错误!", "error");
                },
                complete:function () {
                    $("#loginBtn").text("登陆").removeAttr("disabled");
                }

            });
        }
    });
});
