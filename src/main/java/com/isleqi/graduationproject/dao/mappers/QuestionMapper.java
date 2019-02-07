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

    List<QuestionVo> selectByTagId(Integer tagId);

    List<QuestionVo> selectQuesWithAns();

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKeyWithBLOBs(Question record);

    int updateByPrimaryKey(Question record);
}