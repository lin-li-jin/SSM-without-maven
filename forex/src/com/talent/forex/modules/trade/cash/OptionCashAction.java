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
	private String tradeNo;  //����Ȩ��������ҳ��  ��ִ�н���ʱ�������Ĳ���
	/**
	 * ��ʼ����֤����Ȩ����ҳ�棬���û��ı�֤���˻���Ϣ����ȥ
	 * @return
	 */
	public String optionCashPageInit(){
		UserModel user = (UserModel)UserModelUtil.getUser();
		List<AccInfo> accInfoListB = optionCashBo.getAccInfoListByUserNoQuery(user.getUserId(),"B");
		List<ShowValueAmountModel> valueAmountList = new ArrayList<ShowValueAmountModel>();//�����˻��������

		if(accInfoListB.size()>0){
			//�ѱ�֤���˺Ŵ���ҳ����ʾ,ҲΪ��������һ��Զ�ڽ��׵�ʱ����԰�����������ص�action��
			String accnoB = accInfoListB.get(0).getAccno();
			requestPut("accnoB", accnoB);
		}
		//�����˻����*�Ŵ����õ��˻��������Ѿ������˵��ʽ�Ŀǰֻ����Զ���Լ���Ȩ
		for (AccInfo accInfo:accInfoListB){
			double forward=forwardCashBo.queryAllDone(accInfo.getCcy(),accInfo.getUserNum());	//Զ�ڽ��׵Ŀ������
			double option=optionCashBo.queryAllDone(accInfo.getCcy(),accInfo.getUserNum());		//��Ȩ���׵Ŀ������
			double spot=spotCashBo.queryAllDone(accInfo.getCcy(),accInfo.getUserNum());			//���ڽ��׵Ŀ������
			valueAmountList.add(new ShowValueAmountModel(String.valueOf((forward+option+spot)),accInfo.getCcy()));
		}
		requestPut("valueAmountList",valueAmountList);
		requestPut("accInfoListB",accInfoListB);
		requestPut("radioValue",1);//ѡ����Ȩ���׵ı����1
		
		if(direction.equals("1")){
			requestPut("weCcy",ccy.substring(3, 6));
			requestPut("anaCcy",ccy.substring(0, 3));
		}else{
			requestPut("weCcy",ccy.substring(0, 3));
			requestPut("anaCcy",ccy.substring(3, 6));
		}
		requestPut("direction", direction);
		requestPut("price",price);
		requestPut("ccy",ccy);//����ǰ̨Ϊ���ٴδ�����
		return SUCCESS;
	}
	
	/**
	 * ����optionCash.jspҳ�洫������Ϣ����һ����֤����Ȩ���׼�¼�����ݿ���
	 * @return
	 */
	public String optionCashPageAdd(){
		optionCashBo.newOptionCashAdd(moim);
		return SUCCESS;
	}
	
	public String executeOption(){
		optionCashBo.excuteOptionModify(tradeNo);
		//�ص���ҳ�ٰѼ۸�Ż�ȥ
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