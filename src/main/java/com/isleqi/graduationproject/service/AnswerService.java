package com.isleqi.graduationproject.service;

import com.isleqi.graduationproject.domain.Answer;
import com.isleqi.graduationproject.domain.vo.AnswerParamVo;
import com.isleqi.graduationproject.domain.vo.AnswerVo;

import java.util.List;

public interface AnswerService {

    AnswerVo getByAnsId(Integer ansId);

    AnswerVo getByQuesId(Integer quesId);

    List<AnswerVo> getListByQuesId(Integer quesId);

    List<AnswerVo> getListByUserId(Integer userId);

    List<AnswerVo> getFollowList(Integer userId);

    int addAnswer(AnswerParamVo answerParamVo);

    void updateAnswer(Answer answer);

}
