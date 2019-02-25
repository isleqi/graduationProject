package com.isleqi.graduationproject.controller;


import com.isleqi.graduationproject.component.common.RedisKeyPrefix;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.domain.vo.ArticleParamVo;
import com.isleqi.graduationproject.domain.vo.ArticleVo;
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
            logger.info(e.getMessage());
            return Response.errorResponse("发表专栏失败");
        }

    }
}
