var base64;
document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
$(function() {
  $("#tab_1").click(function() {
window.location.replace('schedule.html')
  })
   $("#tab_2").click(function() {
window.location.replace('schedule_yi_fa_bu.html')
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
 var successAction= function(data) {                $("#demo").tmpl(data.respbody.list).appendTo('#content');
               
}
parVolleyJsonResult(json, successAction)
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

