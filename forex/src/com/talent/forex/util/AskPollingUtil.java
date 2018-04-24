package com.talent.forex.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;

import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.OtcForwardInfo;
import com.talent.forex.bean.domain.OtcSwapInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.bean.model.LiborModel;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.OtcForwardDao;
import com.talent.forex.dao.OtcSwapDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.forex.modules.rateFactory.RateReceive;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.hibernate.util.TransactionNestUtil;

public class AskPollingUtil {
	
	private static Logger logger = Logger.getLogger(AskPollingUtil.class);
	
	/**
	 * ��������Ǳ���ʱ����ѯִ��  ������׵������������ϵͳ���ڷ�����ִ��
	 */
	//Զ�ڽ��׳ɹ���Ĳ���
	public static void checkForwardValueDate() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~forward��ѯ��ʼ������~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		OtcForwardDao otcForwardDao = new OtcForwardDao();
		AccInfoDao accInfoDao = new AccInfoDao();
		WTranFlowMappingDao wTranFlowMappingDao = new WTranFlowMappingDao();
		CTranFlowMappingDao cTranFlowMappingDao = new CTranFlowMappingDao();
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
			OtcForwardInfo otcForwardInfo = new OtcForwardInfo();
			otcForwardInfo.setStatue("A");
			otcForwardInfo.setValueDate(GetDateTimeUtil.getCurrentDate());
			List<OtcForwardInfo> list = (List<OtcForwardInfo>)otcForwardDao.getBeansByBean(otcForwardInfo, MatchMode.ANYWHERE);
			
