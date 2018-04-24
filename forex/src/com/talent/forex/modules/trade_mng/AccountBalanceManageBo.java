package com.talent.forex.modules.trade_mng;

import java.util.Collection;

import org.hibernate.criterion.MatchMode;

import com.talent.base.BaseBo;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS023
 * Create By    : lzc
 * Description  : 账户余额管理
 * Modify Date  : 2014-08-05
 */
public class AccountBalanceManageBo extends BaseBo {

	private AccInfoDao accInfoDao;

	/**
	 * 获得用户账户信息
	 */
	public Collection<?> accountBalanceQuery(){
		AccInfo accInfo = new AccInfo();
		accInfo.setUserNum(UserModelUtil.getUser().getUserId());
		return accInfoDao.getBeansByBean(accInfo, MatchMode.EXACT);
	}

	public AccInfoDao getAccInfoDao() {
		return accInfoDao;
	}

	public void setAccInfoDao(AccInfoDao accInfoDao) {
		this.accInfoDao = accInfoDao;
	}
	
}
