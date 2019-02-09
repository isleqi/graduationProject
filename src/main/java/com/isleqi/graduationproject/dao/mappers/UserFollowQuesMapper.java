package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.UserFollowQues;

public interface UserFollowQuesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFollowQues record);

    int insertSelective(UserFollowQues record);

    UserFollowQues selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFollowQues record);

    int updateByPrimaryKey(UserFollowQues record);
}