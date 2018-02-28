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

$('#content').delegate('#delete', 'click',
    function() {
      var item = $.tmplItem(this);

      weui.confirm("确定删除 " + item.data.name + " 吗?  ",
        function() {
          deleteCommodity(item.data.id);
        });

    });

    $("#img-change").click(function() {

    $("#teamImg").click();
  });

     $teamImg = $("#teamImg"),
  $teamImg.on("change",
    function(e) {
      var files = e.target.files,
      file;
      var reader = new FileReader();
        //先预览图片
        if (files && files.length > 0) {
            // 获取目前上传的文件
            file = files[0]; // 文件大小校验的动作
            if (file.size > 1024 * 1024 * 1) {
              alert('图片大小不能超过 2MB!');
              return false;
            }
            // 获取 window 的 URL 工具
            var URL = window.URL || window.webkitURL;
            // 通过 file 生成目标 url
            var imgURL = URL.createObjectURL(file);
            //用attr将img的src属性改成获得的url
            $("#img-change").attr("src", imgURL);
            // 使用下面这句可以在内存中释放对此 url 的伺服，跑了之后那个 URL 就无效了
            // URL.revokeObjectURL(imgURL);
            reader.onloadend = function(e) {
              base64 = e.target.result;
            };
            reader.readAsDataURL(file);

          }
        });

});

function addcommodity() {
    var keyName = "addcommodity";
    var map = new Map();
    var type= $('#select2 option:selected').val();
    var name = $('#name').val().trim();
        var price = $('#price').val().trim();

    map.set('name',name);
    map.set('price', price);
        map.set('img', base64);
    map.set('type', type);

    var json = getJsonFromMap(map, keyName);
    var successAction = function(data) {
         weui.toast("操作成功", {
    duration: 1000,
    callback: function() {
     location.reload();
   }
 });

    }
    parVolleyJsonResult(json, successAction);

}


function commoditylist() {
  var mFun=function (data) {
   $("#demo").tmpl(data.respbody.list).appendTo('#content');
}
parentCommoditylist("",mFun);
}



function deleteCommodity(id) {
  var keyName = "deletecommodity";
    var map = new Map();

    map.set('id',id);

    var json = getJsonFromMap(map, keyName);
   var successAction= function(data) {
    weui.toast("删除成功", {
      duration: 1000,
      callback: function() {
        location.reload();
      }
    });
  }
  parVolleyJsonResult(json, successAction)
}

 