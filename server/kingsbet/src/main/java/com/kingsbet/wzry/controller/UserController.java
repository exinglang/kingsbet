package com.kingsbet.wzry.controller;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kingsbet.wzry.ApplicationContext;
import com.kingsbet.wzry.Constants;
import com.kingsbet.wzry.dao.UserDao;
import com.kingsbet.wzry.entity.RequestJsonRoot;
import com.kingsbet.wzry.entity.ResponseJsonRoot;
import com.kingsbet.wzry.entity.ResponseLogin;
import com.kingsbet.wzry.entity.User;
import com.kingsbet.wzry.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户管理
 *
 * @author zjn
 */
@Controller
public class UserController extends BaseController {
    @Autowired
    private UserDao dao;

    @RequestMapping("/adminlogin")
    @ResponseBody
    public ResponseJsonRoot requestJson(@RequestBody RequestJsonRoot<User> jsonRoot) {
//        Gson gson = new Gson();
//        JSONObject test=new JSONObject("{name: \"adminlogin\", reqbody: {pwd: \"18ad456df3e3ff95ce2b451bc7609350\", userId: \"12314124\"}}");
//        TypeToken<RequestJsonRoot<User>> userType = new TypeToken<RequestJsonRoot<User>>() {
//        };
//        RequestJsonRoot<User> testResult = gson.fromJson(test.toString(), userType.getType());

////////////
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        User user = jsonRoot.getReqsbody();
//        try {
//
////          userDao.insertById(itemsCustom.getId());
//            userDao.checkPwd(user.getUserId(), user.getPwd());
////            String sessionId = SessionUtil.generateSessionId();
////            ApplicationContext.getApplicationContext().addSession(sessionId);
////            ApplicationContext.getApplicationContext().getSession(sessionId).addAttribute(it);
//        } catch (Exception e) {
//            result.setRetcodeAndMsg(-2, "用户名或密码错误");
//            e.printStackTrace();
//        }


        try {
            int count = dao.checkUserId(user.getUserId());
            if (count == 0) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, "用户名不存在");
                return result;
            }

            int checkPwdD = dao.checkPwd(user.getUserId(), user.getPwd());
            if (checkPwdD != 1) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, "密码不正确");
                return result;
            }
            //生成SESSION
            String sessionId = SessionUtil.generateSessionId();
            ApplicationContext.getApplicationContext().addSession(sessionId);
            ApplicationContext.getApplicationContext().getSession(sessionId).setUser(user);
            ResponseLogin responseLogin= new ResponseLogin(user.getUserId(),sessionId);
            result.setRepbody(responseLogin);
//            userDao.register(user.getUserId(), user.getPwd());
//            userDao.queryById(user.getUserId());
//            selectUser = userDao.queryById(itemsCustom.getUserId());
//            String sessionId = SessionUtil.generateSessionId();
//            ApplicationContext.getApplicationContext().addSession(sessionId);
//            ApplicationContext.getApplicationContext().getSession(sessionId).addAttribute(it);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result; //由于@ResponseBody注解，将itemsCustom转成json格式返回
    }

    @RequestMapping("/register")
    @ResponseBody
    public ResponseJsonRoot register(@RequestBody RequestJsonRoot<User> jsonRoot) {
//        Gson gson = new Gson();
//        TypeToken userType = new TypeToken<JsonRoot<UserEntity>>() {
//        };
//        JsonRoot<UserEntity> userResult = gson.fromJson(response.toString(), userType.getType());
//        myApplication.getUserEntity().setSessionStr(userResult.getJsonRootBodyContent().getSessionStr());
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        User user = jsonRoot.getReqsbody();
        try {
            int count = dao.checkUserId(user.getUserId());
            if (count != 0) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, "用户名已注册");
                return result;
            }
            dao.register(user.getUserId(), user.getPwd());
//            userDao.queryById(user.getUserId());
//            selectUser = userDao.queryById(itemsCustom.getUserId());
//            String sessionId = SessionUtil.generateSessionId();
//            ApplicationContext.getApplicationContext().addSession(sessionId);
//            ApplicationContext.getApplicationContext().getSession(sessionId).addAttribute(it);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return result; //由于@ResponseBody注解，将itemsCustom转成json格式返回
    }


}

