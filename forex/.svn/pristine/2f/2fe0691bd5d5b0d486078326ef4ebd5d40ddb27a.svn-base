package com.talent.forex.modules.trade.ask;

import java.util.Collection;

import com.talent.forex.bean.domain.OtcForwardInfo;
import com.talent.forex.bean.domain.OtcSpotInfo;
import com.talent.forex.bean.domain.OtcSwapInfo;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.util.GetDateTimeUtil;

/*
 * Amendment No.: FOEXAS006
 * Create By    : 陈整队
 * Description  : 即期询价交易
 * Modify Date  : 2014-07-24
 */
public class TradeMessageAction extends ForexBaseAction {
	private TradeMessageBo tradeMessageBo;
	private String tradeType;//询价交易的类型
	private String tranNo;//交易的序号
	private String provider;//对手方
	private String price;//交易的价格
	private String point;
	
	public String tradeMessageInit(){
		requestPut("collection", tradeMessageBo.getOtcSpotInfoQuery());
		requestPut("tradeType", "0");
		return SUCCESS;
	}
	public String tradeMessageQuery(){
		Collection<?> collection=null;
		if(tradeType.equals("0")){
			collection=tradeMessageBo.getOtcSpotInfoQuery();
			requestPut("collection",collection );
		}else if(tradeType.equals("1")){
			collection=tradeMessageBo.getOtcForwardInfoQuery();
			requestPut("collection", collection);
		}else if(tradeType.equals("2")){
			collection= tradeMessageBo.getOtcSwapInfoQuery();
			requestPut("collection",collection);
		}
		requestPut("tradeType", tradeType);
		return SUCCESS;
	}
	public String tradeMessageDetail(){
		//String userNum = UserModelUtil.getUser().getUserId();
		requestPut("tradeType", tradeType);
		if(tradeType.equals("0")){
			OtcSpotInfo bean = tradeMessageBo.otcSpotInfoGet(tranNo, provider);
			if(bean.getDirection().equals("0")){
				requestPut("direction", "0");
				requestPut("weCcy", bean.getWeCcy());
				requestPut("anaCcy", bean.getAnaCcy());
			}
			else {
				requestPut("direction", "1");
				requestPut("weCcy", bean.getAnaCcy());
				requestPut("anaCcy", bean.getWeCcy());
			}
			requestPut("bean",bean);			
			requestPut("price",bean.getPrice());
			String times = bean.getTimes();
			requestPut("times", times);
			
			return "spot";
		}else if(tradeType.equals("1")){
			OtcForwardInfo bean=tradeMessageBo.otcForwardInfoGet(tranNo, provider);
			if(bean.getDirection().equals("0")){
				requestPut("direction", "0");
				requestPut("weCcy", bean.getWeCcy());
				requestPut("anaCcy", bean.getAnaCcy());
			}
			else {
				requestPut("direction", "1");
				requestPut("weCcy", bean.getAnaCcy());
				requestPut("anaCcy", bean.getWeCcy());
			}
			requestPut("valueDate", GetDateTimeUtil.dateTransFmt(bean.getValueDate()));
			requestPut("bean",bean);
			requestPut("price",bean.getPrice());
			String times = bean.getTimes();
			requestPut("times", times);
			return "forward";
		}
		else{
			OtcSwapInfo bean = tradeMessageBo.otcSwapInfoGet(tranNo, provider);
			requestPut("times", bean.getTimes());
			if(bean.getDirection().equals("0")){
				requestPut("direction", "0");
				requestPut("weCcy", bean.getWeCcy());
				requestPut("anaCcy", bean.getAnaCcy());
				double price = Double.parseDouble(bean.getPrice()) + Double.parseDouble(bean.getPoint()) / 10000;
				requestPut("askPrice", String.valueOf(price));
				requestPut("bidPrice", bean.getPrice());
				requestPut("receiveCcy", bean.getAnaCcy());
				requestPut("payCcy", bean.getWeCcy());
			}
			else {
				requestPut("direction", "1");
				requestPut("weCcy", bean.getAnaCcy());
				requestPut("anaCcy", bean.getWeCcy());
				double price = Double.parseDouble(bean.getPrice()) + Double.parseDouble(bean.getPoint()) / 10000;
				requestPut("bidPrice", String.valueOf(price));
				requestPut("askPrice", bean.getPrice());
				requestPut("receiveCcy", bean.getWeCcy());
				requestPut("payCcy", bean.getAnaCcy());
			}
			requestPut("startDate", GetDateTimeUtil.dateTransFmt(bean.getStartDate()));
			requestPut("maturityDate", GetDateTimeUtil.dateTransFmt(bean.getMaturityDate()));
			if(bean.getFixedType().equals("0")){//0为receive
				requestPut("fixedType", "Receive");
				requestPut("fixedRate", bean.getReceiveRate());
				requestPut("floatingType" ,"Pay");
			}
			else{//1为pay
				requestPut("fixedType", "Pay");
				requestPut("fixedRate", bean.getPayRate());
				requestPut("floatingType", "Receive");
			}
			int flag = Integer.parseInt(bean.getFrequency());
			String frequency = "";
			switch(flag){
			case 0:
				frequency = "隔夜";
				break;
			case 1:
				frequency = "一周";
				break;
			case 2:
				frequency = "一个月";
				break;
			case 3:
				frequency = "两个月";
				break;
			case 4:
				frequency = "一季度";
				break;
			case 5:
				frequency = "半年";
				break;
			case 6:
				frequency = "一年";
				break;
			}
			int l = Integer.parseInt(bean.getLibor());
			String libor = "";
			switch(l){
			case 0:
				libor = "隔夜";
				break;
			case 1:
				libor = "1周";
				break;
			case 2:
				libor = "1个月";
				break;
			case 3:
				libor = "2个月";
				break;
			case 4:
				libor = "3个月";
				break;
			case 5:
				libor = "6个月";
				break;
			case 6:
				libor = "12个月";
				break;
			}
			requestPut("frequency", frequency);
			requestPut("libor", libor);
			requestPut("bean", bean);
			return "swap";
		}
	}

	public String sendBySpot(){
		tradeMessageBo.sendBySpotModify(tranNo, provider, price);
		return SUCCESS;
	}
	public String closeBySpot(){
		tradeMessageBo.closeBySpotDel(tranNo, provider);
		return SUCCESS;
	}
	public String sendByForward(){
		tradeMessageBo.sendByForwardModify(tranNo, provider, point);
		return SUCCESS;
	}
	public String closeByForward(){
		tradeMessageBo.closeByForwardDel(tranNo, provider);
		return SUCCESS;
	}
	public String sendBySwap(){
		tradeMessageBo.sendBySwapModify(tranNo, provider, point);
		return SUCCESS;
	}
	public String closeBySwap(){
		tradeMessageBo.closeBySwapDel(tranNo, provider);
		return SUCCESS;
	}
	
	public TradeMessageBo getTradeMessageBo() {
		return tradeMessageBo;
	}

	public void setTradeMessageBo(TradeMessageBo tradeMessageBo) {
		this.tradeMessageBo = tradeMessageBo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTranNo() {
		return tranNo;
	}
	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	
}
