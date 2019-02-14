package com.isleqi.graduationproject.service.impl;

import com.isleqi.graduationproject.dao.mappers.QuestionMapper;
import com.isleqi.graduationproject.dao.mappers.UserFollowQuesMapper;
import com.isleqi.graduationproject.domain.Question;
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
    @Autowired
    QuestionMapper questionMapper;



    @Override
    public void followQues(Integer quesId,Integer userId) {
        UserFollowQues userFollowQues=new UserFollowQues();
        userFollowQues.setQuesId(quesId);
        userFollowQues.setUserId(userId);
       boolean flag= hasFollowQues(quesId,userId);
        if(!flag){
            userFollowQuesMapper.insertSelective(userFollowQues);
            questionMapper.updateFollowNum(quesId);
        }
    }

    @Override
    public Boolean hasFollowQues(Integer quesId, Integer userId) {
        UserFollowQues data=userFollowQuesMapper.selectByUserIdAndQusId(quesId,userId);
        if(data==null)
            return false;
        return true;
    }
}
