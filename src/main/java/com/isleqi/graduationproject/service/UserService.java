package com.isleqi.graduationproject.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.isleqi.graduationproject.domain.User;


/**
 *用户
 */
public interface UserService{

	User findByUsername(String username);

	//Return changePwd(User entity, String pwd);

	Page<User> listByPage(Map<String, String> params,Pageable pageable);

	//Return saveUser(User user);
	
}
