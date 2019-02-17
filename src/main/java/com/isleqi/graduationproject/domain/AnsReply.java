package com.isleqi.graduationproject.domain;

import java.util.Date;

public class AnsReply {
    private Integer id;

    private Integer ansCommentId;

    private Integer replyId;

    private Integer replyUserId;

    private Integer replyedUserId;

    private String replyComtent;

    private Integer likeNum;

    private Date creatTime;

    public AnsReply(Integer id, Integer ansCommentId, Integer replyId, Integer replyUserId, Integer replyedUserId, String replyComtent, Integer likeNum, Date creatTime) {
        this.id = id;
        this.ansCommentId = ansCommentId;
        this.replyId = replyId;
        this.replyUserId = replyUserId;
        this.replyedUserId = replyedUserId;
        this.replyComtent = replyComtent;
        this.likeNum = likeNum;
        this.creatTime = creatTime;
    }

    public AnsReply() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnsCommentId() {
        return ansCommentId;
    }

    public void setAnsCommentId(Integer ansCommentId) {
        this.ansCommentId = ansCommentId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Integer getReplyedUserId() {
        return replyedUserId;
    }

    public void setReplyedUserId(Integer replyedUserId) {
        this.replyedUserId = replyedUserId;
    }

    public String getReplyComtent() {
        return replyComtent;
    }

    public void setReplyComtent(String replyComtent) {
        this.replyComtent = replyComtent == null ? null : replyComtent.trim();
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
}