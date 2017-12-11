var base64;
document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
$(function() {

  
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


