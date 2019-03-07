package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.UserPay;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserPayMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(UserPay record);

    int insertSelective(UserPay record);

    UserPay selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(UserPay record);

    int updateByPrimaryKey(UserPay record);

}