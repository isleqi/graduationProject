package com.isleqi.graduationproject.service;

public interface UserOperationService {
    void followQues(Integer quesId,Integer userId);

    void followAns(Integer ansId,Integer userId);

    void followUser(Integer userId,Integer useredId);

    void cancelFollowUser(Integer userId,Integer useredId);

    void cancelFollowAns(Integer ansId,Integer userId);

    void cancelFollowQues(Integer quesId,Integer userId);

    Boolean hasFollowQues(Integer quesId,Integer userId);

    Boolean hasFollowAns(Integer ansId,Integer userId);

    Boolean hasFollowUser(Integer userId,Integer useredId);

    Boolean hasLike(Integer ansId, Integer userId);

    void setLike(Integer ansId,Integer userId);

    void cancelLike(Integer ansId, Integer userId);

    void thanks(Integer userId,Integer value);
}
