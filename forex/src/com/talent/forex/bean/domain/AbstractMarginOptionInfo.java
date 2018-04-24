package com.talent.forex.bean.domain;

/**
 * AbstractMarginOptionInfo entity provides the base persistence definition of
 * the MarginOptionInfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMarginOptionInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tranType;
	private String tranNo;
	private String userNum;
	private String accNo;
	private String valueDate;
	private String weCcy;
	private String anaCcy;
	private String optionType;
	private String accAmount;
	private String accBanlance;
	private String maturity;
	private String dealAmt;
	private String price;
	private String premium;
	private String statue;
	private String createDatetime;
	private String LAmount;
	private String direction;

	private String statusDescr;
	private String dirDescr;
	// Constructors

	/** default constructor */
	public AbstractMarginOptionInfo() {
	}

	/** minimal constructor */
	public AbstractMarginOptionInfo(String tranType, String tranNo,
			String userNum, String accNo, String weCcy, String anaCcy,
			String optionType, String accAmount, String maturity,
			String dealAmt, String price, String premium, String statue,
			String createDatetime, String direction) {
		this.tranType = tranType;
		this.tranNo = tranNo;
		this.userNum = userNum;
		this.accNo = accNo;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.optionType = optionType;
		this.accAmount = accAmount;
		this.maturity = maturity;
		this.dealAmt = dealAmt;
		this.price = price;
		this.premium = premium;
		this.statue = statue;
		this.createDatetime = createDatetime;
		this.direction = direction;
	}

	/** full constructor */
	public AbstractMarginOptionInfo(String tranType, String tranNo,
			String userNum, String accNo, String valueDate, String weCcy,
			String anaCcy, String optionType, String accAmount,
			String accBanlance, String maturity, String dealAmt, String price,
			String premium, String statue, String createDatetime,
			String LAmount, String direction) {
		this.tranType = tranType;
		this.tranNo = tranNo;
		this.userNum = userNum;
		this.accNo = accNo;
		this.valueDate = valueDate;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.optionType = optionType;
		this.accAmount = accAmount;
		this.accBanlance = accBanlance;
		this.maturity = maturity;
		this.dealAmt = dealAmt;
		this.price = price;
		this.premium = premium;
		this.statue = statue;
		this.createDatetime = createDatetime;
		this.LAmount = LAmount;
		this.direction = direction;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTranType() {
		return this.tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getTranNo() {
		return this.tranNo;
	}

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}

	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getAccNo() {
		return this.accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getValueDate() {
		return this.valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getWeCcy() {
		return this.weCcy;
	}

	public void setWeCcy(String weCcy) {
		this.weCcy = weCcy;
	}

	public String getAnaCcy() {
		return this.anaCcy;
	}

	public void setAnaCcy(String anaCcy) {
		this.anaCcy = anaCcy;
	}

	public String getOptionType() {
		return this.optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public String getAccAmount() {
		return this.accAmount;
	}

	public void setAccAmount(String accAmount) {
		this.accAmount = accAmount;
	}

	public String getAccBanlance() {
		return this.accBanlance;
	}

	public void setAccBanlance(String accBanlance) {
		this.accBanlance = accBanlance;
	}

	public String getMaturity() {
		return this.maturity;
	}

	public void setMaturity(String maturity) {
		this.maturity = maturity;
	}

	public String getDealAmt() {
		return this.dealAmt;
	}

	public void setDealAmt(String dealAmt) {
		this.dealAmt = dealAmt;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPremium() {
		return this.premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getStatue() {
		return this.statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(String createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getLAmount() {
		return this.LAmount;
	}

	public void setLAmount(String LAmount) {
		this.LAmount = LAmount;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getStatusDescr() {
		if(statue.equals("A")){
			return "有效";
		}else if(statue.equals("D")){
			return "完成";
		}else if(statue.equals("I")){
			return "无效";
		}else if(statue.equals("P")){
			return "处理中";
		}else {
			return "取消";
		}
	}

	public void setStatusDescr(String statusDescr) {
		this.statusDescr = statusDescr;
	}

	public String getDirDescr() {
		if(direction.equals("1")){
			return "买";
		}else{
			return "卖";
		}
	}

	public void setDirDescr(String dirDescr) {
		this.dirDescr = dirDescr;
	}
	


}