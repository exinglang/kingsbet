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



  CompositeImpl.prototype.success = function(data) {
   mfun(data);
  }
  parVolleyJsonResult(json, new CompositeImpl())
}


function groupTeamList() {
  var keyName = "groupteam";
  var map = new Map();
  map.set('groupid', storageGet(TEAM_GROUP_DATA_ID));
  var json = getJsonFromMap(map, keyName);
  CompositeImpl.prototype.success = function(data) {
    var mdata = JSON.stringify();
    　　$("#demo").tmpl(data.respbody.list).appendTo('#content');
  }
  parVolleyJsonResult(json, new CompositeImpl())
}


function schedulelist(status) {
  var keyName = "schedulelist";
  var map = new Map();
  map.set('status', status);
  map.set('pageIndex', 0);
  map.set('pageSize', 100);
var json = getJsonFromMap(map, keyName);
CompositeImpl.prototype.success = function(data) {
                $("#demo").tmpl(data.respbody.list).appendTo('#content');
               
}
parVolleyJsonResult(json, new CompositeImpl())
}




function updateScheduleStatus(id,status) {
  var keyName = "updateschedulestatus";
  var map = new Map();
   map.set('id', id);
  map.set('status', status);
 
var json = getJsonFromMap(map, keyName);
CompositeImpl.prototype.success = function(data) {
 
               weui.toast("操作成功", {
      duration: 1000,
      callback: function() {
       location.reload();
      }
    });
}
parVolleyJsonResult(json, new CompositeImpl())
}

