package com.kingsbet.wzry.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Commodity implements Serializable {


    /**
     * name : LGD
     * price : 100
     * type : 1
     * img : 0595CC6B6B4F718BEC0595CC6B6B4F718BEC0595CC6B6B4F718BEC0595CC6B6B4F718BEC
     */
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private int price;
    @SerializedName("type")
    private int type;
    @SerializedName("img")
    private String img;
    @SerializedName("productid")
    private int productid;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}