package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.TagMap;

public interface TagMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TagMap record);

    int insertSelective(TagMap record);

    TagMap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TagMap record);

    int updateByPrimaryKey(TagMap record);
}