package com.isleqi.graduationproject.component.common.domain;

import java.io.Serializable;
public class Sms implements Serializable{
    private static final long serialVersionUID = 1L;
    private String phoneNo; //电话号码
    private String smsContent;//短信内容

    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getSmsContent() {
        return smsContent;
    }
    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

}