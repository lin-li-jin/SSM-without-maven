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
 * Description  : ��֤��Զ�ڽ���
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
	 * ��ʼ����֤��Զ�ڽ���ҳ�棬���û��ı�֤���˻���Ϣ����ȥ
	 * @return
	 */
	public String forwardCashPageInit(){
		UserModel user = (UserModel)UserModelUtil.getUser();
		//��ѯ�û��ı�֤���˻�
		List<AccInfo> accInfoListB = forwardCashBo.getAccInfoListByUserNoQuery(user.getUserId(),"B");
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
		requestPut("radioValue",0);//ѡ��Զ�ڽ��׵ı����0
		
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
	 * ����forwardCash.jspҳ�洫������Ϣ����һ����֤��Զ�ڽ��׼�¼�����ݿ���
	 * @return
	 */
	public String forwardCashPageAdd(){
		forwardCashBo.newForwardCashAdd(mfim);
		return SUCCESS;
	}
	
	
//	/**
//	 * ���action���ڶ�ʱ����ѯ��ʱ�������֤��Զ�ڽ��׵Ľ�������������ϵͳ������һ���Ļ�����ִ�е���action
//	 * ���ܣ���Զ�ڽ��ױ������Զ�ڽ��׵Ľ���״̬�ĳ�DONE,�����˻�����ͳ�Ʊ�,��ˮ��,�˻���
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
