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
    /**
     * scheduleid : 002
     * title1 : 斗鱼黄金大奖赛
     * title2 : A组 VS B组
     * time : 124590346523
     */

    @SerializedName("id")
    private int id;
    @SerializedName("title1")
    private String title1;
    @SerializedName("title2")
    private String title2;
    @SerializedName("time")
    private String time;
    @SerializedName("status")
    private int status;

    @SerializedName("teamlist")
    private List<Team> teamList;
    @SerializedName("pankoulist")
    private  List<Pankou> pankouList;
    @SerializedName("remaintime")
    private String remainTime;
    @SerializedName("pankoutype")
    private int pankoutype;


    @SerializedName("pankouidlist")

    private List<Integer>  pankouIdList;

    @SerializedName("teamidlist")
    private List<Integer> teamIdList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public List<Pankou> getPankouList() {
        return pankouList;
    }

    public void setPankouList(List<Pankou> pankouList) {
        this.pankouList = pankouList;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    public List<Integer> getPankouIdList() {
        return pankouIdList;
    }

    public void setPankouIdList(List<Integer> pankouIdList) {
        this.pankouIdList = pankouIdList;
    }

    public List<Integer> getTeamIdList() {
        return teamIdList;
    }

    public void setTeamIdList(List<Integer> teamIdList) {
        this.teamIdList = teamIdList;
    }

    public int getPankoutype() {
        return pankoutype;
    }

    public void setPankoutype(int pankoutype) {
        this.pankoutype = pankoutype;
    }
}