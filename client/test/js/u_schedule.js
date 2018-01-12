var base64;
var page = 0;
// 每页展示5个
var size = 5;
document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");
document.write("<script type='text/javascript' src='commonjs/constants.js'></script>");

$(function() {

    // dropload
 

    $('#content').delegate('#colume', 'click',
    function() {

        var menu = document.getElementById("actionsheet_menu");

        // var menu = $('#actionsheet_menu');
        var item = $.tmplItem(this);
        var pankoulist = item.data.pankoulist;
        menu.innerHTML = "";

        for (var i in pankoulist) {
            var name = pankoulist[i].pankoutypename;
            var md = pankoulist[i].id;
            menu.innerHTML += "<div  class=\"weui-actionsheet__cell\" id=" + md + ">" + name + "</div>";

        }

        //Innerhtml后,会把前一个onclik清空,原因不明
        for (var i in pankoulist) {
            var name = pankoulist[i].pankoutypename;
            var item2 = document.getElementById(pankoulist[i].id);

            item2.onclick = function() {
                storageSet(U_SCHEDULE_PANKOU_ID, this.id);
                window.location = ('u_schedule_detail.html')
            }
        }

        var $iosActionsheet = $('#iosActionsheet');
        var $iosMask = $('#iosMask');

        function hideActionSheet() {
            $iosActionsheet.removeClass('weui-actionsheet_toggle');
            $iosMask.fadeOut(200);
        }
        $iosMask.on('click', hideActionSheet);
        $('#iosActionsheetCancel').on('click', hideActionSheet);
        $("#showIOSActionSheet").on("click",
        function() {});

        $iosActionsheet.addClass('weui-actionsheet_toggle');
        $iosMask.fadeIn(200);

    }

    );

});

function schedulelist(status) {

    var successAction = function(data) {
        $("#demo").tmpl(data.respbody.list, timerHelper).appendTo('#content');
           $('.content2').dropload({
        scrollArea: window,
        loadDownFn: function(me) {
            schedulelistold(6, me);
        }
    });
        // schedulelistold(6);
    }
    mSchedulelist(status, successAction,0,100);
}
function schedulelistold(status, me) {

    var successAction = function(data) {

        if (data.respbody.list.length > 0) {

            $("#tmpl_history").tmpl(data.respbody.list, timerHelper).appendTo('#content3');
            page = page + size;
            me.resetload();
        } else {
            // 锁定
            me.lock();
            // 无数据
            me.noData();
            me.resetload();
        }

    }
    mSchedulelist(status, successAction,page,size)
}