var base64;

document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");
document.write("<script type='text/javascript' src='commonjs/constants.js'></script>");

$(function() {


	$(':radio').click(function(){if(this.checked)$(this).next().css('color','#eeeeee').siblings('label').css('color','#d3a2a5')}); 

	$("#charge").click(function() {

		window.location = ('u_charge.html')
	})




});

function userregister() {
	var keyName = "userregister";
	var phone = $('#phone').val().trim();
	var pwd = $('#pwd').val().trim();
	var map = new Map();
	map.set('phone', phone);
	map.set('pwd', $.md5(phone + pwd));

	var json = getJsonFromMap(map, keyName);
	var successAction= function(data) {
		   weui.toast("注册成功");
	}
	parVolleyJsonResult(json,successAction)
}

function requestLogin() {
	var keyName = "userlogin";
	var phone = $('#phone').val().trim();
	var pwd = $('#pwd').val().trim();
	var map = new Map();
	map.set('phone', phone);
	map.set('pwd', $.md5(phone + pwd));

	var json = getJsonFromMap(map, keyName);
	var successAction= function(data) {
		storageSet(SESSION_STR,data.respbody.sessionStr);
		storageSet(USER_ID,data.respbody.userid);
		requestUserInfo();
	}
	parVolleyJsonResult(json,successAction)
}


function requestUserInfo() {
  var mTest = function(data) {
       weui.toast("登录成功", {
      duration: 1000,
      callback: function() {
        // requestUserInfo();
        window.location.replace('u_schedule.html')
      }
    });
  }
  mRequestUserInfo(mTest);
}

