package com.talent.forex.modules.trade.ask;

import java.util.ArrayList;
import java.util.List;

import com.talent.auth.bean.domain.Users;
import com.talent.auth.bean.model.UserModel;
import com.talent.forex.bean.domain.AnalogueMag;
import com.talent.forex.bean.domain.OtcSwapInfo;
import com.talent.forex.bean.model.LiborModel;
import com.talent.forex.bean.model.SwapTradeModel;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.modules.rateFactory.RateReceive;
import com.talent.forex.util.SequenceUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS006
 * Create By    : 陈整队
 * Description  : 即期询价交易
 * Modify Date  : 2014-07-24
 */
public class SwapTradeAction extends ForexBaseAction {

	private String tranNo;
	private String startTime;
	private String endTime;
	private String direct;
	private String ccy1;
	private String ccy2;
	private String price;
	private String opponent;
	private String amount;
	private String basis1;
	private String basis2;
	private String fixedType;
	private String fixedRate;
	private String libor;
	private String frequency;
	private String point;
	private String tranNo1;
	private String tranNo2;
	private String tranNo3;
	private String tranNo4;
	private String tranNo5;
	
	private SwapManageBo swapManageBo;
	private SwapTradeModel swapTradeModel;
	
	
	/**
	 * 掉期交易询价交易界面初始化
	 * @return
	 */
	public String swapTradePageInit(){
		UserModel user= UserModelUtil.getUser();
		String initGroup=user.getGroupTwoId();
		List collection = (List) swapManageBo.getOpponentGroupQuery(initGroup);
		List<Users> list=new ArrayList<Users>();
		for(int i=0;i<collection.size();i++){
			AnalogueMag analogueMag=(AnalogueMag) collection.get(i);
			list.addAll((List<Users>)(swapManageBo.getOpponentQuery(analogueMag.getAnaGroup())));
		}
		List<LiborModel> libor = (List<LiborModel>)RateReceive.getInstance().getLibor();
		for(int i=0;i<libor.size();i++){
			if(libor.get(i).getCcy().equals(ccy1)){
				requestPut("liborModel1", libor.get(i));
			}
			if(libor.get(i).getCcy().equals(ccy2)){
				requestPut("liborModel2", libor.get(i));
			}
		}
		requestPut("tradeDirection", direct);
		requestPut("ccy1", ccy1);
		requestPut("ccy2", ccy2);
		requestPut("price", price);
		requestPut("opponentList", list);
		requestPut("radioValue", 2);
		return SUCCESS;
	}
	/*//提交所有对手方的交易！
	public String swapTradeAllOpponent(){
		int i=0;//标识跟多少个对手方交易！
		String tranNo=SequenceUtil.getNextTranSeq("SP");
		String userNum=UserModelUtil.getUser().getUserId();
		//第一个交易对手方
		if(swapTradeModel.getOpponentOne()==null||swapTradeModel.getOpponentOne().equals("")){
			
		}else{
			String provider = swapTradeModel.getOpponentOne();// 对手方账号
			String point = "";//调整价格的点数
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidOne();
			} else {
				point = swapTradeModel.getAskOne();
			}
			String price = swapTradeModel.getPrice();// 交易的价格
			identicalSubmit(userNum, provider, tranNo, point, price);
			i++;
		}
		//第二个交易对手方
		if(swapTradeModel.getOpponentTwo()==null||swapTradeModel.getOpponentTwo ().equals("")){
			
		}else{
			String provider=swapTradeModel.getOpponentTwo();//对手方账号
			String point="";//调整价格的点数
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidTwo();
			} else {
				point = swapTradeModel.getAskTwo();
			}
			String price = swapTradeModel.getPrice();// 交易的价格
			identicalSubmit(userNum, provider, tranNo, point, price);
			i++;
		}
		//第三个交易对手方
		if(swapTradeModel.getOpponentThree()==null||swapTradeModel.getOpponentThree().equals("")){
			
		}else{
			String provider=swapTradeModel.getOpponentThree();//对手方账号
			String point="";//调整价格的点数
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidThree();
			} else {
				point = swapTradeModel.getAskThree();
			}
			String price = swapTradeModel.getPrice();// 交易的价格
			identicalSubmit(userNum, provider, tranNo, point, price);
			i++;
		}
		//第四个交易对手方
		if(swapTradeModel.getOpponentFour()==null||swapTradeModel.getOpponentFour().equals("")){
			
		}else{
			String provider=swapTradeModel.getOpponentFour();//对手方账号
			String point="";//调整价格的点数
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidFour();
			} else {
				point = swapTradeModel.getAskFour();
			}
			String price = swapTradeModel.getPrice();// 交易的价格
			identicalSubmit(userNum, provider, tranNo, point, price);
			i++;
		}
		//第五个交易对手方
		if(swapTradeModel.getOpponentFive()==null||swapTradeModel.getOpponentFive().equals("")){
			
		}else{
			String provider=swapTradeModel.getOpponentFive();//对手方账号
			String point="";//调整价格的点数
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidFive();
			} else {
				point = swapTradeModel.getAskFive();
			}
			String price = swapTradeModel.getPrice();//交易的价格
			identicalSubmit(userNum, provider, tranNo, point, price);
			i++;
		}
		if(i > 0){
			return SUCCESS;
		}
		return "fail";
	}*/
	
