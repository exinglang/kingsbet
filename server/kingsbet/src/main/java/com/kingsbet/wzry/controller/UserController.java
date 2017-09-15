package com.kingsbet.wzry.controller;

import com.kingsbet.wzry.Constants;
import com.kingsbet.wzry.dao.UserDao;
import com.kingsbet.wzry.entity.RequestJsonRoot;
import com.kingsbet.wzry.entity.ResponseJsonRoot;
import com.kingsbet.wzry.entity.User;
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
    //    private static Logger logger = Logger.getLogger(UserController.class);    @Autowired
    @Autowired
    private UserDao userDao;

    @RequestMapping("/adminlogin")
    @ResponseBody
    public ResponseJsonRoot requestJson(@RequestBody RequestJsonRoot<User> jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        User user = jsonRoot.getReqbody();
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
            int count = userDao.checkUserId(user.getUserId());
            if (count == 0) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, "用户名不存在");
                return result;
            }

            int checkPwdD = userDao.checkPwd(user.getUserId(),user.getPwd());
            if (checkPwdD != 1) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, "密码不正确");
                return result;
            }
//            userDao.register(user.getUserId(), user.getPwd());
//            userDao.queryById(user.getUserId());
//            selectUser = userDao.queryById(itemsCustom.getUserId());
//            String sessionId = SessionUtil.generateSessionId();
//            ApplicationContext.getApplicationContext().addSession(sessionId);
//            ApplicationContext.getApplicationContext().getSession(sessionId).addAttribute(it);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL,  Constants.MSG_FAIL_UNKNOW);
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
        User user = jsonRoot.getReqbody();

        try {
            int count = userDao.checkUserId(user.getUserId());
            if (count != 0) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, "用户名已注册");
                return result;
            }
            userDao.register(user.getUserId(), user.getPwd());
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


//    @RequestMapping("/save")
//    public @ResponseBody
//    User requestJson(@RequestBody User itemsCustom) {
//        return itemsCustom; //由于@ResponseBody注解，将itemsCustom转成json格式返回
//    }
}

