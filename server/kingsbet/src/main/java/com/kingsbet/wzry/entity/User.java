package com.kingsbet.wzry.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * @author zjn
     */
    private static final long serialVersionUID = 1L;
    @SerializedName("userid")
    private int userid; // id
    @SerializedName("nickname")
    private String nickname; // name
    @SerializedName("unionid")
    private String unionid; // unionid
    @SerializedName("pwd")
    private String pwd; // pwd
    @SerializedName("creattime")
    private String creattime; // creatTime
    @SerializedName("phone")
    private String phone; // creatTime

    @SerializedName("diamond")
    private int diamond; // diamond

    @SerializedName("balance")
    private int balance; // balance

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}