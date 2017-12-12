package com.kingsbet.wzry.dao;

import com.kingsbet.wzry.entity.Schedule;
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
public interface ScheduleDao {



    int insertSchedule(@Param("entity") Schedule entity, @Param("title1")String title1, @Param("title2") String title2, @Param("time") String time, @Param("state")int state);
    void insertScheduleDetail(@Param("scheduleId")int title1,@Param("teamIdList") List<Integer> teamIdList);
    void insertSchedulePankou(@Param("scheduleId")int title1,@Param("pankounamelist") List<String> teamIdList);


//    "state": "1",	//1.待发布 2已发布 3待结算 4已结算
//            "pageIndex" : 0	, //当前页码
//            "pageSize" : 2    //每页条数
    List<Schedule> getScheduleList(@Param("state")int state,@Param("pageindex")int pageindex,@Param("pagesize")int pagesize);
    void deleteSchedule(@Param("id")int id);

    void updateScheduleState(@Param("id")int id,@Param("state")int state);


}