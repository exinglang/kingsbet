package com.kingsbet.wzry.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.kingsbet.wzry.Constants;
import com.kingsbet.wzry.dao.ScheduleDao;
import com.kingsbet.wzry.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        Schedule schedule = jsonRoot.getReqsbody();
        try {
            dao.insertSchedule(schedule, Constants.SCHEDULE_STATUS_DAI_FA_BU);
            dao.insertScheduleTeam(schedule.getId(), schedule.getTeamList());
            List<String> intList = schedule.getPankou();
            ArrayList<Pankou> pankouList = new ArrayList<>();
            for (int i = 0; i < intList.size(); i++) {
                Pankou pankou = new Pankou();
                pankou.setName(intList.get(i) + "");
                pankou.setScheduleId(schedule.getId());
                pankouList.add(pankou);
            }
            dao.insertSchedulePankou(pankouList);
            dao.insertSchedulePankouDetail(pankouList, schedule.getTeamList());
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result;
    }


    @RequestMapping("/updateschedule")
    @ResponseBody
    public ResponseJsonRoot updateSchedule(@RequestBody RequestJsonRoot<Schedule> jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        Schedule schedule = jsonRoot.getReqsbody();
        try {
            dao.deleteSchedule(schedule.getId());
            addSchedule(jsonRoot);

        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result;
    }

    @RequestMapping("/getschedule")
    @ResponseBody
    public ResponseJsonRoot getSchedule(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        MJsonParse parse = new MJsonParse(jsonRoot);
        try {
            int scheduleId = parse.getInt("scheduleid");
            List<Pankou> pankouList = dao.getSchedulePankou(scheduleId);
            List<Team> teamList = dao.getScheduleTeam(Integer.valueOf(scheduleId));
            ResponseSchedule responseSchedule = new ResponseSchedule();
            responseSchedule.setPankoulist(pankouList);
            responseSchedule.setTeamlist(teamList);

            Schedule schedule = dao.getSchedule(scheduleId);
            responseSchedule.setScheduleid(schedule.getId() + "");
            responseSchedule.setTime(schedule.getTime());
            responseSchedule.setTitle1(schedule.getTitle1());
            responseSchedule.setTitle2(schedule.getTitle2());
            responseSchedule.setStatus(schedule.getStatus() + "");

            result.setRepbody(responseSchedule);

        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result;
    }


    @RequestMapping("/schedulelist")
    @ResponseBody
    public ResponseJsonRoot getScheduleList(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot returnResult = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        try {

            MJsonParse parse = new MJsonParse(jsonRoot);
            int requestStatus=parse.getInt("status");
            List<Schedule> list=getNeedStatusList(requestStatus,parse);

            for (Schedule schedule : list) {

                long remaintime=Long.valueOf(schedule.getTime()) - System.currentTimeMillis();


                //如果请求的是 "已发布",那判断当前时间是否超过设置的比赛时间,如果超过了,将比赛设置为"待结算"
                    if(remaintime<=0&&requestStatus==Constants.SCHEDULE_STATUS_YI_FA_BU){
                        updateScheduleStatusFunction(schedule.getId(),Constants.SCHEDULE_STATUS_DAI_JIE_SUAN);
//                        list.remove(schedule);
                    }

            }
            list =getNeedStatusList(requestStatus,parse);
            for (Schedule schedule : list) {
                long remaintime=Long.valueOf(schedule.getTime()) - System.currentTimeMillis();

                schedule.setRemainTime(String.valueOf(remaintime));

            }

            ResponseList typeAndList = new ResponseList();
            typeAndList.setList(list);
            returnResult.setRepbody(typeAndList);


        } catch (Exception e) {
            e.printStackTrace();
            returnResult.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return returnResult;
    }

    private List<Schedule> getNeedStatusList(int requestStatus,MJsonParse parse) {
        List<Schedule> list;
        if(requestStatus!=5) {

            list = dao.getScheduleList(requestStatus, parse.getInt("pageIndex"), parse.getInt("pageSize"));
        }else{
            list = dao.getScheduleList(2, parse.getInt("pageIndex"), parse.getInt("pageSize"));
            list.addAll(dao.getScheduleList(3, parse.getInt("pageIndex"), parse.getInt("pageSize")));
            list.addAll(dao.getScheduleList(4, parse.getInt("pageIndex"), parse.getInt("pageSize")));

        }
        return list;

    }

    @RequestMapping("/deleteschedule")
    @ResponseBody
    public ResponseJsonRoot deleteSchedule(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        try {
            MJsonParse parse = new MJsonParse(jsonRoot);
            dao.deleteSchedule(parse.getInt("id"));

        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return result;
    }


    @RequestMapping("/updateschedulestatus")
    @ResponseBody
    public ResponseJsonRoot updateScheduleStatus(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        try {
            MJsonParse parse = new MJsonParse(jsonRoot);
            int requestStatus = parse.getInt("status");
            switch (requestStatus) {
                case Constants.SCHEDULE_STATUS_DAI_JIE_SUAN:
                    updateScheduleStatusFunction(parse.getInt("id"), Constants.SCHEDULE_STATUS_DAI_JIE_SUAN);
                    break;
                case Constants.SCHEDULE_STATUS_YI_FA_BU:
                    updateScheduleStatusFunction(parse.getInt("id"), Constants.SCHEDULE_STATUS_YI_FA_BU);
                    break;
                case Constants.SCHEDULE_STATUS_YI_QU_XIAO:
                    updateScheduleStatusFunction(parse.getInt("id"), Constants.SCHEDULE_STATUS_YI_QU_XIAO);
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result;
    }

    public void updateScheduleStatusFunction(int id ,int status) {
        dao.updateScheduleStatus(id, status);
    }


}

