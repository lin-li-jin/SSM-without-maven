package com.talent.forex.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;

import com.talent.exception.BoException;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.hibernate.util.TransactionNestUtil;
import com.talent.forex.bean.domain.Seq;
import com.talent.forex.dao.SeqDao;

/**
 * <p>
 * Title:
 * </p>
 * 0
 * <p>
 * Description:流水号生成器
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Talent Information Solutions Ltd.
 * </p>
 * 
 *
 * @version 1.0
 */

public class SequenceUtil {

	private static Logger logger = Logger.getLogger(SequenceUtil.class);

	public static double getNextSeqNo(String sequenceName,int length) {

		double result = 0.0;
		
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
//			SeqDao seqDao = new SeqDao();
//			Seq s= new Seq();
//			s.setTranType(sequenceName);
//			s=seqDao.getBeanByBean(s, MatchMode.EXACT);
			HibernateUtil.beginTransaction();
			Session session=HibernateUtil.getSession();
			
			String hqlStr  =   " from Seq as seq where tranType='"+ sequenceName+"'"; 
			Query query  =  session.createQuery(hqlStr); 
			query.setLockMode( "seq" ,LockMode.UPGRADE);  // 加锁,防止出现脏读,这个的作用域范围
			List seqList  =  query.list();
			Seq seq=(Seq) seqList.get(0);
			
			result= seq.getSeqNo()+1;

			if(length <(String.valueOf(result).length()-2)){//1.0 长度为3 -2（.0）
				result=0;
			}
//			s.setSeqNo(result);
//			ArrayList c=new ArrayList();
//			c.add(s);
//			seqDao.batchUpdate(c);
//			seqDao.updateBean(s);
			hqlStr= "update Seq set seqNo="+result+" where tranType='"+ sequenceName+"'";
			query  =  session.createQuery(hqlStr); 
			query.executeUpdate();
			HibernateUtil.commitTransaction();
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("Generate sequence fail! sequenceType:["
					+ sequenceName + "]" + e.getMessage());
			BoException be = new BoException("getNextSeqNo");
			throw be;
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();	
				logger.warn("close session -----SequenceUtil");
			}
		}
		return result;
	}

	/**
	 * 业务流水号的生成
	 */
	//ddeason edit start 20140107
	public static String getNextTranSeq(String tranNo){
		//ddeason 20140504 修改，不再通过seq表获取流水号，防止意外锁表
		if ("OP".equals(tranNo)) {
			return "00000000";
		};//ddeason 20140504 end
		int result = (int) SequenceUtil.getNextSeqNo(tranNo,8);
		 String refMsgId = Integer.toString(result);
		 if(refMsgId.length() < 8){
			int length = refMsgId.length();
			for(int i = 0 ; i < 8-length ; i++){
				refMsgId = "0"+refMsgId;
			}
		}
		 refMsgId = tranNo+refMsgId;
		return refMsgId;
	}
	//ddeason end
//	/**
//	 * 实例序号的生成
//	 * @return
//	 */
//	public static String getNextSidPoolIdSeq(){
//		 int result = (int) SequenceUtil.getNextSeqNo(SequenceNameConst.SID_SEQ);
//		 String refMsgId = Integer.toString(result);
//		/* if(refMsgId.length() < 9){
//			int length = refMsgId.length();
//			for(int i = 0 ; i < 9-length ; i++){
//				refMsgId = "0"+refMsgId;
//			}
//		}*/
//		return refMsgId;
//	}
	/**
	 * 增加流水号
	 * @param irTranNo
	 */
	public static void makeNextSeq(String irTranNo) {
		double result1=0.0;
		result1=Double.parseDouble(irTranNo.substring(2));
		Connection connection = null;
		double result=0.0;
		Statement st = null;
		ResultSet rs = null;
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
			//写代码替换存储过程
			SeqDao seqDao = new SeqDao();
			Seq s= new Seq();
			s.setTranType(irTranNo.substring(0,2));
			s=seqDao.getBeanByBean(s, MatchMode.EXACT);
			result= s.getSeqNo()+1;
			if(result1>result){
				result=result1;
			}
			s.setSeqNo(result);
			//forex130012 BEGIN
			ArrayList c=new ArrayList();
			c.add(s);
			seqDao.batchUpdate(c);
//			seqDao.updateBean(s);
			//forex130012 END
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("Generate sequence fail! sequenceType:["
					+ irTranNo.substring(0,2) + "]" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
			try {
				if (rs != null) {
					rs.close();
					st.close();
					//connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	/**
	 * 课程编号的生成
	 * @return
//	 */
//	private static String getCourseNumSeq(){
//		 String result = (String) SequenceUtil.getNextSeqNo(SequenceNameConst.COSNUM_SEQ);
//		 String refMsgId = Integer.toString(result);
//		return refMsgId;
//	}
//	/**
//	 * 群组号的生成
//	 * @return
//	 */
//	public static String getNextGroupNumSeq(String scorFor){
//		int result = 0;
//		if(SysParamNameConst.SCOR_FOR_RCLX.equals(scorFor)){
//			result =(int) SequenceUtil.getNextSeqNo(SequenceNameConst.GROUP_SEQ_D);
//			
//		 }else if(SysParamNameConst.SCOR_FOR_ZSKH.equals(scorFor) ||SysParamNameConst.SCOR_FOR_ZSKHCT.equals(scorFor)){
//			result = (int) SequenceUtil.getNextSeqNo(SequenceNameConst.GROUP_SEQ_E);
//			
//		 }else if(SysParamNameConst.SCOR_FOR_MNKH.equals(scorFor)|| SysParamNameConst.SCOR_FOR_MNKHCT.equals(scorFor) ){
//			 result = (int) SequenceUtil.getNextSeqNo(SequenceNameConst.GROUP_SEQ_S);
//			 
//		 }else if( SysParamNameConst.SCOR_FOR_KTLX.equals(scorFor)|| SysParamNameConst.SCOR_FOR_KTLXCT.equals(scorFor)){
//			  result = (int) SequenceUtil.getNextSeqNo(SequenceNameConst.GROUP_SEQ_C);
//		 }
//		
////		 if(result<999){
////			 
////		 }
//		 String refMsgId = Integer.toString(result);		 
//		 if(refMsgId.length() < 3){
//			int length = refMsgId.length();
//			for(int i = 0 ; i < 3-length ; i++){
//				refMsgId = "0"+refMsgId;
//			}
//		}
//		 //群组作用 D3：日常练习，E1：正式考核，S2：模拟考核 C0:课堂练习
//		 String userFor = "";
//		 if(scorFor.equals(SysParamNameConst.SCOR_FOR_RCLX)){
//			 userFor = "D";
//		 }else if(SysParamNameConst.SCOR_FOR_ZSKH.equals(scorFor) ||SysParamNameConst.SCOR_FOR_ZSKHCT.equals(scorFor)){
//			 userFor = "E";
//		 }else if(SysParamNameConst.SCOR_FOR_MNKH.equals(scorFor)|| SysParamNameConst.SCOR_FOR_MNKHCT.equals(scorFor)){
//			 userFor = "S";
//		 }else if(SysParamNameConst.SCOR_FOR_KTLX.equals(scorFor)|| SysParamNameConst.SCOR_FOR_KTLXCT.equals(scorFor)){
//			 userFor = "C";
//		 }else{
//			 userFor = "_";
//		 }
//		 StringBuffer sb = new StringBuffer(GetDateTimeUtil.getCurrentDate());
//		 sb.append(userFor);
//		 sb.append(refMsgId);
//		return sb.toString();
//	}
}
