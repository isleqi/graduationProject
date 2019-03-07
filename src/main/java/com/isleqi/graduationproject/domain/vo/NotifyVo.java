package com.isleqi.graduationproject.domain.vo;

import com.isleqi.graduationproject.domain.User;
import lombok.Data;

import java.util.Date;

@Data
public class NotifyVo {
    private User sendUser;
    private String content;
    private String type;
    private Integer targetType;
    private Integer targetId;
    private Integer sendUserId;
    private Object target;
    private Date createTime;
}
