var gametype = 2; //1、王者荣耀 2.绝地求生
var SESSION_STR = "sessionStr";
var USER_ID = "USER_ID";
var TEAM_GROUP_DATA = "TEAM_GROUP_DATA";
var TEAM_GROUP_DATA_ID = "TEAM_GROUP_DATA_ID";

var SCHEDULE_ADD_IS_ADD = "SCHEDULE_ADD_IS_ADD";
var SCHEDULE_ID = "SCHEDULE_ID";
var U_SCHEDULE_PANKOU_ID = "U_SCHEDULE_PANKOU_ID";
var USER_INFO = "USER_INFO";
var JIE_SUAN_ID="JIE_SUAN_ID";
var COMMODITY="COMMODITY";

var CompositeImpl = function() {};
document.write("<script type='text/javascript' src='commonjs/MD5.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
// !(function() {
function ajax(data) {

  return $.ajax({
    // var name = data.name,
    url: 'http://localhost:8080/' + data.name,
    contentType: 'application/json;charset=utf-8',
    type: 'POST',
    timeout: 20000,
    //超时时间设置，单位毫秒
    data: JSON.stringify(data),
    error: function(jqXHR, textStatus, errorThrown) {
      // console.log(errorThrown);
    }
  })
}

function parVolleyJsonResult(mJson, mcallback) {
  parVolleyJsonResultIfLoading(true, mJson, mcallback)

  // mcallback.success();
}
function parVolleyJsonResultNoLoading(mJson, mcallback) {
  parVolleyJsonResultIfLoading(false, mJson, mcallback)

}
function parVolleyJsonResultIfLoading(showloading, mJson, mcallback) {

  if (showloading) {
    var loading = weui.loading('');
  }
  ajax(mJson).done(function(data) {
    if (showloading) {

      loading.hide(function() {
        mAction(mcallback, data);
      });

    } else {
      mAction(mcallback, data);
    }

  }).fail(function() {
    if (showloading) {
      hideLoadingAndAlertServerError(loading);
    } else {

      mAlert(server_code_error);
    }
  })

}

function mAction(mcallback, data) {
  if (data.retcode === 0) {
    mcallback(data);
  } else {
    mAlert(data.msg);
  }

}



function getJsonFromMap(mmap, keyName) {
  mmap.keySet();
  var jsonBody = {};
  for (var k of mmap) { //通过定义一个局部变量k遍历获取到了map中所有的key值  
    var key = k[0];
    var value = mmap.get(key);
    jsonBody[key] = value;

    // var docList=map[k]; //获取到了key所对应的value的值！   
  }
  return putOtherJson(jsonBody, keyName);
}

function putOtherJson(jsonBody, keyName) {
  jsonBody["session"] = storageGet(SESSION_STR);
  var jsonRoot = {};
  jsonRoot["name"] = keyName;
  jsonRoot["reqbody"] = jsonBody;
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



function setTabText() {
  var d = document.getElementById('tab_1'); //获取div的节点
  d.innerHTML = TAB_1;
  var d2 = document.getElementById('tab_2'); //获取div的节点
  d2.innerHTML = TAB_2;
}

function setUserInfo() {
  var s = JSON.parse(storageGet(USER_INFO));
  $('#diamond').text(s.diamond);
  $('#balance').text(s.balance);

}

Date.prototype.Format = function(fmt) { //author: meizz  
  var o = {
    "M+": this.getMonth() + 1,
    //月份  
    "d+": this.getDate(),
    //日  
    "h+": this.getHours(),
    //小时  
    "m+": this.getMinutes(),
    //分  
    "s+": this.getSeconds(),
    //秒  
    "q+": Math.floor((this.getMonth() + 3) / 3),
    //季度  
    "S": this.getMilliseconds() //毫秒  
  };
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o) if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
};

// this['http'] = {
//   login: login,
// }
// }
// (this))
