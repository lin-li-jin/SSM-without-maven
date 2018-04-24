package com.talent.forex.modules.trade.bid;

import java.util.Date;

import com.talent.forex.bean.domain.StopLossInfo;
import com.talent.forex.bean.model.StopAndProfitModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.util.CodeTableUtil;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS002
 * Create By    : lzc
 * Description  : 止亏交易
 * Modify Date  : 2014-07-24
 */
public class StopLossTradeAction extends ForexBaseAction {

	//竞价交易页面初始化数据
	private String direct;
	private String ccy1;
	private String ccy2;
	private String bid;
	private String ask;
	
	private String tradeNo;
	
	private StopAndProfitModel stopAndProfitModel;
	
	private StopLossManageBo stopLossManageBo;
	
	/**
	 * 页面初始化
	 */
	public String pageInit(){
		requestPut("radioValue", 0);
		requestPut("tradeDirection",direct);
		requestPut("ccy1",ccy1);
		requestPut("ccy2",ccy2);
		requestPut("bidPrice",bid);
		requestPut("askPrice",ask);
		return SUCCESS;
	}
	
	/**
	 * 添加交易记录
	 */
	public String oneTradeRecordAdd(){
		stopLossManageBo.stopLossInfoAdd(stopAndProfitModel);
		
		requestPut("tradeTypeList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.TRADE_TYPE_LIST));
		requestPut("tradeStatusList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.TRADE_STATUS_LIST));
		requestPut("tradeType","0");
		requestPut("tradeStatus","0");
		
		if (stopAndProfitModel.getCcy2().equals("CNY"))
			return SUCCESS;
		return "foreign";
	}
	
	/**
	 * 取消交易
	 */
	public String oneTradeRecordCancel(){
		StopLossInfo s = stopLossManageBo.getBeanByTradeNo("getStopLossInfo",tradeNo);
		s.setStatue("C");
		stopLossManageBo.stopLossCancelUpdate(s);
		if (s.getTranType().equals("C"))
			return SUCCESS;
		return "foreign";
	}
	
	public String getDirect() {
		return direct;
	}

	public void setDirect(String direct) {
		this.direct = direct;
	}

	public String getCcy1() {
		return ccy1;
	}

	public void setCcy1(String ccy1) {
		this.ccy1 = ccy1;
	}

	public String getCcy2() {
		return ccy2;
	}

	public void setCcy2(String ccy2) {
		this.ccy2 = ccy2;
	}

	public StopLossManageBo getStopLossManageBo() {
		return stopLossManageBo;
	}

	public void setStopLossManageBo(StopLossManageBo stopLossManageBo) {
		this.stopLossManageBo = stopLossManageBo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public StopAndProfitModel getStopAndProfitModel() {
		return stopAndProfitModel;
	}

	public void setStopAndProfitModel(StopAndProfitModel stopAndProfitModel) {
		this.stopAndProfitModel = stopAndProfitModel;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}
	
}
