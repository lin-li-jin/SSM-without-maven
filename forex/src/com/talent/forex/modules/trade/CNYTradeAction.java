package com.talent.forex.modules.trade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.talent.auth.bean.model.UserModel;
import com.talent.forex.bean.model.CcyModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.modules.rateFactory.RateReceive;
import com.talent.forex.util.CodeTableUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS010
 * Create By    : lzc
 * Description  : 人民币交易首页
 * Modify Date  : 2014-07-22
 */
public class CNYTradeAction extends ForexBaseAction {
	
	//接受请求中的参数
	private String tradeType;
	private String tradeStatus;
	private String table;//交易所属实体（查看详细交易记录）
	private String tradeNo;//交易编号（查看详细交易记录）
	private String page;//判别是否由一口价交易页面跳转
	
	private String action = "success";//用于判断交易状态为active时跳转页面
	
	private CNYManageBo CNYBo;

	public CNYManageBo getCNYBo() {
		return CNYBo;
	}

	public void setCNYBo(CNYManageBo cNYBo) {
		CNYBo = cNYBo;
	}
	
	public void CNYPageRefresh(){
		HashMap map1 = new HashMap();
		HashMap map2 = new HashMap();  
        List<CcyModel> bidCollection = (List<CcyModel>) RateReceive.getInstance().getRmbJingJiaRate();
		List<CcyModel> askCollection = (List<CcyModel>) RateReceive.getInstance().getRmbXunJiaRate();
		for (int i=0; i<bidCollection.size(); i++){
			map1.put("B" + bidCollection.get(i).getCcy(), bidCollection.get(i));
		}
		for (int i=0; i<askCollection.size(); i++){
			map2.put("A" + askCollection.get(i).getCcy(), askCollection.get(i));
		}
        JSONObject jsonObject1 = JSONObject.fromObject(map1);  
        JSONObject jsonObject2 = JSONObject.fromObject(map2);  
        String data1=jsonObject1.toString();
        String data2=jsonObject2.toString();
        String data=data1+"$"+data2;
		processText(data,"text/plain;charset=GBK");
	}

