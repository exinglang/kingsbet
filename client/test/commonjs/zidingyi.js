var server_code_error='服务器错误';


function mloading(text){
  return weui.loading(text);
}
function hideLoadingAndAlert(loading,text){
loading.hide(function() {
      weui.topTips(text);
     });
}
function hideLoadingAndAlertServerError(loading,text){
hideLoadingAndAlert(loading,server_code_error);
}

function mAlert(text){
 // weui.alert(text);
 weui.topTips(text);//测试阶段 所有alert改为topTips,便于测试
}

function  storageSet(key,text){
window.sessionStorage.setItem(key,text);
}
function  storageGet(key){
return window.sessionStorage.getItem(key)
}




function sPrompt(s1,t1,v1,callback){
 window.sPromptValue = "";
 sMessageOpen("Prompt");
 if(!t1){
  $("#sMessagePrompt .sT").html("");
 }else{
  $("#sMessagePrompt .sT").html(t1);
 }
 if(!s1){
  $("#sMessagePrompt .sS p").html("");
 }else{
  $("#sMessagePrompt .sS p").html(s1);
 }
 if(!v1){
  $("#sMessagePrompt .sS input.txt").val("");
 }else{
  $("#sMessagePrompt .sS input.txt").val(v1);
 }
 $("#sMessagePrompt .sC span").one("click",function(){
  //window.sPromptValue = $("#sMessagePrompt .sS input.txt").val();
  sMessageClose("Prompt");
 });
  $("#sMessagePrompt div div input").one("click",function(){
  //window.sPromptValue = $("#sMessagePrompt .sS input.txt").val();
  sMessageClose("Prompt");
 });
 if( typeof(callback)=="function" ){
  $("#sMessagePrompt .sC span a.ok").one("click",callback);

  $("#sMessagePrompt  input.no").one("click",function(){
   $("#sMessagePrompt .sC span a.ok").unbind("click");
   window.sPromptValue = "";
  });
 }
 $("#sMessagePrompt .sS input.txt").keyup(function(event){
  window.sPromptValue = $(this).val();
  if(event.keyCode==13){
   $("#sMessagePrompt .sC span input.ok").trigger("click");
  }
 });
}


    // this['http'] = {
    //   login: login,

    // }

  // }
  // (this))