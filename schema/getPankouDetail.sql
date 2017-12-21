SELECT b.team_id,b.name,a.bet_amount,c.bet_amount FROM
 t_schedule_pankou_detail AS a INNER JOIN  t_mteam AS b
ON a.pankou_id=138 and a.team_id =b.team_id INNER JOIN  t_user_bet_detail as c ON c.user_id=123 and c.t_schedule_pankou_detail_id=a.id

