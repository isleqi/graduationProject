package com.isleqi.graduationproject.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionParamVo implements Serializable {

    private String quesTitle;

    private String quesDes;

    private Integer quesUserId;

    private Integer quesdUserId;

    private Integer[] tagIds;



}
