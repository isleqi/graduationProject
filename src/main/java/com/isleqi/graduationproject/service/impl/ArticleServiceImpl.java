package com.isleqi.graduationproject.service.impl;

import com.github.pagehelper.PageHelper;
import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.dao.mappers.ArticleMapper;
import com.isleqi.graduationproject.dao.mappers.UserFollowArticleMapper;
import com.isleqi.graduationproject.dao.mappers.UserValueMapper;
import com.isleqi.graduationproject.domain.Article;
import com.isleqi.graduationproject.domain.UserFollowArticle;
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
    @Autowired
    UserValueMapper userValueMapper;
    @Autowired
    UserFollowArticleMapper userFollowArticleMapper;

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
    public int getUserValue(Integer userId){
      return  userValueMapper.selectByPrimaryKey(userId).getValue();
    }

    public Boolean hasFollowArticle(Integer articleId,Integer userId){
        UserFollowArticle userFollowArticle=new UserFollowArticle();
        userFollowArticle.setArticleId(articleId);
        userFollowArticle.setUserId(userId);
        UserFollowArticle data = userFollowArticleMapper.selectByPrimaryKey(userFollowArticle);
        if(data==null)
            return false;
        return true;
    }

    @Override
    public ArticleVo getArticleById(Integer articleId,Integer userId) {
        ArticleVo item=articleMapper.selectByPrimaryKey(articleId);
        Boolean data=hasFollowArticle(articleId,userId);
        item.setHasPay(data);
        if(userId==item.getUserId())
            item.setMyArticle(true);
        else
            item.setMyArticle(false);
        item.setHasPay(data);

        return item;
    }

    @Override
    public PageBean<ArticleVo> getArticleList(int userId,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleVo> list=null;
        try{
            list=articleMapper.selectArticleList();
            for (ArticleVo item:list) {
             Boolean data=hasFollowArticle(item.getArticleId(),userId);
             if(userId==item.getUserId())
                 item.setMyArticle(true);
             else
                 item.setMyArticle(false);
             item.setHasPay(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<ArticleVo> info = new PageBean<>(list);

        return info;
    }

    @Override
    public PageBean<ArticleVo> getArticleListById(int userId,int _userId,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleVo> list=null;
        try{
            list=articleMapper.selectArticleListByUserId(_userId);
            for (ArticleVo item:list) {
                Boolean data=hasFollowArticle(item.getArticleId(),userId);
                if(userId==item.getUserId())
                    item.setMyArticle(true);
                else
                    item.setMyArticle(false);
                item.setHasPay(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<ArticleVo> info = new PageBean<>(list);

        return info;
    }


    @Override
    public PageBean<ArticleVo> getMyArticleList(int userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleVo> list=null;
        try{
            list=articleMapper.selectMyArticleList(userId);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<ArticleVo> info = new PageBean<>(list);

        return info;
    }

    @Override
    public PageBean<ArticleVo> getListBySearch(int pageNum, int pageSize, String str,Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleVo> list=null;
        try{
            list=articleMapper.selectListByStr(str);
            for (ArticleVo item:list) {
                Boolean data=hasFollowArticle(item.getArticleId(),userId);
                if(userId==item.getUserId())
                    item.setMyArticle(true);
                else
                    item.setMyArticle(false);
                item.setHasPay(data);
            }
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
            for (ArticleVo item:list) {
                Boolean data=hasFollowArticle(item.getArticleId(),userId);
                if(userId==item.getUserId())
                    item.setMyArticle(true);
                else
                    item.setMyArticle(false);
                item.setHasPay(data);
            }
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
    public void updateArticle(Article article) {
          articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    @Transactional
    public void payForArticle(Integer userId,Integer useredId,Integer articleId, Integer value) {
        userValueMapper.updateValueSub(userId, value);
        userValueMapper.updateValueAdd(useredId, value);
        UserFollowArticle userFollowArticle=new UserFollowArticle();
        userFollowArticle.setUserId(userId);
        userFollowArticle.setValue(value);
        userFollowArticle.setArticleId(articleId);
        userFollowArticleMapper.insertSelective(userFollowArticle);
        articleMapper.addBrowser(articleId);
    }
}
