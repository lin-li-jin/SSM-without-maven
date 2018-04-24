package com.talent.forex.modules.trade_mng;

import java.util.List;
import java.util.Map;

import com.talent.auth.bean.model.UserModel;
import com.talent.forex.bean.domain.GroupMng;
import com.talent.forex.bean.domain.GroupSysParam;
import com.talent.forex.bean.model.AccInfoModel;
import com.talent.forex.bean.model.TranFlowMappingModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.util.AccountNumberUtil;
import com.talent.forex.util.CodeTableUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS017��FOEXAS018��FOEXAS020
 * Create By    : lzc
 * Description  : �˻���������
 * Modify Date  : 2014-08-05
 */
public class AccountNumberAction extends ForexBaseAction {
	
	private String tradeType;//�˻�����
	private String groupOne;//һ����
	private String groupTwo;//������
	private String rankingType;//�������
	private String userNumber;//����Ա���
	private String rankingRange;//������Χ
	
	private AccountNumberManageBo accountNumberManageBo;
	
	/**
	 * ������Ӧ��������ʵʱ������ѯ
	 */
	@SuppressWarnings("unchecked")
	public String currentRankSearch(){
		UserModel user = UserModelUtil.getUser();
		List<TranFlowMappingModel> accountNumberList = null;
		List<TranFlowMappingModel> amountList = null;
		List<TranFlowMappingModel> countList = null;
		List<AccInfoModel> accInfoList = null;
		Map<String,Double> map = null;
		int[] a = null;
		int[] b = null;
		
		if (user.getUserType().equals("S")){
			String group = user.getGroupOneId();
			
			if (tradeType.equals("C")){//�鿴�˻�����Ϊ������˻�
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupOne", group);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountAmountRankByGroupOne", group);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountCountRankByGroupOne", group);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", group, "C");
				map = AccountNumberUtil.getCountRateList(accInfoList,"C"); //�����˻�ӯ����
			}
			else if (tradeType.equals("W")){//�鿴�˻�����Ϊ��Ҷ��˻�
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupOne", group);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountAmountRankByGroupOne", group);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountCountRankByGroupOne", group);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", group, "W");
				map = AccountNumberUtil.getCountRateList(accInfoList,"W"); //�����˻�ӯ����
			}
			else{//�鿴�˻�����Ϊ��֤���˻�
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupOne", group);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountAmountRankByGroupOne", group);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountCountRankByGroupOne", group);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", group, "B");
				map = AccountNumberUtil.getCountRateList(accInfoList,"B"); //�����˻�ӯ����
			}
			if (accountNumberList.size() > 0){
				AccountNumberUtil.setAmountRank(accountNumberList, amountList); //�����˻��н����ܶ�����
				AccountNumberUtil.setNumberRank(accountNumberList, countList); //�����˻��н��״�������
				AccountNumberUtil.setRate(accountNumberList, map); //������õ�ӯ���ʷ����ڶ�Ӧ��ÿ���û��˻�
				GroupSysParam g = accountNumberManageBo.groupSysParamQuery(group);
				b = a = AccountNumberUtil.initCollection(accountNumberList, g); //�˻��б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ��������������Ӧ�˻��б��±�
				AccountNumberUtil.rateRankSort(accountNumberList, b); //�����˻�ӯ��������
				AccountNumberUtil.setRateRank(accountNumberList, b); //�����˻���ӯ��������
				AccountNumberUtil.accountRankSort(accountNumberList, a); //�����˻�ֵ����
				accountNumberList = AccountNumberUtil.getListAfterSort(accountNumberList, a, Integer.parseInt(g.getOverallRankNum()), user.getUserId());
				requestPut("accountNumberList",accountNumberList);
			}
			requestPut("tradeTypeList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.ACCOUNT_TYPE_LIST));
			requestPut("tradeType",tradeType);
			return "student";
		}
		
		if (tradeType.equals("C")){//�鿴�˻�����Ϊ������˻�
			if (rankingRange != null){ //�ж��Ƿ��ǲ�ѯ����ѧ�����˻���Ϣ������ѯһ���������������������
				if (rankingRange.equals("One")){ //һ����������
					accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupOne", groupOne);
					amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountAmountRankByGroupOne", groupOne);
					countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountCountRankByGroupOne", groupOne);
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "C");
				}
				else{ //������������
					accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupTwo", groupTwo);
					amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountAmountRankByGroupTwo", groupTwo);
					countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountCountRankByGroupTwo", groupTwo);
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "C");
				}
			}
			else if (!groupTwo.equals("")){ //�鿴һ���������ڳ�Ա�������
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupTwo", groupTwo);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountAmountRankByGroupTwo", groupTwo);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountCountRankByGroupTwo", groupTwo);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "C");
			}
			else{ //�鿴һ��һ�������������
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupOne", groupOne);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountAmountRankByGroupOne", groupOne);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountCountRankByGroupOne", groupOne);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "C");
			}
			map = AccountNumberUtil.getCountRateList(accInfoList,"C"); //�����˻�ӯ����
		}
		else if (tradeType.equals("W")){//�鿴�˻�����Ϊ��Ҷ��˻�
			if (rankingRange != null){ //�ж��Ƿ��ǲ�ѯ����ѧ�����˻���Ϣ������ѯһ���������������������
				if (rankingRange.equals("One")){ //һ����������
					accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupOne", groupOne);
					amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountAmountRankByGroupOne", groupOne);
					countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountCountRankByGroupOne", groupOne);
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "W");
				}
				else{ //������������
					accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupTwo", groupTwo);
					amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountAmountRankByGroupTwo", groupTwo);
					countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountCountRankByGroupTwo", groupTwo);
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "W");
				}
			}
			else if (!groupTwo.equals("")){ //�鿴һ���������ڳ�Ա�������
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupTwo", groupTwo);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountAmountRankByGroupTwo", groupTwo);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountCountRankByGroupTwo", groupTwo);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "W");
			}
			else{ //�鿴һ��һ�������������
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupOne", groupOne);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountAmountRankByGroupOne", groupOne);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountCountRankByGroupOne", groupOne);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "W");
			}
			map = AccountNumberUtil.countRateList(accInfoList,"W"); //�����˻�ӯ����
		}
		else{//�鿴�˻�����Ϊ��֤���˻�
			if (rankingRange != null){ //�ж��Ƿ��ǲ�ѯ����ѧ�����˻���Ϣ������ѯһ���������������������
				if (rankingRange.equals("One")){ //һ����������
					accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupOne", groupOne);
					amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountAmountRankByGroupOne", groupOne);
					countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountCountRankByGroupOne", groupOne);
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "B");
				}
				else{ //������������
					accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupTwo", groupTwo);
					amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountAmountRankByGroupTwo", groupTwo);
					countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountCountRankByGroupTwo", groupTwo);
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "B");
				}
			}
			else if (!groupTwo.equals("")){ //�鿴һ���������ڳ�Ա�������
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupTwo", groupTwo);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountAmountRankByGroupTwo", groupTwo);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountCountRankByGroupTwo", groupTwo);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "B");
			}
			else{ //�鿴һ��һ�������������
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupOne", groupOne);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountAmountRankByGroupOne", groupOne);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountCountRankByGroupOne", groupOne);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "B");
			}
			map = AccountNumberUtil.countRateList(accInfoList,"B"); //�����˻�ӯ����
		}
		if (accountNumberList.size() > 0){
			AccountNumberUtil.setAmountRank(accountNumberList, amountList); //�����˻��н����ܶ�����
			AccountNumberUtil.setNumberRank(accountNumberList, countList); //�����˻��н��״�������
			AccountNumberUtil.setRate(accountNumberList, map); //������õ�ӯ���ʷ����ڶ�Ӧ��ÿ���û��˻�
			GroupSysParam g = null;
			if (groupTwo.equals(""))
				g = accountNumberManageBo.groupSysParamQuery(groupOne);
			else
				g = accountNumberManageBo.groupSysParamQuery(groupTwo);
			b = a = AccountNumberUtil.initCollection(accountNumberList, g); //�˻��б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ��������������Ӧ�˻��б��±�
			AccountNumberUtil.rateRankSort(accountNumberList, b); //�����˻�ӯ��������
			AccountNumberUtil.setRateRank(accountNumberList, b); //�����˻���ӯ��������
			AccountNumberUtil.accountRankSort(accountNumberList, a); //�����˻�ֵ����
			if (rankingRange != null){
				accountNumberList = AccountNumberUtil.getOneNewListAfterSort(accountNumberList, a, userNumber);
			}
			else if (rankingType.equals("down")){
				accountNumberList = AccountNumberUtil.getNewListAfterSortByDesc(accountNumberList, a);
			}
			else
				accountNumberList = AccountNumberUtil.getNewListAfterSort(accountNumberList, a);
			requestPut("accountNumberList",accountNumberList);
		}
		requestPut("groupOneList",accountNumberManageBo.getGroupListDone(SysParamNameConst.GROUP_ONE));
		requestPut("groupOne",groupOne);
		requestPut("groupTwo",groupTwo);
		requestPut("userNumber", userNumber);
		requestPut("rankingType",rankingType);
		requestPut("rankingRange", rankingRange);
		requestPut("tradeType", tradeType);
		requestPut("tradeTypeList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.ACCOUNT_TYPE_LIST));
		return "admin";
	}
	
	/**
	 * ʵʱ����ҳ���ʼ��
	 */
	@SuppressWarnings("unchecked")
	public String pageInit(){
		requestPut("tradeTypeList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.ACCOUNT_TYPE_LIST));
		requestPut("tradeType","C");
		UserModel user = UserModelUtil.getUser();
		int[] a = null;
		int[] b = null;
		List<TranFlowMappingModel> accountNumberList = null;
		List<TranFlowMappingModel> amountList = null;
		List<TranFlowMappingModel> countList = null;
		List<AccInfoModel> accInfoList = null;
		Map<String,Double> map = null;
		
		if (user.getUserType().equals("S")){
			String group = user.getGroupOneId();
			accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupOne", group);
			if (accountNumberList.size() > 0){
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountAmountRankByGroupOne", group);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountCountRankByGroupOne", group);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", group, "C");
				AccountNumberUtil.setAmountRank(accountNumberList, amountList); //�����˻��н��׽������
				AccountNumberUtil.setNumberRank(accountNumberList, countList); //�����˻��н��״�������
				map = AccountNumberUtil.getCountRateList(accInfoList,"C"); //�����˻�ӯ����
				AccountNumberUtil.setRate(accountNumberList, map); //������õ�ӯ���ʷ����ڶ�Ӧ��ÿ���û��˻�
			//	GroupSysParam g = accountNumberManageBo.groupSysParamQuery(grFoup);
				GroupSysParam g = accountNumberManageBo.groupSysParamQuery(group);
				b = a = AccountNumberUtil.initCollection(accountNumberList, g); //�˻��б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ��������������Ӧ�˻��б��±�
				AccountNumberUtil.rateRankSort(accountNumberList, b); //�����˻�ӯ��������
				AccountNumberUtil.setRateRank(accountNumberList, b); //�����˻���ӯ��������
				AccountNumberUtil.accountRankSort(accountNumberList, a); //�����˻�ֵ����
				accountNumberList = AccountNumberUtil.getListAfterSort(accountNumberList, a, Integer.parseInt(g.getOverallRankNum()), user.getUserId());
				requestPut("accountNumberList",accountNumberList);
			}
			return "student";
		}
		else{
			List<GroupMng> groupList = (List<GroupMng>) accountNumberManageBo.getGroupListDone(SysParamNameConst.GROUP_ONE);
			if (groupList.size() > 0){
				String groupO = groupList.get(0).getGroupId();
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupOne", groupO);
				if (accountNumberList.size() > 0){
					amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountAmountRankByGroupOne", groupO);
					countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountCountRankByGroupOne", groupO);
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupO, "C");
					AccountNumberUtil.setAmountRank(accountNumberList, amountList); //�����˻��н����ܶ�����
					AccountNumberUtil.setNumberRank(accountNumberList, countList); //�����˻��н��״�������
					map = AccountNumberUtil.getCountRateList(accInfoList,"C"); //�����˻�ӯ����
					AccountNumberUtil.setRate(accountNumberList, map); //�����˻��н��״�������
					GroupSysParam g = accountNumberManageBo.groupSysParamQuery(groupO);
					b = a = AccountNumberUtil.initCollection(accountNumberList, g); //�˻��б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ��������������Ӧ�˻��б��±�
					AccountNumberUtil.rateRankSort(accountNumberList, b); //�����˻�ӯ��������
					AccountNumberUtil.setRateRank(accountNumberList, b); //�����˻���ӯ��������
					AccountNumberUtil.accountRankSort(accountNumberList, a); //�����˻�ֵ����
					accountNumberList = AccountNumberUtil.getNewListAfterSort(accountNumberList, a);
					requestPut("accountNumberList",accountNumberList);
					requestPut("groupOne",groupO);
				}
			}
			requestPut("groupOneList",groupList);
			requestPut("rankingType","up");
			return "admin";
		}
	}
	
	/**
	 * �ۺ�����ҳ���ʼ��
	 */
	@SuppressWarnings("unchecked")
	public String synthesizePageInit(){
		UserModel user = UserModelUtil.getUser();
		List<TranFlowMappingModel> synthesizeList = null;
		List<AccInfoModel> accInfoList = null;
		Map<String,Double> map = null;
		int[] a = null;
		
		if (user.getUserType().equals("S")){
			String group = user.getGroupOneId();
			GroupSysParam g = accountNumberManageBo.groupSysParamQuery(group);
			List<TranFlowMappingModel> CNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupOne", group);
			if (CNumberList.size() > 0){
				synthesizeList = CNumberList;
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", group, "C");
				map = AccountNumberUtil.getCountRateList(accInfoList,"C"); //����������˻�ӯ����
				AccountNumberUtil.setRate(CNumberList, map); //������õ�ӯ���ʷ����ڶ�Ӧ��ÿ���û��˻�
				AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'C'); //������õ�ӯ���ʷ������ۺ������б��ж�Ӧ��ÿ���û��˻�
				a = AccountNumberUtil.initCollection(CNumberList, g); //�˻��б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ����������Ӧ�˻��б��±�
				AccountNumberUtil.accountRankSort(CNumberList, a); //�����˻�ֵ����
				AccountNumberUtil.setSynthesizeAccRank(synthesizeList, CNumberList, a, 'C'); //������õ��˻�ֵ�����������ۺ������б���
			
				List<TranFlowMappingModel> WNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupOne", group);
				if (WNumberList.size() > 0){
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", group, "W");
					map = AccountNumberUtil.getCountRateList(accInfoList,"W"); //��������˻�ӯ����
					AccountNumberUtil.setRate(WNumberList, map); //������õ�ӯ���ʷ����ڶ�Ӧ��ÿ���û��˻�
					AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'W'); //������õ�ӯ���ʷ������ۺ������б��ж�Ӧ��ÿ���û��˻�
					a = AccountNumberUtil.initCollection(WNumberList, g); //�˻��б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ����������Ӧ�˻��б��±�
					AccountNumberUtil.accountRankSort(WNumberList, a); //�����˻�ֵ����
					AccountNumberUtil.setSynthesizeAccRank(synthesizeList, WNumberList, a, 'W'); //������õ��˻�ֵ�����������ۺ������б���
			
					List<TranFlowMappingModel> BNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupOne", group);
					if (BNumberList.size() > 0){
						accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", group, "B");
						map = AccountNumberUtil.getCountRateList(accInfoList,"B"); //���㱣֤���˻�ӯ����
						AccountNumberUtil.setRate(BNumberList, map); //������õ�ӯ���ʷ����ڶ�Ӧ��ÿ���û��˻�
						AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'B'); //������õ�ӯ���ʷ������ۺ������б��ж�Ӧ��ÿ���û��˻�
						a = AccountNumberUtil.initCollection(BNumberList, g); //�˻��б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ����������Ӧ�˻��б��±�
						AccountNumberUtil.accountRankSort(BNumberList, a); //�����˻�ֵ����
						AccountNumberUtil.setSynthesizeAccRank(synthesizeList, BNumberList, a, 'B'); //������õ��˻�ֵ�����������ۺ������б���
						
						a = AccountNumberUtil.initSynthesizeList(synthesizeList, g); //�˻��ۺ������б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ����������Ӧ�˻��б��±�
						AccountNumberUtil.accountRankSort(synthesizeList, a); //���˻���ֵ����
						synthesizeList = AccountNumberUtil.getListAfterSort(synthesizeList, a, Integer.parseInt(g.getOverallRankNum()), user.getUserId());
						requestPut("accountNumberList",synthesizeList);
					}
				}
			}
			return "student";
		}
		
		List<GroupMng> groupList = (List<GroupMng>) accountNumberManageBo.getGroupListDone(SysParamNameConst.GROUP_ONE);
		if (groupList.size() > 0){
			String groupO = groupList.get(0).getGroupId();
			GroupSysParam g = accountNumberManageBo.groupSysParamQuery(groupO);
			List<TranFlowMappingModel> CNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupOne", groupO);
			if (CNumberList.size() > 0){
				synthesizeList = CNumberList;
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupO, "C");
				map = AccountNumberUtil.getCountRateList(accInfoList,"C"); //����������˻�ӯ����
				AccountNumberUtil.setRate(CNumberList, map); //������õ�ӯ���ʷ����ڶ�Ӧ��ÿ���û��˻�
				AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'C'); //������õ�ӯ���ʷ������ۺ������б��ж�Ӧ��ÿ���û��˻�
				a = AccountNumberUtil.initCollection(CNumberList, g); //�˻��б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ����������Ӧ�˻��б��±�
				AccountNumberUtil.accountRankSort(CNumberList, a); //�����˻�ֵ����
				AccountNumberUtil.setSynthesizeAccRank(synthesizeList, CNumberList, a, 'C'); //������õ��˻�ֵ�����������ۺ������б���
			
				List<TranFlowMappingModel> WNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupOne", groupO);
				if (WNumberList.size() > 0){
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupO, "W");
					map = AccountNumberUtil.countRateList(accInfoList,"W"); //��������˻�ӯ����
					AccountNumberUtil.setRate(WNumberList, map); //������õ�ӯ���ʷ����ڶ�Ӧ��ÿ���û��˻�
					AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'W'); //������õ�ӯ���ʷ������ۺ������б��ж�Ӧ��ÿ���û��˻�
					a = AccountNumberUtil.initCollection(WNumberList, g); //�˻��б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ����������Ӧ�˻��б��±�
					AccountNumberUtil.accountRankSort(WNumberList, a); //�����˻�ֵ����
					AccountNumberUtil.setSynthesizeAccRank(synthesizeList, WNumberList, a, 'W'); //������õ��˻�ֵ�����������ۺ������б���
			
					List<TranFlowMappingModel> BNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupOne", groupO);
					if (BNumberList.size() > 0){
						accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupO, "B");
						map = AccountNumberUtil.getCountRateList(accInfoList,"B"); //���㱣֤���˻�ӯ����
						AccountNumberUtil.setRate(BNumberList, map); //������õ�ӯ���ʷ����ڶ�Ӧ��ÿ���û��˻�
						AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'B'); //������õ�ӯ���ʷ������ۺ������б��ж�Ӧ��ÿ���û��˻�
						a = AccountNumberUtil.initCollection(BNumberList, g); //�˻��б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ����������Ӧ�˻��б��±�
						AccountNumberUtil.accountRankSort(BNumberList, a); //�����˻�ֵ����
						AccountNumberUtil.setSynthesizeAccRank(synthesizeList, BNumberList, a, 'B'); //������õ��˻�ֵ�����������ۺ������б���
						
						a = AccountNumberUtil.initSynthesizeList(synthesizeList, g); //�˻��ۺ������б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ����������Ӧ�˻��б��±�
						AccountNumberUtil.accountRankSort(synthesizeList, a); //���˻���ֵ����
						synthesizeList = AccountNumberUtil.getNewListAfterSort(synthesizeList, a);
						requestPut("accountNumberList",synthesizeList);
					}
				}
			}
			requestPut("groupOne",groupO);
		}
		requestPut("groupOneList",groupList);
		requestPut("rankingType","up");
		return "admin";
	}
	
	/**
	 * ������Ӧ���������ۺ�������ѯ
	 */
	@SuppressWarnings("unchecked")
	public String synthesizeRankSearch(){
		List<TranFlowMappingModel> synthesizeList = null;
		List<TranFlowMappingModel> CNumberList = null;
		List<TranFlowMappingModel> WNumberList = null;
		List<TranFlowMappingModel> BNumberList = null;
		List<AccInfoModel> CAccInfoList = null;
		List<AccInfoModel> WAccInfoList = null;
		List<AccInfoModel> BAccInfoList = null;
		Map<String,Double> map = null;
		int[] a = null;
		if (rankingRange != null){ //�ж��Ƿ��ǲ�ѯ����ѧ�����˻���Ϣ������ѯһ���������������������
			if (rankingRange.equals("One")){ //һ����������
				CNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupOne", groupOne);
				CAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "C");
				WNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupOne", groupOne);
				WAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "W");
				BNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupOne", groupOne);
				BAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "B");
			}
			else{ //������������
				CNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupTwo", groupTwo);
				CAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "C");
				WNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupTwo", groupTwo);
				WAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "W");
				BNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupTwo", groupTwo);
				BAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "B");
			}
		}
		else if (!groupTwo.equals("")){ //�鿴һ���������ڳ�Ա�������
			CNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupTwo", groupTwo);
			CAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "C");
			WNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupTwo", groupTwo);
			WAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "W");
			BNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupTwo", groupTwo);
			BAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "B");
		}
		else{ //�鿴һ��һ�������������
			CNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupOne", groupOne);
			CAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "C");
			WNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupOne", groupOne);
			WAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "W");
			BNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupOne", groupOne);
			BAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "B");
		}
		synthesizeList = CNumberList;
		if (synthesizeList.size() > 0){
			GroupSysParam g = null;
			if (groupTwo.equals(""))
				g = accountNumberManageBo.groupSysParamQuery(groupOne);
			else
				g = accountNumberManageBo.groupSysParamQuery(groupTwo);
			map = AccountNumberUtil.getCountRateList(CAccInfoList,"C"); //����������˻�ӯ����
			AccountNumberUtil.setRate(CNumberList, map); //������õ�ӯ���ʷ����ڶ�Ӧ��ÿ���û��˻�
			AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'C'); //������õ�ӯ���ʷ������ۺ������б��ж�Ӧ��ÿ���û��˻�
			a = AccountNumberUtil.initCollection(CNumberList, g); //�˻��б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ����������Ӧ�˻��б��±�
			AccountNumberUtil.accountRankSort(CNumberList, a); //�����˻�ֵ����
			AccountNumberUtil.setSynthesizeAccRank(synthesizeList, CNumberList, a, 'C'); //������õ��˻�ֵ�����������ۺ������б���
			
			map = AccountNumberUtil.getCountRateList(WAccInfoList,"W"); //��������˻�ӯ����
			AccountNumberUtil.setRate(WNumberList, map); //������õ�ӯ���ʷ����ڶ�Ӧ��ÿ���û��˻�
			AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'W'); //������õ�ӯ���ʷ������ۺ������б��ж�Ӧ��ÿ���û��˻�
			a = AccountNumberUtil.initCollection(WNumberList, g); //�˻��б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ����������Ӧ�˻��б��±�
			AccountNumberUtil.accountRankSort(WNumberList, a); //�����˻�ֵ����
			AccountNumberUtil.setSynthesizeAccRank(synthesizeList, WNumberList, a, 'W'); //������õ��˻�ֵ�����������ۺ������б���
			
			map = AccountNumberUtil.getCountRateList(BAccInfoList,"B"); //���㱣֤���˻�ӯ����
			AccountNumberUtil.setRate(BNumberList, map); //������õ�ӯ���ʷ����ڶ�Ӧ��ÿ���û��˻�
			AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'B'); //������õ�ӯ���ʷ������ۺ������б��ж�Ӧ��ÿ���û��˻�
			a = AccountNumberUtil.initCollection(BNumberList, g); //�˻��б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ����������Ӧ�˻��б��±�
			AccountNumberUtil.accountRankSort(BNumberList, a); //�����˻�ֵ����
			AccountNumberUtil.setSynthesizeAccRank(synthesizeList, BNumberList, a, 'B'); //������õ��˻�ֵ�����������ۺ������б���
			
			a = AccountNumberUtil.initSynthesizeList(synthesizeList, g); //�˻��ۺ������б�����ǰ׼�������ݸ���Ȩֵ������˻�ֵ���õ����������Ӧ�˻��б��±�
			AccountNumberUtil.accountRankSort(synthesizeList, a); //���˻���ֵ����
			if (rankingRange != null){
				synthesizeList = AccountNumberUtil.getOneNewListAfterSort(synthesizeList, a, userNumber);
			}
			else if (rankingType.equals("down")){
				synthesizeList = AccountNumberUtil.getNewListAfterSortByDesc(synthesizeList, a);
			}
			else
				synthesizeList = AccountNumberUtil.getNewListAfterSort(synthesizeList, a);
		}
		requestPut("accountNumberList",synthesizeList);
		requestPut("groupOneList",accountNumberManageBo.getGroupListDone(SysParamNameConst.GROUP_ONE));
		requestPut("groupOne",groupOne);
		requestPut("groupTwo",groupTwo);
		requestPut("userNumber", userNumber);
		requestPut("rankingType",rankingType);
		requestPut("rankingRange", rankingRange);
		return "admin";
	}
	
	/**
	 * ajaxִ�е�action��������ҳ��ת��
	 * ����һ������Ų�ѯ��Ӧ�Ķ�������ʾ��ҳ���������
	 */
	public void getGroupTwoList(){
		String dat=accountNumberManageBo.getGroupTwoDone(groupOne);
		processText(dat,"text/plain;charset=GBK");
	}
	/**
	 * ajaxִ�е�action��������ҳ��ת��
	 * ���ݶ�����Ų�ѯ��Ӧ��ѧ����ʾ��ҳ���������
	 */
	public void getStudentList(){
		String dat1 = accountNumberManageBo.getStudentDone(groupTwo);
		processText(dat1, "text/plain;charset=GBK");
	}
	/**
	 * ajaxִ�е�action��������ҳ��ת��
	 * ����һ����Ų�ѯ��Ӧ��ѧ����ʾ��ҳ���������
	 */
	public void getStudentListByGroupOne(){
		String dat2 = accountNumberManageBo.getStudentQuery(groupOne);
		processText(dat2, "text/plain;charset=GBK");
	}
	
	public AccountNumberManageBo getAccountNumberManageBo() {
		return accountNumberManageBo;
	}

	public void setAccountNumberManageBo(AccountNumberManageBo accountNumberManageBo) {
		this.accountNumberManageBo = accountNumberManageBo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	public String getGroupOne() {
		return groupOne;
	}
	
	public void setGroupOne(String groupOne) {
		this.groupOne = groupOne;
	}
	
	public String getGroupTwo() {
		return groupTwo;
	}
	
	public void setGroupTwo(String groupTwo) {
		this.groupTwo = groupTwo;
	}

	public String getRankingType() {
		return rankingType;
	}

	public void setRankingType(String rankingType) {
		this.rankingType = rankingType;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getRankingRange() {
		return rankingRange;
	}

	public void setRankingRange(String rankingRange) {
		this.rankingRange = rankingRange;
	}
	
}
