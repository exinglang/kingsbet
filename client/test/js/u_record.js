var base64;
// 页数
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
    $('.content2').dropload({
        scrollArea: window,
        loadDownFn: function(me) {

            orderhistory(me);

        }
    });

});

function orderhistory(me) {
    var keyName = "orderhistory";
    var map = new Map();
    map.set('userid', storageGet(USER_ID));
    map.set('pageindex', page);
    map.set('pagesize', size);

    var json = getJsonFromMap(map, keyName);
    var successAction = function(data) {
        if (data.respbody.list.length > 0) {

            $("#tmpl_lishi").tmpl(data.respbody.list,timerHelper).appendTo('#content');
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
    parVolleyJsonResult(json, successAction);

}

 