package com.isleqi.graduationproject.service;

import com.github.pagehelper.PageInfo;
import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.domain.Question;
import com.isleqi.graduationproject.domain.vo.QuestionParamVo;
import com.isleqi.graduationproject.domain.vo.QuestionVo;

import java.util.List;

public interface QuestionService {

    void insert(QuestionParamVo question) throws Exception;

    void update(QuestionParamVo question);

    PageBean<QuestionVo> getByTagId(int pageNum,int pageSize,int tagId);

    PageBean<QuestionVo> getBySearch(int pageNum,int pageSize,String str);

    PageBean<QuestionVo> getQuestionList(int pageNum, int pageSize);




}
