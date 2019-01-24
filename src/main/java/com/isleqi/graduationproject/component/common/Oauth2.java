package com.isleqi.graduationproject.component.common;

import com.alibaba.fastjson.JSONObject;

public abstract class Oauth2 {
    public static final String QQ = "qq";
    public static final String SINA = "sina";
    public static final String WECHAT = "wechat";
    public static final String GITHUB = "github";
    public static GithubOauth2 getOAuthTool(String company){
        switch(company){
            case QQ:
               // return new QQOauth2();
            case SINA:
               // return new SinaOAuth2();
            case WECHAT:
               // return new WechatOAuth2();
            case GITHUB:
                return new GithubOauth2();
        }
        return null;
    }

    public static String getAccessToken(String code){
        return null;
    }

    public static String getUser(String access_token){
        return null;
    }

    public static String getAuthUrl(){
        return null;
    }



}
