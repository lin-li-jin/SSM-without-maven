package com.talent.forex.modules.trade_mng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.talent.auth.bean.domain.Users;
import com.talent.auth.dao.UsersDao;
import com.talent.base.BaseBo;
import com.talent.forex.bean.domain.GroupMng;
import com.talent.forex.bean.domain.GroupSysParam;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.GroupMngDao;
import com.talent.forex.dao.GroupSysParamDao;
/*
 * Amendment No.: FOEXAS017��FOEXAS018��FOEXAS020
 * Create By    : lzc
 * Description  : �˻���������
 * Modify Date  : 2014-08-05
 */
public class AccountNumberManageBo extends BaseBo {

	private GroupSysParamDao groupSysParamDao;
	private GroupMngDao groupMngDao;
	private UsersDao usersDao;
	private CTranFlowMappingDao cTranFlowMappingDao;
	private AccInfoDao accInfoDao;

	/**
	 * �����鼶���ѯ�û��˻���ˮ��Ϣ
	 */
	public Collection<?> accountNumberQuery(String hql, String groupId){
		ArrayList<String> paraList = new ArrayList<String>();
		paraList.add(groupId);
		return cTranFlowMappingDao.getBeansByParams(hql, paraList);
	}
	
	/**
	 * �����鼶�����˻�����ѯ�˻���Ϣ
	 */
	public Collection<?> accInfoListQuery(String hql, String groupId, String accType){
		ArrayList<String> paraList = new ArrayList<String>();
		paraList.add(accType);
		paraList.add(groupId);
		return accInfoDao.getBeansByParams(hql, paraList);
	}
	
	/**
	 * �����鼶�������������
	 */
	public GroupSysParam groupSysParamQuery(String groupNum){
		GroupSysParam g = new GroupSysParam();
		g.setGroupNum(groupNum);
		return (GroupSysParam) groupSysParamDao.getBeanByBean(g, MatchMode.ANYWHERE);
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
	/**
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
	
	/**
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

	public AccInfoDao getAccInfoDao() {
		return accInfoDao;
	}

	public void setAccInfoDao(AccInfoDao accInfoDao) {
		this.accInfoDao = accInfoDao;
	}

	public GroupSysParamDao getGroupSysParamDao() {
		return groupSysParamDao;
	}

	public void setGroupSysParamDao(GroupSysParamDao groupSysParamDao) {
		this.groupSysParamDao = groupSysParamDao;
	}

	public CTranFlowMappingDao getcTranFlowMappingDao() {
		return cTranFlowMappingDao;
	}

	public void setcTranFlowMappingDao(CTranFlowMappingDao cTranFlowMappingDao) {
		this.cTranFlowMappingDao = cTranFlowMappingDao;
	}
	
}
