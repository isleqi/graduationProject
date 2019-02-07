package com.isleqi.graduationproject.service.impl;

import java.util.Map;

import com.isleqi.graduationproject.dao.mappers.UserAuthMapper;
import com.isleqi.graduationproject.dao.mappers.UserMapper;
import com.isleqi.graduationproject.domain.UserAuth;
import com.isleqi.graduationproject.domain.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.service.UserService;


@Transactional
@Service("userService")
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserAuthMapper userAuthMapper;


	@Override
	public User findByUsername(String username) {

		return null;
	}

	@Override
	public User findByUserId(Integer id) {
		return null;
	}

	@Override
	public UserInfoVo findUserInfoByIdentifiter(String identifier) {
		return userAuthMapper.selectUserInfoByIdentifier(identifier);
	}


	@Override
	public UserAuth findUserAuthByIdentifier(String identifier) {
		return userAuthMapper.selectByIdentifier(identifier);
	}


	@Override
	public int updateUser(User user) {
		int result=userMapper.updateByPrimaryKeySelective(user);
		return result;
	}

	@Override
	public int updateUserAuth(UserAuth userAuth) {
		int result=userAuthMapper.updateByPrimaryKeySelective(userAuth);
		return result;
	}


	@Override
	@Transactional
	public int saveUser(User user, UserAuth userAuth) {
		int result=0;
		userMapper.insertSelective(user);
		int userId=user.getId();
		userAuth.setUserId(userId);
		result=userAuthMapper.insertSelective(userAuth);
		return result;

	}


}
