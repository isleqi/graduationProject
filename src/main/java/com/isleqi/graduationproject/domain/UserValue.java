package com.isleqi.graduationproject.domain;

public class UserValue {
    private Integer userId;

    private Integer value;

    public UserValue(Integer userId, Integer value) {
        this.userId = userId;
        this.value = value;
    }

    public UserValue() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}