package com.kingsbet.wzry;

public class Constants {

    public static final int SESSION_TIMEOUT = 60;//session过期时间  (分)
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAIL = -1;
    public static final String MSG_FAIL_UNKNOW = "未知错误";
    public static final String MSG_FAIL_EXCHANGE = "金额验证失败,请联系管理员";
    public static final String MSG_FAIL_NOT_BALANCE = "K币不足";
    public static final int HUA_FEI = 1;
    public static final int KA = 2;

    public static final String JVHE_APPKEY_HUAFEI = "11998047e3b74aecf6f9b69f38b2d912";
    public static final String JVHE_APPKEY_LI_PING_KA = "457d68417f98f66e425e5941987b30ce";


    public static final String JVHE_OPENID = "JH26b9229d4b98cb2ff4c7b7138cd16130";

//    public static final String MSG_FAIL_NOT_BALANCE = "未知错误";

    public static final int USER_ID = 1;
    public static final int SCHEDULE_STATUS_DAI_FA_BU = 1;//待发布
    public static final int SCHEDULE_STATUS_YI_FA_BU = 2;//已发布(竞猜中)
    public static final int SCHEDULE_STATUS_DAI_JIE_SUAN= 3;//待结算
    public static final int SCHEDULE_STATUS_YI_QU_XIAO= 4;//已取消
    public static final int SCHEDULE_STATUS_YI_JIE_SUAN= 5;//已结算
    public static final int SCHEDULE_STATUS_OLD= 6;//包括3待结算,4已取消,5已结算
    //t_user_account_record  type 1 :用户充值钻石 2,钻石转金币  3.兑换金币


}
