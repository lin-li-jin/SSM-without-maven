package com.talent.forex.modules.trade.ask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.talent.auth.bean.domain.Users;
import com.talent.auth.bean.model.UserModel;
import com.talent.auth.dao.UsersDao;
import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.AnalogueMag;
import com.talent.forex.bean.domain.BTranFlowMapping;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.OtcForwardInfo;
import com.talent.forex.bean.domain.OtcSpotInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.bean.model.SpotTradeModel;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.AnalogueMagDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.OtcSpotDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.forex.util.CalculateUtil;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.SequenceUtil;
import com.talent.forex.util.SpotUtil;
import com.talent.forex.util.UserModelUtil;
import com.talent.hibernate.base.DaoException;
/*
 * Amendment No.: FOEXAS006
 * Create By    : ������
 * Description  : ����ѯ�۽���
 * Modify Date  : 2014-07-24
 */
public class SpotManageBo extends BaseBo {
	
	private AnalogueMagDao analogueMagDao;
	
	private UsersDao userDao;
	
	private OtcSpotDao otcSpotDao;
	
	private AccInfoDao accInfoDao;
	
	private CTranFlowMappingDao cTranFlowMappingDao;
	private WTranFlowMappingDao wTranFlowMappingDao;

	/**
	 * ͨ���û����������ȡ�����ж��ַ������(����д�ģ���ʱ�����ò��ϣ������ǲ�������)
	 * @return
	 */
	public Collection getOpponentGroupQuery(){
		String initGroup = UserModelUtil.getUser().getGroupTwoId();
		AnalogueMag analogueMag=new AnalogueMag();
		analogueMag.setInitGroup(initGroup);
		return analogueMagDao.getBeansByBean(analogueMag,MatchMode.ANYWHERE);
	}
	
	/**
	 * ͨ�����ȡ��ͬһ��ŵĶ��ַ�(����д�ģ���ʱ�����ò��ϣ������ǲ�������)
	 * @param groupTwoId
	 * @return
	 */
	public Collection getOpponentQuery(String groupTwoId){
		Users user=new Users();
		user.setGroupTwo(groupTwoId);
		return analogueMagDao.getBeansByBean(user,MatchMode.EXACT);
	}
	
