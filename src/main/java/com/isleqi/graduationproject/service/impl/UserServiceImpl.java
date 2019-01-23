package com.isleqi.graduationproject.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isleqi.graduationproject.component.common.dao.BaseDao;
import com.isleqi.graduationproject.dao.UserDao;
import com.isleqi.graduationproject.domain.User;
import com.isleqi.graduationproject.service.UserService;


@Transactional
@Service("userService")
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserDao userDao;


	@Override
	public User findByUsername(String username) {
		return null;
	}



	@Override
	public Page<User> listByPage(Map<String, String> params, Pageable pageable) {
		return null;
	}


}
