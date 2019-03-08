package com.isleqi.graduationproject.service.impl;

import com.github.pagehelper.PageHelper;
import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.dao.mappers.QuestionMapper;
import com.isleqi.graduationproject.dao.mappers.TagMapMapper;
import com.isleqi.graduationproject.dao.mappers.UserFollowQuesMapper;
import com.isleqi.graduationproject.domain.*;
import com.isleqi.graduationproject.domain.vo.AnswerVo;
import com.isleqi.graduationproject.domain.vo.QuestionParamVo;
import com.isleqi.graduationproject.domain.vo.QuestionVo;
import com.isleqi.graduationproject.domain.vo.TagMapVo;
import com.isleqi.graduationproject.service.AnswerService;
import com.isleqi.graduationproject.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    TagMapMapper tagMapMapper;
    @Autowired
    UserFollowQuesMapper userFollowQuesMapper;
    @Autowired
    AnswerService answerService;

    @Override
    @Transactional
    public int insert(QuestionParamVo questionParamVo) throws Exception {

        Question question = new Question();
        question.setQuesTitle(questionParamVo.getQuesTitle());
        question.setQuesDes(questionParamVo.getQuesDes());
        question.setQuesUserId(questionParamVo.getQuesUserId());
        question.setQuesdUserId(questionParamVo.getQuesdUserId());

        questionMapper.insertSelective(question);

        int quesId=question.getId();

        UserFollowQues userFollowQues = new UserFollowQues();
        userFollowQues.setQuesId(quesId);
        userFollowQues.setUserId(questionParamVo.getQuesUserId());
        userFollowQuesMapper.insertSelective(userFollowQues);

        Integer[] tagIds=questionParamVo.getTagIds();
       List<TagMap> list=new ArrayList<>();
        for(int tagId:tagIds){
            TagMap tagMap=new TagMap();
           tagMap.setQuesId(quesId);
           tagMap.setTagId(tagId);
            list.add(tagMap);
        }

        tagMapMapper.insertBatch(list);

        return quesId;
    }

    @Override
    public void update(QuestionParamVo questionParamVo) {

    }



    @Override
    public PageBean<QuestionVo> getByTagId(int pageNum, int pageSize, int tagId) {
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<QuestionVo> list=null;
        try {
            list = (ArrayList<QuestionVo>) questionMapper.selectByTagId(tagId);
            for (QuestionVo item:list) {
                AnswerVo ans=answerService.getByQuesId(item.getId());
                item.setAnswerVo(ans);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            PageHelper.clearPage();
        }
        PageBean<QuestionVo> info = new PageBean<>(list);

        return info;
    }

    @Override
    public PageBean<QuestionVo> getByUserId(int pageNum, int pageSize, int userId) {
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<QuestionVo> list=null;

        try {
            list = (ArrayList<QuestionVo>) questionMapper.selectByUserId(userId);

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<QuestionVo> info = new PageBean<>(list);

        return info;
    }

    @Override
    public PageBean<QuestionVo> getListBySearch(int pageNum, int pageSize, String str) {
        PageHelper.startPage(pageNum, pageSize);
        List<QuestionVo> list=null;
        try{
            list=questionMapper.selectListByStr(str);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<QuestionVo> info = new PageBean<>(list);

        return info;
    }

    @Override
    public PageBean<QuestionVo> getQuestionList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<QuestionVo> list=null;

        try {
            list = (ArrayList<QuestionVo>) questionMapper.selectQuesWithAns();
            for (QuestionVo item:list) {
                logger.info(item.getId().toString());
                List<TagMapVo> tagMapVos=tagMapMapper.selectAllTagByQuesId(item.getId());
                List<Tag> tags=new ArrayList<>();
                for(TagMapVo vo:tagMapVos){
                    tags.add(vo.getTag());
                }
                item.setTagList(tags);
            }


        } catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<QuestionVo> info = new PageBean<>(list);

        return info;

    }

    @Override
    public PageBean<QuestionVo> getFollowQuestionList(int userId,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<QuestionVo> list=null;
        try {
          list =  questionMapper.selectFollowQuesWithAns(userId);
            for (QuestionVo item:list) {
                logger.info(item.getId().toString());
                List<TagMapVo> tagMapVos=tagMapMapper.selectAllTagByQuesId(item.getId());
                List<Tag> tags=new ArrayList<>();
                for(TagMapVo vo:tagMapVos){
                    tags.add(vo.getTag());
                }
                item.setTagList(tags);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            PageHelper.clearPage();
        }
        PageBean<QuestionVo> info = new PageBean<>(list);

        return info;
    }

    @Override
    public List<Integer> getFollowUsersIdList(Integer quesId) {
        return  userFollowQuesMapper.selectByQuesId(quesId);
    }


}
