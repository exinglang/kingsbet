package com.kingsbet.wzry.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JvHeEntity<T> implements Serializable {


    /**
     * reason : 允许充值的手机号码及金额
     * result : null
     * error_code : 0
     */

    @SerializedName("reason")
    private String reason;
    @SerializedName("result")
    private T result;
    @SerializedName("error_code")
    private int errorCode;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "JvHeEntity{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", errorCode=" + errorCode +
                '}';
    }
}