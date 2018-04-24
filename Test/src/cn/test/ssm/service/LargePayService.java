package cn.test.ssm.service;

import java.util.Map;

import cn.test.ssm.pojo.LargePay;


/**   
 * Copyright © 2018 Talent Information Solutions Ltd. All rights reserved.
 * 
 * @ClassName: LargePayService
 * @Description: TODO(大额支付接口) 
 * @author: GK
 * @version V1.0
 */
public interface LargePayService {
	/**
	 * 组装报文
	 * @param largePay 业务要素信息
	 */
	String packageMessage(LargePay largePay);
	
	/**
	 * 拆包
	 * @return {"returnMsg","LargePay"}
	 */
	Map<String, Object> unpack();
}
