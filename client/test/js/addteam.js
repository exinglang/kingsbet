var base64;

document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/addteamjson.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");

$(function() {

  　$('#content').delegate('#delete', 'click',
    function() {
      var item = $.tmplItem(this);

      weui.confirm("确定删除 " + item.data.name + " 吗?  ",
        function() {
          deleteTeam(item.data.id);
        });

    });

 
$('#content').delegate('#deletegroupteam', 'click',
    function() {
      var item = $.tmplItem(this);

      weui.confirm("确定删除 " + item.data.name + " 吗?  ",
        function() {
          deleteTeamGroupTeam(item.data.id);
        });

    });
  $('#content').delegate('#mcolume', 'click',
    function() {
      var item = $.tmplItem(this);
        // var obj = { name:'Jim' };
        var data = item.data;
        var id=data.id;
        // var str1 = JSON.stringify(obj);
        var str2 = JSON.stringify(data);
        // var data=item.data;
        // var save = JSON.parse(item.data);
        
                storageSet(TEAM_GROUP_DATA_ID, id);

        storageSet(TEAM_GROUP_DATA, str2);
        window.location=('zhan_dui_zu_edit.html')

      });



   $('#content').delegate('#deletegroupt', 'click',
    function() {
      var item = $.tmplItem(this);

      weui.confirm("确定删除 " + item.data.name + " 吗?  ",
        function() {
          deleteTeamGroup(item.data.id);
        });

    });
  $("#img-change").click(function() {

    $("#teamImg").click();
  })
  $("#gotoaddteam").click(function() {
   window.location=('zhan_dui_zu_add_team.html');
 })


  $teamImg = $("#teamImg"),
  $teamImg.on("change",
    function(e) {
      var files = e.target.files,
      file;
      var reader = new FileReader();
        //先预览图片
        if (files && files.length > 0) {
            // 获取目前上传的文件
            file = files[0]; // 文件大小校验的动作
            if (file.size > 1024 * 1024 * 1) {
              alert('图片大小不能超过 2MB!');
              return false;
            }
            // 获取 window 的 URL 工具
            var URL = window.URL || window.webkitURL;
            // 通过 file 生成目标 url
            var imgURL = URL.createObjectURL(file);
            //用attr将img的src属性改成获得的url
            $("#img-change").attr("src", imgURL);
            // 使用下面这句可以在内存中释放对此 url 的伺服，跑了之后那个 URL 就无效了
            // URL.revokeObjectURL(imgURL);
            reader.onloadend = function(e) {
              base64 = e.target.result;
            };
            reader.readAsDataURL(file);

          }
        })
});

// function addTeam(){
//    var  userId = $('#teamname').val().trim();
//    var loading = weui.loading('处理中...');

//    register(base64).done(function(data) {
//      loading.hide(function() {
//        if (data.retcode === 0) {
//          weui.toast("添加成功",{
//            duration:1000,
//            callback:function(){
//              window.location.replace('index.html')
//            }
//          });
//        } else {
//          mAlert(data.msg);
//        }
//      })
//    }).fail(function() {
//       hideLoadingAndAlertServerError(loading);
//    })
//  }
function addTeam() {
  var teamName = $('#teamname').val().trim();
  var json = getAddTeam(teamName, base64);
 var successAction= function(data) {
    weui.toast("添加成功", {
      duration: 1000,
      callback: function() {
        window.location.replace('addteam.html')
      }
    });
  }
  parVolleyJsonResult(json, successAction)
}

function addTeamGroup() {
  var teamGroupName = $('#teamgroupname').val().trim();
  var json = getAddTeamGroup(teamGroupName);
 var successAction= function(data) {
    weui.toast("添加成功", {
      duration: 1000,
      callback: function() {
        window.location.replace('zhan_dui_zu_guan_li.html')
                // window.location.replace('addteam.html')
              }
            });
  }
  parVolleyJsonResult(json, successAction)
}



function deleteTeam(id) {
  var json = getDeleteTeam(id);
   var successAction= function(data) {
    weui.toast("删除成功", {
      duration: 1000,
      callback: function() {
        window.location.replace('addteam.html')
      }
    });
  }
  parVolleyJsonResult(json, successAction)
}

function deleteTeamGroup(id) {
  var json = getDeleteTeamGroup(id);
  var successAction= function(data) {
    weui.toast("删除成功", {
      duration: 1000,
      callback: function() {
        window.location.replace('zhan_dui_zu_guan_li.html')
      }
    });
  }
  parVolleyJsonResult(json, successAction)
}
// "type":1,  //1、王者荣耀 2.绝地求生
//     "name":"LGD",//战队名字
//     "pageIndex" : 0 , //当前页码
//     "pageSize" : 2    //每页条数

function teamList() {
  var json = getTeamList("", 0);
 var successAction= function(data) {
    // var mdata = JSON.stringify();
    　　$("#demo").tmpl(data.respbody.list).appendTo('#content');
  }
  parVolleyJsonResult(json, successAction)
}







function deleteTeamGroupTeam(teamid) {
  var keyName = "deletegroupteam";
  var map = new Map();
  var tem = storageGet(TEAM_GROUP_DATA);
  var js = JSON.parse(tem);
  map.set('groupid', js.id);
  map.set('teamid', teamid);
  var json = getJsonFromMap(map, keyName);
  var successAction= function(data) {
    weui.toast("删除成功", {
      duration: 1000,
      callback: function() {
                window.location.replace('zhan_dui_zu_edit.html')
                // window.location.replace('addteam.html')
              }
            });
  }
  parVolleyJsonResult(json, successAction)
}

function updateTeamGroup() {
  var keyName = "updateteamgroup";
  var map = new Map();
  var tem = storageGet(TEAM_GROUP_DATA);
  var js = JSON.parse(tem);
  map.set('id', js.id);
  map.set('name', $('#teamgroupname').val().trim());
  var json = getJsonFromMap(map, keyName);
  var successAction= function(data) {
    weui.toast("修改成功", {
      duration: 1000,
      callback: function() {
                // window.location.replace('zhan_dui_zu_edit.html')
                // window.location.replace('addteam.html')
              }
            });
  }
  parVolleyJsonResult(json, successAction)
}




function teamGroupAddTeam() {
  var keyName = "teamgroupaddteam";
  var map = new Map();
  var tem = storageGet(TEAM_GROUP_DATA);
  var js = JSON.parse(tem);
  map.set('teamgroupid', js.id);
  var teamIdList=new Array();
//遍历
$(":checkbox").each(function(){
  if (true == $(this).prop("checked")) { 
    teamIdList.push($(this).val());
  }
});
map.set('teamidlist', teamIdList);
var json = getJsonFromMap(map, keyName);
var successAction= function(data) {
  weui.toast("修改成功", {
    duration: 1000,
    callback: function() {
                window.history.go(-1);
                // window.location.replace('addteam.html')
              }
            });
}
parVolleyJsonResult(json, successAction)
}


function teamGroupList() {
  
  var mTest=function (data) {
 // var mdata = JSON.stringify();　　
 $("#demo").tmpl(data.respbody.list).appendTo('#content');
}
mteamGroupList(mTest);

}






