package com.isleqi.graduationproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.isleqi.graduationproject.component.common.*;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.dao.mappers.UserValueMapper;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.domain.UserAuth;
import com.isleqi.graduationproject.domain.UserValue;
import com.isleqi.graduationproject.domain.vo.UserInfoVo;
import com.isleqi.graduationproject.service.UserService;
import com.isleqi.graduationproject.util.HttpClientUtil;
import com.isleqi.graduationproject.util.JWTUtil;
import com.isleqi.graduationproject.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    UserValueMapper userValueMapper;
    @Value("${web.url}")
    String url;

    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public Response loginSubmit(@RequestParam("account") String account, @RequestParam("password") String password) {
        Response response = new Response();
       UserAuth userAuth= userService.findUserAuthByIdentifier(account);
       if(userAuth==null)
           return Response.errorResponse("该账号未注册，请注册");
       String psw=userAuth.getCredential();

       if(psw.equals(password)){
           UserInfoVo userInfoVo= userService.findUserInfoByIdentifiter(account);
           User user =userInfoVo.getUser();
           String subject= JWTUtil.generalSubject(user);
           String token;
           try {
               token= JWTUtil.createJWT(Constant.JWT_ID,subject,Constant.JWT_TTL);
           }catch (Exception e){
               logger.debug("生成token失败："+e.toString());
               return Response.errorResponse("登录失败");
           }
           //将token放入redis
           if(redisUtil == null) {
               logger.info("redisUtil为空");
           }
           redisUtil.set(RedisKeyPrefix.USER_TOKEN+token,user,Constant.JWT_TTL);
           return Response.successResponseWithData(token);
       }
       else {
           return  Response.errorResponse("密码错误");
       }

    }

    @RequestMapping(value = "githubOauth",method =RequestMethod.GET)
    public ModelAndView githubOauth(@RequestParam("code") String code){
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




           UserAuth userAuth= userService.findUserAuthByIdentifier(id);

            if(userAuth==null){
                UserAuth _userAuth=new UserAuth();

                _userAuth.setIdentityType("GitHub");
                _userAuth.setIdentifier(id);
                _userAuth.setCredential(token);
               int userId= userService.saveUser(userInfo,_userAuth);
                UserValue userValue=new UserValue();
                userValue.setUserId(userId);
                userValueMapper.insertSelective(userValue);
               if(userId==0){
                   logger.info("GitHub用户信息保存失败");

               }
                userInfo.setId(userId);

            }
            else{
                   userAuth.setCredential(token);
                   userService.updateUserAuth(userAuth);
                userInfo.setId(userAuth.getUserId());
            }
            redisUtil.set(RedisKeyPrefix.USER_TOKEN+token,userInfo,Constant.JWT_TTL);   //将token存入redis中

            ModelAndView mv = new ModelAndView();
            Map<String, String> data=new HashMap();
            data.put("token",token);
            data.put("code","200");
            mv.setViewName("success");
            mv.addObject("data", data);
            return mv;

        }catch (Exception e){
            logger.info("github登录失败："+e.getMessage());

            ModelAndView mv = new ModelAndView();
            Map<String, String> data=new HashMap<String, String>();
            data.put("code","500");
            mv.setViewName("error");
            mv.addObject("data", data);
            return mv;
        }



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
            JSONObject msg= JSONObject.parseObject(SinaOauth2.getAccessToken(code,url));
            String token=msg.getString("access_token");
            String uid=msg.getString("uid");
            String getUser=SinaOauth2.getUser(token,uid);
            JSONObject user= JSONObject.parseObject(getUser);
            System.out.println(getUser);

            String userName=user.getString("name");
            String id=user.getString("idstr");
            String avatar_url=user.getString("avatar_large");
            String description=user.getString("description");

            logger.info(token);
            User userInfo=new User();

            userInfo.setUserName(userName);
            userInfo.setUserIconUrl(avatar_url);

            UserAuth userAuth= userService.findUserAuthByIdentifier(id);

            if(userAuth==null){
                UserAuth _userAuth=new UserAuth();


                _userAuth.setIdentityType("Sina");
                _userAuth.setIdentifier(id);
                _userAuth.setCredential(token);
                int userId= userService.saveUser(userInfo,_userAuth);
                UserValue userValue=new UserValue();
                userValue.setUserId(userId);
                userValueMapper.insertSelective(userValue);
                if(userId==0){
                    logger.info("Sina用户信息保存失败");

                }

            }
            else{
                userAuth.setCredential(token);
                userService.updateUserAuth(userAuth);
                userInfo.setId(userAuth.getUserId());
            }



            redisUtil.set(RedisKeyPrefix.USER_TOKEN+token,userInfo,Constant.JWT_TTL);   //将token存入redis中



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
        String url_=SinaOauth2.getAuthUrl(url);
        response.sendRedirect(url_);
    }

    @RequestMapping(value = "wechatOauth",method =RequestMethod.GET)
    public Response wechatOauth(@RequestParam("code") String code){



        return Response.successResponseWithData("Github登录成功");


    }


}