	/**
	 * �״η������������µĽ��׼�¼�������׼�¼��������½��׼�¼
	 * @param tranNo ���׼�¼
	 * @param opponent ���ַ�
	 * @param ccy1  ǰ̨I sell ccy1 vs ccy2
	 * @param ccy2  ǰ̨I sell ccy1 vs ccy2
	 * @param price ��������ļ۸�
	 * @param direct ���׷���1(buy)/0(sell)
	 * @param amount ���׽��
	 * @return tranNo �����������׼�¼�����Ǹ��½��׼�¼����Ҫ���ؽ��׼�¼
	 */
	public String otcSpotTradeAdd(String tranNo, String opponent, String ccy1, String ccy2, String price, String direct, String amount){
		try {			
			String userNum = UserModelUtil.getUser().getUserId();
			// ����µĽ���
			if (tranNo.equals("") || tranNo == null) {//�״η������������µĽ��׼�¼				
				tranNo = SequenceUtil.getNextTranSeq("ST");//�����µĽ��ױ��
				//���𷽵Ľ��׼�¼
				OtcSpotInfo otcSpotInfo = new OtcSpotInfo();
				AccInfo accInfo = new AccInfo();
				String weCcy = "";
				String anaCcy = "";
				if (direct.equals("0")) {
					weCcy = ccy1;
					anaCcy = ccy2;
				} else {
					anaCcy = ccy1;
					weCcy = ccy2;
				}
				if(weCcy.equals("CNY")||anaCcy.equals("CNY")){
					otcSpotInfo.setTranType("C");//����ҽ���
					accInfo.setAccType("C");
				}else {
					otcSpotInfo.setTranType("W");//��ҶԽ���
					accInfo.setAccType("W");
				}
				accInfo.setUserNum(userNum);
				accInfo.setCcy(weCcy);
				Double needAmount = 0.0d;//���н�������Ҫ�Ľ�
				//�������ı��ִ��뱾����������ı��ִ�����ַ�
				if(direct.equals("1")){//���׷���Ϊ��
					needAmount = Double.parseDouble(CalculateUtil.getAmountAfterTrade(amount, price));
				}else {
					needAmount = Double.parseDouble(amount);
				}
				//�жϱ��������ı�����Ӧ������Ƿ��㹻��
				AccInfo accInfo2=accInfoDao.getBeanByBean(accInfo, MatchMode.EXACT);
				if (needAmount > Double.parseDouble(accInfo2.getAmount())){
					logger.error("�û���" + userNum + " ��Ӽ���ѯ�۽���ʧ�ܣ��˻�"+ accInfo.getCcy() +"����");
//					BoException be = new BoException("otcSpotTradeAdd");
//					if (accInfo.getAccType().equals("C"))
//						be.setExceptionType("������˻�"+ accInfo.getCcy() +"����,����ѯ�۽������ύʧ�ܣ�");
//					else
//						be.setExceptionType("����˻�"+ accInfo.getCcy() +"����,����ѯ�۽����ύʧ�ܣ�");
//					throw be;
					return "fail";
				}
				otcSpotInfo.setTranNo(tranNo);//���ױ��
				otcSpotInfo.setUserNum(userNum);
				otcSpotInfo.setProvider(opponent);
				otcSpotInfo.setWeCcy(weCcy);
				otcSpotInfo.setAnaCcy(anaCcy);
				otcSpotInfo.setAmount(FormatParamUtil.getAmountAndPriceFmt(amount));
				otcSpotInfo.setCreateDatetime(GetDateTimeUtil.getCurrentDateTimeToMinute());
				otcSpotInfo.setPrice(price);
				otcSpotInfo.setIsInit("1");
				otcSpotInfo.setStatue("P");
				otcSpotInfo.setDirection(direct);
				otcSpotInfo.setTimes("0");
				
				otcSpotDao.addBean(otcSpotInfo);
				OtcSpotInfo bean = otcSpotDao.getBeanByBean(otcSpotInfo, MatchMode.EXACT);
				SpotUtil.getInstance().spotAdd(bean);
				//���ַ��Ľ��׼�¼
				OtcSpotInfo otcSpotInfo2 = new OtcSpotInfo();
				otcSpotInfo2.setTranType(otcSpotInfo.getTranType());//����ҽ���
				otcSpotInfo2.setTranNo(tranNo);//���ױ��
				otcSpotInfo2.setUserNum(opponent);
				otcSpotInfo2.setProvider(userNum);
				otcSpotInfo2.setAmount(FormatParamUtil.getAmountAndPriceFmt(amount));
				otcSpotInfo2.setWeCcy(anaCcy);
				otcSpotInfo2.setAnaCcy(weCcy);
				otcSpotInfo2.setCreateDatetime(GetDateTimeUtil.getCurrentDateTimeToMinute());
				otcSpotInfo2.setPrice(price);
				otcSpotInfo2.setIsInit("0");
				otcSpotInfo2.setStatue("R");
				otcSpotInfo2.setTimes("0");
				if(direct.equals("1")){
					otcSpotInfo2.setDirection("0");
				}else{
					otcSpotInfo2.setDirection("1");
				}
				
				otcSpotDao.addBean(otcSpotInfo2);
				OtcSpotInfo bean2 = otcSpotDao.getBeanByBean(otcSpotInfo2, MatchMode.EXACT);
				SpotUtil.getInstance().spotAdd(bean2);
			}
			else {
				otcSpotTradeUpdate(tranNo, userNum, price, "send");
			}
			return tranNo;
		} catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("����ѯ�۽��׵���Ϣû�гɹ����͸����ַ����û���" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSpotTradeAdd");
			be.setExceptionType("����ѯ�۽��׵���Ϣû�гɹ����͸����ַ���");
			throw be;
		}
	}
	
