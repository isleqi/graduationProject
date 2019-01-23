package com.isleqi.graduationproject.domain;

import java.util.Date;

public class User {
    private Integer id;

    private String userName;

    private String userDes;

    private String userIconUrl;

    private Date createTime;

    private Date editTime;

    public User(Integer id, String userName, String userDes, String userIconUrl, Date createTime, Date editTime) {
        this.id = id;
        this.userName = userName;
        this.userDes = userDes;
        this.userIconUrl = userIconUrl;
        this.createTime = createTime;
        this.editTime = editTime;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserDes() {
        return userDes;
    }

    public void setUserDes(String userDes) {
        this.userDes = userDes == null ? null : userDes.trim();
    }

    public String getUserIconUrl() {
        return userIconUrl;
    }

    public void setUserIconUrl(String userIconUrl) {
        this.userIconUrl = userIconUrl == null ? null : userIconUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}