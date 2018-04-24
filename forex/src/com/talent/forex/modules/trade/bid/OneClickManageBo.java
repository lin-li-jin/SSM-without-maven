package com.talent.forex.modules.trade.bid;

import org.hibernate.criterion.MatchMode;

import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.OneClickInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.OneClickDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.forex.util.CalculateUtil;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.SequenceUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS004
 * Create By    : lzc
 * Description  : һ�ڼ۽���
 * Modify Date  : 2014-07-24
 */
public class OneClickManageBo extends BaseBo {

	private OneClickDao oneClickDao;
	private AccInfoDao accInfoDao;
	private CTranFlowMappingDao cTranFlowMappingDao;
	private WTranFlowMappingDao wTranFlowMappingDao;

	/**
	 * ��ӽ��׼�¼
	 */
	public OneClickInfo oneClickInfoAdd(String direct, String ccy1, String ccy2, String amount, String price){
		OneClickInfo o = null;
		Double rate = 1.0;//����������������priceֵ���û��ύ���׽��ĳ˻������жϽ��׽���Ƿ�����˻���Ϣ����Ӧ����ʣ����
		try{
			AccInfo accInfo = new AccInfo();
			o = new OneClickInfo();
			
			//���ݽ������������ȡ�˻���Ϣ��ʵ�壬�������������Ա������ң��������ң�����ַ����ң�������ң���ʽ���뽻��ʵ�壬0��������1������
			if (direct.equals("0")){
				o.setWeCcy(ccy1);
				o.setAnaCcy(ccy2);
				accInfo.setCcy(ccy1);
			}
			else{
				o.setWeCcy(ccy2);
				o.setAnaCcy(ccy1);
				accInfo.setCcy(ccy2);
				rate = Double.parseDouble(price);
			}
			if (ccy2.equals("CNY")){
				o.setTranType("C");
				accInfo.setAccType("C");
			}else{
				o.setTranType("W");
				accInfo.setAccType("W");
			}
			accInfo.setUserNum(UserModelUtil.getUser().getUserId());
			accInfo = accInfoDao.getBeanByBean(accInfo, MatchMode.ANYWHERE);
			
			//�ж��û��ύ�����н���Ƿ�����Ҫ�󣬼����ܴ����˻���Ϣ������Ӧ����ʣ����
			if (Double.parseDouble(amount) * rate > Double.parseDouble(accInfo.getAmount())){
				logger.error("�û���" + UserModelUtil.getUser().getUserId() + " ���һ�ڼ۽���ʧ�ܣ��˻�"+ accInfo.getCcy() +"����");
				BoException be = new BoException("oneClickInfoAdd");
				if (accInfo.getAccType().equals("C"))
					be.setExceptionType("������˻�"+ accInfo.getCcy() +"����,һ�ڼ۽����ύʧ�ܣ�");
				else
					be.setExceptionType("����˻�"+ accInfo.getCcy() +"����,һ�ڼ۽����ύʧ�ܣ�");
				throw be;
			}
			
			//�������Ϣ���������ʵ�壬�ȴ������
			String date = GetDateTimeUtil.getCurrentDateTimeToMinute();
			o.setTranNo(SequenceUtil.getNextTranSeq("OC"));
			o.setUserNum(UserModelUtil.getUser().getUserId());
			o.setDirection(direct);
			o.setAmount(FormatParamUtil.getAmountAndPriceFmt(amount));
			o.setPromptDate(date.substring(0, 8));
			o.setDate(date.substring(0, 8));
			o.setCreateDatetime(date);
			o.setTime(date.substring(8, date.length()));
			o.setPrice(price);
			o.setLAmount(CalculateUtil.getAmountAfterTrade(amount, price));//����һ�����ҽ��
			excuteOneClickAdd(o);
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("�û���" + UserModelUtil.getUser().getUserId() + " ���һ�ڼ۽���ʧ�ܣ�");
			logger.error(e.getMessage(), e);
			BoException be = new BoException("oneClickInfoAdd");
			be.setExceptionType("һ�ڼ۽����ύʧ�ܣ�");
			throw be;
		}
		return o;
	}
	
