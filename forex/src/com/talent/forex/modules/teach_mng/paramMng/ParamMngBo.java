package com.talent.forex.modules.teach_mng.paramMng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONObject;

import org.hibernate.criterion.MatchMode;

import com.talent.auth.bean.domain.Users;
import com.talent.auth.bean.model.UserModel;
import com.talent.auth.dao.UsersDao;
import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AnalogueMag;
import com.talent.forex.bean.domain.GroupMng;
import com.talent.forex.bean.domain.GroupSysParam;
import com.talent.forex.bean.model.ParamMngModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.dao.AnalogueMagDao;
import com.talent.forex.dao.GroupMngDao;
import com.talent.forex.dao.GroupSysParamDao;
import com.talent.forex.util.GetFixWordUtil;
/*
 * Amendment No.: FOEXAS009
 * Create By    : lzc
 * Description  : 交易首页
 * Modify Date  : 2014-07-21
 * 
 */
public class ParamMngBo extends BaseBo {
	private GroupMngDao groupMngDao;
	private GroupSysParamDao groupSysParamDao;
	private UsersDao usersDao;
	private AnalogueMagDao analogueMagDao;
	public ParamMngBo() {

	}
	
	/**
	 * 通过一级组查询相关二级组
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
	 * 获取组别列表
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
	 * 更改账户实时排名参数
	 * @param paramMngModel
	 */
	public void accQryParamModify(ParamMngModel paramMngModel){
		GroupSysParam groupSysParam = new GroupSysParam();
		groupSysParam.setGroupNum(paramMngModel.getGroupNum());
		groupSysParam = groupSysParamDao.getBeanByBean(groupSysParam, MatchMode.ANYWHERE);
		if(groupSysParam==null){
			logger.error("没有改组号信息！组号："+paramMngModel.getGroupNum());
			BoException be = new BoException("accQryParamModify");
			be.setExceptionType("没有改组号信息！组号："+paramMngModel.getGroupNum());
			throw be;
		}
		else{
			groupSysParam.setWAmount(paramMngModel.getWAmount());
			groupSysParam.setWQuantity(paramMngModel.getWQuantity());
			groupSysParam.setWRate(paramMngModel.getWRate());
			groupSysParam.setAccRankNum(paramMngModel.getAccRankNum());
			
			groupSysParamDao.updateBean(groupSysParam);
		}
	}
	
	/**
	 * 更改综合排名参数
	 * @param paramMngModel
	 */
	public void overAllQryParamModify(ParamMngModel paramMngModel){
		GroupSysParam groupSysParam = new GroupSysParam();
		groupSysParam.setGroupNum(paramMngModel.getGroupNum());
		groupSysParam = groupSysParamDao.getBeanByBean(groupSysParam, MatchMode.ANYWHERE);
		if(groupSysParam==null){
			logger.error("没有改组号信息！组号："+paramMngModel.getGroupNum());
			BoException be = new BoException("accQryParamModify");
			be.setExceptionType("没有改组号信息！组号："+paramMngModel.getGroupNum());
			throw be;
		}
		else{
			groupSysParam.setWCnyAcc(paramMngModel.getWCnyAcc());
			groupSysParam.setWForAcc(paramMngModel.getWForAcc());
			groupSysParam.setWMarginAcc(paramMngModel.getWMarginAcc());
			groupSysParam.setOverallRankNum(paramMngModel.getOverallRankNum());
			
			groupSysParamDao.updateBean(groupSysParam);
		}
	}
	
	/**
	 * 根据用户组别与交易员号获取交易员信息
	 * @param paramMngModel
	 * @return
	 */
	public Collection getUsersDone(ParamMngModel paramMngModel){
		Users user = new Users();
		ArrayList arr=new ArrayList();
		arr.add(GetFixWordUtil.getLikeWords(paramMngModel.getUserNum()));
		arr.add(GetFixWordUtil.getLikeWords(paramMngModel.getGroupOne()));
		arr.add(GetFixWordUtil.getLikeWords(paramMngModel.getGroupTwo()));
		List<UserModel> userList = (List<UserModel>)usersDao.getBeansByParams("getUserInfoByContents", arr);
		return userList;
	}
	
