package cn.test.ssm.mapper;

import cn.test.ssm.pojo.User;

/**
 * Copyright © 2018 Talent Information Solutions Ltd. All rights reserved.
 * 
 * @ClassName: UserMapper
 * @Description: TODO(用户管理数据库接口)
 * @author: GK
 * @version V1.0
 */
public interface UserMapper {
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	User loginByPwd(User user);
}
