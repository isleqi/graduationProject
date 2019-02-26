package com.isleqi.graduationproject.service;

import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.domain.Article;
import com.isleqi.graduationproject.domain.ArticleComment;
import com.isleqi.graduationproject.domain.ArticleReply;
import com.isleqi.graduationproject.domain.vo.ArticleCommentVo;
import com.isleqi.graduationproject.domain.vo.ArticleReplyVo;

public interface ArticleCommentAndReplyService {
    void addComment(ArticleComment articleComment);

    void addReply(ArticleReply articleReply);

    ArticleCommentVo getCommentById(Integer id);

    PageBean<ArticleCommentVo> getCommentByArticleId(Integer articleId, Integer pageNum, Integer pageSize);

    ArticleReplyVo getReplyById(Integer id);

    PageBean<ArticleReplyVo> getReplyListByCommnetId(Integer commentId,Integer pageNum,Integer pageSize);

    PageBean<ArticleReplyVo> getReplyListByReplyId(Integer replyId,Integer pageNum,Integer pageSize);
}
