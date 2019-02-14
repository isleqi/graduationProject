package com.isleqi.graduationproject.controller;

import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.domain.Answer;
import com.isleqi.graduationproject.domain.vo.AnswerVo;
import com.isleqi.graduationproject.service.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("app/answer")
public class AnswerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AnswerService answerService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Response add(Answer answer){
        try{
            answerService.addAnswer(answer);
            return Response.successResponse();
        }catch (Exception e){
            logger.info(e.getMessage());
            return  Response.errorResponse("添加答案失败");
        }

    }

    @RequestMapping(value = "getAnswerList",method = RequestMethod.GET)
    public Response getAnswerList(Integer quesId){
        try{

            List<AnswerVo> data = answerService.getListByQuesId(quesId);

            return Response.successResponseWithData(data);

        }catch (Exception e){
            logger.info(e.getMessage());
            return  Response.errorResponse("获取答案列表失败");
        }

    }



}
