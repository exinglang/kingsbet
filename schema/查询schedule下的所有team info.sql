SELECT b.team_id,b.name,a.bet_amount  FROM
 t_schedule_pankou_detail AS a,
 t_mteam AS b 
 WHERE a.pankou_id=132 and a.team_id =b.team_id