
// !(function() {
  function ajax(data, cb) {
    return $.ajax({
      url: 'http://localhost:8080/adminlogin',
      contentType: 'application/json;charset=utf-8',
      type: 'POST',

      data: JSON.stringify(data),
      error: function(jqXHR, textStatus, errorThrown) {
        console.log(errorThrown);
      }
    })
  }

  function login(userId, password) {
    return ajax({
      "userId": userId,
      "pwd": password
      // "name": "register",
      // "ctype": "weChat",
      // reqbody: {
      //   "mobile": mobile,
      //   "pwd": $.md5(mobile + pwd),
      //   "verifycode": code,
      //   "invitationcode": invitationcode,
      //   openid: $.fn.cookie('wxOpenId')
      // }
    })}

 function register(userId, password) {
    return ajax({
      "userId": userId,
      "pwd": password
      // "name": "register",
      // "ctype": "weChat",
      // reqbody: {
      //   "mobile": mobile,
      //   "pwd": $.md5(mobile + pwd),
      //   "verifycode": code,
      //   "invitationcode": invitationcode,
      //   openid: $.fn.cookie('wxOpenId')
      // }
    })}

    // this['http'] = {
    //   login: login,

    // }

  // }
  // (this))