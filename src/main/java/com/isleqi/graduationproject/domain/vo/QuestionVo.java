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

    private Answer answer;

    private List<Tag> tagList;



}
