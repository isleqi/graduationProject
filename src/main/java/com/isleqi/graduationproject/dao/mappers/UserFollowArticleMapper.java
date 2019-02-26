package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.UserFollowArticle;
import com.isleqi.graduationproject.domain.UserFollowArticleKey;

public interface UserFollowArticleMapper {
    int deleteByPrimaryKey(UserFollowArticleKey key);

    int insert(UserFollowArticle record);

    int insertSelective(UserFollowArticle record);

    UserFollowArticle selectByPrimaryKey(UserFollowArticleKey key);

    int updateByPrimaryKeySelective(UserFollowArticle record);

    int updateByPrimaryKey(UserFollowArticle record);
}