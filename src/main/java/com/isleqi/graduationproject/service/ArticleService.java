package com.isleqi.graduationproject.service;

import com.isleqi.graduationproject.domain.vo.ArticleParamVo;
import com.isleqi.graduationproject.domain.vo.ArticleVo;

public interface ArticleService {

    int addArticle(ArticleParamVo articleParamVo);

    ArticleVo getArticleById(Integer articleId);
}
