package com.isleqi.graduationproject.dao;

import com.isleqi.graduationproject.component.common.dao.BaseDao;
import com.isleqi.graduationproject.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Mapper
public interface UserDao extends BaseDao{

    int insert(@Param("user") User user);

    User findById (@Param("id") int Id);


}
