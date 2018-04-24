package cn.test.ssm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.test.ssm.pojo.LargePay;
import cn.test.ssm.service.LargePayService;

/**   
 * Copyright © 2018 Talent Information Solutions Ltd. All rights reserved.
 * 
 * @ClassName: LargePayController
 * @Description: TODO(大额支付管理控制器) 
 * @author: GK
 * @version V1.0
 */
@Controller
@RequestMapping("/laygePay")
public class LargePayController {
	
	@Autowired
	private LargePayService largePayService;

	/**
	 * 转向信息录入界面
	 */
	@RequestMapping("/toInput.action")
	public String toInput() {
		return "jsp/largePay/largePayInput";
	}

	/**
	 * 组装报文
	 */
	@RequestMapping("/packageMessage.action")
	public String packageMessage(LargePay largePay, Model model) {
		model.addAttribute("retrunMsg", largePayService.packageMessage(largePay));
		return "jsp/largePay/largePayInput";
	}
	
	/**
	 * 拆包
	 */
	@RequestMapping("/unpack.action")
	public String unpack(Model model) {
		Map<String, Object> map = largePayService.unpack();
		model.addAttribute("retrunMsg", map.get("retrunMsg"));
		model.addAttribute("model", map.get("largePay"));
		return "jsp/largePay/largePayDetail";
	}
	
}
