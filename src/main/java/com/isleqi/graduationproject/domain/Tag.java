package com.isleqi.graduationproject.domain;

public class Tag {
    private Integer id;

    private String tagName;

    private Integer num;

    public Tag(Integer id, String tagName, Integer num) {
        this.id = id;
        this.tagName = tagName;
        this.num = num;
    }

    public Tag() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}