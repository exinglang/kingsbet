var base64;

document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");
$(function() {


　$('#content').delegate('#delete', 'click',
  function() {
    var item = $.tmplItem(this);
    weui.confirm("确定删除 " + item.data.name + " 吗?  ",
    function() {
      deleteTeam(item.data.id);
      window.location = ('zhan_dui_zu_edit.html')
    });
  });

　$('#content').delegate('#deleteschedule', 'click',
  function() {
     var item = $.tmplItem(this);
   weui.dialog({

    content: '确定删除'+item.data.title1+item.data.title2+"?",
    buttons: [{
        label: '取消',
        type: 'default'
       
    }, {
        label: '确定',
        type: 'primary',
        onClick: function () { 
            deleteschedule(item.data.id);
         }
    }]
});
  });


$('#content').delegate('#colume', 'click',
    function() {
      var item = $.tmplItem(this);
        // var obj = { name:'Jim' };
        var data = item.data;
        var id=data.id;
        // var str1 = JSON.stringify(obj);
        // var data=item.data;
        // var save = JSON.parse(item.data);
         storageSet(SCHEDULE_ADD_IS_ADD, false);
                storageSet(SCHEDULE_ID, id);
               
        window.location=('schedule_add.html')

      });
  

$('#content').delegate('#pushschedule', 'click',
  function() {
     var item = $.tmplItem(this);
   weui.dialog({

    content: '确定发布'+item.data.title1+item.data.title2+"?",
    buttons: [{
        label: '取消',
        type: 'default'
       
    }, {
        label: '确定',
        type: 'primary',
        onClick: function () { 
            updateScheduleState(item.data.id,2);
         }
    }]
});
  });
  $('#content_pankou').delegate('#deletepankou', 'click',
  function() {

    var item = $.tmplItem(this);
    var sss = this.parentElement.parentElement;
    sss.removeChild(this.parentElement); // 拼接 id   
  });

$('#content').delegate('#deleteteam', 'click',
  function() {

    var item = $.tmplItem(this);
    var sss = this.parentElement.parentElement;
    sss.removeChild(this.parentElement); // 拼接 id   
  });


  $("#add_schedule").click(function() {
     storageSet(SCHEDULE_ADD_IS_ADD, true);
    window.location = ('schedule_add.html')
  })
});

function teamGroupList() {
  var mTest = function(data) {
    var mdata = JSON.stringify();　　$("#group_tmpl").tmpl(data.respbody.list).appendTo('#content_select');
  }
  mteamGroupList(mTest);

}

function groupTeamListSetGroupId() {
  // storageSet(TEAM_GROUP_ID, 2);
  storageSet(TEAM_GROUP_DATA_ID, $('#content_select option:selected').val()); //选中的值);
  groupTeamList();
}

function addPankou() {
  var json = [];
  var row1 = {
    name: $('#pankou').val().trim()
  }
  json.push(row1);
  $("#tmpl_pankou").tmpl(json).appendTo('#content_pankou');
  $("#pankou").val("");
}

function schedulelist(state) {
  var keyName = "schedulelist";
  var map = new Map();
  map.set('state', state);
  map.set('pageIndex', 0);
  map.set('pageSize', 100);
var json = getJsonFromMap(map, keyName);
CompositeImpl.prototype.success = function(data) {
 
                $("#demo").tmpl(data.respbody.list).appendTo('#content');
}
parVolleyJsonResult(json, new CompositeImpl())
}

function deleteschedule(id) {
  var keyName = "deleteschedule";
  var map = new Map();
  map.set('id', id);
var json = getJsonFromMap(map, keyName);
CompositeImpl.prototype.success = function(data) {
 
                weui.toast("已删除", {
      duration: 1000,
      callback: function() {
       location.reload();
       // window.location 
      }
    });
}
parVolleyJsonResult(json, new CompositeImpl())
}



function addSchedule() {
  var keyName = "addschedule";

  // "title1": "LGD",        //赛事名称
  //   "title2":"Liquid",//具体盘口  "进入前五"  "第一"  
  //   "teamlist":[1,6,8],//战队ID
  //   "time":"125239748992",
  var map = new Map();
  map.set('title1', $('#title1').val().trim());
  map.set('title2', $('#title2').val().trim());

  var oTimer = document.getElementById('time');
  var timeStamp = new Date(oTimer.value).getTime();
  map.set('time', timeStamp);

  var pankouNameList = new Array();
  $("#content_pankou p").each(function() {
    var mThis = $(this);
    var mtype = mThis.attr("id");
    // $(this).attr("class","changToWhite");
    if (mtype == "IS_NAME") {
      pankouNameList.push($(this).text());
    }

  });
  map.set('pankou', pankouNameList);

  var teamIdList = new Array();
  $("#content p").each(function() {
    var mThis = $(this);
    var mtype = mThis.attr("id");
    // $(this).attr("class","changToWhite");
    if (mtype == "IS_ID") {
      teamIdList.push(parseInt($(this).text()));
    }

  });

  map.set('teamlist', teamIdList);
  var json = getJsonFromMap(map, keyName);
  CompositeImpl.prototype.success = function() {
    weui.toast("添加成功", {
      duration: 1000,
      callback: function() {
        window.history.go( - 1);
      }
    });
  }
  parVolleyJsonResult(json, new CompositeImpl())
}


function updateScheduleState(id,state) {
  var keyName = "updateschedulestate";
  var map = new Map();
   map.set('id', id);
  map.set('state', state);
 
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