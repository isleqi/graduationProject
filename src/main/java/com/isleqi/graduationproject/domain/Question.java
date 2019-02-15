package com.isleqi.graduationproject.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Question {
    private Integer id;

    private String quesTitle;

    private String quesDes;

    private Integer quesUserId;

    private Integer quesdUserId;

    private Integer followNum;

    private Date createTime;

    private Date editTime;

    public Question(Integer id,String  quesTitle,String quesDes, Integer quesUserId, Integer quesdUserId){
        this.id=id;
        this.quesTitle=quesTitle;
        this.quesDes=quesDes;
        this.quesUserId=quesUserId;
        this.quesdUserId=quesdUserId;

    }

    public Question(){
        super();
    }



}