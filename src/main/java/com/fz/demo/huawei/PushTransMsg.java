/*
 * FileName:  RedirectMsgService.java
 * License:  Copyright 2014-2024 Huawei Tech. Co. Ltd. All Rights Reserved.
 * Description: Sample of Push Transparent Message Sender  
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

//Push透传消息Demo
//本示例程序中的appId,appSecret以及deviceTokens需要用户自行替换为有效值
public class PushTransMsg
{
    private static String appSecret = "a902e828c9fb54f3c5a5e85187daa7c9";
    private static  String appId = "100459351";//用户在华为开发者联盟申请的appId和appSecret（会员中心->应用管理，点击应用名称的链接）
    private static  String tokenUrl = "https://login.vmall.com/oauth2/token";//获取认证Token的URL
    private static  String apiUrl = "https://api.push.hicloud.com/pushsend.do"; //应用级消息下发API
    private static  String accessToken;//下发通知消息的认证Token
    private static  long tokenExpiredTime;  //accessToken的过期时间
    
    public static void main(String[] args) throws IOException
    {
        refreshToken();
        sendPushMessage();
    }
    
    //获取下发通知消息的认证Token
    private static  void refreshToken() throws IOException
    {
        String msgBody = MessageFormat.format(
        				"grant_type=client_credentials&client_secret={0}&client_id={1}", 
        				URLEncoder.encode(appSecret, "UTF-8"), appId);
        String response = httpPost(tokenUrl, msgBody, 5000, 5000);
        AccessToken obj =new Gson().fromJson(response, AccessToken.class);
        accessToken = obj.getAccess_token();
        tokenExpiredTime = System.currentTimeMillis() + obj.getExpires_in() - 5*60*1000;
    }
    
    //发送Push消息
    private static  void sendPushMessage() throws IOException
    {
        if (tokenExpiredTime <= System.currentTimeMillis())
        {
            refreshToken();
        }
        /*PushManager.requestToken为客户端申请token的方法，可以调用多次以防止申请token失败*/
        /*PushToken不支持手动编写，需使用客户端的onToken方法获取*/
        List<String> deviceTokens = new ArrayList<>();//目标设备Token
        deviceTokens.add("0865452043503058300005939200CN01");

        HashMap<String,Object> body = new HashMap<>();
        body.put("key1", "value1");//透传消息自定义body内容
        body.put("key2", "value2");//透传消息自定义body内容
        body.put("key3", "value3");//透传消息自定义body内容

        HashMap<String,Object> msg = new HashMap<>();
        msg.put("type", 1);//1: 透传异步消息，通知栏消息请根据接口文档设置
        msg.put("body", body);//body内容不一定是JSON，可以是String，若为JSON需要转化为String发送

        HashMap<String,Object> hps = new HashMap<>();//华为PUSH消息总结构体
        hps.put("msg", msg);

        HashMap<String,Object> payload = new HashMap<>();
        payload.put("hps", hps);
        String tokens=new Gson().toJson(deviceTokens);
        String payloadJson= new Gson().toJson(payload);
        System.out.println(payloadJson);
        String postBody = MessageFormat.format(
        	"access_token={0}&nsp_svc={1}&nsp_ts={2}&device_token_list={3}&payload={4}",
            URLEncoder.encode(accessToken,"UTF-8"),
            URLEncoder.encode("openpush.message.api.send","UTF-8"),
            URLEncoder.encode(String.valueOf(System.currentTimeMillis() / 1000),"UTF-8"),
            URLEncoder.encode(tokens,"UTF-8"),
            URLEncoder.encode(payloadJson,"UTF-8"));
        
        String postUrl = apiUrl + "?nsp_ctx=" + URLEncoder.encode("{\"ver\":\"1\", \"appId\":\"" + appId + "\"}", "UTF-8");
        httpPost(postUrl, postBody, 5000, 5000);
    }
    
    public static String httpPost(String httpUrl, String data, int connectTimeout, int readTimeout) throws IOException
    {
        OutputStream outPut = null;
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        
        try
        {
            URL url = new URL(httpUrl);
            urlConnection = (HttpURLConnection)url.openConnection();          
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
            if (urlConnection.getResponseCode() < 400)
            {
                in = urlConnection.getInputStream();
            }
            else
            {
                in = urlConnection.getErrorStream();
            }
            
            List<String> lines = IOUtils.readLines(in, urlConnection.getContentEncoding());
            StringBuffer strBuf = new StringBuffer();
            for (String line : lines)
            {
                strBuf.append(line);
            }
            System.out.println(strBuf.toString());
            return strBuf.toString();
        }
        finally
        {
            IOUtils.closeQuietly(outPut);
            IOUtils.closeQuietly(in);
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
        }
    }
}
