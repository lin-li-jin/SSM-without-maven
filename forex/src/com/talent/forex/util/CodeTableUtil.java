package com.talent.forex.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;

import com.talent.exception.BoException;
import com.talent.forex.bean.domain.CodeTable;
import com.talent.forex.dao.CodeTableDao;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.hibernate.util.TransactionNestUtil;

public class CodeTableUtil implements Comparator  {
	private static CodeTableUtil instance = new CodeTableUtil();

	private static Hashtable table = null;

	public CodeTableUtil() {

	}

	public static CodeTableUtil getInstance() {
		if(table == null){
			instance.genCodeTable();
		}
		
		return instance;
	}

	public void refresh() {
		table = null;
	}

	public String codeValToDescr(String codeType, String codeVal){
		try{
			String descr = codeVal == null ? "" : codeVal;
			if (codeType != null && codeVal != null) {
				Hashtable valTable = (Hashtable) table.get(codeType);
				if (valTable != null) {
					CodeTable bean = (CodeTable) valTable.get(codeVal);
					if (bean != null)
						descr = bean.getDescr();
				}
			}
			return descr;
		}catch(Exception e){
			BoException be = new BoException("codeValToDescr");
			be.setExceptionType("codeValToDescr failed！");
			throw be;
		}
	}
	
	public String descrToCodeVal(String codeType, String descr){
		String val = "";
		Collection list = getCodeTypeList(codeType);
		if(list != null){
			Iterator it = list.iterator();
			while(it.hasNext()){
				CodeTable code = (CodeTable)it.next();
				if(code.getDescr().equals(descr)){
					val = code.getId().getCodeVal();
					break;
				}
			}
		}
		return val;
	}
	
	public boolean isCodeValReg(String codeType, String codeVal){
		codeType = codeType == null ? "" : codeType;
		codeVal = codeVal == null ? "" : codeVal;
		Hashtable valTable = (Hashtable) table.get(codeType);
		if(valTable == null){
			return false;
		}else{
			return valTable.containsKey(codeVal);
		}
	}
	
	
	private void genCodeTable() {
		String ref = "";
		try{
			CodeTableDao dao = new CodeTableDao();
			
			ref = TransactionNestUtil.reference();

			Collection collection = ((CodeTableDao) dao.addOrder(Order
					.asc("id.codeType"))).getAll();	
			
			TransactionNestUtil.releaseRef(ref);
			
			if (collection != null) {
				synchronized (instance) {
					if (table == null) {
						table = new Hashtable();
						Iterator it = collection.iterator();
						String codeType = "";
						Hashtable tmp = null;
						
						
						//codeTable
						while (it.hasNext()) {
							CodeTable bean = (CodeTable) it.next();
							if (!codeType.equals(bean.getId().getCodeType())) {
								if (tmp != null) {
									table.put(codeType, tmp);
								}
								tmp = new Hashtable();
								codeType = bean.getId().getCodeType();
							}
							tmp.put(bean.getId().getCodeVal(), bean);
						}
						if (tmp != null) {
							table.put(codeType, tmp);
						}
						
						//locCodeTable
						codeType = null;
						Hashtable tmpLoc = null;
									
					}
				}
			}
		}catch(Exception e){
			TransactionNestUtil.releaseRef(ref);
		}finally{
			if(!TransactionNestUtil.isReference()){
				HibernateUtil.closeSession();
			}
		}
	}
	
	
	
	private Collection getCodeTypeList(String codeType) {
		Collection collection = null;
		if (codeType != null) {
			Hashtable valTable = (Hashtable) table.get(codeType);
			if (valTable != null) {
				collection = valTable.values();
			}
		}
		return sortAsc(collection);
	}

	private Collection sortAsc(Collection collection) {
		List list = new ArrayList();
		if (collection != null) {
			list.addAll(collection);
			Collections.sort(list, this);
		}
		return list;
	}

	private Collection sortDes(Collection collection) {
		List list = new ArrayList();
		if (collection != null) {
			list.addAll(collection);
			Collections.sort(list, this);
			Collections.reverse(list);
		}
		return list;
	}

	public int compare(Object arg0, Object arg1) {
		CodeTable bean0 = (CodeTable) arg0;
		CodeTable bean1 = (CodeTable) arg1;

		return bean0.getId().getCodeVal().compareTo(bean1.getId().getCodeVal());
	}
	
	public Collection getCodeList(String codeTpye) {
		return sortAsc(getCodeTypeList(codeTpye));
	}
	
	/**
	 * 读取各学期起止时间
	 * @return
	 */
	public Map getTerms( String codeType ){
		Map<String, String> map = this.getValDesc(codeType);
		return map;
	}
	
//	/**
//	 * 读取各节次的上课时间
//	 * @return
//	 */
//	public Map<String, String> getCourseNum(){
//		Collection<CodeTable> col = getCodeTypeList(CodeTypeNameConst.CNUM);
//		HashMap<String, String> map = new HashMap<String, String>();
//		for( CodeTable it : col ){
//			map.put(it.getDescr(), it.getCodeVal());
//		}
//		return map;
//	}
	
//	public String getCurrentTerm(){
//		Map<String, CodeTable> map = CodeTableUtil.getValByCodeType("TERM"+UserModelUtil.getFixedSchoolNum());
//		Collection<CodeTable> col = map.values();
//		long[] tmp = new long[2];
//		String[] date = new String[2];
//		long now = new Date().getTime();
//		String term = null;
//		for( CodeTable code : col ){
//			date = code.getDescr().split("-");
//			try {
//				tmp[0] = GetDateTimeUtil.formatI.parse( date[0] ).getTime();
//				tmp[1] = GetDateTimeUtil.formatI.parse( date[1] ).getTime();
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			
//			if( tmp[0] <= now && tmp[1] >= now ){
//				term = code.getCodeVal();
//				break;
//			}
//			
//		}
//		
//		if( term == null ){
//			return null;
//		}
//		return term;
//	}

//	public HashMap<String, String> getCurrentSchoolTerm(){
//		//获取各个学期
//		Collection<CodeTable> col = CodeTableUtil.getInstance()
//												 .getCodeList(CodeTypeNameConst.TERM+UserModelUtil.getUser().getSchoolNum());
//		
//		HashMap<String, String> map = new HashMap<String, String>();
//		for( CodeTable ct : col ){
//			String tmp = ct.getCodeVal().substring(0, ct.getCodeVal().length()-2 );
//			map.put(tmp, tmp+"-"+(Integer.valueOf(tmp)+1));
//		}
//		return map;
//	}
	
	public Map<String, String> getValDesc( String codeType ){
		HashMap<String, String> map = new HashMap<String, String>();
		Collection<CodeTable> codes = getCodeTypeList(codeType);
		for ( CodeTable code : codes ){
			map.put(code.getId().getCodeVal(), code.getDescr());
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public static Hashtable getValByCodeType(String codeType){
		Hashtable ht = (Hashtable)table.get(codeType);
		return ht;
	}
	
//	public static String getOperRoleDesc(String codeType){
//		
//	}
}
