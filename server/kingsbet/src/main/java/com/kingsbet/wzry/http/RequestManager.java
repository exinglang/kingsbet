package com.kingsbet.wzry.http;

import com.kingsbet.wzry.Constants;
import com.kingsbet.wzry.entity.Commodity;
import com.kingsbet.wzry.entity.Exchange;
import com.kingsbet.wzry.util.MD5;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kingsbet.wzry.http.HttpRequestUtil;


import java.util.ArrayList;
import java.util.HashMap;

public class RequestManager {
    private static Logger logger = LoggerFactory.getLogger(RequestManager.class);

    public String checkPhoneSocket(Exchange entity, Commodity commodity) {
        String url = "http://op.juhe.cn/ofpay/mobile/telcheck";
        ArrayList paramList = new ArrayList();
        paramList.add(new BasicNameValuePair("phoneno", entity.getPhone()));
        paramList.add(new BasicNameValuePair("cardnum", String.valueOf(commodity.getPrice() / 100)));
        paramList.add(new BasicNameValuePair("key", Constants.JVHE_APPKEY_HUAFEI));
        return HttpRequestUtil.httpGet(url, paramList);

    }

    public String phoneRecharge(Exchange entity, Commodity commodity) {
        String url = "http://op.juhe.cn/ofpay/mobile/onlineorder";
        ArrayList paramList = new ArrayList();
        paramList.add(new BasicNameValuePair("phoneno", entity.getPhone()));
        String cardNum=String.valueOf(commodity.getPrice() / 100);
        paramList.add(new BasicNameValuePair("cardnum",cardNum ));
        paramList.add(new BasicNameValuePair("orderid", entity.getOrderid()));
        paramList.add(new BasicNameValuePair("key", Constants.JVHE_APPKEY_HUAFEI));
        String md5=MD5.getMD532(Constants.JVHE_OPENID+Constants.JVHE_APPKEY_HUAFEI+entity.getPhone()+cardNum+entity.getOrderid());
        logger.info("MD5:"+md5);
        paramList.add(new BasicNameValuePair("sign", md5));
        return HttpRequestUtil.httpGet(url, paramList);
    }
    public String buyCard(Exchange entity, Commodity commodity) {
        String url = "http://v.juhe.cn/giftCard/buy";
        ArrayList paramList = new ArrayList();
        paramList.add(new BasicNameValuePair("dtype", ""));

        paramList.add(new BasicNameValuePair("num", entity.getNumber()+""));
        paramList.add(new BasicNameValuePair("productId",entity.getProductid()+"" ));
        paramList.add(new BasicNameValuePair("userOrderId", entity.getOrderid()));
        String md5=MD5.getMD532(Constants.JVHE_OPENID+Constants.JVHE_APPKEY_LI_PING_KA+entity.getNumber()+entity.getOrderid());
        paramList.add(new BasicNameValuePair("sign", md5));
        paramList.add(new BasicNameValuePair("key", Constants.JVHE_APPKEY_LI_PING_KA));

        logger.info("MD5:"+md5);

        return HttpRequestUtil.httpGet(url, paramList);
    }

}
