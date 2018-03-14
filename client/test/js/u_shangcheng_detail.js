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

	$("#next").click(function() {
		var s = JSON.parse(storageGet(USER_INFO));
		var totalPrice =parseInt($("#input_number").val())*parseInt($("#price").text());
		if(s.balance<totalPrice){
			mAlert("余额不足,请充值");
			return;
		}



		var data = JSON.parse(storageGet(COMMODITY));
		data.number=$("#input_number").val();
		data.totalPrice=totalPrice;
		storageSet(EXCHANGE, JSON.stringify(data));


		 //根据商品,选择要跳转的页面

		 var s = JSON.parse(storageGet(COMMODITY));

		 var name =JSON.stringify(s.name);
		 if(name.indexOf("话费") > 0 ){
		 	window.location = ('u_shangcheng_detail_huafei.html');
		 } 
		 if(name.indexOf("京东") > 0 ){
		 	window.location = ('u_shangcheng_detail_jd.html');
		 } 


		// exchange(totalPrice);
	});
	$("#exchange_hf").click(function() {
		var s = JSON.parse(storageGet(USER_INFO));
		var totalPrice =parseInt($("#input_number").val())*parseInt($("#price").text());
		if(s.balance<totalPrice){
			mAlert("余额不足,请充值");
			return;
		}
		exchange(1,$("#input_phone").val());
	});

	$("#exchange_jd").click(function() {
		var s = JSON.parse(storageGet(USER_INFO));
		var totalPrice =parseInt($("#input_number").val())*parseInt($("#price").text());
		if(s.balance<totalPrice){
			mAlert("余额不足,请充值");
			return;
		}
		exchange(2,null);
	});
	
	$("#bt_jian").click(function() {
		var te=$("#input_number").val();
		if(te==1){
			return;
		}
		var number = parseInt(te);
		var number_1=number-1;
		$("#input_number").val(number_1);
	});
	$("#bt_jia").click(function() {
		var te=$("#input_number").val();
		var number = parseInt(te);
		var number_1=number+1;
		$("#input_number").val(number_1);
	});
	$("#input_number").click(function() {
	});



	$('#content_zidong').delegate('#mclick', 'click',
		function() {
			storageSet(COMMODITY, $.tmplItem(this));
			window.location = ('u_shangcheng_detail.html')
		}
		);

});


function setCommodity(){
	
	var s = JSON.parse(storageGet(COMMODITY));

	$("#price").text(s.price);
	$("#img").attr("src",s.img);
	$("#name").text(s.name);
}
//请求兑换商品
function exchange(extype,phone){

		// data.number=$("#input_number").val();
		// data.totalPrice=totalPrice;
		// storageSet(EXCHANGE, JSON.stringify(data));


		//  //根据商品,选择要跳转的页面

		//  var s = JSON.parse(storageGet(COMMODITY));

		//  var name =JSON.stringify(s.name);
		//  if(name.indexOf("话费") > 0 ){
		//  	 window.location = ('u_shangcheng_detail_huafei.html');
		//  } 



		var data = JSON.parse(storageGet(EXCHANGE));

		var keyName = "exchange";
		var map = new Map();
		map.set('userid', storageGet(USER_ID));
		map.set('number', data.number);
		var s = JSON.parse(storageGet(COMMODITY));
		map.set('commodityId', data.id);
		map.set('price', data.totalPrice);
		map.set('extype', extype);
		map.set('phone', phone);
		map.set('productid', data.productid);
		var json = getJsonFromMap(map, keyName);
		var successAction = function(data) {


			weui.toast("兑换成功", {
				duration: 1000,
				callback: function() {
					window.location = ('u_shangcheng.html');

				}
			});
		}
		parVolleyJsonResult(json, successAction);

	}





