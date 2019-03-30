package com.isleqi.graduationproject.domain;

public class Administrator {
    private Integer userId;

    public Administrator(Integer userId) {
        this.userId = userId;
    }

    public Administrator() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}