package com.kingsbet.wzry.entity;

public class ResponseLogin {

    private String userid; // id
    private String sessionStr; // session

    public ResponseLogin(String userid, String sessionStr) {
        this.userid = userid;
        this.sessionStr = sessionStr;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSessionStr() {
        return sessionStr;
    }

    public void setSessionStr(String sessionStr) {
        this.sessionStr = sessionStr;
    }
}
