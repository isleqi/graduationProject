package com.isleqi.graduationproject.domain;

public class Question {
    private Integer id;

    private String quesTitle;

    private String quesDes;

    private Integer quesUserId;

    private Integer quesdUserId;

    private String quesTagIds;

    public Question(Integer id, String quesTitle, String quesDes, Integer quesUserId, Integer quesdUserId, String quesTagIds) {
        this.id = id;
        this.quesTitle = quesTitle;
        this.quesDes = quesDes;
        this.quesUserId = quesUserId;
        this.quesdUserId = quesdUserId;
        this.quesTagIds = quesTagIds;
    }

    public Question() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuesTitle() {
        return quesTitle;
    }

    public void setQuesTitle(String quesTitle) {
        this.quesTitle = quesTitle == null ? null : quesTitle.trim();
    }

    public String getQuesDes() {
        return quesDes;
    }

    public void setQuesDes(String quesDes) {
        this.quesDes = quesDes == null ? null : quesDes.trim();
    }

    public Integer getQuesUserId() {
        return quesUserId;
    }

    public void setQuesUserId(Integer quesUserId) {
        this.quesUserId = quesUserId;
    }

    public Integer getQuesdUserId() {
        return quesdUserId;
    }

    public void setQuesdUserId(Integer quesdUserId) {
        this.quesdUserId = quesdUserId;
    }

    public String getQuesTagIds() {
        return quesTagIds;
    }

    public void setQuesTagIds(String quesTagIds) {
        this.quesTagIds = quesTagIds == null ? null : quesTagIds.trim();
    }
}