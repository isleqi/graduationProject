package com.isleqi.graduationproject.domain;

public class UserFollowArticleKey {
    private Integer userId;

    private Integer articleId;

    public UserFollowArticleKey(Integer userId, Integer articleId) {
        this.userId = userId;
        this.articleId = articleId;
    }

    public UserFollowArticleKey() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}