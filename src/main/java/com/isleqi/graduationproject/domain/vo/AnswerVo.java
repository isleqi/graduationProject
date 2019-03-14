package com.isleqi.graduationproject.domain.vo;

import com.isleqi.graduationproject.domain.Question;
import com.isleqi.graduationproject.domain.Tag;
import com.isleqi.graduationproject.domain.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AnswerVo {
    private Integer ansId;

    private Integer quesId;

    private Integer userId;

    private Integer likeNum;

    private Integer commentNum;

    private Date creatTime;

    private Date editTime;

    private String ansContent;

    private User user;

    private Question ques;

    private List<Tag> tagList;

}
