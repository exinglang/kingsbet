package com.kingsbet.wzry.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderHistory implements Serializable {


    /**
     * state : 1
     * time : 823534631
     * title1 : 斗鱼黄金大奖赛
     * title2 : A vs s
     * pankouname : 进前五
     * teamname : SECRET
     * bet : 150
     * betwin : -150
     */

    @SerializedName("status")
    private int status;
    @SerializedName("time")
    private String time;
    @SerializedName("title1")
    private String title1;
    @SerializedName("title2")
    private String title2;
    @SerializedName("pankouname")
    private String pankouname;
    @SerializedName("teamname")
    private String teamname;
    @SerializedName("bet")
    private String bet;
    @SerializedName("betwin")
    private String betwin;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getPankouname() {
        return pankouname;
    }

    public void setPankouname(String pankouname) {
        this.pankouname = pankouname;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getBet() {
        return bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public String getBetwin() {
        return betwin;
    }

    public void setBetwin(String betwin) {
        this.betwin = betwin;
    }
}