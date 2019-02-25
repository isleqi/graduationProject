package com.isleqi.graduationproject.service.impl;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.isleqi.graduationproject.component.common.PageBean;
import com.isleqi.graduationproject.dao.mappers.UserAuthMapper;
import com.isleqi.graduationproject.dao.mappers.UserMapper;
import com.isleqi.graduationproject.dao.mappers.UserRelationsMapper;
import com.isleqi.graduationproject.domain.UserAuth;
import com.isleqi.graduationproject.domain.UserRelationsKey;
import com.isleqi.graduationproject.domain.vo.UserInfoVo;
import com.isleqi.graduationproject.domain.vo.UserRelationVo;
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
	@Autowired
	private UserRelationsMapper userRelationsMapper;


	@Override
	public User findByUsername(String username) {

		return null;
	}

	@Override
	public User findByUserId(Integer id) {

		return userMapper.selectByPrimaryKey(id);
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
		return userId;

	}

	@Override
	public List<Integer> getFollowIds(Integer userId) {
		return userRelationsMapper.getFollowIds(userId);
	}

	@Override
	public List<Integer> getFanIds(Integer userId) {
		return userRelationsMapper.getFanIds(userId);
	}

	@Override
	public PageBean<UserRelationVo> getFollowUserList(int userId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserRelationVo> list=null;
		try{
			list=userRelationsMapper.getFollowUsers(userId);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			PageHelper.clearPage();
		}
		PageBean<UserRelationVo> info = new PageBean<>(list);

		return info;	}

	@Override
	public PageBean<UserRelationVo> getFanUserList(int userId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserRelationVo> list=null;
		try{
			list=userRelationsMapper.getFanUsers(userId);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			PageHelper.clearPage();
		}
		PageBean<UserRelationVo> info = new PageBean<>(list);

		return info;	}


}
