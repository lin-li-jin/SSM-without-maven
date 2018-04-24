package com.talent.forex.modules.trade.bid;

import java.util.ArrayList;

import org.hibernate.criterion.MatchMode;

import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.OcoInfo;
import com.talent.forex.bean.domain.TakeProfitInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.bean.model.OcoAndMarketModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.OcoDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.SequenceUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS004
 * Create By    : lzc
 * Description  : �Զ����������
 * Modify Date  : 2014-07-24
 */
public class OcoManageBo extends BaseBo {

	private OcoDao ocoDao;
	private AccInfoDao accInfoDao;
	private CTranFlowMappingDao cTranFlowMappingDao;
	private WTranFlowMappingDao wTranFlowMappingDao;

	/**
	 * ��ӽ���
	 */
	public void ocoInfoAdd(OcoAndMarketModel model){
		try{
			OcoInfo o = new OcoInfo();
			AccInfo accInfo = new AccInfo();
			//����������������tprice��spriceֵ���û��ύ���׽��ĳ˻������жϽ��׽���Ƿ�����˻���Ϣ����Ӧ����ʣ����
			Double tPrice = 1.0;
			Double sPrice = 1.0;
			
			//���ݽ������������ȡ�˻���Ϣ��ʵ�壬�������������Ա������ң��������ң�����ַ����ң�������ң���ʽ���뽻��ʵ�壬0��������1������
			if (model.getTradeDirection().equals("0")){
				o.setWeCcy(model.getCcy1());
				o.setAnaCcy(model.getCcy2());
				accInfo.setCcy(model.getCcy1());
			}
			else{
				o.setWeCcy(model.getCcy2());
				o.setAnaCcy(model.getCcy1());
				accInfo.setCcy(model.getCcy2());
				tPrice = Double.parseDouble(model.gettPrice());
				sPrice = Double.parseDouble(model.getsPrice());
			}
			if (model.getCcy2().equals("CNY")){
				o.setTranType("C");
				accInfo.setAccType("C");
			}else{
				o.setTranType("W");
				accInfo.setAccType("W");
			}
			accInfo.setUserNum(UserModelUtil.getUser().getUserId());
			accInfo = accInfoDao.getBeanByBean(accInfo, MatchMode.ANYWHERE);
			
			//�ж��û��ύ�����н���Ƿ�����Ҫ�󣬼����ܴ����˻���Ϣ������Ӧ����ʣ����
			if (Double.parseDouble(model.gettAmount())*tPrice > Double.parseDouble(accInfo.getAmount()) || Double.parseDouble(model.getsAmount())*sPrice > Double.parseDouble(accInfo.getAmount())){
				logger.error("�û���" + UserModelUtil.getUser().getUserId() + " ���OCO����ʧ�ܣ��˻�"+ accInfo.getCcy() +"����");
				BoException be = new BoException("ocoInfoAdd");
				if (accInfo.getAccType().equals("C"))
					be.setExceptionType("������˻�"+ accInfo.getCcy() +"����,OCO�����ύʧ�ܣ�");
				else
					be.setExceptionType("����˻�"+ accInfo.getCcy() +"����,OCO�����ύʧ�ܣ�");
				throw be;
			}
			
			//�������Ϣ���������ʵ�壬�ȴ������
			o.setTranNo(SequenceUtil.getNextTranSeq("OO"));
			o.setUserNum(UserModelUtil.getUser().getUserId());
			o.setTAmount(FormatParamUtil.getAmountAndPriceFmt(model.gettAmount()));
			o.setSAmount(FormatParamUtil.getAmountAndPriceFmt(model.getsAmount()));
			o.setDirection(model.getTradeDirection());
			o.setTPrice(FormatParamUtil.getAmountAndPriceFmt(model.gettPrice()));
			o.setSPrice(FormatParamUtil.getAmountAndPriceFmt(model.getsPrice()));
			o.setMonitorPrice(model.getMonitorPrice());
			if (model.getActiveTime().equals(""))
				o.setGoodFrom(GetDateTimeUtil.getCurrentDate());
			else
				o.setGoodFrom(GetDateTimeUtil.dateTransFmt2(model.getActiveTime()));
			if (model.getCancelTime().equals(""))
				o.setGoodTill(SysParamNameConst.UNLIMITED_TIME);
			else
				o.setGoodTill(GetDateTimeUtil.dateTransFmt2(model.getCancelTime()));
			o.setCreateDatetime(GetDateTimeUtil.getCurrentDateTimeToMinute());
			o.setStatue("A");
			
			ocoDao.ocoInfoAdd(o);
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("OCO����ʧ�ܣ��û���" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("ocoInfoAdd");
			be.setExceptionType("OCO����ʧ�ܣ��û���"+UserModelUtil.getUser().getUserId());
			throw be;
		}
	}
	
	/**
	 * ȡ������
	 */
	public void ocoCancelUpdate(OcoInfo o){
		try{
			ocoDao.updateBean(o);
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("OCO����ȡ��ʧ�ܣ��û���" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("ocoCancelUpdate");
			be.setExceptionType("OCO����ȡ��ʧ�ܣ�");
			throw be;
		}
	}
	
	/**
	 * ���ݽ��ױ�Ż��ʵ��
	 */
	public OcoInfo getBeanByTradeNo(String hql,String tradeNo){
		ArrayList list = new ArrayList();
		list.add(tradeNo);
		return ocoDao.getBeanByParams(hql, list);
	}
	
	/**
	 * ִ��һ��OCO����
	 * ���ܣ���OCO���ױ������OCO���׵Ľ���״̬�ĳ�DONE,�����˻�����ͳ�Ʊ�,�˻���
	 */
	public void excuteOcoAdd(OcoInfo bean){
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
			Double amount = Double.parseDouble(bean.getAmount());//OCO���׵�ʵ�ʽ��׽��
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
				//OCO���״�����1��OCO�������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
				ctf.setOcoQty(String.valueOf(Integer.parseInt(ctf.getOcoQty()) + 1));
				ctf.setOcoAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(ctf.getOcoAmt()) + amount));
				ctf.setCount(ctf.getCount() + 1);
				ctf.setAmount(ctf.getAmount() + amount);
				cTranFlowMappingDao.updateBean(ctf);
			}
			else{//������ҽ�����ˮ��w_tran_flow_mapping
				WTranFlowMapping w = new WTranFlowMapping();
				w.setUserNum(bean.getUserNum());
				WTranFlowMapping wtf = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
				//OCO���״�����1��OCO�������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
				wtf.setOcoQty(String.valueOf(Integer.parseInt(wtf.getOcoQty()) + 1));
				wtf.setOcoAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wtf.getOcoAmt()) + amount));
				wtf.setCount(wtf.getCount() + 1);
				wtf.setAmount(wtf.getAmount() + amount);
				wTranFlowMappingDao.updateBean(wtf);
			}
			
			//3.����bean�Ľ���״̬Ϊ���
			bean.setStatue("D");
			bean.setLAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(amt));
			
			//4.����ص�bean���µ���Ӧ�ı�
			ocoDao.updateBean(bean);
			accInfoDao.updateBean(weAccInfo);
			accInfoDao.updateBean(anaAccInfo);
			
		}catch(Exception e){
			if (e instanceof BoException) {
				throw (BoException) e;
			}
			logger.error("OCO����ִ��ʧ��");
			logger.error(e.getMessage(), e);
			BoException be = new BoException("excuteOcoAdd");
			be.setExceptionType("OCO����ִ��ʧ��");
			throw be;
		}
	}
	
	public OcoDao getOcoDao() {
		return ocoDao;
	}

	public void setOcoDao(OcoDao ocoDao) {
		this.ocoDao = ocoDao;
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
