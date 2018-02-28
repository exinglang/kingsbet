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


	$("#bt_jian").click(function() {
		var te=$("#input_number").val();
		if(te==0){
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





