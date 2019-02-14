package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.UserFollowQues;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserFollowQuesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFollowQues record);

    int insertSelective(UserFollowQues record);

    UserFollowQues selectByPrimaryKey(Integer id);

    UserFollowQues selectByUserIdAndQusId(@Param("quesId") Integer quesId,@Param("userId") Integer userId);

    int updateByPrimaryKeySelective(UserFollowQues record);

    int updateByPrimaryKey(UserFollowQues record);
}