package com.kingsbet.wzry.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * @author zjn
     */
    private static final long serialVersionUID = 1L;
    private int userId; // id
    private String strnickname; // name
    private String pwd; // pwd
    private Integer ageM; // age
    private Date creatTime; // creatTime

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getAge() {
        return ageM;
    }

    public void setAge(Integer age) {
        this.ageM = age;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }


}