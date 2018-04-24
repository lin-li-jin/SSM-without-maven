package com.talent.forex.modules.teach_mng.dataInput;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import javax.servlet.ServletException;

import com.talent.auth.bean.model.UserModel;
import com.talent.auth.login.LoginConst;
import com.talent.forex.bean.model.FileUploadModel;
import com.talent.forex.bean.model.StuInputResultModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.core.AjaxBasicAction;
import com.talent.forex.util.FileUtil;
import com.talent.forex.util.SysParamUtil;
/*
 * Amendment No.: FOEXAS024
 * Create By    : ggm
 * Description  : 创建分组
 * Modify Date  : 2014-07-21
 * 
 */
/*
 * Amendment No.: FOEXAS025
 * Create By    : ggm
 * Description  : 学生信息导入
 * Modify Date  : 2014-07-21
 * 
 */
/*
 * Amendment No.: FOEXAS026
 * Create By    : ggm
 * Description  : 学生管理-密码重置/解锁
 * Modify Date  : 2014-07-21
 * 
 */
@SuppressWarnings("serial")
public class DataInputAction extends AjaxBasicAction{
	
	private DataInputBo dataInputBo;
	private FileUploadModel fileUploadModel = new FileUploadModel();
	/**
	 * system data init
	 * @return
	 */
	public String dataInputInit(){
		UserModel userModel = (UserModel) sessionGet(LoginConst.LOGIN_USER_MODEL);
		sessionPut("fileUploadModel",null);
		return SUCCESS;
	}
	
	/**
	 * 文件导入
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public String fileImport() throws Exception{
		UserModel userModel = (UserModel) sessionGet(LoginConst.LOGIN_USER_MODEL);
		fileUploadModel.setClassId(1);
		StuInputResultModel model =dataInputBo.genStudentFileDone(fileUploadModel,userModel);
		if(null!=model&&!("").equals(model.getResult())&&SysParamNameConst.DATA_INPUT_S.equals(model.getResult())){
			sessionPut("fileUploadModel",fileUploadModel);
			Collection stuCol=model.getStuCol();
			sessionPut("userList",stuCol);
			return SUCCESS;
		}
		else{
			requestPut("errorResult",model.getResult());
			return ERROR;
		}
	}
	
	public String stuQueryTable(){
		
		return SUCCESS;
	}
	/**
	 * 下载文件导入模板
	 * 
	 */
	public void fileDownLoad()throws Exception{
		try {
			String fullPath = DataInputAction.class.getResource("") + "学生信息导入模板.xls";
			fullPath = fullPath.substring(6,fullPath.length());
			FileUtil.fileDownload("学生信息导入模板.xls",fullPath);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		//return SUCCESS;
	}
	/**
	 * 文件导出
	 * @return
	 * @throws Exception
	 */
	public String downLoad() throws Exception{
		return SUCCESS;
	}

	public DataInputBo getDataInputBo() {
		return dataInputBo;
	}

	public void setDataInputBo(DataInputBo dataInputBo) {
		this.dataInputBo = dataInputBo;
	}

	public FileUploadModel getFileUploadModel() {
		return fileUploadModel;
	}

	public void setFileUploadModel(FileUploadModel fileUploadModel) {
		this.fileUploadModel = fileUploadModel;
	}
}