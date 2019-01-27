package com.isleqi.graduationproject.service;

import com.isleqi.graduationproject.domain.Question;

import java.util.List;

public interface QuestionService {

    void insert(Question question);

    void update(Question question);

   List<Question> selectByTagId(int TagId);

   List<Question> selectBySearch(String str);


}
