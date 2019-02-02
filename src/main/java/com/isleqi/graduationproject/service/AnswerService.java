package com.isleqi.graduationproject.service;

import com.isleqi.graduationproject.domain.Answer;

import java.util.List;

public interface AnswerService {

    Answer getByQuesId(Integer quesId);

    List<Answer> getListByQuesId(Integer quesId);

}
