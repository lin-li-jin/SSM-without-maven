package com.talent.forex.modules.trade;

import java.util.ArrayList;
import java.util.Collection;

import com.talent.base.BaseBo;
import com.talent.forex.dao.ForeignTradeDao;
import com.talent.forex.util.GetFixWordUtil;
/*
 * Amendment No.: FOEXAS010
 * Create By    : lzc
 * Description  : ��ҶԽ�����ҳ
 * Modify Date  : 2014-07-28
 */
public class ForeignManageBo extends BaseBo {

	private ForeignTradeDao foreignTradeDao;
	
	/**
	 * ��ѯ���û���Ҷ��˻������н��׼�¼
	 */
	public Collection<?> foreignAllTradeRecordListQuery(String hql, String userNum, String tranType){
		ArrayList<String> paraList = new ArrayList<String>();
		paraList.add(userNum);
		paraList.add(tranType);
		return foreignTradeDao.getBeansByParams(hql, paraList);
	}

	/**
	 * ���ݽ��������뽻��״̬��ѯ��Ӧ�Ľ��׼�¼
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
	 * ������ϸ��Ϣ��ѯ
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