	/**
	 * 密码重置
	 * @param strUsers
	 */
	public void resetUserPwDone(String[] strUsers){
		for(int i=0;i<strUsers.length;i++){
			Users user = new Users();
			String[] userInfo = strUsers[i].split("\\$\\$");
			String groupOne = userInfo[0];
			String userNum = userInfo[1];
			user.setGroupOne(groupOne);
			user.setUserNum(userNum);
			user = usersDao.getBeanByBean(user, MatchMode.ANYWHERE);
			if(user != null){
				user.setPassword(user.getUserNum());
				usersDao.updateBean(user);
			}
		}
	}
	
	/**
	 * 账号解锁
	 * @param strUsers
	 */
	public void accoutUnlockDone(String[] strUsers){
		for(int i=0;i<strUsers.length;i++){
			Users user = new Users();
			String[] userInfo = strUsers[i].split("\\$\\$");
			String groupOne = userInfo[0];
			String userNum = userInfo[1];
			user.setGroupOne(groupOne);
			user.setUserNum(userNum);
			user = usersDao.getBeanByBean(user, MatchMode.ANYWHERE);
			if(user != null){
				user.setStatus("1");
				user.setFalseTime(0);
				usersDao.updateBean(user);
			}
		}
	}
	
	/**
	 * 对手方类型：0，组内成员互为对手方
	 * 对手方类型：1，与他组成员成为对手方，组内成员不能为对手方，存储到数据库
	 * @param paramMngModel
	 */
	public void setAnaogueDone(ParamMngModel paramMngModel){
		if("0".equals(paramMngModel.getAnalogueType())){
			AnalogueMag analogueMag = new AnalogueMag();
			analogueMag.setInitGroup(paramMngModel.getInitGroup());
			analogueMag = analogueMagDao.getBeanByBean(analogueMag, MatchMode.EXACT);
			if(analogueMag==null){
				analogueMag = new AnalogueMag();
				analogueMag.setInitGroup(paramMngModel.getInitGroup());
				analogueMag.setAnaGroup(paramMngModel.getInitGroup());
				analogueMagDao.makePersistent(analogueMag, false);
			}
			else{
				analogueMag.setAnaGroup(paramMngModel.getInitGroup());
				analogueMagDao.updateBean(analogueMag);
			}
		}
		if("1".equals(paramMngModel.getAnalogueType())){
			AnalogueMag analogueMag = new AnalogueMag();
			analogueMag.setInitGroup(paramMngModel.getInitGroup());
			analogueMag = analogueMagDao.getBeanByBean(analogueMag, MatchMode.EXACT);
			if(analogueMag==null){
				analogueMag = new AnalogueMag();
				analogueMag.setInitGroup(paramMngModel.getInitGroup());
				analogueMag.setAnaGroup(paramMngModel.getAnaGroup());
				analogueMagDao.makePersistent(analogueMag, false);
			}
			else{
				analogueMag.setAnaGroup(paramMngModel.getAnaGroup());
				analogueMagDao.updateBean(analogueMag);
			}
		}
	}
	/**
	 * 查询排名参数
	 * @param groupNum
	 * @return
	 */
	public String getParamQuery(String groupNum){
		GroupSysParam gsp=new GroupSysParam();
		gsp.setGroupNum(groupNum);
		gsp = this.groupSysParamDao.getBeanByBean(gsp, MatchMode.EXACT);
		JSONObject jo=JSONObject.fromObject(gsp);
		return jo.toString();
	}
	
	public String getAnaGroupQuery(String groupNum){
		AnalogueMag analogueMag=new AnalogueMag();
		analogueMag.setInitGroup(groupNum);
		analogueMag=analogueMagDao.getBeanByBean(analogueMag, MatchMode.EXACT);
		if(analogueMag==null){
			return "-1";
		}else{
			return analogueMag.getAnaGroup();
		}
	}
	
	public GroupMngDao getGroupMngDao() {
		return groupMngDao;
	}

	public void setGroupMngDao(GroupMngDao groupMngDao) {
		this.groupMngDao = groupMngDao;
	}

	public GroupSysParamDao getGroupSysParamDao() {
		return groupSysParamDao;
	}

	public void setGroupSysParamDao(GroupSysParamDao groupSysParamDao) {
		this.groupSysParamDao = groupSysParamDao;
	}

	public UsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public AnalogueMagDao getAnalogueMagDao() {
		return analogueMagDao;
	}

	public void setAnalogueMagDao(AnalogueMagDao analogueMagDao) {
		this.analogueMagDao = analogueMagDao;
	}
	
}
