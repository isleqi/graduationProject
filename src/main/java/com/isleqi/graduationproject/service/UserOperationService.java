package com.isleqi.graduationproject.service;

public interface UserOperationService {
    void followQues(Integer quesId,Integer userId);

    Boolean hasFollowQues(Integer quesId,Integer userId);
}
