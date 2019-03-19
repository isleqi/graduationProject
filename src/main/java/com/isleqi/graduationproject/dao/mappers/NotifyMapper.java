package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.Notify;
import com.isleqi.graduationproject.domain.vo.NotifyVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface NotifyMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteAllByPrimaryKey(List list);


    int insert(Notify record);

    int insertSelective(Notify record);

    Notify selectByPrimaryKey(Integer id);

    List<NotifyVo> selectByUserId(Integer userId);

    List<NotifyVo> selectAllNotReadByUserId(Integer userId);

    int updateByPrimaryKeySelective(Notify record);

    int updateByPrimaryKey(Notify record);
}