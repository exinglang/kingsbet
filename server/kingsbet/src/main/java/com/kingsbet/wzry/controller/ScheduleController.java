package com.kingsbet.wzry.controller;


import com.kingsbet.wzry.Constants;
import com.kingsbet.wzry.dao.ScheduleDao;
import com.kingsbet.wzry.entity.*;
import com.kingsbet.wzry.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
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
            dao.insertScheduleTeam(schedule.getId(), schedule.getTeamIdList());
            List<Integer> intList = schedule.getPankouIdList();
            ArrayList<Pankou> pankouList = new ArrayList<>();
            for (int i = 0; i < intList.size(); i++) {
                Pankou pankou = new Pankou();
//                pankou.setName(intList.get(i) + "");
                pankou.setScheduleId(schedule.getId());
                pankou.setType(intList.get(i));
                pankouList.add(pankou);
            }
            dao.insertSchedulePankou(pankouList);
            dao.insertSchedulePankouDetail(pankouList, schedule.getTeamIdList());
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
            int scheduleId = parse.getInt("id");
            List<Pankou> pankouList = dao.getSchedulePankou(scheduleId);
            List<Team> teamList = dao.getScheduleTeam(scheduleId);
            Schedule schedule = dao.getSchedule(scheduleId);
            schedule.setPankouList(pankouList);
            schedule.setTeamList(teamList);
            result.setRepbody(schedule);

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
            int requestStatus = parse.getInt("status");
            List<Schedule> list = getNeedStatusList(requestStatus, parse);
            //先把过期比赛设置为待结算
            for (Schedule schedule : list) {
                long remaintime = Long.valueOf(schedule.getTime()) - System.currentTimeMillis();
                //如果请求的是 "已发布",那判断当前时间是否超过设置的比赛时间,如果超过了,将比赛设置为"待结算"
                if (remaintime <= 0 && requestStatus == Constants.SCHEDULE_STATUS_YI_FA_BU) {
                    updateScheduleStatusFunction(Integer.valueOf(schedule.getId()), Constants.SCHEDULE_STATUS_DAI_JIE_SUAN);
//                        list.remove(schedule);
                }

            }
            //重新获取已发布的比赛
            list = getNeedStatusList(requestStatus, parse);
            //计算剩余时间
            for (Schedule schedule : list) {
                long remaintime = Long.valueOf(schedule.getTime()) - System.currentTimeMillis();
                schedule.setRemainTime(String.valueOf(remaintime));
                List<Pankou> pankouList = dao.getSchedulePankou(schedule.getId());
                schedule.setPankouList(pankouList);
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

    private List<Schedule> getNeedStatusList(int requestStatus, MJsonParse parse) {
        List<Schedule> list;
        if (requestStatus != 5) {

            list = dao.getScheduleList(requestStatus, parse.getInt("pageIndex"), parse.getInt("pageSize"));
        } else {
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

    public void updateScheduleStatusFunction(int id, int status) {
        dao.updateScheduleStatus(id, status);
    }


    @RequestMapping("/getpankou")
    @ResponseBody
    public ResponseJsonRoot getPankou(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        MJsonParse parse = new MJsonParse(jsonRoot);
        try {
            int pankouid = parse.getInt("id");
            int scheduleId = dao.getScheduleIdFromPankouId(pankouid);
//            List<Pankou> pankouList = dao.getSchedulePankou(scheduleId);
            Schedule schedule = dao.getSchedule(scheduleId);
            schedule.setTime("" + (Long.valueOf(schedule.getTime()) - System.currentTimeMillis()));
            schedule.setPankoutype(dao.getPankouType(pankouid));
            List<Team> teamList = dao.getPankouDetail(pankouid, Constants.USER_ID);
            calPeiLv(teamList, pankouid);
            schedule.setTeamList(teamList);
            result.setRepbody(schedule);

        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result;
    }

    //从赌注金额 计算赔率
    private void calPeiLv(List<Team> teamList, int pankouid) {
        int pankoutype = dao.getPankouType(pankouid);
        double betamount = 0;//此盘口投注总额
        List<Integer> betlist = new ArrayList();
        for (Team team : teamList) {
            betlist.add(Integer.valueOf(team.getBetAmount()));
            betamount = betamount + Integer.valueOf(team.getBetAmount());
        }
        //要减去20%平台扣除的分成,剩下的是预测准确的用户分的总金额
//        betamount=betamount*0.8;
        Collections.sort(betlist, Collections.reverseOrder());
        //取得胜利的前N个队伍的投注总额
        int qianN = 0;
        //取得胜利的前N-1个队伍的投注总额
        int qianNjian1 = 0;
        for (int i = 0; i < pankoutype; i++) {
            qianN = qianN + betlist.get(i);
        }
        qianNjian1 = qianN - betlist.get(pankoutype - 1);
        //计算最低赔率
        for (Team team : teamList) {
            double peilv;
            int teambet = Integer.valueOf(team.getBetAmount());
            //赔率= (输家的钱*80%(平台扣除))/所有队伍赢家的钱*选择队伍赢家的钱
            if (teambet >= betlist.get(pankoutype - 1)) {
                //投注的是前N名
                peilv = (betamount - qianN) * 0.8 / qianN*0.8;
            } else {
                peilv = (betamount - qianNjian1 - teambet) * 0.8 / (qianNjian1 + teambet)*0.8;
            }
//            System.out.println("....."+peilv+".........");
            if (qianN == 0) {
                peilv = 0;
            }
            team.setPeilv(Util.changeDouble(String.valueOf(peilv), 2));
        }


    }

}


