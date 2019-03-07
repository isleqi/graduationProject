package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.UserFollowQues;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserFollowQuesMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByData(@Param("quesId") Integer quesId,@Param("userId") Integer userId);

    int insert(UserFollowQues record);

    int insertSelective(UserFollowQues record);

    UserFollowQues selectByPrimaryKey(Integer id);

    UserFollowQues selectByUserIdAndQusId(@Param("quesId") Integer quesId,@Param("userId") Integer userId);

    List<Integer> selectByQuesId(@Param("quesId") Integer quesId);

    int updateByPrimaryKeySelective(UserFollowQues record);

    int updateByPrimaryKey(UserFollowQues record);
}