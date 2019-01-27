package com.isleqi.graduationproject.service.impl;

import com.isleqi.graduationproject.dao.mappers.QuestionMapper;
import com.isleqi.graduationproject.domain.Question;
import com.isleqi.graduationproject.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public void insert(Question question) {
        questionMapper.insert(question);
    }

    @Override
    public void update(Question question) {

    }

    @Override
    public List<Question> selectByTagId(int TagId) {
        return null;
    }

    @Override
    public List<Question> selectBySearch(String str) {
        return null;
    }
}
