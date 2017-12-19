package com.kingsbet.wzry.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSchedule {


    /**
     * scheduleid : 002
     * title1 : 斗鱼黄金大奖赛
     * title2 : A组 VS B组
     * time : 124590346523
     */

    @SerializedName("scheduleid")
    private String scheduleid;
    @SerializedName("title1")
    private String title1;
    @SerializedName("title2")
    private String title2;
    @SerializedName("time")
    private String time;
    @SerializedName("status")
    private String status;

    @SerializedName("teamlist")
    private List<Team> teamlist;
    @SerializedName("pankoulist")
    private  List<Pankou> pankoulist;

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
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

    public List<Team> getTeamlist() {
        return teamlist;
    }

    public void setTeamlist(List<Team> teamlist) {
        this.teamlist = teamlist;
    }

    public List<Pankou> getPankoulist() {
        return pankoulist;
    }

    public void setPankoulist(List<Pankou> pankoulist) {
        this.pankoulist = pankoulist;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
