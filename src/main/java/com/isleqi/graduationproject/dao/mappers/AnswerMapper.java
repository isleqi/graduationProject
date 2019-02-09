package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AnswerMapper {
    int deleteByPrimaryKey(Integer ansId);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer ansId);

    Answer selectByQuesId(Integer quesId);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKeyWithBLOBs(Answer record);

    int updateByPrimaryKey(Answer record);
}