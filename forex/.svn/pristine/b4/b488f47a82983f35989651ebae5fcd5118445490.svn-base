package com.talent.forex.modules.teach_mng.paramMng;

import com.talent.forex.bean.model.ParamMngModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.core.ForexBaseAction;
/*
 * Amendment No.: FOEXAS009
 * Create By    : lzc
 * Description  : 交易
 * 首页
 * Modify Date  : 2014-07-21
 */
public class ParamMngAction  extends ForexBaseAction{
	
	public ParamMngAction(){}
	
	private ParamMngBo paramMngBo;
	private ParamMngModel paramModel = new ParamMngModel();
	private String groupOne;//一级组组号
	private String groupNum;//组号
	private String strUsers;//用户信息
	private String paramType;
	public String qryParamMngInit(){
		requestPut("groupOneList",paramMngBo.getGroupListDone(SysParamNameConst.GROUP_ONE));
		return SUCCESS;
	}
	
	public String accQryParamModify(){
		paramMngBo.accQryParamModify(paramModel);
		return SUCCESS;
	}
	
	public String overAllQryParamModify(){
		paramMngBo.overAllQryParamModify(paramModel);
		return SUCCESS;
	}
	
	public String accParamSetInit(){
		requestPut("groupOneList",paramMngBo.getGroupListDone(SysParamNameConst.GROUP_ONE));
		return SUCCESS;
	}
	
	public String setAnalogue(){
		paramMngBo.setAnaogueDone(paramModel);
		return SUCCESS;
	}

	public String pwResetAaccUnlockInit(){
		requestPut("groupOneList",paramMngBo.getGroupListDone(SysParamNameConst.GROUP_ONE));
		return SUCCESS;
	}
	
	public String pwResetGetUserList(){
		requestPut("userList",paramMngBo.getUsersDone(paramModel));
		requestPut("paramModel",paramModel);
		requestPut("groupOneList",paramMngBo.getGroupListDone(SysParamNameConst.GROUP_ONE));
		return SUCCESS;
	}
	
	public String resetUserPw(){
		String [] userInfo = strUsers.split("\\|\\|");
		paramMngBo.resetUserPwDone(userInfo);
		return SUCCESS;
	}
	
	public String accoutUnlock(){
		String [] userInfo = strUsers.split("\\|\\|");
		paramMngBo.accoutUnlockDone(userInfo);
		return SUCCESS;
	}
	
	public void getGroupTwos(){
		String dat=paramMngBo.getGroupTwoDone(groupOne);
		processText(dat,"text/plain;charset=GBK");
	}
	
	public void accQryParamQuery(){
		String data=paramMngBo.getParamQuery(groupNum);
		processText(data,"text/plain;charset=GBK");
	}
	
	public void getAnaGroup(){
		String data=paramMngBo.getAnaGroupQuery(groupNum);
		processText(data,"text/plain;charset=GBK");
	}
	
	public ParamMngBo getParamMngBo() {
		return paramMngBo;
	}

	public void setParamMngBo(ParamMngBo paramMngBo) {
		this.paramMngBo = paramMngBo;
	}

	public ParamMngModel getParamModel() {
		return paramModel;
	}

	public void setParamModel(ParamMngModel paramModel) {
		this.paramModel = paramModel;
	}

	public String getGroupOne() {
		return groupOne;
	}

	public void setGroupOne(String groupOne) {
		this.groupOne = groupOne;
	}

	public String getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(String groupNum) {
		this.groupNum = groupNum;
	}

	public String getStrUsers() {
		return strUsers;
	}

	public void setStrUsers(String strUsers) {
		this.strUsers = strUsers;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	
}
