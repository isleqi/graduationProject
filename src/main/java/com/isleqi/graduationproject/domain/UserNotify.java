package com.isleqi.graduationproject.domain;

import java.util.Date;

public class UserNotify {
    private Integer id;

    private Integer read;

    private Integer userId;

    private Integer notifyId;

    private Date createTime;

    public UserNotify(Integer id, Integer read, Integer userId, Integer notifyId, Date createTime) {
        this.id = id;
        this.read = read;
        this.userId = userId;
        this.notifyId = notifyId;
        this.createTime = createTime;
    }

    public UserNotify() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Integer notifyId) {
        this.notifyId = notifyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}