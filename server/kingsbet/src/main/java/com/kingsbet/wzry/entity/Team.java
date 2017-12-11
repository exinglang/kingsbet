package com.kingsbet.wzry.entity;

import java.io.Serializable;
import java.util.Date;

public class Team implements Serializable {

    private String name,id; // id
    private String img,gametype; // name

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
}