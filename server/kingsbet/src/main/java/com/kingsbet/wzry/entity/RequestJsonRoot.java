package com.kingsbet.wzry.entity;

import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;

public class RequestJsonRoot<T> {
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
    private static final String TAG_REQBODY="reqbody";
    @SerializedName(TAG_REQBODY)
    private  T reqbody;
//    @SerializedName(TAG_REQBODY)
//    private JSONObject reqbody2;
//
//    public JSONObject getReqbody2() {
//        return reqbody2;
//    }
//
//    public void setReqbody2(JSONObject reqbody2) {
//        this.reqbody2 = reqbody2;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRetcode() {
        return retcode;
    }

    public T getReqsbody() {
        return reqbody;
    }

    public void setReqsbody(T reqbody) {
        this.reqbody = reqbody;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
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
