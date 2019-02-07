package com.isleqi.graduationproject.component.common;

import com.alibaba.fastjson.JSONObject;
import com.isleqi.graduationproject.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GithubOauth2 extends Oauth2{

    private final static Logger logger = LoggerFactory.getLogger(GithubOauth2.class);

    static String client_ID = "742507a3c11705661108";
    static String client_SERCRET = "9478b727ba0c376b94ff3d00d01ea457552548e5";
    static String redirect_URI = "http://192.168.1.6:8070/app/login/githubOauth";


    public static String getAccessToken(String code) {
        String url="https://github.com/login/oauth/access_token?client_id="
                +client_ID+"&client_secret="
                +client_SERCRET+"&code="
                +code;
        String access_token=HttpClientUtil.doGet(url);
        return access_token;
    }


    public static String getUser(String access_token) {
        String url="https://api.github.com/user?access_token="+access_token;
        String user=null;
        try{
          user =HttpClientUtil.doGet(url);
        }catch (Exception e){
            logger.debug("获取GitHub用户信息失败："+e.toString());
        }
        return user;

    }


    public static String getAuthUrl() {
        return "https://github.com/login/oauth/authorize?client_id="+client_ID;
    }
}
