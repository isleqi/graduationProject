package com.isleqi.graduationproject.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/column")
public class ColumnController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
