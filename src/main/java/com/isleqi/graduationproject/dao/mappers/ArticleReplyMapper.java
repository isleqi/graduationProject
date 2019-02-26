package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.ArticleReply;
import com.isleqi.graduationproject.domain.vo.ArticleReplyVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ArticleReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleReply record);

    int insertSelective(ArticleReply record);

    ArticleReplyVo selectByPrimaryKey(Integer id);

    List<ArticleReplyVo> selectListByCommentId(Integer commentId);

    List<ArticleReplyVo>  selectByReplyId(Integer replyId);

    int updateByPrimaryKeySelective(ArticleReply record);

    int updateByPrimaryKey(ArticleReply record);
}