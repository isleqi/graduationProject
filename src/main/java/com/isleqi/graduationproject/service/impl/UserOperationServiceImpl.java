package com.isleqi.graduationproject.service.impl;

import com.isleqi.graduationproject.dao.mappers.*;
import com.isleqi.graduationproject.domain.*;
import com.isleqi.graduationproject.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Transactional
@Service("userOperationService")
public class UserOperationServiceImpl implements UserOperationService {

    @Autowired
    UserFollowQuesMapper userFollowQuesMapper;
    @Autowired
    UserFollowAnsMapper userFollowAnsMapper;
    @Autowired
    UserRelationsMapper userRelationsMapper;
    @Autowired
    UserLikeAnsMapper userLikeAnsMapper;
    @Autowired
    QuestionMapper questionMapper;


    @Override
    public void followQues(Integer quesId, Integer userId) {
        UserFollowQues userFollowQues = new UserFollowQues();
        userFollowQues.setQuesId(quesId);
        userFollowQues.setUserId(userId);
        boolean flag = hasFollowQues(quesId, userId);
        if (!flag) {
            userFollowQuesMapper.insertSelective(userFollowQues);
            questionMapper.updateFollowNum(quesId);
        }
    }

    @Override
    public void followAns(Integer ansId, Integer userId) {
        UserFollowAns userFollowAns = new UserFollowAns();
        userFollowAns.setAnswerId(ansId);
        userFollowAns.setUserId(userId);
        boolean flag = hasFollowAns(ansId, userId);
        if (!flag) {
            userFollowAnsMapper.insertSelective(userFollowAns);
        }
    }

    @Override
    public void followUser(Integer userId, Integer useredId) {
        UserRelationsKey userRelationsKey = new UserRelationsKey();
        userRelationsKey.setUserId(userId);
        userRelationsKey.setFollowId(useredId);
        boolean flag = hasFollowUser(userId, useredId);
        if (!flag) {
            userRelationsMapper.insertSelective(userRelationsKey);
        }

    }

    @Override
    public void cancelFollowUser(Integer userId, Integer useredId) {
        UserRelationsKey userRelationsKey = new UserRelationsKey();
        userRelationsKey.setUserId(userId);
        userRelationsKey.setFollowId(useredId);
        userRelationsMapper.deleteByPrimaryKey(userRelationsKey);
    }

    @Override
    public void cancelFollowAns(Integer ansId, Integer userId) {
        UserFollowAns userFollowAns = new UserFollowAns();
        userFollowAns.setAnswerId(ansId);
        userFollowAns.setUserId(userId);
        userFollowAnsMapper.deleteByData(ansId, userId);
    }

    @Override
    public void cancelFollowQues(Integer quesId, Integer userId) {
        UserFollowQues userFollowQues = new UserFollowQues();
        userFollowQues.setQuesId(quesId);
        userFollowQues.setUserId(userId);
        userFollowQuesMapper.deleteByData(quesId, userId);
    }

    @Override
    public Boolean hasFollowQues(Integer quesId, Integer userId) {
        UserFollowQues data = userFollowQuesMapper.selectByUserIdAndQusId(quesId, userId);
        if (data == null)
            return false;
        return true;
    }

    @Override
    public Boolean hasFollowAns(Integer answerId, Integer userId) {
        UserFollowAns data = userFollowAnsMapper.selectByUserIdAndAnsId(answerId, userId);
        if (data == null)
            return false;
        return true;
    }

    @Override
    public Boolean hasFollowUser(Integer userId, Integer useredId) {
        UserRelationsKey data = userRelationsMapper.selectByPrimaryKey(userId, useredId);
        if (data == null)
            return false;
        return true;
    }

    @Override
    public void setLike(Integer ansId, Integer userId) {
        UserLikeAnsKey userLikeAnsKey = new UserLikeAnsKey();
        userLikeAnsKey.setAnsId(ansId);
        userLikeAnsKey.setUserId(userId);
        userLikeAnsMapper.insertSelective(userLikeAnsKey);
    }

    @Override
    public Boolean hasLike(Integer ansId, Integer userId) {
        UserLikeAnsKey data = userLikeAnsMapper.selectByPrimaryKey(ansId,userId);
        if (data == null)
            return false;
        return true;
    }
}
