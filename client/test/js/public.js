var base64;
document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
$(function() {
  $("#tab_1").click(function() {
    window.location.replace('schedule.html')
  })
  $("#tab_2").click(function() {
    window.location.replace('schedule_yi_fa_bu.html')
  })
  $("#tab_3").click(function() {
    window.location.replace('schedule_dai_jie_suan.html')
  })
  $("#tab_4").click(function() {
    window.location.replace('schedule_yi_qu_xiao.html')
  })
});


function mteamGroupList(mfun) {
  var keyName = "teamgrouplist";
  var map = new Map();
  map.set('type', gametype);
  map.set('name', "");
  map.set('pageIndex', 0);
  map.set('pageSize',50);
  var json = getJsonFromMap(map, keyName);



  var successAction= function(data) {
   mfun(data);
 }
 parVolleyJsonResultNoLoading(json, successAction)
}



function mGetschedule(id,mfun) {
 var keyName = "getschedule";
  var map = new Map();
  map.set('id', id);
  var json = getJsonFromMap(map, keyName);



  var successAction= function(data) {
   mfun(data);
 }
 parVolleyJsonResultNoLoading(json, successAction)
}

function mRequestUserInfo(mfun) {
  var keyName = "userinfo";
  var map = new Map();
  map.set('userid', storageGet(USER_ID));
  var json = getJsonFromMap(map, keyName);
  var successAction = function(data) {
    storageSet(USER_INFO, JSON.stringify(data.respbody));
    mfun(data);

    // weui.toast("登录成功", {

    //   duration: 1000,
    //   callback: function() {
    //     storageSet(USER_INFO, JSON.stringify(data.respbody));
    //     // requestUserInfo();
    //     window.location.replace('u_schedule.html')
    //   }
    // });
  }
  parVolleyJsonResult(json, successAction)
}


function parentPankoulist(childFun) {

  var keyName = "pankoutypelist";
  var map = new Map();
  var json = getJsonFromMap(map, keyName);
  var success= function(data) {
   childFun(data);

 }


 parVolleyJsonResultNoLoading(json,success)
}



function groupTeamList() {
  var keyName = "groupteam";
  var map = new Map();
  map.set('groupid', storageGet(TEAM_GROUP_DATA_ID));
  var json = getJsonFromMap(map, keyName);
  var successAction= function(data) {
    var mdata = JSON.stringify();
    　　$("#demo").tmpl(data.respbody.list).appendTo('#content');
  }
  parVolleyJsonResult(json, successAction)
}


function schedulelist(status) {
  var keyName = "schedulelist";
  var map = new Map();
  map.set('status', status);
  map.set('pageIndex', 0);
  map.set('pageSize', 100);
  var json = getJsonFromMap(map, keyName);
  var successAction = function(data) {
    $("#demo").tmpl(data.respbody.list, timerHelper).appendTo('#content');

  }
  parVolleyJsonResult(json, successAction)
}

  //时间计算方法 timllieanm TO XX天X小时X分钟后
  var timerHelper = {
    time: function(remaintime) {
      var rTimeFen = (Math.round((remaintime / (1000 * 60))) % 60) + 1;
        var t1=(remaintime / (1000 * 60 * 60 * 24));
      var t2=Math.round(t1);
      var t3=Math.abs(t2% 30);
      var rTime =t3 + "天" + Math.abs(Math.round((remaintime / (1000 * 60 * 60))) % 24) + "小时" + Math.abs((Math.round((remaintime / (1000 * 60))) % 60) + 1) + "分钟";
      if (rTimeFen >= 0) {
        return rTime + "后";
      } else {
        return rTime + "前";
      }
    },
    time_onlyday: function(remaintime) {
      var rTimeFen = (Math.round((remaintime / (1000 * 60))) % 60) + 1;
      var rTime;

      var t1=(remaintime / (1000 * 60 * 60 * 24));
      var t2=Math.round(t1);
      var t3=Math.abs(t2% 30);
      var tian=t3 + "天";
      var shi=Math.abs(Math.round((remaintime / (1000 * 60 * 60))) % 24) + "小时"   ;
      var fen =  Math.abs((Math.round((remaintime / (1000 * 60))) % 60) + 1) + "分钟";
      if(tian!="0天"){
       rTime=tian;
     }else{
      if(shi!="0小时"){
        rTime=shi;
      }else{
        rTime=fen;

      }
    }


    if (rTimeFen >= 0) {
      return rTime + "后";
    } else {
      return rTime + "前";
    }
  }
}



function updateScheduleStatus(id,status) {
  var keyName = "updateschedulestatus";
  var map = new Map();
  map.set('id', id);
  map.set('status', status);

  var json = getJsonFromMap(map, keyName);
  var successAction= function(data) { 
   weui.toast("操作成功", {
    duration: 1000,
    callback: function() {
     location.reload();
   }
 });
 }
 parVolleyJsonResult(json, successAction)
}

