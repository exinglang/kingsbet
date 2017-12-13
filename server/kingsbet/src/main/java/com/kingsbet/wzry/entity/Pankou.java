package com.kingsbet.wzry.entity;

import java.io.Serializable;
import java.util.Date;

public class Pankou implements Serializable {
    private int id,scheduleId; // id
    private String name; // name

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}