update t_user set balance=balance+(select user_bet_amount from t_user_bet_detail where t_schedule_pankou_detail_id in 

(select id from t_schedule_pankou_detail where schedule_id=207 ))

where id in (select user_id from t_user_bet_detail where t_schedule_pankou_detail_id in 

(select id from t_schedule_pankou_detail where schedule_id=207 ))