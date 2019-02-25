package com.isleqi.graduationproject.domain.vo;

import com.isleqi.graduationproject.domain.User;
import lombok.Data;

@Data
public class UserRelationVo {
    private  Integer userId;

    private  Integer followId;

    private User user;
}
