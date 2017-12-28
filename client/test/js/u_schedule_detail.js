var base64;

document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");
document.write("<script type='text/javascript' src='commonjs/constants.js'></script>");

$(function() {
// 	$('#content').delegate('#jingcai', 'touchstart',
// 	function() {
// $.tmplItem(this).css({background: 323350});

// 	});
	$('#content').delegate('#jingcai', 'click',
	function() {

jingcai($.tmplItem(this));
   // $(".friend").on("touchstart", function() {
   //              $(this).css({background: #323350});

   //      }).on("touchend", function() {
   //              $(this).css({background: #EF3350 });
   //      });





		// var item = $.tmplItem(this);

		// weui.confirm("确定竞猜 " + item.data.name + " 吗?  ",
		// function() {
		// 	// deleteTeam(item.data.id);
		// 	// dow.location = ('zhwinan_dui_zu_edit.html')
		// });

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
    alert(123);
  }
  // JSON.stringify(jsonobj);
sPrompt("s1",item.data.name,"",callback);
}




function getpankou(pankouid) {

	var keyName = "getpankou";
	var map = new Map();
	map.set('id', pankouid);
	var json = getJsonFromMap(map, keyName);
	var successAction = function(data) {
		var resp = data.respbody;
		$('#title1').text(resp.title1);
		$('#title2').text(resp.title2);
		$('#time').text(timerHelper.time(resp.time));

		// .toJSONString();
		// $('#title1').text("resp.title1");
		// $('#title2').val(resp.title2);

		var pankoutype = data.respbody.pankoutype;

		if (pankoutype == 1) {
			$("#demo_pankoutype_1").tmpl(data.respbody.teamlist).appendTo('#content');
		} else {
			$("#demo_pankoutype_not1").tmpl(data.respbody.teamlist).appendTo('#content');
		}

	}
	parVolleyJsonResult(json, successAction)
}


