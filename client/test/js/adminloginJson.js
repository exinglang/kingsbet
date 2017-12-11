    function getRegister(userId, password){
      return {
        "name":"register",
        reqbody: {
          "pwd": $.md5(userId + password),
          "userId": userId
        }}
      };