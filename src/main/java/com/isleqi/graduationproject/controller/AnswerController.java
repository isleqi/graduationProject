package com.isleqi.graduationproject.controller;

import com.isleqi.graduationproject.component.common.Authorized;
import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.component.common.RedisKeyPrefix;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.domain.*;
import com.isleqi.graduationproject.domain.vo.*;
import com.isleqi.graduationproject.service.AnswerService;

import com.isleqi.graduationproject.service.CommentAndReplyService;
import com.isleqi.graduationproject.service.NotifyService;
import com.isleqi.graduationproject.service.UserOperationService;
import com.isleqi.graduationproject.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    CommentAndReplyService ansCommentAndReplyService;
    @Autowired
    NotifyService notifyService;
    @Autowired
    RedisUtil redisUtil;

    @Authorized
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response add(@RequestBody AnswerParamVo answerParamVo,
                        HttpServletRequest request) {


        AnswerVo answerVo;
        User user = (User) request.getAttribute("user");

        try {
            answerParamVo.setUserId(user.getId());
            int ansId = answerService.addAnswer(answerParamVo);
            answerVo = answerService.getByAnsId(ansId);

            try {
                Notify notify = new Notify();
                notify.setType("发表回答");
                notify.setSendUserId(user.getId());
                notify.setTargetId(ansId);
                notify.setTargetType(1);
                notifyService.addNotifyToAllUserForQues(notify, answerParamVo.getQuesId());
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("通知失败");
            }

            return Response.successResponseWithData(answerVo);

        } catch (Exception e) {
            logger.info(e.getMessage());
            return Response.errorResponse("添加答案失败");
        }


    }

    @RequestMapping(value = "deleteAnswer", method = RequestMethod.GET)
    public Response deleteAnswer(@RequestParam("ansId") Integer ansId,
                                 @RequestParam("quesId") Integer quesId) {
        try {
            answerService.delAnswer(ansId, quesId);
            return Response.successResponse();
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return Response.errorResponse("删除回答失败");
        }

    }

    @RequestMapping(value = "updateAnswer", method = RequestMethod.POST)

    public Response updateAnswer(@RequestParam Integer ansId,
                                 @RequestParam String content) {
        try {
            answerService.updateAnswer(content, ansId);
            return Response.successResponse();
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return Response.errorResponse("更新回答失败");
        }

    }

    @Authorized
    @RequestMapping(value = "follow", method = RequestMethod.GET)
    public Response followAns(@RequestParam("ansId") Integer ansId,
                              HttpServletRequest request) {

        User user = (User) request.getAttribute("user");

        try {

            int userId = user.getId();
            userOperationService.followAns(ansId, userId);
            return Response.successResponse();
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return Response.errorResponse("收藏回答失败");
        }

    }

    @Authorized
    @RequestMapping(value = "hasfollow", method = RequestMethod.GET)
    public Response hasfollow(@RequestParam("ansId") Integer ansId,
                              HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        try {

            int userId = user.getId();
            boolean data = userOperationService.hasFollowAns(ansId, userId);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return Response.errorResponse("获取收藏回答失败");
        }

    }

    @Authorized
    @RequestMapping(value = "cancelFollow", method = RequestMethod.GET)
    public Response cancelFollow(@RequestParam("ansId") Integer ansId,
                                 HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        try {


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
    public Response getAnswerList(@RequestParam("pageNum") int pageNum,
                                  @RequestParam("pageSize") int pageSize,
                                  Integer quesId) {
        try {

            PageBean<AnswerVo> data = answerService.getListByQuesId(pageNum, pageSize, quesId);

            return Response.successResponseWithData(data);

        } catch (Exception e) {
            logger.info(e.getMessage());
            return Response.errorResponse("获取答案列表失败");
        }

    }

    @Authorized
    @RequestMapping(value = "setLike", method = RequestMethod.GET)
    public Response setLike(Integer ansId,
                            HttpServletRequest request) {

        User user = (User) request.getAttribute("user");

        try {
            int userId = user.getId();
            userOperationService.setLike(ansId, userId);

            return Response.successResponse();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("点赞失败");
            return Response.errorResponse("点赞失败");
        }
    }

    @Authorized
    @RequestMapping(value = "thanks", method = RequestMethod.GET)
    public Response thanks(Integer ansId,
                           HttpServletRequest request) {

        User user = (User) request.getAttribute("user");

        try {

            int userId = user.getId();
            AnswerVo data = answerService.getByAnsId(ansId);
            userOperationService.thanks(userId, data.getUserId(), 5);
            try {
                Notify notify = new Notify();
                notify.setType("打赏");
                notify.setSendUserId(user.getId());
                notify.setTargetId(ansId);
                notify.setTargetType(1);
                notifyService.addNotify(notify, data.getUserId());
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("通知失败");
            }
            return Response.successResponse();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("打赏失败");
            return Response.errorResponse("打赏失败");
        }
    }

    @Authorized
    @RequestMapping(value = "cancelLike", method = RequestMethod.GET)
    public Response cancelLike(Integer ansId,
                               HttpServletRequest request) {

        User user = (User) request.getAttribute("user");

        try {

            int userId = user.getId();
            userOperationService.cancelLike(ansId, userId);
            return Response.successResponse();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("取消点赞失败");
            return Response.errorResponse("取消点赞失败");
        }
    }

    @Authorized
    @RequestMapping(value = "hasLike", method = RequestMethod.GET)
    public Response hasLike(Integer ansId,
                            HttpServletRequest request) {

        User user = (User) request.getAttribute("user");

        try {

            int userId = user.getId();
            Boolean data = userOperationService.hasLike(ansId, userId);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取是否点赞失败");
            return Response.errorResponse("获取是否点赞失败");
        }
    }

    @Authorized
    @RequestMapping(value = "comment", method = RequestMethod.POST)
    public Response comment(String comment,
                            Integer ansId,
                            HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        try {

            int userId = user.getId();
            AnsComment ansComment = new AnsComment();
            ansComment.setAnsId(ansId);
            ansComment.setUserId(userId);
            ansComment.setCommentContent(comment);
            ansCommentAndReplyService.addComment(ansComment);
            Integer commentId = ansComment.getId();

            AnsCommentVo data = ansCommentAndReplyService.getCommentById(commentId);

            AnswerVo answer = answerService.getByAnsId(ansId);

            try {
                Notify notify = new Notify();
                notify.setType("评论");
                notify.setSendUserId(user.getId());
                notify.setTargetId(commentId);
                notify.setContent(comment);
                notify.setTargetType(4);
                notifyService.addNotify(notify, answer.getUserId());

            } catch (Exception e) {
                e.printStackTrace();
                logger.info("通知失败");
            }
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("评论失败");
            return Response.errorResponse("评论失败");

        }
    }

    @Authorized
    @RequestMapping(value = "comment/reply", method = RequestMethod.POST)
    public Response reply(String comtent, Integer commentId,
                          Integer replyedUserId,
                          HttpServletRequest request) {

        User user = (User) request.getAttribute("user");

        try {

            int userId = user.getId();

            AnsReply ansReply = new AnsReply();
            ansReply.setAnsCommentId(commentId);
            ansReply.setReplyComtent(comtent);
            ansReply.setReplyedUserId(replyedUserId);
            ansReply.setReplyUserId(userId);
            ansCommentAndReplyService.addReply(ansReply);
            AnsReplyVo data = ansCommentAndReplyService.getReplyById(ansReply.getId());

            try {
                Notify notify = new Notify();
                notify.setType("回复");
                notify.setSendUserId(user.getId());
                notify.setTargetId(commentId);
                notify.setContent(comtent);
                notify.setTargetType(4);
                notifyService.addNotify(notify, replyedUserId);

            } catch (Exception e) {
                e.printStackTrace();
                logger.info("通知失败");
            }

            return Response.successResponseWithData(data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("回复失败");
            return Response.errorResponse("回复失败");

        }
    }


    @RequestMapping(value = "getCommentList", method = RequestMethod.GET)
    public Response getCommentList(@RequestParam("pageNum") int pageNum,
                                   @RequestParam("pageSize") int pageSize,
                                   @RequestParam("ansId") Integer ansId) {
        try {
            PageBean<AnsCommentVo> data = ansCommentAndReplyService.getCommentByAnsId(ansId, pageNum, pageSize);
            return Response.successResponseWithData(data);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取评论列表失败");
            return Response.errorResponse("获取评论列表失败");
        }
    }

    @RequestMapping(value = "getReplyList", method = RequestMethod.GET)
    public Response getReplyList(@RequestParam("pageNum") int pageNum,
                                 @RequestParam("pageSize") int pageSize,
                                 @RequestParam("commentId") Integer commentId) {
        try {
            PageBean<AnsReplyVo> data = ansCommentAndReplyService.getReplyListByCommnetId(commentId, pageNum, pageSize);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取回复列表失败");
            return Response.errorResponse("获取回复列表失败");
        }
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public Response search(@RequestParam("pageNum") int pageNum,
                           @RequestParam("pageSize") int pageSize,
                           @RequestParam("str") String str) {
        try {
            PageBean<AnswerVo> data = answerService.getListBySearch(pageNum, pageSize, str);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.errorResponse("获取搜索结果失败");
        }
    }


}
