package cn.test.ssm.pojo;

/**
 * Copyright © 2018 Talent Information Solutions Ltd. All rights reserved.
 * 
 * @ClassName: User
 * @Description: TODO(用户信息pojo)
 * @author: GK
 * @version V1.0
 */
public class User {
	/**
	 * 主键ID，自增
	 */
	private Integer id;

	/**
	 * 登录用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