	//ͨ�淢���˺źͶ��ַ��˺�ȡ�ý��ױ�ţ�
//	public OtcSpotInfo otcSpotInfoGet(String tranNo, String userNum){
//		OtcSpotInfo bean = new OtcSpotInfo();
//		bean.setTranNo(tranNo);//tranNo�������Ѿ����ڵģ�
//		bean.setUserNum(userNum);
//		OtcSpotInfo otcSpotInfo = otcSpotDao.getBeanByBean(bean, MatchMode.EXACT);
//		return otcSpotInfo;
//		/*otcSpotInfo.setProvider(provider);
//		otcSpotInfo.setWeCcy(weCcy);
//		otcSpotInfo.setAnaCcy(anaCcy);*/
//		/*Collection collection = otcSpotDao.getBeansByBean(otcSpotInfo, MatchMode.ANYWHERE);
//		Iterator it=collection.iterator();
//		//��ý���״̬����DONE�ļ�¼
//		while(it.hasNext()){
//			OtcSpotInfo otcSpotInfo2=(OtcSpotInfo) it.next();
//			if(otcSpotInfo2.getStatue().equals("P")||otcSpotInfo2.getStatue().equals("R")){
//				return otcSpotInfo2;
//			}
//		}
//		return null;*/
//	}
	
