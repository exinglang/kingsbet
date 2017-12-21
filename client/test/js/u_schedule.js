var base64;

document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");
document.write("<script type='text/javascript' src='commonjs/constants.js'></script>");

$(function() {


$('#content').delegate('#colume', 'click',
    function() {

var menu = document.getElementById("actionsheet_menu");

// var menu = $('#actionsheet_menu');
 var item = $.tmplItem(this);
var pankoulist = item.data.pankoulist;
menu.innerHTML="";

for(var i in pankoulist){
  var name = pankoulist[i].name;
  var md = pankoulist[i].id;
menu.innerHTML+="<div  class=\"weui-actionsheet__cell\" id="+md+">"+name+"</div>";


}

//Innerhtml后,会把前一个onclik清空,原因不明
for(var i in pankoulist){
  var name = pankoulist[i].name;
var item2 = document.getElementById(pankoulist[i].id);

item2.onclick =function(){
  storageSet(U_SCHEDULE_PANKOU_ID,this.id);
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
        $("#showIOSActionSheet").on("click", function(){
        });






  $iosActionsheet.addClass('weui-actionsheet_toggle');
  $iosMask.fadeIn(200);

      }

      );





});





