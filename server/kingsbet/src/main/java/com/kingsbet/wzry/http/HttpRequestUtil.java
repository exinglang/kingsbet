package com.kingsbet.wzry.http;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequestUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return
     */
    public static String httpGet(String url) {
        return httpGet(url, null);
//
    }

    /**
     * 发送get请求 带参数
     *
     * @param url 路径
     * @return
     */
    public static String httpGet(String url, ArrayList params) {
        String strResult = null;
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request;
            //发送get请求
            if (params == null) {
                request = new HttpGet(url);
            } else {
                String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
                logger.info(str);
                request = new HttpGet(url + "?" + str);
                logger.info("HttpGet:"+url + "?" + str);
            }
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                strResult  = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                logger.info(strResult);
                jsonResult = new JSONObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        }
        return strResult;
    }

//    /**
//     * httpPost
//     * @param url  路径
//     * @param jsonParam 参数
//     * @return
//     */
//    public static JSONObject httpPost(String url,JSONObject jsonParam){
//        return httpPost(url, jsonParam, false);
//    }

    /**
     * post请求
     *
     * @param url            url地址
     * @param jsonParam      参数
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam) {

        //post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);

        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());

                    /**把json字符串转换成json对象**/
                    jsonResult = new JSONObject(str);
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }
}