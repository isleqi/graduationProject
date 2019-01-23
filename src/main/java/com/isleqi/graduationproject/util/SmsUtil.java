package com.isleqi.graduationproject.util;


import com.alibaba.fastjson.JSONObject;
import com.isleqi.graduationproject.component.common.domain.Response;

import com.isleqi.graduationproject.component.common.domain.Sms;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


public class SmsUtil {
    /**
     * @Description: 共同部分(单条短信发送)
     * @author xieyc
     * @date 2018年2月6日 下午6:31:16
     */
    public static Response aliPushCom(String smsParamString,String recNum,String smsTemplateCode) {
        Response result = new Response();
        String smsType="normal";
        String smsFreeSignName="滨惠商城";//短信签名
        String url = "http://gw.api.taobao.com/router/rest";
        String appkey = "23441256";
        String secret = "1a690d0375fc604dc28ea6d881e0c006";
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType(smsType);
        req.setSmsFreeSignName(smsFreeSignName);
        req.setSmsParamString(smsParamString);
        req.setRecNum(recNum);
        req.setSmsTemplateCode(smsTemplateCode);
        try {
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            JSONObject jsonObject = JSONObject.parseObject(rsp.getBody());
            if (jsonObject.containsKey("error_response")) {// 存在异常
                JSONObject bodyJson = (JSONObject) jsonObject.get("error_response");
                if (bodyJson != null) {
                    System.out.println("err-->" + bodyJson.get("sub_msg").toString());
                    result.setCode("400");
                    result.setMessage(bodyJson.get("sub_msg").toString());
                }
            } else {
                System.out.println("success");
                result.setCode("200");
                result.setMessage("短信发送成功");
            }
        } catch (Exception e) {
            System.out.println("track");
            e.printStackTrace();
            result.setCode("400");
            result.setMessage("短信发送异常:" + e.getMessage());
        }

        return result;
    }
    /**
     *
     * @Description: 滨惠商城用户注册短信：【滨惠商城】亲爱的会员，您的手机注册验证码为${code}，10分钟内有效；如非本人操作，请忽略本条短信。
     * @author xieyc
     * @date 2018年2月5日 下午7:37:14
     */
    public static Response aliPushUserReg(Sms sms) {
        JSONObject paramJson = new JSONObject();
        paramJson.put("code", sms.getSmsContent());
        String smsParamString=paramJson.toJSONString();
        String recNum=sms.getPhoneNo();
        String smsTemplateCode="SMS_126361552";
        return  aliPushCom(smsParamString,recNum,smsTemplateCode);
    }



}