			//��ΪupdateBean�����޷�ʹ��   ֻ��ͨ�������ύ��
			List<OtcForwardInfo> forwardListToUpdate = new ArrayList<OtcForwardInfo>();
			List<CTranFlowMapping> cTranListToUpdate = new ArrayList<CTranFlowMapping>();
			List<WTranFlowMapping> wTranListToUpdate = new ArrayList<WTranFlowMapping>();
			List<AccInfo> accListToUpdate = new ArrayList<AccInfo>();
			for (int i = 0; i < list.size(); i++) {
				OtcForwardInfo bean = list.get(i);
				// 1.�����˻���acc_info,�ȵõ����ױ��ֵ��˻�bean
				// weCcy�������� anaCcy���������
				AccInfo accInfo = new AccInfo();
				accInfo.setUserNum(bean.getUserNum());
				accInfo.setAccType(bean.getTranType());
				accInfo.setCcy(bean.getWeCcy());
				AccInfo weAccInfo = accInfoDao.getBeanByBean(accInfo,MatchMode.ANYWHERE);

				AccInfo accInfo2 = new AccInfo();
				accInfo2.setUserNum(bean.getUserNum());
				accInfo2.setAccType(bean.getTranType());
				accInfo2.setCcy(bean.getAnaCcy());
				AccInfo anaAccInfo = accInfoDao.getBeanByBean(accInfo2,MatchMode.ANYWHERE);

				Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());// weCcy���ֶ�Ӧ���˻����
				Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());// anaCcy���ֶ�Ӧ���˻����
				
				Double price = Double.parseDouble(bean.getPrice()) + Double.parseDouble(bean.getPoint()) / 10000;// Զ�ڽ��׵ļ۸�
				Double amt = Double.parseDouble(bean.getAmount());// Զ�ڽ��׵�ʵ�ʽ��׽��

				if (bean.getDirection().equals("1")) {
					// ��
					// weCcy���ֶ�Ӧ���˻���� = ���-ʵ�ʽ��׽��*�۸�
					// anaCcy���ֶ�Ӧ���˻���� = ���+ʵ�ʽ��׽��
					if(weCcyMoney - amt * price < 0){
						logger.error("�û���" + bean.getUserNum() + " ���ӵ���ѯ�۽���ʧ�ܣ��˻�"+ accInfo.getCcy() +"����");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("������˻�"+ weAccInfo.getCcy() +"����,����ѯ�۽������ύʧ�ܣ�");
						else
							be.setExceptionType("����˻�"+ weAccInfo.getCcy() +"����,����ѯ�۽����ύʧ�ܣ�");
						throw be;
					}
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney - amt * price));
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney + amt));
				} else {
					// ��
					// weCcy���ֶ�Ӧ���˻���� = ���-ʵ�ʽ��׽��
					// anaCcy���ֶ�Ӧ���˻���� = ���+ʵ�ʽ��׽��*�۸�
					if(weCcyMoney - amt < 0){
						logger.error("�û���" + bean.getUserNum() + " ���ӵ���ѯ�۽���ʧ�ܣ��˻�"+ accInfo.getCcy() +"����");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("������˻�"+ weAccInfo.getCcy() +"����,����ѯ�۽������ύʧ�ܣ�");
						else
							be.setExceptionType("����˻�"+ weAccInfo.getCcy() +"����,����ѯ�۽����ύʧ�ܣ�");
						throw be;
					}
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney - amt));
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney + price * amt));
				}

				// 2.���½�����ˮ��
				if(list.get(i).getTranType().equals("C")){
					CTranFlowMapping c = new CTranFlowMapping();
					c.setUserNum(bean.getUserNum());
					CTranFlowMapping cTranFlowMapping = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
					// Զ�ڽ��״�����1��Զ�ڽ������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
					cTranFlowMapping.setOtcForwartQty((Integer.parseInt(cTranFlowMapping.getOtcForwartQty()) + 1) + "");
					cTranFlowMapping.setOtcForwartAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(cTranFlowMapping.getOtcForwartAmt()) + amt));
					cTranFlowMapping.setCount(cTranFlowMapping.getCount() + 1);
					cTranListToUpdate.add(cTranFlowMapping);
				}else {
					WTranFlowMapping w = new WTranFlowMapping();
					w.setUserNum(bean.getUserNum());
					WTranFlowMapping wTranFlowMapping = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
					// Զ�ڽ��״�����1��Զ�ڽ������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
					wTranFlowMapping.setOtcForwartQty((Integer.parseInt(wTranFlowMapping.getOtcForwartQty()) + 1) + "");
					wTranFlowMapping.setOtcForwartAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wTranFlowMapping.getOtcForwartAmt()) + amt));
					wTranFlowMapping.setCount(wTranFlowMapping.getCount() + 1);
					wTranListToUpdate.add(wTranFlowMapping);
				}
				bean.setStatue("D");
				// 4.����ص�bean���µ���Ӧ�ı�
				forwardListToUpdate.add(bean);
				accListToUpdate.add(weAccInfo);
				accListToUpdate.add(anaAccInfo);
			}
			//�����ύ
			if(forwardListToUpdate.size() > 0){
				otcForwardDao.batchUpdate(forwardListToUpdate);
			}
			if(cTranListToUpdate.size() > 0){
				cTranFlowMappingDao.batchUpdate(cTranListToUpdate);
			}
			if(wTranListToUpdate.size() > 0){
				wTranFlowMappingDao.batchUpdate(wTranListToUpdate);
			}
			if(accListToUpdate.size() > 0){
				accInfoDao.batchUpdate(accListToUpdate);
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("Զ�ڽ�����ѯ������" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	/**
	 * ��������Ǳ���ʱ����ѯִ��  ������׵������������ϵͳ���ڷ�����ִ��
	 */
	//���ڽ����еĽ��˽���
	public static void checkSwapStartDate(){
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~swap���˽�����ѯ��ʼ������~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		OtcSwapDao otcSwapDao = new OtcSwapDao();
		AccInfoDao accInfoDao = new AccInfoDao();
		WTranFlowMappingDao wTranFlowMappingDao = new WTranFlowMappingDao();
		CTranFlowMappingDao cTranFlowMappingDao = new CTranFlowMappingDao();
		String rsf = null;
		try {
			rsf = TransactionNestUtil.reference();
			OtcSwapInfo otcSwapInfo = new OtcSwapInfo();
			otcSwapInfo.setStatue("A");
			String today = GetDateTimeUtil.getCurrentDate();
			otcSwapInfo.setStartDate(today);
			otcSwapInfo.setDate(today);
			List<OtcSwapInfo> list = (List<OtcSwapInfo>)otcSwapDao.getBeansByBean(otcSwapInfo, MatchMode.ANYWHERE);
			
			//��ΪupdateBean�����޷�ʹ��   ֻ��ͨ�������ύ��
			ArrayList<OtcSwapInfo> swapListToUpdate = new ArrayList<OtcSwapInfo>();
			ArrayList<CTranFlowMapping> cTranListToUpdate = new ArrayList<CTranFlowMapping>();
			ArrayList<WTranFlowMapping> wTranListToUpdate = new ArrayList<WTranFlowMapping>();
			ArrayList<AccInfo> accListToUpdate = new ArrayList<AccInfo>();
			for (int i = 0; i < list.size(); i++) {
				OtcSwapInfo bean = list.get(i);
				// 1.�����˻���acc_info,�ȵõ����ױ��ֵ��˻�bean
				// weCcy�������� anaCcy���������
				AccInfo accInfo = new AccInfo();
				accInfo.setUserNum(bean.getUserNum());
				accInfo.setAccType(bean.getTranType());
				accInfo.setCcy(bean.getWeCcy());
				AccInfo weAccInfo = accInfoDao.getBeanByBean(accInfo,MatchMode.ANYWHERE);

				AccInfo accInfo2 = new AccInfo();
				accInfo2.setUserNum(bean.getUserNum());
				accInfo2.setAccType(bean.getTranType());
				accInfo2.setCcy(bean.getAnaCcy());
				AccInfo anaAccInfo = accInfoDao.getBeanByBean(accInfo2,MatchMode.ANYWHERE);

				Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());// ��ȡweCcy���ֶ�Ӧ���˻����
				Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());// ��ȡanaCcy���ֶ�Ӧ���˻����
				Double price = Double.parseDouble(bean.getPrice()) + Double.parseDouble(bean.getPoint()) / 10000;// ���ڽ��׵ļ۸�(Լ���۸�)
				Double amt = Double.parseDouble(bean.getCAmount());// ���ڽ��׵�ʵ�ʽ��׽��

				//2.��������
				if (bean.getDirection().equals("1")) {
					// ��
					// weCcy���ֶ�Ӧ���˻���� = ���-ʵ�ʽ��׽��*�۸�
					// anaCcy���ֶ�Ӧ���˻���� = ���+ʵ�ʽ��׽��
					if(weCcyMoney - amt * price < 0){
						logger.error("�û���" + bean.getUserNum() + " ���ӵ���ѯ�۽���ʧ�ܣ��˻�"+ accInfo.getCcy() +"����");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("������˻�"+ weAccInfo.getCcy() +"����,����ѯ�۽������ύʧ�ܣ�");
						else
							be.setExceptionType("����˻�"+ weAccInfo.getCcy() +"����,����ѯ�۽����ύʧ�ܣ�");
						throw be;
					}
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney - amt * price));
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney + amt));
				} else {
					// ��
					// weCcy���ֶ�Ӧ���˻���� = ���-ʵ�ʽ��׽��
					// anaCcy���ֶ�Ӧ���˻���� = ���+ʵ�ʽ��׽��*�۸�
					if(weCcyMoney - amt < 0){
						logger.error("�û���" + bean.getUserNum() + " ���ӵ���ѯ�۽���ʧ�ܣ��˻�"+ accInfo.getCcy() +"����");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("������˻�"+ weAccInfo.getCcy() +"����,����ѯ�۽������ύʧ�ܣ�");
						else
							be.setExceptionType("����˻�"+ weAccInfo.getCcy() +"����,����ѯ�۽����ύʧ�ܣ�");
						throw be;
					}
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney - amt));
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney + price * amt));
				}

				// 3.���½�����ˮ��
				if(list.get(i).getTranType().equals("C")){
					CTranFlowMapping c = new CTranFlowMapping();
					c.setUserNum(bean.getUserNum());
					CTranFlowMapping cTranFlowMapping = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
					// Զ�ڽ��״�����1��Զ�ڽ������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
					cTranFlowMapping.setOtcSwapQty((Integer.parseInt(cTranFlowMapping.getOtcSwapQty()) + 1) + "");
					cTranFlowMapping.setOtcSwapAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(cTranFlowMapping.getOtcSwapAmt()) + amt));
					cTranFlowMapping.setCount(cTranFlowMapping.getCount() + 1);
					cTranListToUpdate.add(cTranFlowMapping);
				}else {
					WTranFlowMapping w = new WTranFlowMapping();
					w.setUserNum(bean.getUserNum());
					WTranFlowMapping wTranFlowMapping = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
					// Զ�ڽ��״�����1��Զ�ڽ������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
					wTranFlowMapping.setOtcSwapQty((Integer.parseInt(wTranFlowMapping.getOtcSwapQty()) + 1) + "");
					wTranFlowMapping.setOtcSwapAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wTranFlowMapping.getOtcSwapAmt()) + amt));
					wTranFlowMapping.setCount(wTranFlowMapping.getCount() + 1);
					wTranListToUpdate.add(wTranFlowMapping);
				}
				
				//3.������һ����Ϣ����
				int frequency = Integer.parseInt(bean.getFrequency());
				bean.setDate(getNextFrequency(today, frequency));
				
				// 4.����ص�bean���µ���Ӧ�ı�
				swapListToUpdate.add(bean);
				accListToUpdate.add(weAccInfo);
				accListToUpdate.add(anaAccInfo);
			}
			//�����ύ
			if(swapListToUpdate.size() > 0){
				otcSwapDao.batchUpdate(swapListToUpdate);
			}
			if(cTranListToUpdate.size() > 0){
				cTranFlowMappingDao.batchUpdate(cTranListToUpdate);
			}
			if(wTranListToUpdate.size() > 0){
				wTranFlowMappingDao.batchUpdate(wTranListToUpdate);
			}
			if(accListToUpdate.size() > 0){
				accInfoDao.batchUpdate(accListToUpdate);
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("���ڽ��׽��˽�����ѯ������" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	
	public static String getNextFrequency(String today, int frequency){
		int year = Integer.parseInt(today.substring(0, 4));
		int month = Integer.parseInt(today.substring(4, 6));
		int day = Integer.parseInt(today.substring(6, 8));
		switch(frequency){
		case 0://��ҹ
			if(day == getDaysByMonth(month)){
				if(month == 12){
					year++;
					month = 1;
					day = 1;
				}
				else{
					month++;
					day = 1;
				}
			}
			else{
				day++;
			}
			break;
		case 1://һ��
			if(day + 7 >= getDaysByMonth(month)){
				if(month == 12){
					year++;
					month = 1;
					day = 7 - (getDaysByMonth(month) - day);
				}
				else{
					month++;
					day = 7 - (getDaysByMonth(month) - day);
				}
			}
			else{
				day = day + 7;
			}
			break;
		case 2://һ����
			if(month == 12){
				year++;
				month = 1;
			}
			else{
				month++;
			}
			break;
		case 3://������
			if(month > 10){
				year++;
				month = month + 2 - 12;
			}
			else{
				month = month + 2;
			}
			break;
		case 4://һ����
			if(month > 9){
				year++;
				month = month + 3 - 12;
			}
			else{
				month = month + 3;
			}
			break;
		case 5://����
			if(month > 6){
				year++;
				month = month + 6 - 12;
			}
			else{
				month = month + 6;
			}
			break;
		case 6://һ��
			year++;
			break;
		}
		String m = month + "";
		String d = day + "";
		if(month < 10){
			m = "0" + m;
		}
		if(day < 10){
			d = "0" + d;
		}
		String date = year + m +d;
		return date;
	}
	
	public static int getDaysByMonth(int month){
		int day = 0;
		switch(month){
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			day = 30;
			break;
		case 2:
			day = 28;
			break;
		}
		return day;
	}
	
	/**
	 * ��������Ǳ���ʱ����ѯִ��  ������׵������������ϵͳ���ڷ�����ִ��
	 */
	//���ڽ����е�֧�����ڽ���
	public static void checkSwapFrequencyDate(){
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~swap֧�����ڽ�����ѯ��ʼ������~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		OtcSwapDao otcSwapDao = new OtcSwapDao();
		AccInfoDao accInfoDao = new AccInfoDao();
		String rsf = null;
		try {
			rsf = TransactionNestUtil.reference();
			OtcSwapInfo otcSwapInfo = new OtcSwapInfo();
			otcSwapInfo.setStatue("A");
			String today = GetDateTimeUtil.getCurrentDate();
			otcSwapInfo.setDate(today);
			List<OtcSwapInfo> list = (List<OtcSwapInfo>)otcSwapDao.getBeansByBean(otcSwapInfo, MatchMode.ANYWHERE);
			
			//��ΪupdateBean�����޷�ʹ��   ֻ��ͨ�������ύ��
			ArrayList<OtcSwapInfo> swapListToUpdate = new ArrayList<OtcSwapInfo>();
			ArrayList<AccInfo> accListToUpdate = new ArrayList<AccInfo>();
			for (int i = 0; i < list.size(); i++) {
				OtcSwapInfo bean = list.get(i);
				// 1.�����˻���acc_info,�ȵõ����ױ��ֵ��˻�bean
				// weCcy�������� anaCcy���������
				AccInfo accInfo = new AccInfo();
				accInfo.setUserNum(bean.getUserNum());
				accInfo.setAccType(bean.getTranType());
				accInfo.setCcy(bean.getWeCcy());
				AccInfo weAccInfo = accInfoDao.getBeanByBean(accInfo,MatchMode.ANYWHERE);

				AccInfo accInfo2 = new AccInfo();
				accInfo2.setUserNum(bean.getUserNum());
				accInfo2.setAccType(bean.getTranType());
				accInfo2.setCcy(bean.getAnaCcy());
				AccInfo anaAccInfo = accInfoDao.getBeanByBean(accInfo2,MatchMode.ANYWHERE);

				Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());// ��ȡweCcy���ֶ�Ӧ���˻����
				Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());// ��ȡanaCcy���ֶ�Ӧ���˻����
				Double price = Double.parseDouble(bean.getPrice()) + Double.parseDouble(bean.getPoint()) / 10000;// ���ڽ��׵ļ۸�(Լ���۸�)
				Double amt = Double.parseDouble(bean.getCAmount());// ���ڽ��׵�ʵ�ʽ��׽��

				//2.����������Ϣ
				double receiveRate = 0d;
				double payRate = 0d;
				if(bean.getFixedType().equals("0")){//receive weccy fixedRate,pay anaccy floatingRate
					//����receive weccy fixedRate
					double rRate = Double.parseDouble(bean.getReceiveRate()) / 100;
					double basis1 = (double)Integer.parseInt(bean.getCBasis()) / 10000;
					receiveRate = rRate + basis1;
					
					//����pay anaccy floatingRate
					double libor = 0d;
					List<LiborModel> liborModels = (List<LiborModel>)GetRateUtil.getInstance().getLibor();
					for(LiborModel liborModel : liborModels){
						if(liborModel.getCcy().equals(bean.getAnaCcy())){
							int liborType = Integer.parseInt(bean.getLibor());
							switch(liborType){
							case 0:
								libor = Double.parseDouble(liborModel.getOneDay());
								break;
							case 1:
								libor = Double.parseDouble(liborModel.getOneWeek());
								break;
							case 2:
								libor = Double.parseDouble(liborModel.getOneMonth());
								break;
							case 3:
								libor = Double.parseDouble(liborModel.getTwoMonth());
								break;
							case 4:
								libor = Double.parseDouble(liborModel.getThreeMonth());
								break;
							case 5:
								libor = Double.parseDouble(liborModel.getSixMonth());
								break;
							case 6:
								libor = Double.parseDouble(liborModel.getTwelveMonth());
								break;
							}
							break;
						}
					}
					double basis2 = (double)Integer.parseInt(bean.getFBasis()) / 10000;
					payRate = libor / 100 + basis2;
				}
				else{//pay anaccy fixedRate,receive weccy floatingRate
					//����pay anaccy fixedRate
					double pRate = Double.parseDouble(bean.getPayRate()) / 100;
					double basis1 = (double)Integer.parseInt(bean.getCBasis()) / 10000;
					payRate = pRate + basis1;
					
					//����receive weccy floatingRate
					double libor = 0d;
					List<LiborModel> liborModels = (List<LiborModel>)GetRateUtil.getInstance().getLibor();
					for(LiborModel liborModel : liborModels){
						if(liborModel.getCcy().equals(bean.getWeCcy())){
							int liborType = Integer.parseInt(bean.getLibor());
							switch(liborType){
							case 0:
								libor = Double.parseDouble(liborModel.getOneDay());
								break;
							case 1:
								libor = Double.parseDouble(liborModel.getOneWeek());
								break;
							case 2:
								libor = Double.parseDouble(liborModel.getOneMonth());
								break;
							case 3:
								libor = Double.parseDouble(liborModel.getTwoMonth());
								break;
							case 4:
								libor = Double.parseDouble(liborModel.getThreeMonth());
								break;
							case 5:
								libor = Double.parseDouble(liborModel.getSixMonth());
								break;
							case 6:
								libor = Double.parseDouble(liborModel.getTwelveMonth());
								break;
							}
							break;
						}
					}
					double basis2 = (double)Integer.parseInt(bean.getFBasis()) / 10000;
					receiveRate = libor / 100 + basis2;
				}
				int frequency = Integer.parseInt(bean.getFrequency());
				int days = 0;
				switch(frequency){
				case 0:
					days = 1;
					break;
				case 1:
					days = 7;
					break;
				case 2:
					days = 30;
					break;
				case 3:
					days = 60;
					break;
				case 4:
					days = 90;
					break;
				case 5:
					days = 180;
					break;
				case 6:
					days = 360;
					break;
				}
				if(bean.getDirection().equals("1")){//��
					double receiveInterest = amt * price * receiveRate * days / 360;
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney + receiveInterest));
					
					double payInterest = amt * payRate * days / 360;
					if(anaCcyMoney - payInterest < 0){
						logger.error("�û���" + bean.getUserNum() + " ���ӵ���ѯ�۽���ʧ�ܣ��˻�"+ accInfo.getCcy() +"����");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("������˻�"+ weAccInfo.getCcy() +"����,����ѯ�۽����ύʧ�ܣ�");
						else
							be.setExceptionType("����˻�"+ weAccInfo.getCcy() +"����,����ѯ�۽����ύʧ�ܣ�");
						throw be;
					}
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney - payInterest));
				}
				else{//��
					double receiveInterest = amt * receiveRate * days / 360;
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney + receiveInterest));
					
					double payInterest = amt * price * payRate * days / 360;
					if(anaCcyMoney - payInterest < 0){
						logger.error("�û���" + bean.getUserNum() + " ���ӵ���ѯ�۽���ʧ�ܣ��˻�"+ accInfo.getCcy() +"����");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("������˻�"+ weAccInfo.getCcy() +"����,����ѯ�۽����ύʧ�ܣ�");
						else
							be.setExceptionType("����˻�"+ weAccInfo.getCcy() +"����,����ѯ�۽����ύʧ�ܣ�");
						throw be;
					}
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney - payInterest));
				}
				
				//3.������һ����Ϣ����
				String nextDay = getNextFrequency(today, frequency);
				String maturityDate = bean.getMaturityDate();
				int nd = Integer.parseInt(nextDay);
				int md = Integer.parseInt(maturityDate);
				if(nd < md){//��һ����Ϣ֧���ղ��ܳ�����Ϣ��
					bean.setDate(nextDay);
				}
				
				// 4.����ص�bean���µ���Ӧ�ı�
				swapListToUpdate.add(bean);
				accListToUpdate.add(weAccInfo);
				accListToUpdate.add(anaAccInfo);
			}
			//�����ύ
			if(swapListToUpdate.size() > 0){
				otcSwapDao.batchUpdate(swapListToUpdate);
			}
			if(accListToUpdate.size() > 0){
				accInfoDao.batchUpdate(accListToUpdate);
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("���ڽ�������֧��������ѯ������" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	
	/**
	 * ��������Ǳ���ʱ����ѯִ��  ������׵������������ϵͳ���ڷ�����ִ��
	 */
	//���ڽ����е�Զ�˽��ף�
	public static void checkSwapMaturityDate(){
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~swapԶ�˽�����ѯ��ʼ������~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		OtcSwapDao otcSwapDao = new OtcSwapDao();
		AccInfoDao accInfoDao = new AccInfoDao();
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
			OtcSwapInfo otcSwapInfo = new OtcSwapInfo();
			otcSwapInfo.setStatue("A");
			otcSwapInfo.setMaturityDate(GetDateTimeUtil.getCurrentDate());
			List<OtcSwapInfo> list = (List<OtcSwapInfo>)otcSwapDao.getBeansByBean(otcSwapInfo, MatchMode.ANYWHERE);
			
			//��ΪupdateBean�����޷�ʹ��   ֻ��ͨ�������ύ��
			ArrayList<OtcSwapInfo> swapListToUpdate = new ArrayList<OtcSwapInfo>();
			ArrayList<AccInfo> accListToUpdate = new ArrayList<AccInfo>();
			for (int i = 0; i < list.size(); i++) {
				OtcSwapInfo bean = list.get(i);
				// 1.�����˻���acc_info,�ȵõ����ױ��ֵ��˻�bean
				// weCcy�������� anaCcy���������
				AccInfo accInfo = new AccInfo();
				accInfo.setUserNum(bean.getUserNum());
				accInfo.setAccType(bean.getTranType());
				accInfo.setCcy(bean.getAnaCcy());
				AccInfo anaAccInfo = accInfoDao.getBeanByBean(accInfo,MatchMode.ANYWHERE);

				AccInfo accInfo2 = new AccInfo();
				accInfo2.setUserNum(bean.getUserNum());
				accInfo2.setAccType(bean.getTranType());
				accInfo2.setCcy(bean.getWeCcy());
				AccInfo weAccInfo = accInfoDao.getBeanByBean(accInfo2,MatchMode.ANYWHERE);

				Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());// weCcy���ֶ�Ӧ���˻����
				Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());// anaCcy���ֶ�Ӧ���˻����
				Double price = Double.parseDouble(bean.getPrice()) + Double.parseDouble(bean.getPoint()) / 10000;// ���ڽ��׵ļ۸�(Լ���۸�)
				Double amt = Double.parseDouble(bean.getFAmount());// ���ڽ��׵�ʵ�ʽ��׽��

				//2.���ر���
				if (bean.getDirection().equals("0")) {//���׷�������˵��෴��
					// ��
					// weCcy���ֶ�Ӧ���˻���� = ���+ʵ�ʽ��׽��*�۸�
					// anaCcy���ֶ�Ӧ���˻���� = ���-ʵ�ʽ��׽��
					if(anaCcyMoney - amt * price < 0){
						logger.error("�û���" + bean.getUserNum() + " ���ӵ���ѯ�۽���ʧ�ܣ��˻�"+ accInfo.getCcy() +"����");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("������˻�"+ anaAccInfo.getCcy() +"����,����ѯ�۽������ύʧ�ܣ�");
						else
							be.setExceptionType("����˻�"+ anaAccInfo.getCcy() +"����,����ѯ�۽����ύʧ�ܣ�");
						throw be;
					}
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney - amt * price));
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney + amt));
				} else {
					// ��
					// weCcy���ֶ�Ӧ���˻���� = ���+ʵ�ʽ��׽��*�۸�
					// anaCcy���ֶ�Ӧ���˻���� = ���-ʵ�ʽ��׽��
					if(anaCcyMoney - amt < 0){
						logger.error("�û���" + bean.getUserNum() + " ���ӵ���ѯ�۽���ʧ�ܣ��˻�"+ accInfo.getCcy() +"����");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("������˻�"+ anaAccInfo.getCcy() +"����,����ѯ�۽������ύʧ�ܣ�");
						else
							be.setExceptionType("����˻�"+ anaAccInfo.getCcy() +"����,����ѯ�۽����ύʧ�ܣ�");
						throw be;
					}
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney - amt));
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney + price * amt));
				}
				bean.setStatue("D");

				// 4.����ص�bean���µ���Ӧ�ı�
				swapListToUpdate.add(bean);
				accListToUpdate.add(anaAccInfo);
				accListToUpdate.add(weAccInfo);
			}
			//�����ύ
			if(swapListToUpdate.size() > 0){
				otcSwapDao.batchUpdate(swapListToUpdate);
			}
			if(accListToUpdate.size() > 0){
				accInfoDao.batchUpdate(accListToUpdate);
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("���ڽ���Զ�˽�����ѯ������" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
}