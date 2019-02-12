package com.isleqi.graduationproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.isleqi.graduationproject.component.common.Constant;
import com.isleqi.graduationproject.component.common.RedisKeyPrefix;
import com.isleqi.graduationproject.component.common.ResponseEnmus;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.component.common.domain.Sms;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.domain.UserAuth;
import com.isleqi.graduationproject.domain.vo.UserInfoVo;
import com.isleqi.graduationproject.service.UserService;
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

    @RequestMapping(value = "uploadAvatar",method = RequestMethod.POST)
    public Response uploadAvatar(@RequestParam("file") MultipartFile file){
        try{
         String fileName= FileUtil.saveImg(file,localAvatarPath);
         String path=avatarPath+fileName;
         return Response.successResponseWithData(path);

        }catch (Exception e){
            e.printStackTrace();
            logger.info("上传头像失败");
            return Response.errorResponse("上传头像失败");
        }

    }

    @RequestMapping(value = "uploadImage",method = RequestMethod.POST)
    public Response uploadAvatar(@RequestParam("files") MultipartFile[] files){
        List<String> paths=new ArrayList<>();
        try{
            for(MultipartFile file:files){
                String fileName= FileUtil.saveImg(file,localImagePath);
                String path=imagePath+fileName;
                paths.add(path);
            }

            return Response.successResponseWithData(paths);

        }catch (Exception e){
            e.printStackTrace();
            logger.info("上传图片失败");
            return Response.errorResponse("上传图片失败");
        }

    }

    @RequestMapping(value = "getBaseUserInfo", method = RequestMethod.POST)
    public Response getBaseUserInfo(String token) {
        logger.info(token);
        // String token_=JSONObject.parseObject(token).getString("token");
        User data = (User) redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
        System.out.println((data + "!!!!!!"));
        if (data == null || "".equals(data)) {
            JSONObject msg = new JSONObject();
            msg.put("code", 403);
            msg.put("msg", "token失效");
            return Response.successResponseWithData(msg);
        } else {
            redisUtil.expire(RedisKeyPrefix.USER_TOKEN + token, Constant.JWT_TTL);
            User userInfoVo = userService.findByUserId(data.getId());
            return Response.successResponseWithData(userInfoVo);
        }
    }

    @RequestMapping(value = "updateUserInfo", method = RequestMethod.GET)
    public Response updateUserInfo(@RequestParam String userName, @RequestParam String userDes, @RequestParam String userIconUrl) {
        return null;
    }


}
