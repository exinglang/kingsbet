var 
userId,password,
imgId,
flag = true,
imgFlag = true,
second = 120,
time = 0,

timerId = null;
document.write("<script type='text/javascript' src='commonjs/httpclient.js'></script>");


function requestLogin(){
  userId = $('#userId').val().trim()
  password = $('#password').val().trim()

  login(userId, password).done(function(data) {
   alert(data.strnickname);
 }).fail(function() {
   alert("fail");
 })
}


function requestRegister(){
  userId = $('#userId').val().trim()
  password = $('#password').val().trim()

  register(userId, password).done(function(data) {
    // loading.hide(function() {
      var retcode = data.retcode;
      if (retcode === 0) {
        alert("成功");
        // weui.toast('注册成功', {
        //   duration: 2000,
        //   callback: function() {
        //     window.location.replace('/index')
        //   }
        // })
      } else {
        alert("失败");
      }
    // })
  }).fail(function() {
    // loading.hide(function() {
    //   flag = true
    //   showError(data.msg || '服务器错误，请稍后重试')
    // })
     alert("服务器失败");
  })



}

// function getImgCode() {
//   if (imgFlag) {
//     imgFlag = false
//     http.getCaptcha().done(function(res) {
//       if (res.retcode === 0) {
//         $('#imgCode').attr('src', 'data:image/png;base64,' + res.respbody.captcha)
//         imgId = res.respbody.captchaId
//       } else {
//         $('#imgCode').attr('src', '')
//         imgId = null
//       }
//       imgFlag = true;
//     }).fail(function() {
//       imgFlag = true
//     })
//   }
// }
// $(function() {
//   getImgCode()
//   $('#imgCode').on('click', function() {
//     getImgCode()
//   })
//   $('#first').on('click', function() {
//     mobile = $('#mobile').val().trim()
//     var code = $('#vcode').val().trim()
//     if (mobile === '') {
//       showError('请输入手机号')
//       return
//     } else if (!validator.validateMobile(mobile)) {
//       showError('请输入正确的手机号')
//       return;
//     }
//     if (code === '') {
//       showError('请输入验证码')
//       return;
//     }
//     http.retrievePwdFirst(mobile, imgId, code).done(function(data) {
//       if (data.retcode === 0) {
//         $('#firstStep').hide()
//         $('.second-tips').text('请输入' + mobile.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2') + '收到的短信验证码')
//         $('#secondStep').show()
//       } else {
//         showError(data.msg || '服务器错误，请稍后重试')
//       }
//     })
//   })
//   $('#second').on('click', function() {
//     var code = $('#smscode').val().trim()
//     if (code === '') {
//       showError('请输入短信验证码')
//       return
//     }
//     http.retrievePwdSecond(mobile, code).done(function(data) {
//       if (data.retcode === 0) {
//         $('#secondStep').hide()
//         $('#lastStep').show()
//       } else {
//         showError(data.msg || '服务器错误，请稍后重试')
//       }
//     })
//   })

//   $('#last').on('click', function() {
//     var pwd = $('#pwd').val().trim()
//     if (pwd === '') {
//       showError('请输入密码')
//       return;
//     }
//     http.retrievePwdLast(mobile, pwd).done(function(data) {
//       if (data.retcode === 0) {
//         weui.toast('找回成功', {
//           duration: 2000,
//           callback: function() {
//             window.location.href = '/index'
//           }
//         })
//       } else {
//         showError(data.msg || '服务器错误，请稍后重试')
//       }
//     })
//   })


//   $('#getSmsCode').on('click', function() {
//     var $mobileEl = $('#mobile'),
//     mobile = $mobileEl.val().trim(),
//     $this = $(this)
//     if ($this.hasClass('btn-disabled')) {
//       return
//     }
//     $this.addClass('btn-disabled')
//     http.sendSMS(mobile).done(function(data) {
//       if (data.retcode !== 0) {
//         if (timerId) {
//           clearTimeout(timerId)
//         }
//         weui.alert(data.msg || '系统错误，请稍后再试！')
//         $this.removeClass('btn-disabled').text('获取验证码')
//       } else {
//         time = second;
//         timer()
//       }
//     }).fail(function() {
//       $this.removeClass('btn-disabled')
//     })
//   })
// })

