package com.talent.forex.modules.trade;

import java.util.ArrayList;
import java.util.Collection;

import com.talent.base.BaseBo;
import com.talent.forex.dao.ForeignTradeDao;
import com.talent.forex.util.GetFixWordUtil;
/*
 * Amendment No.: FOEXAS010
 * Create By    : lzc
 * Description  : 外币对交易首页
 * Modify Date  : 2014-07-28
 */
public class ForeignManageBo extends BaseBo {

	private ForeignTradeDao foreignTradeDao;
	
	/**
	 * 查询该用户外币对账户中所有交易记录
	 */
	public Collection<?> foreignAllTradeRecordListQuery(String hql, String userNum, String tranType){
		ArrayList<String> paraList = new ArrayList<String>();
		paraList.add(userNum);
		paraList.add(tranType);
		return foreignTradeDao.getBeansByParams(hql, paraList);
	}

	/**
	 * 根据交易类型与交易状态查询相应的交易记录
	 */
	public Collection<?> tradeRecordListQuery(String hql,String userNum,String tradeStatus,String tranType){
		ArrayList<String> paraList = new ArrayList<String>();
		if (tradeStatus.equals(""))
			tradeStatus = GetFixWordUtil.getLikeWords(tradeStatus);
		paraList.add(userNum);
		paraList.add(tradeStatus);
		paraList.add(tranType);
		return foreignTradeDao.getBeansByParams(hql, paraList);
	}
	
	/**
	 * 交易详细信息查询
	 */
	public Collection<?> tradeDetailQuery(String hql, String tradeNo){
		ArrayList<String> paraList = new ArrayList<String>();
		paraList.add(tradeNo);
		return foreignTradeDao.getBeansByParams(hql, paraList);
	}
	
	public ForeignTradeDao getForeignTradeDao() {
		return foreignTradeDao;
	}

	public void setForeignTradeDao(ForeignTradeDao foreignTradeDao) {
		this.foreignTradeDao = foreignTradeDao;
	}
	
}