	/**
	 * ���½�����Ϣ��
	 * @param tranNo ���׼�¼
	 * @param userNum �û��ʺţ���1115442
	 * @param price �����޸ĺ�ļ۸�
	 * @param type send(���ͣ�����δ����)/accept(���ܣ����׽�������)
	 */
	public void otcSpotTradeUpdate(String tranNo, String userNum, String price, String type){
		try {
			if(type.equals("send")){
				//�޸ķ��𷽵�״̬
//				OtcSpotInfo bean = new OtcSpotInfo();
//				bean.setTranNo(tranNo);
//				bean.setUserNum(userNum);
				//OtcSpotInfo otcSpotInfo = otcSpotDao.getBeanByBean(bean, MatchMode.EXACT);
				OtcSpotInfo otcSpotInfo = SpotUtil.getInstance().getSpotByParams(tranNo, userNum);
				otcSpotInfo.setPrice(price);
				otcSpotInfo.setStatue("P");
				int times = Integer.parseInt(otcSpotInfo.getTimes());
				times++;
				otcSpotInfo.setTimes(String.valueOf(times));
				//otcSpotDao.updateBean(otcSpotInfo);
				//�޸Ķ��ַ���״̬
//				OtcSpotInfo bean2 = new OtcSpotInfo();
//				bean2.setTranNo(tranNo);
//				bean2.setProvider(userNum);
				String opponent = otcSpotInfo.getProvider();
				OtcSpotInfo provider = SpotUtil.getInstance().getSpotByParams(tranNo, opponent);
				provider.setPrice(price);
				provider.setStatue("R");
				//otcSpotDao.updateBean(provider);
				
//				if((otcSpotInfo.getUserNum()).equals((UserModelUtil.getUser().getUserId()))){
//					otcSpotInfo.setPrice(price);
//					otcSpotInfo.setStatue("P");
//					int times = Integer.parseInt(otcSpotInfo.getTimes());
//					times++;
//					otcSpotInfo.setTimes(String.valueOf(times));
//					otcSpotDao.updateBean(otcSpotInfo);
//				}
//				else{
//					otcSpotInfo.setStatue("R");
//					otcSpotInfo.setPrice(price);
//					otcSpotDao.updateBean(otcSpotInfo);
//				}
			}
			else if(type.equals("accept")){
				//�޸ı������׼�¼
//				OtcSpotInfo bean = new OtcSpotInfo();
//				bean.setTranNo(tranNo);
//				bean.setUserNum(userNum);
//				OtcSpotInfo otcSpotInfo = otcSpotDao.getBeanByBean(bean, MatchMode.EXACT);
				OtcSpotInfo otcSpotInfo = SpotUtil.getInstance().getSpotByParams(tranNo, userNum);
				otcSpotInfo.setPrice(price);
				otcSpotInfo.setStatue("D");
				otcSpotInfo.setDate(GetDateTimeUtil.getCurrentDate());
				otcSpotInfo.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
				//����ִ�к�Ľ��
				otcSpotInfo.setLAmount(CalculateUtil.getAmountAfterTrade(otcSpotInfo.getAmount(), price));
				otcSpotDao.updateBean(otcSpotInfo);
				//�޸Ķ��ַ���״̬
//				OtcSpotInfo bean2 = new OtcSpotInfo();
//				bean2.setTranNo(tranNo);
//				bean2.setProvider(userNum);
//				OtcSpotInfo provider = otcSpotDao.getBeanByBean(bean2, MatchMode.EXACT);
				String opponent = otcSpotInfo.getProvider();
				OtcSpotInfo provider = SpotUtil.getInstance().getSpotByParams(tranNo, opponent);
				provider.setPrice(price);
				provider.setStatue("D");
				provider.setDate(GetDateTimeUtil.getCurrentDate());
				provider.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
				//����ִ�к�Ľ��
				provider.setLAmount(CalculateUtil.getAmountAfterTrade(provider.getAmount(), price));
				otcSpotDao.updateBean(provider);
			}
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("������Ϣ�޸�ʧ�ܣ��û���" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSpotTradeUpdate");
			be.setExceptionType("������Ϣ�޸�ʧ�ܣ�");
			throw be;
		}
	}
	
	/**
	 * �����ڶ��ַ���ֹ����ʱ����Ҫɾ�����ף�
	 * @param tranNo ֻ��Ҫ���ױ�ž���ɾ����
	 * @return ��־������ɾ���ɹ���
	 */
	public String otcSpotTradeDel(String tranNo){
		try {
			OtcSpotInfo bean = new OtcSpotInfo();
			bean.setTranNo(tranNo);
			Collection collection = otcSpotDao.getBeansByBean(bean, MatchMode.ANYWHERE);
			otcSpotDao.batchDelete(collection);
			String userNum = UserModelUtil.getUser().getUserId();
			OtcSpotInfo otcSpotInfo = SpotUtil.getInstance().getSpotByParams(tranNo, userNum);
			String provider = otcSpotInfo.getProvider();
			SpotUtil.getInstance().spotDestroy(tranNo, userNum);
			SpotUtil.getInstance().spotDestroy(tranNo, provider);
			
			return "1";
			/*//ɾ�������ļ�¼
			OtcSpotInfo otcSpotInfo=new OtcSpotInfo();
			otcSpotInfo.setTranNo(tranNo);
			otcSpotInfo.setUserNum(userNum);
			otcSpotInfo.setWeCcy(weCcy);
			otcSpotInfo.setAnaCcy(anaCcy);
			collection=(List<OtcSpotInfo>)otcSpotDao.getBeansByBean(otcSpotInfo, MatchMode.ANYWHERE);
			//ɾ�����ַ��ļ�¼
			OtcSpotInfo otcSpotInfo2=new OtcSpotInfo();
			otcSpotInfo.setTranNo(tranNo);
			otcSpotInfo2.setProvider(userNum);
			otcSpotInfo2.setWeCcy(anaCcy);
			otcSpotInfo2.setAnaCcy(weCcy);
			collection.addAll(otcSpotDao.getBeansByBean(otcSpotInfo2, MatchMode.ANYWHERE));
			for(int i=0;i<collection.size();i++){
				if(collection.get(i).getStatue().equals("D")){
					collection.remove(i);
				}
			}
			otcSpotDao.batchDelete(collection);*/
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("������Ϣɾ��ʧ�ܣ��û���" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSpotTradeDelete");
			be.setExceptionType("������Ϣɾ��ʧ�ܣ�");
			throw be;
		}
	}

	/**
	 * ���׳ɹ���Ĳ�����
	 * @param tranNo ȷ�Ͻ��׺�Ľ��ױ��
	 * @param provider ȷ�����׵Ķ��ַ�
	 * @param price ���ռ۸�
	 * @return true���׳ɹ���false����ʧ��
	 */
	public boolean acceptDone(String tranNo, String provider, String price){
		try {
			// ȡ�÷��𷽵Ľ��׼�¼
			String userNum = UserModelUtil.getUser().getUserId();
//			OtcSpotInfo bean = new OtcSpotInfo();
//			bean.setTranNo(tranNo);
//			bean.setUserNum(userNum);
			OtcSpotInfo otcSpotInfo = SpotUtil.getInstance().getSpotByParams(tranNo, userNum);
			// ȡ�ö��ַ��Ľ��׼�¼
			OtcSpotInfo otcSpotInfo2 = SpotUtil.getInstance().getSpotByParams(tranNo, provider);
			if (otcSpotInfo != null && otcSpotInfo2 != null) {
				// �޸ķ��𷽺Ͷ��ַ��Ľ��׼�¼
				otcSpotTradeUpdate(tranNo, userNum, price, "accept");
				//otcSpotTradeUpdate(otcSpotInfo2, price, "accept");
				Double price1 = Double.parseDouble(price);//���׵ļ۸�
				Double amt = Double.parseDouble(otcSpotInfo.getAmount());//���׵Ľ��

				// �޸ı����˻���Ϣ���ж�Ӧ���ֵĽ��
				//���ּ��ٵ��˻�
				AccInfo accInfo = new AccInfo();
				accInfo.setUserNum(userNum);
				accInfo.setAccType(otcSpotInfo.getTranType());
				accInfo.setCcy(otcSpotInfo.getWeCcy());// ��������
				AccInfo weAccInfo = accInfoDao.getBeanByBean(accInfo, MatchMode.EXACT);

				//�������ӵ��˻�
				AccInfo accInfo2 = new AccInfo();
				accInfo2.setUserNum(userNum);
				accInfo2.setAccType(otcSpotInfo.getTranType());
				accInfo2.setCcy(otcSpotInfo.getAnaCcy());//�������
				AccInfo anaAccInfo = accInfoDao.getBeanByBean(accInfo2, MatchMode.EXACT);

				//acc_info����µĽ���õ���ʵ�ʽ��
				Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());//weCcy���ֶ�Ӧ���˻����
				Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());//anaCcy���ֶ�Ӧ���˻����

				//weCcy���ֶ�Ӧ���˻����     = ���-ʵ�ʽ��׽��(�Խ���Ա�ĽǶȣ�weCcy�洢����Զ������ȥ�ı���)
				//anaCcy���ֶ�Ӧ���˻����   = ���+ʵ�ʽ��׽��*�۸�(�Խ���Ա�ĽǶȣ�anaCcy�洢����Զ������ı���)
				if (otcSpotInfo.getDirection().equals("0")) {
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney - amt));
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney + price1*amt));
				} else {
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney - price1*amt));
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney + amt));
				}
				accInfoDao.updateBean(weAccInfo);
				accInfoDao.updateBean(anaAccInfo);

				//�޸Ķ��ַ��˻�����Ϣ���Ӧ���ֵĽ��
				//�������ӵ��˻�
				AccInfo accInfo3 = new AccInfo();
				accInfo3.setUserNum(provider);
				accInfo3.setAccType(otcSpotInfo.getTranType());
				accInfo3.setCcy(otcSpotInfo.getWeCcy());// �������
				AccInfo weAccInfo1 = accInfoDao.getBeanByBean(accInfo3,MatchMode.EXACT);

				//���ּ��ٵ��˻�
				AccInfo accInfo4 = new AccInfo();
				accInfo4.setUserNum(provider);
				accInfo4.setAccType(otcSpotInfo.getTranType());
				accInfo4.setCcy(otcSpotInfo.getAnaCcy());//��������
				AccInfo anaAccInfo1 = accInfoDao.getBeanByBean(accInfo4,MatchMode.EXACT);

				//acc_info����µĽ���õ���ʵ�ʽ��
				Double weCcyMoney1 = Double.parseDouble(weAccInfo1.getAmount());//weCcy���ֶ�Ӧ���˻����
				Double anaCcyMoney1 = Double.parseDouble(anaAccInfo1.getAmount());//anaCcy���ֶ�Ӧ���˻����

				//weCcy���ֶ�Ӧ���˻����     = ���+ʵ�ʽ��׽��*��(�Զ��ַ��ĽǶ�������weCcy�洢����Զ������ı���)
				//anaCcy���ֶ�Ӧ���˻����   = ���-ʵ�ʽ��׽���(���ַ��ĽǶ�������anaCcy�洢����Զ�������ı���)
				if (otcSpotInfo.getDirection().equals("0")) {
					weAccInfo1.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney1 + amt));
					anaAccInfo1.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney1 - price1 * amt));
				} else {
					weAccInfo1.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney1 + price1 * amt));
					anaAccInfo1.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney1 - amt));
				}
				accInfoDao.updateBean(weAccInfo1);
				accInfoDao.updateBean(anaAccInfo1);
				//���½�����ˮ��c_tran_flow_mapping
				if (otcSpotInfo.getTranType().equals("C")) {
					//����
					CTranFlowMapping c = new CTranFlowMapping();
					c.setUserNum(userNum);
					CTranFlowMapping cTranFlowMapping = cTranFlowMappingDao.getBeanByBean(c, MatchMode.EXACT);
					//���״�����1���������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
					cTranFlowMapping.setOtcSpotQty((Integer.parseInt(cTranFlowMapping.getOtcSpotQty()) + 1)+ "");
					cTranFlowMapping.setOtcSpotAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(cTranFlowMapping.getOtcSpotAmt())+amt));
					cTranFlowMapping.setCount(cTranFlowMapping.getCount() + 1);
					cTranFlowMapping.setAmount(cTranFlowMapping.getAmount()+ amt);

					//���ַ�
					CTranFlowMapping c1 = new CTranFlowMapping();
					c1.setUserNum(provider);
					CTranFlowMapping cTranFlowMapping1 = cTranFlowMappingDao.getBeanByBean(c1, MatchMode.EXACT);
					//���״�����1���������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
					cTranFlowMapping1.setOtcSpotQty((Integer.parseInt(cTranFlowMapping1.getOtcSpotQty()) + 1)+ "");
					cTranFlowMapping1.setOtcSpotAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(cTranFlowMapping1.getOtcSpotAmt())+ amt));
					cTranFlowMapping1.setCount(cTranFlowMapping1.getCount() + 1);
					cTranFlowMapping1.setAmount(cTranFlowMapping1.getAmount()+ amt);
					cTranFlowMappingDao.updateBean(cTranFlowMapping);
					cTranFlowMappingDao.updateBean(cTranFlowMapping1);
				} else {
					//����
					WTranFlowMapping w = new WTranFlowMapping();
					w.setUserNum(userNum);
					WTranFlowMapping wTranFlowMapping = wTranFlowMappingDao.getBeanByBean(w, MatchMode.EXACT);
					//���״�����1���������ӽ�� �� �ܽ��״�����һ���ܽ������ӽ��
					String qty = wTranFlowMapping.getOtcSpotQty();
					int otcSpotQty = Integer.parseInt(qty);
					otcSpotQty++;
					wTranFlowMapping.setOtcSpotQty(otcSpotQty + "");
					wTranFlowMapping.setOtcSpotAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wTranFlowMapping.getOtcSpotAmt())+ amt));
					wTranFlowMapping.setCount(wTranFlowMapping.getCount() + 1);
					wTranFlowMapping.setAmount(wTranFlowMapping.getAmount()+ amt);

					//���ַ�
					WTranFlowMapping w1 = new WTranFlowMapping();
					w1.setUserNum(provider);
					WTranFlowMapping wTranFlowMapping1 = wTranFlowMappingDao.getBeanByBean(w1, MatchMode.EXACT);
					//���״�����1���������ӽ�� �� �ܽ��״�����һ���ܽ������ӽ��
					String qty1 = wTranFlowMapping1.getOtcSpotQty();
					int otcSpotQty1 = Integer.parseInt(qty1);
					otcSpotQty1++;
					wTranFlowMapping1.setOtcSpotQty(otcSpotQty1 + "");
					wTranFlowMapping1.setOtcSpotAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wTranFlowMapping1.getOtcSpotAmt())+ amt));
					wTranFlowMapping1.setCount(wTranFlowMapping1.getCount() + 1);
					wTranFlowMapping1.setAmount(wTranFlowMapping1.getAmount()+ amt);
					wTranFlowMappingDao.updateBean(wTranFlowMapping);
					wTranFlowMappingDao.updateBean(wTranFlowMapping1);
					
					//ɾ��table�еĽ��׼�¼��������ɾ�����ݿ��еĽ��׼�¼��
					SpotUtil.getInstance().spotDestroy(tranNo, userNum);
					SpotUtil.getInstance().spotDestroy(tranNo, provider);
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("����ѯ�۽��ײ��ɹ����û���" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("acceptDone");
			be.setExceptionType("����ѯ�۽��ײ��ɹ���");
			throw be;
		}
	}
	
	/**
	 * ���ϵز�ѯtable�ļ۸�仯���
	 * @param tranNo ����ѯ�Ľ��ױ��
	 * @param provider ����ѯ�Ķ��ַ�
	 * @return ״̬���߼۸���Ҫ�ǿ���ǰ̨�ļ۸�������
	 */
	public String otcSpotTradeCheckPrice(String tranNo, String provider){
		//OtcSpotInfo otcSpotInfo = otcSpotDao.getBeanByBean2(tranNo, provider);
		String userNum = UserModelUtil.getUser().getUserId();
		OtcSpotInfo otcSpotInfo = SpotUtil.getInstance().getSpotByParams(tranNo, userNum);
		if(otcSpotInfo.getStatue().equals("C")){
			return "C";//ȡ������
		}
		else if(otcSpotInfo.getStatue().equals("F")) {
			return "F";//ͬ��۸�
		}
		else{
			return otcSpotInfo.getPrice();//�޸ĺ�ļ۸�
		}
		//String userNum = UserModelUtil.getUser().getUserId();
//		OtcSpotInfo bean = new OtcSpotInfo();
//		bean.setTranNo(tranNo);
//		//bean.setUserNum(userNum);
//		bean.setProvider(provider);
//		OtcSpotInfo otcSpotInfo = otcSpotDao.getBeanByBean(bean, MatchMode.EXACT);
//		String price = otcSpotInfo.getPrice();
		//System.out.println("----------price-------------"+price);
	}
	
	/**
	 * ���withdraw����ͨ���÷�����ɾ�����еĽ��׼�¼
	 * @param tranNo ��ɾ���Ľ��׼�¼
	 */
	public void otcSpotTradeWithdraw(String tranNo){
		if(tranNo.startsWith("ST")){
			otcSpotTradeDel(tranNo);
		}
	}
	
	public AccInfoDao getAccInfoDao() {
		return accInfoDao;
	}
	public void setAccInfoDao(AccInfoDao accInfoDao) {
		this.accInfoDao = accInfoDao;
	}
	public OtcSpotDao getOtcSpotDao() {
		return otcSpotDao;
	}
	public void setOtcSpotDao(OtcSpotDao otcSpotDao) {
		this.otcSpotDao = otcSpotDao;
	}
	public UsersDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UsersDao userDao) {
		this.userDao = userDao;
	}
	public AnalogueMagDao getAnalogueMagDao() {
		return analogueMagDao;
	}
	public void setAnalogueMagDao(AnalogueMagDao analogueMagDao) {
		this.analogueMagDao = analogueMagDao;
	}
	public CTranFlowMappingDao getcTranFlowMappingDao() {
		return cTranFlowMappingDao;
	}
	public void setcTranFlowMappingDao(CTranFlowMappingDao cTranFlowMappingDao) {
		this.cTranFlowMappingDao = cTranFlowMappingDao;
	}
	public WTranFlowMappingDao getwTranFlowMappingDao() {
		return wTranFlowMappingDao;
	}
	public void setwTranFlowMappingDao(WTranFlowMappingDao wTranFlowMappingDao) {
		this.wTranFlowMappingDao = wTranFlowMappingDao;
	}
}
