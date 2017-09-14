package com.kingsbet.wzry.entity;

import com.google.gson.annotations.SerializedName;

public class JsonRoot<T> {
    /**
     * name : digAssetCommodity
     * retcode : 0
     * msg :
     * repbody : T
     */

    @SerializedName("name")
    private String name;
    @SerializedName("retcode")
    private int retcode;
    @SerializedName("msg")
    private String msg;

    public static final String TAG_REPBODY="reqbody";
    @SerializedName(TAG_REPBODY)
    private  T reqbody;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRetcode() {
        return retcode;
    }

    public T getReqbody() {
        return reqbody;
    }

    public void setReqbody(T reqbody) {
        this.reqbody = reqbody;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

//    public JsonRootBody<T> getJsonRootBody() {
//        return jsonRootBody;
//    }
//
//    public void setJsonRootBody(JsonRootBody<T> jsonRootBody) {
//        this.jsonRootBody = jsonRootBody;
//    }


//    @Expose
//    @SerializedName("MMTS")
//    private MmtsRep<T> mmts;
//
//    public MmtsRep<T> getMmts() {
//        return mmts;
//    }
//
//    public void setMmts(MmtsRep<T> mmts) {
//        this.mmts = mmts;
//    }

}
