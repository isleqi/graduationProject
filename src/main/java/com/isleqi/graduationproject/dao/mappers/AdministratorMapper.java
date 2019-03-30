package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AdministratorMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    Administrator selectByUserId(Integer UserId);
}