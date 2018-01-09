package com.kingsbet.wzry.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PankouType implements Serializable {




    /**
     * id : 7
     * name : 前三
     * type : 3
     */

    @SerializedName("pankoutypename")
    private String name;
    @SerializedName("pankoutypetype")
    private int type;
    @SerializedName("pankoutypeid")
    private String id;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}