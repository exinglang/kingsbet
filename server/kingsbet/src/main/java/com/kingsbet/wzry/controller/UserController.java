package com.kingsbet.wzry.controller;

import com.kingsbet.wzry.dao.TeamDao;
import com.kingsbet.wzry.entity.*;
import com.kingsbet.wzry.http.HttpRequestUtil;
import com.kingsbet.wzry.http.RequestManager;
import com.kingsbet.wzry.util.Util;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kingsbet.wzry.ApplicationContext;
import com.kingsbet.wzry.Constants;
import com.kingsbet.wzry.dao.UserDao;
import com.kingsbet.wzry.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 用户管理
 *
 * @author zjn
 */
@Controller
public class UserController extends BaseController {
    @Autowired
    private UserDao dao;
    @Autowired
    private TeamDao teamDao;
//    @RequestMapping("/adminlogin")
//    @ResponseBody
//    public ResponseJsonRoot requestJson(@RequestBody RequestJsonRoot<User> jsonRoot) {
////        Gson gson = new Gson();
////        JSONObject test=new JSONObject("{name: \"adminlogin\", reqbody: {pwd: \"18ad456df3e3ff95ce2b451bc7609350\", userId: \"12314124\"}}");
////        TypeToken<RequestJsonRoot<User>> userType = new TypeToken<RequestJsonRoot<User>>() {
////        };
////        RequestJsonRoot<User> testResult = gson.fromJson(test.toString(), userType.getType());
//
//////////////
//        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
//        User user = jsonRoot.getReqsbody();
////        try {
////
//////          userDao.insertById(itemsCustom.getId());
////            userDao.checkPwd(user.getUserId(), user.getPwd());
//////            String sessionId = SessionUtil.generateSessionId();
//////            ApplicationContext.getApplicationContext().addSession(sessionId);
//////            ApplicationContext.getApplicationContext().getSession(sessionId).addAttribute(it);
////        } catch (Exception e) {
////            result.setRetcodeAndMsg(-2, "用户名或密码错误");
////            e.printStackTrace();
////        }
//
//
//        try {
//            int count = dao.checkUserId(user.getUserId());
//            if (count == 0) {
//                result.setRetcodeAndMsg(Constants.CODE_FAIL, "用户名不存在");
//                return result;
//            }
//
//            int checkPwdD = dao.checkPwd(user.getUserId(), user.getPwd());
//            if (checkPwdD != 1) {
//                result.setRetcodeAndMsg(Constants.CODE_FAIL, "密码不正确");
//                return result;
//            }
//            //生成SESSION
//            String sessionId = SessionUtil.generateSessionId();
//            ApplicationContext.getApplicationContext().addSession(sessionId);
//            ApplicationContext.getApplicationContext().getSession(sessionId).setUser(user);
//            ResponseLogin responseLogin= new ResponseLogin(user.getUserId(),sessionId);
//            result.setRepbody(responseLogin);
////            userDao.register(user.getUserId(), user.getPwd());
////            userDao.queryById(user.getUserId());
////            selectUser = userDao.queryById(itemsCustom.getUserId());
////            String sessionId = SessionUtil.generateSessionId();
////            ApplicationContext.getApplicationContext().addSession(sessionId);
////            ApplicationContext.getApplicationContext().getSession(sessionId).addAttribute(it);
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
//        }
//        return result; //由于@ResponseBody注解，将itemsCustom转成json格式返回
//    }

//    @RequestMapping("/register")
//    @ResponseBody
//    public ResponseJsonRoot register(@RequestBody RequestJsonRoot<User> jsonRoot) {
////        Gson gson = new Gson();
////        TypeToken userType = new TypeToken<JsonRoot<UserEntity>>() {
////        };
////        JsonRoot<UserEntity> userResult = gson.fromJson(response.toString(), userType.getType());
////        myApplication.getUserEntity().setSessionStr(userResult.getJsonRootBodyContent().getSessionStr());
//        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
//        User user = jsonRoot.getReqsbody();
//        try {
//            int count = dao.checkUserId(user.getUserId());
//            if (count != 0) {
//                result.setRetcodeAndMsg(Constants.CODE_FAIL, "用户名已注册");
//                return result;
//            }
//            dao.register(user.getUserId(), user.getPwd());
////            userDao.queryById(user.getUserId());
////            selectUser = userDao.queryById(itemsCustom.getUserId());
////            String sessionId = SessionUtil.generateSessionId();
////            ApplicationContext.getApplicationContext().addSession(sessionId);
////            ApplicationContext.getApplicationContext().getSession(sessionId).addAttribute(it);
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
//        }
//
//        return result; //由于@ResponseBody注解，将itemsCustom转成json格式返回
//    }


