package com.kingsbet.wzry.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.kingsbet.wzry.Constants;
import com.kingsbet.wzry.dao.ScheduleDao;
import com.kingsbet.wzry.dao.TeamDao;
import com.kingsbet.wzry.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 *
 * @author zjn
 */
@Controller
public class ScheduleController extends BaseController {
    @Autowired
    private ScheduleDao dao;

    @RequestMapping("/addschedule")
    @ResponseBody
    public ResponseJsonRoot addSchedule(@RequestBody RequestJsonRoot<Schedule> jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        Schedule entity = jsonRoot.getReqsbody();
        try {


          dao.insertSchedule(entity,entity.getTitle1(), entity.getTitle2(), String.valueOf(System.currentTimeMillis()),Constants.SCHEDULE_STATE_WEI_JIE_SUAN);
            dao.insertScheduleDetail(entity.getInsertKey(),entity.getTeamList());

        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return result;
    }

}

