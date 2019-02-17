package com.isleqi.graduationproject.service.impl;

import com.github.pagehelper.PageHelper;
import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.dao.mappers.AnsCommentMapper;
import com.isleqi.graduationproject.dao.mappers.AnsReplyMapper;
import com.isleqi.graduationproject.domain.AnsComment;
import com.isleqi.graduationproject.domain.AnsReply;
import com.isleqi.graduationproject.domain.vo.AnsCommentVo;
import com.isleqi.graduationproject.domain.vo.AnsReplyVo;
import com.isleqi.graduationproject.service.CommentAndReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("commentAndReplyService")
public class CommentAndReplyServiceImpl implements CommentAndReplyService {
    @Autowired
    AnsCommentMapper ansCommentMapper;
    @Autowired
    AnsReplyMapper ansReplyMapper;



    @Override
    public void addComment(AnsComment ansComment) {
        ansCommentMapper.insertSelective(ansComment);

    }


    @Override
    public void addReply(AnsReply ansReply) {
        ansReplyMapper.insertSelective(ansReply);
    }

    @Override
    public AnsCommentVo getCommentById(Integer id) {
        return ansCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<AnsCommentVo> getCommentByAnsId(Integer ansId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<AnsCommentVo> list=null;

        try {
            list = (ArrayList<AnsCommentVo>) ansCommentMapper.selectByAnsId(ansId);;

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<AnsCommentVo> info = new PageBean<>(list);

        return info;

    }

    @Override
    public AnsReplyVo getReplyById(Integer id) {
        return ansReplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<AnsReplyVo> getReplyListByCommnetId(Integer commentId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<AnsReplyVo> list=null;

        try {
            list = (ArrayList<AnsReplyVo>) ansReplyMapper.selectListByCommentId(commentId);

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<AnsReplyVo> info = new PageBean<>(list);

        return info;

    }

    @Override
    public PageBean<AnsReplyVo> getReplyListByReplyId(Integer replyId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<AnsReplyVo> list=null;

        try {
            list = (ArrayList<AnsReplyVo>)ansReplyMapper.selectByReplyId(replyId);

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<AnsReplyVo> info = new PageBean<>(list);

        return info;


    }


}
