package com.kingsbet.wzry.controller;

import com.kingsbet.wzry.Constants;
import com.kingsbet.wzry.dao.ScheduleDao;
import com.kingsbet.wzry.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


          dao.insertSchedule(entity,entity.getTitle1(), entity.getTitle2(), entity.getTime(),Constants.SCHEDULE_STATE_WEI_JIE_SUAN);
            dao.insertScheduleDetail(entity.getId(),entity.getTeamList());
            dao.insertSchedulePankou(entity.getId(),entity.getPankou());
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
            List<Schedule> list = dao.getScheduleList(parse.getInt("state"), parse.getInt("pageIndex"),parse.getInt("pageSize"));

            ResponseList typeAndList = new ResponseList();
            typeAndList.setList(list);
            returnResult.setRepbody(typeAndList);


        } catch (Exception e) {
            e.printStackTrace();
            returnResult.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return returnResult;
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


    @RequestMapping("/updateschedulestate")
    @ResponseBody
    public ResponseJsonRoot updateScheduleState(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        try {
            MJsonParse parse = new MJsonParse(jsonRoot);

            dao.updateScheduleState(parse.getInt("id"), parse.getInt("state"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result;
    }


}

