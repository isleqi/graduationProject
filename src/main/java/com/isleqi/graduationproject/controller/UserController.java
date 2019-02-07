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

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("app/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    @Lazy
    private RedisUtil redisUtil;

    @RequestMapping(value="getBaseUserInfo",method =RequestMethod.POST)
    public Response getBaseUserInfo(@RequestParam("token") String token){
       String data= (String)redisUtil.get(RedisKeyPrefix.USER_TOKEN+token);
       System.out.println((data+"!!!!!!"));
       if(data==null||"".equals(data)){
           JSONObject msg=new JSONObject();
           msg.put("code","403");
           msg.put("msg","token失效");
           return Response.successResponseWithData(msg);
       }
       else{
           redisUtil.expire(RedisKeyPrefix.USER_TOKEN+token,Constant.JWT_TTL);
           UserInfoVo userInfoVo=userService.findUserInfoByIdentifiter(data);
           return Response.successResponseWithData(userInfoVo);
       }

    }








}
