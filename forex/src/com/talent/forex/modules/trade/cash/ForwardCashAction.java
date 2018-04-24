package com.talent.forex.modules.trade.cash;

import java.util.ArrayList;
import java.util.List;

import com.talent.auth.bean.model.UserModel;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.model.MarginForwardInfoModel;
import com.talent.forex.bean.model.ShowValueAmountModel;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.util.UserModelUtil;
/**
 * Amendment No.: FOEXAS013
 * Create By    : atggdsaiDong
 * Description  : 保证金远期交易
 * Modify Date  : 2014-07-28
 */
public class ForwardCashAction extends ForexBaseAction{

	private MarginForwardInfoModel mfim ;
	private ForwardCashBo forwardCashBo;
	private OptionCashBo optionCashBo;
	private SpotCashBo spotCashBo;
	private String direction;
	private String ccy; 
	private String price;

	/**
	 * 初始化保证金远期交易页面，把用户的保证金账户信息传过去
	 * @return
	 */
	public String forwardCashPageInit(){
		UserModel user = (UserModel)UserModelUtil.getUser();
		//查询用户的保证金账户
		List<AccInfo> accInfoListB = forwardCashBo.getAccInfoListByUserNoQuery(user.getUserId(),"B");
		List<ShowValueAmountModel> valueAmountList = new ArrayList<ShowValueAmountModel>();//定义账户可用余额

		if(accInfoListB.size()>0){
			//把保证金账号传到页面显示,也为了在新增一条远期交易的时候可以把这个参数传回到action里
			String accnoB = accInfoListB.get(0).getAccno();
			requestPut("accnoB", accnoB);
		}
		//根据账户余额*放大倍数得到账户可用余额，已经交割了的资金，目前只考虑远期以及期权
		for (AccInfo accInfo:accInfoListB){
			double forward=forwardCashBo.queryAllDone(accInfo.getCcy(),accInfo.getUserNum());	//远期交易的可用余额
			double option=optionCashBo.queryAllDone(accInfo.getCcy(),accInfo.getUserNum());		//期权交易的可用余额
			double spot=spotCashBo.queryAllDone(accInfo.getCcy(),accInfo.getUserNum());			//即期交易的可用余额
			valueAmountList.add(new ShowValueAmountModel(String.valueOf((forward+option+spot)),accInfo.getCcy()));
		}
		requestPut("valueAmountList",valueAmountList);
		requestPut("accInfoListB",accInfoListB);
		requestPut("radioValue",0);//选中远期交易的编号是0
		
		if(direction.equals("1")){
			requestPut("weCcy",ccy.substring(3, 6));
			requestPut("anaCcy",ccy.substring(0, 3));
		}else{
			requestPut("weCcy",ccy.substring(0, 3));
			requestPut("anaCcy",ccy.substring(3, 6));
		}
		requestPut("direction", direction);
		requestPut("price",price);
		requestPut("ccy",ccy);//传到前台为了再次传回来
		return SUCCESS;
	}
	
	
	/**
	 * 根据forwardCash.jsp页面传来的信息增加一条保证金远期交易记录到数据库中
	 * @return
	 */
	public String forwardCashPageAdd(){
		forwardCashBo.newForwardCashAdd(mfim);
		return SUCCESS;
	}
	
	
//	/**
//	 * 这个action是在定时器轮询的时候，如果保证金远期交易的交割日期与现在系统的日期一样的话，刚执行到此action
//	 * 功能：在远期交易表把这条远期交易的交易状态改成DONE,更新账户交易统计表,流水表,账户表
//	 * @return
//	 */
//	public String executeForwardCash(){
//		//forwardCashBo.excuteForwardModify("FC00000075");
//		return SUCCESS;
//	}
	
	
	public ForwardCashBo getForwardCashBo() {
		return forwardCashBo;
	}
	public void setForwardCashBo(ForwardCashBo forwardCashBo) {
		this.forwardCashBo = forwardCashBo;
	}


	public MarginForwardInfoModel getMfim() {
		return mfim;
	}


	public void setMfim(MarginForwardInfoModel mfim) {
		this.mfim = mfim;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}




	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getCcy() {
		return ccy;
	}


	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public OptionCashBo getOptionCashBo() {
		return optionCashBo;
	}

	public void setOptionCashBo(OptionCashBo optionCashBo) {
		this.optionCashBo = optionCashBo;
	}

	public SpotCashBo getSpotCashBo() {
		return spotCashBo;
	}

	public void setSpotCashBo(SpotCashBo spotCashBo) {
		this.spotCashBo = spotCashBo;
	}
}
