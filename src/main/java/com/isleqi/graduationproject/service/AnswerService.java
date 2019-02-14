package com.isleqi.graduationproject.service;

import com.isleqi.graduationproject.domain.Answer;
import com.isleqi.graduationproject.domain.vo.AnswerVo;

import java.util.List;

public interface AnswerService {

    AnswerVo getByQuesId(Integer quesId);

    List<AnswerVo> getListByQuesId(Integer quesId);

    int addAnswer(Answer answer);

    void updateAnswer(Answer answer);

}
