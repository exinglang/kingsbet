document.write("<script type='text/javascript' src='commonjs/MD5.js'></script>");
// !(function() {
  function ajax(data, cb) {

    return $.ajax({
      // var name = data.name,
      url: 'http://localhost:8080/'+data.name,
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
     "name":"adminlogin",
     reqbody: {

      "pwd": $.md5(userId + password),
      "userId": userId
    }
    
 // reqbody: {
      //   "mobile": mobile,
      //   "pwd": $.md5(mobile + pwd),
      // "userId": userId,
      // "pwd": password
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
        "name":"register",
         reqbody: {
          "pwd": $.md5(userId + password),
          "userId": userId
        }

      // reqbody: {
      //   "userId": userId,
      //   "pwd": $.md5(userId + password),
      // }
    })
    }



    // this['http'] = {
    //   login: login,

    // }

  // }
  // (this))