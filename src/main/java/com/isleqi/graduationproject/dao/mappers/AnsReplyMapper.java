package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.AnsReply;
import com.isleqi.graduationproject.domain.vo.AnsReplyVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AnsReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnsReply record);

    int insertSelective(AnsReply record);

    AnsReplyVo selectByPrimaryKey(Integer id);

    List<AnsReplyVo> selectListByCommentId(Integer commentId);

    List<AnsReplyVo>  selectByReplyId(Integer replyId);

    int updateByPrimaryKeySelective(AnsReply record);

    int updateByPrimaryKey(AnsReply record);
}