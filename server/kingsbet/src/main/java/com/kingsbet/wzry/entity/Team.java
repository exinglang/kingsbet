package com.kingsbet.wzry.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Team implements Serializable {
    @SerializedName("name")
    private String name; // id
    @SerializedName("id")
    private String id; // id
    @SerializedName("img")
    private String img; // name
    @SerializedName("peilv")
    private String peilv;
    @SerializedName("userbet")
    private String userbet;
    @SerializedName("gametype")
    private String gametype; // id

    private transient String betamount;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}