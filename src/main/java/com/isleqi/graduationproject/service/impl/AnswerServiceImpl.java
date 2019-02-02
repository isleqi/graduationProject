package com.isleqi.graduationproject.service.impl;

import com.isleqi.graduationproject.dao.mappers.AnswerMapper;
import com.isleqi.graduationproject.domain.Answer;
import com.isleqi.graduationproject.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service("answerService")
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerMapper answerMapper;

    @Override
    public Answer getByQuesId(Integer quesId) {
      return  answerMapper.selectByQuesId(quesId);
    }

    @Override
    public List<Answer> getListByQuesId(Integer quesId) {
        return null;
    }
}
