// /*自制对话框begin
// sAlert(s1,t1,callback)
// sConfirm(s1,t1,callback)
// sPrompt(s1,t1,v1,callback)
// */
// $(function(){
//  //生成元素
//  // if( !document.getElementById("sMessage") ){
  
//   window.sMessageOpen = function(s){
//    if(s){$("#sMessage,#sMessage"+s).fadeIn(233,"swing");
//     var w_h = $(window).height();
//     if(w_h>$("#sMessage"+s).height()){
//      $("#sMessage"+s).css("marginTop",(w_h*0.3)-($("#sMessage"+s).height()*0.3));
//     }
//    }
//   }
//   window.sMessageClose = function(s){
//    if(s){$("#sMessage,#sMessage"+s).hide();}
//   }
//  // }
// });

// function addSchedule(){
//   var callback =function(){
//     alert(123);
//   }
// sPrompt("s1","t1","v1",callback);




// }



// //提示� �� 框
// function sPrompt(s1,t1,v1,callback){
//  window.sPromptValue = "";
//  sMessageOpen("Prompt");
//  if(!t1){
//   $("#sMessagePrompt .sT").html("");
//  }else{
//   $("#sMessagePrompt .sT").html(t1);
//  }
//  if(!s1){
//   $("#sMessagePrompt .sS p").html("");
//  }else{
//   $("#sMessagePrompt .sS p").html(s1);
//  }
//  if(!v1){
//   $("#sMessagePrompt .sS input.txt").val("");
//  }else{
//   $("#sMessagePrompt .sS input.txt").val(v1);
//  }
//  $("#sMessagePrompt .sC span").one("click",function(){
//   //window.sPromptValue = $("#sMessagePrompt .sS input.txt").val();
//   sMessageClose("Prompt");
//  });
//  if( typeof(callback)=="function" ){
//   $("#sMessagePrompt .sC span input.ok").one("click",callback);
//   $("#sMessagePrompt .sC span input.no").one("click",function(){
//    $("#sMessagePrompt .sC span input.ok").unbind("click");
//    window.sPromptValue = "";
//   });
//  }
//  $("#sMessagePrompt .sS input.txt").keyup(function(event){
//   window.sPromptValue = $(this).val();
//   if(event.keyCode==13){
//    $("#sMessagePrompt .sC span input.ok").trigger("click");
//   }
//  });
// }
// //改写原生js的alert为sAlert
// // window.alert = function(s){sAlert(s);}
// /*自制对话框end*/