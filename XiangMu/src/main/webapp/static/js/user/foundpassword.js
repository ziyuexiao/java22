/**
 * Created by lenovo on 2016/12/17.
 */
$(function () {
    $("#btn").click(function () {
        $("#form").submit();
    });
    $("#type").change(function () {
        var value = $(this).val()
        if("email"==value){
            $("#typename").text("电子邮件");
        }
        if("phone"==value){
            $("#typename").text("手机号码");
        }
    });
    $("#form").validate({
        errorElement:"span",
        errorClass:"text-error",
        rules:{
            value:{
                required:true
            }
        },
        messages:{
            value:{
                required:"该项必须填写"
            }
        },
        submitHandler:function (form) {
            $.ajax({
                url:"/foundpassword",
                type:"post",
                data:$(form).serialize(),
                beforeSend:function () {
                    $("#btn").text("提交中。。。。").attr("disabled","disabled");
                },
                success:function (data) {
                    if(data.state=="success"){
                        var type = $("#type").val();
                        if("email"==type){
                            //alert("请查看邮件");
                            swal("请查看邮件!", "You clicked the button!", "success");
                        }else{
                            /*电话操作业务*/
                        }

                    }else {
                        //alert(data.message);
                        sweetAlert(data.message);
                    }
                },
                error:function () {
                    //alert(data.message);
                    sweetAlert(data.message);
                },
                complete:function () {
                    $("#btn").text("提交").removeAttr("disabled");
                }
            });
        }
    });
});