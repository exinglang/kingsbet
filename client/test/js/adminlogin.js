var 
userId,password,
imgId,
flag = true,
imgFlag = true,
second = 120,
time = 0,
timerId = null;

document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/adminloginJson.js'></script>");
function requestLogin(){
  userId = $('#userId').val().trim()
  password = $('#password').val().trim()
  login(userId, password).done(function(data) {
   if (data.retcode === 0) {

    storageSet("sessionStr",data.respbody.sessionStr);

    window.location.replace('schedule.html')
        // weui.toast('注册成功', {
        //   duration: 2000,
        //   callback: function() {
        //     window.location.replace('/index')
        //   }
        // })

      } else {

       mAlert(data.msg);

     }

   }).fail(function() {
     hideLoadingAndAlertServerError(loading);
   })
 }





function requestRegister(){
  userId = $('#userId').val().trim();
  password = $('#password').val().trim();
  var json =getRegister(userId,password);
  CompositeImpl.prototype.success = function () {  
   weui.toast("注册成功",{
    duration:1000,
    callback:function(){
      window.location.replace('addteam.html')
    }
  });
 } 
 parVolleyJsonResult(json, new CompositeImpl() )
}


