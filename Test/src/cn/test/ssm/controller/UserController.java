package cn.test.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.test.ssm.pojo.User;
import cn.test.ssm.service.UserService;

/**
 * Copyright © 2018 Talent Information Solutions Ltd. All rights reserved.
 * 
 * @ClassName: UserController
 * @Description: TODO(用户管理控制器)
 * @author: GK
 * @version V1.0
 */
@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/login.action")
	public String login(User user, Model model, HttpSession session) {
		User sessionUser = (User)session.getAttribute("loginUser");
		if(null != sessionUser){
			return "index";
		}
		User user2 = userService.login(user);
		if (user2 == null) {
			model.addAttribute("info", "登录失败！用户名或者密码错误！");
			return "forward:/login.jsp";
		} else {
			session.setAttribute("loginUser", user2);
			return "index";
		}
	}
}
