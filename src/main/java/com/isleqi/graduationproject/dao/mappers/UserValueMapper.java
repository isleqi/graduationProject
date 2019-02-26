package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.UserValue;

public interface UserValueMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserValue record);

    int insertSelective(UserValue record);

    UserValue selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserValue record);

    int updateByPrimaryKey(UserValue record);
}