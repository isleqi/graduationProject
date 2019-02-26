package com.isleqi.graduationproject.domain;

import java.util.Date;

public class ArticleComment {
    private Integer id;

    private Integer articleId;

    private Integer userId;

    private Integer likeNum;

    private Date creatTime;

    private Integer replyNum;

    private String commentContent;

    public ArticleComment(Integer id, Integer articleId, Integer userId, Integer likeNum, Date creatTime, Integer replyNum, String commentContent) {
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
        this.likeNum = likeNum;
        this.creatTime = creatTime;
        this.replyNum = replyNum;
        this.commentContent = commentContent;
    }

    public ArticleComment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }
}