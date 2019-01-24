package com.isleqi.graduationproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.isleqi.graduationproject.component.common.GithubOauth2;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("app/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
        }catch (Exception e){
            logger.debug("github登录失败："+e.toString());
            return  Response.errorResponse("GitHub验证失败");

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
