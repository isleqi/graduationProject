package com.isleqi.graduationproject.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.component.common.RedisKeyPrefix;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.dao.mappers.QuestionMapper;
import com.isleqi.graduationproject.dao.mappers.TagMapMapper;
import com.isleqi.graduationproject.dao.mappers.TagMapper;
import com.isleqi.graduationproject.domain.Notify;
import com.isleqi.graduationproject.domain.Question;
import com.isleqi.graduationproject.domain.Tag;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.domain.vo.AnswerVo;
import com.isleqi.graduationproject.domain.vo.QuestionParamVo;
import com.isleqi.graduationproject.domain.vo.QuestionVo;
import com.isleqi.graduationproject.domain.vo.TagMapVo;
import com.isleqi.graduationproject.service.NotifyService;
import com.isleqi.graduationproject.service.QuestionService;
import com.isleqi.graduationproject.service.UserOperationService;
import com.isleqi.graduationproject.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("app/question")
public class QuestionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TagMapper tagMapper;
    @Autowired
    TagMapMapper tagMapMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    QuestionService questionService;
    @Autowired
    UserOperationService userOperationService;
    @Autowired
    NotifyService notifyService;


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response addQuestion(@RequestHeader("token") String token, @RequestBody QuestionParamVo questionParamVo) {
        try {

            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);

            if (user == null) {
                return Response.errorResponse("token失效，请重新登录");
            }

            Integer userId = user.getId();
            questionParamVo.setQuesUserId(user.getId());
            int quesId = questionService.insert(questionParamVo);

            //     Question question = questionService.getById(quesId);

            userOperationService.followQues(quesId, userId);


            List<TagMapVo> tagMapVos = tagMapMapper.selectAllTagByQuesId(quesId);
            List<Tag> tags = new ArrayList<>();
            for (TagMapVo vo : tagMapVos) {
                tags.add(vo.getTag());
            }


            QuestionVo questionVo = new QuestionVo();
            questionVo.setId(quesId);
            questionVo.setQuesTitle(questionParamVo.getQuesTitle());
            questionVo.setQuesDes(questionParamVo.getQuesDes());
            questionVo.setTagList(tags);

            return Response.successResponseWithData(questionVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.errorResponse("添加问题失败");
        }
    }

    @RequestMapping(value = "getFollowQuesList", method = RequestMethod.GET)
    public Response getFollowQues(@RequestHeader("token") String token, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        try {

            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("token失效，请重新登录");
            }
            int userId = user.getId();
            PageBean<AnswerVo> data = questionService.getFollowQuestionList(userId, pageNum, pageSize);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return Response.errorResponse("获取关注问题列表失败");
        }

    }

    @RequestMapping(value = "follow", method = RequestMethod.GET)
    public Response followQues(@RequestHeader("token") String token, @RequestParam("quesId") Integer quesId) {
        try {

            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("token失效，请重新登录");
            }
            int userId = user.getId();
            userOperationService.followQues(quesId, userId);

            return Response.successResponse();
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return Response.errorResponse("关注问题失败");
        }

    }

    @RequestMapping(value = "cancelFollow", method = RequestMethod.GET)
    public Response cancelFollow(@RequestHeader("token") String token, @RequestParam("quesId") Integer quesId) {
        try {

            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("token失效，请重新登录");
            }
            int userId = user.getId();
            userOperationService.cancelFollowQues(quesId, userId);

            return Response.successResponse();
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return Response.errorResponse("取消关注问题失败");
        }

    }

    @RequestMapping(value = "hasfollow", method = RequestMethod.GET)
    public Response hasfollow(@RequestHeader("token") String token, @RequestParam("quesId") Integer quesId) {
        try {
            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("token失效，请重新登录");
            }
            int userId = user.getId();
            boolean flag = userOperationService.hasFollowQues(quesId, userId);
            int num = questionMapper.selectByPrimaryKey(quesId).getFollowNum();
            JSONObject data = new JSONObject();
            data.put("hasFollow", flag);
            data.put("followNum", num);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return Response.errorResponse("获取是否关注问题失败");
        }

    }

    @RequestMapping(value = "getAllQuestion", method = RequestMethod.GET)
    public Response getAllQuestion(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        try {
            PageBean<QuestionVo> data = questionService.getQuestionList(pageNum, pageSize);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            e.getStackTrace();

            return Response.errorResponse("获取全部问题失败");
        }

    }

    @RequestMapping(value = "getByTag", method = RequestMethod.GET)
    public Response getByTag(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("tagId") int tagId) {
        try {
            PageBean<QuestionVo> data = questionService.getByTagId(pageNum, pageSize, tagId);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            return Response.errorResponse("获取问题失败");
        }

    }

    @RequestMapping(value = "getByStr", method = RequestMethod.GET)
    public Response getByStr(@RequestParam String str) {

        return null;
    }

    @RequestMapping(value = "tag/add", method = RequestMethod.GET)
    public Response addTag(@RequestParam("tagName") String tagName) {
        try {
            Tag tag = new Tag();
            tag.setTagName(tagName);
            tagMapper.insertSelective(tag);
            JSONObject data = new JSONObject();
            data.put("id", tag.getId());
            data.put("tagName", tagName);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            return Response.errorResponse("添加标签失败");
        }

    }

    @RequestMapping(value = "tag/get", method = RequestMethod.GET)
    public Response getTag(@RequestParam("str") String str) {
        try {
            List<Tag> tags = tagMapper.selectBySearch(str);
            return Response.successResponseWithData(tags);
        } catch (Exception e) {
            return Response.errorResponse("获取标签类表失败");
        }


    }


    @RequestMapping(value = "tag/getHot", method = RequestMethod.GET)
    public Response getTag() {
        try {
            List<Tag> tags = tagMapper.selectByHot();
            return Response.successResponseWithData(tags);
        } catch (Exception e) {
            return Response.errorResponse("获取热门标签类表失败");
        }


    }

    @RequestMapping(value = "invitation", method = RequestMethod.GET)
    public Response invitation(@RequestHeader("token") String token, @RequestParam(value = "userIds[]") Integer[] userIds, @RequestParam(value = "quesId") Integer quesId) {
        Object info = redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
        User user;
        if (info instanceof User) {
            user = (User) info;
        } else {
            return Response.errorResponse("token失效，请重新登录");
        }
        try {
            for (int i = 0; i < userIds.length; i++) {
                Notify notify = new Notify();
                notify.setType("邀请");
                notify.setSendUserId(user.getId());
                notify.setTargetId(quesId);
                notify.setTargetType(6);
                notifyService.addNotify(notify, userIds[i]);
            }
            return Response.successResponse();
        } catch (Exception e) {
            return Response.errorResponse("邀请失败");
        }


    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public Response search(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("str") String str) {
        try {
            PageBean<QuestionVo> data = questionService.getListBySearch(pageNum, pageSize, str);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.errorResponse("获取搜索结果失败");
        }
    }

}
