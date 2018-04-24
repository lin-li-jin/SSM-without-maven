package com.talent.forex.bean.model;

public class SwapTradeModel {
	
	private String opponentOne;//交易的第一个对手方
	
	private String opponentTwo;//交易的第二个对手方
	
	private String opponentThree;//交易的第三个对手方
	
	private String opponentFour;//交易的第四个对手方
	
	private String opponentFive;//交易的第五个对手方
	
	private String cAmount;//近端交易的金额
	
	private String fAmount;//远端交易的金额
	
	private String anaCcy;//对方交易的币种
	
	private String weCcy;//本方交易的币种
	
	private String bidOne;//交易的价格
	
	private String bidTwo;//交易的价格
	
	private String bidThree;//交易的价格
	
	private String bidFour;//交易的价格
	
	private String bidFive;//交易的价格
	
	private String askOne;//交易的价格
	
	private String askTwo;//交易的价格
	
	private String askThree;//交易的价格
	
	private String askFour;//交易的价格
	
	private String askFive;//交易的价格
	
	private String tranType;//交易的类型（Sell或者Buy）
	
	private String startDate;//开始交易的日期
	
	private String endDate;//到期交易的日期
	
	private String fixedType;//固定利率类型（receive或pay）
	
	private String fixedRate;//近端利率
	
	private String floatingRate;//远端利率
	
	private String cBasis;//近端掉期点
	
	private String fBasis;//远端掉期点
	
	private String frequency;//近端付息类型
	
	private String libor;//利率的类型
	
	private String price;//页面显示的价格，用来计算交易的价格！
	
	private String direction;//交易的方向
	
	private String tranNo1;
	
	private String tranNo2;
	
	private String tranNo3;
	
	private String tranNo4;
	
	private String tranNo5;

	public String getOpponentOne() {
		return opponentOne;
	}

	public void setOpponentOne(String opponentOne) {
		this.opponentOne = opponentOne;
	}

	public String getOpponentTwo() {
		return opponentTwo;
	}

	public void setOpponentTwo(String opponentTwo) {
		this.opponentTwo = opponentTwo;
	}

	public String getOpponentThree() {
		return opponentThree;
	}

	public void setOpponentThree(String opponentThree) {
		this.opponentThree = opponentThree;
	}

	public String getOpponentFour() {
		return opponentFour;
	}

	public void setOpponentFour(String opponentFour) {
		this.opponentFour = opponentFour;
	}

	public String getOpponentFive() {
		return opponentFive;
	}

	public void setOpponentFive(String opponentFive) {
		this.opponentFive = opponentFive;
	}

	public String getcAmount() {
		return cAmount;
	}

	public void setcAmount(String cAmount) {
		this.cAmount = cAmount;
	}

	public String getfAmount() {
		return fAmount;
	}

	public void setfAmount(String fAmount) {
		this.fAmount = fAmount;
	}

	public String getAnaCcy() {
		return anaCcy;
	}

	public void setAnaCcy(String anaCcy) {
		this.anaCcy = anaCcy;
	}

	public String getWeCcy() {
		return weCcy;
	}

	public void setWeCcy(String weCcy) {
		this.weCcy = weCcy;
	}

	public String getBidOne() {
		return bidOne;
	}

	public void setBidOne(String bidOne) {
		this.bidOne = bidOne;
	}

	public String getBidTwo() {
		return bidTwo;
	}

	public void setBidTwo(String bidTwo) {
		this.bidTwo = bidTwo;
	}

	public String getBidThree() {
		return bidThree;
	}

	public void setBidThree(String bidThree) {
		this.bidThree = bidThree;
	}

	public String getBidFour() {
		return bidFour;
	}

	public void setBidFour(String bidFour) {
		this.bidFour = bidFour;
	}

	public String getBidFive() {
		return bidFive;
	}

	public void setBidFive(String bidFive) {
		this.bidFive = bidFive;
	}

	public String getAskOne() {
		return askOne;
	}

	public void setAskOne(String askOne) {
		this.askOne = askOne;
	}

	public String getAskTwo() {
		return askTwo;
	}

	public void setAskTwo(String askTwo) {
		this.askTwo = askTwo;
	}

	public String getAskThree() {
		return askThree;
	}

	public void setAskThree(String askThree) {
		this.askThree = askThree;
	}

	public String getAskFour() {
		return askFour;
	}

	public void setAskFour(String askFour) {
		this.askFour = askFour;
	}

	public String getAskFive() {
		return askFive;
	}

	public void setAskFive(String askFive) {
		this.askFive = askFive;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public String getFloatingRate() {
		return floatingRate;
	}

	public void setFloatingRate(String floatingRate) {
		this.floatingRate = floatingRate;
	}

	public String getcBasis() {
		return cBasis;
	}

	public void setcBasis(String cBasis) {
		this.cBasis = cBasis;
	}

	public String getfBasis() {
		return fBasis;
	}

	public void setfBasis(String fBasis) {
		this.fBasis = fBasis;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getLibor() {
		return libor;
	}

	public void setLibor(String libor) {
		this.libor = libor;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
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