	public String swapTradeSend(){
		// 对手方账号
		if (opponent == null || opponent.equals("")) {
			return "fail";
		} else {
			String dat = swapManageBo.otcSwapTradeAdd(tranNo, startTime, endTime, direct, ccy1, ccy2, price, opponent, amount, basis1, basis2, fixedType, fixedRate, libor, frequency, point);
			processText(dat, "text/plain;charset=GBK");
			return SUCCESS;
		}
	}
	
	/*public String swapTradeSendOne(){
		//对手方账号
		String provider=swapTradeModel.getOpponentOne();
		if(provider==null || provider.equals("")){
			return "fail";
		} else {
			// 修改后的价格
			String point = "0";
			String price = swapTradeModel.getPrice();
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidOne();
			} else {
				point = swapTradeModel.getAskOne();
			}
			identicalSend(provider, point, price);
			return SUCCESS;
		}
	}
	public String swapTradeSendTwo(){
		//对手方账号
		String provider=swapTradeModel.getOpponentTwo();
		if(provider==null||provider.equals("")){
			return "fail";
		} else {
			// 修改后的价格
			String point = "";
			String price = swapTradeModel.getPrice();
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidTwo();
			} else {
				point = swapTradeModel.getAskTwo();
			}
			identicalSend(provider, point, price);
			return SUCCESS;
		}
	}
	public String swapTradeSendThree(){
		//对手方账号
		String provider=swapTradeModel.getOpponentThree();
		if(provider==null||provider.equals("")){
			return "fail";
		} else {
			// 修改后的价格
			String point = "";
			String price = swapTradeModel.getPrice();
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidThree();
			} else {
				point = swapTradeModel.getAskThree();
			}
			identicalSend(provider, point, price);
			return SUCCESS;
		}
	}
	public String swapTradeSendFour(){
		//对手方账号
		String provider=swapTradeModel.getOpponentFour();
		if(provider==null||provider.equals("")){
			return "fail";
		} else {
			// 修改后的价格
			String point = "";
			String price = swapTradeModel.getPrice();
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidFour();
			} else {
				point = swapTradeModel.getAskFour();
			}
			identicalSend(provider, point, price);
			return SUCCESS;
		}
	}
	public String swapTradeSendFive(){
		//对手方账号
		String provider=swapTradeModel.getOpponentFive();
		if(provider==null||provider.equals("")){
			return "fail";
		} else {
			// 修改后的价格
			String point = "";
			String price = swapTradeModel.getPrice();
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidFive();
			} else {
				point = swapTradeModel.getAskFive();
			}
			identicalSend(provider, point, price);
			return SUCCESS;
		}
	}*/
	
	// 跟某一个对手方交易成功
	public String swapTradeAccept(){
		if(tranNo1.equals(tranNo)){
			if(swapManageBo.acceptDone(tranNo1, opponent, point)){
				// 删除没有成功交易的记录
				swapManageBo.otcSwapTradeWithdraw(tranNo2);
				swapManageBo.otcSwapTradeWithdraw(tranNo3);
				swapManageBo.otcSwapTradeWithdraw(tranNo4);
				swapManageBo.otcSwapTradeWithdraw(tranNo5);					
				return SUCCESS;
			}
		}
		else if(tranNo2.equals(tranNo)){
			if(swapManageBo.acceptDone(tranNo2, opponent, point)){
				// 删除没有成功交易的记录
				swapManageBo.otcSwapTradeWithdraw(tranNo1);
				swapManageBo.otcSwapTradeWithdraw(tranNo3);
				swapManageBo.otcSwapTradeWithdraw(tranNo4);
				swapManageBo.otcSwapTradeWithdraw(tranNo5);					
				return SUCCESS;
			}			
		}
		else if(tranNo3.equals(tranNo)){
			if(swapManageBo.acceptDone(tranNo3, opponent, point)){
				// 删除没有成功交易的记录
				swapManageBo.otcSwapTradeWithdraw(tranNo2);
				swapManageBo.otcSwapTradeWithdraw(tranNo1);
				swapManageBo.otcSwapTradeWithdraw(tranNo4);
				swapManageBo.otcSwapTradeWithdraw(tranNo5);					
				return SUCCESS;
			}
		}
		else if(tranNo4.equals(tranNo)){
			if(swapManageBo.acceptDone(tranNo4, opponent, point)){
				// 删除没有成功交易的记录
				swapManageBo.otcSwapTradeWithdraw(tranNo2);
				swapManageBo.otcSwapTradeWithdraw(tranNo3);
				swapManageBo.otcSwapTradeWithdraw(tranNo1);
				swapManageBo.otcSwapTradeWithdraw(tranNo5);					
				return SUCCESS;
			}
		}
		else if(tranNo5.equals(tranNo)){
			if(swapManageBo.acceptDone(tranNo5, opponent, point)){
				// 删除没有成功交易的记录
				swapManageBo.otcSwapTradeWithdraw(tranNo2);
				swapManageBo.otcSwapTradeWithdraw(tranNo3);
				swapManageBo.otcSwapTradeWithdraw(tranNo4);
				swapManageBo.otcSwapTradeWithdraw(tranNo1);					
				return SUCCESS;
			}
		}
		return "fail";
	}
	
