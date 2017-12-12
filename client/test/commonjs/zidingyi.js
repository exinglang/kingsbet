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







    // this['http'] = {
    //   login: login,

    // }

  // }
  // (this))