package com.isleqi.graduationproject.controller;

import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.component.common.RedisKeyPrefix;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.domain.AnsComment;
import com.isleqi.graduationproject.domain.AnsReply;
import com.isleqi.graduationproject.domain.Answer;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.domain.vo.*;
import com.isleqi.graduationproject.service.AnswerService;
import com.isleqi.graduationproject.service.CommentAndReplyService;
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
    CommentAndReplyService commentAndReplyService;
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

            AnswerVo answerVo = answerService.getByAnsId(ansId);

            return Response.successResponseWithData(answerVo);

        } catch (Exception e) {
            logger.info(e.getMessage());
            return Response.errorResponse("添加答案失败");
        }

    }

    @RequestMapping(value = "deleteAnswer", method = RequestMethod.GET)
    public Response deleteAnswer(@RequestParam("ansId") Integer ansId,@RequestParam("quesId") Integer quesId) {
        try {
            answerService.delAnswer(ansId, quesId);
            return Response.successResponse();
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return Response.errorResponse("删除回答失败");
        }

    }
    @RequestMapping(value = "updateAnswer", method = RequestMethod.GET)
    public Response updateAnswer(@RequestParam("ansId") Integer ansId,@RequestParam("content") String content) {
        try {
            answerService.updateAnswer(content,ansId);
            return Response.successResponse();
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return Response.errorResponse("更新回答失败");
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
                return Response.errorResponse("hasfollow_token失效，请重新登录");
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
    public Response getAnswerList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Integer quesId) {
        try {

            PageBean<AnswerVo> data = answerService.getListByQuesId(pageNum,pageSize,quesId);

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
                return Response.errorResponse("setLike_token失效，请重新登录");
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

    @RequestMapping(value = "cancelLike", method = RequestMethod.GET)
    public Response cancelLike(@RequestHeader("token") String token, Integer ansId) {
        try {
            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("cancelLike_token失效，请重新登录");
            }
            int userId = user.getId();
            userOperationService.cancelLike(ansId, userId);
            return Response.successResponse();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("取消点赞失败");
            return Response.errorResponse("取消点赞失败");
        }
    }

    @RequestMapping(value = "hasLike", method = RequestMethod.GET)
    public Response hasLike(@RequestHeader("token") String token, Integer ansId) {
        try {
            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("hasLike_token失效，请重新登录");
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

    @RequestMapping(value = "comment", method = RequestMethod.POST)
    public Response comment(@RequestHeader("token") String token, String comment, Integer ansId) {
        try {
            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("comment_token失效，请重新登录");
            }
            int userId = user.getId();
            AnsComment ansComment = new AnsComment();
            ansComment.setAnsId(ansId);
            ansComment.setUserId(userId);
            ansComment.setCommentContent(comment);
             commentAndReplyService.addComment(ansComment);
             Integer commentId=ansComment.getId();
             logger.info("commentId:"+commentId);
            AnsCommentVo data = commentAndReplyService.getCommentById(commentId);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("评论失败");
            return Response.errorResponse("评论失败");

        }
    }

    @RequestMapping(value = "comment/reply", method = RequestMethod.POST)
    public Response reply(@RequestHeader("token") String token, String comtent, Integer commentId, Integer replyedUserId) {
        try {
            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("reply_token失效，请重新登录");
            }
            int userId = user.getId();

            AnsReply ansReply = new AnsReply();
            ansReply.setAnsCommentId(commentId);
            ansReply.setReplyComtent(comtent);
            ansReply.setReplyedUserId(replyedUserId);
            ansReply.setReplyUserId(userId);
            commentAndReplyService.addReply(ansReply);
            AnsReplyVo data = commentAndReplyService.getReplyById(ansReply.getId());
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("回复失败");
            return Response.errorResponse("回复失败");

        }
    }


    @RequestMapping(value = "getCommentList",method = RequestMethod.GET)
    public Response getCommentList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("ansId") Integer ansId) {
        try {
            PageBean<AnsCommentVo> data  = commentAndReplyService.getCommentByAnsId(ansId,pageNum,pageSize);
            return Response.successResponseWithData(data);

        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取评论列表失败");
            return Response.errorResponse("获取评论列表失败");
        }
    }

    @RequestMapping(value = "getReplyList",method = RequestMethod.GET)
    public Response getReplyList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("commentId") Integer commentId) {
        try {
            PageBean<AnsReplyVo> data  = commentAndReplyService.getReplyListByCommnetId(commentId,pageNum,pageSize);
            return Response.successResponseWithData(data);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取回复列表失败");
            return Response.errorResponse("获取回复列表失败");
        }
    }



}
