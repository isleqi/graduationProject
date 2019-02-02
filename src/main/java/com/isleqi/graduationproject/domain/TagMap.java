package com.isleqi.graduationproject.domain;

public class TagMap {
    private Integer id;

    private Integer tagId;

    private Integer quesId;

    public TagMap(Integer id, Integer tagId, Integer quesId) {
        this.id = id;
        this.tagId = tagId;
        this.quesId = quesId;
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

    public Integer getQuesId() {
        return quesId;
    }

    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }
}