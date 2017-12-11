package com.kingsbet.wzry.entity;

import java.io.Serializable;

public class TeamList implements Serializable {
//	"type":1,  //1、王者荣耀 2.绝地求生
//            "name":"LGD",//战队名字
//            "pageIndex" : 0	, //当前页码
//            "pageSize" : 2    //每页条数
    private String type,name,pageIndex,pageSize;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}