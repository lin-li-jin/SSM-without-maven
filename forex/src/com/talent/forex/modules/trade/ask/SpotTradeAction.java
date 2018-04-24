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
 * Create By    : ������
 * Description  : ����ѯ�۽���
 * Modify Date  : 2014-07-24
 */
public class SpotTradeAction extends ForexBaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ccy1;//���׵ı���
	private String ccy2;//���׵ı���
	private String price;//���׵ļ۸�
	private String direct;//���׷���
	private String amount;//���׵Ľ��
	private String opponent;//���ַ�
	private String tranNo;//���ױ��
	private String tranNo1;
	private String tranNo2;
	private String tranNo3;
	private String tranNo4;
	private String tranNo5;

	private SpotManageBo spotManageBo;

	private SpotTradeModel spotTradeModel;

	/**
	 * ҳ���ʼ��
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
	 * �ύ���ж��ַ��Ľ��ף�����ͣ�ã���
	 * @return
	 */
	public String spotTradeAllOpponent() {
		int i = 0;// ��ʶ�����ٸ����ַ����ף�
		String weCcy=spotTradeModel.getWeCcy();
		String anaCcy=spotTradeModel.getAnaCcy();
		String amount=spotTradeModel.getAmount();
		String direction=spotTradeModel.getDirection();
		// ��һ�����׶��ַ�
		if (spotTradeModel.getOpponentOne() == null	|| spotTradeModel.getOpponentOne().equals("")) {
			
		} else {
			String provider = spotTradeModel.getOpponentOne();// ���ַ��˺�
			String price = "";// ���׵ļ۸�
			if (direction.equals("1")) {//������׷���Ϊ��
				price = spotTradeModel.getBidOne();
			} else {
				price = spotTradeModel.getAskOne();
			}
			spotManageBo.otcSpotTradeAdd(spotTradeModel.getTranNo1(), provider, weCcy, anaCcy, price, direction, amount);
			i++;
		}
		// �ڶ������׶��ַ�
		if (spotTradeModel.getOpponentTwo() == null	|| spotTradeModel.getOpponentTwo().equals("")) {
			
		} else {
			String provider = spotTradeModel.getOpponentTwo();// ���ַ��˺�
			String price = "";// ���׵ļ۸�
			if (direction.equals("1")) {//������׷���Ϊ��
				price = spotTradeModel.getBidTwo();
			} else {
				price = spotTradeModel.getAskTwo();
			}
			spotManageBo.otcSpotTradeAdd(spotTradeModel.getTranNo2(), provider, weCcy, anaCcy, price, direction, amount);
			i++;
		}
		// ���������׶��ַ�
		if (spotTradeModel.getOpponentThree() == null || spotTradeModel.getOpponentThree().equals("")) {
		} else {
			String provider = spotTradeModel.getOpponentThree();// ���ַ��˺�
			String price = "";// ���׵ļ۸�
			if (direction.equals("1")) {//������׷���Ϊ��
				price = spotTradeModel.getBidThree();
			} else {
				price = spotTradeModel.getAskThree();
			}
			spotManageBo.otcSpotTradeAdd(spotTradeModel.getTranNo3(), provider, weCcy, anaCcy, price, direction, amount);
			i++;
		}
		// ���ĸ����׶��ַ�
		if (spotTradeModel.getOpponentFour() == null || spotTradeModel.getOpponentFour().equals("")) {
		} else {
			String provider = spotTradeModel.getOpponentFour();// ���ַ��˺�
			String price = "";// ���׵ļ۸�
			if (direction.equals("1")) {//������׷���Ϊ��
				price = spotTradeModel.getBidFour();
			} else {
				price = spotTradeModel.getAskFour();
			}
			spotManageBo.otcSpotTradeAdd(spotTradeModel.getTranNo4(), provider, weCcy, anaCcy, price, direction, amount);
			i++;
		}
		// ��������׶��ַ�
		if (spotTradeModel.getOpponentFive() == null || spotTradeModel.getOpponentFive().equals("")) {
		} else {
			String provider = spotTradeModel.getOpponentFive();// ���ַ��˺�
			String price = "";// ���׵ļ۸�
			if (direction.equals("1")) {//������׷���Ϊ��
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
	 * ���send�����������¼�
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
	 * ��ĳһ�����ַ����׳ɹ������ܼ۸�
	 * @return
	 */
	public String spotTradeAccept() {
		if(tranNo1.equals(tranNo)){
			if(spotManageBo.acceptDone(tranNo1, opponent, price)){
				// ɾ��û�гɹ����׵ļ�¼
				spotManageBo.otcSpotTradeWithdraw(tranNo2);
				spotManageBo.otcSpotTradeWithdraw(tranNo3);
				spotManageBo.otcSpotTradeWithdraw(tranNo4);
				spotManageBo.otcSpotTradeWithdraw(tranNo5);
				return SUCCESS;
			}
		}
		else if(tranNo2.equals(tranNo)){
			if(spotManageBo.acceptDone(tranNo2, opponent, price)){
				// ɾ��û�гɹ����׵ļ�¼
				spotManageBo.otcSpotTradeWithdraw(tranNo1);
				spotManageBo.otcSpotTradeWithdraw(tranNo3);
				spotManageBo.otcSpotTradeWithdraw(tranNo4);
				spotManageBo.otcSpotTradeWithdraw(tranNo5);
				return SUCCESS;
			}			
		}
		else if(tranNo3.equals(tranNo)){
			if(spotManageBo.acceptDone(tranNo3, opponent, price)){
				// ɾ��û�гɹ����׵ļ�¼
				spotManageBo.otcSpotTradeWithdraw(tranNo2);
				spotManageBo.otcSpotTradeWithdraw(tranNo1);
				spotManageBo.otcSpotTradeWithdraw(tranNo4);
				spotManageBo.otcSpotTradeWithdraw(tranNo5);
				return SUCCESS;
			}
		}
		else if(tranNo4.equals(tranNo)){
			if(spotManageBo.acceptDone(tranNo4, opponent, price)){
				// ɾ��û�гɹ����׵ļ�¼
				spotManageBo.otcSpotTradeWithdraw(tranNo2);
				spotManageBo.otcSpotTradeWithdraw(tranNo3);
				spotManageBo.otcSpotTradeWithdraw(tranNo1);
				spotManageBo.otcSpotTradeWithdraw(tranNo5);
				return SUCCESS;
			}
		}
		else if(tranNo5.equals(tranNo)){
			if(spotManageBo.acceptDone(tranNo5, opponent, price)){
				// ɾ��û�гɹ����׵ļ�¼
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
				// ɾ��û�гɹ����׵ļ�¼
				spotManageBo.otcSpotTradeDel(tranNo, UserModelUtil.getUser().getUserId(),weCcy,anaCcy);
				return SUCCESS;
			}
			return "fail";
		}*/
	}
	
	/**
	 * ��ѯtable�۸�仯
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
	 * ɾ�����׼�¼�������ڶ��ַ���ֹ����ʱ��ʹ�ã�
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
	 * ����withdraw�������������¼�
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
