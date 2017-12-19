var base64;

document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");
$(function() {

　$('#content').delegate('#cancelschedule', 'click',
  function() {
     var item = $.tmplItem(this);
   weui.dialog({

    content: '确定取消比赛'+item.data.title1+item.data.title2+"?",
    buttons: [{
        label: '取消',
        type: 'default'
       
    }, {
        label: '确定',
        type: 'primary',
        onClick: function () { 
            updateScheduleState(item.data.id,4);
         }
    }]
});
  });
　

});




function cancelschedule(id) {
  var keyName = "cancelschedule";
  var map = new Map();
  map.set('id', id);
var json = getJsonFromMap(map, keyName);
CompositeImpl.prototype.success = function(data) {
 
                weui.toast("已删除", {
      duration: 1000,
      callback: function() {
       location.reload();
       // window.location 
      }
    });
}
parVolleyJsonResult(json, new CompositeImpl())
}