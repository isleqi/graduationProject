package com.isleqi.graduationproject.component.common;

import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.util.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

@Aspect
@Component
public class AuthorizedAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Lazy
    RedisUtil redisUtil;




    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
    }

    @Pointcut("execution(*  com.isleqi.graduationproject.controller.*Controller.*(..))")
    public void methodPointCut() {
    }

    /**
     * 某个方法执行前进行请求合法性认证 注入Authorized注解 （先）
     */
    @Before("requestMapping() && methodPointCut()&&@annotation(authorized)")
    public void doBefore(JoinPoint joinPoint, Authorized authorized) throws Exception {

        logger.info("方法认证开始...");

        Class type = joinPoint.getSignature().getDeclaringType();

        Annotation[] annotations = type.getAnnotationsByType(Authorized.class);

        if (annotations != null && annotations.length > 0) {
            logger.info("直接类认证");
            return;
        }

        //获取当前http请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String token = request.getHeader("token");

        Object info=redisUtil.get(RedisKeyPrefix.USER_TOKEN + token);
        User user;
        if(info instanceof User){
            user=(User)info;
            request.setAttribute("user",user);
        }else {
           throw new Exception("未登录或token失效");
        }


    }



}