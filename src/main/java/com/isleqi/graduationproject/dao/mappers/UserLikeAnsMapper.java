package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.UserLikeAnsKey;
import com.isleqi.graduationproject.domain.UserRelationsKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserLikeAnsMapper {

    UserLikeAnsKey selectByPrimaryKey(@Param("ansId") Integer ansId,@Param("userId") Integer userId);

    int deleteByPrimaryKey(UserLikeAnsKey key);

    int insert(UserLikeAnsKey record);

    int insertSelective(UserLikeAnsKey record);
}