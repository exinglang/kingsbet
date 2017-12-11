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
                  window.location=('zhan_dui_zu_edit.html')

        });

    });


  $("#add_schedule").click(function() {

    window.location=('schedule_add.html')
  })
 

});




function teamGroupList() {
  var mTest=function (data) {
 var mdata = JSON.stringify();　　
 $("#group_tmpl").tmpl(data.respbody.list).appendTo('#content_select');
}
mteamGroupList(mTest);

}

function groupTeamListSetGroupId() {
 // storageSet(TEAM_GROUP_ID, 2);
   storageSet(TEAM_GROUP_DATA_ID,$('#content_select option:selected').val());//选中的值);
groupTeamList();
}





function addSchedule() {
  var keyName = "addschedule";

  // "title1": "LGD",        //赛事名称
  //   "title2":"Liquid",//具体盘口  "进入前五"  "第一"  
  //   "teamlist":[1,6,8],//战队ID
  //   "time":"125239748992",
  var map=new Map();
  map.set('title1',$('#title1').val().trim());
    map.set('title2', $('#title2').val().trim());

  

 var teamIdList=new Array();

$("#content p").each(function(){  
  var mThis=$(this);
  var mtype=mThis.attr("id");
        // $(this).attr("class","changToWhite");

 if(mtype=="IS_ID"){
 teamIdList.push(  parseInt( $(this).text() ) );  
 }

    
    }); 

  map.set('teamlist',teamIdList);
  var json = getJsonFromMap(map, keyName);
  CompositeImpl.prototype.success = function() {
    weui.toast("添加成功", {
      duration: 1000,
      callback: function() {
        window.history.go(-1);
                // window.location.replace('zhan_dui_zu_edit.html')
                // window.location.replace('addteam.html')
              }
            });
  }
  parVolleyJsonResult(json, new CompositeImpl())
}