    @RequestMapping("/userregister")
    @ResponseBody
    public ResponseJsonRoot userregister(@RequestBody RequestJsonRoot<User> jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        User user = jsonRoot.getReqsbody();
        try {
            int count = dao.checkPhone(user.getPhone());
            if (count != 0) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, "手机号已注册");
                return result;
            }
            dao.register(user.getPhone(), user.getPwd(), System.currentTimeMillis() + "");
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return result; //由于@ResponseBody注解，将itemsCustom转成json格式返回
    }


    @RequestMapping("/charge")
    @ResponseBody
    public ResponseJsonRoot charge(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        MJsonParse parse = new MJsonParse(jsonRoot);
        try {
            dao.charge(parse.getInt("userid"), parse.getInt("amount"), System.currentTimeMillis() + "");

        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return result; //由于@ResponseBody注解，将itemsCustom转成json格式返回
    }

    @RequestMapping("/diamondtobalance")
    @ResponseBody
    public ResponseJsonRoot diamondtobalance(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        MJsonParse parse = new MJsonParse(jsonRoot);
        try {
            int yue = dao.getdiamond(parse.getInt("userid"));
            if (yue == 0) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, "钻石余额为0,无法兑换");
                return result;
            }
            ;
            dao.diamondtobalance(parse.getInt("userid"), System.currentTimeMillis() + "");

        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result;
    }

    @RequestMapping("/userlogin")
    @ResponseBody
    public ResponseJsonRoot userlogin(@RequestBody RequestJsonRoot<User> jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        User user = jsonRoot.getReqsbody();
        try {
            int count = dao.checkPhone(user.getPhone());
            if (count == 0) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, "手机号不存在");
                return result;
            }
            int checkPwdD = dao.checkPwd(user.getPhone(), user.getPwd());
            if (checkPwdD != 1) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, "密码不正确");
                return result;
            }
            //生成SESSION
            String sessionId = SessionUtil.generateSessionId();
            ApplicationContext.getApplicationContext().addSession(sessionId);
            ApplicationContext.getApplicationContext().getSession(sessionId).setUser(user);
            ResponseLogin responseLogin = new ResponseLogin(dao.getUserIdByPhone(user.getPhone()) + "", sessionId);
            result.setRepbody(responseLogin);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result; //由于@ResponseBody注解，将itemsCustom转成json格式返回
    }


    @RequestMapping("/userinfo")
    @ResponseBody
    public ResponseJsonRoot userinfo(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        MJsonParse parse = new MJsonParse(jsonRoot);
        try {
            int userid = parse.getInt("userid");
            User user = dao.userInfo(userid);
            result.setRepbody(user);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result; //由于@ResponseBody注解，将itemsCustom转成json格式返回
    }


    @RequestMapping("/orderhistory")
    @ResponseBody
    public ResponseJsonRoot orderhistory(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        MJsonParse parse = new MJsonParse(jsonRoot);
        try {
            int userid = parse.getInt("userid");
            List<OrderHistory> orderhistorylist = dao.orderhistory(userid, parse.getInt("pageindex"), parse.getInt("pagesize"));
            for (OrderHistory orderHistory : orderhistorylist) {
                long remaintime = Long.valueOf(orderHistory.getTime()) - System.currentTimeMillis();
                orderHistory.setTime(remaintime + "");
                if (orderHistory.getBetwin() == null) {
                    orderHistory.setBetwin("待结算");
                }
            }

            ResponseList typeAndList = new ResponseList();
            typeAndList.setList(orderhistorylist);
            result.setRepbody(typeAndList);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result; //由于@ResponseBody注解，将itemsCustom转成json格式返回
    }

    @RequestMapping("/exchange")
    @ResponseBody
    public ResponseJsonRoot exchange(@RequestBody RequestJsonRoot<Exchange> jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");

//        MJsonParse parse = new MJsonParse(jsonRoot);
        Exchange entity = jsonRoot.getReqsbody();
        logger.info("exchange:" + entity.toString());
        try {
            //先验证客户端发来的总金额是否正确
            int commodityId = entity.getCommodityId();
            int number = entity.getNumber();
            int totalprice = entity.getPrice();
            Commodity commodity = teamDao.getCommodityById(commodityId);
            if (totalprice != commodity.getPrice() * number) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_EXCHANGE);
                ;
                return result;
            }
            entity.setReqdate(System.currentTimeMillis() + "");
            entity.setCommoditytype(commodity.getType());
            entity.setStatus(1);
            //生成聚合需要的订单号
            String uuid = UUID.randomUUID().toString();
            entity.setOrderid(uuid.replaceAll("-", ""));
            User user = dao.userInfo(entity.getUserid());
            //检测账户余额是否不足
            if (user.getBalance() < entity.getPrice()) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_NOT_BALANCE);

                return result;
            }

            if (entity.getExtype() == Constants.HUA_FEI) {
                return huaFei(entity, commodity);

            } else if (entity.getExtype() == Constants.KA) {
                return liPingKa(entity, commodity);
            }


        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result; //由于@ResponseBody注解，将itemsCustom转成json格式返回
    }

    private ResponseJsonRoot liPingKa(Exchange entity, Commodity commodity) {
        RequestManager requestManager = new RequestManager();
        //聚合发货
        addRecordAndBalance(entity);
        JvHeEntity jvHeEntity = new Gson().fromJson(requestManager.buyCard(entity, commodity), JvHeEntity.class);
        entity.setStatus(jvHeEntity.getErrorCode());
        teamDao.updateTExchangeStatus(entity);
        if (jvHeEntity.getErrorCode() == Constants.CODE_SUCCESS) {
            return new ResponseJsonRoot("", Constants.CODE_SUCCESS, "");
        } else {
            dao.saveException(Util.getCurrentTimeYYYYMMDDHHMMSS(), "用户扣除金币后,充值失败:" + entity.toString() + jvHeEntity.toString());
            if (jvHeEntity.getErrorCode() == 208517) {
                return new ResponseJsonRoot("", jvHeEntity.getErrorCode(), "服务器余额不足,请联系管理员");
            } else {
                return new ResponseJsonRoot("", jvHeEntity.getErrorCode(), jvHeEntity.getReason());
            }
        }


    }

    //添加用户兑换记录
    private void addRecordAndBalance(Exchange entity) {

        teamDao.insertExchange(entity);
        dao.balanceexchange(entity.getUserid(), -entity.getPrice(), System.currentTimeMillis() + "");

    }

    private ResponseJsonRoot huaFei(Exchange entity, Commodity commodity) {
//        return new ResponseJsonRoot("", Constants.CODE_SUCCESS,"");
        RequestManager requestManager = new RequestManager();
        //聚合检测手机号码是否能充值
        JvHeEntity jvHeEntity = new Gson().fromJson(requestManager.checkPhoneSocket(entity, commodity), JvHeEntity.class);
        if (jvHeEntity.getErrorCode() == Constants.CODE_SUCCESS) {
            //检测通过,现在进行充值

            addRecordAndBalance(entity);
            jvHeEntity = new Gson().fromJson(requestManager.phoneRecharge(entity, commodity), JvHeEntity.class);
            entity.setStatus(jvHeEntity.getErrorCode());
            teamDao.updateTExchangeStatus(entity);
            if (jvHeEntity.getErrorCode() == Constants.CODE_SUCCESS) {
                return new ResponseJsonRoot("", Constants.CODE_SUCCESS, "");
            } else {
                dao.saveException(Util.getCurrentTimeYYYYMMDDHHMMSS(), "用户扣除金币后,充值失败:" + entity.toString() + jvHeEntity.toString());
                if (jvHeEntity.getErrorCode() == 208517) {
                    return new ResponseJsonRoot("", jvHeEntity.getErrorCode(), "服务器余额不足,请联系管理员");
                } else {
                    return new ResponseJsonRoot("", jvHeEntity.getErrorCode(), jvHeEntity.getReason());
                }
            }
        } else {
            return new ResponseJsonRoot("", jvHeEntity.getErrorCode(), jvHeEntity.getReason());
        }


    }


}

