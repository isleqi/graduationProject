package com.isleqi.graduationproject.domain.vo;

import com.isleqi.graduationproject.domain.User;
import lombok.Data;

@Data
public class UserInfoVo {
    private  Integer id;
    private String identifier;
    private  String UserId;
    private User user;
}
