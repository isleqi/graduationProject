package com.isleqi.graduationproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.isleqi.graduationproject.component.common.GithubOauth2;
import com.isleqi.graduationproject.component.common.RedisKeyPrefix;
import com.isleqi.graduationproject.component.common.SinaOauth2;
import com.isleqi.graduationproject.component.common.WeChatOauth2;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


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


            User userInfo=new User();

            userInfo.setUserName(userName);
            userInfo.setUserIconUrl(avatar_url);


            redisUtil.set(RedisKeyPrefix.USER_TOKEN+token,id);   //将token存入redis中

           UserAuth userAuth= userService.findUserAuthByIdentifier(id);

            if(userAuth==null){
                UserAuth _userAuth=new UserAuth();

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
            logger.info("github登录失败："+e.getMessage());
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

    @RequestMapping(value = "sinaOauth",method =RequestMethod.GET)
    public ModelAndView sinaOauth(@RequestParam("code") String code, HttpServletResponse response){
        try{
            JSONObject msg= JSONObject.parseObject(SinaOauth2.getAccessToken(code));
            String token=msg.getString("access_token");
            String uid=msg.getString("uid");
            String getUser=SinaOauth2.getUser(token,uid);
            JSONObject user= JSONObject.parseObject(getUser);
            System.out.println(getUser);

            String userName=user.getString("name");
            String id=user.getString("id");
            String avatar_url=user.getString("avatar_large");
            String description=user.getString("description");




            UserAuth userAuth= userService.findUserAuthByIdentifier(id);

            if(userAuth==null){
                UserAuth _userAuth=new UserAuth();

                User userInfo=new User();

                userInfo.setUserName(userName);
                userInfo.setUserIconUrl(avatar_url);

                _userAuth.setIdentityType("Sina");
                _userAuth.setIdentifier(id);
                _userAuth.setCredential(token);
                int result= userService.saveUser(userInfo,_userAuth);
                if(result==0){
                    logger.info("Sina");
                }
            }
            else{
                userAuth.setCredential(token);
                userService.updateUserAuth(userAuth);
            }



            redisUtil.set(RedisKeyPrefix.USER_TOKEN+token,id);   //将token存入redis中



            ModelAndView mv = new ModelAndView();
            Map<String, String> data=new HashMap<String, String>();
            data.put("userName",userName);
            data.put("avatarUrl",avatar_url);
            data.put("description",description);
            data.put("token",token);
            data.put("code","200");
            mv.setViewName("/success");
            mv.addObject("data", data);
            return mv;

        }catch (Exception e){
            logger.info("Sina登录失败："+e.getMessage());
            ModelAndView mv = new ModelAndView();
            Map<String, String> data=new HashMap<String, String>();
            data.put("code","500");
            mv.setViewName("/error");
            mv.addObject("data", data);
            return mv;
        }


    }

    @RequestMapping(value = "FromSina",method =RequestMethod.GET)
    public void fromSina(HttpServletResponse response) throws IOException {
        String url=SinaOauth2.getAuthUrl();
        response.sendRedirect(url);
    }

    @RequestMapping(value = "wechatOauth",method =RequestMethod.GET)
    public Response wechatOauth(@RequestParam("code") String code){
      //  try{
//            String access_token=  WeChatOauth2.getAccessToken(code);
//            System.out.println(access_token);
//
//            String getUser=WeChatOauth2.getUser(token);
//            JSONObject user= JSONObject.parseObject(getUser);
//            System.out.println(getUser);
//
//
//
//            String userName=user.getString("login");
//            String id=user.getString("id");
//            String avatar_url=user.getString("avatar_url");
//
//
//
//            redisUtil.set(RedisKeyPrefix.USER_TOKEN+id,token);   //将token存入redis中
//
//            UserAuth userAuth= userService.findUserAuthByIdentifier(id);
//
//            if(userAuth==null){
//                UserAuth _userAuth=new UserAuth();
//
//                User userInfo=new User();
//
//                userInfo.setUserName(userName);
//                userInfo.setUserIconUrl(avatar_url);
//
//                _userAuth.setIdentityType("GitHub");
//                _userAuth.setIdentifier(id);
//                _userAuth.setCredential(token);
//                int result= userService.saveUser(userInfo,_userAuth);
//                if(result==0){
//                    logger.info("GitHub用户信息保存失败");
//                    return  Response.errorResponse("GitHub用户信息保存失败");
//                }
//            }
//            else{
//                userAuth.setCredential(token);
//                userService.updateUserAuth(userAuth);
//            }
//
//        }catch (Exception e){
//            logger.info("github登录失败："+e.getMessage());
//            // System.out.println(e.toString());
//            return  Response.errorResponse("github登录失败");

            System.out.println(code);

       // }

        return Response.successResponseWithData("Github登录成功");


    }

//    @RequestMapping(value = "FromGithub",method =RequestMethod.GET)
//    public void fromGithub(HttpServletResponse response) throws IOException {
//        // HttpClientUtil.doGet(GithubOauth2.getAuthUrl());
//        String url=GithubOauth2.getAuthUrl();
//        response.sendRedirect(url);
//        // return "redirect:"+url;
//    }



}
