package com.isleqi.graduationproject.domain;

public class UserFollowAns {
    private Integer id;

    private Integer userId;

    private Integer answerId;

    public UserFollowAns(Integer id, Integer userId, Integer answerId) {
        this.id = id;
        this.userId = userId;
        this.answerId = answerId;
    }

    public UserFollowAns() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }
}