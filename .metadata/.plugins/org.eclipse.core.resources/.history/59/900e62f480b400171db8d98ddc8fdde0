package com.talent.forex.modules.admin_mng;

import com.opensymphony.webwork.ServletActionContext;
import com.talent.auth.bean.domain.Users;
import com.talent.forex.bean.model.FileUploadModel;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.util.PageBean;


import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/8/15.
 */
public class TeachMngAction extends ForexBaseAction {

    private static final Logger log = Logger.getLogger(TeachMngAction.class);

    private FileUploadModel fileUploadModel;



    private Users users;

    private String userNum;//页面传递的教工号

    private PageBean pageBean = new PageBean();//分页类
    
    private int page;//属性驱动接收页面传递的当前页数,用于封装进pageBean的currentPage
    
    private int rows;//属性驱动接收页面传递的每页显示条数,用于封装进pageBean的pageSize

    public FileUploadModel getFileUploadModel() {
        return fileUploadModel;
    }

    public void setFileUploadModel(FileUploadModel fileUploadModel) {
        this.fileUploadModel = fileUploadModel;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    private TeachMngBO teachMngBO;

    /**
     * 教师
     */
    public String teachInit() {
        return SUCCESS;
    }

    /**
     * 查询教师信息
     * @return userNum 教师账号
     */
    public void queryTeacher(String userNum){
        Collection teachers=teachMngBO.selectTeacher(userNum);
        flushSuccessJSON(teachers);
    }
    
    /**
     * 分页查询教师信息
     */
    public void pageQueryTeacher() throws IOException{
    	pageBean.setDetachedCriteria(DetachedCriteria.forClass(Users.class));
    	pageBean.setCurrentPage(page);
    	pageBean.setPageSize(rows);
    	teachMngBO.pageQuery(userNum, pageBean);
    	
    	//创建json配置对象,将不需要转换成json的属性去除
    	JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"currentPage","pageSize","detachedCriteria","password","groupOne","groupTwo","post","userType","status","loginTime","falseTime"});
		
		//将包含数据总条数和数据集合的pageBean转换为json
		String json = JSONObject.fromObject((Object)pageBean, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
    }

    /**
     * 新增教师信息
     */
    public void insertTeacher() throws Exception {
        BaseMessage result=teachMngBO.insertTeacher(users);
        if (!result.isSuccess())
            flushFailJSON(result.getMessage());
        else{
            Collection<Object> baseMessages=new ArrayList<Object>();
            baseMessages.add(result);
            flushSuccessJSON(baseMessages);
        }
    }

    public TeachMngBO getTeachMngBO() {
        return teachMngBO;
    }

    public void setTeachMngBO(TeachMngBO teachMngBO) {
        this.teachMngBO = teachMngBO;
    }

	public void setUserNum(String userNum){
		this.userNum = userNum;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}


}
