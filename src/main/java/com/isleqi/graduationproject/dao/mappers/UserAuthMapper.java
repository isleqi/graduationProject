package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.UserAuth;
import com.isleqi.graduationproject.domain.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuth record);

    int insertSelective(UserAuth record);

    UserAuth selectByPrimaryKey(Integer id);

    UserAuth selectByIdentifier(String identifier);

    UserInfoVo selectUserInfoByIdentifier(String identifier);

    int updateByPrimaryKeySelective(UserAuth record);

    int updateByPrimaryKey(UserAuth record);
}