	/**
	 * 人民币交易菜单页面初始化
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String pageInit(){
		Collection collection = CNYBo.CNYAllTradeRecordListQuery("getAllListByTranType", UserModelUtil.getUser().getUserId(), "C");
		collection.addAll(CNYBo.tradeRecordListQuery("getOtcSwapListByUserNum", UserModelUtil.getUser().getUserId(), "", "C"));
		
		if (collection != null && collection.size() > 0){
			requestPut("collection", collection);
		}
		List<CcyModel> bidCollection = (List<CcyModel>) RateReceive.getInstance().getRmbJingJiaRate();
		List<CcyModel> askCollection = (List<CcyModel>) RateReceive.getInstance().getRmbXunJiaRate();
		for (int i=0; i<bidCollection.size(); i++){
			requestPut("B" + bidCollection.get(i).getCcy(),bidCollection.get(i));
		}
		for (int i=0; i<askCollection.size(); i++){
			requestPut("A" + askCollection.get(i).getCcy(),askCollection.get(i));
		}
		requestPut("tradeTypeList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.TRADE_TYPE_LIST));
		requestPut("tradeStatusList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.TRADE_STATUS_LIST));
		return SUCCESS;
	}
	
	/**
	 * 一口价交易页面初始化
	 */
	public String oneClickPageInit(){
		Collection<?> collection = CNYBo.CNYAllTradeRecordListQuery("getAllListByTranType",UserModelUtil.getUser().getUserId(),"C");
		if (collection.size() > 0){
			requestPut("collection",collection);
		}
		List<CcyModel> bidCollection = (List<CcyModel>) RateReceive.getInstance().getRmbJingJiaRate();
		for (int i=0; i<bidCollection.size(); i++){
			requestPut(bidCollection.get(i).getCcy(),bidCollection.get(i));
		}
		requestPut("tradeTypeList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.TRADE_TYPE_LIST));
		requestPut("tradeStatusList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.TRADE_STATUS_LIST));
		return SUCCESS;
	}
	
	/**
	 * 用户交易记录查询
	 */
	public String tradeQuery(){
		UserModel user= UserModelUtil.getUser();
		
		requestPut("tradeTypeList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.TRADE_TYPE_LIST));
		requestPut("tradeStatusList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.TRADE_STATUS_LIST));
		requestPut("tradeType",tradeType);
		requestPut("tradeStatus",tradeStatus);
		
		Collection<?> collection = getCollectionByUserNumAndStatus(user);
		if (collection.size() > 0){
			requestPut("collection",collection);
		}
		if (page != null){
			if (page.equals("OC"))
				return "oneclick";
		}
		List<CcyModel> bidCollection = (List<CcyModel>) RateReceive.getInstance().getRmbJingJiaRate();
		List<CcyModel> askCollection = (List<CcyModel>) RateReceive.getInstance().getRmbXunJiaRate();
		for (int i=0; i<bidCollection.size(); i++){
			requestPut("B" + bidCollection.get(i).getCcy(),bidCollection.get(i));
		}
		for (int i=0; i<askCollection.size(); i++){
			requestPut("A" + askCollection.get(i).getCcy(),askCollection.get(i));
		} 
		return SUCCESS;
	}
	
	/**
	 * 用户一条交易详细记录查询
	 */
	public String tradeDetail(){
		List<?> list = (List<?>)getTradeDetailByTradeNo();
		if (list.size() > 0){
			requestPut("tradeCard",list.get(0));
			requestPut("table", table);
		}
		return action;
	}
	
	/**
	 * 通过用户名、交易类型和交易状态查询出交易记录
	 */
	private Collection<?> getCollectionByUserNumAndStatus(UserModel user){
		//System.out.println("------------"+tradeType+"--------------");
		if (tradeType.equals("A"))
			return CNYBo.tradeRecordListQuery("getOneClickListByUserNum",user.getUserId(),tradeStatus,"C");
		else if (tradeType.equals("B"))
			return CNYBo.tradeRecordListQuery("getStopLossListByUserNum",user.getUserId(),tradeStatus,"C");
		else if (tradeType.equals("C"))
			return CNYBo.tradeRecordListQuery("getTakeProfitListByUserNum",user.getUserId(),tradeStatus,"C");
		else if (tradeType.equals("D"))
			return CNYBo.tradeRecordListQuery("getOcoListByUserNum",user.getUserId(),tradeStatus,"C");
		else if (tradeType.equals("E"))
			return CNYBo.tradeRecordListQuery("getMarketBreakoutListByUserNum",user.getUserId(),tradeStatus,"C");
		else if (tradeType.equals("F"))
			return CNYBo.tradeRecordListQuery("getOtcSpotListByUserNum",user.getUserId(),tradeStatus,"C");
		else if (tradeType.equals("G"))
			return CNYBo.tradeRecordListQuery("getOtcForwardListByUserNum",user.getUserId(),tradeStatus,"C");
		else if (tradeType.equals("H"))
			return CNYBo.tradeRecordListQuery("getOtcSwapListByUserNum",user.getUserId(),tradeStatus,"C");
		else if (tradeType.equals("") && !tradeStatus.equals("")){
			Collection c = CNYBo.tradeRecordListQuery("getAllListByStatus", user.getUserId(), tradeStatus,"C");
			c.addAll(CNYBo.tradeRecordListQuery("getOtcSwapListByUserNum",user.getUserId(),tradeStatus,"C"));
			return c;
		}
		else{
			Collection c = CNYBo.CNYAllTradeRecordListQuery("getAllListByTranType", user.getUserId(),"C");
			c.addAll(CNYBo.tradeRecordListQuery("getOtcSwapListByUserNum",user.getUserId(),tradeStatus,"C"));
			return c;			
		}
	}
	
	/**
	 * 根据交易编号获得该条交易记录的的详情
	 */
	private Collection<?> getTradeDetailByTradeNo(){
		if (table.equals("OneClickInfo"))
			return CNYBo.tradeDetailQuery("getOneClickDetailByTradeNo",tradeNo);
		else if (table.equals("StopLossInfo")){
			if (tradeStatus.equals("A")){
				action = "STActive";
				return CNYBo.tradeDetailQuery("getStopLossActiveDetailByTradeNo",tradeNo);
			}
			return CNYBo.tradeDetailQuery("getStopLossDetailByTradeNo",tradeNo);
		}
		else if (table.equals("TakeProfitInfo")){
			if (tradeStatus.equals("A")){
				action = "STActive";
				return CNYBo.tradeDetailQuery("getTakeProfitActiveDetailByTradeNo",tradeNo);
			}
			return CNYBo.tradeDetailQuery("getTakeProfitDetailByTradeNo",tradeNo);
		}
		else if (table.equals("OcoInfo")){
			if (tradeStatus.equals("A")){
				action = "OMActive";
				return CNYBo.tradeDetailQuery("getOcoActiveDetailByTradeNo",tradeNo);
			}
			return CNYBo.tradeDetailQuery("getOcoDetailByTradeNo",tradeNo);
		}
		else if (table.equals("MarketBreakoutInfo")){
			if (tradeStatus.equals("A")){
				action = "OMActive";
				return CNYBo.tradeDetailQuery("getMarketBreakoutActiveDetailByTradeNo",tradeNo);
			}
			return CNYBo.tradeDetailQuery("getMarketBreakoutDetailByTradeNo",tradeNo);
		}
		else if (table.equals("OtcForwardInfo")){
			action="Forward";
			return CNYBo.tradeDetailQuery("getOtcForwardDetailByTradeNo",tradeNo);
		}
		else if (table.equals("OtcSpotInfo")){
			action="Spot";
			return CNYBo.tradeDetailQuery("getOtcSpotDetailByTradeNo",tradeNo);
		}
		else if (table.equals("OtcSwapInfo")){
			action="Swap";
			return CNYBo.tradeDetailQuery("getOtcSwapDetailByTradeNo",tradeNo);
		}
		return null;
	}
	
	public void getCNYRate(){
		String dat = "";
		List<CcyModel> bidCollection = (List<CcyModel>) RateReceive.getInstance().getRmbJingJiaRate();
		List<CcyModel> askCollection = (List<CcyModel>) RateReceive.getInstance().getRmbXunJiaRate();
		for (int i=0; i<bidCollection.size(); i++){
			requestPut("B" + bidCollection.get(i).getCcy(),bidCollection.get(i));
		}
		for (int i=0; i<askCollection.size(); i++){
			requestPut("A" + askCollection.get(i).getCcy(),askCollection.get(i));
		} 
		processText(dat,"text/plain;charset=GBK");
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
}