	/*//跟第一个对手方交易成功
	public String swapTradeAcceptOne(){
			String provider=swapTradeModel.getOpponentOne();//对手方账号
			if(provider==null||provider.equals("")){
				return "fail";
			}else{
				// 修改后的价格...交易的价格记得修改！(买还是卖？)
				String point = "";
				String price = swapTradeModel.getPrice();
				if (swapTradeModel.getDirection().equals("1")) {
					point = swapTradeModel.getBidOne();
				} else {
					point = swapTradeModel.getAskOne();
				}
				if(swapManageBo.acceptDone(swapTradeModel, provider, point, price)){
					return SUCCESS;
				}
				return "fail";
			}
		}
	//跟第二个对手方交易成功
	public String swapTradeAcceptTwo() {
		String provider = swapTradeModel.getOpponentTwo();// 对手方账号
		if (provider == null || provider.equals("")) {
			return "fail";
		} else {
			// 修改后的价格...交易的价格记得修改！(买还是卖？)
			String point = "";
			String price = swapTradeModel.getPrice();
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidTwo();
			} else {
				point = swapTradeModel.getAskTwo();
			}
			if (swapManageBo.acceptDone(swapTradeModel, provider, point, price)) {
				return SUCCESS;
			}
			return "fail";
		}
	}
	// 跟第三个对手方交易成功
	public String swapTradeAcceptThree() {
		String provider = swapTradeModel.getOpponentThree();// 对手方账号
		if (provider == null || provider.equals("")) {
			return "fail";
		} else {
			// 修改后的价格...交易的价格记得修改！(买还是卖？)
			String point = "";
			String price = swapTradeModel.getPrice();
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidThree();
			} else {
				point = swapTradeModel.getAskThree();
			}
			if (swapManageBo.acceptDone(swapTradeModel, provider, point, price)) {
				return SUCCESS;
			}
			return "fail";
		}
	}
	// 跟第四个对手方交易成功
	public String swapTradeAcceptFour() {
		String provider = swapTradeModel.getOpponentFour();// 对手方账号
		if (provider == null || provider.equals("")) {
			return "fail";
		} else {
			// 修改后的价格...交易的价格记得修改！(买还是卖？)
			String point = "";
			String price = swapTradeModel.getPrice();
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidFour();
			} else {
				point = swapTradeModel.getAskFour();
			}
			if (swapManageBo.acceptDone(swapTradeModel, provider, point, price)) {
				return SUCCESS;
			}
			return "fail";
		}
	}
	// 跟第五个对手方交易成功
	public String swapTradeAcceptFive() {
		String provider = swapTradeModel.getOpponentFive();// 对手方账号
		if (provider == null || provider.equals("")) {
			return "fail";
		} else {
			// 修改后的价格...交易的价格记得修改！(买还是卖？)
			String point = "";
			String price = swapTradeModel.getPrice();
			if (swapTradeModel.getDirection().equals("1")) {
				point = swapTradeModel.getBidFive();
			} else {
				point = swapTradeModel.getAskFive();
			}
			if(swapManageBo.acceptDone(swapTradeModel, provider, point, price)){
				return SUCCESS;
			}
			return "fail";
		}
	}
	//send相同部分
	public void identicalSend(String provider,String point,String price){
		String userNum=UserModelUtil.getUser().getUserId();
		String direction = swapTradeModel.getDirection();
		String weCcy = "";
		String anaCcy = "";
		if (direction.equals("0")) {
			weCcy = swapTradeModel.getWeCcy();
			anaCcy = swapTradeModel.getAnaCcy();
		} else {
			anaCcy = swapTradeModel.getWeCcy();
			weCcy = swapTradeModel.getAnaCcy();
		}
		// 取得发起方的交易记录
		OtcSwapInfo otcSwapInfo = swapManageBo.otcSwapInfoGet(tranNo, userNum);
		// 取得对手方的交易记录
		OtcSwapInfo otcSwapInfo2 = swapManageBo.otcSwapInfoGet(tranNo, provider);
		//添加新的交易
		if(otcSwapInfo==null||otcSwapInfo2==null){//首次交易则创建一条新的交易记录
			String tranNo=SequenceUtil.getNextTranSeq("SP");
			//swapManageBo.otcSwapTradeAdd(swapTradeModel, tranNo, provider, point, price);
		}else{
			//交易的价格记得修改！
			swapManageBo.otcSwapTradeUpdate(otcSwapInfo, point, "send");
			swapManageBo.otcSwapTradeUpdate(otcSwapInfo2, point, "send");
		}
	}
	//submit相同部分
	public void identicalSubmit(String userNum,String provider,String tranNo,String point,String price){
		String direction = swapTradeModel.getDirection();
		String weCcy = "";
		String anaCcy = "";
		if (direction.equals("0")) {
			weCcy = swapTradeModel.getWeCcy();
			anaCcy = swapTradeModel.getAnaCcy();
		} else {
			anaCcy = swapTradeModel.getWeCcy();
			weCcy = swapTradeModel.getAnaCcy();
		}
		// 取得发起方的交易记录
		OtcSwapInfo otcSwapInfo = swapManageBo.otcSwapInfoGet(tranNo, userNum);
		// 取得对手方的交易记录
		OtcSwapInfo otcSwapInfo2 = swapManageBo.otcSwapInfoGet(tranNo, provider);
		//判断数据库是否已经存在，如果不存在则添加新的交易
		if(otcSwapInfo==null||otcSwapInfo2==null){
			//swapManageBo.otcSwapTradeAdd(swapTradeModel, tranNo, provider,point,price);
		}else{
			//如果交易已经存在，则将其删除后再提交新的记录（交易的价格记得修改！）
			swapManageBo.otcSwapTradeDel(otcSwapInfo.getTranNo());
			swapManageBo.otcSwapTradeDel(otcSwapInfo2.getTranNo());
			//swapManageBo.otcSwapTradeAdd(swapTradeModel, tranNo, provider,point,price);
		}
	}*/
	
