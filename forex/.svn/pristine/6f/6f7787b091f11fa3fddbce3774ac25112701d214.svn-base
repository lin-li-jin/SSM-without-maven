package com.talent.forex.modules.trade.bid;

import java.util.ArrayList;

import org.hibernate.criterion.MatchMode;

import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.TakeProfitInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.bean.model.StopAndProfitModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.TakeProfitDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.SequenceUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS003
 * Create By    : lzc
 * Description  : 止盈交易
 * Modify Date  : 2014-07-24
 */
public class TakeProfitManageBo extends BaseBo {

	private TakeProfitDao takeProfitDao;
	private AccInfoDao accInfoDao;
	private CTranFlowMappingDao cTranFlowMappingDao;
	private WTranFlowMappingDao wTranFlowMappingDao;
	
	/**
	 * 添加一条交易记录
	 */
	public void takeProfitInfoAdd(StopAndProfitModel model){
		try{
			TakeProfitInfo t = new TakeProfitInfo();
			AccInfo accInfo = new AccInfo();
			Double price = 1.0;//根据买卖方向，设置price值与用户提交交易金额的乘积，以判断交易金额是否大于账户信息表相应货币剩余金额
			
			//根据交易买卖方向获取账户信息表实体，并将两个货币以本方货币（卖出货币）与对手方货币（买入货币）形式存入交易实体，0代表卖，1代表买
			if (model.getTradeDirection().equals("0")){
				t.setWeCcy(model.getCcy1());
				t.setAnaCcy(model.getCcy2());
				accInfo.setCcy(model.getCcy1());
			}
			else{
				t.setWeCcy(model.getCcy2());
				t.setAnaCcy(model.getCcy1());
				accInfo.setCcy(model.getCcy2());
				price = Double.parseDouble(model.getPrice());
			}
			if (model.getCcy2().equals("CNY")){
				t.setTranType("C");
				accInfo.setAccType("C");
			}else{
				t.setTranType("W");
				accInfo.setAccType("W");
			}
			accInfo.setUserNum(UserModelUtil.getUser().getUserId());
			accInfo = accInfoDao.getBeanByBean(accInfo, MatchMode.ANYWHERE);
			
			//判断用户提交交易中金额是否满足要求，即不能大于账户信息表中相应货币剩余金额
			if (Double.parseDouble(model.getAmount()) * price > Double.parseDouble(accInfo.getAmount())){
				logger.error("用户：" + UserModelUtil.getUser().getUserId() + " 添加Take Profit交易失败！账户"+ accInfo.getCcy() +"金额不足");
				BoException be = new BoException("takeProfitInfoAdd");
				if (accInfo.getAccType().equals("C"))
					be.setExceptionType("人民币账户"+ accInfo.getCcy() +"金额不足,Take Profit交易提交失败！");
				else
					be.setExceptionType("外币账户"+ accInfo.getCcy() +"金额不足,Take Profit交易提交失败！");
				throw be;
			}
			
			//将相关信息保存进交易实体，等待被添加
			t.setTranNo(SequenceUtil.getNextTranSeq("TP"));
			t.setUserNum(UserModelUtil.getUser().getUserId());
			t.setAmount(FormatParamUtil.getAmountAndPriceFmt(model.getAmount()));
			t.setDirection(model.getTradeDirection());
			t.setPrice(FormatParamUtil.getAmountAndPriceFmt(model.getPrice()));
			t.setMonitorPrice("");//因为takeProfit不用监控价格  但是志诚那时候业务做错了   这个不用存
			if (model.getActiveTime().equals(""))
				t.setGoodFrom(GetDateTimeUtil.getCurrentDate());
			else
				t.setGoodFrom(GetDateTimeUtil.dateTransFmt2(model.getActiveTime()));
			if (model.getCancelTime().equals(""))
				t.setGoodTill(SysParamNameConst.UNLIMITED_TIME);
			else
				t.setGoodTill(GetDateTimeUtil.dateTransFmt2(model.getCancelTime()));
			t.setCreateDatetime(GetDateTimeUtil.getCurrentDateTimeToMinute());
			t.setStatue("A");
			
			takeProfitDao.takeProfitInfoAdd(t);
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("Take Profit交易失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("takeProfitInfoAdd");
			be.setExceptionType("Take Profit交易失败！");
			throw be;
		}
	}

	/**
	 * 取消交易
	 */
	public void takeProfitCancelUpdate(TakeProfitInfo t){
		try{
			takeProfitDao.updateBean(t);
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("Take Profit交易取消失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("takeProfitCancelUpdate");
			be.setExceptionType("Take Profit交易取消失败！");
			throw be;
		}
	}
	
	/**
	 * 根据交易编号获得实体
	 */
	public TakeProfitInfo getBeanByTradeNo(String hql,String tradeNo){
		ArrayList list = new ArrayList();
		list.add(tradeNo);
		return takeProfitDao.getBeanByParams(hql, list);
	}
	
	
	
	public TakeProfitDao getTakeProfitDao() {
		return takeProfitDao;
	}

	public void setTakeProfitDao(TakeProfitDao takeProfitDao) {
		this.takeProfitDao = takeProfitDao;
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
