package com.isleqi.graduationproject.service.impl;

import com.github.pagehelper.PageHelper;
import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.dao.mappers.*;
import com.isleqi.graduationproject.domain.Notify;
import com.isleqi.graduationproject.domain.Tag;
import com.isleqi.graduationproject.domain.UserNotify;
import com.isleqi.graduationproject.domain.vo.NotifyVo;
import com.isleqi.graduationproject.domain.vo.QuestionVo;
import com.isleqi.graduationproject.domain.vo.TagMapVo;
import com.isleqi.graduationproject.service.ArticleService;
import com.isleqi.graduationproject.service.NotifyService;
import com.isleqi.graduationproject.service.QuestionService;
import com.isleqi.graduationproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.CommandMap;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("notifyService")
public class NotifyServiceImpl implements NotifyService {
    @Autowired
    NotifyMapper notifyMapper;
    @Autowired
    UserNotifyMapper userNotifyMapper;
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleReplyMapper articleReplyMapper;
    @Autowired
    AnsReplyMapper ansReplyMapper;
    @Autowired
    ArticleService articleService;
    @Autowired
    AnsCommentMapper ansCommentMapper;
    @Autowired
    ArticleCommentMapper articleCommentMapper;
    @Autowired
    TagMapMapper tagMapMapper;


    @Transactional
    @Override
    public void addNotify(Notify notify, Integer userId) {
        notifyMapper.insertSelective(notify);
        int notifyId = notify.getId();
        UserNotify userNotify = new UserNotify();
        userNotify.setNotifyId(notifyId);
        userNotify.setUserId(userId);
        userNotifyMapper.insertSelective(userNotify);
    }

    @Transactional
    @Override
    public void addNotifyToAllUser(Notify notify) {
        notifyMapper.insertSelective(notify);
        int userId = notify.getSendUserId();
        int notifyId = notify.getId();
        List<Integer> fanIds = userService.getFanIds(userId);
        for (Integer id : fanIds
        ) {
            UserNotify userNotify = new UserNotify();
            userNotify.setNotifyId(notifyId);
            userNotify.setUserId(id);
            userNotifyMapper.insertSelective(userNotify);
        }
    }

    @Override
    public void addNotifyToAllUserForQues(Notify notify,Integer quesId) {
        notifyMapper.insertSelective(notify);
        int notifyId = notify.getId();
        List<Integer> followUserList = questionService.getFollowUsersIdList(quesId);
        for (Integer id : followUserList
        ) {
            UserNotify userNotify = new UserNotify();
            userNotify.setNotifyId(notifyId);
            userNotify.setUserId(id);
            userNotifyMapper.insertSelective(userNotify);
        }

    }

    @Override
    public PageBean<NotifyVo> getNotifyList(int userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<NotifyVo> list = null;
        System.out.println(userId+"dfdfdfd");
        try {
            list = notifyMapper.selectByUserId(userId);
            for (NotifyVo item : list) {
                Object target=getTarget(item.getTargetType(), item.getTargetId(),userId);
                item.setTarget(target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            PageHelper.clearPage();
        }
        PageBean<NotifyVo> info = new PageBean<>(list);

        return info;
    }

    public Object getTarget(Integer type, Integer targetId,Integer userId) {
        switch (type) {
            case 1:
                return answerMapper.selectAnswer(targetId);
            case 2:
                return questionMapper.selectByPrimaryKey(targetId);
            case 3:
                return articleService.getArticleById(targetId);
            case 4:
                return ansCommentMapper.selectByPrimaryKey(targetId);
            case 5:
                return articleCommentMapper.selectByPrimaryKey(targetId);
            case 6:
                QuestionVo data=questionMapper.selectByQuesId(targetId);
                List<TagMapVo> tagMapVos=tagMapMapper.selectAllTagByQuesId(data.getId());
                List<Tag> tags=new ArrayList<>();
                for(TagMapVo vo:tagMapVos){
                    tags.add(vo.getTag());
                }
                data.setTagList(tags);
                return data;


        }
        return null;
    }
}
