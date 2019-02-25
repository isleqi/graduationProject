package com.isleqi.graduationproject.service.impl;

import com.isleqi.graduationproject.dao.mappers.ArticleMapper;
import com.isleqi.graduationproject.domain.Article;
import com.isleqi.graduationproject.domain.vo.ArticleParamVo;
import com.isleqi.graduationproject.domain.vo.ArticleVo;
import com.isleqi.graduationproject.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public int addArticle(ArticleParamVo articleParamVo) {
        Article article =new Article();
        article.setArticleContent(articleParamVo.getArticleContent());
        article.setUserId(articleParamVo.getUserId());
        article.setArticleTitle(articleParamVo.getArticleTitle());
        article.setValue(articleParamVo.getValue());
        article.setType(articleParamVo.getType());
        articleMapper.insertSelective(article);
        return article.getArticleId();
    }

    @Override
    public ArticleVo getArticleById(Integer articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }
}
