package com.isleqi.graduationproject.service.impl;

import com.github.pagehelper.PageHelper;
import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.dao.mappers.ArticleCommentMapper;
import com.isleqi.graduationproject.dao.mappers.ArticleMapper;
import com.isleqi.graduationproject.dao.mappers.ArticleReplyMapper;
import com.isleqi.graduationproject.domain.ArticleComment;
import com.isleqi.graduationproject.domain.ArticleReply;
import com.isleqi.graduationproject.domain.vo.ArticleCommentVo;
import com.isleqi.graduationproject.domain.vo.ArticleReplyVo;
import com.isleqi.graduationproject.service.ArticleCommentAndReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("articleCommentAndReplyService")
public class ArticleCommentAndReplyServiceImpl implements ArticleCommentAndReplyService {
    @Autowired
    ArticleCommentMapper articleCommentMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleReplyMapper articleReplyMapper;

    @Override
    public void addComment(ArticleComment articleComment) {
        articleCommentMapper.insertSelective(articleComment);
        articleMapper.addCommentNum(articleComment.getArticleId());
    }

    @Override
    public void addReply(ArticleReply articleReply) {
        articleReplyMapper.insertSelective(articleReply);
        articleCommentMapper.addReplyNum(articleReply.getArticleCommentId());
    }

    @Override
    public ArticleCommentVo getCommentById(Integer id) {
        return articleCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<ArticleCommentVo> getCommentByArticleId(Integer articleId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<ArticleCommentVo> list=null;

        try {
            list = (ArrayList<ArticleCommentVo>) articleCommentMapper.selectByArticleId(articleId);;

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<ArticleCommentVo> info = new PageBean<>(list);

        return info;
    }

    @Override
    public ArticleReplyVo getReplyById(Integer id) {
        return articleReplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<ArticleReplyVo> getReplyListByCommnetId(Integer commentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<ArticleReplyVo> list=null;

        try {
            list = (ArrayList<ArticleReplyVo>) articleReplyMapper.selectListByCommentId(commentId);

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<ArticleReplyVo> info = new PageBean<>(list);

        return info;
    }

    @Override
    public PageBean<ArticleReplyVo> getReplyListByReplyId(Integer replyId, Integer pageNum, Integer pageSize) {
        return null;
    }
}
