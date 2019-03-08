package com.isleqi.graduationproject.service.impl;

import com.github.pagehelper.PageHelper;
import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.dao.mappers.AnsCommentMapper;
import com.isleqi.graduationproject.dao.mappers.AnswerMapper;
import com.isleqi.graduationproject.dao.mappers.QuestionMapper;
import com.isleqi.graduationproject.domain.AnsComment;
import com.isleqi.graduationproject.domain.Answer;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.domain.vo.AnsCommentVo;
import com.isleqi.graduationproject.domain.vo.AnswerParamVo;
import com.isleqi.graduationproject.domain.vo.AnswerVo;
import com.isleqi.graduationproject.domain.vo.QuestionVo;
import com.isleqi.graduationproject.service.AnswerService;
import com.isleqi.graduationproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service("answerService")
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserService userService;


    @Override
    public AnswerVo getByAnsId(Integer ansId) {
        return answerMapper.selectAnswer(ansId);
    }

    @Override
    public AnswerVo getByQuesId(Integer quesId) {
      return  answerMapper.selectByQuesId(quesId);
    }

    @Override
    public PageBean<AnswerVo> getListByQuesId(int pageNum,int pageSize,Integer quesId) {
        PageHelper.startPage(pageNum, pageSize);
        List<AnswerVo> list=null;
        try{
            list=answerMapper.selectListByQuesId(quesId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<AnswerVo> info = new PageBean<>(list);

        return info;

    }

    @Override
    public PageBean<AnswerVo> getListByUserId(int pageNum,int pageSize,Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<AnswerVo> list=null;
        try{
            list=answerMapper.selectListByUserId(userId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<AnswerVo> info = new PageBean<>(list);

        return info;    }

    @Override
    public PageBean<AnswerVo> getFollowList(int pageNum,int pageSize,Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<AnswerVo> list=null;
        try{
            list=answerMapper.selectFollowList(userId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<AnswerVo> info = new PageBean<>(list);

        return info;
    }

    @Override
    public PageBean<AnswerVo> getListBySearch(int pageNum, int pageSize, String str) {
        PageHelper.startPage(pageNum, pageSize);
        List<AnswerVo> list=null;
        try{
            list=answerMapper.selectListByStr(str);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<AnswerVo> info = new PageBean<>(list);

        return info;
    }


    @Override
    @Transactional
    public int addAnswer(AnswerParamVo answerParamVo) {
        Answer answer =new Answer();
        answer.setQuesId(answerParamVo.getQuesId());
        answer.setUserId(answerParamVo.getUserId());
        answer.setAnsContent(answerParamVo.getAnsContent());
       answerMapper.insertSelective(answer);
       questionMapper.addAnswerNum(answerParamVo.getQuesId());
        return answer.getAnsId();
    }

    @Override
    @Transactional
    public int delAnswer(Integer ansId,Integer quesId) {
      questionMapper.subAnswerNum(quesId);
      return answerMapper.deleteByPrimaryKey(ansId);

    }

    @Override
    @Transactional
    public void updateAnswer(String content,Integer ansId) {
        Answer answer =new Answer();
        answer.setAnsId(ansId);
        answer.setAnsContent(content);
        answerMapper.updateByPrimaryKeySelective(answer);
    }

    @Override
    public void updateAnswer(Answer answer) {
        answerMapper.updateByPrimaryKeySelective(answer);
    }


}
