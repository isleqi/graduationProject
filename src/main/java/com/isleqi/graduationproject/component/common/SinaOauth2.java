package com.isleqi.graduationproject.component.common;

import com.isleqi.graduationproject.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SinaOauth2 {
    private final static Logger logger = LoggerFactory.getLogger(SinaOauth2.class);

    static String appKey = "524337518";
    static String appSecret = "bd5dc471062ac73d09fd1ba7d7129441";
    static String redirect_URI = "http://127.0.0.1:8070/app/login/sinaOauth";


    public static String getAccessToken(String code) {

        Map<String,String> param=new HashMap<>();
        param.put("client_id",appKey);
        param.put("client_secret",appSecret);
        param.put("grant_type","authorization_code");
        param.put("code",code);
        param.put("redirect_uri",redirect_URI);



        String url="https://api.weibo.com/oauth2/access_token";

        String access_token= HttpClientUtil.doPost(url,param);
        return access_token;
    }


    public static String getUser(String access_token,String uid) {
        String url="https://api.weibo.com/2/users/show.json?access_token="+
                access_token+"&uid="+uid;
        String user=null;
        try{
            user =HttpClientUtil.doGet(url);
        }catch (Exception e){
            logger.debug("获取Sina用户信息失败："+e.toString());
        }
        return user;

    }

    public static String getAuthUrl() {
        return "https://api.weibo.com/oauth2/authorize?client_id=" +
                appKey +
                "&response_type=code" +
                "&redirect_uri=" +
                redirect_URI;
    }

}
