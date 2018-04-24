package com.talent.forex.util;//package com.talent.forex.util;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.net.SocketTimeoutException;
//import java.net.UnknownHostException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.springframework.context.ApplicationContext;
//
//import com.opensymphony.util.DateUtil;
//import com.talent.hibernate.util.HibernateUtil;
//import com.talent.hibernate.util.TransactionNestUtil;
//import com.talent.forex.bean.domain.CodeTable;
//import com.talent.forex.bean.domain.CreditInterfaceLog;
//import com.talent.forex.dao.CreditInterfaceLogDao;
//import com.talent.forex.modules.creditInterface.BatchHandler;
//import com.talent.forex.modules.creditInterface.Combainer;
//import com.talent.forex.modules.creditInterface.CreditInterfaceConst;
//import com.talent.forex.modules.creditInterface.Parser;
//import com.talent.forex.modules.timeTask.ExpReportTask;
//
///**
// * ����map����ҪHANDLECODE��Ϣ��ע������ַ�����д
// * �а��ļ�ͷ����Ҫ��ˮ��RUNNINGNO,
// */
//public class InterfaceUtil {
//	private static String creditIp=null;
//	private static String creditPort=null;
//	private static Logger logger = Logger.getLogger(InterfaceUtil.class.getName());
//	
//	public static HashMap sendToCredit(HashMap<String,String> map){
//		HashMap<String, HashMap<String,String>> socketMap=new HashMap<String, HashMap<String,String>>();
//		String handlecode=map.get("HANDLECODE");
//		HashMap<String,String> header=new HashMap<String,String>();
//		header.put("HANDLECODE", map.get("HANDLECODE"));	
//		header.put("DEPARTMENTNO", CreditInterfaceConst.DEFAULT_SEND_DEPARTMENTNO);
//		header.put("OPERATORNO", CreditInterfaceConst.DEFAULT_SEND_OPERATORNO);		
//		if (map.get("RUNNINGNO")==null || map.get("RUNNINGNO").length()==0) {
//			header.put("RUNNINGNO",SequenceUtil.getNextRunningSeq());
//		} else {
//			header.put("RUNNINGNO", map.get("RUNNINGNO"));
//		}		
//		if (map.get("DATE")==null || map.get("DATE").length()==0) {
//			header.put("DATE",GetDateTimeUtil.getCurrentDate());
//		} else {
//			header.put("DATE", map.get("DATE"));
//		}	
//
//		String respond = getRespondStr(header, map); 
//		
//    	if (respond!=null) {
//			 try {
//			        Socket socket =new Socket(getCreditIp(),Integer.valueOf(getCreditPort()));
//			        
//			        OutputStream os=socket.getOutputStream();
//			        PrintWriter pw=new PrintWriter(os);
//			        InputStream is=socket.getInputStream(); 
//		            BufferedReader br=new BufferedReader(new InputStreamReader(is));
//		            
//			        pw.write(respond);
//			        pw.flush();
//			       // socket.shutdownOutput();
//			        logger.warn("������Ϣ��"+respond);
////			        Char[] reply=null;
////			        while(!((reply=br.readLine())==null)){
////		                System.out.println("���շ���������Ϣ��"+reply);
////		            }
//			        	String info="";
//			 	        socket.setSoTimeout(60000);
//			 	        try {
//			 	        	byte[] buffer=new byte[8];
//			 	        	is.read(buffer); 
//			 	 	        int length = Integer.valueOf(new String(buffer,"gbk").trim());
//			 	 	        int off=0;
//			 	 	        byte[] bufferBody= new byte[3000];
//			 	 	        for (int i=0;i<length;i++){
//			 	 	        	off=is.read();
//			 	 	        	bufferBody[i]=(byte) off;
//			 	 	        }
//			 	 	        info=new String(buffer,"gbk")+new String(bufferBody,"gbk");
//			 	 	        logger.warn("�յ���Ϣ��"+info);
//			 	        } catch (SocketTimeoutException e) {
//			 	        	e.printStackTrace();
//			 	        	System.out.println("��ȡ��ʱ");
//			 	        }
//				 		Parser parser = new Parser();
//				 		 socketMap  = parser.parse(info);
//			 			CreditInterfaceLog creditInterfaceLog= new CreditInterfaceLog();
//			 			creditInterfaceLog.setMessage(info);
//			 			creditInterfaceLog.setIndicator("input");
//			 			creditInterfaceLog.setTimeStamp(GetDateTimeUtil.formatDate(new Date(), 0));
//			 			creditInterfaceLog.setSequence(header.get("RUNNINGNO"));
//			 			CreditInterfaceLogDao creditInterfaceLogDao=new CreditInterfaceLogDao();
//			 			
//			 			logger.warn("���͵��Ŵ�ip��"+getCreditIp()+":"+getCreditPort());
//			 			
//			 	    	String rsf=null;
//			 	    	try {					
////			 				HibernateUtil.beginTransaction();
//			 				rsf = TransactionNestUtil.reference();
//			 				creditInterfaceLogDao.makePersistent(creditInterfaceLog, false);
//			 				TransactionNestUtil.releaseRef(rsf);
//			 			}catch (Exception e) {
//			 				TransactionNestUtil.releaseRef(rsf);
//			 				if (!TransactionNestUtil.isReference()) {
//			 					HibernateUtil.rollbackTransaction();
//			 				}
//			 				e.printStackTrace();
//			 			}finally {
//			 				if (!TransactionNestUtil.isReference()) {
//			 					HibernateUtil.closeSession();			
//			 				}
//			 			} 
//			 	       
//
//			        socket.shutdownOutput();
//		         //4.�ر���Դ
//		            br.close();
//		            is.close();
//		           pw.close();
//		           os.close();
//		           socket.close();
//		           
//		          
//	       } catch (UnknownHostException e) {
//	           e.printStackTrace();
//	       } catch (IOException e) {
//	           e.printStackTrace();
//	       }
//    	}
//		return socketMap;
//	}
////	public static void sendToCredit(HashMap<String,String> header,HashMap<String,String> body){
////		String respond = getRespondStr(header,body);
////		if (respond!=null) {
////			 try {
////			        Socket socket =new Socket(getCreditIp(),Integer.valueOf(getCreditPort()));
////			        OutputStream os=socket.getOutputStream();
////			        PrintWriter pw=new PrintWriter(os);
////	
////			        pw.write(respond);
////			        pw.flush();
////			        System.out.println("������Ϣ��"+respond);
////			        socket.shutdownOutput();
////		         //4.�ر���Դ
////		           pw.close();
////		           os.close();
////		           socket.close();
////	       } catch (UnknownHostException e) {
////	           e.printStackTrace();
////	       } catch (IOException e) {
////	           e.printStackTrace();
////	       }
////    	}
////		
////	}
//
//	public static String getRespondStr(HashMap<String,String> header,HashMap<String,String> body){
//		String handlecode=header.get("HANDLECODE");		
//		Combainer combainer=new Combainer();
//		
//		String headerString = combainer.combainHead(header);
//		String bodyString=combainer.combainBody(body,handlecode,"DOWN");
//		
//		byte[] b=(headerString+bodyString).getBytes();
//		String respond=  combainer.combainItemLength(String.valueOf(b.length),8)+headerString+bodyString;
//		
//		//���ͳ�ȥ�����ݰ��鵵
//		CreditInterfaceLog creditInterfaceLog= new CreditInterfaceLog();
//		creditInterfaceLog.setMessage(respond);
//		creditInterfaceLog.setIndicator("output");
//		creditInterfaceLog.setTimeStamp(GetDateTimeUtil.formatDate(new Date(), 0));
//		creditInterfaceLog.setSequence(header.get("RUNNINGNO"));
//		CreditInterfaceLogDao creditInterfaceLogDao=new CreditInterfaceLogDao();
//		
//    	String rsf=null;
//    	try {					
////			HibernateUtil.beginTransaction();
//			rsf = TransactionNestUtil.reference();
//			creditInterfaceLogDao.makePersistent(creditInterfaceLog, false);
//			TransactionNestUtil.releaseRef(rsf);
//		}catch (Exception e) {
//			TransactionNestUtil.releaseRef(rsf);
//			if (!TransactionNestUtil.isReference()) {
//				HibernateUtil.rollbackTransaction();
//			}
//			e.printStackTrace();
//		}finally {
//			if (!TransactionNestUtil.isReference()) {
//				HibernateUtil.closeSession();			
//			}
//		}
////    	if (respond!=null) {    		
////    	}
//    	return respond;
//	}
//	
//
//	public static String getCreditIp() {
//		if (creditIp==null || creditIp.length()==0) {
//            String ipInsys="";            
//    		String ipInSysParam=SysParamUtil.getCharValueByName("SYN_CREDIT_IP");
//    		if(null==ipInSysParam||("").equals(ipInSysParam)) ipInsys=CreditInterfaceConst.DEFAULT_CREDIT_SERVER_IP;
//    		else ipInsys=ipInSysParam;
//            creditIp=ipInsys;
//		}
//		return creditIp;
//	}
//
//	public static void setCreditIp(String creditIp) {
//		InterfaceUtil.creditIp = creditIp;
//	}
//
//	public static String getCreditPort() {
//		if (creditPort==null || creditPort.length()==0) {
//            String portInsys="";            
//    		String portInSysParam=SysParamUtil.getCharValueByName("SYN_CREDIT_PORT");
//    		if(null==portInSysParam||("").equals(portInSysParam)) creditPort=CreditInterfaceConst.DEFAULT_CREDIT_SERVER_PORT;
//    		else creditPort=portInSysParam;
//		}
//		return creditPort;
//	}
//
//	public static void setCreditPort(String creditPort) {
//		InterfaceUtil.creditPort = creditPort;
//	}
//	public static void queryChange() {
//		ApplicationContext contex=XmlContextUtil.getInstance().getContex();
//		if (contex==null){
//			logger.warn("ApplicationContext null��timer query skipped");	
//		} else {
//			logger.warn("Timer trigger��batch ��"+new Date().toString());
//			BatchHandler batchHandler = new BatchHandler();
//			batchHandler.query();
//		}
//		
//	}
//}
