var base64;

document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");
$(function() {

　$('#pankou_tmpl_content').delegate('#delete', 'click',
    function() {
      var item = $.tmplItem(this);

      weui.confirm("确定删除 " + item.data.name + " 吗?  ",
        function() {
          deletePankou(item.data.id);
        });

    });


　

});




function addpankou() {

  var keyName = "addpankoutype";
  var map = new Map();
  map.set('name', $('#name').val().trim());
  map.set('type', $('#type').val().trim());
var json = getJsonFromMap(map, keyName);
var successAction= function(data) {
 
                weui.toast("添加成功", {
      duration: 1000,
      callback: function() {
       location.reload();
       // window.location 
      }
    });
}
parVolleyJsonResult(json, successAction)
}








function pankoulist() {
  var childFun = function(data) {
    weui.toast("", {
      duration: 0,
      callback: function() {
       　    $("#pankou_tmpl").tmpl(data.respbody.list).appendTo('#pankou_tmpl_content');

       // window.location 
      }
    });
  
  }
  parentPankoulist(childFun);
}


function deletePankou(id) {

  var keyName = "deletepankoutype";
  var map = new Map();
  map.set('id', id);
var json = getJsonFromMap(map, keyName);
var successAction= function(data) {

 
                weui.toast("删除成功", {
      duration: 1000,
      callback: function() {
       location.reload();
       // window.location 
      }
    });
}
parVolleyJsonResult(json,successAction)
}



