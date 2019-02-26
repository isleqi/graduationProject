package com.isleqi.graduationproject.domain.vo;

import com.isleqi.graduationproject.domain.User;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleCommentVo {
    private Integer id;

    private Integer articleId;

    private Integer userId;

    private Integer likeNum;

    private Integer replyNum;

    private Date creatTime;

    private String commentContent;

    private User user;
}
