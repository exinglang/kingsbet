package com.kingsbet.wzry.entity;

import com.google.gson.annotations.SerializedName;

public class ResponseJsonRoot<T> {
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
    private static final String TAG_REPBODY = "respbody";
    @SerializedName(TAG_REPBODY)
    private T repbody;



    public ResponseJsonRoot(String name, int retcode, String msg) {
        this.name = name;
        this.retcode = retcode;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRetcode() {
        return retcode;
    }

    public T getRepbody() {
        return repbody;
    }

    public void setRepbody(T repbody) {
        this.repbody = repbody;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }
    public void setRetcodeAndMsg(int retcode,String msg) {
        this.retcode = retcode;
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    public interface UserDaoI {
        /**
         * 通过ID查询单本图书
         *
         * @param id
         * @return
         */
        User queryById(int id);
        void insertById(long id);
        void register(User user);
        void adminLogin(String username,String pwdMd5);
    }

    @Override
    public String toString() {
        return "ResponseJsonRoot{" +
                "name='" + name + '\'' +
                ", retcode=" + retcode +
                ", msg='" + msg + '\'' +
                ", repbody=" + repbody +
                '}';
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
