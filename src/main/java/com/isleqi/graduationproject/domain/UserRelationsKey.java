package com.isleqi.graduationproject.domain;

public class UserRelationsKey {
    private Integer userId;

    private Integer followId;

    public UserRelationsKey(Integer userId, Integer followId) {
        this.userId = userId;
        this.followId = followId;
    }

    public UserRelationsKey() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFollowId() {
        return followId;
    }

    public void setFollowId(Integer followId) {
        this.followId = followId;
    }
}