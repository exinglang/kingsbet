var base64;

document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");
document.write("<script type='text/javascript' src='commonjs/weui.min.js'></script>");
document.write("<script type='text/javascript' src='commonjs/zidingyi.js'></script>");
document.write("<script type='text/javascript' src='js/public.js'></script>");
document.write("<script type='text/javascript' src='commonjs/constants.js'></script>");

$(function() {


$("#charge").click(function() {
    
    window.location = ('u_charge.html')
  })




});

function requestUserInfo() {
	var mTest = function(data) {
		setUserInfo();
	}
	mRequestUserInfo(mTest);
}







