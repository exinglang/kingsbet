var base64;

document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");
document.write("<script type='text/javascript' src='commonjs/constants.js'></script>");

$(function() {


$('#content').delegate('#jingcai', 'click',
    function() {
var item = $.tmplItem(this);
    weui.confirm("确定竞猜 " + item.data.name + " 吗?  ",
    function() {
      // deleteTeam(item.data.id);
      // dow.location = ('zhwinan_dui_zu_edit.html')
    });

}
      );





});


function getpankou(pankouid){

var keyName = "getpankou";
  var map = new Map();
  map.set('id', pankouid);
var json = getJsonFromMap(map, keyName);
var successAction= function(data) {
                $("#demo").tmpl(data.respbody.teamlist).appendTo('#content');
               
}
parVolleyJsonResult(json, successAction)
}


