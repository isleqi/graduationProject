package com.isleqi.graduationproject.service.impl;

import com.isleqi.graduationproject.dao.mappers.UserFollowQuesMapper;
import com.isleqi.graduationproject.domain.UserFollowQues;
import com.isleqi.graduationproject.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Transactional
@Service("userOperationService")
public class UserOperationServiceImpl implements UserOperationService {

    @Autowired
    UserFollowQuesMapper userFollowQuesMapper;

    @Override
    public void followQues(Integer quesId,Integer userId) {
        UserFollowQues userFollowQues=new UserFollowQues();
        userFollowQues.setQuesId(quesId);
        userFollowQues.setUserId(userId);
        userFollowQuesMapper.insertSelective(userFollowQues);
    }
}
