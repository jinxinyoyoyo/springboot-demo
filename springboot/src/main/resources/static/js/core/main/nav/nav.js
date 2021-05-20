$(function () {
    // 菜单栏宽度
    var toggle = true;
    $("#navLogoDiv").click(function () {
        if (!toggle) {
            $("#leftDiv").addClass("left-open").removeClass("left-close");
            $("#rightDiv").addClass("right-open").removeClass("right-close");
            // $(".leftUl span").css({"position": "absolute", "display": "block", "left": "38px"});
            // $('#navLogoDiv span').show();
            $('#navLogoDiv span').show();
        } else {
            $("#leftDiv").removeClass("left-open").addClass("left-close");
            $("#rightDiv").removeClass("right-open").addClass("right-close");
            // $(".leftUl span").css({"position": "relative", "display": "none"});
            // $('#navLogoDiv span').hide();
            $('#navLogoDiv span').hide();

        }
        toggle = !toggle;
    });
    // 菜单栏点击之后变色
    $(".navListDiv .leftUl .cliBg span").click(function(){
        $(".navListDiv .cliBg").removeClass('activeBg').addClass('bgWit');
        $(this).parent().addClass('activeBg').removeClass('bgWit');
        $(".navSec li").removeClass('activeSeed');
        $(this).addClass('active').parent().siblings().find('span').removeClass('active');
        // 判断是否点击设置中某一项
        if($("#sets").hasClass('activeBg')){
            $(".triangle").addClass('actTri');
            $("#sets").children('span').addClass('active');
        }else{
            $(".triangle").removeClass('actTri');
            $("#sets").children('span').removeClass('active');
        }
    })
    // 综合查询
    $("#seoQuery").click(function () {
        $("#rightDiv").empty().load('../../../html/aqxj/querySeo/querySeo.html');
    })
    // 点击设置
    var sets = true;
    $(".setting").click(function () {
        if(sets){
            $('.setFam').show();
        }else{
            $('.setFam').hide();
        }
        sets = !sets;
    });
    // 直接点击设置中的某一项
    $(".navSec li").click(function(){
        $(".navListDiv .cliBg").removeClass('activeBg').addClass('bgWit');
        $("#sets").addClass('activeBg').removeClass('bgWit');
        $(".navListDiv .cliBg span").removeClass('active');
        $("#sets span").addClass('active');
        $(".triangle").addClass('actTri');
        $(".navSec li").removeClass('activeSeed');
        $(this).addClass('activeSeed');
        $(this).children('span').addClass('active').parent().siblings().find('span').removeClass('active');

    })
    // 右边跳转
    $("#userLi").click(function () {
        // alert(MapWindow);
        // if(MapWindow) {
        //     MapWindow.SaySomethingToUnity(111);
        // }
        $("#rightDiv").empty().load('../../../html/core/auth/user/user.html');
    });

    $("#roleLi").click(function () {
        $("#rightDiv").empty().load('../../../html/core/auth/role/role.html');
    });

    $("#regionLi").click(function () {
        $("#rightDiv").empty().load('../../../html/aqxj/region/region.html');
    });

    $("#dutyLi").click(function () {
        $("#rightDiv").empty().load('../../../html/aqxj/duty/duty.html');
    });

    $("#holidayLi").click(function () {
        $("#rightDiv").empty().load('../../../html/aqxj/holiday/holiday.html');
    });

    $("#pendingLi").click(function () {
        $("#rightDiv").empty().load('../../../html/aqxj/pending/pending.html');
    });
    //巡检标准项
    $("#standardLi").click(function () {
        $("#rightDiv").empty().load('../../../html/aqxj/standard/standard.html');
    });

    $("#hazardLi").click(function () {
        $("#rightDiv").empty().load('../../../html/aqxj/hazard/hazard.html');
    });

    $("#planLi").click(function () {
        $("#rightDiv").empty().load('../../../html/aqxj/plan/plan.html');
    });
    //班别
    $("#scheduleLi").click(function () {
        $("#rightDiv").empty().load('../../../html/aqxj/schedule/schedule.html');
    });
    //排班规则管理
    $("#scheduleRuleLi").click(function () {
        $("#rightDiv").empty().load('../../../html/aqxj/scheduleRule/scheduleRule.html');
    });
    //人员排班
    $("#rosterLi").click(function () {
        $("#rightDiv").empty().load('../../../html/aqxj/roster/roster.html');
    });
    //管控规则
    $("#controlSettingLi").click(function () {
        $("#rightDiv").empty().load('../../../html/aqxj/controlSetting/controlSettingMain.html' );

    });
});
