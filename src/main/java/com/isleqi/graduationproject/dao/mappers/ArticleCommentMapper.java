package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.ArticleComment;
import com.isleqi.graduationproject.domain.vo.ArticleCommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ArticleCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    ArticleCommentVo selectByPrimaryKey(Integer id);

    List<ArticleCommentVo> selectByArticleId(Integer ansId);

    int updateByPrimaryKeySelective(ArticleComment record);

    int updateByPrimaryKeyWithBLOBs(ArticleComment record);

    int updateByPrimaryKey(ArticleComment record);

    void addReplyNum(Integer id);
}