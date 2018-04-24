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
 * Amendment No.: FOEXAS017、FOEXAS018、FOEXAS020
 * Create By    : lzc
 * Description  : 账户排名管理
 * Modify Date  : 2014-08-05
 */
public class AccountNumberAction extends ForexBaseAction {
	
	private String tradeType;//账户类型
	private String groupOne;//一级组
	private String groupTwo;//二级组
	private String rankingType;//升序或降序
	private String userNumber;//交易员编号
	private String rankingRange;//排名范围
	
	private AccountNumberManageBo accountNumberManageBo;
	
	/**
	 * 根据相应条件进行实时排名查询
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
			
			if (tradeType.equals("C")){//查看账户类型为人民币账户
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupOne", group);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountAmountRankByGroupOne", group);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountCountRankByGroupOne", group);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", group, "C");
				map = AccountNumberUtil.getCountRateList(accInfoList,"C"); //计算账户盈亏率
			}
			else if (tradeType.equals("W")){//查看账户类型为外币对账户
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupOne", group);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountAmountRankByGroupOne", group);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountCountRankByGroupOne", group);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", group, "W");
				map = AccountNumberUtil.getCountRateList(accInfoList,"W"); //计算账户盈亏率
			}
			else{//查看账户类型为保证金账户
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupOne", group);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountAmountRankByGroupOne", group);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountCountRankByGroupOne", group);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", group, "B");
				map = AccountNumberUtil.getCountRateList(accInfoList,"B"); //计算账户盈亏率
			}
			if (accountNumberList.size() > 0){
				AccountNumberUtil.setAmountRank(accountNumberList, amountList); //计算账户中交易总额排名
				AccountNumberUtil.setNumberRank(accountNumberList, countList); //计算账户中交易次数排名
				AccountNumberUtil.setRate(accountNumberList, map); //将计算好的盈亏率放置在对应的每个用户账户
				GroupSysParam g = accountNumberManageBo.groupSysParamQuery(group);
				b = a = AccountNumberUtil.initCollection(accountNumberList, g); //账户列表排序前准备，根据各项权值计算好账户值，得到两个整型数组对应账户列表下标
				AccountNumberUtil.rateRankSort(accountNumberList, b); //根据账户盈亏率排序
				AccountNumberUtil.setRateRank(accountNumberList, b); //计算账户中盈亏率排名
				AccountNumberUtil.accountRankSort(accountNumberList, a); //计算账户值排名
				accountNumberList = AccountNumberUtil.getListAfterSort(accountNumberList, a, Integer.parseInt(g.getOverallRankNum()), user.getUserId());
				requestPut("accountNumberList",accountNumberList);
			}
			requestPut("tradeTypeList",CodeTableUtil.getInstance().getCodeList(SysParamNameConst.ACCOUNT_TYPE_LIST));
			requestPut("tradeType",tradeType);
			return "student";
		}
		
		if (tradeType.equals("C")){//查看账户类型为人民币账户
			if (rankingRange != null){ //判断是否是查询单个学生的账户信息，并查询一级组排名或二级组内排名
				if (rankingRange.equals("One")){ //一级组内排名
					accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupOne", groupOne);
					amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountAmountRankByGroupOne", groupOne);
					countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountCountRankByGroupOne", groupOne);
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "C");
				}
				else{ //二级组内排名
					accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupTwo", groupTwo);
					amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountAmountRankByGroupTwo", groupTwo);
					countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountCountRankByGroupTwo", groupTwo);
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "C");
				}
			}
			else if (!groupTwo.equals("")){ //查看一个二级组内成员排名情况
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupTwo", groupTwo);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountAmountRankByGroupTwo", groupTwo);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountCountRankByGroupTwo", groupTwo);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "C");
			}
			else{ //查看一个一级组内排名情况
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupOne", groupOne);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountAmountRankByGroupOne", groupOne);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getCAccountCountRankByGroupOne", groupOne);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "C");
			}
			map = AccountNumberUtil.getCountRateList(accInfoList,"C"); //计算账户盈亏率
		}
		else if (tradeType.equals("W")){//查看账户类型为外币对账户
			if (rankingRange != null){ //判断是否是查询单个学生的账户信息，并查询一级组排名或二级组内排名
				if (rankingRange.equals("One")){ //一级组内排名
					accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupOne", groupOne);
					amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountAmountRankByGroupOne", groupOne);
					countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountCountRankByGroupOne", groupOne);
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "W");
				}
				else{ //二级组内排名
					accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupTwo", groupTwo);
					amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountAmountRankByGroupTwo", groupTwo);
					countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountCountRankByGroupTwo", groupTwo);
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "W");
				}
			}
			else if (!groupTwo.equals("")){ //查看一个二级组内成员排名情况
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupTwo", groupTwo);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountAmountRankByGroupTwo", groupTwo);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountCountRankByGroupTwo", groupTwo);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "W");
			}
			else{ //查看一个一级组内排名情况
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupOne", groupOne);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountAmountRankByGroupOne", groupOne);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getWAccountCountRankByGroupOne", groupOne);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "W");
			}
			map = AccountNumberUtil.countRateList(accInfoList,"W"); //计算账户盈亏率
		}
		else{//查看账户类型为保证金账户
			if (rankingRange != null){ //判断是否是查询单个学生的账户信息，并查询一级组排名或二级组内排名
				if (rankingRange.equals("One")){ //一级组内排名
					accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupOne", groupOne);
					amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountAmountRankByGroupOne", groupOne);
					countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountCountRankByGroupOne", groupOne);
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "B");
				}
				else{ //二级组内排名
					accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupTwo", groupTwo);
					amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountAmountRankByGroupTwo", groupTwo);
					countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountCountRankByGroupTwo", groupTwo);
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "B");
				}
			}
			else if (!groupTwo.equals("")){ //查看一个二级组内成员排名情况
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupTwo", groupTwo);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountAmountRankByGroupTwo", groupTwo);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountCountRankByGroupTwo", groupTwo);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "B");
			}
			else{ //查看一个一级组内排名情况
				accountNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupOne", groupOne);
				amountList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountAmountRankByGroupOne", groupOne);
				countList = (List<TranFlowMappingModel>) accountNumberManageBo.accountNumberQuery("getBAccountCountRankByGroupOne", groupOne);
				accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "B");
			}
			map = AccountNumberUtil.countRateList(accInfoList,"B"); //计算账户盈亏率
		}
		if (accountNumberList.size() > 0){
			AccountNumberUtil.setAmountRank(accountNumberList, amountList); //计算账户中交易总额排名
			AccountNumberUtil.setNumberRank(accountNumberList, countList); //计算账户中交易次数排名
			AccountNumberUtil.setRate(accountNumberList, map); //将计算好的盈亏率放置在对应的每个用户账户
			GroupSysParam g = null;
			if (groupTwo.equals(""))
				g = accountNumberManageBo.groupSysParamQuery(groupOne);
			else
				g = accountNumberManageBo.groupSysParamQuery(groupTwo);
			b = a = AccountNumberUtil.initCollection(accountNumberList, g); //账户列表排序前准备，根据各项权值计算好账户值，得到两个整型数组对应账户列表下标
			AccountNumberUtil.rateRankSort(accountNumberList, b); //根据账户盈亏率排序
			AccountNumberUtil.setRateRank(accountNumberList, b); //计算账户中盈亏率排名
			AccountNumberUtil.accountRankSort(accountNumberList, a); //计算账户值排名
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
	 * 实时排名页面初始化
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
				AccountNumberUtil.setAmountRank(accountNumberList, amountList); //计算账户中交易金额排名
				AccountNumberUtil.setNumberRank(accountNumberList, countList); //计算账户中交易次数排名
				map = AccountNumberUtil.getCountRateList(accInfoList,"C"); //计算账户盈亏率
				AccountNumberUtil.setRate(accountNumberList, map); //将计算好的盈亏率放置在对应的每个用户账户
			//	GroupSysParam g = accountNumberManageBo.groupSysParamQuery(grFoup);
				GroupSysParam g = accountNumberManageBo.groupSysParamQuery(group);
				b = a = AccountNumberUtil.initCollection(accountNumberList, g); //账户列表排序前准备，根据各项权值计算好账户值，得到两个整型数组对应账户列表下标
				AccountNumberUtil.rateRankSort(accountNumberList, b); //根据账户盈亏率排序
				AccountNumberUtil.setRateRank(accountNumberList, b); //计算账户中盈亏率排名
				AccountNumberUtil.accountRankSort(accountNumberList, a); //计算账户值排名
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
					AccountNumberUtil.setAmountRank(accountNumberList, amountList); //计算账户中交易总额排名
					AccountNumberUtil.setNumberRank(accountNumberList, countList); //计算账户中交易次数排名
					map = AccountNumberUtil.getCountRateList(accInfoList,"C"); //计算账户盈亏率
					AccountNumberUtil.setRate(accountNumberList, map); //计算账户中交易次数排名
					GroupSysParam g = accountNumberManageBo.groupSysParamQuery(groupO);
					b = a = AccountNumberUtil.initCollection(accountNumberList, g); //账户列表排序前准备，根据各项权值计算好账户值，得到两个整型数组对应账户列表下标
					AccountNumberUtil.rateRankSort(accountNumberList, b); //根据账户盈亏率排序
					AccountNumberUtil.setRateRank(accountNumberList, b); //计算账户中盈亏率排名
					AccountNumberUtil.accountRankSort(accountNumberList, a); //计算账户值排名
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
	 * 综合排名页面初始化
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
				map = AccountNumberUtil.getCountRateList(accInfoList,"C"); //计算人民币账户盈亏率
				AccountNumberUtil.setRate(CNumberList, map); //将计算好的盈亏率放置在对应的每个用户账户
				AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'C'); //将计算好的盈亏率放置在综合排名列表中对应的每个用户账户
				a = AccountNumberUtil.initCollection(CNumberList, g); //账户列表排序前准备，根据各项权值计算好账户值，得到整型数组对应账户列表下标
				AccountNumberUtil.accountRankSort(CNumberList, a); //计算账户值排名
				AccountNumberUtil.setSynthesizeAccRank(synthesizeList, CNumberList, a, 'C'); //将计算好的账户值排名保存在综合排名列表中
			
				List<TranFlowMappingModel> WNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupOne", group);
				if (WNumberList.size() > 0){
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", group, "W");
					map = AccountNumberUtil.getCountRateList(accInfoList,"W"); //计算外币账户盈亏率
					AccountNumberUtil.setRate(WNumberList, map); //将计算好的盈亏率放置在对应的每个用户账户
					AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'W'); //将计算好的盈亏率放置在综合排名列表中对应的每个用户账户
					a = AccountNumberUtil.initCollection(WNumberList, g); //账户列表排序前准备，根据各项权值计算好账户值，得到整型数组对应账户列表下标
					AccountNumberUtil.accountRankSort(WNumberList, a); //计算账户值排名
					AccountNumberUtil.setSynthesizeAccRank(synthesizeList, WNumberList, a, 'W'); //将计算好的账户值排名保存在综合排名列表中
			
					List<TranFlowMappingModel> BNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupOne", group);
					if (BNumberList.size() > 0){
						accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", group, "B");
						map = AccountNumberUtil.getCountRateList(accInfoList,"B"); //计算保证金账户盈亏率
						AccountNumberUtil.setRate(BNumberList, map); //将计算好的盈亏率放置在对应的每个用户账户
						AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'B'); //将计算好的盈亏率放置在综合排名列表中对应的每个用户账户
						a = AccountNumberUtil.initCollection(BNumberList, g); //账户列表排序前准备，根据各项权值计算好账户值，得到整型数组对应账户列表下标
						AccountNumberUtil.accountRankSort(BNumberList, a); //计算账户值排名
						AccountNumberUtil.setSynthesizeAccRank(synthesizeList, BNumberList, a, 'B'); //将计算好的账户值排名保存在综合排名列表中
						
						a = AccountNumberUtil.initSynthesizeList(synthesizeList, g); //账户综合排名列表排序前准备，根据各项权值计算好账户值，得到整型数组对应账户列表下标
						AccountNumberUtil.accountRankSort(synthesizeList, a); //将账户总值排序
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
				map = AccountNumberUtil.getCountRateList(accInfoList,"C"); //计算人民币账户盈亏率
				AccountNumberUtil.setRate(CNumberList, map); //将计算好的盈亏率放置在对应的每个用户账户
				AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'C'); //将计算好的盈亏率放置在综合排名列表中对应的每个用户账户
				a = AccountNumberUtil.initCollection(CNumberList, g); //账户列表排序前准备，根据各项权值计算好账户值，得到整型数组对应账户列表下标
				AccountNumberUtil.accountRankSort(CNumberList, a); //计算账户值排名
				AccountNumberUtil.setSynthesizeAccRank(synthesizeList, CNumberList, a, 'C'); //将计算好的账户值排名保存在综合排名列表中
			
				List<TranFlowMappingModel> WNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupOne", groupO);
				if (WNumberList.size() > 0){
					accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupO, "W");
					map = AccountNumberUtil.countRateList(accInfoList,"W"); //计算外币账户盈亏率
					AccountNumberUtil.setRate(WNumberList, map); //将计算好的盈亏率放置在对应的每个用户账户
					AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'W'); //将计算好的盈亏率放置在综合排名列表中对应的每个用户账户
					a = AccountNumberUtil.initCollection(WNumberList, g); //账户列表排序前准备，根据各项权值计算好账户值，得到整型数组对应账户列表下标
					AccountNumberUtil.accountRankSort(WNumberList, a); //计算账户值排名
					AccountNumberUtil.setSynthesizeAccRank(synthesizeList, WNumberList, a, 'W'); //将计算好的账户值排名保存在综合排名列表中
			
					List<TranFlowMappingModel> BNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupOne", groupO);
					if (BNumberList.size() > 0){
						accInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupO, "B");
						map = AccountNumberUtil.getCountRateList(accInfoList,"B"); //计算保证金账户盈亏率
						AccountNumberUtil.setRate(BNumberList, map); //将计算好的盈亏率放置在对应的每个用户账户
						AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'B'); //将计算好的盈亏率放置在综合排名列表中对应的每个用户账户
						a = AccountNumberUtil.initCollection(BNumberList, g); //账户列表排序前准备，根据各项权值计算好账户值，得到整型数组对应账户列表下标
						AccountNumberUtil.accountRankSort(BNumberList, a); //计算账户值排名
						AccountNumberUtil.setSynthesizeAccRank(synthesizeList, BNumberList, a, 'B'); //将计算好的账户值排名保存在综合排名列表中
						
						a = AccountNumberUtil.initSynthesizeList(synthesizeList, g); //账户综合排名列表排序前准备，根据各项权值计算好账户值，得到整型数组对应账户列表下标
						AccountNumberUtil.accountRankSort(synthesizeList, a); //将账户总值排序
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
	 * 根据相应条件进行综合排名查询
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
		if (rankingRange != null){ //判断是否是查询单个学生的账户信息，并查询一级组排名或二级组内排名
			if (rankingRange.equals("One")){ //一级组内排名
				CNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupOne", groupOne);
				CAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "C");
				WNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupOne", groupOne);
				WAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "W");
				BNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupOne", groupOne);
				BAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupOne", groupOne, "B");
			}
			else{ //二级组内排名
				CNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupTwo", groupTwo);
				CAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "C");
				WNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupTwo", groupTwo);
				WAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "W");
				BNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupTwo", groupTwo);
				BAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "B");
			}
		}
		else if (!groupTwo.equals("")){ //查看一个二级组内成员排名情况
			CNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getCTranFlowListByGroupTwo", groupTwo);
			CAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "C");
			WNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getWTranFlowListByGroupTwo", groupTwo);
			WAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "W");
			BNumberList = (List<TranFlowMappingModel>)accountNumberManageBo.accountNumberQuery("getBTranFlowListByGroupTwo", groupTwo);
			BAccInfoList = (List<AccInfoModel>) accountNumberManageBo.accInfoListQuery("getAccInfoListByGroupTwo", groupTwo, "B");
		}
		else{ //查看一个一级组内排名情况
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
			map = AccountNumberUtil.getCountRateList(CAccInfoList,"C"); //计算人民币账户盈亏率
			AccountNumberUtil.setRate(CNumberList, map); //将计算好的盈亏率放置在对应的每个用户账户
			AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'C'); //将计算好的盈亏率放置在综合排名列表中对应的每个用户账户
			a = AccountNumberUtil.initCollection(CNumberList, g); //账户列表排序前准备，根据各项权值计算好账户值，得到整型数组对应账户列表下标
			AccountNumberUtil.accountRankSort(CNumberList, a); //计算账户值排名
			AccountNumberUtil.setSynthesizeAccRank(synthesizeList, CNumberList, a, 'C'); //将计算好的账户值排名保存在综合排名列表中
			
			map = AccountNumberUtil.getCountRateList(WAccInfoList,"W"); //计算外币账户盈亏率
			AccountNumberUtil.setRate(WNumberList, map); //将计算好的盈亏率放置在对应的每个用户账户
			AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'W'); //将计算好的盈亏率放置在综合排名列表中对应的每个用户账户
			a = AccountNumberUtil.initCollection(WNumberList, g); //账户列表排序前准备，根据各项权值计算好账户值，得到整型数组对应账户列表下标
			AccountNumberUtil.accountRankSort(WNumberList, a); //计算账户值排名
			AccountNumberUtil.setSynthesizeAccRank(synthesizeList, WNumberList, a, 'W'); //将计算好的账户值排名保存在综合排名列表中
			
			map = AccountNumberUtil.getCountRateList(BAccInfoList,"B"); //计算保证金账户盈亏率
			AccountNumberUtil.setRate(BNumberList, map); //将计算好的盈亏率放置在对应的每个用户账户
			AccountNumberUtil.setSynthesizeRate(synthesizeList, map, 'B'); //将计算好的盈亏率放置在综合排名列表中对应的每个用户账户
			a = AccountNumberUtil.initCollection(BNumberList, g); //账户列表排序前准备，根据各项权值计算好账户值，得到整型数组对应账户列表下标
			AccountNumberUtil.accountRankSort(BNumberList, a); //计算账户值排名
			AccountNumberUtil.setSynthesizeAccRank(synthesizeList, BNumberList, a, 'B'); //将计算好的账户值排名保存在综合排名列表中
			
			a = AccountNumberUtil.initSynthesizeList(synthesizeList, g); //账户综合排名列表排序前准备，根据各项权值计算好账户值，得到整型数组对应账户列表下标
			AccountNumberUtil.accountRankSort(synthesizeList, a); //将账户总值排序
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
	 * ajax执行的action，不进行页面转发
	 * 根据一级组组号查询对应的二级组显示到页面的下拉框
	 */
	public void getGroupTwoList(){
		String dat=accountNumberManageBo.getGroupTwoDone(groupOne);
		processText(dat,"text/plain;charset=GBK");
	}
	/**
	 * ajax执行的action，不进行页面转发
	 * 根据二级组号查询对应的学生显示到页面的下拉框
	 */
	public void getStudentList(){
		String dat1 = accountNumberManageBo.getStudentDone(groupTwo);
		processText(dat1, "text/plain;charset=GBK");
	}
	/**
	 * ajax执行的action，不进行页面转发
	 * 根据一级组号查询对应的学生显示到页面的下拉框
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
