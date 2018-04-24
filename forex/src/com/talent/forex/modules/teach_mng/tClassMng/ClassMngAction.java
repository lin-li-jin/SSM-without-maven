package com.talent.forex.modules.teach_mng.tClassMng;

import com.opensymphony.webwork.ServletActionContext;
import com.talent.auth.bean.domain.TClass;
import com.talent.auth.bean.domain.Users;
import com.talent.auth.bean.model.UserModel;
import com.talent.auth.login.LoginConst;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.modules.admin_mng.BaseMessage;
import com.talent.forex.util.PageBean;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.hibernate.criterion.DetachedCriteria;

/**
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/8/17.
 */
public class ClassMngAction extends ForexBaseAction{

//	private PageContext pageContext;
	
    private PageBean pageBean = new PageBean();//��ҳ��

    private TClass tClass;

    private ClassMngBo classMngBo;
    
    private int page;//������������ҳ�洫�ݵĵ�ǰҳ��,���ڷ�װ��pageBean��currentPage
    
    private int rows;//������������ҳ�洫�ݵ�ÿҳ��ʾ����,���ڷ�װ��pageBean��pageSize

    /**
     * ��ʼ���༶
     */
    public String InitClass(){
        return SUCCESS;
    }

    /**
     * �����༶
     */
    public void insertClass() throws UnsupportedEncodingException{
        UserModel userModel= (UserModel) sessionGet(LoginConst.LOGIN_USER_MODEL);
        String classname = new String(tClass.getClassName().getBytes("ISO-8859-1"),"UTF-8");
        tClass.setClassName(classname);
        BaseMessage result=classMngBo.insertClass(userModel,tClass);
        Collection<Object> list=new ArrayList<Object>();
        list.add(result);
        flushSuccessJSON(list);
    }

    /**
     * ��ѯ�༶��Ϣ
     * @return
     */
    public void queryClass() throws IOException{
        UserModel userModel= (UserModel) sessionGet(LoginConst.LOGIN_USER_MODEL);
        String classNo = tClass==null?null:tClass.getClassNo();//tClassΪ��˵����β�ѯ��û������༶�ŵ�,ֱ�Ӳ�ý�ʦ���������а༶,�����������ѯ
        classMngBo.queryClass(classNo,pageBean, userModel.getuId());
//        ArrayList<PageBean> pageBeans=new ArrayList<PageBean>();
//        pageBeans.add(pageBean);
        //����װ�÷�ҳ���ݵ�pageBeanд��ҳ��
		String json = JSONObject.fromObject((Object)pageBean).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
    }
    
    /**
     * ��ѯ�༶����ϸ��Ϣ
     */
    public void queryClassDetail(){
    	String classNo = tClass==null?null:tClass.getClassNo().toString();
    	pageBean.setDetachedCriteria(DetachedCriteria.forClass(Users.class));
        classMngBo.getStudentsByclassNo(classNo,pageBean);
//      ����װ�÷�ҳ���ݵ�pageBeanд��ҳ��
        String json = JSONObject.fromObject((Object)pageBean).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public TClass gettClass() {
        return tClass;
    }

    public void settClass(TClass tClass) {
        this.tClass = tClass;
    }

    public ClassMngBo getClassMngBo() {
        return classMngBo;
    }

    public void setClassMngBo(ClassMngBo classMngBo) {
        this.classMngBo = classMngBo;
    }
    
//	public PageContext getPageContext() {
//		return pageContext;
//	}
//
//	public void setPageContext(PageContext pageContext) {
//		this.pageContext = pageContext;
//	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public void setPage(int page) {
		this.pageBean.setCurrentPage(page);
	}

	public void setRows(int rows) {
		this.pageBean.setPageSize(rows);
	}
    

}