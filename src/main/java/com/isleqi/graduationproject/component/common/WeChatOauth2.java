package com.isleqi.graduationproject.component.common;

import com.isleqi.graduationproject.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class WeChatOauth2 {
    private final static Logger logger = LoggerFactory.getLogger(SinaOauth2.class);

    static String appKey = "wxcc202534aa5de560";
    static String appSecret = "38ff207e40f48135dcd084199331e02d";
    static String redirect_URI = "http://127.0.0.1:8070/app/login/wechatOauth";


    public static String getAccessToken(String code) {

        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +
                appKey +
                "&secret=" +
                appSecret +
                "&code=" +
                code +
                "&grant_type=authorization_code";

        String access_token= HttpClientUtil.doGet(url);
        return access_token;
    }


    public static String getUser(String access_token,String  openId) {
        String url="https://api.weixin.qq.com/sns/userinfo?access_token=" +
                access_token+
                "&openid=" +
                 openId;
        String user=null;
        try{
            user =HttpClientUtil.doGet(url);
        }catch (Exception e){
            logger.debug("获取微信用户信息失败："+e.toString());
        }
        return user;

    }

    public static String getAuthUrl() {
        return "https://open.weixin.qq.com/connect/qrconnect?appid=" +
                appKey +
                "&redirect_uri=" +
                redirect_URI +
                "&response_type=code&scope=snsapi_login&state=2019#wechat_redirect";
    }

}

