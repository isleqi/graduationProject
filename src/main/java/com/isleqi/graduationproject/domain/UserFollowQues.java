package com.isleqi.graduationproject.domain;

public class UserFollowQues {
    private Integer id;

    private Integer userId;

    private Integer quesId;

    public UserFollowQues(Integer id, Integer userId, Integer quesId) {
        this.id = id;
        this.userId = userId;
        this.quesId = quesId;
    }

    public UserFollowQues() {
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

    public Integer getQuesId() {
        return quesId;
    }

    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }
}