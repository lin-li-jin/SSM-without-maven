package com.talent.forex.modules.trade.cash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.talent.auth.bean.model.UserModel;
import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.BTranFlowMapping;
import com.talent.forex.bean.domain.MarginEnlarge;
import com.talent.forex.bean.domain.MarginOptionInfo;
import com.talent.forex.bean.model.MarginOptionInfoModel;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.BTranFlowMappingDao;
import com.talent.forex.dao.MarginEnlargeDao;
import com.talent.forex.dao.MarginOptionInfoDao; 
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.GetFixWordUtil;
import com.talent.forex.util.RateUtil;
import com.talent.forex.util.SequenceUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS014
 * Create By    : atggdsaiDong
 * Description  : ��֤����Ȩ����
 * Modify Date  : 2014-07-31
 */
public class OptionCashBo extends BaseBo{
	
	private MarginOptionInfoDao marginOptionInfoDao;
	private MarginEnlargeDao marginEnlargeDao;
	private AccInfoDao accInfoDao;
	private BTranFlowMappingDao bTranFlowMappingDao;


	/**
	 * ��ѯanaccy�Ѿ���Ч����Ȩ����
	 */
	public double queryAllDone(String anaccy,String userNum){
		MarginOptionInfo marginOptionInfo=new MarginOptionInfo();
		marginOptionInfo.setStatue("D");
		marginOptionInfo.setUserNum(userNum);
		marginOptionInfo.setAnaCcy(anaccy);
		List<MarginOptionInfo> marginOptionInfos=
				(List<MarginOptionInfo>) marginOptionInfoDao.getBeansByBean(marginOptionInfo,MatchMode.ANYWHERE);
		double d=0;
		if (null!=marginOptionInfos && !marginOptionInfos.isEmpty()){
			for (MarginOptionInfo info:marginOptionInfos){
				d+=Double.valueOf(info.getDealAmt())*Double.valueOf(info.getPrice());
			}
		}
		return d;
	}

