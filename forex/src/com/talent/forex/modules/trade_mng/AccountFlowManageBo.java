package com.talent.forex.modules.trade_mng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.talent.auth.bean.domain.Users;
import com.talent.auth.dao.UsersDao;
import com.talent.base.BaseBo;
import com.talent.forex.bean.domain.BTranFlowMapping;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.GroupMng;
import com.talent.forex.bean.domain.OtcSwapInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.bean.model.TradeRecordModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.dao.BTranFlowMappingDao;
import com.talent.forex.dao.CNYTradeDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.CompletePriceTranInfoDao;
import com.talent.forex.dao.GroupMngDao;
import com.talent.forex.dao.OtcSwapDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetFixWordUtil;
/*
 * Amendment No.: FOEXAS015
 * Create By    : lzc
 * Description  : �˻�������ˮͳ��
 * Modify Date  : 2014-08-05
 */
public class AccountFlowManageBo extends BaseBo {

	private CompletePriceTranInfoDao completePriceTranInfoDao;
	private GroupMngDao groupMngDao;
	private UsersDao usersDao;
	private BTranFlowMappingDao bTranFlowMappingDao;//��֤����
	private WTranFlowMappingDao wTranFlowMappingDao;//��ҽ���
	private CTranFlowMappingDao cTranFlowMappingDao;//����ҽ���
	private OtcSwapDao otcSwapDao;
	
	private CNYTradeDao CNYDao;
	
	/**
	 * �����û����ͽ������ͻ�ø��û����еĽ��׼�¼
	 */
	public Collection<?> accountFlowAllTradeRecordListQuery(String hql, String userNum, String tranType){
		ArrayList<String> paraList = new ArrayList<String>();
		if (tranType.equals(""))
			tranType = GetFixWordUtil.getLikeWords(tranType);
		paraList.add(userNum);
		paraList.add(tranType);
		Collection<TradeRecordModel> c = (Collection<TradeRecordModel>) completePriceTranInfoDao.getBeansByParams(hql, paraList);
		if(!tranType.equals("B")){
			List<OtcSwapInfo> list = otcSwapDao.getBeansByUserNum(userNum);
			if(list.size() > 0){
				for(OtcSwapInfo l : list){
					TradeRecordModel tr = new TradeRecordModel();
					tr.setId(l.getId());
					tr.setTradeNo(l.getTranNo());
					tr.setTableName("OtcSwapInfo");
					tr.setTradeType("����ѯ�۽���");
					tr.setCurrencyBuy(l.getAnaCcy());
					tr.setCurrencySell(l.getWeCcy());
					tr.setTradeSum(l.getCAmount());
					double price = Double.parseDouble(l.getPrice()) + Double.parseDouble(l.getPoint()) / 10000;
					tr.setTradePrice(FormatParamUtil.getAmountAndPriceFmt(String.valueOf(price)));
					tr.setTradeDate(l.getMaturityDate());
					tr.setTradeStatus(l.getStatue());
					tr.setProvider(l.getProvider());
					c.add(tr);
				}
			}
		}
		/*ArrayList<String> paraList2 = new ArrayList<String>();
		paraList2.add(userNum);
		paraList2.add(GetFixWordUtil.getLikeWords(""));
		paraList2.add(tranType);
		Collection cc = otcSwapDao.getBeansByParams("getOtcSwapListByUserNum", paraList2);
		Iterator it = cc.iterator();
		while(it.hasNext()){
			c.add(it.next());
		}*/
		return c;
	}
	
