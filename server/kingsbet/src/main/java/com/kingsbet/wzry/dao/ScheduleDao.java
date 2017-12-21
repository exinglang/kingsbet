package com.kingsbet.wzry.dao;

import com.kingsbet.wzry.entity.Pankou;
import com.kingsbet.wzry.entity.Schedule;
import com.kingsbet.wzry.entity.Team;
import com.kingsbet.wzry.entity.TeamGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Repository
public interface ScheduleDao {



    int insertSchedule(@Param("schedule") Schedule entity,  @Param("status")int status);
    int updateSchedule(@Param("schedule") Schedule entity);


//    void insertScheduleDetail(@Param("pankoulist") ArrayList<Pankou> scheduleId,@Param("teamIdList") List<Integer> teamIdList);



//    "state": "1",	//1.待发布 2已发布 3待结算 4已结算
//            "pageIndex" : 0	, //当前页码
//            "pageSize" : 2    //每页条数
    List<Schedule> getScheduleList(@Param("status")int status,@Param("pageindex")int pageindex,@Param("pagesize")int pagesize);
    void deleteSchedule(@Param("id")int id);

    void updateScheduleStatus(@Param("id")int id,@Param("status")int status);
    void deleteScheduleTeam(@Param("scheduleId") int scheduleId);

    void insertScheduleTeam(@Param("scheduleId") int scheduleId,@Param("teamIdList") List<Integer> teamIdList);
    void insertSchedulePankou( ArrayList<Pankou> pankoulist);

    void insertSchedulePankouDetail(@Param("pankouList")  ArrayList<Pankou> pankouList,@Param("teamIdList") List<Integer> teamIdList);

    List<Pankou> getSchedulePankou(@Param("scheduleid")int scheduleid);
    List<Team> getScheduleTeam(@Param("id")int id);
    Schedule  getSchedule(@Param("id")int id);

    int getScheduleIdFromPankouId(@Param("pankouid") int pankouid);

    List<Team> getPankouDetail(@Param("pankouId")int pankouid,@Param("userId")int userid);
    int getPankouType(@Param("pankouid") int pankouid);


}