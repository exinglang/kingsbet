

var gametype = 2;//1、王者荣耀 2.绝地求生
var SESSION_STR = "sessionStr";
var TEAM_GROUP_DATA = "TEAM_GROUP_DATA";
var TEAM_GROUP_DATA_ID = "TEAM_GROUP_DATA_ID";

var SCHEDULE_ADD_IS_ADD = "SCHEDULE_ADD_IS_ADD";
var SCHEDULE_ID = "SCHEDULE_ID";


var CompositeImpl = function(){};
document.write("<script type='text/javascript' src='commonjs/MD5.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
// !(function() {
  function ajax(data, cb) {

    return $.ajax({
// var name = data.name,
url: 'http://localhost:8080/'+data.name,
contentType: 'application/json;charset=utf-8',
type: 'POST',
timeout : 20000, //超时时间设置，单位毫秒
data: JSON.stringify(data),
error: function(jqXHR, textStatus, errorThrown) {
// console.log(errorThrown);
}
})
  }

  function login(userId, password) {
    return ajax({
      "name":"adminlogin",
      reqbody: {
        "pwd": $.md5(userId + password),
        "userId": userId
      }
    })}





    function parVolleyJsonResult(mJson,mcallback ){

      var loading = weui.loading('');
      ajax(mJson).done(function(data) {
        loading.hide(function() {
          if (data.retcode === 0) {
           mcallback.success(data);
         }else{
           mAlert(data.msg);
         }
       })
      }
      ).fail(function() {
        hideLoadingAndAlertServerError(loading);
      })
// mcallback.success();
}


function getJsonFromMap(mmap, keyName) {
  mmap.keySet();
  var jsonBody = {};
for(var k of mmap){  //通过定义一个局部变量k遍历获取到了map中所有的key值  
  var key = k[0];
  var value =mmap.get(key);
  jsonBody[key]=value;
 
  // var docList=map[k]; //获取到了key所对应的value的值！   
}  
return putOtherJson(jsonBody,keyName);
}

function putOtherJson(jsonBody,keyName) {
 jsonBody["session"]=storageGet(SESSION_STR);
 var jsonRoot={};
 jsonRoot["name"]=keyName;
 jsonRoot["reqbody"]=jsonBody;
 return jsonRoot;
}

Map.prototype.keySet = function() {
  var keyset = new Array();
  var count = 0;
  for (var key in this.container) {
// 跳过object的extend函数
if (key == 'extend') {
  continue;
}
keyset[count] = key;
count++;
}
return keyset;
}

// this['http'] = {
//   login: login,

// }

// }
// (this))