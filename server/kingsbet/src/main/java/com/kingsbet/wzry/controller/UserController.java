package com.kingsbet.wzry.controller;

import com.kingsbet.wzry.ApplicationContext;
import com.kingsbet.wzry.dao.UserDao;
import com.kingsbet.wzry.entity.JsonRoot;
import com.kingsbet.wzry.entity.User;
import com.kingsbet.wzry.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.logging.Logger;

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
    public User requestJson(@RequestBody User itemsCustom) {
        User selectUser = null;
        try {
//          userDao.insertById(itemsCustom.getId());

            selectUser = userDao.queryById(itemsCustom.getUserId());
//            String sessionId = SessionUtil.generateSessionId();
//            ApplicationContext.getApplicationContext().addSession(sessionId);
//            ApplicationContext.getApplicationContext().getSession(sessionId).addAttribute(it);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return selectUser; //由于@ResponseBody注解，将itemsCustom转成json格式返回
    }

    @RequestMapping("/register")
    @ResponseBody
    public User register(@RequestBody JsonRoot jsonRoot) {
        
        User selectUser = null;
        try {
//          userDao.insertById(itemsCustom.getId());

//            selectUser = userDao.queryById(itemsCustom.getUserId());
//            String sessionId = SessionUtil.generateSessionId();
//            ApplicationContext.getApplicationContext().addSession(sessionId);
//            ApplicationContext.getApplicationContext().getSession(sessionId).addAttribute(it);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return selectUser; //由于@ResponseBody注解，将itemsCustom转成json格式返回
    }


//    @RequestMapping("/save")
//    public @ResponseBody
//    User requestJson(@RequestBody User itemsCustom) {
//        return itemsCustom; //由于@ResponseBody注解，将itemsCustom转成json格式返回
//    }
}

