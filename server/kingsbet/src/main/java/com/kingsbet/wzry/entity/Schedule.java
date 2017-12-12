package com.kingsbet.wzry.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Schedule implements Serializable {


    /**
     * title1 : LGD
     * title2 : Liquid
     * teamList : [1,6,8]
     * time : 125239748992
     * session : 3634F440030595CC6B6B4F718BECA437
     */
    private  int id,state;//插入记录的主键值
    private String title1;
    private String title2;
    private String time;
    private String session;
    @SerializedName("teamlist")
    private List<Integer> teamList;
    @SerializedName("pankou")
    private List<String> pankou;

    public List<String> getPankou() {
        return pankou;
    }

    public void setPankou(List<String> pankou) {
        this.pankou = pankou;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public List<Integer> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Integer> teamList) {
        this.teamList = teamList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}