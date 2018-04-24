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
 * Description  : 自动替代单交易
 * Modify Date  : 2014-07-24
 */
public class OcoManageBo extends BaseBo {

	private OcoDao ocoDao;
	private AccInfoDao accInfoDao;
	private CTranFlowMappingDao cTranFlowMappingDao;
	private WTranFlowMappingDao wTranFlowMappingDao;

	/**
	 * 添加交易
	 */
	public void ocoInfoAdd(OcoAndMarketModel model){
		try{
			OcoInfo o = new OcoInfo();
			AccInfo accInfo = new AccInfo();
			//根据买卖方向，设置tprice或sprice值与用户提交交易金额的乘积，以判断交易金额是否大于账户信息表相应货币剩余金额
			Double tPrice = 1.0;
			Double sPrice = 1.0;
			
			//根据交易买卖方向获取账户信息表实体，并将两个货币以本方货币（卖出货币）与对手方货币（买入货币）形式存入交易实体，0代表卖，1代表买
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
			
			//判断用户提交交易中金额是否满足要求，即不能大于账户信息表中相应货币剩余金额
			if (Double.parseDouble(model.gettAmount())*tPrice > Double.parseDouble(accInfo.getAmount()) || Double.parseDouble(model.getsAmount())*sPrice > Double.parseDouble(accInfo.getAmount())){
				logger.error("用户：" + UserModelUtil.getUser().getUserId() + " 添加OCO交易失败！账户"+ accInfo.getCcy() +"金额不足");
				BoException be = new BoException("ocoInfoAdd");
				if (accInfo.getAccType().equals("C"))
					be.setExceptionType("人民币账户"+ accInfo.getCcy() +"金额不足,OCO交易提交失败！");
				else
					be.setExceptionType("外币账户"+ accInfo.getCcy() +"金额不足,OCO交易提交失败！");
				throw be;
			}
			
			//将相关信息保存进交易实体，等待被添加
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
			logger.error("OCO交易失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("ocoInfoAdd");
			be.setExceptionType("OCO交易失败！用户："+UserModelUtil.getUser().getUserId());
			throw be;
		}
	}
	
	/**
	 * 取消交易
	 */
	public void ocoCancelUpdate(OcoInfo o){
		try{
			ocoDao.updateBean(o);
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("OCO交易取消失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("ocoCancelUpdate");
			be.setExceptionType("OCO交易取消失败！");
			throw be;
		}
	}
	
	/**
	 * 根据交易编号获得实体
	 */
	public OcoInfo getBeanByTradeNo(String hql,String tradeNo){
		ArrayList list = new ArrayList();
		list.add(tradeNo);
		return ocoDao.getBeanByParams(hql, list);
	}
	
	/**
	 * 执行一条OCO交易
	 * 功能：在OCO交易表把这条OCO交易的交易状态改成DONE,更新账户交易统计表,账户表
	 */
	public void excuteOcoAdd(OcoInfo bean){
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
			Double amount = Double.parseDouble(bean.getAmount());//OCO交易的实际交易金额
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
				//OCO交易次数加1，OCO交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
				ctf.setOcoQty(String.valueOf(Integer.parseInt(ctf.getOcoQty()) + 1));
				ctf.setOcoAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(ctf.getOcoAmt()) + amount));
				ctf.setCount(ctf.getCount() + 1);
				ctf.setAmount(ctf.getAmount() + amount);
				cTranFlowMappingDao.updateBean(ctf);
			}
			else{//更新外币交易流水表w_tran_flow_mapping
				WTranFlowMapping w = new WTranFlowMapping();
				w.setUserNum(bean.getUserNum());
				WTranFlowMapping wtf = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
				//OCO交易次数加1，OCO交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
				wtf.setOcoQty(String.valueOf(Integer.parseInt(wtf.getOcoQty()) + 1));
				wtf.setOcoAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wtf.getOcoAmt()) + amount));
				wtf.setCount(wtf.getCount() + 1);
				wtf.setAmount(wtf.getAmount() + amount);
				wTranFlowMappingDao.updateBean(wtf);
			}
			
			//3.更新bean的交易状态为完成
			bean.setStatue("D");
			bean.setLAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(amt));
			
			//4.把相关的bean更新到对应的表
			ocoDao.updateBean(bean);
			accInfoDao.updateBean(weAccInfo);
			accInfoDao.updateBean(anaAccInfo);
			
		}catch(Exception e){
			if (e instanceof BoException) {
				throw (BoException) e;
			}
			logger.error("OCO交易执行失败");
			logger.error(e.getMessage(), e);
			BoException be = new BoException("excuteOcoAdd");
			be.setExceptionType("OCO交易执行失败");
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