	/**
	 * ִ��һ��һ�ڼ۽���
	 * ���ܣ���һ�ڼ۽��ױ������һ�ڼ۽��׵Ľ���״̬�ĳ�DONE,�����˻�����ͳ�Ʊ�,�˻���
	 */
	public void excuteOneClickAdd(OneClickInfo bean){
		try{		
			//1.�����˻���acc_info,�ȵõ���֤���������Ҫ���׵ı��ֵ��˻�bean
			//weCcy��������  anaCcy���������
			AccInfo a1 = new AccInfo();
			a1.setUserNum(bean.getUserNum());
			a1.setAccType(bean.getTranType());
			a1.setCcy(bean.getWeCcy());
			AccInfo weAccInfo = accInfoDao.getBeanByBean(a1, MatchMode.ANYWHERE);
			
			AccInfo a2 = new AccInfo();
			a2.setUserNum(bean.getUserNum());
			a2.setAccType(bean.getTranType());
			a2.setCcy(bean.getAnaCcy());
			AccInfo anaAccInfo = accInfoDao.getBeanByBean(a2, MatchMode.ANYWHERE);
			
			Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());//weCcy���ֶ�Ӧ���˻����
			Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());//anaCcy���ֶ�Ӧ���˻����
			Double amount = Double.parseDouble(bean.getAmount());//һ�ڼ۽��׵�ʵ�ʽ��׽��
			Double amt = Double.parseDouble(bean.getAmount()) * Double.parseDouble(bean.getPrice());//�һ����ʺ���һ���ֽ��
			
			//weCcy���ֶ�Ӧ���˻����     = ���-ʵ�ʽ��׽��
			//anaCcy���ֶ�Ӧ���˻����   = ���+ʵ�ʽ��׽��*�۸�
			if (bean.getDirection().equals("0")){
				weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amount));
				anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amt));
			}
			else{
				weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amt));
				anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amount));
			}
			
			//2.��������ҽ�����ˮ��c_tran_flow_mapping
			if (bean.getTranType().equals("C")){
				CTranFlowMapping c = new CTranFlowMapping();
				c.setUserNum(bean.getUserNum());
				CTranFlowMapping ctf = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
				//һ�ڼ۽��״�����1��һ�ڼ۽������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
				ctf.setOneClickQty(String.valueOf(Integer.parseInt(ctf.getOneClickQty()) + 1));
				ctf.setOneClickAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(ctf.getOneClickAmt()) + amount));
				ctf.setCount(ctf.getCount() + 1);
				ctf.setAmount(ctf.getAmount() + amount);
				cTranFlowMappingDao.updateBean(ctf);
			}
			else{//������ҽ�����ˮ��w_tran_flow_mapping
				WTranFlowMapping w = new WTranFlowMapping();
				w.setUserNum(bean.getUserNum());
				WTranFlowMapping wtf = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
				//һ�ڼ۽��״�����1��һ�ڼ۽������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
				wtf.setOneClickQty(String.valueOf(Integer.parseInt(wtf.getOneClickQty()) + 1));
				wtf.setOneClickAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wtf.getOneClickAmt()) + amount));
				wtf.setCount(wtf.getCount() + 1);
				wtf.setAmount(wtf.getAmount() + amount);
				wTranFlowMappingDao.updateBean(wtf);
			}
			
			//3.����bean�Ľ���״̬Ϊ���
			bean.setStatus("D");
			bean.setLAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(amt));
			
			//4.����ص�bean���µ���Ӧ�ı�
			oneClickDao.makePersistent(bean, false);
			accInfoDao.updateBean(weAccInfo);
			accInfoDao.updateBean(anaAccInfo);
			
		}catch(Exception e){
			if (e instanceof BoException) {
				throw (BoException) e;
			}
			logger.error("һ�ڼ۽���ִ��ʧ��");
			logger.error(e.getMessage(), e);
			BoException be = new BoException("excuteOneClickAdd");
			be.setExceptionType("һ�ڼ۽���ִ��ʧ��");
			throw be;
		}

	}
	
	public OneClickDao getOneClickDao() {
		return oneClickDao;
	}

	public void setOneClickDao(OneClickDao oneClickDao) {
		this.oneClickDao = oneClickDao;
	}

	public AccInfoDao getAccInfoDao() {
		return accInfoDao;
	}

	public void setAccInfoDao(AccInfoDao accInfoDao) {
		this.accInfoDao = accInfoDao;
	}

	public CTranFlowMappingDao getcTranFlowMappingDao() {
		return cTranFlowMappingDao;
	}

	public void setcTranFlowMappingDao(CTranFlowMappingDao cTranFlowMappingDao) {
		this.cTranFlowMappingDao = cTranFlowMappingDao;
	}

	public WTranFlowMappingDao getwTranFlowMappingDao() {
		return wTranFlowMappingDao;
	}

	public void setwTranFlowMappingDao(WTranFlowMappingDao wTranFlowMappingDao) {
		this.wTranFlowMappingDao = wTranFlowMappingDao;
	}
	
}
