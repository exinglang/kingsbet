var base64;

document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");
$(function() {
$('#content').delegate('#colume', 'click',
    function() {
     
      var item = $.tmplItem(this);
        // var obj = { name:'Jim' };
        var data = item.data;
        var id=data.id;
        // var str1 = JSON.stringify(obj);
        // var data=item.data;
        // var save = JSON.parse(item.data);
         storageSet(SCHEDULE_ADD_IS_ADD, false);
                storageSet(SCHEDULE_ID, id);
               
        window.location=('schedule_add.html')

      });
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
  var successAction= function(data) {

 
                weui.toast("已删除", {
      duration: 1000,
      callback: function() {
       location.reload();
       // window.location 
      }
    });
}
parVolleyJsonResult(json, successAction)
}