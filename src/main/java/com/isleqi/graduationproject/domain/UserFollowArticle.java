package com.isleqi.graduationproject.domain;

public class UserFollowArticle extends UserFollowArticleKey {
    private Integer value;

    public UserFollowArticle(Integer userId, Integer articleId, Integer value) {
        super(userId, articleId);
        this.value = value;
    }

    public UserFollowArticle() {
        super();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}