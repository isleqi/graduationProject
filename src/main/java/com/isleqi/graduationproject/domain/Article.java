package com.isleqi.graduationproject.domain;

import java.util.Date;

public class Article {
    private Integer articleId;

    private Integer userId;

    private String articleTitle;

    private Integer type;

    private Integer value;

    private Integer likeNum;

    private Integer commentNum;

    private Integer browse;

    private Date creatTime;

    private Date editTime;

    private String articleContent;

    public Article(Integer articleId, Integer userId, String articleTitle, Integer type, Integer value, Integer likeNum, Integer commentNum, Integer browse, Date creatTime, Date editTime, String articleContent) {
        this.articleId = articleId;
        this.userId = userId;
        this.articleTitle = articleTitle;
        this.type = type;
        this.value = value;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
        this.browse = browse;
        this.creatTime = creatTime;
        this.editTime = editTime;
        this.articleContent = articleContent;
    }

    public Article() {
        super();
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

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
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

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }
}