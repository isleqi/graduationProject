package com.isleqi.graduationproject.service;

import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.domain.AnsComment;
import com.isleqi.graduationproject.domain.Answer;
import com.isleqi.graduationproject.domain.vo.AnsCommentVo;
import com.isleqi.graduationproject.domain.vo.AnswerParamVo;
import com.isleqi.graduationproject.domain.vo.AnswerVo;
import com.isleqi.graduationproject.domain.vo.QuestionVo;

import java.util.List;

public interface AnswerService {

    AnswerVo getByAnsId(Integer ansId);

    AnswerVo getByQuesId(Integer quesId);

    PageBean<AnswerVo> getListByQuesId(int pageNum,int pageSize,Integer quesId);

    PageBean<AnswerVo> getListByUserId(int pageNum,int pageSize,Integer userId);

    PageBean<AnswerVo> getFollowList(int pageNum,int pageSize,Integer userId);

    PageBean<AnswerVo> getListBySearch(int pageNum,int pageSize,String str);


    int addAnswer(AnswerParamVo answerParamVo);

    int delAnswer(Integer ansId,Integer quesId);

    void updateAnswer(Answer answer);

    void updateAnswer(String content,Integer ansId);



}
