package com.isleqi.graduationproject.controller;

import com.isleqi.graduationproject.component.common.RedisKeyPrefix;
import com.isleqi.graduationproject.component.common.ResponseEnmus;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.component.common.domain.Sms;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.domain.UserAuth;
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
    @Lazy
    private RedisUtil redisUtil;

    @Autowired
    private JavaMailSender mailSender;
    @Value("${mail.fromMail.addr}")
    private String from;

    @RequestMapping(value = "login/submit", method = RequestMethod.POST)
    public Response loginSubmit(@RequestBody String account, @RequestBody String password) {
        Response response = new Response();
        return response;
    }

    @ApiOperation(value = "用户提交注册", notes = "")
    @RequestMapping(value = "register/submit", method = RequestMethod.POST)
    public Response loginSubmit(@RequestBody Map<String, String> map) {
        Response response = new Response();

        User user=new User();
        UserAuth userAuth=new UserAuth();

        String account=map.get("account");                  //获取账号
        String identityType=map.get("identityType");      //获取注册类型（手机号还是邮箱）
        String userName=map.get("userName");                //获取用户名称
        String credential=map.get("password");            //获取密码

        user.setUserName(userName);
        user.setCreateTime(new Date());
        user.setEditTime(new Date());






        userAuth.setIdentityType(identityType);
        userAuth.setIdentifier(account);
        userAuth.setCredential(credential);
        userAuth.setCreateTime(new Date());
        userAuth.setEditTime(new Date());

        return response;
    }

    @ApiOperation(value = "给用户填写的手机号发送短信", notes = "")
    @PostMapping(value = "sendUserMsm")
    @ResponseBody
    public Response sendUserMsm(@RequestParam(value = "mobile") String mobile) {
        String code = (int) (Math.random() * 9000) + 1000 + "";
        Sms sms=new Sms();
        sms.setPhoneNo(mobile);
        sms.setSmsContent(code);
        Response smsResponse = SmsUtil.aliPushUserReg(sms);
        if(smsResponse ==null || ResponseEnmus.FAIL.getCode().equals(smsResponse.getCode())) {
            return Response.errorResponse("发送短信失败，请稍候再试");
        }
        //将用户的短信保存入redis中 以user_id为key
        if(redisUtil == null) {
            logger.info("redisUtil为空");
        }
        redisUtil.set(RedisKeyPrefix.SMS_KEY+mobile, code, 180l);
        Response response = Response.successResponseWithData("发送成功");
        return response;

    }

    @ApiOperation(value = "给用户填写邮箱发送验证码", notes = "")
    @PostMapping(value = "sendUserEmail")
    @ResponseBody
    public Response sendUserEmail(@RequestParam(value = "Email") String emailAccount) {
        String code = (int) (Math.random() * 9000) + 1000 + "";
        String subject = "注册验证码";
        Response response = sendSimpleMail(emailAccount, subject, code);
        if (response.getCode().equals(ResponseEnmus.SUCCESS.getCode())) {

            if (redisUtil == null) {
                logger.info("redisUtil为空");
            }

            //将用户的邮箱验证码保存在redis中
            redisUtil.set(RedisKeyPrefix.EMAIL_KEY + emailAccount,code, 180l);
        }

        return response;
    }

    public Response sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
            return Response.successResponseWithData("邮件已成功发送");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
            return Response.errorResponse("发送邮件失败：" + e);
        }

    }

    @ApiOperation(value = "验证用户验证码", notes = "")
    @PostMapping(value = "checkCode")
    @ResponseBody
    public Response checkCode(@RequestParam(value = "code") String code,@RequestParam(value = "email") String email) {
        String redisCode = (String) redisUtil.get(RedisKeyPrefix.EMAIL_KEY+email);
        if(!redisCode.equals(code)){
           Response.errorResponse("验证码错误");
        }
        return Response.successResponseWithData("验证成功");
    }


}
