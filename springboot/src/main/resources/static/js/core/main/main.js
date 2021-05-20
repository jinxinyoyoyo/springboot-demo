$(function () {

    //监听所有点击事件，是否已经登录，没有session跳转到登录页
    document.onclick = function (e) {
        $.ajax({
            url : "/loginController/getSessionid",
            type : "post",
            dataType : "json",
            contentType : "application/json;charset=utf-8",
            success : function (data) {
                var resultCode = data.resultCode;
                if(resultCode == 0) {
                    window.location.href = "/";
                }
            }
        });
    };

    //导入主页面头部内容
    $(".headerDiv").empty().load('../../../html/core/main/header/header.html', function () {
        $.ajax({
            url: "/userController/getUserBySessionid",
            type: "post",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                $("#loginTd").empty().html(data.resultMessage);
            }
        });
    });

    //导入左侧导航栏内容
    $("#leftDiv").empty().load('../../../html/core/main/nav/nav.html');
    //导入右侧展示信息内容，初始化加载职责页面内容
    $("#rightDiv").empty().load('../../../html/aqxj/duty/duty.html');

    /*--------------------------------------------------------------权限开始--------------------------------------------------------------------*/
    $.ajax({
        url : "/permissionController/getPermissionBySessionid",
        type : "post",
        dataType : "json",
        contentType : "application/json;charset=utf-8",
        success : function (data) {
            var resultCode = data.resultCode;
            if(resultCode == 1) {
                var resultMessage = data.resultMessage;
                // alert(resultMessage);
                console.log(data);
                var resultContent = data.resultContent;
                var permissionList = resultContent.permissionList;
                var str1 = "";//部门权限
                var str2 = "";//职责权限
                var str3 = "";//菜单权限
                $(".navSec li").hide();
                for(var i=0;i<permissionList.length;i++) {
                    var permissionMap = permissionList[i];
                    if(permissionMap.type == 1) {
                        // alert("可以看到哪些部门信息");
                    }else if(permissionMap.type == 2) {
                        // alert("可以看到哪些职责信息");
                    }else if(permissionMap.type == 3) {
                        // var c1 = "<li id='"+permissionMap.permission_desc+"' onclick=\"clickNavLi('"+permissionMap.permission_url+"')\"><a href='#'><span>"+permissionMap.permission_name+"</span></a></li>";
                        // str3 = str3 + c1;
                        $("#"+permissionMap.permission_desc).show();
                    }
                }
                // $(".leftUl").empty().append(str3);//菜单权限
            }else {
                window.location.href = "/";
            }
        }
    });
    /*--------------------------------------------------------------权限结束--------------------------------------------------------------------*/

})

//点击左侧导航列表各个项
function clickNavLi(url) {
    $("#rightDiv").empty().load(url);
}

//新打开的u3d页面对象
var MapWindow = null;

//点击三维地理
function clickU3d() {
    MapWindow = window.open('../../../js/core/u3d/UnityPageManager/out.html');
}

var instanceId = "";
var taskId = "";
