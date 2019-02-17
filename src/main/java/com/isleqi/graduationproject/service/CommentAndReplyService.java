package com.isleqi.graduationproject.service;

import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.domain.AnsComment;
import com.isleqi.graduationproject.domain.AnsReply;
import com.isleqi.graduationproject.domain.vo.AnsCommentVo;
import com.isleqi.graduationproject.domain.vo.AnsReplyVo;

import java.util.List;

public interface CommentAndReplyService {

    void addComment(AnsComment ansComment);



    void addReply(AnsReply ansReply);

    AnsCommentVo getCommentById(Integer id);

    PageBean<AnsCommentVo> getCommentByAnsId(Integer ansId, Integer pageNum, Integer pageSize);

    AnsReplyVo getReplyById(Integer id);

    PageBean<AnsReplyVo> getReplyListByCommnetId(Integer commentId,Integer pageNum,Integer pageSize);

    PageBean<AnsReplyVo> getReplyListByReplyId(Integer replyId,Integer pageNum,Integer pageSize);

}
