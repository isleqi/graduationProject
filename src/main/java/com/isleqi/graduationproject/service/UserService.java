package com.isleqi.graduationproject.service;

import java.util.Map;

import com.isleqi.graduationproject.domain.UserAuth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.isleqi.graduationproject.domain.User;


/**
 *用户
 */
public interface UserService{

	User findByUsername(String username);

	User findByUserId(Integer id);

	UserAuth findUserAuthByIdentifier(String identifier);

	int updateUser(User user);

	int updateUserAuth(UserAuth userAuth);

	Page<User> listByPage(Map<String, String> params,Pageable pageable);

	int saveUser(User user , UserAuth userAuth);


	
}
