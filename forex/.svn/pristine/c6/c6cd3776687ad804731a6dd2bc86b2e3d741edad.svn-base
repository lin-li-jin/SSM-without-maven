package com.talent.forex.modules.trade.ask;

import java.util.ArrayList;
import java.util.List;

import com.talent.auth.bean.domain.Users;
import com.talent.auth.bean.model.UserModel;
import com.talent.forex.bean.domain.AnalogueMag;
import com.talent.forex.bean.model.ForwardTradeModel;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.util.UserModelUtil;

/*
 * Amendment No.: FOEXAS006
 * Create By    : 陈整队
 * Description  : 即期询价交易
 * Modify Date  : 2014-07-24
 */
public class ForwardTradeAction extends ForexBaseAction {

	private static final long serialVersionUID = 1L;
	private String ccy1;//交易的币种
	private String ccy2;//交易的币种
	private String price;//显示的价格
	private String direct;//交易的方向
	private String opponent;//对手方
	private String amount;//交易的金额
	private String valueDate;//交易的时间
	private String point;//基本点
	private String tranNo;//交易编号
	private String tranNo1;
	private String tranNo2;
	private String tranNo3;
	private String tranNo4;
	private String tranNo5;
	
	private ForwardManageBo forwardManageBo;

	private ForwardTradeModel forwardTradeModel;

	public String forwardTradepageInit() {
		UserModel user = UserModelUtil.getUser();
		String initGroup = user.getGroupTwoId();
		List collection = (List) forwardManageBo.getOpponentGroupQuery(initGroup);
		List<Users> list = new ArrayList<Users>();
		for (int i = 0; i < collection.size(); i++) {
			AnalogueMag analogueMag=(AnalogueMag) collection.get(i);
			list.addAll((List<Users>)(forwardManageBo.getOpponentQuery(analogueMag.getAnaGroup())));
		}
		requestPut("tradeDirection", direct);
		requestPut("ccy1", ccy1);
		requestPut("ccy2", ccy2);
		requestPut("price", price);
		requestPut("opponentList", list);
		requestPut("radioValue", 1);
		return SUCCESS;
	}

