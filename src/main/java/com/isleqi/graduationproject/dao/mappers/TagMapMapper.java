package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.Question;
import com.isleqi.graduationproject.domain.Tag;
import com.isleqi.graduationproject.domain.TagMap;
import com.isleqi.graduationproject.domain.vo.TagVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface TagMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TagMap record);

    int insertSelective(TagMap record);

    int insertBatch(List<TagMap> lsit);

    TagMap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TagMap record);

    int updateByPrimaryKey(TagMap record);

    List<Tag> selectAllTag(Integer quesId);

    List<TagVo> selectQuestionByTagId(Integer tagId);
}