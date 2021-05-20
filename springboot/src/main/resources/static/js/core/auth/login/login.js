$(function () {

    //回车键登陆
    document.onkeydown = function (e) {
        var event = e || window.event;
        var code = event.keyCode || event.which || event.charCode;
        if (code == 13) {
            $("#submitBtn").trigger("click");
        }
    };

    $("#submitBtn").click(function () {
        var loginid = $("#loginid").val();
        var password = $("#password").val();

        //提交条件参数
        var submitData = {
            loginid: loginid,
            password: password
        };

        $.ajax({
            url: "/loginController/checkLogin",
            type: "post",
            dataType: "json",
            data: JSON.stringify(submitData),
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                var resultCode = data.resultCode;
                var resultMessage = data.resultMessage;
                var resultContent = '';
                if (!isNullOrEmpty(data.resultContent)) {
                    resultContent = data.resultContent;
                }
                if (resultCode == 1) {
                    window.location.href = "/mainController/toMain";
                    window.sessionStorage.setItem("userId", resultContent.id);
                } else {
                    alert(resultMessage);
                }
            }
        });

    });

});

//判断是否为null、空
function isNullOrEmpty(param) {
    if (param == '' || param == null || param == undefined) {
        return true;
    } else {
        return false;
    }
}
