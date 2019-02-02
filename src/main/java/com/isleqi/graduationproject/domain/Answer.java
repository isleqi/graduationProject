package com.isleqi.graduationproject.domain;

import java.util.Date;

public class Answer {
    private Integer ansId;

    private Integer quesId;

    private Integer userId;

    private Integer likeNum;

    private Integer commentNum;

    private Date creatTime;

    private Date editTime;

    private String ansContent;

    public Answer(Integer ansId, Integer quesId, Integer userId, Integer likeNum, Integer commentNum, Date creatTime, Date editTime, String ansContent) {
        this.ansId = ansId;
        this.quesId = quesId;
        this.userId = userId;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
        this.creatTime = creatTime;
        this.editTime = editTime;
        this.ansContent = ansContent;
    }

    public Answer() {
        super();
    }

    public Integer getAnsId() {
        return ansId;
    }

    public void setAnsId(Integer ansId) {
        this.ansId = ansId;
    }

    public Integer getQuesId() {
        return quesId;
    }

    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getAnsContent() {
        return ansContent;
    }

    public void setAnsContent(String ansContent) {
        this.ansContent = ansContent == null ? null : ansContent.trim();
    }
}