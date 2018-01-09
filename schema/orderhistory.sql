SELECT    c.title_1,c.title_2,c.status,c.time  ,e.name ,f.name ,a.user_bet_amount,a.user_bet_earn FROM
        t_user_bet_detail AS a  JOIN  t_schedule_pankou_detail AS b
        ON  a.user_id =1 and  a.t_schedule_pankou_detail_id=b.id  join t_schedule as c on b.schedule_id=c.schedule_id   
         join t_schedule_pankou as d  on  b.pankou_id=d.id join t_pankou_type as e  on d.pankou_type_id = e.id join t_mteam as f on b.team_id=f.team_id