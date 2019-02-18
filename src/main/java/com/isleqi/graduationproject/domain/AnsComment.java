package com.isleqi.graduationproject.domain;

import java.util.Date;

public class AnsComment {
    private Integer id;

    private Integer ansId;

    private Integer userId;

    private Integer likeNum;

    private Date creatTime;

    private String commentContent;

    private Integer replyNum;

    public AnsComment(Integer id, Integer ansId, Integer userId, Integer likeNum, Date creatTime, String commentContent,Integer replyNum) {
        this.id = id;
        this.ansId = ansId;
        this.userId = userId;
        this.likeNum = likeNum;
        this.creatTime = creatTime;
        this.commentContent = commentContent;
        this.replyNum=replyNum;
    }

    public AnsComment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnsId() {
        return ansId;
    }

    public void setAnsId(Integer ansId) {
        this.ansId = ansId;
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

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }
}