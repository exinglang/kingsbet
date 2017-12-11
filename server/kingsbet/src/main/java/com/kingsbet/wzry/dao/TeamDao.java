package com.kingsbet.wzry.dao;

import com.kingsbet.wzry.entity.Team;
import com.kingsbet.wzry.entity.TeamGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Repository
public interface TeamDao {

    void insertTeam(@Param("name")String name,@Param("imgBase64") String imgBase64,@Param("gameType") String gameType);
    //	"type":1,  //1、王者荣耀 2.绝地求生
//            "name":"LGD",//战队名字
//            "pageIndex" : 0	, //当前页码
//            "pageSize" : 2    //每页条数
    List<Team> getTeamList(@Param("type")String type, @Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize);
    List<TeamGroup> getTeamGroupList(@Param("type")String type, @Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize);

    int getSqlImgLength();
    void deleteTeam(@Param("id")int id);

    void deleteTeamGroup(@Param("id")int id);

    void addTeamGroup(@Param("name")String name,@Param("gameType")String gameType);
    void updateTeamGroup(@Param("id")int id,@Param("name")String name);

    void teamGroupAddTeam(@Param("teamgroupId")int teamgroupId,@Param("teamIdList")List<Integer> teamIdList);
    List<Team> getGroupTeam(@Param("id")int groupId);
    void deleteGroupTeam(@Param("groupid")int groupid,@Param("teamid")int teamid);



}