	// 提交所有对手方的交易！
	public String forwardTradeAllOpponent() {
		int i = 0;// 标识跟多少个对手方交易！
		String weCcy=forwardTradeModel.getWeCcy();
		String anaCcy=forwardTradeModel.getAnaCcy();
		String amount1=forwardTradeModel.getAmount();
		String direction=forwardTradeModel.getDirection();
		String valueDate1=forwardTradeModel.getDate();
		String price1=forwardTradeModel.getPrice();
		// 第一个交易对手方
		if (forwardTradeModel.getOpponentOne() == null || forwardTradeModel.getOpponentOne().equals("")) {
		} else {
			String provider = forwardTradeModel.getOpponentOne();// 对手方账号
			String point = "";
			if (forwardTradeModel.getDirection().equals("1")) {
				point = forwardTradeModel.getBidOne();// 交易的价格
			} else {
				point = forwardTradeModel.getAskOne();// 交易的价格
			}
			forwardManageBo.otcForwardTradeAdd(forwardTradeModel.getTranNo1(), provider, weCcy, anaCcy, price1, direction, amount1,valueDate1,point);
			i++;
		}
		// 第二个交易对手方
		if (forwardTradeModel.getOpponentTwo() == null
				|| forwardTradeModel.getOpponentTwo().equals("")) {
		} else {
			String provider = forwardTradeModel.getOpponentTwo();// 对手方账号
			String point = "";
			if (forwardTradeModel.getDirection().equals("1")) {
				point = forwardTradeModel.getBidTwo();// 交易的价格
			} else {
				point = forwardTradeModel.getAskTwo();// 交易的价格
			}
			forwardManageBo.otcForwardTradeAdd(forwardTradeModel.getTranNo2(), provider, weCcy, anaCcy, price1, direction, amount1,valueDate1,point);
			i++;
		}
		// 第三个交易对手方
		if (forwardTradeModel.getOpponentThree() == null
				|| forwardTradeModel.getOpponentThree().equals("")) {
		} else {
			String provider = forwardTradeModel.getOpponentThree();// 对手方账号
			String point = "";
			if (forwardTradeModel.getDirection().equals("1")) {
				point = forwardTradeModel.getBidThree();// 交易的价格
			} else {
				point = forwardTradeModel.getAskThree();// 交易的价格
			}
			forwardManageBo.otcForwardTradeAdd(forwardTradeModel.getTranNo3(), provider, weCcy, anaCcy, price1, direction, amount1,valueDate1,point);
			i++;
		}
		// 第四个交易对手方
		if (forwardTradeModel.getOpponentFour() == null
				|| forwardTradeModel.getOpponentFour().equals("")) {
		} else {
			String provider = forwardTradeModel.getOpponentFour();// 对手方账号
			String point = "";
			if (forwardTradeModel.getDirection().equals("1")) {
				point = forwardTradeModel.getBidFour();// 交易的价格
			} else {
				point = forwardTradeModel.getAskFour();// 交易的价格
			}
			forwardManageBo.otcForwardTradeAdd(forwardTradeModel.getTranNo4(), provider, weCcy, anaCcy, price1, direction, amount1,valueDate1,point);
			i++;
		}
		// 第五个交易对手方
		if (forwardTradeModel.getOpponentFive() == null
				|| forwardTradeModel.getOpponentFive().equals("")) {
		} else {
			String provider = forwardTradeModel.getOpponentFive();// 对手方账号
			String point = "";
			if (forwardTradeModel.getDirection().equals("1")) {
				point = forwardTradeModel.getBidFive();// 交易的价格
			} else {
				point = forwardTradeModel.getAskFive();// 交易的价格
			}
			forwardManageBo.otcForwardTradeAdd(forwardTradeModel.getTranNo5(), provider, weCcy, anaCcy, price1, direction, amount1,valueDate1,point);
			i++;
		}
		if (i > 0) {
			return SUCCESS;
		}
		return "fail";
	}

	public String forwardTradeSend() {
		// 对手方账号
		if (opponent == null || opponent.equals("")) {
			return "fail";
		} else {
			String dat = forwardManageBo.otcForwardTradeAdd(tranNo, opponent, ccy1, ccy2, price, direct, amount, valueDate, point);
			processText(dat, "text/plain;charset=GBK");
			return SUCCESS;
		}
	}