	/**
	 * ����ѧ��ѧ�Ų�ѯ���״����ͽ��׽��
	 * ���ݲ�ͬ�Ľ�������,���ò�ͬ��dao��ѯ��Ӧ�ı�
	 * @param userNum
	 * @return
	 */
	public Collection<?> tradeRecordByUserNumDone(String userNum,String tradeType){
		if(tradeType.equals("B")){
			BTranFlowMapping btfm = new BTranFlowMapping();
			btfm.setUserNum(userNum);
			return bTranFlowMappingDao.getBeansByBean(btfm, MatchMode.EXACT); 
		}else if(tradeType.equals("W")){
			WTranFlowMapping wtfm = new WTranFlowMapping();
			wtfm.setUserNum(userNum);
			return wTranFlowMappingDao.getBeansByBean(wtfm, MatchMode.EXACT); 
		}else{
			//(tradeType.equals("C"))
			CTranFlowMapping ctfm = new CTranFlowMapping();
			ctfm.setUserNum(userNum);
			return cTranFlowMappingDao.getBeansByBean(ctfm, MatchMode.EXACT); 
		}
	}
	
	
	/**
	 * ���ݰ༶�Ż���˵��һ�����ѯ��Ӧѧ���Ľ��״����ͽ��׽��
	 * ���ݲ�ͬ�Ľ�������,���ò�ͬ��dao��ѯ��Ӧ�ı�
	 * @param classNo
	 * @return
	 */
	public Collection<?> tradeRecordByClassNoQuery(String classNo,String tradeType){
		Collection returnList = new ArrayList();
		Users users = new Users();
		users.setGroupOne(classNo);
		ArrayList<?> usersList = (ArrayList<?>) usersDao.getBeansByBean(users, MatchMode.ANYWHERE);
		
		if(tradeType.equals("B")){
			for(int i=0; i<usersList.size(); i++){
				BTranFlowMapping btfm = new BTranFlowMapping();
				btfm.setUserNum(((Users)usersList.get(i)).getUserNum());
				returnList.addAll(bTranFlowMappingDao.getBeansByBean(btfm, MatchMode.EXACT));
			}
		}else if(tradeType.equals("W")){
			for(int i=0; i<usersList.size(); i++){
				WTranFlowMapping wtfm = new WTranFlowMapping();
				wtfm.setUserNum(((Users)usersList.get(i)).getUserNum());
				returnList.addAll(wTranFlowMappingDao.getBeansByBean(wtfm, MatchMode.EXACT));
			}
		}else if(tradeType.equals("C")){
			for(int i=0; i<usersList.size(); i++){
				CTranFlowMapping ctfm = new CTranFlowMapping();
				ctfm.setUserNum(((Users)usersList.get(i)).getUserNum());
				returnList.addAll(cTranFlowMappingDao.getBeansByBean(ctfm, MatchMode.EXACT));
			}
		}
		return returnList;
	}
	
	
	/**
	 * ͨ��һ����Ͷ������ѯѧ��
	 * ���ݲ�ͬ�Ľ�������,���ò�ͬ��dao��ѯ��Ӧ�ı�
	 */
	public Collection getStudentByGroupOneTwoQuery(String groupOne,String groupTwo,String tradeType){
		Collection returnList = new ArrayList();
		Users users = new Users();
		users.setGroupOne(groupOne);
		users.setGroupTwo(groupTwo);
		ArrayList<?> usersList = (ArrayList<?>) usersDao.getBeansByBean(users, MatchMode.ANYWHERE);
		
		if(tradeType.equals("B")){
			for(int i=0; i<usersList.size(); i++){
				BTranFlowMapping btfm = new BTranFlowMapping();
				btfm.setUserNum(((Users)usersList.get(i)).getUserNum());
				returnList.addAll(bTranFlowMappingDao.getBeansByBean(btfm, MatchMode.ANYWHERE));
			}
		}else if(tradeType.equals("W")){
			for(int i=0; i<usersList.size(); i++){
				WTranFlowMapping wtfm = new WTranFlowMapping();
				wtfm.setUserNum(((Users)usersList.get(i)).getUserNum());
				returnList.addAll(wTranFlowMappingDao.getBeansByBean(wtfm, MatchMode.ANYWHERE));
			}
		}else if(tradeType.equals("C")){
			for(int i=0; i<usersList.size(); i++){
				CTranFlowMapping ctfm = new CTranFlowMapping();
				ctfm.setUserNum(((Users)usersList.get(i)).getUserNum());
				returnList.addAll(cTranFlowMappingDao.getBeansByBean(ctfm, MatchMode.ANYWHERE));
			}
		}

		return returnList;
		
	}
	
	
	/**ajax�ύ��action
	 * ͨ��һ�����ѯ��ض�����
	 * @param groupOne
	 * @return
	 */
	public String getGroupTwoDone(String groupOne){
		String groupTwos="";
		GroupMng groupMng = new GroupMng();
		groupMng.setParentGroup(groupOne);
		groupMng.setGroupType(SysParamNameConst.GROUP_TWO);
		List<GroupMng> groupMngList = (List<GroupMng>)groupMngDao.getBeansByBean(groupMng, MatchMode.ANYWHERE);
		if(groupMngList!=null){
			for(int i=0;i<groupMngList.size();i++){
				groupTwos += groupMngList.get(i).getGroupId()+"$$";
			}
			groupTwos = groupTwos.substring(0, groupTwos.length()-2);
		}
		return groupTwos;
	}
	
