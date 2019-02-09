package com.isleqi.graduationproject.service.impl;

import com.isleqi.graduationproject.dao.mappers.AnswerMapper;
import com.isleqi.graduationproject.domain.Answer;
import com.isleqi.graduationproject.domain.User;
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
    public Answer getByQuesId(Integer quesId) {
      return  answerMapper.selectByQuesId(quesId);
    }

    @Override
    public List<Answer> getListByQuesId(Integer quesId) {
        return null;
    }

    @Override
    public int addAnswer(Answer answer) {
     int ansId = answerMapper.insertSelective(answer);
     return ansId;
    }

    @Override
    public void updateAnswer(Answer answer) {
        answerMapper.updateByPrimaryKeySelective(answer);
    }
}
