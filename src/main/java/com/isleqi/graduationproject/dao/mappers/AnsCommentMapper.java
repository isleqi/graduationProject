package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.AnsComment;
import com.isleqi.graduationproject.domain.vo.AnsCommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AnsCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnsComment record);

    int insertSelective(AnsComment record);

    AnsCommentVo selectByPrimaryKey(Integer id);

    List<AnsCommentVo> selectByAnsId(Integer ansId);

    int updateByPrimaryKeySelective(AnsComment record);

    int updateByPrimaryKeyWithBLOBs(AnsComment record);

    int updateByPrimaryKey(AnsComment record);

    void addReplyNum(Integer id);
}