package com.isleqi.graduationproject.util;

import org.springframework.beans.factory.annotation.Value;

public class PayUtil {

    @Value("${web.url}")
    static String url;

    static String appId = "XNVrrgVgVcg8EB92E001BC00C5E85DC0";
    static String secretKey = "sNVuuTVTVeTD30F9473B7FF2B3735082";
    static String redirect_URI = url+"/app/user/paySuccess";



}
