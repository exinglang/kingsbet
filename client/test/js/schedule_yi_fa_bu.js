var base64;
var mRank=0;
document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");
$(function() {
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
  　$('#content').delegate('#cancelschedule', 'click',
    function() {
     var item = $.tmplItem(this);
     weui.dialog({

      content: '确定取消比赛'+item.data.title1+item.data.title2+"?",
      buttons: [{
        label: '取消',
        type: 'default'

      }, {
        label: '确定',
        type: 'primary',
        onClick: function () { 
          updateScheduleStatus2(item.data.id,4);
        }
      }]
    });
   });

  $('#content').delegate('#jiesuan', 'click',
    function() {
     var item = $.tmplItem(this);
     storageSet(JIE_SUAN_ID, item.data.id);
     window.location=('jie_suan.html')

   });


  $('#content').delegate('#colume2', 'click',
    function() {


    // var menu = $('#actionsheet_menu');
    var item = $.tmplItem(this);
    // var itmejs= JSON.stringify(item);
    mRank++;
    item.data.rank=mRank;
       // item.data.name2=item.data.name;
       // item.data.id=rank;

    // var pankoulist = item.data.rank;
    // alert(pankoulist);
     // $("#demo").tmpl(data.respbody.teamlist).appendTo('#content');
     $("#tmpl_rank").tmpl(item.data).appendTo('#tmpl_content_xuanze');

     var ss=$("#demo");
     
     var sss = this.parentElement;
    sss.removeChild(this); // 拼接 id   
  }

  );
  　

});


function getschedule(id) {
  var mFun = function(data) {
   $("#demo").tmpl(data.respbody.teamlist).appendTo('#content');
 }
 mGetschedule(id,mFun);
}

function cancelschedule(id) {
  var keyName = "cancelschedule";
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
  parVolleyJsonResult(json, successAction)
}

function schedulelist(status) {
 
  var successAction = function(data) {
    $("#demo").tmpl(data.respbody.list, timerHelper).appendTo('#content');

  }
  mSchedulelist(status, successAction,0,100);
}


function updateScheduleStatus(id,status) {
  var mFun = function(data) {
    window.history.go(-1);
  }
  mUpdateScheduleStatus(id,status,mFun);
}
//管理员界面取消比赛后
function updateScheduleStatus2(id,status) {
  var mFun = function(data) {

    weui.toast("已取消,投注已退回用户账户", {
    duration: 1000,
    callback: function() {
     location.reload();
   }
 });
     
  }
  mUpdateScheduleStatus(id,status,mFun);
}
function setschedulerank(id) {
  var pankouIdList = new Array();
  var size = $('#content').children().length;
  if(size!=0){
    weui.toast("请将所有队伍排名");
    return;
  }
 // alert(size);
 $("#tmpl_content_xuanze p").each(function() {
  var mThis = $(this);
  var row1 = {};
  row1.id= mThis.attr("id");
  row1.rank = mThis.attr("value");
  pankouIdList.push(row1);
});

 var keyName = "setschedulerank";
 var map = new Map();
 map.set('scheduleid', storageGet(JIE_SUAN_ID));
 map.set('teamidlist',pankouIdList);

 var json = getJsonFromMap(map, keyName);
 var successAction= function(data) {


  weui.toast("结算成功", {
    duration: 1000,
    callback: function() {
      updateScheduleStatus(storageGet(JIE_SUAN_ID),5);
         // 
       // window.location 
     }
   });
}
parVolleyJsonResult(json, successAction)
}
