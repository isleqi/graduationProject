package com.isleqi.graduationproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.isleqi.graduationproject.component.common.*;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.component.common.domain.Sms;
import com.isleqi.graduationproject.dao.mappers.AdministratorMapper;
import com.isleqi.graduationproject.dao.mappers.UserPayMapper;
import com.isleqi.graduationproject.dao.mappers.UserValueMapper;
import com.isleqi.graduationproject.domain.*;
import com.isleqi.graduationproject.domain.vo.*;
import com.isleqi.graduationproject.service.*;
import com.isleqi.graduationproject.util.FileUtil;
import com.isleqi.graduationproject.util.JWTUtil;
import com.isleqi.graduationproject.util.RedisUtil;
import com.isleqi.graduationproject.util.SmsUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("app/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;
    @Autowired
    AnswerService answerService;
    @Autowired
    QuestionService questionService;
    @Autowired
    UserOperationService userOperationService;
    @Autowired
    UserValueMapper userValueMapper;
    @Autowired
    UserPayMapper userPayMapper;
    @Autowired
    ArticleService articleService;
    @Autowired
    AdministratorMapper administratorMapper;


    @Autowired
    @Lazy
    RedisUtil redisUtil;

    @Value("${web.local_avatar_path}")
    private String localAvatarPath;

    @Value("${web.avatar_path}")
    private String avatarPath;

    @Value("${web.upload_image_path}")
    private String localImagePath;

    @Value("${web.image_path}")
    private String imagePath;

    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public Response updateUserInfo(@RequestParam(value = "des", required = false) String des,
                                   @RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "avatarPath", required = false) String avatarPath,
                                   HttpServletRequest request) {


        User user = (User) request.getAttribute("user");

        try {


            if (des != null)
                user.setUserDes(des);
            if (avatarPath != null)
                user.setUserIconUrl(avatarPath);
            if (name != null)
                user.setUserName(name);

            userService.updateUser(user);

            return Response.successResponseWithData(user);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新用户信息失败");
            return Response.errorResponse("更新用户信息失败");
        }

    }

    @RequestMapping(value = "uploadImage", method = RequestMethod.POST)
    public Response uploadImage(@RequestParam("files") MultipartFile[] files) {
        List<String> paths = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                String fileName = FileUtil.saveImg(file, localImagePath);
                String path = imagePath + fileName;
                paths.add(path);
            }

            return Response.successResponseWithData(paths);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("上传图片失败");
            return Response.errorResponse("上传图片失败");
        }

    }

    @RequestMapping(value = "uploadAvatar", method = RequestMethod.POST)
    public Response uploadAvatar(
            @RequestParam("file") MultipartFile file) {
        try {


            String fileName = FileUtil.saveImg(file, localAvatarPath);
            String path = avatarPath + fileName;

            return Response.successResponseWithData(path);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("上传头像失败");
            return Response.errorResponse("上传头像失败");
        }

    }

    @Authorized
    @RequestMapping(value = "getBaseUserInfo", method = RequestMethod.POST)
    public Response getBaseUserInfo(@RequestHeader("token") String token, HttpServletRequest request) {


        User _user = (User) request.getAttribute("user");

        int userId = _user.getId();
        User user = userService.findByUserId(userId);
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUser(user);
        List<Integer> followIds = userService.getFollowIds(userId);
        List<Integer> fanIds = userService.getFanIds(userId);

        //将关注列表放进redis中
        redisUtil.set(RedisKeyPrefix.GET_FOLLOWUSERIDS + token, followIds, Constant.JWT_TTL);

        int followsNum = followIds.size();
        int fansNum = fanIds.size();
        userInfoVo.setFansNum(fansNum);
        userInfoVo.setFollowsNum(followsNum);

        return Response.successResponseWithData(userInfoVo);

    }

    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    public Response getUserInfo(Integer userId) {

        User user = userService.findByUserId(userId);
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUser(user);
        List<Integer> followIds = userService.getFollowIds(userId);
        List<Integer> fanIds = userService.getFanIds(userId);


        int followsNum = followIds.size();
        int fansNum = fanIds.size();
        userInfoVo.setFansNum(fansNum);
        userInfoVo.setFollowsNum(followsNum);

        return Response.successResponseWithData(userInfoVo);

    }

    @Authorized
    @RequestMapping(value = "follow", method = RequestMethod.GET)
    public Response followUser(HttpServletRequest request,
                               @RequestParam("useredId") Integer useredId) {
        User user = (User) request.getAttribute("user");

        try {


            int userId = user.getId();

            userOperationService.followUser(userId, useredId);
            return Response.successResponse();
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return Response.errorResponse("关注用户失败");
        }

    }

    @Authorized
    @RequestMapping(value = "hasfollow", method = RequestMethod.GET)
    public Response hasfollow(HttpServletRequest request,
                              @RequestParam("useredId") Integer useredId) {
        User user = (User) request.getAttribute("user");

        try {

            int userId = user.getId();
            boolean data = userOperationService.hasFollowUser(userId, useredId);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return Response.errorResponse("获取是否关注用户失败");
        }

    }

    @Authorized
    @RequestMapping(value = "cancelFollow", method = RequestMethod.GET)
    public Response cancelFollow(HttpServletRequest request,
                                 @RequestParam("useredId") Integer useredId) {
        User user = (User) request.getAttribute("user");

        try {

            int userId = user.getId();

            userOperationService.cancelFollowUser(userId, useredId);
            return Response.successResponse();
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return Response.errorResponse("取消关注用户失败");
        }

    }

    @Authorized
    @RequestMapping(value = "getFollowAnswerList", method = RequestMethod.GET)
    public Response getFollowAnswerList(HttpServletRequest request,
                                        @RequestParam("pageNum") int pageNum,
                                        @RequestParam("pageSize") int pageSize) {

        User user = (User) request.getAttribute("user");
        try {

            int userId = user.getId();
            PageBean<AnswerVo> data = answerService.getFollowList(pageNum, pageSize, userId);

            return Response.successResponseWithData(data);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取收藏列表失败");
            return Response.errorResponse("获取收藏列表失败");
        }
    }

    @Authorized
    @RequestMapping(value = "getFollowQuesList", method = RequestMethod.GET)
    public Response getFollowQuesList(HttpServletRequest request,
                                      @RequestParam("pageNum") int pageNum,
                                      @RequestParam("pageSize") int pageSize) {

        User user = (User) request.getAttribute("user");

        try {

            int userId = user.getId();
            PageBean<AnswerVo> data = questionService.getFollowQuestionList(userId, pageNum, pageSize);

            return Response.successResponseWithData(data);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取收藏列表失败");
            return Response.errorResponse("获取收藏列表失败");
        }
    }

    @Authorized
    @RequestMapping(value = "getMyAnswer", method = RequestMethod.GET)
    public Response getMyAnswer(HttpServletRequest request,
                                @RequestParam("pageNum") int pageNum,
                                @RequestParam("pageSize") int pageSize) {

        User user = (User) request.getAttribute("user");

        try {

            int userId = user.getId();
            PageBean<AnswerVo> data = answerService.getListByUserId(pageNum, pageSize, userId);

            return Response.successResponseWithData(data);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取我的回答失败");
            return Response.errorResponse("获取我的回答失败");
        }
    }

    @RequestMapping(value = "getUserAnswer", method = RequestMethod.GET)
    public Response getUserAnswer(@RequestParam("userId") int userId,
                                  @RequestParam("pageNum") int pageNum,
                                  @RequestParam("pageSize") int pageSize) {


        try {
            PageBean<AnswerVo> data = answerService.getListByUserId(pageNum, pageSize, userId);

            return Response.successResponseWithData(data);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取用户的回答失败");
            return Response.errorResponse("获取用户的回答失败");
        }
    }

    @Authorized
    @RequestMapping(value = "getMyArticle", method = RequestMethod.GET)
    public Response getMyArticle(HttpServletRequest request,
                                 @RequestParam("pageNum") int pageNum,
                                 @RequestParam("pageSize") int pageSize) {

        User user = (User) request.getAttribute("user");

        try {

            int userId = user.getId();
            PageBean<ArticleVo> data = articleService.getMyArticleList(userId, pageNum, pageSize);

            return Response.successResponseWithData(data);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取我的专栏失败");
            return Response.errorResponse("获取我的专栏失败");
        }
    }

    @Authorized
    @RequestMapping(value = "getUserArticle", method = RequestMethod.GET)
    public Response getUserArticle(@RequestParam("userId") int _userId,
                                   @RequestParam("pageNum") int pageNum,
                                   @RequestParam("pageSize") int pageSize,
                                   HttpServletRequest request) {

        User user = (User) request.getAttribute("user");
        int userId = user.getId();
        try {

            PageBean<ArticleVo> data = articleService.getArticleListById(userId, _userId, pageNum, pageSize);

            return Response.successResponseWithData(data);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取用户的专栏失败");
            return Response.errorResponse("获取用户的专栏失败");
        }
    }


    @Authorized
    @RequestMapping(value = "getMyQuestion", method = RequestMethod.GET)
    public Response getMyQuestion(HttpServletRequest request,
                                  @RequestParam("pageNum") int pageNum,
                                  @RequestParam("pageSize") int pageSize) {

        User user = (User) request.getAttribute("user");

        try {

            int userId = user.getId();
            PageBean<QuestionVo> data = questionService.getByUserId(pageNum, pageSize, userId);

            return Response.successResponseWithData(data);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取我的提问失败");
            return Response.errorResponse("获取我的提问失败");
        }
    }

    @Authorized
    @RequestMapping(value = "getFollowUsers", method = RequestMethod.GET)
    public Response getFollowUsers(HttpServletRequest request,
                                   @RequestParam("pageNum") int pageNum,
                                   @RequestParam("pageSize") int pageSize) {

        User user = (User) request.getAttribute("user");

        try {

            int userId = user.getId();
            PageBean<UserRelationVo> data = userService.getFollowUserList(userId, pageNum, pageSize);

            return Response.successResponseWithData(data);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取我的关注用户失败");
            return Response.errorResponse("获取我的关注用户失败");
        }
    }

    @Authorized
    @RequestMapping(value = "getFanUsers", method = RequestMethod.GET)
    public Response getFanUsers(HttpServletRequest request,
                                @RequestParam("pageNum") int pageNum,
                                @RequestParam("pageSize") int pageSize) {
        User user = (User) request.getAttribute("user");
        try {

            int userId = user.getId();
            PageBean<UserRelationVo> data = userService.getFanUserList(userId, pageNum, pageSize);

            return Response.successResponseWithData(data);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取我的粉丝用户失败");
            return Response.errorResponse("获取我的粉丝用户失败");
        }
    }

    @Authorized
    @RequestMapping(value = "getMyValue", method = RequestMethod.GET)
    public Response getMyValue(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        try {
            int userId = user.getId();
            UserValue data = userValueMapper.selectByPrimaryKey(userId);
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.errorResponse("获取积分失败");
        }
    }

    @Authorized
    @RequestMapping(value = "payForValue", method = RequestMethod.POST)
    public Response payForValue(HttpServletRequest request,
                                @RequestParam("price") Integer price,
                                @RequestParam("orderId") String orderId) {
        User user = (User) request.getAttribute("user");
        try {
            int userId = user.getId();
            UserPay userPay = new UserPay();
            userPay.setUserId(userId);
            userPay.setOrderId(orderId);
            userPay.setPrice(price);
            userPayMapper.insertSelective(userPay);
            return Response.successResponse();
        } catch (Exception e) {
            return Response.errorResponse("创建订单失败");
        }
    }


    @RequestMapping(value = "paySuccess", method = RequestMethod.POST)
    public Response paySuccess(
            @RequestParam("payTo") String payTo,
            @RequestParam("orderId") String orderId,
            @RequestParam("playerId") String playerId,
            @RequestParam("goodsId") String goodsId,
            @RequestParam("payPrice") Integer payPrice,
            @RequestParam("sign") String sign
    ) {
        try {
            UserPay userPay = userPayMapper.selectByPrimaryKey(orderId);
            if (userPay == null)
                return Response.errorResponse("订单不存在");
            int userId = userPay.getUserId();
            int value = payPrice * 10;
            userValueMapper.updateValueAdd(userId, value);
            return Response.successResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.errorResponse("充值失败");
        }
    }


    @RequestMapping(value = "isAdministrator", method = RequestMethod.GET)
    public Response isAdministrator(
            @RequestParam("id") Integer id
    ) {
        try {
          Administrator administrator =new Administrator();
          administrator=administratorMapper.selectByUserId(id);
          Boolean data;
          if(administrator!=null)
              data=true;
          else
              data=false;
            return Response.successResponseWithData(data);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.errorResponse("查询失败");
        }
    }


}
