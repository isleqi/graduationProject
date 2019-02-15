package com.isleqi.graduationproject.controller;

import com.isleqi.graduationproject.component.common.RedisKeyPrefix;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.domain.Answer;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.domain.vo.AnswerParamVo;
import com.isleqi.graduationproject.domain.vo.AnswerVo;
import com.isleqi.graduationproject.service.AnswerService;
import com.isleqi.graduationproject.service.UserOperationService;
import com.isleqi.graduationproject.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/answer")
public class AnswerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AnswerService answerService;
    @Autowired
    UserOperationService userOperationService;
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response add(@RequestHeader("token") String token, @RequestBody AnswerParamVo answerParamVo) {
        try {
            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("token失效，请重新登录");
            }
            answerParamVo.setUserId(user.getId());
            int ansId = answerService.addAnswer(answerParamVo);
           AnswerVo answerVo=answerService.getByAnsId(ansId);
            return Response.successResponseWithData(answerVo);

        } catch (Exception e) {
            logger.info(e.getMessage());
            return Response.errorResponse("添加答案失败");
        }

    }

    @RequestMapping(value = "follow", method = RequestMethod.GET)
    public Response followAns(@RequestHeader("token") String token, @RequestParam("ansId") Integer ansId) {
        try {

            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("token失效，请重新登录");
            }
            int userId = user.getId();
            userOperationService.followAns(ansId, userId);
            return Response.successResponse();
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return Response.errorResponse("收藏回答失败");
        }

    }

    @RequestMapping(value = "hasfollow", method = RequestMethod.GET)
    public Response hasfollow(@RequestHeader("token") String token, @RequestParam("ansId") Integer ansId) {
        try {
            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("token失效，请重新登录");
            }
            int userId = user.getId();
            boolean data = userOperationService.hasFollowAns(ansId, userId);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return Response.errorResponse("获取收藏回答失败");
        }

    }

    @RequestMapping(value = "cancelFollow", method = RequestMethod.GET)
    public Response cancelFollow(@RequestHeader("token") String token, @RequestParam("ansId") Integer ansId) {
        try {

            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("token失效，请重新登录");
            }
            int userId = user.getId();
            userOperationService.cancelFollowAns(ansId, userId);
            return Response.successResponse();
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return Response.errorResponse("取消收藏失败");
        }

    }


    @RequestMapping(value = "getAnswerList", method = RequestMethod.GET)
    public Response getAnswerList(Integer quesId) {
        try {

            List<AnswerVo> data = answerService.getListByQuesId(quesId);

            return Response.successResponseWithData(data);

        } catch (Exception e) {
            logger.info(e.getMessage());
            return Response.errorResponse("获取答案列表失败");
        }

    }

    @RequestMapping(value = "setLike", method = RequestMethod.GET)
    public Response setLike(@RequestHeader("token") String token, Integer ansId) {
        try {
            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("token失效，请重新登录");
            }
            int userId = user.getId();
            userOperationService.setLike(ansId, userId);
            return Response.successResponse();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("点赞失败");
            return Response.errorResponse("点赞失败");
        }
    }

    @RequestMapping(value = "hasLike", method = RequestMethod.GET)
    public Response hasLike(@RequestHeader("token") String token, Integer ansId) {
        try {
            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("token失效，请重新登录");
            }
            int userId = user.getId();
           Boolean data = userOperationService.hasLike(ansId, userId);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取是否点赞失败");
            return Response.errorResponse("获取是否点赞失败");
        }
    }





}
