package com.talent.forex.modules.trade.bid;

import java.util.ArrayList;

import org.hibernate.criterion.MatchMode;

import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.MarketBreakoutInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.bean.model.OcoAndMarketModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.MarketBreakoutDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.SequenceUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS005
 * Create By    : lzc
 * Description  : MarketBreakout交易
 * Modify Date  : 2014-07-24
 */
public class MarketBreakoutManageBo extends BaseBo {

	private MarketBreakoutDao marketBreakoutDao;
	private AccInfoDao accInfoDao;
	private CTranFlowMappingDao cTranFlowMappingDao;
	private WTranFlowMappingDao wTranFlowMappingDao;
	
	/**
	 * 添加交易
	 */
	public void marketBreakoutInfoAdd(OcoAndMarketModel model){
		try{
			MarketBreakoutInfo m = new MarketBreakoutInfo();
			AccInfo accInfo = new AccInfo();
			//根据买卖方向，设置tprice或sprice值与用户提交交易金额的乘积，以判断交易金额是否大于账户信息表相应货币剩余金额
			Double tPrice = 1.0;
			Double sPrice = 1.0;
			
			//根据交易买卖方向获取账户信息表实体，并将两个货币以本方货币（卖出货币）与对手方货币（买入货币）形式存入交易实体，0代表卖，1代表买
			if (model.getTradeDirection().equals("0")){
				m.setWeCcy(model.getCcy1());
				m.setAnaCcy(model.getCcy2());
				accInfo.setCcy(model.getCcy1());
			}
			else{
				m.setWeCcy(model.getCcy2());
				m.setAnaCcy(model.getCcy1());
				accInfo.setCcy(model.getCcy2());
				tPrice = Double.parseDouble(model.gettPrice());
				sPrice = Double.parseDouble(model.getsPrice());
			}
			if (model.getCcy2().equals("CNY")){
				m.setTranType("C");
				accInfo.setAccType("C");
			}else{
				m.setTranType("W");
				accInfo.setAccType("W");
			}
			accInfo.setUserNum(UserModelUtil.getUser().getUserId());
			accInfo = accInfoDao.getBeanByBean(accInfo, MatchMode.ANYWHERE);
			
			//判断用户提交交易中金额是否满足要求，即不能大于账户信息表中相应货币剩余金额
			if (Double.parseDouble(model.gettAmount())*tPrice > Double.parseDouble(accInfo.getAmount()) || Double.parseDouble(model.getsAmount())*sPrice > Double.parseDouble(accInfo.getAmount())){
				logger.error("用户：" + UserModelUtil.getUser().getUserId() + " 添加MarketBreakout交易失败！账户"+ accInfo.getCcy() +"金额不足");
				BoException be = new BoException("marketBreakoutInfoAdd");
				if (accInfo.getAccType().equals("C"))
					be.setExceptionType("人民币账户"+ accInfo.getCcy() +"金额不足,MarketBreakout交易提交失败！");
				else
					be.setExceptionType("外币账户"+ accInfo.getCcy() +"金额不足,MarketBreakout交易提交失败！");
				throw be;
			}
			m.setTranNo(SequenceUtil.getNextTranSeq("MB"));
			m.setUserNum(UserModelUtil.getUser().getUserId());
			if (model.getTradeDirection().equals("0")){
				m.setWeCcy(model.getCcy1());
				m.setAnaCcy(model.getCcy2());
			}
			else{
				m.setWeCcy(model.getCcy2());
				m.setAnaCcy(model.getCcy1());
			}
			
			//将相关信息保存进交易实体，等待被添加
			m.setS1Amount(FormatParamUtil.getAmountAndPriceFmt(model.gettAmount()));
			m.setS2Amount(FormatParamUtil.getAmountAndPriceFmt(model.getsAmount()));
			m.setDirection(model.getTradeDirection());
			m.setS1Price(FormatParamUtil.getAmountAndPriceFmt(model.gettPrice()));
			m.setS2Price(FormatParamUtil.getAmountAndPriceFmt(model.getsPrice()));
			m.setMonitorPrice(model.getMonitorPrice());
			if (model.getActiveTime().equals(""))
				m.setGoodFrom(GetDateTimeUtil.getCurrentDate());
			else
				m.setGoodFrom(GetDateTimeUtil.dateTransFmt2(model.getActiveTime()));
			if (model.getCancelTime().equals(""))
				m.setGoodTill(SysParamNameConst.UNLIMITED_TIME);
			else
				m.setGoodTill(GetDateTimeUtil.dateTransFmt2(model.getCancelTime()));
			m.setCreateDatetime(GetDateTimeUtil.getCurrentDateTimeToMinute());
			m.setStatue("A");
			
			marketBreakoutDao.marketBreakoutInfoAdd(m);
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("MarketBreakout交易失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("marketBreakoutInfoAdd");
			be.setExceptionType("MarketBreakout交易失败！用户："+UserModelUtil.getUser().getUserId());
			throw be;
		}
	}

	/**
	 * 取消交易
	 */
	public void marketBreakoutCancelUpdate(MarketBreakoutInfo m){
		try{
			marketBreakoutDao.updateBean(m);
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("MarketBreakout交易取消失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("marketBreakoutCancelUpdate");
			be.setExceptionType("MarketBreakout交易取消失败！");
			throw be;
		}
	}
	
	/**
	 * 根据交易编号获得实体
	 */
	public MarketBreakoutInfo getBeanByTradeNo(String hql,String tradeNo){
		ArrayList list = new ArrayList();
		list.add(tradeNo);
		return marketBreakoutDao.getBeanByParams(hql, list);
	}
	
	/**
	 * 执行一条MarketBreakout交易
	 * 功能：在MarketBreakout交易表把这条MarketBreakout交易的交易状态改成DONE,更新账户交易统计表,账户表
	 */
	public void excuteMarketBreakoutAdd(MarketBreakoutInfo bean){
		try{		
			//1.更新账户表acc_info,先得到保证金里的两个要交易的币种的账户bean
			//weCcy卖出货币  anaCcy是买入货币
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
			
			Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());//weCcy币种对应的账户余额
			Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());//anaCcy币种对应的账户余额
			Double amount = Double.parseDouble(bean.getAmount());//MarketBreakout交易的实际交易金额
			Double amt = Double.parseDouble(bean.getAmount()) * Double.parseDouble(bean.getPrice());//兑换汇率后另一币种金额
			
			//weCcy币种对应的账户金额     = 余额-实际交易金额
			//anaCcy币种对应的账户金额   = 余额+实际交易金额*价格
			if (bean.getDirection().equals("0")){
				weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amount));
				anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amt));
			}
			else{
				weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amt));
				anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amount));
			}
			
			//2.更新人民币交易流水表c_tran_flow_mapping
			if (bean.getTranType().equals("C")){
				CTranFlowMapping c = new CTranFlowMapping();
				c.setUserNum(bean.getUserNum());
				CTranFlowMapping ctf = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
				//MarketBreakout交易次数加1，MarketBreakout交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
				ctf.setMarketBreakoutQty(String.valueOf(Integer.parseInt(ctf.getMarketBreakoutQty()) + 1));
				ctf.setMarketBreakoutAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(ctf.getMarketBreakoutAmt()) + amount));
				ctf.setCount(ctf.getCount() + 1);
				ctf.setAmount(ctf.getAmount() + amount);
				cTranFlowMappingDao.updateBean(ctf);
			}
			else{//更新外币交易流水表w_tran_flow_mapping
				WTranFlowMapping w = new WTranFlowMapping();
				w.setUserNum(bean.getUserNum());
				WTranFlowMapping wtf = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
				//MarketBreakout交易次数加1，MarketBreakout交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
				wtf.setMarketBreakoutQty(String.valueOf(Integer.parseInt(wtf.getMarketBreakoutQty()) + 1));
				wtf.setMarketBreakoutAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wtf.getMarketBreakoutAmt()) + amount));
				wtf.setCount(wtf.getCount() + 1);
				wtf.setAmount(wtf.getAmount() + amount);
				wTranFlowMappingDao.updateBean(wtf);
			}
			
			//3.更新bean的交易状态为完成
			bean.setStatue("D");
			bean.setLAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(amt));
			
			//4.把相关的bean更新到对应的表
			marketBreakoutDao.updateBean(bean);
			accInfoDao.updateBean(weAccInfo);
			accInfoDao.updateBean(anaAccInfo);
			
		}catch(Exception e){
			if (e instanceof BoException) {
				throw (BoException) e;
			}
			logger.error("MarketBreakout交易执行失败");
			logger.error(e.getMessage(), e);
			BoException be = new BoException("excuteMarketBreakoutAdd");
			be.setExceptionType("MarketBreakout交易执行失败");
			throw be;
		}
	}
	
	public MarketBreakoutDao getMarketBreakoutDao() {
		return marketBreakoutDao;
	}

	public void setMarketBreakoutDao(MarketBreakoutDao marketBreakoutDao) {
		this.marketBreakoutDao = marketBreakoutDao;
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
