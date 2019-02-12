package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer id);

    List<Tag> selectBySearch(String str);

    List<Tag> selectByHot();

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}