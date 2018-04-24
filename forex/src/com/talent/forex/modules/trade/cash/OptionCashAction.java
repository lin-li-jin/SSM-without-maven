package com.talent.forex.modules.trade.cash;

import java.util.ArrayList;
import java.util.List;

import com.talent.auth.bean.model.UserModel;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.model.CcyModel;
import com.talent.forex.bean.model.MarginForwardInfoModel;
import com.talent.forex.bean.model.MarginOptionInfoModel;
import com.talent.forex.bean.model.ShowValueAmountModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.util.CodeTableUtil;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetRateUtil;
import com.talent.forex.util.UserModelUtil;


public class OptionCashAction extends ForexBaseAction{
	
	private OptionCashBo optionCashBo ;
	private ForwardCashBo forwardCashBo;
	private SpotCashBo spotCashBo;
	MarginOptionInfoModel moim ;
	private String direction;
	private String ccy; 
	private String price;
	private String tradeNo;  //在期权交易详情页面  点执行交易时传回来的参数
	/**
	 * 初始化保证金期权交易页面，把用户的保证金账户信息传过去
	 * @return
	 */
	public String optionCashPageInit(){
		UserModel user = (UserModel)UserModelUtil.getUser();
		List<AccInfo> accInfoListB = optionCashBo.getAccInfoListByUserNoQuery(user.getUserId(),"B");
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
		requestPut("radioValue",1);//选中期权交易的编号是1
		
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
	 * 根据optionCash.jsp页面传来的信息增加一条保证金期权交易记录到数据库中
	 * @return
	 */
	public String optionCashPageAdd(){
		optionCashBo.newOptionCashAdd(moim);
		return SUCCESS;
	}
	
	public String executeOption(){
		optionCashBo.excuteOptionModify(tradeNo);
		//回到首页再把价格放回去
		@SuppressWarnings("unchecked")
		List<CcyModel> cashCollection = (List<CcyModel>) GetRateUtil.getInstance().getMarginRate();
		for (int i=0; i<cashCollection.size(); i++){
			requestPut(cashCollection.get(i).getCcy(),cashCollection.get(i));
		}
		requestPut("tradeStatusList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.CASH_TRADE_STATUS_LIST));
		requestPut("tradeTypeList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.TRADE_TYPE_CASH_LIST));
		return SUCCESS;
	}
	


	public OptionCashBo getOptionCashBo() {
		return optionCashBo;
	}

	public void setOptionCashBo(OptionCashBo optionCashBo) {
		this.optionCashBo = optionCashBo;
	}

	public MarginOptionInfoModel getMoim() {
		return moim;
	}

	public void setMoim(MarginOptionInfoModel moim) {
		this.moim = moim;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public ForwardCashBo getForwardCashBo() {
		return forwardCashBo;
	}

	public void setForwardCashBo(ForwardCashBo forwardCashBo) {
		this.forwardCashBo = forwardCashBo;
	}

	public SpotCashBo getSpotCashBo() {
		return spotCashBo;
	}

	public void setSpotCashBo(SpotCashBo spotCashBo) {
		this.spotCashBo = spotCashBo;
	}
}
