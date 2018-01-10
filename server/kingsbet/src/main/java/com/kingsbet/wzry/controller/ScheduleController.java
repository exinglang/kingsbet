package com.kingsbet.wzry.controller;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.kingsbet.wzry.Constants;
import com.kingsbet.wzry.dao.ScheduleDao;
import com.kingsbet.wzry.dao.UserDao;
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
//    private UserDao userDao;

    @RequestMapping("/addschedule")
    @ResponseBody
    public ResponseJsonRoot addSchedule(@RequestBody RequestJsonRoot<Schedule> jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        Schedule schedule = jsonRoot.getReqsbody();
        try {


            dao.insertSchedule(schedule, Constants.SCHEDULE_STATUS_DAI_FA_BU);
            dao.insertScheduleTeam(schedule.getId(), schedule.getTeamIdList());
            List<Integer> pankoutypeidlist = schedule.getPankoutypeidlist();

            ArrayList<Pankou> pankouTypeList = new ArrayList<>();
            for (int i = 0; i < pankoutypeidlist.size(); i++) {
                Pankou pankou = new Pankou();
//                pankou.setName(intList.get(i) + "");
                pankou.setScheduleid(schedule.getId());
                pankou.setPankoutypeid(pankoutypeidlist.get(i));
                pankouTypeList.add(pankou);
            }
            if (pankouTypeList.size() == 0) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, "请选择盘口");
                return result;
            }
            dao.insertSchedulePankou(pankouTypeList);
            dao.insertSchedulePankouDetail(pankouTypeList, schedule.getTeamIdList());
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

    //    //用户下注
