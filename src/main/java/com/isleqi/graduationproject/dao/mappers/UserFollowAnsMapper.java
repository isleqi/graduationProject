package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.UserFollowAns;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserFollowAnsMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByData(@Param("ansId") Integer ansId,@Param("userId") Integer userId);

    int insert(UserFollowAns record);

    int insertSelective(UserFollowAns record);

    UserFollowAns selectByPrimaryKey(Integer id);

    UserFollowAns selectByUserIdAndAnsId(@Param("answerId") Integer answerId,@Param("userId") Integer userId);

    int updateByPrimaryKeySelective(UserFollowAns record);

    int updateByPrimaryKey(UserFollowAns record);
}