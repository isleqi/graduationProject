package com.isleqi.graduationproject.dao.mappers;

import com.isleqi.graduationproject.domain.Article;
import com.isleqi.graduationproject.domain.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    ArticleVo selectByPrimaryKey(Integer articleId);

    List<ArticleVo> selectArticleList();

    List<ArticleVo> selectFollowUserArticle(Integer userId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    int addLikeNum(Integer id);

    int subLikeNum(Integer id);

    int addCommentNum(Integer id);

    int addBrowser(Integer id);

}