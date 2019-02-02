package com.isleqi.graduationproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.dao.mappers.TagMapper;
import com.isleqi.graduationproject.domain.Question;
import com.isleqi.graduationproject.domain.Tag;
import com.isleqi.graduationproject.domain.vo.QuestionParamVo;
import com.isleqi.graduationproject.domain.vo.QuestionVo;
import com.isleqi.graduationproject.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("app/question")
public class QuestionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TagMapper tagMapper;
    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Response addQuestion(@RequestBody QuestionParamVo questionParamVo){
        try {

            questionService.insert(questionParamVo);

            return  Response.successResponse();
        }catch (Exception e){
            e.printStackTrace();
            return Response.errorResponse("添加问题失败");
        }
    }

    @RequestMapping(value = "getAllQuestion",method = RequestMethod.GET)
    public Response getAllQuestion(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        try {
            PageBean<QuestionVo> data=questionService.getQuestionList(pageNum,pageSize);
            return  Response.successResponseWithData(data);
        }catch (Exception e){
            e.getStackTrace();

            return Response.errorResponse("获取全部问题失败");
        }

    }

    @RequestMapping(value = "getByTag",method = RequestMethod.GET)
    public Response getByTag(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("tagId") int tagId){
        try {
            PageBean<QuestionVo> data=questionService.getByTagId(pageNum,pageSize,tagId);
            return  Response.successResponseWithData(data);
        }catch (Exception e){
            return Response.errorResponse("获取问题失败");
        }

    }

    @RequestMapping(value = "getByStr",method = RequestMethod.GET)
    public Response getByStr(@RequestParam Question question){

        return null;
    }

    @RequestMapping(value = "tag/add",method = RequestMethod.GET)
    public Response addTag(@RequestParam("tagName") String tagName){
        try{
            Tag tag=new Tag();
            tag.setTagName(tagName);
            tagMapper.insertSelective(tag);
            JSONObject data=new JSONObject();
            data.put("tagId",tag.getId());
            return  Response.successResponseWithData(data);
        }catch (Exception e){
            return Response.errorResponse("添加标签失败");
        }

    }

    @RequestMapping(value = "tag/get",method = RequestMethod.GET)
    public Response getTag(@RequestParam("str") String str){

        return null;
    }









}
