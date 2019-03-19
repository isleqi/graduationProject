package com.isleqi.graduationproject.service;

import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.domain.Notify;
import com.isleqi.graduationproject.domain.vo.NotifyVo;

public interface NotifyService {
    void addNotify(Notify notify,Integer userId);

    void addNotifyToAllUser(Notify notify);

    void addNotifyToAllUserForQues(Notify notify,Integer quesId);

    PageBean<NotifyVo> getNotifyList(int userId, int pageNum, int pageSize);

    PageBean<NotifyVo> getNotReadNotifyList(int userId, int pageNum, int pageSize);


    void clearAll(Integer userId);

}
