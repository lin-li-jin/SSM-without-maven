package com.talent.forex.modules.trade.bid;

import java.util.Date;

import com.talent.forex.bean.domain.StopLossInfo;
import com.talent.forex.bean.domain.TakeProfitInfo;
import com.talent.forex.bean.model.StopAndProfitModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.util.CodeTableUtil;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.UserModelUtil;

/*
 * Amendment No.: FOEXAS003
 * Create By    : lzc
 * Description  : ֹӯ����
 * Modify Date  : 2014-07-24
 */
public class TakeProfitTradeAction extends ForexBaseAction {

	private String direct;
	private String ccy1;
	private String ccy2;
	private String bid;
	private String ask;
	
	private String tradeNo;
	
	private StopAndProfitModel stopAndProfitModel;

	private TakeProfitManageBo takeProfitManageBo;

	/**
	 * ҳ���ʼ��
	 */
	public String pageInit() {
		requestPut("radioValue", 1);
		requestPut("tradeDirection", direct);
		requestPut("ccy1", ccy1);
		requestPut("ccy2", ccy2);
		requestPut("bidPrice",bid);
		requestPut("askPrice",ask);
		return SUCCESS;
	}

	/**
	 * ��ӽ��׼�¼
	 */
	public String oneTradeRecordAdd(){
		takeProfitManageBo.takeProfitInfoAdd(stopAndProfitModel);
		
		requestPut("tradeTypeList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.TRADE_TYPE_LIST));
		requestPut("tradeStatusList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.TRADE_STATUS_LIST));
		requestPut("tradeType","0");
		requestPut("tradeStatus","0");
		
		if (stopAndProfitModel.getCcy2().equals("CNY"))
			return SUCCESS;
		return "foreign";
	}
	
	/**
	 * ȡ������
	 */
	public String oneTradeRecordCancel(){
		TakeProfitInfo t = takeProfitManageBo.getBeanByTradeNo("getTakeProfitInfo",tradeNo);
		t.setStatue("C");
		takeProfitManageBo.takeProfitCancelUpdate(t);
		if (t.getTranType().equals("C"))
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

	public TakeProfitManageBo getTakeProfitManageBo() {
		return takeProfitManageBo;
	}

	public void setTakeProfitManageBo(TakeProfitManageBo takeProfitManageBo) {
		this.takeProfitManageBo = takeProfitManageBo;
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
