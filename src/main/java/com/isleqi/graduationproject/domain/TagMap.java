package com.isleqi.graduationproject.domain;

public class TagMap {
    private Integer id;

    private Integer tagId;

    private Integer articleId;

    public TagMap(Integer id, Integer tagId, Integer articleId) {
        this.id = id;
        this.tagId = tagId;
        this.articleId = articleId;
    }

    public TagMap() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}