

document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");
document.write("<script type='text/javascript' src='commonjs/constants.js'></script>");
var base64;
var mPankouid;
var canclick=true;
$(function() {
// 	$('#content').delegate('#jingcai', 'touchstart',
// 	function() {
// $.tmplItem(this).css({background: 323350});

// 	});
$('#content').delegate('#jingcai', 'click',
	function() {

			jingcai($.tmplItem(this));
		

	});

window.sMessageOpen = function(s){
	if(s){$("#sMessage,#sMessage"+s).fadeIn(233,"swing");
	var w_h = $(window).height();
	if(w_h>$("#sMessage"+s).height()){
		$("#sMessage"+s).css("marginTop",(w_h*0.3)-($("#sMessage"+s).height()*0.3));
	}
}
}
window.sMessageClose = function(s){
	if(s){$("#sMessage,#sMessage"+s).hide();}
}




});



function jingcai(item){
	// var name = item.name;
	var callback =function(){
		var amount = $('#amount').val().trim();

		if(canclick){
			canclick=false;
			order(item.data.id,amount);
		}
		
	}
  // JSON.stringify(jsonobj);
  var s = JSON.parse(storageGet(USER_INFO));



  
  sPrompt("可用K币:    "+s.balance,item.data.name,"",callback);
}



function order(teamid,amount) {

	var keyName = "order";
	var map = new Map();

	map.set('pankouid', mPankouid);
	map.set('teamid', teamid);
	map.set('amount', amount);
	map.set('userid', storageGet(USER_ID));
	var json = getJsonFromMap(map, keyName);
	var successAction = function(data) {
		// weui.toast("竞猜成功");



		var mTest = function(data) {
			weui.toast("竞猜成功", {
				duration: 1000,
				callback: function() {
        // requestUserInfo();
        location.reload();
    }
});


		}
		mRequestUserInfo(mTest);



		// mRequestUserInfo(null);

		
	}
	parVolleyJsonResult(json, successAction)
}

function getpankou(pankouid) {

	var keyName = "getpankou";
	var map = new Map();
	map.set('id', pankouid);
	var json = getJsonFromMap(map, keyName);
	var successAction = function(data) {
		var resp = data.respbody;
		$('#title1').text(resp.title1);
		$('#title2').text(resp.title2+resp.pankoutypename);
		$('#time').text(timerHelper.time(resp.time));
		var pankoutype = data.respbody.pankoutypetype;
		mPankouid=resp.id;
		if (pankoutype == 1) {
			$("#demo_pankoutype_1").tmpl(data.respbody.teamlist).appendTo('#content');
		} else {
			$("#demo_pankoutype_not1").tmpl(data.respbody.teamlist).appendTo('#content');
		}
	}
	parVolleyJsonResult(json, successAction)
}


