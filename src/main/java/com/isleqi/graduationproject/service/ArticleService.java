package com.isleqi.graduationproject.service;

import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.domain.vo.ArticleParamVo;
import com.isleqi.graduationproject.domain.vo.ArticleVo;

public interface ArticleService {

    int addArticle(ArticleParamVo articleParamVo);

    ArticleVo getArticleById(Integer articleId);

    PageBean<ArticleVo> getArticleList(int pageNum, int pageSize);

    PageBean<ArticleVo> getFollowUserArticleList(int pageNum,int pageSize,Integer userId);

    int delArticle(Integer articleId);

    void updateArticle(String content,Integer articleId);
}
