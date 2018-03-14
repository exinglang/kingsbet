package com.kingsbet.wzry.entity;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class Exchange implements Serializable {
    /**
     * @author zjn
     */
    private static final long serialVersionUID = 1L;

    /**
     * commodityId : 001
     * number : 3
     * price : 3
     */
    @SerializedName("orderid")
    private String orderid;
    @SerializedName("productid")
    private int productid;
    @SerializedName("extype")
    private int extype;
    @SerializedName("phone")
    private String phone;
    @SerializedName("userid")
    private int userid;
    @SerializedName("commodityId")
    private int commodityId;
    @SerializedName("number")
    private int number;
    @SerializedName("price")
    private int price;
    @SerializedName("reqdate")
    private String reqdate;
    @SerializedName("commoditytype")
    private int commoditytype;
    @SerializedName("status")
    private int status;//1.申请兑换

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getExtype() {
        return extype;
    }

    public void setExtype(int extype) {
        this.extype = extype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCommoditytype() {
        return commoditytype;
    }

    public void setCommoditytype(int commoditytype) {
        this.commoditytype = commoditytype;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getReqdate() {
        return reqdate;
    }

    public void setReqdate(String reqdate) {
        this.reqdate = reqdate;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}