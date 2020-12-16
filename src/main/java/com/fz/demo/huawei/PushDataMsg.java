/*
 * FileName:  RedirectMsgService.java
 * License:  Copyright 2014-2024 Huawei Tech. Co. Ltd. All Rights Reserved.
 * Description: Sample of Push Notification Message Sender
 * Modifier:
 * Modification Date: 2017年7月13日
 * Modification Content: New
 * Modification Version: Push
 */
package com.fz.demo.huawei;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 数据消息
 */
public class PushDataMsg {
    private static String appSecret = "a902e828c9fb54f3c5a5e85169daa7c9";
    private static String appId = "1004593381";//用户在华为开发者联盟申请的appId和appSecret（会员中心->应用管理，点击应用名称的链接）
    private static String tokenUrl = "https://login.vmall.com/oauth2/token"; //获取认证Token的URL
    private static String apiUrl = "https://push-api.cloud.huawei.com/v1/{0}/messages:send"; //应用级消息下发API
    private static final String ACCESS_TOKEN_FORMAT = "Bearer {0}";
    private static String accessToken;//下发通知消息的认证Token
    private static long tokenExpiredTime;  //accessToken的过期时间

    public static void main(String[] args) throws IOException {
        refreshToken();
        sendPushMessage();
    }

    //获取下发通知消息的认证Token
    private static void refreshToken() throws IOException {
        String msgBody = MessageFormat.format(
                "grant_type=client_credentials&client_secret={0}&client_id={1}",
                URLEncoder.encode(appSecret, "UTF-8"), appId);
        String response = httpPost(tokenUrl, msgBody, 5000, 5000);
        AccessToken obj = new Gson().fromJson(response, AccessToken.class);
        accessToken = obj.getAccess_token();
        tokenExpiredTime = System.currentTimeMillis() + obj.getExpires_in() - 5 * 60 * 1000;
    }

    //发送Push消息
    private static void sendPushMessage() throws IOException {
        if (tokenExpiredTime <= System.currentTimeMillis()) {
            refreshToken();
        }
        /*PushManager.requestToken为客户端申请token的方法，可以调用多次以防止申请token失败*/
        /*PushToken不支持手动编写，需使用客户端的onToken方法获取*/
        List<String> deviceTokens = new ArrayList<>();//目标设备Token
        deviceTokens.add("0865452043503058300005939200YY01");

        HashMap<String, Object> data = new HashMap<>();
        data.put("push_id", "123456");//类型3为打开APP，其他行为请参考接口文档设置
        data.put("body", "Zaful-noticeZaful-noticeZaf\uD83D\uDE00Zaful-noticeZaful-noticeZaf\uD83D\uDE00Zaful-noticeZaful-noticeZaf\uD83D\uDE00");//消息点击动作参数
        data.put("title", "Zaful-noticeZaful-noticeZaf\uD83D\uDE00Zaful notice Zaful notice Zaful\uD83D\uDE00");//类型3为打开APP，其他行为请参考接口文档设置
        data.put("url", "zaful://action?actiontype=5&url= https://www.baidu.com&name=fff");//消息点击动作参数
        data.put("is_retargeting", "true");//类型3为打开APP，其他行为请参考接口文档设置
        data.put("pid", "testmediasource");//消息点击动作参数
        data.put("c", "retargetingcampagin");//消息点击动作参数
        data.put("image", "https://gloimg.zafcdn.com/zaful/pdm-product-pic/Clothing/2019/09/18/goods-first-img/1576211961551905999.jpg");//消息点击动作参数
        data.put("sound", "maybe.wav");//消息点击动作参数
        data.put("image_type", "1");//消息点击动作参数


        HashMap<String, Object> message = new HashMap<>();//华为PUSH消息总结构体
        message.put("data", new Gson().toJson(data));
        message.put("token", deviceTokens);

        HashMap<String, Object> payload = new HashMap<>();
        payload.put("validate_only", false);
        payload.put("message", message);
        String payloadJson = new Gson().toJson(payload);
        System.out.println(payloadJson);
        String url = MessageFormat.format(apiUrl, appId);
        httpPost2(url, payloadJson, 5000, 5000);
    }
    public static String httpPost(String httpUrl, String data, int connectTimeout, int readTimeout) throws IOException {
        OutputStream outPut = null;
        HttpURLConnection urlConnection = null;
        InputStream in = null;

        try {
            System.out.println(httpUrl);
            System.out.println(data);
            URL url = new URL(httpUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            urlConnection.setConnectTimeout(connectTimeout);
            urlConnection.setReadTimeout(readTimeout);
            urlConnection.connect();

            // POST data
            outPut = urlConnection.getOutputStream();
            outPut.write(data.getBytes("UTF-8"));
            outPut.flush();

            // read response
            if (urlConnection.getResponseCode() < 400) {
                in = urlConnection.getInputStream();
            } else {
                in = urlConnection.getErrorStream();
            }

            List<String> lines = IOUtils.readLines(in, urlConnection.getContentEncoding());
            StringBuffer strBuf = new StringBuffer();
            for (String line : lines) {
                strBuf.append(line);
            }
            System.out.println(strBuf.toString());
            return strBuf.toString();
        } finally {
            IOUtils.closeQuietly(outPut);
            IOUtils.closeQuietly(in);
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
    public static String httpPost2(String httpUrl, String data, int connectTimeout, int readTimeout) throws IOException {
        OutputStream outPut = null;
        HttpURLConnection urlConnection = null;
        InputStream in = null;

        try {
            System.out.println(httpUrl);
            System.out.println(data);
            URL url = new URL(httpUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            String author = MessageFormat.format(ACCESS_TOKEN_FORMAT, accessToken);
            System.out.println(author);
            urlConnection.setRequestProperty("Authorization", author);
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setConnectTimeout(connectTimeout);
            urlConnection.setReadTimeout(readTimeout);
            urlConnection.connect();

            // POST data
            outPut = urlConnection.getOutputStream();
            outPut.write(data.getBytes("UTF-8"));
            outPut.flush();

            // read response
            if (urlConnection.getResponseCode() < 400) {
                in = urlConnection.getInputStream();
            } else {
                in = urlConnection.getErrorStream();
            }

            List<String> lines = IOUtils.readLines(in, urlConnection.getContentEncoding());
            StringBuffer strBuf = new StringBuffer();
            for (String line : lines) {
                strBuf.append(line);
            }
            System.out.println(strBuf.toString());
            return strBuf.toString();
        } finally {
            IOUtils.closeQuietly(outPut);
            IOUtils.closeQuietly(in);
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
