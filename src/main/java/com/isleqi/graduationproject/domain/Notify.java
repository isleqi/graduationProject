package com.isleqi.graduationproject.domain;

import java.util.Date;

public class Notify {
    private Integer id;

    private String content;

    private String type;

    private Integer targetId;

    private Integer targetType;

    private Integer sendUserId;

    private Date createTime;

    public Notify(Integer id, String content, String type, Integer targetId, Integer targetType, Integer sendUserId, Date createTime) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.targetId = targetId;
        this.targetType = targetType;
        this.sendUserId = sendUserId;
        this.createTime = createTime;
    }

    public Notify() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}