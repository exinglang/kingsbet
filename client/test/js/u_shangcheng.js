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
	});

	$('#content_zidong').delegate('#mclick', 'click',
		function() {
			var s=$.tmplItem(this);
    	 // storageSet(COMMODITY, JSON.stringify(s));

    	 storageSet(COMMODITY, JSON.stringify(s.data));
    	 window.location = ('u_shangcheng_detail.html');
    	}
    	);
	


	

});

function clickCharge(div,chargetype){
	$("#div_grids div").each(function(){  
		$(this).removeClass("weui-btn_chargeyellow");
		$(this).addClass("weui-btn_chargegrey");
	});  
	div.removeClass("weui-btn_commonyellow");
	div.addClass("weui-btn_chargeyellow");
	$("#chongzhijine").text(chargetype);
	// alert(chargetype);
}
function charge(){
	var cz=$("#chongzhijine");
	var amount= cz.text();

	var keyName = "charge";
	var map = new Map();
	map.set('userid', storageGet(USER_ID));
	map.set('amount', amount);

	var json = getJsonFromMap(map, keyName);
	var successAction= function(data) {
		weui.toast("充值成功");

		requestUserInfo();
	}
	parVolleyJsonResult(json,successAction);

}

function commoditylistShouDong() {
	var mFun=function (data) {
		$("#demo").tmpl(data.respbody.list).appendTo('#content_shoudong');
		 requestUserInfo();
	}
	parentCommoditylist("1",mFun);
}
function commoditylistZiDong() {
	var mFun=function (data) {
		$("#demo").tmpl(data.respbody.list).appendTo('#content_zidong');
		commoditylistShouDong();
	}
	parentCommoditylist("2",mFun);
}


function diamondtobalance(){
	

	var keyName = "diamondtobalance";
	var map = new Map();
	map.set('userid', storageGet(USER_ID));

	var json = getJsonFromMap(map, keyName);
	var successAction= function(data) {
		
		weui.toast("兑换成功");
		requestUserInfo();
	}
	parVolleyJsonResult(json,successAction);

}



function requestUserInfo() {
	var mTest = function(data) {
		setUserInfo();
	}
	mRequestUserInfo(mTest);
}




