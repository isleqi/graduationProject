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



}