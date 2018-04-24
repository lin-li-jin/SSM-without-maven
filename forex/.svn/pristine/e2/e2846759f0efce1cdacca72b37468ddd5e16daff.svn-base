package com.talent.forex.modules.trade.bid;

import java.util.ArrayList;

import org.hibernate.criterion.MatchMode;

import com.talent.auth.bean.model.UserModel;
import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.StopLossInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.bean.model.StopAndProfitModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.StopLossDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.RateUtil;
import com.talent.forex.util.SequenceUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS002
 * Create By    : lzc
 * Description  : 止亏交易
 * Modify Date  : 2014-07-24
 */
public class StopLossManageBo extends BaseBo {

	private StopLossDao stopLossDao;
	private AccInfoDao accInfoDao;
	private CTranFlowMappingDao cTranFlowMappingDao;
	private WTranFlowMappingDao wTranFlowMappingDao;
	
	/**
	 * 添加交易记录
	 */
	public void stopLossInfoAdd(StopAndProfitModel model){
		StopLossInfo s = null;
		Double price = 1.0;//根据买卖方向，设置price值与用户提交交易金额的乘积，以判断交易金额是否大于账户信息表相应货币剩余金额
		try{
			s = new StopLossInfo();
			AccInfo accInfo = new AccInfo();
			
			//根据交易买卖方向获取账户信息表实体，并将两个货币以本方货币（卖出货币）与对手方货币（买入货币）形式存入交易实体，0代表卖，1代表买
			if (model.getTradeDirection().equals("0")){
				s.setWeCcy(model.getCcy1());
				s.setAnaCcy(model.getCcy2());
				accInfo.setCcy(model.getCcy1());
			}
			else{
				s.setWeCcy(model.getCcy2());
				s.setAnaCcy(model.getCcy1());
				accInfo.setCcy(model.getCcy2());
				price = Double.parseDouble(model.getPrice());
			}
			if (model.getCcy2().equals("CNY")){
				s.setTranType("C");
				accInfo.setAccType("C");
			}else{
				s.setTranType("W");
				accInfo.setAccType("W");
			}
			accInfo.setUserNum(UserModelUtil.getUser().getUserId());
			accInfo = accInfoDao.getBeanByBean(accInfo, MatchMode.ANYWHERE);
			
			//判断用户提交交易中金额是否满足要求，即不能大于账户信息表中相应货币剩余金额
			if (Double.parseDouble(model.getAmount()) * price > Double.parseDouble(accInfo.getAmount())){
				logger.error("用户：" + UserModelUtil.getUser().getUserId() + " 添加Stop Loss交易失败！账户"+ accInfo.getCcy() +"金额不足");
				BoException be = new BoException("stopLossInfoAdd");
				if (accInfo.getAccType().equals("C"))
					be.setExceptionType("人民币账户"+ accInfo.getCcy() +"金额不足,Stop Loss交易提交失败！");
				else
					be.setExceptionType("外币账户"+ accInfo.getCcy() +"金额不足,Stop Loss交易提交失败！");
				throw be;
			}
			
			//将相关信息保存进交易实体，等待被添加
			s.setTranNo(SequenceUtil.getNextTranSeq("SL"));
			s.setUserNum(UserModelUtil.getUser().getUserId());
			s.setAmount(FormatParamUtil.getAmountAndPriceFmt(model.getAmount()));
			s.setDirection(model.getTradeDirection());
			s.setPrice(FormatParamUtil.getAmountAndPriceFmt(model.getPrice()));
			s.setMonitorPrice(model.getMonitorPrice());
			if (model.getActiveTime().equals(""))
				s.setGoodFrom(GetDateTimeUtil.getCurrentDate());
			else
				s.setGoodFrom(GetDateTimeUtil.dateTransFmt2(model.getActiveTime()));
			if (model.getCancelTime().equals(""))
				s.setGoodTill(SysParamNameConst.UNLIMITED_TIME);
			else
				s.setGoodTill(GetDateTimeUtil.dateTransFmt2(model.getCancelTime()));
			s.setCreateDatetime(GetDateTimeUtil.getCurrentDateTimeToMinute());
			s.setStatue("A");
			
			stopLossDao.stopLossInfoSave(s);
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("Stop Loss交易失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("stopLossInfoSave");
			be.setExceptionType("Stop Loss交易失败！用户："+UserModelUtil.getUser().getUserId());
			throw be;
		}
	}

	/**
	 * 取消交易
	 */
	public void stopLossCancelUpdate(StopLossInfo s){
		try{
			stopLossDao.updateBean(s);
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("Stop Loss交易取消失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("stopLossCancelUpdate");
			be.setExceptionType("Stop Loss交易取消失败！");
			throw be;
		}
	}
	

	
	/**
	 * 根据交易编号获得实体
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public StopLossInfo getBeanByTradeNo(String hql,String tradeNo){
		ArrayList list = new ArrayList();
		list.add(tradeNo);
		return stopLossDao.getBeanByParams(hql, list);
	}
	
	public StopLossDao getStopLossDao() {
		return stopLossDao;
	}

	public void setStopLossDao(StopLossDao stopLossDao) {
		this.stopLossDao = stopLossDao;
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
