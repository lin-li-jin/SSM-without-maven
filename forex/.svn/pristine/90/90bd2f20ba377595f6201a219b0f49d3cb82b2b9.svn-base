package com.talent.forex.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.util.ForwardUtil;
import com.talent.forex.util.SpotUtil;
import com.talent.forex.util.SwapUtil;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.util.HibernateUtil;

public class HomePageDao extends AbstractDaoImpl{

	@SuppressWarnings("unchecked")
	public List<AccInfo> getAccInfoListByUserNo(String userNo,String accType){
		Session session = HibernateUtil.getSession();
		String hql = " from AccInfo where USER_NUM=:userNo and ACC_TYPE=:accType";
		Query query = session.createQuery(hql);
		query.setString("userNo",userNo);
		query.setString("accType",accType);
		List<AccInfo> accInfo = (List<AccInfo>)query.list();
		for(int i = 0; i < accInfo.size(); i++){
			AccInfo ai = accInfo.get(i);
			if(ai.getAmount().indexOf(".") == -1){
				ai.setAmount(ai.getAmount()+".0000");
			}
		}
		return accInfo;
	}
	
	public int getMessage(String userNum){
//		Session session = HibernateUtil.getSession();
		
//		String hql1 = "from OtcSpotInfo where USER_NUM=:userNo and IS_INIT='0' and STATUE='R'";
//		Query query1 = session.createQuery(hql1);
//		query1.setString("userNo",userNo);
//		message = message + query1.list().size();
		
//		String hql2 = "from OtcForwardInfo where USER_NUM=:userNo and IS_INIT='0' and STATUE='R'";
//		Query query2 = session.createQuery(hql2);
//		query2.setString("userNo",userNum);
//		message = message + query2.list().size();
		
//		String hql3 = "from OtcSwapInfo where USER_NUM=:userNo and IS_INIT='0' and STATUE='R'";
//		Query query3 = session.createQuery(hql3);
//		query3.setString("userNo",userNum);
//		message = message + query3.list().size();
		
		return SpotUtil.getInstance().getSpotListByUserNum(userNum).size() + ForwardUtil.getInstance().getForwardListByUserNum(userNum).size() + SwapUtil.getInstance().getSwapListByUserNum(userNum).size();
	}
}
