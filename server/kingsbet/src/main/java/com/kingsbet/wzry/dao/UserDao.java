package com.kingsbet.wzry.dao;

import com.kingsbet.wzry.entity.OrderHistory;
import com.kingsbet.wzry.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Repository
public interface UserDao {
    /**
     * 通过ID查询单本图书
     *
     * @param id
     * @return
     */
    User queryById(int id);

    void insertById(long id);

    void register(@Param("phone") String phone, @Param("pwd") String pwd, @Param("time") String time);

    void charge(@Param("userid") int userid, @Param("amount") int amount, @Param("time") String time);

    void diamondtobalance(@Param("userid") int userid, @Param("time") String time);

    int getdiamond(@Param("userid") int userid);

    List<OrderHistory> orderhistory(@Param("userid") int userid, @Param("pageindex") int pageindex, @Param("pagesize") int pagesize);

    int checkPhone(@Param("phone") String phone);

    int checkPwd(@Param("phone") String phone, @Param("pwd") String pwd);

    void adminLogin(String username, String pwdMd5);

    int getUserIdByPhone(@Param("phone") String phone);

    User userInfo(int userid);


}
