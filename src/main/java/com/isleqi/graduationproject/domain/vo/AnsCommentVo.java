package com.isleqi.graduationproject.domain.vo;

import com.isleqi.graduationproject.domain.User;
import lombok.Data;

import java.util.Date;

@Data
public class AnsCommentVo {
    private Integer id;

    private Integer ansId;

    private Integer userId;

    private Integer likeNum;

    private Date creatTime;

    private String commentContent;

    private User user;

}