	// 跟某一个对手方交易成功
	public String forwardTradeAccept() {
		System.out.println(tranNo);
		System.out.println(tranNo1);
		System.out.println(tranNo2);
		System.out.println(tranNo3);
		System.out.println(tranNo4);
		System.out.println(tranNo5);
		
		if(tranNo1.equals(tranNo)){
			if(forwardManageBo.acceptDone(tranNo1, opponent, point)){
				// 删除没有成功交易的记录
				forwardManageBo.otcForwardTradeWithdraw(tranNo2);
				forwardManageBo.otcForwardTradeWithdraw(tranNo3);
				forwardManageBo.otcForwardTradeWithdraw(tranNo4);
				forwardManageBo.otcForwardTradeWithdraw(tranNo5);					
				return SUCCESS;
			}
		}
		else if(tranNo2.equals(tranNo)){
			if(forwardManageBo.acceptDone(tranNo2, opponent, point)){
				// 删除没有成功交易的记录
				forwardManageBo.otcForwardTradeWithdraw(tranNo1);
				forwardManageBo.otcForwardTradeWithdraw(tranNo3);
				forwardManageBo.otcForwardTradeWithdraw(tranNo4);
				forwardManageBo.otcForwardTradeWithdraw(tranNo5);					
				return SUCCESS;
			}			
		}
		else if(tranNo3.equals(tranNo)){
			if(forwardManageBo.acceptDone(tranNo3, opponent, point)){
				// 删除没有成功交易的记录
				forwardManageBo.otcForwardTradeWithdraw(tranNo2);
				forwardManageBo.otcForwardTradeWithdraw(tranNo1);
				forwardManageBo.otcForwardTradeWithdraw(tranNo4);
				forwardManageBo.otcForwardTradeWithdraw(tranNo5);					
				return SUCCESS;
			}
		}
		else if(tranNo4.equals(tranNo)){
			if(forwardManageBo.acceptDone(tranNo4, opponent, point)){
				// 删除没有成功交易的记录
				forwardManageBo.otcForwardTradeWithdraw(tranNo2);
				forwardManageBo.otcForwardTradeWithdraw(tranNo3);
				forwardManageBo.otcForwardTradeWithdraw(tranNo1);
				forwardManageBo.otcForwardTradeWithdraw(tranNo5);					
				return SUCCESS;
			}
		}
		else if(tranNo5.equals(tranNo)){
			if(forwardManageBo.acceptDone(tranNo5, opponent, point)){
				// 删除没有成功交易的记录
				forwardManageBo.otcForwardTradeWithdraw(tranNo2);
				forwardManageBo.otcForwardTradeWithdraw(tranNo3);
				forwardManageBo.otcForwardTradeWithdraw(tranNo4);
				forwardManageBo.otcForwardTradeWithdraw(tranNo1);					
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
			if(forwardManageBo.acceptDone(tranNo, opponent, price, point, amount, weCcy, anaCcy)){
				// 删除没有成功交易的记录
				forwardManageBo.otcForwardTradeDel(UserModelUtil.getUser().getUserId(),weCcy,anaCcy);
				return SUCCESS;
			}
			return "fail";
		}*/
	}
	
	public void forwardTradeCheckPoint(){
		if (opponent == null || opponent.equals("")) {
			processText("", "text/plain;charset=GBK");
		}
		else {
			String dat = forwardManageBo.otcForwardTradeCheckPoint(tranNo, opponent);
			processText(dat, "text/plain;charset=GBK");
		}
	}
	
	public void forwardTradeDel(){
		if (opponent == null || opponent.equals("")) {
			processText("", "text/plain;charset=GBK");
		} 
		else {
			String flag = forwardManageBo.otcForwardTradeDel(tranNo);
			processText(flag, "text/plain;charset=GBK");
		}
	}
	
	public void forwardTradeWithdraw(){
		if(tranNo1.equals("") || tranNo1 == null){
		}
		else{
			forwardManageBo.otcForwardTradeWithdraw(tranNo1);	
		}
		if(tranNo2.equals("") || tranNo2 == null){
		}
		else{
			forwardManageBo.otcForwardTradeWithdraw(tranNo2);
		}
		if(tranNo3.equals("") || tranNo3 == null){
		}
		else{
			forwardManageBo.otcForwardTradeWithdraw(tranNo3);
		}
		if(tranNo4.equals("") || tranNo4 == null){
		}
		else{
			forwardManageBo.otcForwardTradeWithdraw(tranNo4);
		}
		if(tranNo5.equals("") || tranNo5 == null){
		}
		else{
			forwardManageBo.otcForwardTradeWithdraw(tranNo5);
		}
		
		String dat = "1";
		processText(dat, "text/plain;charset=GBK");		
	}
	
	public ForwardManageBo getForwardManageBo() {
		return forwardManageBo;
	}

	public void setForwardManageBo(ForwardManageBo forwardManageBo) {
		this.forwardManageBo = forwardManageBo;
	}

	public ForwardTradeModel getForwardTradeModel() {
		return forwardTradeModel;
	}

	public void setForwardTradeModel(ForwardTradeModel forwardTradeModel) {
		this.forwardTradeModel = forwardTradeModel;
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

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getTranNo() {
		return tranNo;
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

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}
}
