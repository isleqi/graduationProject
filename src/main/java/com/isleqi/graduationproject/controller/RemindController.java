package com.isleqi.graduationproject.controller;

import com.isleqi.graduationproject.component.common.Authorized;
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

import javax.servlet.http.HttpServletRequest;

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

    @Authorized
    @RequestMapping(value = "get",method = RequestMethod.GET)
    public Response get(HttpServletRequest request,
                        @RequestParam("pageNum") int pageNum,
                        @RequestParam("pageSize") int pageSize){
        User user= (User) request.getAttribute("user");
        try{
            PageBean<NotifyVo> data = notifyService.getNotifyList(user.getId(),pageNum,pageSize);
            return Response.successResponseWithData(data);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取通知列表失败");
            return  Response.errorResponse("获取通知列表失败");
        }
    }

    @Authorized
    @RequestMapping(value = "hadRead",method = RequestMethod.GET)
    public Response hadRead(HttpServletRequest request,
                            @RequestParam("notifyId") Integer notifyId){
        User user= (User) request.getAttribute("user");
        try{
            userNotifyMapper.hadRead(user.getId(),notifyId);
            return Response.successResponse();
        }catch (Exception e){
            e.printStackTrace();
            return Response.errorResponse("更新消息失败");
        }
    }

    @Authorized
    @RequestMapping(value = "hadReadAll",method = RequestMethod.GET)
    public Response hadRead(HttpServletRequest request){
        User user= (User) request.getAttribute("user");
        try{
            userNotifyMapper.hadReadAll(user.getId());
            return Response.successResponse();
        }catch (Exception e){
            e.printStackTrace();
            return Response.errorResponse("更新全部消息失败");
        }
    }

}
