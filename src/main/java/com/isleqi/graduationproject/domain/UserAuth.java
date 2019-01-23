package com.isleqi.graduationproject.domain;

import java.util.Date;

public class UserAuth {
    private Integer id;

    private Integer userId;

    private String identityType;

    private String identifier;

    private String credential;

    private Date createTime;

    private Date editTime;

    public UserAuth(Integer id, Integer userId, String identityType, String identifier, String credential, Date createTime, Date editTime) {
        this.id = id;
        this.userId = userId;
        this.identityType = identityType;
        this.identifier = identifier;
        this.credential = credential;
        this.createTime = createTime;
        this.editTime = editTime;
    }

    public UserAuth() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType == null ? null : identityType.trim();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier == null ? null : identifier.trim();
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential == null ? null : credential.trim();
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