	/**ajax�ύ��action
	 * ͨ���������ѯ��ص�ѧ��
	 */
	public String getStudentDone(String groupTwo){
		String student = "";
		Users user = new Users();
		user.setGroupTwo(groupTwo);
		List<Users> studentList = (List<Users>)usersDao.getBeansByBean(user, MatchMode.ANYWHERE);
		if(studentList!=null){
			for(int i=0;i<studentList.size();i++){
				student += studentList.get(i).getUserNum()+"$$";
			}
			student = student.substring(0, student.length()-2);
		}		
		return student;
	}
	
	
	/**ajax�ύ��action
	 * ͨ��һ�����ѯ��ص�ѧ��
	 */
	public String getStudentQuery(String groupOne){
		String student = "";
		Users user = new Users();
		user.setGroupOne(groupOne);
		List<Users> studentList = (List<Users>)usersDao.getBeansByBean(user, MatchMode.ANYWHERE);
		if(studentList!=null){
			for(int i=0;i<studentList.size();i++){
				student += studentList.get(i).getUserNum()+"$$";
			}
			student = student.substring(0, student.length()-2);
		}		
		return student;
	}
	
	/**
	 * ������ϸ��Ϣ��ѯ
	 */
	public Collection tradeDetailQuery(String hql, String tranNo){
		ArrayList<String> paraList = new ArrayList<String>();
		paraList.add(tranNo);
		return CNYDao.getBeansByParams(hql, paraList);
	}
	
	
	/**
	 * ��ȡ����б�
	 * @param groupType
	 * @return
	 */
	public Collection getGroupListDone(String groupType){
		GroupMng groupMng = new GroupMng();
		if(SysParamNameConst.GROUP_ONE.equals(groupType)){
			groupMng.setGroupType(SysParamNameConst.GROUP_ONE);
			return groupMngDao.getBeansByBean(groupMng, MatchMode.ANYWHERE);
		}
		else if(SysParamNameConst.GROUP_TWO.equals(groupType)){
			groupMng.setGroupType(SysParamNameConst.GROUP_TWO);
			return groupMngDao.getBeansByBean(groupMng, MatchMode.ANYWHERE);
		}
		else{
			return null;
		}
	}
	
	
	public GroupMngDao getGroupMngDao() {
		return groupMngDao;
	}


	public void setGroupMngDao(GroupMngDao groupMngDao) {
		this.groupMngDao = groupMngDao;
	}


	public UsersDao getUsersDao() {
		return usersDao;
	}


	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public CompletePriceTranInfoDao getCompletePriceTranInfoDao() {
		return completePriceTranInfoDao;
	}

	public void setCompletePriceTranInfoDao(
			CompletePriceTranInfoDao completePriceTranInfoDao) {
		this.completePriceTranInfoDao = completePriceTranInfoDao;
	}

	public BTranFlowMappingDao getbTranFlowMappingDao() {
		return bTranFlowMappingDao;
	}

	public void setbTranFlowMappingDao(BTranFlowMappingDao bTranFlowMappingDao) {
		this.bTranFlowMappingDao = bTranFlowMappingDao;
	}

	public WTranFlowMappingDao getwTranFlowMappingDao() {
		return wTranFlowMappingDao;
	}

	public void setwTranFlowMappingDao(WTranFlowMappingDao wTranFlowMappingDao) {
		this.wTranFlowMappingDao = wTranFlowMappingDao;
	}

	public CTranFlowMappingDao getcTranFlowMappingDao() {
		return cTranFlowMappingDao;
	}

	public void setcTranFlowMappingDao(CTranFlowMappingDao cTranFlowMappingDao) {
		this.cTranFlowMappingDao = cTranFlowMappingDao;
	}

	public CNYTradeDao getCNYDao() {
		return CNYDao;
	}

	public void setCNYDao(CNYTradeDao cNYDao) {
		CNYDao = cNYDao;
	}

	public OtcSwapDao getOtcSwapDao() {
		return otcSwapDao;
	}

	public void setOtcSwapDao(OtcSwapDao otcSwapDao) {
		this.otcSwapDao = otcSwapDao;
	}
	
}
