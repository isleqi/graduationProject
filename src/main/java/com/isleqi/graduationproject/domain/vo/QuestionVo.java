package com.isleqi.graduationproject.domain.vo;

import com.isleqi.graduationproject.domain.Answer;
import com.isleqi.graduationproject.domain.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestionVo implements Serializable {
    private Integer id;

    private String quesTitle;

    private String quesDes;

    private Integer followNum;

    private AnswerVo answerVo;

    private List<Tag> tagList;

    private  Boolean follow;



}
