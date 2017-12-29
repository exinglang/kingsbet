package com.kingsbet.wzry.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Pankou implements Serializable {


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
    @SerializedName("scheduleid")
    private  transient int scheduleid;
    @SerializedName("title1")
    private String title1;
    @SerializedName("title2")
    private String title2;
    @SerializedName("time")
    private String time;
    @SerializedName("status")
    private transient int status;
    @SerializedName("teamlist")
    private List<Team> teamList;

    @SerializedName("remaintime")
    private String remainTime;

    @SerializedName("pankoutypename")
    private String pankoutypename;

    @SerializedName("pankoutypeid")
    private int pankoutypeid;

    @SerializedName("pankoutypetype")
    private int pankoutypetype;
    @SerializedName("teamidlist")

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



    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }






    public String getPankoutypename() {
        return pankoutypename;
    }

    public void setPankoutypename(String pankoutypename) {
        this.pankoutypename = pankoutypename;
    }

    public int getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(int scheduleid) {
        this.scheduleid = scheduleid;
    }

    public int getPankoutypeid() {
        return pankoutypeid;
    }

    public void setPankoutypeid(int pankoutypeid) {
        this.pankoutypeid = pankoutypeid;
    }

    public int getPankoutypetype() {
        return pankoutypetype;
    }

    public void setPankoutypetype(int pankoutypetype) {
        this.pankoutypetype = pankoutypetype;
    }

}