package com.kingsbet.wzry.entity;

import java.io.Serializable;

public class ResponseTypeAndList<T> implements Serializable {

    private String type; // type
    private T list; // list

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}