package com.kingsbet.wzry.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.kingsbet.wzry.ApplicationContext;
import com.kingsbet.wzry.Constants;
import com.kingsbet.wzry.dao.TeamDao;
import com.kingsbet.wzry.dao.UserDao;
import com.kingsbet.wzry.entity.*;
import com.kingsbet.wzry.util.SessionUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * 用户管理
 *
 * @author zjn
 */
@Controller
public class TeamController extends BaseController {
    @Autowired
    private TeamDao dao;

    @RequestMapping("/addteam")
    @ResponseBody
    public ResponseJsonRoot addteam(@RequestBody RequestJsonRoot<Team> jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        Team entity = jsonRoot.getReqsbody();
        try {
            int sqlImgLength = dao.getSqlImgLength();
            if (sqlImgLength < entity.getImg().length()) {
                result.setRetcodeAndMsg(Constants.CODE_FAIL, "图片过大,请上传较小图片");
                return result;
            }

            dao.insertTeam(entity.getName(), entity.getImg(), entity.getGametype());
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return result;
    }

    @RequestMapping("/addteamgroup")
    @ResponseBody
    public ResponseJsonRoot addteamgroup(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        try {
            MJsonParse parse = new MJsonParse(jsonRoot);
            dao.addTeamGroup(parse.getString("name"), parse.getString("gametype"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result;
    }


    @RequestMapping("/updateteamgroup")
    @ResponseBody
    public ResponseJsonRoot updateTeamGroup(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        try {
            MJsonParse parse = new MJsonParse(jsonRoot);

            dao.updateTeamGroup(Integer.valueOf(parse.getString("id")), parse.getString("name"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }
        return result;
    }

    @RequestMapping("/teamlist")
    @ResponseBody
    public ResponseJsonRoot getTeamList(@RequestBody RequestJsonRoot<TeamList> jsonRoot) {
        ResponseJsonRoot returnResult = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        TeamList entity = jsonRoot.getReqsbody();
        try {
            //需先转为LImit
            int limitPre = Integer.valueOf(entity.getPageIndex()) * Integer.valueOf(entity.getPageSize());
            List<Team> list = dao.getTeamList(entity.getType(), limitPre, Integer.valueOf(entity.getPageSize()));

            ResponseTypeAndList typeAndList = new ResponseTypeAndList();
            typeAndList.setList(list);
            typeAndList.setType(entity.getType());
            returnResult.setRepbody(typeAndList);


        } catch (Exception e) {
            e.printStackTrace();
            returnResult.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return returnResult;
    }

    @RequestMapping("/teamgrouplist")
    @ResponseBody
    public ResponseJsonRoot getTeamGroupList(@RequestBody RequestJsonRoot<TeamList> jsonRoot) {
        ResponseJsonRoot returnResult = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        TeamList entity = jsonRoot.getReqsbody();
        try {
            //需先转为LImit
            int limitPre = Integer.valueOf(entity.getPageIndex()) * Integer.valueOf(entity.getPageSize());
            List<TeamGroup> list = dao.getTeamGroupList(entity.getType(), limitPre, Integer.valueOf(entity.getPageSize()));

            ResponseTypeAndList typeAndList = new ResponseTypeAndList();
            typeAndList.setList(list);
            typeAndList.setType(entity.getType());
            returnResult.setRepbody(typeAndList);


        } catch (Exception e) {
            e.printStackTrace();
            returnResult.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return returnResult;
    }

    @RequestMapping("/deleteteam")
    @ResponseBody
    public ResponseJsonRoot deleteTeam(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        try {
            MJsonParse parse = new MJsonParse(jsonRoot);
            dao.deleteTeam(Integer.valueOf(parse.getString("id")));
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return result;
    }

    @RequestMapping("/deleteteamgroup")
    @ResponseBody
    public ResponseJsonRoot deleteTeamGroup(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        try {
            MJsonParse parse = new MJsonParse(jsonRoot);
            dao.deleteTeamGroup(Integer.valueOf(parse.getString("id")));
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return result;
    }


//    @RequestMapping("/teamgroupaddteam")
//    @ResponseBody
//    public ResponseJsonRoot teamGroupAddTeam(@RequestBody RequestJsonRoot<Team> jsonRoot) {
//        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
//        Team entity = jsonRoot.getReqsbody();
//        try {
//            int sqlImgLength = dao.getSqlImgLength();
//            if (sqlImgLength < entity.getImg().length()) {
//                result.setRetcodeAndMsg(Constants.CODE_FAIL, "图片过大,请上传较小图片");
//                return result;
//            }
//
//            dao.insertTeam(entity.getName(), entity.getImg(), entity.getGametype());
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
//        }
//
//        return result;
//    }


    //    {
//        "name": "teamgroupaddteam",
//            "reqbody": {
//        "teamgroupid": "1",
//                "teamidlist": [
//        1,
//                2,
//                3,
//                4,
//                5
//        ]
//    }
//    }
    @RequestMapping("/teamgroupaddteam")
    @ResponseBody
    public ResponseJsonRoot teamGroupAddTeam(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        try {
            Gson gson = new Gson();
            LinkedTreeMap map = (LinkedTreeMap) jsonRoot.getReqsbody();
            JsonObject gsin = gson.toJsonTree(map).getAsJsonObject();
            int name = gsin.get("teamgroupid").getAsInt();
            JsonArray array = gsin.getAsJsonArray("teamidlist");
            ArrayList list = new ArrayList();
            for (int i = 0; i <array.size(); i++) {
                list.add(array.get(i).getAsInt());
            }

            dao.teamGroupAddTeam(name, list);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return result;
    }
    @RequestMapping("/groupteam")
    @ResponseBody
    public ResponseJsonRoot getGroupTeam(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        try {
            MJsonParse parse = new MJsonParse(jsonRoot);
            List<Team> list =  dao.getGroupTeam(parse.getInt("groupid"));


            ResponseTypeAndList typeAndList = new ResponseTypeAndList();
            typeAndList.setList(list);
            result.setRepbody(typeAndList);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return result;
    }




    @RequestMapping("/deletegroupteam")
    @ResponseBody
    public ResponseJsonRoot deleteGroupTeam(@RequestBody RequestJsonRoot jsonRoot) {
        ResponseJsonRoot result = new ResponseJsonRoot(jsonRoot.getName(), Constants.CODE_SUCCESS, "");
        try {
            MJsonParse parse = new MJsonParse(jsonRoot);
            dao.deleteGroupTeam(parse.getInt("groupid"),parse.getInt("teamid"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetcodeAndMsg(Constants.CODE_FAIL, Constants.MSG_FAIL_UNKNOW);
        }

        return result;
    }

    private class MJsonParse {
        RequestJsonRoot jsonRoot;

        MJsonParse(RequestJsonRoot jsonRoot) {
            this.jsonRoot = jsonRoot;

        }

        private String getString(String tag) {
            Gson gson = new Gson();
            LinkedTreeMap map = (LinkedTreeMap) jsonRoot.getReqsbody();
            JsonObject gsin = gson.toJsonTree(map).getAsJsonObject();
            return gsin.get(tag).getAsString();

        }
        private int getInt(String tag) {
            Gson gson = new Gson();
            LinkedTreeMap map = (LinkedTreeMap) jsonRoot.getReqsbody();
            JsonObject gsin = gson.toJsonTree(map).getAsJsonObject();
            return gsin.get(tag).getAsInt();

        }
    }


}

