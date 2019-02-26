package com.isleqi.graduationproject.service.impl;

import com.github.pagehelper.PageHelper;
import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.dao.mappers.ArticleMapper;
import com.isleqi.graduationproject.domain.Article;
import com.isleqi.graduationproject.domain.vo.ArticleParamVo;
import com.isleqi.graduationproject.domain.vo.ArticleVo;
import com.isleqi.graduationproject.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public PageBean<ArticleVo> getArticleList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleVo> list=null;
        try{
            list=articleMapper.selectArticleList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<ArticleVo> info = new PageBean<>(list);

        return info;
    }

    @Override
    public PageBean<ArticleVo> getFollowUserArticleList(int pageNum, int pageSize, Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleVo> list=null;
        try{
            list=articleMapper.selectFollowUserArticle(userId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<ArticleVo> info = new PageBean<>(list);

        return info;
    }

    @Override
    public int delArticle(Integer articleId) {
        return 0;
    }

    @Override
    public void updateArticle(String content, Integer articleId) {

    }
}
