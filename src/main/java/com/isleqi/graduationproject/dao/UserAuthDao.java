package com.isleqi.graduationproject.dao;

import com.isleqi.graduationproject.domain.UserAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserAuthDao {

    int insert (@Param("userAuth") UserAuth userAuth);

    UserAuth findById(@Param("id") int id);

}
