package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.UserNotify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserNotifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserNotify record);

    int insertSelective(UserNotify record);

    UserNotify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserNotify record);

    int updateByPrimaryKey(UserNotify record);

    void hadRead(@Param("userId") Integer userId,@Param("notifyId") Integer notifyId);

    void hadReadAll(@Param("userId") Integer userId);

}