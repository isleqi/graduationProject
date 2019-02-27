package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.UserValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserValueMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserValue record);

    int insertSelective(UserValue record);

    UserValue selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserValue record);

    int updateByPrimaryKey(UserValue record);

    int updateValue(@Param("userId") Integer userId,@Param("value") Integer value);
}