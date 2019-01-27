package com.isleqi.graduationproject.controller;

import com.isleqi.graduationproject.component.common.domain.Response;
import com.isleqi.graduationproject.domain.Question;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/question")
public class QuestionController {

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public Response addQestion(@RequestParam Question question){

        return null;
    }





}
