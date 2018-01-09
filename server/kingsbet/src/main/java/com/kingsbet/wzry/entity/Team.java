package com.kingsbet.wzry.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Team implements Serializable {
    @SerializedName("scheduleid")
    private int scheduleid; // id
    @SerializedName("name")
    private String name; // id
    @SerializedName("id")
    private int id; // id
    @SerializedName("img")
    private String img; // name
    @SerializedName("peilv")
    private String peilv;
    @SerializedName("userbet")
    private String userbet;
    @SerializedName("gametype")
    private String gametype; // id

    @SerializedName("pankoudetailid")
    private int pankoudetailid; // id

    private transient String betamount;

    @SerializedName("rank")
    private int rank; // id

    public int getPankoudetailid() {
        return pankoudetailid;
    }

    public void setPankoudetailid(int pankoudetailid) {
        this.pankoudetailid = pankoudetailid;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getBetAmount() {
        return betamount;
    }

    public void setBetAmount(String betAmount) {
        this.betamount = betAmount;
    }

    public String getUserBet() {
        return userbet;
    }

    public void setUserBet(String userBet) {
        this.userbet = userBet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGametype() {
        return gametype;
    }

    public void setGametype(String gametype) {
        this.gametype = gametype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPeilv() {
        return peilv;
    }

    public void setPeilv(String peilv) {
        this.peilv = peilv;
    }

    public int getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(int scheduleid) {
        this.scheduleid = scheduleid;
    }
}