	public void swapTradeCheckPoint(){
		if (opponent == null || opponent.equals("")) {
			processText("", "text/plain;charset=GBK");
		}
		else {
			String dat = swapManageBo.otcSwapTradeCheckPoint(tranNo, opponent);
			processText(dat, "text/plain;charset=GBK");
		}
	}
	
	public void swapTradeDel(){
		if (opponent == null || opponent.equals("")) {
			processText("", "text/plain;charset=GBK");
		}
		else {
			String flag = swapManageBo.otcSwapTradeDel(tranNo);
			processText(flag, "text/plain;charset=GBK");
		}
	}
	
	public void swapTradeWithdraw(){
		if(tranNo1.equals("") || tranNo1 == null){
		}
		else{
			swapManageBo.otcSwapTradeWithdraw(tranNo1);	
		}
		if(tranNo2.equals("") || tranNo2 == null){
		}
		else{
			swapManageBo.otcSwapTradeWithdraw(tranNo2);
		}
		if(tranNo3.equals("") || tranNo3 == null){
		}
		else{
			swapManageBo.otcSwapTradeWithdraw(tranNo3);
		}
		if(tranNo4.equals("") || tranNo4 == null){
		}
		else{
			swapManageBo.otcSwapTradeWithdraw(tranNo4);
		}
		if(tranNo5.equals("") || tranNo5 == null){
		}
		else{
			swapManageBo.otcSwapTradeWithdraw(tranNo5);
		}
		
		String dat = "1";
		processText(dat, "text/plain;charset=GBK");
	}
	
	public SwapManageBo getSwapManageBo() {
		return swapManageBo;
	}
	public void setSwapManageBo(SwapManageBo swapManageBo) {
		this.swapManageBo = swapManageBo;
	}
	public SwapTradeModel getSwapTradeModel() {
		return swapTradeModel;
	}
	public void setSwapTradeModel(SwapTradeModel swapTradeModel) {
		this.swapTradeModel = swapTradeModel;
	}
	public String getTranNo() {
		return tranNo;
	}
	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
	public String getBasis1() {
		return basis1;
	}
	public void setBasis1(String basis1) {
		this.basis1 = basis1;
	}
	public String getBasis2() {
		return basis2;
	}
	public void setBasis2(String basis2) {
		this.basis2 = basis2;
	}
	public String getFixedType() {
		return fixedType;
	}
	public void setFixedType(String fixedType) {
		this.fixedType = fixedType;
	}
	public String getFixedRate() {
		return fixedRate;
	}
	public void setFixedRate(String fixedRate) {
		this.fixedRate = fixedRate;
	}
	public String getLibor() {
		return libor;
	}
	public void setLibor(String libor) {
		this.libor = libor;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
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
