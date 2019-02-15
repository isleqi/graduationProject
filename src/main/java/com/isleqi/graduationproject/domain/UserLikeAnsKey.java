package com.isleqi.graduationproject.domain;

public class UserLikeAnsKey {
    private Integer userId;

    private Integer ansId;

    public UserLikeAnsKey(Integer userId, Integer ansId) {
        this.userId = userId;
        this.ansId = ansId;
    }

    public UserLikeAnsKey() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAnsId() {
        return ansId;
    }

    public void setAnsId(Integer ansId) {
        this.ansId = ansId;
    }
}