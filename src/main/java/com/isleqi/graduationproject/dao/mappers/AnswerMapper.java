package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.Answer;
import com.isleqi.graduationproject.domain.vo.AnswerVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AnswerMapper {
    int deleteByPrimaryKey(Integer ansId);

    int insert(Answer record);

    int insertSelective(Answer record);

    AnswerVo selectByPrimaryKey(Integer ansId);

    AnswerVo selectByQuesId(Integer quesId);

    AnswerVo selectListByUserId(Integer userId);

    List<AnswerVo> selectListByQuesId(Integer quesId);

    List<AnswerVo> selectFollowList(Integer userId);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKeyWithBLOBs(Answer record);

    int updateByPrimaryKey(Answer record);
}