package com.isleqi.graduationproject.service;

import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.domain.Article;
import com.isleqi.graduationproject.domain.vo.ArticleParamVo;
import com.isleqi.graduationproject.domain.vo.ArticleVo;

public interface ArticleService {

    int addArticle(ArticleParamVo articleParamVo);

    ArticleVo getArticleById(Integer articleId);

    ArticleVo getArticleById(Integer articleId,Integer userId);

    PageBean<ArticleVo> getArticleList(int userId,int pageNum, int pageSize);

    PageBean<ArticleVo> getArticleListById(int userId,int _userId,int pageNum, int pageSize);


    PageBean<ArticleVo> getMyArticleList(int userId,int pageNum, int pageSize);

    PageBean<ArticleVo> getListBySearch(int pageNum,int pageSize,String str,Integer userId);

    PageBean<ArticleVo> getFollowUserArticleList(int pageNum,int pageSize,Integer userId);

    int delArticle(Integer articleId);

    void updateArticle(Article article);

    void payForArticle(Integer userId,Integer useredId,Integer articleId, Integer value);

    public int getUserValue(Integer userId);
}
