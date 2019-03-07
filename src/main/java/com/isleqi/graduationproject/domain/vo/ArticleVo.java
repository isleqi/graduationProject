package com.isleqi.graduationproject.domain.vo;

import com.isleqi.graduationproject.domain.User;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleVo {
    private Integer articleId;

    private Integer userId;

    private Integer type;

    private Integer value;

    private Integer likeNum;

    private Integer commentNum;

    private Integer browse;

    private Date creatTime;

    private Date editTime;

    private String articleTitle;

    private String articleContent;

    private User user;

    private  Boolean hasPay;

    private Boolean myArticle;

}
