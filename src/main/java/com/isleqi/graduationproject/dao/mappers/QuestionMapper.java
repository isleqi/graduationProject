package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.Question;
import com.isleqi.graduationproject.domain.vo.QuestionVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    Question selectBystr(String str);

    List<QuestionVo> selectByTagId(Integer tagId);

    List<QuestionVo> selectByUserId(Integer tagId);

    List<QuestionVo> selectQuesWithAns();

    List<QuestionVo> selectListByStr(String str);

    List<QuestionVo> selectFollowQuesWithAns(Integer userId);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKeyWithBLOBs(Question record);

    int updateByPrimaryKey(Question record);

    int addFollowNum(Integer id);

    int subFollowNum(Integer id);

    int addAnswerNum(Integer id);

    int subAnswerNum(Integer id);
}