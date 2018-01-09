package com.kingsbet.wzry.dao;

import com.kingsbet.wzry.entity.Pankou;
import com.kingsbet.wzry.entity.PankouType;
import com.kingsbet.wzry.entity.Schedule;
import com.kingsbet.wzry.entity.Team;
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

    void updatePankouDetailAndUser(@Param("pankoudetailid") int pankoudetailid,  @Param("userid") int userid,@Param("amount") int amount);

    void insertUserBetDetail(@Param("pankoudetailid") int pankoudetailid,  @Param("userid") int userid,@Param("amount") int amount);

    void updateUserBetDetail(@Param("pankoudetailid") int pankoudetailid,  @Param("userid") int userid,@Param("amount") int amount);

    int checkUserHasBet(@Param("userid")int userid,@Param("pankoudetailid")int pankoudetailid);

    int updateSchedule(@Param("schedule") Schedule entity);

    List<Schedule> getScheduleList(@Param("status")int status,@Param("pageindex")int pageindex,@Param("pagesize")int pagesize);
    void deleteSchedule(@Param("id")int id);

    void updateScheduleStatus(@Param("id")int id,@Param("status")int status);
    void deleteScheduleTeam(@Param("scheduleId") int scheduleId);

    void insertScheduleTeam(@Param("scheduleId") int scheduleId,@Param("teamIdList") List<Integer> teamIdList);
    void insertSchedulePankou( ArrayList<Pankou> pankoulist);
    void setScheduleRank(ArrayList<Team> teamlist);

    void insertSchedulePankouDetail(@Param("pankoulist")  ArrayList<Pankou> pankouList, @Param("teamIdList") List<Integer> teamIdList);
    List<Pankou> getSchedulePankou(@Param("scheduleid")int scheduleid);
    List<Team> getScheduleTeam(@Param("id")int id);
    Schedule  getSchedule(@Param("id")int id);

    int getScheduleIdFromPankouId(@Param("pankouid") int pankouid);

    List<Team> getPankouDetail(@Param("pankouId")int pankouid,@Param("userId")int userid);


    List<Team> getPankouDetailOrderBybetamount(@Param("pankouId")int pankouid,@Param("userId")int userid);


    PankouType getPankouType(@Param("pankouid") int pankouid);


    int getPankouDetailId(@Param("pankouid")int pankouid,@Param("teamid") int teamid);
    void updateWinUserBetDeail(@Param("pankoudetailid")int pankoudetailid,@Param("peilv") double peilv);
    void updateLoseUserBetDeail(@Param("pankoudetailid")int pankoudetailid);

}