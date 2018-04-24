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
 * Description  : 一口价交易
 * Modify Date  : 2014-07-24
 */
public class OneClickManageBo extends BaseBo {

	private OneClickDao oneClickDao;
	private AccInfoDao accInfoDao;
	private CTranFlowMappingDao cTranFlowMappingDao;
	private WTranFlowMappingDao wTranFlowMappingDao;

	/**
	 * 添加交易记录
	 */
	public OneClickInfo oneClickInfoAdd(String direct, String ccy1, String ccy2, String amount, String price){
		OneClickInfo o = null;
		Double rate = 1.0;//根据买卖方向，设置price值与用户提交交易金额的乘积，以判断交易金额是否大于账户信息表相应货币剩余金额
		try{
			AccInfo accInfo = new AccInfo();
			o = new OneClickInfo();
			
			//根据交易买卖方向获取账户信息表实体，并将两个货币以本方货币（卖出货币）与对手方货币（买入货币）形式存入交易实体，0代表卖，1代表买
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
			
			//判断用户提交交易中金额是否满足要求，即不能大于账户信息表中相应货币剩余金额
			if (Double.parseDouble(amount) * rate > Double.parseDouble(accInfo.getAmount())){
				logger.error("用户：" + UserModelUtil.getUser().getUserId() + " 添加一口价交易失败！账户"+ accInfo.getCcy() +"金额不足");
				BoException be = new BoException("oneClickInfoAdd");
				if (accInfo.getAccType().equals("C"))
					be.setExceptionType("人民币账户"+ accInfo.getCcy() +"金额不足,一口价交易提交失败！");
				else
					be.setExceptionType("外币账户"+ accInfo.getCcy() +"金额不足,一口价交易提交失败！");
				throw be;
			}
			
			//将相关信息保存进交易实体，等待被添加
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
			o.setLAmount(CalculateUtil.getAmountAfterTrade(amount, price));//计算兑换后货币金额
			excuteOneClickAdd(o);
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("用户：" + UserModelUtil.getUser().getUserId() + " 添加一口价交易失败！");
			logger.error(e.getMessage(), e);
			BoException be = new BoException("oneClickInfoAdd");
			be.setExceptionType("一口价交易提交失败！");
			throw be;
		}
		return o;
	}
	
	/**
	 * 执行一条一口价交易
	 * 功能：在一口价交易表把这条一口价交易的交易状态改成DONE,更新账户交易统计表,账户表
	 */
	public void excuteOneClickAdd(OneClickInfo bean){
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
			Double amount = Double.parseDouble(bean.getAmount());//一口价交易的实际交易金额
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
				//一口价交易次数加1，一口价交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
				ctf.setOneClickQty(String.valueOf(Integer.parseInt(ctf.getOneClickQty()) + 1));
				ctf.setOneClickAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(ctf.getOneClickAmt()) + amount));
				ctf.setCount(ctf.getCount() + 1);
				ctf.setAmount(ctf.getAmount() + amount);
				cTranFlowMappingDao.updateBean(ctf);
			}
			else{//更新外币交易流水表w_tran_flow_mapping
				WTranFlowMapping w = new WTranFlowMapping();
				w.setUserNum(bean.getUserNum());
				WTranFlowMapping wtf = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
				//一口价交易次数加1，一口价交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
				wtf.setOneClickQty(String.valueOf(Integer.parseInt(wtf.getOneClickQty()) + 1));
				wtf.setOneClickAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wtf.getOneClickAmt()) + amount));
				wtf.setCount(wtf.getCount() + 1);
				wtf.setAmount(wtf.getAmount() + amount);
				wTranFlowMappingDao.updateBean(wtf);
			}
			
			//3.更新bean的交易状态为完成
			bean.setStatus("D");
			bean.setLAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(amt));
			
			//4.把相关的bean更新到对应的表
			oneClickDao.makePersistent(bean, false);
			accInfoDao.updateBean(weAccInfo);
			accInfoDao.updateBean(anaAccInfo);
			
		}catch(Exception e){
			if (e instanceof BoException) {
				throw (BoException) e;
			}
			logger.error("一口价交易执行失败");
			logger.error(e.getMessage(), e);
			BoException be = new BoException("excuteOneClickAdd");
			be.setExceptionType("一口价交易执行失败");
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
