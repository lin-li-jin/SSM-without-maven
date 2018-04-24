package com.talent.forex.modules.home;

import java.util.List;

import com.talent.base.BaseBo;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.dao.HomePageDao;
/*
 * Amendment No.: FOEXAS009
 * Create By    : lzc
 * Description  : 交易首页
 * Modify Date  : 2014-07-21
 * 
 */
public class HomeManageBo extends BaseBo {

	private HomePageDao homePageDao;
	
	public HomeManageBo() {

	}
	
	public HomePageDao getHomePageDao() {
		return homePageDao;
	}

	public void setHomePageDao(HomePageDao homePageDao) {
		this.homePageDao = homePageDao;
	}

	/**
	 * 根据用户名获得accInfo实体列表
	 * @return
	 */
	public List<AccInfo> getAccInfoListByUserNo(String userNo,String accType){
		return homePageDao.getAccInfoListByUserNo(userNo,accType);
	}
	
	public int getMessage(String userNo){
		return homePageDao.getMessage(userNo);
	}
}
