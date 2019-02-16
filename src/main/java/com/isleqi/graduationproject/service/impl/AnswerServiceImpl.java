package com.isleqi.graduationproject.service.impl;

import com.isleqi.graduationproject.dao.mappers.AnswerMapper;
import com.isleqi.graduationproject.domain.Answer;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.domain.vo.AnswerParamVo;
import com.isleqi.graduationproject.domain.vo.AnswerVo;
import com.isleqi.graduationproject.service.AnswerService;
import com.isleqi.graduationproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service("answerService")
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    UserService userService;

    @Override
    public AnswerVo getByAnsId(Integer ansId) {
        return answerMapper.selectAnswer(ansId);
    }

    @Override
    public AnswerVo getByQuesId(Integer quesId) {
      return  answerMapper.selectByQuesId(quesId);
    }

    @Override
    public List<AnswerVo> getListByQuesId(Integer quesId) {

        return answerMapper.selectListByQuesId(quesId);
    }

    @Override
    public List<AnswerVo> getListByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<AnswerVo> getFollowList(Integer userId) {
        return answerMapper.selectFollowList(userId);
    }


    @Override
    public int addAnswer(AnswerParamVo answerParamVo) {
        Answer answer =new Answer();
        answer.setQuesId(answerParamVo.getQuesId());
        answer.setUserId(answerParamVo.getUserId());
        answer.setAnsContent(answerParamVo.getAnsContent());
       answerMapper.insertSelective(answer);
        return answer.getAnsId();
    }

    @Override
    public void updateAnswer(Answer answer) {
        answerMapper.updateByPrimaryKeySelective(answer);
    }
}
