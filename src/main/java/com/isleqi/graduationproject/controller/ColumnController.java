package com.isleqi.graduationproject.controller;


import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.component.common.RedisKeyPrefix;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.domain.Article;
import com.isleqi.graduationproject.domain.ArticleComment;
import com.isleqi.graduationproject.domain.ArticleReply;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.domain.vo.ArticleCommentVo;
import com.isleqi.graduationproject.domain.vo.ArticleParamVo;
import com.isleqi.graduationproject.domain.vo.ArticleReplyVo;
import com.isleqi.graduationproject.domain.vo.ArticleVo;
import com.isleqi.graduationproject.service.ArticleCommentAndReplyService;
import com.isleqi.graduationproject.service.ArticleService;
import com.isleqi.graduationproject.service.CommentAndReplyService;
import com.isleqi.graduationproject.service.UserOperationService;
import com.isleqi.graduationproject.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/column")
public class ColumnController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserOperationService userOperationService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleCommentAndReplyService articleCommentAndReplyService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response add(@RequestHeader("token") String token, @RequestBody ArticleParamVo articleParamVo) {
        try {
            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("token失效，请重新登录");
            }
            articleParamVo.setUserId(user.getId());
           int articleId= articleService.addArticle(articleParamVo);
            ArticleVo articleVo = articleService.getArticleById(articleId);
            return Response.successResponseWithData(articleVo);

        } catch (Exception e) {
            e.printStackTrace();
            return Response.errorResponse("发表专栏失败");
        }

    }

    @RequestMapping(value = "getArticleList", method = RequestMethod.GET)
    public Response getArticleList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        try {
            PageBean<ArticleVo> data =articleService.getArticleList(pageNum, pageSize);

            return Response.successResponseWithData(data);

        }catch (Exception e){
            logger.info(e.getMessage());
            return Response.errorResponse("获取专栏列表失败");
        }
    }
    @RequestMapping(value = "comment", method = RequestMethod.POST)
    public Response comment(@RequestHeader("token") String token, String comment, Integer articleId) {
        try {
            User user = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
            if (user == null) {
                return Response.errorResponse("comment_token失效，请重新登录");
            }
            int userId = user.getId();
            ArticleComment articleComment = new ArticleComment();
            articleComment.setArticleId(articleId);
            articleComment.setUserId(userId);
            articleComment.setCommentContent(comment);
            articleCommentAndReplyService.addComment(articleComment);
            Integer commentId=articleComment.getId();

            ArticleCommentVo data = articleCommentAndReplyService.getCommentById(commentId);
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

            ArticleReply articleReply = new ArticleReply();
            articleReply.setArticleCommentId(commentId);
            articleReply.setReplyComtent(comtent);
            articleReply.setReplyedUserId(replyedUserId);
            articleReply.setReplyUserId(userId);
            articleCommentAndReplyService.addReply(articleReply);
            ArticleReplyVo data = articleCommentAndReplyService.getReplyById(articleReply.getId());
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("回复失败");
            return Response.errorResponse("回复失败");

        }
    }


    @RequestMapping(value = "getCommentList",method = RequestMethod.GET)
    public Response getCommentList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("articleId") Integer articleId) {
        try {
            PageBean<ArticleCommentVo> data  = articleCommentAndReplyService.getCommentByArticleId(articleId,pageNum,pageSize);
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
            PageBean<ArticleReplyVo> data  = articleCommentAndReplyService.getReplyListByCommnetId(commentId,pageNum,pageSize);
            return Response.successResponseWithData(data);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取回复列表失败");
            return Response.errorResponse("获取回复列表失败");
        }
    }

    @RequestMapping(value = "getFollowUserArticleList",method = RequestMethod.GET)
    public Response getFollowUserArticleList(@RequestHeader("token") String token,@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        try{

            User user= (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN+token);
            if(user==null){
                return Response.errorResponse("token失效，请重新登录");
            }
            int userId=user.getId();
            PageBean<ArticleVo> data  = articleService.getFollowUserArticleList(pageNum,pageSize,userId);
            return Response.successResponseWithData(data);
        }
        catch (Exception e){
            logger.info(e.getMessage());
            e.printStackTrace();
            return Response.errorResponse("获取关注的人文章列表失败");
        }

    }




}
