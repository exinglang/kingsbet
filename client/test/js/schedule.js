var base64;

document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");
$(function() {


  　$('#content').delegate('#delete', 'click', 
    function() {
      var item = $.tmplItem(this);
      weui.confirm("确定删除 " + item.data.name + " 吗?  ",
        function() {
          deleteTeam(item.data.id);
          dow.location = ('zhwinan_dui_zu_edit.html')
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
          updateScheduleStatus(item.data.id,2);
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
function isAdd(){
  var isAdd=storageGet(SCHEDULE_ADD_IS_ADD);
  if(isAdd=="false"){
    return false;

  }else{
    //新建
    return true;
    
  }
}

function teamGroupList() {
  var mTest = function(data) {
    var mdata = JSON.stringify();　　
    $("#group_tmpl").tmpl(data.respbody.list).appendTo('#content_select');
    // var isAdd=storageGet(SCHEDULE_ADD_IS_ADD);

    if (isAdd() == false) {
      //编辑
      weui.toast("如已发布,请只修改比赛时间!");
      getschedule(storageGet(SCHEDULE_ID));
      // var btnVal = document.getElementById("commit");
      // btnVal.innerHTML = "更新比赛";
      $("#commit").text("更新比赛");
    }
  }
  mteamGroupList(mTest);
}

function pankoulist() {
  var ss=1;
  var childFun = function(data) {
    $("#pankou_tmpl").tmpl(data.respbody.list).appendTo('#pankou_tmpl_content');
  }
  parentPankoulist(childFun);
}




// function getschedule(id) {
//   var keyName = "getschedule";
//   var map = new Map();
//   map.set('id', id);
//   var json = getJsonFromMap(map, keyName);
//   var successAction= function(data) {
  



//  }
//  parVolleyJsonResult(json, successAction)
// }


function getschedule(id) {
  var mFun = function(data) {
    var resp=data.respbody;
    $('#title1').val(resp.title1);
    $('#title2').val(resp.title2 );
    var time =new Date(parseInt(resp.time)).Format("yyyy-MM-ddThh:mm:ss")
    $('#time').val(time);

    $("#tmpl_pankou").tmpl(resp.pankoulist).appendTo('#content_pankou');
    $("#demo").tmpl(resp.teamlist).appendTo('#content'); 
  }
  mGetschedule(id,mFun);
}


function updateScheduleStatus(id,status) {
  var mFun = function(data) {
   weui.toast("操作成功", {
    duration: 1000,
    callback: function() {
     location.reload();
   }
 });
 }
 mUpdateScheduleStatus(id,status,mFun);
}





function groupTeamListSetGroupId() {
  // storageSet(TEAM_GROUP_ID, 2);
  storageSet(TEAM_GROUP_DATA_ID, $('#content_select option:selected').val()); //选中的值);
  groupTeamList();
}

function addPankou() {
  var json = [];
  var s = $('#pankou_tmpl_content option:selected')
  var row1 = {
    pankoutypename:s.val(),pankoutypeid: s.attr("id")

  }
  json.push(row1);
  $("#tmpl_pankou").tmpl(json).appendTo('#content_pankou');
}



function deleteschedule(id) {
  var keyName = "deleteschedule";
  var map = new Map();
  map.set('id', id);
  var json = getJsonFromMap(map, keyName);
  var successAction= function(data) {
   
    weui.toast("已删除", {
      duration: 1000,
      callback: function() {
       location.reload();
       // window.location 
     }
   });
  }
  parVolleyJsonResult(json,successAction)
}

function schedulelist(status) {
 
  var successAction = function(data) {
    $("#demo").tmpl(data.respbody.list, timerHelper).appendTo('#content');

  }
  mSchedulelist(status, successAction,0,100)
}

function addSchedule() {
  var keyName;
  var map = new Map();
  if(isAdd()==true){


    keyName = "addschedule";
  }else{
    map.set('id',  storageGet(SCHEDULE_ID));
    keyName="updateschedule";
  }
  // "title1": "LGD",        //赛事名称
  //   "title2":"Liquid",//具体盘口  "进入前五"  "第一"  
  //   "teamlist":[1,6,8],//战队ID
  //   "time":"125239748992",
  
  map.set('title1', $('#title1').val().trim());
  map.set('title2', $('#title2').val().trim());
  // map.set('time', $('#time').val().trim());

  var oTimer = document.getElementById('time');
  var timeStamp = new Date(oTimer.value).getTime();
  map.set('time', timeStamp);

  var pankouIdList = new Array();

  $("#content_pankou p").each(function() {
    var mThis = $(this);
    var mtype = mThis.attr("id");
    // $(this).attr("class","changToWhite");
    if (mtype == "IS_NAME") {
      pankouIdList.push(mThis.attr("value"));
    }

  });
  map.set('pankoutypeidlist', pankouIdList);

  var teamIdList = new Array();
  $("#content p").each(function() {
    var mThis = $(this);
    var mtype = mThis.attr("id");
    // $(this).attr("class","changToWhite");
    if (mtype == "IS_ID") {
      teamIdList.push(parseInt($(this).text()));
    }

  });

  map.set('teamidlist', teamIdList);
  var json = getJsonFromMap(map, keyName);
  var successAction= function(data) {

    weui.toast("添加成功", {
      duration: 1000,
      callback: function() {
        window.history.go( - 1);
      }
    });
  }
  parVolleyJsonResult(json, successAction)
}











