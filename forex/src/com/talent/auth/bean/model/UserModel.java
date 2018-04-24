package com.talent.auth.bean.model;

import com.octo.captcha.image.ImageCaptcha;

import java.util.ArrayList;
import java.util.Date;

/*
 * Amendment No.: forex130011
 * Modify By    : Alpha Liang
 * Description  : 多国语言支持模块，增加用户的语言设置
 * Modify Date  : 2013-6-28
 * 
 */

/**
 * <p> Title: </p>
 * <p> Description: </p>
 * <p> Copyright: Copyright (c) 2005 </p>
 * <p> Company: Talent Information Solutions Ltd. </p>
 * @efswor not attributable
 * @version 1.0
 */

public class UserModel implements java.io.Serializable {
	
	private static final long serialVersionUID = 5697201736305949L;

	/* 20100803 */
	private String groupId;/* 群组号 */

	/*20170817*/
	private Integer uId;/*唯一标识id,即指users中的id*/

	private String userId;/* 登录号 教师工号、学生学号*/
	private String password;/* 密码 */
	private Long   courseId;/* 课程号 */
	private String postName;/* 岗位号 */
	private Long    id; /*序号 identity生成的那个序号 */
	private String classNo;
	private String postId;/*权限id*/
	
	
	/* 20100803 */
	private String 		name;
	private String 		userType;/* 用户类型 */
	private ArrayList<String> 	permissions;
	private String 		unitId;
	private String 		unitName;
	private ImageCaptcha 	captcha;
	private boolean 		loginFlag;
	private String 		sessionId;
	private Date 		lastLoginTime;

	
	private String loginDate;
	private String loginTime;
	
	private String lang = null;	//forex130011
	private String branchNo;
	private String ActionName;
	private String bankNo;
	
	/* 20140718 */
	private String groupOneId;//一级组组号
	private String groupTwoId;//二级组组号
	
	private boolean pwdModifyFlag;//更改密码标志
	private String status;//用户状态


	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	
	public UserModel() {
	}
	
	public UserModel(String userNum, String name, String groupOne, String groupTwo,
                     String post, String userType, String status){
		this.setUserId(userNum);
		this.setName(name);
		this.setGroupOneId(groupOne);
		this.setGroupTwoId(groupTwo);
		this.setPostId(post);
		this.setUserType(userType);
		this.setStatus(status);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public ArrayList<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(ArrayList<String> permissions) {
		this.permissions = permissions;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public ImageCaptcha getCaptcha() {
		return captcha;
	}

	public void setCaptcha(ImageCaptcha captcha) {
		this.captcha = captcha;
	}

	public boolean isLoginFlag() {
		return loginFlag;
	}
	
	public boolean getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(boolean loginFlag) {
		this.loginFlag = loginFlag;
	}
	

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	
	

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	

	

	public String getActionName() {
		return ActionName;
	}

	public void setActionName(String actionName) {
		ActionName = actionName;
	}

	
	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	//forex130011 Start
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	//forex130011 End

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getGroupOneId() {
		return groupOneId;
	}

	public void setGroupOneId(String groupOneId) {
		this.groupOneId = groupOneId;
	}

	public String getGroupTwoId() {
		return groupTwoId;
	}

	public void setGroupTwoId(String groupTwoId) {
		this.groupTwoId = groupTwoId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isPwdModifyFlag() {
		return pwdModifyFlag;
	}

	public void setPwdModifyFlag(boolean pwdModifyFlag) {
		this.pwdModifyFlag = pwdModifyFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSelectedBox(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<input type='checkbox'");
		buffer.append("value='"+this.getGroupOneId()+"$$"+this.getUserId()+"'");
		buffer.append("\'>&nbsp;");
		return buffer.toString();
	}
	
	public String getPwResetOper(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<input type='button'");
		buffer.append("value='密码重置' onclick='window.location=\"");
		buffer.append("resetUserPw.action?strUsers=");
		buffer.append(this.getGroupOneId()+"$$"+this.getUserId());
		buffer.append("\"'>&nbsp;");
		return buffer.toString();
	}
	
	public String getUnlockOper(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<input type='button'");
		buffer.append("value='账户解锁' onclick='window.location=\"");
		buffer.append("accoutUnlock.action?strUsers=");
		buffer.append(this.getGroupOneId()+"$$"+this.getUserId());
		buffer.append("\"'>&nbsp;");
		return buffer.toString();
	}
	
	public String getStatusDescr(){
		String descr="";
		if(null!=this.status&&!("").equals(this.status)){
			if("1".equals(this.status)){
				descr = "有效";
			}
			if("0".equals(this.status)){
				descr = "无效";
			}
		}
		return descr;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}
}