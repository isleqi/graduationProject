package com.isleqi.graduationproject.domain;

import java.util.Date;

public class UserPay {
    private String orderId;

    private Integer userId;

    private Integer price;

    private Date createTime;

    public UserPay(String orderId, Integer userId, Integer price, Date createTime) {
        this.orderId = orderId;
        this.userId = userId;
        this.price = price;
        this.createTime = createTime;
    }

    public UserPay() {
        super();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}