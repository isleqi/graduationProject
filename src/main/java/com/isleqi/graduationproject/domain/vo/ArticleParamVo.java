package com.isleqi.graduationproject.domain.vo;

import lombok.Data;

@Data
public class ArticleParamVo {
    private Integer userId;

    private String articleTitle;

    private String articleContent;

    private Integer type;

    private Integer value;


}