////request
//    {
//        "name": "order",
//            "reqbody": {
//        "pankouid": "003",
//                "teamid": "003",
//                "amount":1000,
//                "userid":123,
//                "sessionStr": "3634F440030595CC6B6B4F718BECA437"
//    }
//    }
    @RequestMapping("/order")
    @ResponseBody
    public ResponseJsonRoot order(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        MJsonParse parse = new MJsonParse(jsonRoot);
        try {
            int pankoudetailid = dao.getPankouDetailId(parse.getInt("pankouid"), parse.getInt("teamid"));
            int count = dao.checkUserHasBet(parse.getInt("userid"), pankoudetailid);
            //包含2个SQL语句.   1t_user扣除用户余额 2更新t_schedule_pankou_detail 用户的投注总额
            dao.updatePankouDetailAndUser(pankoudetailid, parse.getInt("userid"), parse.getInt("amount"));
            if (count == 0) {
                //==0说明用户没有下注过
                dao.insertUserBetDetail(pankoudetailid, parse.getInt("userid"), parse.getInt("amount"));
            } else {
                //!=0  追加
                dao.updateUserBetDetail(pankoudetailid, parse.getInt("userid"), parse.getInt("amount"));
            }
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
//                "scheduleid": 0,
//                        "status": 0,
//                for(Pankou pankou:pankouList){
//                    pankou.setRemainTime();
//                }
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
//        if (requestStatus != 5) {

            list = dao.getScheduleList(requestStatus, parse.getInt("pageIndex"), parse.getInt("pageSize"));
//        } else {
//            list = dao.getScheduleList(2, parse.getInt("pageIndex"), parse.getInt("pageSize"));
//            list.addAll(dao.getScheduleList(3, parse.getInt("pageIndex"), parse.getInt("pageSize")));
//            list.addAll(dao.getScheduleList(4, parse.getInt("pageIndex"), parse.getInt("pageSize")));
//
//        }
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
            if(requestStatus==Constants.SCHEDULE_STATUS_YI_QU_XIAO){
                int status=   dao.getScheduleStatus(parse.getInt("id"));
                //防止恶意请求,重复退款到用户账户
                if(status==Constants.SCHEDULE_STATUS_YI_FA_BU) {
                    dao.returnUserBet(parse.getInt("id"));
                    updateScheduleStatusFunction(parse.getInt("id"), requestStatus);
                }else{
                    result.setRetcodeAndMsg(-1,"比赛状态不在竞猜中");
                }

            }else{
                updateScheduleStatusFunction(parse.getInt("id"), requestStatus);

            }

            //如果是取消比赛,需要退回用户的押注

//            switch (requestStatus) {
//                case Constants.SCHEDULE_STATUS_DAI_JIE_SUAN:
//                    updateScheduleStatusFunction(parse.getInt("id"), Constants.SCHEDULE_STATUS_DAI_JIE_SUAN);
//                    break;
//                case Constants.SCHEDULE_STATUS_YI_FA_BU:
//                    updateScheduleStatusFunction(parse.getInt("id"), Constants.SCHEDULE_STATUS_YI_FA_BU);
//                    break;
//                case Constants.SCHEDULE_STATUS_YI_QU_XIAO:
//                    updateScheduleStatusFunction(parse.getInt("id"), Constants.SCHEDULE_STATUS_YI_QU_XIAO);
//                    break;
//
//            }

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
            Pankou pankou = new Pankou();
            pankou.setId(pankouid);
            pankou.setTime("" + (Long.valueOf(schedule.getTime()) - System.currentTimeMillis()));
            PankouType pankouType = dao.getPankouType(pankouid);
            pankou.setTitle1(schedule.getTitle1());
            pankou.setTitle2(schedule.getTitle2());
            pankou.setPankoutypetype(pankouType.getType());
            pankou.setPankoutypename(pankouType.getName());
            List<Team> teamList = dao.getPankouDetail(pankouid, Constants.USER_ID);
            calPeiLv(teamList, pankouid);
            pankou.setTeamList(teamList);
            result.setRepbody(pankou);

        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result;
    }

    //从赌注金额 计算赔率
    private void calPeiLv(List<Team> teamList, int pankouid) {
        PankouType pankoutype = dao.getPankouType(pankouid);
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
        for (int i = 0; i < pankoutype.getType(); i++) {
            qianN = qianN + betlist.get(i);
        }
        qianNjian1 = qianN - betlist.get(pankoutype.getType() - 1);
        //计算最低赔率
        for (Team team : teamList) {
            double peilv;
            int teambet = Integer.valueOf(team.getBetAmount());
            //投注的是前N名
            if (teambet >= betlist.get(pankoutype.getType() - 1)) {
                double loseTeamAmount = betamount - qianN;
                double winTeamAmount = qianN;
                peilv = calpl(loseTeamAmount, winTeamAmount, teambet);

            } else {
                double loseTeamAmount = betamount - qianNjian1 - teambet;
                double winTeamAmount = qianNjian1 + teambet;
                //投注的不是前N名
                peilv = calpl(loseTeamAmount, winTeamAmount, teambet);
            }
//            System.out.println("....."+peilv+".........");
            if ((qianN == 0) || ((qianNjian1 + teambet) == 0) || teambet == 0) {
                team.setPeilv("--.--");
            } else {
                team.setPeilv(Util.changeDouble(String.valueOf(peilv), 2));
            }
        }


    }

    private double calpl(double loseTeamAmount, double winTeamAmount, double teambet) {
        if(winTeamAmount==0){
            return 0;
        }
        double peilv;
        //如果投注差异过大(输家的钱X0.8<=赢家的0.2手续费),就不扣除赢家的0.8,以防赔率出现负数
        if (loseTeamAmount * 0.8 <= (winTeamAmount * 0.2)) {
            //赔率= ((输家的钱*80%+所有赢家的钱-所有队伍赢家的钱)/所有队伍赢家的钱*(选择的队伍/所有队伍赢家的钱)=选择的队伍可以赢得的钱
            peilv = ((loseTeamAmount * 0.8 + winTeamAmount) - winTeamAmount) / winTeamAmount * (teambet / winTeamAmount);
        } else {
            //赔率= ((输家的钱*80%+所有赢家的钱*0.8-所有队伍赢家的钱)/所有队伍赢家的钱*选择的队伍=选择的队伍可以赢得的钱
            peilv = ((loseTeamAmount * 0.8 + winTeamAmount * 0.8) - winTeamAmount) / winTeamAmount * (teambet / winTeamAmount);
        }
        return peilv;
    }

    @RequestMapping("/setschedulerank")
    @ResponseBody
    public ResponseJsonRoot setScheduleRank(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot returnResult = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
//        TeamList entity = jsonRoot.getReqsbody();
        try {
            Gson gson = new Gson();
            LinkedTreeMap map = (LinkedTreeMap) jsonRoot.getReqsbody();
            JsonObject gsin = gson.toJsonTree(map).getAsJsonObject();
            int scheduleid = gsin.get("scheduleid").getAsInt();
            JsonArray array = gsin.getAsJsonArray("teamidlist");
            ArrayList<Team> list = new ArrayList();
            ArrayList<Integer> winTeamId = new ArrayList();//胜利TEAM的ID集合
            for (int i = 0; i < array.size(); i++) {
                Team team = new Team();
                team.setScheduleid(scheduleid);
                team.setId(array.get(i).getAsJsonObject().get("id").getAsInt());
                winTeamId.add(array.get(i).getAsJsonObject().get("id").getAsInt());
                team.setRank(array.get(i).getAsJsonObject().get("rank").getAsInt());
                list.add(team);
            }
            //设置比赛名次
            dao.setScheduleRank(list);
            List<Pankou> pankouList = dao.getSchedulePankou(scheduleid);
            for (Pankou pankou : pankouList) {
                int pankoutypetype = pankou.getPankoutypetype();
                List<Team> teamList = dao.getPankouDetailOrderBybetamount(pankou.getId(), Constants.USER_ID);
                double winTeamAmount = 0;
                double loseTeamAmount = 0;
                //计算输赢总额
                for (int i = 0; i < teamList.size(); i++) {
                    if (i < pankoutypetype) {
                        winTeamAmount = winTeamAmount + Double.valueOf(teamList.get(i).getBetAmount());
                    } else {
                        loseTeamAmount = loseTeamAmount + Double.valueOf(teamList.get(i).getBetAmount());
                    }
                }

                for (int i = 0; i < teamList.size(); i++) {
                    int pankoudetailid = teamList.get(i).getPankoudetailid();
                    //根据输赢总额计算每个队伍的实际赔率
                    if (i < pankoutypetype) {
//                        teamList.get(i).setPeilv("" + );
                        double d=Double.valueOf(teamList.get(i).getBetAmount());
                        double pl=calpl(loseTeamAmount, winTeamAmount, d);
                        dao.updateWinUserBetDeail(pankoudetailid,pl);
                    } else {
//                        teamList.get(i).setPeilv("0");
                        dao.updateLoseUserBetDeail(pankoudetailid);

                    }
                    //设置用户的
                }
//                for (int i = 0; i < teamList.size(); i++) {
////                    pankoutypetype
//                    int pankoudetailid= teamList.get(i).getPankoudetailid();
//                    dao.setUserBetDetailEarn(pankoudetailid);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnResult.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return returnResult;
    }


}


