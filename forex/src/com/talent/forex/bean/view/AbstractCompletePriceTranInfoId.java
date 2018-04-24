package com.talent.forex.bean.view;

/**
 * AbstractCompletePriceTranInfoId entity provides the base persistence
 * definition of the CompletePriceTranInfoId entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractCompletePriceTranInfoId implements
		java.io.Serializable {

	// Fields

	private String tranType;
	private String tranNo;
	private String userNum;
	private String weCcy;
	private String anaCcy;
	private String amount;
	private String price;
	private String date;
	private String status;
	private String createDatetime;
	private String type;
	private String providor;

	// Constructors

	/** default constructor */
	public AbstractCompletePriceTranInfoId() {
	}

	/** minimal constructor */
	public AbstractCompletePriceTranInfoId(String tranType, String tranNo,
			String userNum, String weCcy, String anaCcy, String status,
			String createDatetime, String type, String providor) {
		this.tranType = tranType;
		this.tranNo = tranNo;
		this.userNum = userNum;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.status = status;
		this.createDatetime = createDatetime;
		this.type = type;
		this.providor = providor;
	}

	/** full constructor */
	public AbstractCompletePriceTranInfoId(String tranType, String tranNo,
			String userNum, String weCcy, String anaCcy, String amount,
			String price, String date, String status, String createDatetime,
			String type, String providor) {
		this.tranType = tranType;
		this.tranNo = tranNo;
		this.userNum = userNum;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.amount = amount;
		this.price = price;
		this.date = date;
		this.status = status;
		this.createDatetime = createDatetime;
		this.type = type;
		this.providor = providor;
	}

	// Property accessors

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

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(String createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProvidor() {
		return this.providor;
	}

	public void setProvidor(String providor) {
		this.providor = providor;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractCompletePriceTranInfoId))
			return false;
		AbstractCompletePriceTranInfoId castOther = (AbstractCompletePriceTranInfoId) other;

		return ((this.getTranType() == castOther.getTranType()) || (this
				.getTranType() != null && castOther.getTranType() != null && this
				.getTranType().equals(castOther.getTranType())))
				&& ((this.getTranNo() == castOther.getTranNo()) || (this
						.getTranNo() != null && castOther.getTranNo() != null && this
						.getTranNo().equals(castOther.getTranNo())))
				&& ((this.getUserNum() == castOther.getUserNum()) || (this
						.getUserNum() != null && castOther.getUserNum() != null && this
						.getUserNum().equals(castOther.getUserNum())))
				&& ((this.getWeCcy() == castOther.getWeCcy()) || (this
						.getWeCcy() != null && castOther.getWeCcy() != null && this
						.getWeCcy().equals(castOther.getWeCcy())))
				&& ((this.getAnaCcy() == castOther.getAnaCcy()) || (this
						.getAnaCcy() != null && castOther.getAnaCcy() != null && this
						.getAnaCcy().equals(castOther.getAnaCcy())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null && castOther.getAmount() != null && this
						.getAmount().equals(castOther.getAmount())))
				&& ((this.getPrice() == castOther.getPrice()) || (this
						.getPrice() != null && castOther.getPrice() != null && this
						.getPrice().equals(castOther.getPrice())))
				&& ((this.getDate() == castOther.getDate()) || (this.getDate() != null
						&& castOther.getDate() != null && this.getDate()
						.equals(castOther.getDate())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getCreateDatetime() == castOther.getCreateDatetime()) || (this
						.getCreateDatetime() != null
						&& castOther.getCreateDatetime() != null && this
						.getCreateDatetime().equals(
								castOther.getCreateDatetime())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())))
				&& ((this.getProvidor() == castOther.getProvidor()) || (this
						.getProvidor() != null
						&& castOther.getProvidor() != null && this
						.getProvidor().equals(castOther.getProvidor())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTranType() == null ? 0 : this.getTranType().hashCode());
		result = 37 * result
				+ (getTranNo() == null ? 0 : this.getTranNo().hashCode());
		result = 37 * result
				+ (getUserNum() == null ? 0 : this.getUserNum().hashCode());
		result = 37 * result
				+ (getWeCcy() == null ? 0 : this.getWeCcy().hashCode());
		result = 37 * result
				+ (getAnaCcy() == null ? 0 : this.getAnaCcy().hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37 * result
				+ (getPrice() == null ? 0 : this.getPrice().hashCode());
		result = 37 * result
				+ (getDate() == null ? 0 : this.getDate().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37
				* result
				+ (getCreateDatetime() == null ? 0 : this.getCreateDatetime()
						.hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result
				+ (getProvidor() == null ? 0 : this.getProvidor().hashCode());
		return result;
	}

}