package com.kingsbet.wzry.dao;

import com.kingsbet.wzry.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    void register(@Param("userId")String userId,@Param("pwd") String pwd);
    int checkUserId(@Param("userId")String userId);
    int checkPwd(@Param("userId")String userId,@Param("pwd") String pwd);

    void adminLogin(String username,String pwdMd5);
}
