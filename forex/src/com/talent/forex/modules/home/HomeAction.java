package com.talent.forex.modules.home;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.opensymphony.webwork.ServletActionContext;
import com.talent.auth.bean.model.UserModel;
import com.talent.auth.login.LoginConst;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.core.ForexBaseAction;
/*
 * Amendment No.: FOEXAS009
 * Create By    : lzc
 * Description  : 交易首页
 * Modify Date  : 2014-07-21
 */
public class HomeAction  extends ForexBaseAction{
	
	private HomeManageBo homeManageBo;
	
	public HomeAction(){}
	
	/**
	 * 首页面初始化数据
	 * @return
	 */
	public String pageInit(){
		HttpSession session = ServletActionContext.getRequest().getSession();  
		UserModel user = (UserModel)session.getAttribute(LoginConst.LOGIN_USER_MODEL);
		List<AccInfo> accInfoListC = homeManageBo.getAccInfoListByUserNo(user.getUserId(),"C");
		requestPut("accInfoListC",accInfoListC);
		List<AccInfo> accInfoListW = homeManageBo.getAccInfoListByUserNo(user.getUserId(),"W");
		requestPut("accInfoListW",accInfoListW);
		List<AccInfo> accInfoListB = homeManageBo.getAccInfoListByUserNo(user.getUserId(),"B");
		requestPut("accInfoListB",accInfoListB);
		
		if(accInfoListC.size()>0){
			String accnoC = accInfoListC.get(0).getAccno();
			requestPut("accnoC", accnoC);
		}
		if(accInfoListB.size()>0){
			String accnoB = accInfoListB.get(0).getAccno();
			requestPut("accnoB", accnoB);
		}
		if(accInfoListW.size()>0){
			String accnoW = accInfoListW.get(0).getAccno();
			requestPut("accnoW", accnoW);
		}
		int message = homeManageBo.getMessage(user.getUserId());
		requestPut("message", message);
		return SUCCESS;
	}
	
	public HomeManageBo gethomeManageBo() {
		return homeManageBo;
	}

	public void sethomeManageBo(HomeManageBo homeManageBo) {
		this.homeManageBo = homeManageBo;
	}
	
}
