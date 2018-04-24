package cn.test.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.ssm.mapper.UserMapper;
import cn.test.ssm.pojo.User;
import cn.test.ssm.service.UserService;

/**
 * Copyright © 2018 Talent Information Solutions Ltd. All rights reserved.
 * 
 * @ClassName: UserService
 * @Description: TODO(用户信息接口实现类)
 * @author: GK
 * @version V1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	public User login(User user) {
		return userMapper.loginByPwd(user);
	}
}
