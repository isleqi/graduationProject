package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.UserRelationsKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@Mapper
public interface UserRelationsMapper {

    UserRelationsKey selectByPrimaryKey(@Param("userId") Integer userId,@Param("useredId") Integer useredId);

    List<Integer> getFollowIds(Integer userId);

    List<Integer> getFanIds(Integer userId);

    int deleteByPrimaryKey(UserRelationsKey key);

    int insert(UserRelationsKey record);

    int insertSelective(UserRelationsKey record);
}