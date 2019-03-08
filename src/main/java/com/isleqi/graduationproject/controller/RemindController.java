package com.isleqi.graduationproject.controller;

import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.component.common.RedisKeyPrefix;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.dao.mappers.UserNotifyMapper;
import com.isleqi.graduationproject.domain.Notify;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.domain.vo.NotifyVo;
import com.isleqi.graduationproject.service.NotifyService;
import com.isleqi.graduationproject.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/remind")
public class RemindController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NotifyService notifyService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserNotifyMapper userNotifyMapper;

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public Response get(@RequestHeader("token") String token, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize){
        Object info=redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
        User user;
        if(info instanceof User){
            user=(User)info;
        }else {
            return Response.errorResponse("token失效，请重新登录");
        }
        try{
            PageBean<NotifyVo> data = notifyService.getNotifyList(user.getId(),pageNum,pageSize);
            return Response.successResponseWithData(data);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取通知列表失败");
            return  Response.errorResponse("获取通知列表失败");
        }
    }

    @RequestMapping(value = "hadRead",method = RequestMethod.GET)
    public Response hadRead(@RequestHeader("token") String token,@RequestParam("notifyId") Integer notifyId){
        Object info=redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
        User user;
        if(info instanceof User){
            user=(User)info;
        }else {
            return Response.errorResponse("token失效，请重新登录");
        }
        try{
            userNotifyMapper.hadRead(user.getId(),notifyId);
            return Response.successResponse();
        }catch (Exception e){
            e.printStackTrace();
            return Response.errorResponse("更新消息失败");
        }
    }

    @RequestMapping(value = "hadReadAll",method = RequestMethod.GET)
    public Response hadRead(@RequestHeader("token") String token){
        Object info=redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
        User user;
        if(info instanceof User){
            user=(User)info;
        }else {
            return Response.errorResponse("token失效，请重新登录");
        }
        try{
            userNotifyMapper.hadReadAll(user.getId());
            return Response.successResponse();
        }catch (Exception e){
            e.printStackTrace();
            return Response.errorResponse("更新全部消息失败");
        }
    }

}