	/**
	 * �����û��ĵ�¼�˺ź��˻����Ͳ�ѯ��Ӧ�˻����͵���Ϣ
	 * �����ݿ�Ĳ�ѯ,����try catch,�����ûع�
	 * @param userNo
	 * @param accType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AccInfo> getAccInfoListByUserNoQuery(String userNo,String accType){
		AccInfo accInfo = new  AccInfo();
		accInfo.setUserNum(userNo);
		accInfo.setAccType(accType);
		return (List<AccInfo>) marginOptionInfoDao.getBeansByBean(accInfo, MatchMode.ANYWHERE);
	}
	
	/**
	 * ����һ����֤����Ȩ���׼�¼
	 * @param model
	 */
	public void newOptionCashAdd(MarginOptionInfoModel model){
		try{
			UserModel user = (UserModel)UserModelUtil.getUser();
			MarginOptionInfo bean = new MarginOptionInfo();
			
			//��Ϊ�������������  ����weCcy���ֶ�Ӧ���˻��ڼ���  anaCcy���ֶ�Ӧ���˻����������
			//�������ж�����Ƿ��㹻��������,ֻ��Ҫ�ж�weCcy���ֶ�Ӧ���˻����û�����Ľ���С������,����Ҫ������������
			//����CNY/USD,������۽���,���ǽ���Ա����CNY,����USD���û���CNY����,USD����,���԰��û�����Ľ���뱣֤���˻����USD���ֱȽ�
			//Ҫͨ���û��˻�,�˻�����,�����˻����ֲ���ȷ��Ψһ��һ����¼
			AccInfo accInfo = new AccInfo();
			accInfo.setUserNum(user.getUserId());
			accInfo.setAccType("B");
			accInfo.setCcy(model.getWeCcy());
			accInfo = accInfoDao.getBeanByBean(accInfo, MatchMode.ANYWHERE);
			
			// start ���ж��û��˻��Ƿ��ѱ����� 
			if(accInfo.getActiveDate().equals("--------")){
				logger.error("�û���" + user.getUserId() + " ������Ȩ����ʧ�ܣ���ı�֤���˻��ѱ����ᣡ");
				BoException be = new BoException("forwardInfoAdd");
					be.setExceptionType("������Ȩ����ʧ�ܣ���ı�֤���˻��ѱ����ᣡ");
				throw be;
			}
			//end
			//�жϵ�ǰ�Ƿ��㹻�۵���Ȩ��
			double option=Double.valueOf(model.getPremium());  //��Ȩ����
			double profit=Double.valueOf(model.getAccountAmount());		//�ֿ۵ķ���
			//accInfo���ﱣ����ʵ�ʽ��   ���ǷŴ��������̽��
			if(model.getDirection().equals("1")) {
				//1��������   ���ж����ʱ  �ж�����Ƿ��㹻�ֿ���Ȩ����
				if ( option+profit> Double.parseDouble(accInfo.getAmount())) {
					logger.error("�û���" + user.getUserId() + " ������Ȩ����ʧ�ܣ��˻�" + accInfo.getCcy() + "����");
					BoException be = new BoException("forwardInfoAdd");
					be.setExceptionType("��֤���˻�" + accInfo.getCcy() + "����,����ʧ�ܣ���ǰ���Ϊ:" + accInfo.getAmount()+"!���д˴ν�����Ҫ"+accInfo.getCcy()+":"+(option+profit)+"Ԫ,����:"
					+"��Ȩ��:"+option+",������:"+profit);
					throw be;
				}
			}else{
				//model.getDirection().equals("0")
				//1����������
				BoException be=new BoException("forwardInfoAdd");
				be.setExceptionType("�ݲ�֧�ֱ�֤�����������");
				throw be;
				/*if (Double.parseDouble(model.getAccountAmount()) > Double.parseDouble(accInfo.getAmount())){
					logger.error("�û���" + user.getUserId() + " ������Ȩ����ʧ�ܣ��˻�"+ accInfo.getCcy() +"����");
					BoException be = new BoException("forwardInfoAdd");
					be.setExceptionType("��֤���˻�"+ accInfo.getCcy() +"����,����ʧ�ܣ���ǰ���Ϊ:"+accInfo.getAmount()+"!���д˴ν�����Ҫ"+accInfo.getCcy()+(Double.parseDouble(model.getAccountAmount()))+"Ԫ");
					throw be;
				}*/
			}



			int days = 0;
			if("5d".equals(model.getValueDate())){
				days = 5;
			}else if("14d".equals(model.getValueDate())){
				days = 14;
			}else if("30d".equals(model.getValueDate())){
				days = 30;
			}else{
				days = 60;
			}
			String date = GetDateTimeUtil.getCurrentDateTimeToMinute();
			String afterDate = GetDateTimeUtil.getDates(GetDateTimeUtil.dateTransFmt(date), days);//Զ��ִ�е�����
			
			bean.setTranType("B");//��֤���׶�������ҽ���,��B��ʾ
			bean.setTranNo(SequenceUtil.getNextTranSeq("MO"));//��BO�������ˮ��Ϊ�˳��쳣��ʱ��,��ˮ����Ҳ�ܻع�,MO������Ȩ����
			bean.setUserNum(user.getUserId());
			bean.setAccNo(model.getAccount());
			//valueDate�ֶκ�maturity�ֶ��ظ��� ��setMaturity������
			bean.setMaturity(GetDateTimeUtil.dateTransFmt2(afterDate)); 
			bean.setWeCcy(model.getWeCcy());
			bean.setAnaCcy(model.getAnaCcy());
			bean.setOptionType(model.getOptionType());
			bean.setAccAmount(FormatParamUtil.getAmountAndPriceFmt(model.getAccountAmount()));
			bean.setDealAmt(FormatParamUtil.getAmountAndPriceFmt(model.getDealAmount()));
			bean.setPrice(model.getPrice());
			bean.setPremium(model.getPremium());
			bean.setStatue("A");
			bean.setCreateDatetime(date);
			bean.setDirection(model.getDirection());
			marginOptionInfoDao.makePersistent(bean, false);
			
			//������һ����Ȩ���׵�ʱ��Ҫ�Ȱ���Ȩ�Ѹ�������    ��������USD/CAD  ��Ȩ�Ѷ��ǿ�USD��
			if(model.getDirection().equals("1")){
				AccInfo a = new AccInfo();
				a.setUserNum(user.getUserId());
				a.setAccType("B");
				a.setCcy(model.getAnaCcy());
				AccInfo a1 = accInfoDao.getBeanByBean(a, MatchMode.ANYWHERE);
				Double originAmout = Double.parseDouble(a1.getAmount());
				a1.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(originAmout-Double.parseDouble(model.getPremium()))); 
				accInfoDao.updateBean(a1);
			}else{
				AccInfo a = new AccInfo();
				a.setUserNum(user.getUserId());
				a.setAccType("B");
				a.setCcy(model.getWeCcy());
				AccInfo a1 = accInfoDao.getBeanByBean(a, MatchMode.ANYWHERE);
				Double originAmout = Double.parseDouble(a1.getAmount());
				a1.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(originAmout-Double.parseDouble(model.getPremium())));
				accInfoDao.updateBean(a1);
			}
			
		}catch(Exception e){
			if (e instanceof BoException) {
				throw (BoException) e;
			}
			logger.error("������֤����Ȩ����ʧ�ܣ�");
			logger.error(e.getMessage(), e);
			BoException be = new BoException("newForwardCashAdd");
			be.setExceptionType("������֤����Ȩ����ʧ�ܣ�");
			throw be;
		}

	}
	
	
	/**
	 * ����group1�ı�Ŵ�margin_enlarge�����ѯ��Ӧ�ķŴ���
	 * �����ݿ�Ĳ�ѯ,����try catch,�����ûع�
	 * @param GroupOne
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String EnlargementFactorQuery(String GroupOne){
		String enlarge = "";
		MarginEnlarge me = new MarginEnlarge();
		me.setGroupId(GroupOne);
		Collection<?> collection = marginEnlargeDao.getBeansByBean(me, MatchMode.ANYWHERE);
		if(!collection.isEmpty()){
			Iterator iterator = collection.iterator();
			while(iterator.hasNext()){
				enlarge = ((MarginEnlarge)iterator.next()).getEnlarge();
				break;
			}
		}
		return enlarge;
	}
	
	
	/**
	 * ��������ǲ�ѯ���еı�֤����Ȩ���׼�¼,�������е�״̬
	 * ������getAll()����,�����Ӱ�����table�ļ�¼���ó�����,ֻ��Ҫ�õ�ǰ��¼�˻��ļ�¼
	 * �����ݿ�Ĳ�ѯ,����try catch,�����ûع�
	 * @return
	 */
	public Collection<?> allOptionCashQuery(String hql,String tradeStatus){
		UserModel user = (UserModel)UserModelUtil.getUser();
		ArrayList<String> paraList = new ArrayList<String>();
		String status = tradeStatus.equals("0")?"":tradeStatus;
		if (status.equals(""))
			status = GetFixWordUtil.getLikeWords(status);
		paraList.add(user.getUserId());
		paraList.add(status);
		return marginOptionInfoDao.getBeansByParams(hql, paraList);
	}
	
	/**
	 * ����tranNo��ѯΨһ��Զ�ڽ��׼�¼
	 * @param tranNo
	 * @return
	 */
	public MarginOptionInfo queryForwardCashByTranNo(String tranNo){
		MarginOptionInfo marginOptionInfo = new MarginOptionInfo();
		marginOptionInfo.setTranNo(tranNo);
		return marginOptionInfoDao.getBeanByBean(marginOptionInfo, MatchMode.ANYWHERE);
	}
	
	
	/**
	 * ִ��һ����Ȩ����
	 * ���ܣ�����Ȩ���ױ�������Զ�ڽ��׵Ľ���״̬�ĳ�DONE,�����˻�����ͳ�Ʊ�,�˻���
	 * @param tradeNo
	 * @return
	 */
	public void excuteOptionModify(String tradeNo){
		try{		
			MarginOptionInfo m  = new MarginOptionInfo();
			m.setTranNo(tradeNo);
			//����Ψһ����ˮ�ŵõ�Ҫ��ִ�е���Ȩ���׼�¼Bean
			MarginOptionInfo bean = marginOptionInfoDao.getBeanByBean(m, MatchMode.ANYWHERE);
			//1.�����˻���acc_info,�ȵõ���֤���������Ҫ���׵ı��ֵ��˻�bean
			//weCcy��������  anaCcy���������
			AccInfo a1 = new AccInfo();
			a1.setUserNum(bean.getUserNum());
			a1.setAccType("B");
			a1.setCcy(bean.getWeCcy());
			AccInfo weAccInfo = accInfoDao.getBeanByBean(a1, MatchMode.ANYWHERE);
			
			AccInfo a2 = new AccInfo();
			a2.setUserNum(bean.getUserNum());
			a2.setAccType("B");
			a2.setCcy(bean.getAnaCcy());
			AccInfo anaAccInfo = accInfoDao.getBeanByBean(a2, MatchMode.ANYWHERE);
			
			//acc_info�����µĽ���õ���ʵ�ʽ��������̽��
			Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());//weCcy���ֶ�Ӧ���˻����
			//Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());//anaCcy���ֶ�Ӧ���˻����
			Double price = Double.parseDouble(bean.getPrice());//��Ȩ���׵ļ۸�
			//Double amt = Double.parseDouble(bean.getAccAmount());//��Ȩ���׵�ʵ�ʽ��׽��
			Double deal_amt = Double.parseDouble(bean.getDealAmt());//��Ȩ���׵����̽��׽��
			
			Double currentPrice = RateUtil.getMarginRateByCcy(bean.getAnaCcy() + bean.getWeCcy(), bean.getDirection());//�õ���ǰ�������ӵı���ת�ؼ��ٱ��֣��ļ۸�
			//Double currentPrice = RateUtil.getRate(bean.getAnaCcy(), bean.getWeCcy());//�õ���ǰ�������ӵı���ת�ؼ��ٱ��֣��ļ۸�
			Double profit = 0.0;
			if(bean.getDirection().equals("1")){
				//��
				//weCcy���ֶ�Ӧ���˻����     = ���+���̽��׵�����
				profit = deal_amt*currentPrice-deal_amt*price;	//������������Ҳ�����Ǹ�
				double option =Double.valueOf(bean.getPremium());	//��Ȩ��
				double cash=Double.valueOf(bean.getAccAmount());
				weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney+profit-option-cash));
			}else{
				//bean.getDirection().equals("0")
				//��
				//weCcy���ֶ�Ӧ���˻����     = ���+���̽��׵�����
				profit=deal_amt*price*currentPrice-deal_amt;
				weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney+profit));
			}
			

			
			//2.���±�֤������ˮ��b_tran_flow_mapping,���½���õ������̽��
			BTranFlowMapping b = new BTranFlowMapping();
			b.setUserNum(bean.getUserNum());
			BTranFlowMapping bTranFlowMapping = bTranFlowMappingDao.getBeanByBean(b, MatchMode.ANYWHERE);
			//��Ȩ���״�����1����Ȩ�����������̽��׽�� 
			bTranFlowMapping.setMarginOptionQty((Integer.parseInt(bTranFlowMapping.getMarginOptionQty())+1)+"");
			bTranFlowMapping.setMarginOptionAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(bTranFlowMapping.getMarginOptionAmt())+deal_amt));  
			//�ܽ��״�����һ���ܽ����������̽��׽��
			bTranFlowMapping.setCount(bTranFlowMapping.getCount()+1);
			bTranFlowMapping.setAmount(bTranFlowMapping.getAmount()+deal_amt);
			
			
			
			//3.����bean�Ľ���״̬Ϊ���
			bean.setStatue("D");
			
			//4.����ص�bean���µ���Ӧ�ı�
			marginOptionInfoDao.updateBean(bean);
			bTranFlowMappingDao.updateBean(bTranFlowMapping);
			accInfoDao.updateBean(weAccInfo);
			accInfoDao.updateBean(anaAccInfo);
			
		}catch(Exception e){
			if (e instanceof BoException) {
				throw (BoException) e;
			}
			logger.error("��֤����Ȩ����ִ��ʧ��");
			logger.error(e.getMessage(), e);
			BoException be = new BoException("excuteOptionAdd");
			be.setExceptionType("��֤����Ȩ����ִ��ʧ��");
			throw be;
		}

	}
	
	public MarginOptionInfoDao getMarginOptionInfoDao() {
		return marginOptionInfoDao;
	}
	public void setMarginOptionInfoDao(MarginOptionInfoDao marginOptionInfoDao) {
		this.marginOptionInfoDao = marginOptionInfoDao;
	}
	public MarginEnlargeDao getMarginEnlargeDao() {
		return marginEnlargeDao;
	}
	public void setMarginEnlargeDao(MarginEnlargeDao marginEnlargeDao) {
		this.marginEnlargeDao = marginEnlargeDao;
	}
	
	public AccInfoDao getAccInfoDao() {
		return accInfoDao;
	}

	public void setAccInfoDao(AccInfoDao accInfoDao) {
		this.accInfoDao = accInfoDao;
	}

	public BTranFlowMappingDao getbTranFlowMappingDao() {
		return bTranFlowMappingDao;
	}

	public void setbTranFlowMappingDao(BTranFlowMappingDao bTranFlowMappingDao) {
		this.bTranFlowMappingDao = bTranFlowMappingDao;
	}
}