package com.talent.forex.modules.trade.ask;

import java.util.ArrayList;
import java.util.List;

import com.talent.auth.bean.domain.Users;
import com.talent.auth.bean.model.UserModel;
import com.talent.forex.bean.domain.AnalogueMag;
import com.talent.forex.bean.model.SpotTradeModel;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.modules.trade.ask.SpotManageBo;
import com.talent.forex.util.UserModelUtil;

/*
 * Amendment No.: FOEXAS006
 * Create By    : 陈整队
 * Description  : 即期询价交易
 * Modify Date  : 2014-07-24
 */
public class SpotTradeAction extends ForexBaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ccy1;//交易的币种
	private String ccy2;//交易的币种
	private String price;//交易的价格
	private String direct;//交易方向
	private String amount;//交易的金额
	private String opponent;//对手方
	private String tranNo;//交易编号
	private String tranNo1;
	private String tranNo2;
	private String tranNo3;
	private String tranNo4;
	private String tranNo5;

	private SpotManageBo spotManageBo;

	private SpotTradeModel spotTradeModel;

	/**
	 * 页面初始化
	 * @return
	 */
	public String spotTradePageInit() {
		List collection = (List) spotManageBo.getOpponentGroupQuery();
		List<Users> list = new ArrayList<Users>();
		for (int i = 0; i < collection.size(); i++) {
			AnalogueMag analogueMag=(AnalogueMag) collection.get(i);
			list.addAll((List<Users>)(spotManageBo.getOpponentQuery(analogueMag.getAnaGroup())));
		}
		requestPut("tradeDirection", direct);
		requestPut("ccy1", ccy1);
		requestPut("ccy2", ccy2);
		requestPut("price", price);
		requestPut("opponentList", list);
		requestPut("radioValue", 0);
		return SUCCESS;
	}

	/**
	 * 提交所有对手方的交易！（已停用！）
	 * @return
	 */
	public String spotTradeAllOpponent() {
		int i = 0;// 标识跟多少个对手方交易！
		String weCcy=spotTradeModel.getWeCcy();
		String anaCcy=spotTradeModel.getAnaCcy();
		String amount=spotTradeModel.getAmount();
		String direction=spotTradeModel.getDirection();
		// 第一个交易对手方
		if (spotTradeModel.getOpponentOne() == null	|| spotTradeModel.getOpponentOne().equals("")) {
			
		} else {
			String provider = spotTradeModel.getOpponentOne();// 对手方账号
			String price = "";// 交易的价格
			if (direction.equals("1")) {//如果交易方向为买
				price = spotTradeModel.getBidOne();
			} else {
				price = spotTradeModel.getAskOne();
			}
			spotManageBo.otcSpotTradeAdd(spotTradeModel.getTranNo1(), provider, weCcy, anaCcy, price, direction, amount);
			i++;
		}
		// 第二个交易对手方
		if (spotTradeModel.getOpponentTwo() == null	|| spotTradeModel.getOpponentTwo().equals("")) {
			
		} else {
			String provider = spotTradeModel.getOpponentTwo();// 对手方账号
			String price = "";// 交易的价格
			if (direction.equals("1")) {//如果交易方向为买
				price = spotTradeModel.getBidTwo();
			} else {
				price = spotTradeModel.getAskTwo();
			}
			spotManageBo.otcSpotTradeAdd(spotTradeModel.getTranNo2(), provider, weCcy, anaCcy, price, direction, amount);
			i++;
		}
		// 第三个交易对手方
		if (spotTradeModel.getOpponentThree() == null || spotTradeModel.getOpponentThree().equals("")) {
		} else {
			String provider = spotTradeModel.getOpponentThree();// 对手方账号
			String price = "";// 交易的价格
			if (direction.equals("1")) {//如果交易方向为买
				price = spotTradeModel.getBidThree();
			} else {
				price = spotTradeModel.getAskThree();
			}
			spotManageBo.otcSpotTradeAdd(spotTradeModel.getTranNo3(), provider, weCcy, anaCcy, price, direction, amount);
			i++;
		}
		// 第四个交易对手方
		if (spotTradeModel.getOpponentFour() == null || spotTradeModel.getOpponentFour().equals("")) {
		} else {
			String provider = spotTradeModel.getOpponentFour();// 对手方账号
			String price = "";// 交易的价格
			if (direction.equals("1")) {//如果交易方向为买
				price = spotTradeModel.getBidFour();
			} else {
				price = spotTradeModel.getAskFour();
			}
			spotManageBo.otcSpotTradeAdd(spotTradeModel.getTranNo4(), provider, weCcy, anaCcy, price, direction, amount);
			i++;
		}
		// 第五个交易对手方
		if (spotTradeModel.getOpponentFive() == null || spotTradeModel.getOpponentFive().equals("")) {
		} else {
			String provider = spotTradeModel.getOpponentFive();// 对手方账号
			String price = "";// 交易的价格
			if (direction.equals("1")) {//如果交易方向为买
				price = spotTradeModel.getBidFive();
			} else {
				price = spotTradeModel.getAskFive();
			}
			spotManageBo.otcSpotTradeAdd(spotTradeModel.getTranNo5(), provider, weCcy, anaCcy, price, direction, amount);
			i++;
		}
		if (i > 0) {
			return SUCCESS;
		}
		return "fail";
	}

	/**
	 * 点击send后所触发的事件
	 * @return
	 */
	public String spotTradeSend() {
		if (opponent == null || opponent.equals("")) {
			return "fail";
		} else {			
			String dat = spotManageBo.otcSpotTradeAdd(tranNo, opponent, ccy1, ccy2, price, direct, amount);
			processText(dat, "text/plain;charset=GBK");
			return SUCCESS;
		}
	}
	
	/**
	 * 跟某一个对手方交易成功，接受价格
	 * @return
	 */
	public String spotTradeAccept() {
		if(tranNo1.equals(tranNo)){
			if(spotManageBo.acceptDone(tranNo1, opponent, price)){
				// 删除没有成功交易的记录
				spotManageBo.otcSpotTradeWithdraw(tranNo2);
				spotManageBo.otcSpotTradeWithdraw(tranNo3);
				spotManageBo.otcSpotTradeWithdraw(tranNo4);
				spotManageBo.otcSpotTradeWithdraw(tranNo5);
				return SUCCESS;
			}
		}
		else if(tranNo2.equals(tranNo)){
			if(spotManageBo.acceptDone(tranNo2, opponent, price)){
				// 删除没有成功交易的记录
				spotManageBo.otcSpotTradeWithdraw(tranNo1);
				spotManageBo.otcSpotTradeWithdraw(tranNo3);
				spotManageBo.otcSpotTradeWithdraw(tranNo4);
				spotManageBo.otcSpotTradeWithdraw(tranNo5);
				return SUCCESS;
			}			
		}
		else if(tranNo3.equals(tranNo)){
			if(spotManageBo.acceptDone(tranNo3, opponent, price)){
				// 删除没有成功交易的记录
				spotManageBo.otcSpotTradeWithdraw(tranNo2);
				spotManageBo.otcSpotTradeWithdraw(tranNo1);
				spotManageBo.otcSpotTradeWithdraw(tranNo4);
				spotManageBo.otcSpotTradeWithdraw(tranNo5);
				return SUCCESS;
			}
		}
		else if(tranNo4.equals(tranNo)){
			if(spotManageBo.acceptDone(tranNo4, opponent, price)){
				// 删除没有成功交易的记录
				spotManageBo.otcSpotTradeWithdraw(tranNo2);
				spotManageBo.otcSpotTradeWithdraw(tranNo3);
				spotManageBo.otcSpotTradeWithdraw(tranNo1);
				spotManageBo.otcSpotTradeWithdraw(tranNo5);
				return SUCCESS;
			}
		}
		else if(tranNo5.equals(tranNo)){
			if(spotManageBo.acceptDone(tranNo5, opponent, price)){
				// 删除没有成功交易的记录
				spotManageBo.otcSpotTradeWithdraw(tranNo2);
				spotManageBo.otcSpotTradeWithdraw(tranNo3);
				spotManageBo.otcSpotTradeWithdraw(tranNo4);
				spotManageBo.otcSpotTradeWithdraw(tranNo1);
				return SUCCESS;
			}
		}
		return "fail";
		/*String weCcy="";
		String anaCcy="";
		if(direct.equals("0")){
			weCcy=ccy1;
			anaCcy=ccy2;
		}else{
			weCcy=ccy2;
			anaCcy=ccy1;
		}
		if (opponent == null || opponent.equals("")) {
			return "fail";
		} else {
			if(spotManageBo.acceptDone(tranNo, opponent, price, amount, ccy1, ccy2, direct)){
				// 删除没有成功交易的记录
				spotManageBo.otcSpotTradeDel(tranNo, UserModelUtil.getUser().getUserId(),weCcy,anaCcy);
				return SUCCESS;
			}
			return "fail";
		}*/
	}
	
	/**
	 * 轮询table价格变化
	 */
	public void spotTradeCheckPrice(){
		if (opponent == null || opponent.equals("")) {
			processText("", "text/plain;charset=GBK");
		} 
		else {
			String dat = spotManageBo.otcSpotTradeCheckPrice(tranNo, opponent);
			processText(dat, "text/plain;charset=GBK");
		}
	}
	
	/**
	 * 删除交易记录，仅限于对手方终止交易时才使用！
	 */
	public void spotTradeDel(){
		if (opponent == null || opponent.equals("")) {
			processText("", "text/plain;charset=GBK");
		} 
		else {
			String flag = spotManageBo.otcSpotTradeDel(tranNo);
			processText(flag, "text/plain;charset=GBK");
		}
	}
	
	/**
	 * 本方withdraw交易所触发的事件
	 */
	public void spotTradeWithdraw(){
		if(tranNo1.equals("") || tranNo1 == null){
		}
		else{
			spotManageBo.otcSpotTradeWithdraw(tranNo1);	
		}
		if(tranNo2.equals("") || tranNo2 == null){
		}
		else{
			spotManageBo.otcSpotTradeWithdraw(tranNo2);
		}
		if(tranNo3.equals("") || tranNo3 == null){
		}
		else{
			spotManageBo.otcSpotTradeWithdraw(tranNo3);
		}
		if(tranNo4.equals("") || tranNo4 == null){
		}
		else{
			spotManageBo.otcSpotTradeWithdraw(tranNo4);
		}
		if(tranNo5.equals("") || tranNo5 == null){
		}
		else{
			spotManageBo.otcSpotTradeWithdraw(tranNo5);
		}
		
		String dat = "1";
		processText(dat, "text/plain;charset=GBK");
	}
	
	public SpotTradeModel getSpotTradeModel() {
		return spotTradeModel;
	}

	public void setSpotTradeModel(SpotTradeModel spotTradeModel) {
		this.spotTradeModel = spotTradeModel;
	}

	public SpotManageBo getSpotManageBo() {
		return spotManageBo;
	}

	public void setSpotManageBo(SpotManageBo spotManageBo) {
		this.spotManageBo = spotManageBo;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDirect() {
		return direct;
	}

	public void setDirect(String direct) {
		this.direct = direct;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public String getTranNo() {
		return tranNo;
	}

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}

	public String getTranNo1() {
		return tranNo1;
	}

	public void setTranNo1(String tranNo1) {
		this.tranNo1 = tranNo1;
	}

	public String getTranNo2() {
		return tranNo2;
	}

	public void setTranNo2(String tranNo2) {
		this.tranNo2 = tranNo2;
	}

	public String getTranNo3() {
		return tranNo3;
	}

	public void setTranNo3(String tranNo3) {
		this.tranNo3 = tranNo3;
	}

	public String getTranNo4() {
		return tranNo4;
	}

	public void setTranNo4(String tranNo4) {
		this.tranNo4 = tranNo4;
	}

	public String getTranNo5() {
		return tranNo5;
	}

	public void setTranNo5(String tranNo5) {
		this.tranNo5 = tranNo5;
	}

}
