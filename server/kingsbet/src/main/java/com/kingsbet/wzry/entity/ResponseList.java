package com.kingsbet.wzry.entity;

import java.io.Serializable;

public class ResponseList<T> implements Serializable {

    private T list; // list


    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}