package com.isleqi.graduationproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.isleqi.graduationproject.component.common.GithubOauth2;
import com.isleqi.graduationproject.component.common.RedisKeyPrefix;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.domain.UserAuth;
import com.isleqi.graduationproject.service.UserService;
import com.isleqi.graduationproject.util.HttpClientUtil;
import com.isleqi.graduationproject.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("app/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserService userService;

    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public Response loginSubmit(@RequestBody String account, @RequestBody String password) {
        Response response = new Response();
        return response;
    }

    @RequestMapping(value = "githubOauth",method =RequestMethod.GET)
    public Response githubOauth(@RequestParam("code") String code){
        try{
            String access_token=  GithubOauth2.getAccessToken(code);
            System.out.println(access_token);
            String token=access_token.split("&")[0].split("=")[1];
            String getUser=GithubOauth2.getUser(token);
            JSONObject user= JSONObject.parseObject(getUser);
            System.out.println(getUser);



            String userName=user.getString("login");
            String id=user.getString("id");
            String avatar_url=user.getString("avatar_url");



            redisUtil.set(RedisKeyPrefix.USER_TOKEN+id,token);   //将token存入redis中

           UserAuth userAuth= userService.findUserAuthByIdentifier(id);

            if(userAuth==null){
                UserAuth _userAuth=new UserAuth();

                User userInfo=new User();

                userInfo.setUserName(userName);
                userInfo.setUserIconUrl(avatar_url);

                _userAuth.setIdentityType("GitHub");
                _userAuth.setIdentifier(id);
                _userAuth.setCredential(token);
               int result= userService.saveUser(userInfo,_userAuth);
               if(result==0){
                   logger.info("GitHub用户信息保存失败");
                 return  Response.errorResponse("GitHub用户信息保存失败");
               }
            }
            else{
                   userAuth.setCredential(token);
                   userService.updateUserAuth(userAuth);
            }

        }catch (Exception e){
            logger.info("github登录失败："+e.toString());
           // System.out.println(e.toString());
            return  Response.errorResponse("github登录失败");

        }

        return Response.successResponseWithData("Github登录成功");


    }

    @RequestMapping(value = "FromGithub",method =RequestMethod.GET)
    public void fromGithub(HttpServletResponse response) throws IOException {
       // HttpClientUtil.doGet(GithubOauth2.getAuthUrl());
        String url=GithubOauth2.getAuthUrl();
        response.sendRedirect(url);
       // return "redirect:"+url;
    }
}
