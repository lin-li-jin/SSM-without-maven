package com.talent.forex.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;

import com.talent.forex.bean.domain.Rate;
import com.talent.forex.bean.model.CcyModel;
import com.talent.forex.bean.model.LiborModel;
import com.talent.forex.dao.RateDao;
import com.talent.forex.modules.rateFactory.RandomRate;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.hibernate.util.TransactionNestUtil;

public class GetRateUtil implements Comparator{
	private static Logger logger = Logger.getLogger(GetRateUtil.class
			.getName());
	private static GetRateUtil instance = new GetRateUtil();

	private static Hashtable RmbJingJiaTable = null;
	private static Hashtable RmbXunJiaTable = null;
	private static Hashtable ForJingJiaTable = null;
	private static Hashtable MarginTable = null;
	private static Hashtable LiborTable = null;
	
	//private RateDao rateDao;
	
	private static String[] CName_CCYS={//人民币账户币种中文名，除人民币
		"美元",
		"欧元",
		"澳大利亚元",
		"英镑",
		"日元",
		"加拿大元"
	};
	private static String[] MName_CCYS={//保证金Libor币种
		"美元",
		"欧元",
		"澳元",
		"英镑",
		"日元",
		"加元"
	};
	
	private static String[] C_CCYS={//人民币账户币种
		"USD",
		"EUR",
		"AUD",
		"GBP",
		"JPY",
		"CAD"
	};
	
	private static String[] W_CCYS={//外币账户币种
		"USDGBP",
		"USDEUR",
		"USDAUD",
		"USDCAD",
		"USDJPY",
		
		"EURCAD",
		"EURGBP",
		"EURAUD",
		"EURJPY",
		"EURUSD",
		
		"GBPUSD",
		"GBPEUR",
		"GBPAUD",
		"GBPCAD", 
		"GBPJPY",
		
		"AUDGBP",
		"AUDEUR",
		"AUDUSD",
		"AUDCAD",
		"AUDJPY",
		
		"CADGBP",
		"CADEUR",
		"CADUSD",
		"CADAUD",
		"CADJPY",
		
		"JPYGBP",
		"JPYEUR",
		"JPYUSD",
		"JPYAUD",
		"JPYCAD"
	};
	
	private static String[] WB_CCYS={//缺失保证金账户币种
		"USDEUR",
		"USDAUD",
		"USDGBP",
		"JPYGBP",
		"JPYEUR",
		"JPYUSD",
		"JPYAUD",
		"JPYCAD",
		"CADUSD"
	};
	
	public static GetRateUtil getInstance() {
		if(RmbJingJiaTable == null||RmbXunJiaTable == null||ForJingJiaTable == null){
			/*instance.genRmbJingJiaRate();
			instance.genRmbXunJiaRate();
			instance.genForJingJiaRate();
			instance.genMarginRate();
			instance.genLibor();*/
			refresh();//等同上面
		}
		
		return instance;
	}
	
	public static void refresh(){
		instance.genRmbJingJiaRate();
		instance.genRmbXunJiaRate();
		instance.genForJingJiaRate();
		instance.genMarginRate();
		instance.genLibor();
	}
	
	public void genRmbJingJiaRate(){
			HibernateUtil.beginTransaction();
			if(RmbJingJiaTable == null){
				RmbJingJiaTable = new Hashtable();
			}
			//String urlStr = SysParamNameConst.RMB_JINGJIA_RATE_URL;
			String urlStr = "http://fx.cmbchina.com/hq";
			String rsf=null;
			ResultSet rs = null;
			Statement st = null;
			String str=null;
			BufferedReader br = null;
			try{
				URL url = new URL(urlStr);//读取url对应的页面
				URLConnection connection = url.openConnection();
				connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
				InputStream is = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is,"UTF-8");
				br = new BufferedReader(isr);
				while((str=br.readLine())!=null)//网页内容定位
				{//先找到那个table
					if (str.indexOf("<table cellpadding=\"0\" cellspacing=\"1\" width=\"740\" align=\"center\" class=\"data\">")>=0) {
						break;
					}
	//		    	//System.out.println(str);
				}
				str=br.readLine();
			} catch(Exception e){
				e.printStackTrace();
			}
		    while (str!=null && str.indexOf("</table>")<0) {
		    	if (str.indexOf("<td class=\"fontbold\">")>=0){//每一条行数据
		    		try {
						str=br.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
	        		int i=0; //列数计数器
	        		boolean isCcy = false; //判断是否是我们需要的币种
	        		int index = -1; //币种下标
	        		CcyModel ccyModel = new CcyModel();
	        		for (i=0;i<23 && str!=null && str.indexOf("</tr>")<0;i++){
	        			str = str.trim();
	        			switch(i){
	        				case 0:{//判断币种
	        					for(int j=0;j<CName_CCYS.length;j++){
	        						if(str.equals(CName_CCYS[j])){
	        							isCcy = true;
	        							ccyModel.setCcy(C_CCYS[j]);
	        							//System.out.println(ccyModel.getCcy());
	        							break;
	        						}
	        					}
	        					break;
	        				}
	        				case 12:{
	        					if(isCcy){
	        						ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(getMoneyDivise(str)));
	        						//System.out.println(ccyModel.getBidValue());
	        					}
	        					break;
	        				}
	        				case 18:{
	        					if(isCcy){
	        						ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(getMoneyDivise(str)));
	        						//System.out.println(ccyModel.getAskValue());
	        					}
	        					break;
	        				}
	        				default: break;
	        			}
	        			try {
							str=br.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
	        		}//for 每一列
	        		if(isCcy){
	        			//判断价格升降
	        			if(RmbJingJiaTable.containsKey(ccyModel.getCcy())){
	        				CcyModel model = (CcyModel)RmbJingJiaTable.get(ccyModel.getCcy());
	        				double srcBidValue = Double.valueOf(model.getBidValue());
	        				double srcAskValue = Double.valueOf(model.getAskValue());
	        				double tarBidValue = Double.valueOf(ccyModel.getBidValue());
	        				double tarAskValue = Double.valueOf(ccyModel.getAskValue());
	        				if(tarBidValue>srcBidValue){
	        					ccyModel.setBidFlag("1");
	        				}
	        				else if(tarBidValue==srcBidValue){
	        					ccyModel.setBidFlag("0");
	        				}
	        				else{
	        					ccyModel.setBidFlag("-1");
	        				}
	        				if(tarAskValue>srcAskValue){
	        					ccyModel.setAskFlag("1");
	        				}
	        				else if(tarAskValue==srcAskValue){
	        					ccyModel.setAskFlag("0");
	        				}
	        				else{
	        					ccyModel.setAskFlag("-1");
	        				}
	        				RmbJingJiaTable.remove(model.getCcy());
	        				RmbJingJiaTable.put(ccyModel.getCcy(), ccyModel);
	        			}else{
	        				ccyModel.setBidFlag("0");
	        				ccyModel.setAskFlag("0");
	        				RmbJingJiaTable.put(ccyModel.getCcy(), ccyModel);
	        			}
	        			//更新汇率
	        			rsf = TransactionNestUtil.reference();
	        			RateDao rateDao = new RateDao();
	        			//卖价
	        			Rate bean = new Rate();
	        			bean.setAccType("J");
	        			bean.setSourceCcy(ccyModel.getCcy());
	        			bean.setTargetCcy("CNY");
	        			bean.setDirection("0");
	        			//Rate rate = rateDao.getBeanByParams("J", ccyModel.getCcy(), "CNY", "0");
	        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
	        			if(rate == null){
		        			bean.setRate(ccyModel.getAskValue());
		        			rateDao.addBean(bean);
	        			}
	        			else{
	        				rate.setRate(ccyModel.getAskValue());
	        				rateDao.updateBean(rate);
	        			}
	        			//买价
	        			Rate bean2 = new Rate();
	        			bean2.setAccType("J");
	        			bean2.setSourceCcy(ccyModel.getCcy());
	        			bean2.setTargetCcy("CNY");
	        			bean2.setDirection("1");
	        			//Rate rate2 = rateDao.getBeanByParams("J", ccyModel.getCcy(), "CNY", "1");
	        			Rate rate2 = rateDao.getBeanByBean(bean, MatchMode.EXACT);
	        			if(rate2 == null){
		        			bean2.setRate(ccyModel.getBidValue());
		        			rateDao.addBean(bean2);
	        			}
	        			else{
	        				rate2.setRate(ccyModel.getBidValue());
	        				rateDao.updateBean(rate2);
	        			}
	        			TransactionNestUtil.releaseRef(rsf);
					}
		    	}
		    	try {
					str=br.readLine();
				} catch (IOException e) {
					HibernateUtil.rollbackTransaction();
					logger.error("访问数据失败"+e.getMessage());
					logger.warn("正在重试...");
					genRmbJingJiaRate();
					return;
				}
		    }
		    HibernateUtil.commitTransaction();
	}
	
	public void genRmbXunJiaRate(){
		if(RmbXunJiaTable == null){
			RmbXunJiaTable = new Hashtable();
		}
		//String urlStr = SysParamNameConst.RMB_JINGJIA_RATE_URL;
		String urlStr = "http://www.boc.cn/sourcedb/whpj/index.html";
	    String str=null;
	    BufferedReader br = null;
		try{
			URL url = new URL(urlStr);//读取url对应的页面
		    URLConnection connection = url.openConnection();
		    connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		    InputStream is = connection.getInputStream();
		    InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		    br = new BufferedReader(isr);
		    while((str=br.readLine())!=null)//网页内容定位
	        {//先找到那个table
		    	if (str.indexOf("<table cellpadding=\"0\" align=\"left\" cellspacing=\"0\" width=\"100%\">")>=0) {
		        	break;
		    	}
//		    	//System.out.println(str);
	        }
		    str=br.readLine();
		} catch(Exception e){
			e.printStackTrace();
		}
		    while (str!=null) {
		    	if (str.indexOf("<tr>")>=0){//每一条行数据
		    		try {
						str=br.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
	        		int i=0; //列数计数器
	        		boolean isCcy = false; //判断是否是我们需要的币种
	        		int index = -1; //币种下标
	        		CcyModel ccyModel = new CcyModel();
	        		for (i=0;i<8 && str!=null && str.indexOf("</tr>")<0;i++){
	        			//去掉<>之间内容
	        			while (true) {
		        			int index1=str.indexOf("<");
		        			int index2=str.indexOf(">");
		        			if (index1>=0 && index2>=0) {
		        				str=str.subSequence(0, index1)+str.substring(index2+1,str.length());
		        			} else {break;}
	        			}
	        			str=str.trim();
	        			switch (i) {
	        			case 0:{//判断币种
        					for(int j=0;j<CName_CCYS.length;j++){
        						if(str.equals(CName_CCYS[j])){
        							isCcy = true;
        							ccyModel.setCcy(C_CCYS[j]);
        							//System.out.println(ccyModel.getCcy());
        							break;
        						}
        					}
        					break;
        				}
	        			case 2:{
        					if(isCcy){
        						ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(getMoneyDivise(str)));
        						//System.out.println(ccyModel.getBidValue());
        					}
        					break;
        				}
	        			case 4:{
        					if(isCcy){
        						ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(getMoneyDivise(str)));
        						//System.out.println(ccyModel.getAskValue());
        					}
        					break;
        				}
        				default: break;
	        			}
        			try {
						str=br.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
	        		}//for
	        		if(isCcy){
	        			//判断价格升降
	        			if(RmbXunJiaTable.containsKey(ccyModel.getCcy())){
	        				CcyModel model = (CcyModel)RmbXunJiaTable.get(ccyModel.getCcy());
	        				double srcBidValue = Double.valueOf(model.getBidValue());
	        				double srcAskValue = Double.valueOf(model.getAskValue());
	        				double tarBidValue = Double.valueOf(ccyModel.getBidValue());
	        				double tarAskValue = Double.valueOf(ccyModel.getAskValue());
	        				if(tarBidValue>srcBidValue){
	        					ccyModel.setBidFlag("1");
	        				}
	        				else if(tarBidValue==srcBidValue){
	        					ccyModel.setBidFlag("0");
	        				}
	        				else{
	        					ccyModel.setBidFlag("-1");
	        				}
	        				if(tarAskValue>srcAskValue){
	        					ccyModel.setAskFlag("1");
	        				}
	        				else if(tarAskValue==srcAskValue){
	        					ccyModel.setAskFlag("0");
	        				}
	        				else{
	        					ccyModel.setAskFlag("-1");
	        				}
	        				RmbXunJiaTable.remove(model.getCcy());
	        				RmbXunJiaTable.put(ccyModel.getCcy(), ccyModel);
	        			}else{
	        				ccyModel.setBidFlag("0");
	        				ccyModel.setAskFlag("0");
	        				RmbXunJiaTable.put(ccyModel.getCcy(), ccyModel);
	        			}
	        			//更新汇率
	        			String rsf = TransactionNestUtil.reference();
	        			RateDao rateDao = new RateDao();
	        			//卖价
	        			Rate bean = new Rate();
	        			bean.setAccType("X");
	        			bean.setSourceCcy(ccyModel.getCcy());
	        			bean.setTargetCcy("CNY");
	        			bean.setDirection("0");
	        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
	        			if(rate == null){
		        			bean.setRate(ccyModel.getAskValue());
		        			rateDao.addBean(bean);
	        			}
	        			else{
	        				rate.setRate(ccyModel.getAskValue());
	        				rateDao.updateBean(rate);
	        			}
	        			//买价
	        			Rate bean2 = new Rate();
	        			bean2.setAccType("X");
	        			bean2.setSourceCcy(ccyModel.getCcy());
	        			bean2.setTargetCcy("CNY");
	        			bean2.setDirection("1");
	        			Rate rate2 = rateDao.getBeanByBean(bean2, MatchMode.EXACT);
	        			if(rate2 == null){
		        			bean2.setRate(ccyModel.getBidValue());
		        			rateDao.addBean(bean2);
	        			}
	        			else{
	        				rate2.setRate(ccyModel.getBidValue());
	        				rateDao.updateBean(rate2);
	        			}
	        			TransactionNestUtil.releaseRef(rsf);
					}
		    	}
		    	try {
					str=br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }//while
		/*} catch (Exception e){
			e.printStackTrace();
	  }*/
	}
	
	public void genForJingJiaRate(){
		if(ForJingJiaTable == null){
			ForJingJiaTable = new Hashtable();
		}
		//String urlStr = SysParamNameConst.RMB_JINGJIA_RATE_URL;
		String urlStr = null;
		URL url = null;
		URLConnection connection = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String str=null;
		try{
			//美元
			urlStr = "http://www.fxdiv.com/data/usd/";
			url = new URL(urlStr);//读取url对应的页面
		    connection = url.openConnection();
		    connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		    is = connection.getInputStream();
		    isr = new InputStreamReader(is,"GBK");
		    br = new BufferedReader(isr);
		    str=null;
		    while((str=br.readLine())!=null)//网页内容定位
	        {//先找到那个table
		    	if (str.indexOf("<li class=\"wt\"><span class=\"w1\">名称</span>")>=0) {
		        	break;
		    	}
//		    	//System.out.println(str);
	        }
		    str=br.readLine();
		    while (str!=null) {
		    	//System.out.println(str);
		    	str=str.trim();
		    	if (str.indexOf("<li")>=0){//每一条行数据
		    		CcyModel ccyModel = new CcyModel();
		    		int ccysNameIndex = str.indexOf("name=\"")+6;
		    		boolean isEnough = str.length()>ccysNameIndex+6?true:false;
		    		if(ccysNameIndex>=0&&isEnough){
		    			String ccys = str.substring(ccysNameIndex, ccysNameIndex+6);
		    			boolean isCcy = false;
		    			for(int i=0;i<W_CCYS.length;i++){
		    				if(W_CCYS[i].equals(ccys)){
		    					ccyModel.setCcy(W_CCYS[i]);
				    			//System.out.println(ccyModel.getCcy());
				    			isCcy = true;
		    				}
		    			}
		    			int bidPriceIndex = str.indexOf("<span class=\"w7") + 21;
		    			String bidPrice = getValue(str, bidPriceIndex);
			    		int askPriceIndex = str.indexOf("<span class=\"w8")+21;
			    		String askPrice = getValue(str, askPriceIndex);
			    		int endIndex = str.indexOf("<span class=\"w9");
			    		if(bidPriceIndex>=0&&askPriceIndex>=0&&endIndex>=0&&isCcy){
//			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(bidPriceIndex, askPriceIndex-7-21)));
//			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(askPriceIndex, endIndex-7)));
		        			RateDao rateDao = new RateDao();
			    			if(bidPrice.equals("")){
			    				Rate bean = new Rate();
			    				bean.setAccType("W");
			    				bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			    				Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				bidPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			String rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("W");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
				        			bean.setRate(bidPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(bidPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(bidPrice));
			    			if(askPrice.equals("")){
			    				Rate bean = new Rate();
			        			bean.setAccType("W");
			    				bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			    				Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				askPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			String rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("W");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
			        				bean.setRate(askPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(askPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(askPrice));
			    			//判断价格升降
		        			if(ForJingJiaTable.containsKey(ccyModel.getCcy())){
		        				CcyModel model = (CcyModel)ForJingJiaTable.get(ccyModel.getCcy());
		        				double srcBidValue = Double.valueOf(model.getBidValue());
		        				double srcAskValue = Double.valueOf(model.getAskValue());
		        				double tarBidValue = Double.valueOf(ccyModel.getBidValue());
		        				double tarAskValue = Double.valueOf(ccyModel.getAskValue());
		        				if(tarBidValue>srcBidValue){
		        					ccyModel.setBidFlag("1");
		        				}
		        				else if(tarBidValue==srcBidValue){
		        					ccyModel.setBidFlag("0");
		        				}
		        				else{
		        					ccyModel.setBidFlag("-1");
		        				}
		        				if(tarAskValue>srcAskValue){
		        					ccyModel.setAskFlag("1");
		        				}
		        				else if(tarAskValue==srcAskValue){
		        					ccyModel.setAskFlag("0");
		        				}
		        				else{
		        					ccyModel.setAskFlag("-1");
		        				}
		        				ForJingJiaTable.remove(model.getCcy());
		        				ForJingJiaTable.put(ccyModel.getCcy(), ccyModel);
		        			}else{
		        				ccyModel.setBidFlag("0");
		        				ccyModel.setAskFlag("0");
		        				ForJingJiaTable.put(ccyModel.getCcy(), ccyModel);
		        			}
			    		}
		    		}
		    	}
		    	str=br.readLine();
		    }//while
		} catch (Exception e){
			e.printStackTrace();
	  }
	}
	
	public void genForXunJiaRate(){
		
	}
	
	public void genMarginRate(){
		if(MarginTable == null){
			MarginTable = new Hashtable();
		}
		//String urlStr = SysParamNameConst.RMB_JINGJIA_RATE_URL;
		String urlStr = null;
		URL url = null;
		URLConnection connection = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String str=null;
		String rsf=null;
		ResultSet rs = null;
		Statement st = null;
		try{
			//美元
			urlStr = "http://www.fxdiv.com/data/usd/";
			url = new URL(urlStr);//读取url对应的页面
		    connection = url.openConnection();
		    connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		    is = connection.getInputStream();
		    isr = new InputStreamReader(is,"GBK");
		    br = new BufferedReader(isr);
		    str=null;
		    while((str=br.readLine())!=null)//网页内容定位
	        {//先找到那个table
		    	if (str.indexOf("<li class=\"wt\"><span class=\"w1\">名称</span>")>=0) {
		        	break;
		    	}
//		    	//System.out.println(str);
	        }
		    str=br.readLine();
		    while (str!=null) {
		    	//System.out.println(str);
		    	str=str.trim();
		    	if (str.indexOf("<li")>=0){//每一条行数据
		    		CcyModel ccyModel = new CcyModel();
		    		int ccysNameIndex = str.indexOf("name=\"")+6;
		    		boolean isEnough = str.length()>ccysNameIndex+6?true:false;
		    		if(ccysNameIndex>=0&&isEnough){
		    			String ccys = str.substring(ccysNameIndex, ccysNameIndex+6);
		    			boolean isCcy = false;
		    			for(int i=0;i<W_CCYS.length;i++){
		    				if(W_CCYS[i].equals(ccys)){
		    					ccyModel.setCcy(W_CCYS[i]);
				    			//System.out.println(ccyModel.getCcy());
				    			isCcy = true;
		    				}
		    			}
		    			int bidPriceIndex = str.indexOf("<span class=\"w7") + 21;
		    			String bidPrice = getValue(str, bidPriceIndex);
			    		int askPriceIndex = str.indexOf("<span class=\"w8")+21;
			    		String askPrice = getValue(str, askPriceIndex);
			    		int endIndex = str.indexOf("<span class=\"w9");
			    		if(bidPriceIndex>=0&&askPriceIndex>=0&&endIndex>=0&&isCcy){
//			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(bidPriceIndex, askPriceIndex-7-21)));
//			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(askPriceIndex, endIndex-7)));
		        			RateDao rateDao = new RateDao();
			    			if(bidPrice.equals("")){
			    				Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			    				Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				bidPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
				        			bean.setRate(bidPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(bidPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(bidPrice));
			    			if(askPrice.equals("")){
			    				Rate bean = new Rate();
			        			bean.setAccType("B");
			    				bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			    				Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				askPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
			        				bean.setRate(askPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(askPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(askPrice));
			    			//System.out.println(ccyModel.getBidValue());
			    			//System.out.println(ccyModel.getAskValue());
			    			if(!MarginTable.containsKey(ccyModel.getCcy())){
			    				MarginTable.put(ccyModel.getCcy(), ccyModel);
			    			}else{
			    				MarginTable.remove(ccyModel.getCcy());
			    				MarginTable.put(ccyModel.getCcy(), ccyModel);
			    			}
			    		}
		    		}
		    	}
		    	str=br.readLine();
		    }//while
		    
		    //欧元
		    urlStr = "http://www.fxdiv.com/data/eur/";
			url = new URL(urlStr);//读取url对应的页面
		    connection = url.openConnection();
		    connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		    is = connection.getInputStream();
		    isr = new InputStreamReader(is,"GBK");
		    br = new BufferedReader(isr);
		    str=null;
		    while((str=br.readLine())!=null)//网页内容定位
	        {//先找到那个table
		    	if (str.indexOf("<li class=\"wt\"><span class=\"w1\">名称</span>")>=0) {
		        	break;
		    	}
//		    	//System.out.println(str);
	        }
		    str=br.readLine();
		    while (str!=null) {
		    	//System.out.println(str);
		    	str=str.trim();
		    	if (str.indexOf("<li")>=0){//每一条行数据
		    		CcyModel ccyModel = new CcyModel();
		    		int ccysNameIndex = str.indexOf("name=\"")+6;
		    		boolean isEnough = str.length()>ccysNameIndex+6?true:false;
		    		if(ccysNameIndex>=0&&isEnough){
		    			String ccys = str.substring(ccysNameIndex, ccysNameIndex+6);
		    			boolean isCcy = false;
		    			for(int i=0;i<W_CCYS.length;i++){
		    				if(W_CCYS[i].equals(ccys)){
		    					ccyModel.setCcy(W_CCYS[i]);
				    			//System.out.println(ccyModel.getCcy());
				    			isCcy = true;
		    				}
		    			}
		    			int bidPriceIndex = str.indexOf("<span class=\"w7") + 21;
		    			String bidPrice = getValue(str, bidPriceIndex);
			    		int askPriceIndex = str.indexOf("<span class=\"w8")+21;
			    		String askPrice = getValue(str, askPriceIndex);
			    		int endIndex = str.indexOf("<span class=\"w9");
			    		if(bidPriceIndex>=0&&askPriceIndex>=0&&endIndex>=0&&isCcy){
//			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(bidPriceIndex, askPriceIndex-7-21)));
//			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(askPriceIndex, endIndex-7)));
		        			RateDao rateDao = new RateDao();
			    			if(bidPrice.equals("")){
			    				Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				bidPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
				        			bean.setRate(bidPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(bidPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(bidPrice));
			    			if(askPrice.equals("")){
			    				Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				askPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
				        			bean.setRate(askPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(askPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(askPrice));
			    			//System.out.println(ccyModel.getBidValue());
			    			//System.out.println(ccyModel.getAskValue());
			    			if(!MarginTable.containsKey(ccyModel.getCcy())){
			    				MarginTable.put(ccyModel.getCcy(), ccyModel);
			    			}else{
			    				MarginTable.remove(ccyModel.getCcy());
			    				MarginTable.put(ccyModel.getCcy(), ccyModel);
			    			}
			    		}
		    		}
		    	}
		    	str=br.readLine();
		    }//while
		    
		    //英镑
		    urlStr = "http://www.fxdiv.com/data/gbp/";
			url = new URL(urlStr);//读取url对应的页面
		    connection = url.openConnection();
		    connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		    is = connection.getInputStream();
		    isr = new InputStreamReader(is,"GBK");
		    br = new BufferedReader(isr);
		    str=null;
		    while((str=br.readLine())!=null)//网页内容定位
	        {//先找到那个table
		    	if (str.indexOf("<li class=\"wt\"><span class=\"w1\">名称</span>")>=0) {
		        	break;
		    	}
//		    	//System.out.println(str);
	        }
		    str=br.readLine();
		    while (str!=null) {
		    	//System.out.println(str);
		    	str=str.trim();
		    	if (str.indexOf("<li")>=0){//每一条行数据
		    		CcyModel ccyModel = new CcyModel();
		    		int ccysNameIndex = str.indexOf("name=\"")+6;
		    		boolean isEnough = str.length()>ccysNameIndex+6?true:false;
		    		if(ccysNameIndex>=0&&isEnough){
		    			String ccys = str.substring(ccysNameIndex, ccysNameIndex+6);
		    			boolean isCcy = false;
		    			for(int i=0;i<W_CCYS.length;i++){
		    				if(W_CCYS[i].equals(ccys)){
		    					ccyModel.setCcy(W_CCYS[i]);
				    			//System.out.println(ccyModel.getCcy());
				    			isCcy = true;
		    				}
		    			}
		    			int bidPriceIndex = str.indexOf("<span class=\"w7") + 21;
		    			String bidPrice = getValue(str, bidPriceIndex);
			    		int askPriceIndex = str.indexOf("<span class=\"w8")+21;
			    		String askPrice = getValue(str, askPriceIndex);
			    		int endIndex = str.indexOf("<span class=\"w9");
			    		if(bidPriceIndex>=0&&askPriceIndex>=0&&endIndex>=0&&isCcy){
//			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(bidPriceIndex, askPriceIndex-7-21)));
//			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(askPriceIndex, endIndex-7)));
		        			RateDao rateDao = new RateDao();
			    			if(bidPrice.equals("")){
			    				Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				bidPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
				        			bean.setRate(bidPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(bidPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(bidPrice));
			    			if(askPrice.equals("")){
			    				Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				askPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
				        			bean.setRate(askPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(askPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(askPrice));
			    			//System.out.println(ccyModel.getBidValue());
			    			//System.out.println(ccyModel.getAskValue());
			    			if(!MarginTable.containsKey(ccyModel.getCcy())){
			    				MarginTable.put(ccyModel.getCcy(), ccyModel);
			    			}else{
			    				MarginTable.remove(ccyModel.getCcy());
			    				MarginTable.put(ccyModel.getCcy(), ccyModel);
			    			}
			    		}
		    		}
		    	}
		    	str=br.readLine();
		    }//while
		    
		    //澳元
		    urlStr = "http://www.fxdiv.com/data/aud/";
			url = new URL(urlStr);//读取url对应的页面
		    connection = url.openConnection();
		    connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		    is = connection.getInputStream();
		    isr = new InputStreamReader(is,"GBK");
		    br = new BufferedReader(isr);
		    str=null;
		    while((str=br.readLine())!=null)//网页内容定位
	        {//先找到那个table
		    	if (str.indexOf("<li class=\"wt\"><span class=\"w1\">名称</span>")>=0) {
		        	break;
		    	}
//		    	//System.out.println(str);
	        }
		    str=br.readLine();
		    while (str!=null) {
		    	//System.out.println(str);
		    	str=str.trim();
		    	if (str.indexOf("<li")>=0){//每一条行数据
		    		CcyModel ccyModel = new CcyModel();
		    		int ccysNameIndex = str.indexOf("name=\"")+6;
		    		boolean isEnough = str.length()>ccysNameIndex+6?true:false;
		    		if(ccysNameIndex>=0&&isEnough){
		    			String ccys = str.substring(ccysNameIndex, ccysNameIndex+6);
		    			boolean isCcy = false;
		    			for(int i=0;i<W_CCYS.length;i++){
		    				if(W_CCYS[i].equals(ccys)){
		    					ccyModel.setCcy(W_CCYS[i]);
				    			//System.out.println(ccyModel.getCcy());
				    			isCcy = true;
		    				}
		    			}
		    			int bidPriceIndex = str.indexOf("<span class=\"w7") + 21;
		    			String bidPrice = getValue(str, bidPriceIndex);
			    		int askPriceIndex = str.indexOf("<span class=\"w8")+21;
			    		String askPrice = getValue(str, bidPriceIndex);
			    		int endIndex = str.indexOf("<span class=\"w9");
			    		if(bidPriceIndex>=0&&askPriceIndex>=0&&endIndex>=0&&isCcy){
//			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(bidPriceIndex, askPriceIndex-7-21)));
//			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(askPriceIndex, endIndex-7)));
		        			RateDao rateDao = new RateDao();
			    			if(bidPrice.equals("")){
			    				Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				bidPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
				        			bean.setRate(bidPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(bidPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(bidPrice));
			    			if(askPrice.equals("")){
			    				Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				askPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
				        			bean.setRate(askPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(askPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(askPrice));
			    			//System.out.println(ccyModel.getBidValue());
			    			//System.out.println(ccyModel.getAskValue());
			    			if(!MarginTable.containsKey(ccyModel.getCcy())){
			    				MarginTable.put(ccyModel.getCcy(), ccyModel);
			    			}else{
			    				MarginTable.remove(ccyModel.getCcy());
			    				MarginTable.put(ccyModel.getCcy(), ccyModel);
			    			}
			    		}
		    		}
		    	}
		    	str=br.readLine();
		    }//while
		    
		    //加元
		    urlStr = "http://www.fxdiv.com/data/cad/";
			url = new URL(urlStr);//读取url对应的页面
		    connection = url.openConnection();
		    connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		    is = connection.getInputStream();
		    isr = new InputStreamReader(is,"GBK");
		    br = new BufferedReader(isr);
		    str=null;
		    while((str=br.readLine())!=null)//网页内容定位
	        {//先找到那个table
		    	if (str.indexOf("<li class=\"wt\"><span class=\"w1\">名称</span>")>=0) {
		        	break;
		    	}
//		    	//System.out.println(str);
	        }
		    str=br.readLine();
		    while (str!=null) {
		    	//System.out.println(str);
		    	str=str.trim();
		    	if (str.indexOf("<li")>=0){//每一条行数据
		    		CcyModel ccyModel = new CcyModel();
		    		int ccysNameIndex = str.indexOf("name=\"")+6;
		    		boolean isEnough = str.length()>ccysNameIndex+6?true:false;
		    		if(ccysNameIndex>=0&&isEnough){
		    			String ccys = str.substring(ccysNameIndex, ccysNameIndex+6);
		    			boolean isCcy = false;
		    			for(int i=0;i<W_CCYS.length;i++){
		    				if(W_CCYS[i].equals(ccys)){
		    					ccyModel.setCcy(W_CCYS[i]);
				    			//System.out.println(ccyModel.getCcy());
				    			isCcy = true;
		    				}
		    			}
		    			int bidPriceIndex = str.indexOf("<span class=\"w7") + 21;
		    			String bidPrice = getValue(str, bidPriceIndex);
			    		int askPriceIndex = str.indexOf("<span class=\"w8")+21;
			    		String askPrice = getValue(str, bidPriceIndex);
			    		int endIndex = str.indexOf("<span class=\"w9");
			    		if(bidPriceIndex>=0&&askPriceIndex>=0&&endIndex>=0&&isCcy){
//			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(bidPriceIndex, askPriceIndex-7-21)));
//			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(askPriceIndex, endIndex-7)));
		        			RateDao rateDao = new RateDao();
			    			if(bidPrice.equals("")){
			    				Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				bidPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
				        			bean.setRate(bidPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(bidPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(bidPrice));
			    			if(askPrice.equals("")){
			    				Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				askPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
				        			bean.setRate(askPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(askPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(askPrice));
			    			//System.out.println(ccyModel.getBidValue());
			    			//System.out.println(ccyModel.getAskValue());
			    			if(!MarginTable.containsKey(ccyModel.getCcy())){
			    				MarginTable.put(ccyModel.getCcy(), ccyModel);
			    			}else{
			    				MarginTable.remove(ccyModel.getCcy());
			    				MarginTable.put(ccyModel.getCcy(), ccyModel);
			    			}
			    		}
		    		}
		    	}
		    	str=br.readLine();
		    }//while
		    
		    //日元
		    urlStr = "http://www.fxdiv.com/data/jpy/";
			url = new URL(urlStr);//读取url对应的页面
		    connection = url.openConnection();
		    connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		    is = connection.getInputStream();
		    isr = new InputStreamReader(is,"GBK");
		    br = new BufferedReader(isr);
		    str=null;
		    while((str=br.readLine())!=null)//网页内容定位
	        {//先找到那个table
		    	if (str.indexOf("<li class=\"wt\"><span class=\"w1\">名称</span>")>=0) {
		        	break;
		    	}
//		    	//System.out.println(str);
	        }
		    str=br.readLine();
		    while (str!=null) {
		    	//System.out.println(str);
		    	str=str.trim();
		    	if (str.indexOf("<li")>=0){//每一条行数据
		    		CcyModel ccyModel = new CcyModel();
		    		int ccysNameIndex = str.indexOf("name=\"")+6;
		    		boolean isEnough = str.length()>ccysNameIndex+6?true:false;
		    		if(ccysNameIndex>=0&&isEnough){
		    			String ccys = str.substring(ccysNameIndex, ccysNameIndex+6);
		    			boolean isCcy = false;
		    			for(int i=0;i<W_CCYS.length;i++){
		    				if(W_CCYS[i].equals(ccys)){
		    					ccyModel.setCcy(W_CCYS[i]);
				    			//System.out.println(ccyModel.getCcy());
				    			isCcy = true;
		    				}
		    			}
		    			int bidPriceIndex = str.indexOf("<span class=\"w7") + 21;
		    			String bidPrice = getValue(str, bidPriceIndex);
			    		int askPriceIndex = str.indexOf("<span class=\"w8")+21;
			    		String askPrice = getValue(str, bidPriceIndex);
			    		int endIndex = str.indexOf("<span class=\"w9");
			    		if(bidPriceIndex>=0&&askPriceIndex>=0&&endIndex>=0&&isCcy){
//			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(bidPriceIndex, askPriceIndex-7-21)));
//			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(str.substring(askPriceIndex, endIndex-7)));
		        			RateDao rateDao = new RateDao();
			    			if(bidPrice.equals("")){
			    				Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				bidPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("1");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
				        			bean.setRate(bidPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(bidPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setBidValue(FormatParamUtil.getAmountAndPriceFmt(bidPrice));
			    			if(askPrice.equals("")){
			    				Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			    				askPrice = rate.getRate();
			    			}
			    			else{
			    				//更新汇率
			        			rsf = TransactionNestUtil.reference();
			        			Rate bean = new Rate();
			        			bean.setAccType("B");
			        			bean.setSourceCcy(ccyModel.getCcy().substring(0, 3));
			    				bean.setTargetCcy(ccyModel.getCcy().substring(3, 6));
			        			bean.setDirection("0");
			        			Rate rate = rateDao.getBeanByBean(bean, MatchMode.EXACT);
			        			if(rate == null){
				        			bean.setRate(askPrice);
				        			rateDao.addBean(bean);
			        			}
			        			else{
			        				rate.setRate(askPrice);
			        				rateDao.updateBean(rate);
			        			}
			        			TransactionNestUtil.releaseRef(rsf);
			    			}
			    			ccyModel.setAskValue(FormatParamUtil.getAmountAndPriceFmt(askPrice));
			    			//System.out.println(ccyModel.getBidValue());
			    			//System.out.println(ccyModel.getAskValue());
			    			if(!MarginTable.containsKey(ccyModel.getCcy())){
			    				MarginTable.put(ccyModel.getCcy(), ccyModel);
			    			}else{
			    				MarginTable.remove(ccyModel.getCcy());
			    				MarginTable.put(ccyModel.getCcy(), ccyModel);
			    			}
			    		}
		    		}
		    	}
		    	str=br.readLine();
		    }//while
		    for(int i=0;i<WB_CCYS.length;i++){
		    	String ccy = WB_CCYS[i];
	    		String newCcy = ccy.substring(3, 6) + ccy.substring(0, 3);
	    		if(MarginTable.containsKey(newCcy)){
	    			CcyModel ccyModel = new CcyModel();
	    			CcyModel model = (CcyModel)MarginTable.get(newCcy);
	    			double dBidValue = 1.0/Double.valueOf(model.getBidValue());
	    			double dAskValue = 1.0/Double.valueOf(model.getAskValue());
	    			String bidValue = FormatParamUtil.getAmountAndPriceFmt(String.valueOf(dBidValue));
	    			String askValue = FormatParamUtil.getAmountAndPriceFmt(String.valueOf(dAskValue));
	    			ccyModel.setCcy(ccy);
	    			ccyModel.setAskValue(askValue);
	    			ccyModel.setBidValue(bidValue);
	    			if(!MarginTable.containsKey(ccyModel.getCcy())){
	    				MarginTable.put(ccyModel.getCcy(), ccyModel);
	    			}else{
	    				MarginTable.remove(ccyModel.getCcy());
	    				MarginTable.put(ccyModel.getCcy(), ccyModel);
	    			}
	    		}
		    }
		    //更新汇率
		    /*for(int i=0;i<W_CCYS.length;i++){
		    	if(MarginTable.containsKey(W_CCYS[i])){
		    		CcyModel ccyModel = (CcyModel)MarginTable.get(W_CCYS[i]);
		    		rsf = TransactionNestUtil.reference();
					Rate rate = new Rate();
					RateDao rateDao = new RateDao();
					rate.setSourceCcy(ccyModel.getCcy().substring(0, 3));
					rate.setTargetCcy(ccyModel.getCcy().substring(3, 6));
					rate = rateDao.getBeanByBean(rate, MatchMode.ANYWHERE);
					if(rate == null){
						rate = new Rate();
						rate.setSourceCcy(ccyModel.getCcy().substring(0, 3));
						rate.setTargetCcy(ccyModel.getCcy().substring(3, 6));
		    			rate.setRate(ccyModel.getBidValue());
		    			rateDao.makePersistent(rate, false);
					}else{
						rate.setRate(ccyModel.getBidValue());
						ArrayList col=new ArrayList();
						col.add(rate);
						rateDao.batchUpdate(col);
					}
					TransactionNestUtil.releaseRef(rsf);
		    	}
		    }*/
		} catch (Exception e){
			e.printStackTrace();
	  }
	}
	
	public void genLibor(){
		if(LiborTable == null){
			LiborTable = new Hashtable();
		}
		//String urlStr = SysParamNameConst.RMB_JINGJIA_RATE_URL;
		String urlStr = "";
		URL url = null;
		URLConnection connection = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String str=null;
		try{
			urlStr = "http://www.fx678.com/rates/libor.html";
			url = new URL(urlStr);//读取url对应的页面
		    connection = url.openConnection();
		    connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		    is = connection.getInputStream();
		    isr = new InputStreamReader(is,"GBK");
		    br = new BufferedReader(isr);
		    str=null;
		    while((str=br.readLine())!=null)//网页内容定位
	        {
		    	//System.out.println(str);
		    	boolean isCcy = false;
		    	LiborModel liborModel = new LiborModel();
		    	if(str.indexOf("<div class=\"Libor_London_box\">")>=0){
		    		str=br.readLine();
				    while (str!=null&&str.indexOf("</table>")<0) {
				    	if(str.indexOf("</dd>")>=0){//截取币种
				    		int begin = str.indexOf("(");
				    		int end   = str.indexOf(")");
				    		String ccy = str.substring(begin+1, end);
				    		for(int i=0;i<MName_CCYS.length;i++){
			    				if(MName_CCYS[i].equals(ccy)){
			    					liborModel.setCcy(C_CCYS[i]);
			    					//System.out.println(liborModel.getCcy());
					    			isCcy = true;
			    				}
			    			}
				    	}
				    	int type = -1;//libor类型
				    	if(isCcy&&str.indexOf("cycle=n")>=0){//隔夜
				    		type=0;
				    	}
				    	if(isCcy&&str.indexOf("cycle=w1")>=0){//1周
				    		type=1;
				    	}
				    	if(isCcy&&str.indexOf("cycle=m1")>=0){//1个月
				    		type=2;
				    	}
				    	if(isCcy&&str.indexOf("cycle=m2")>=0){//2个月
				    		type=3;
				    	}
				    	if(isCcy&&str.indexOf("cycle=m3")>=0){//3个月
				    		type=4;
				    	}
				    	if(isCcy&&str.indexOf("cycle=m6")>=0){//6个月
				    		type=5;
				    	}
				    	if(isCcy&&str.indexOf("cycle=m12")>=0){//12个月
				    		type=6;
				    	}
				    	if(type!=-1){
				    		String libor = "";
				    		str=br.readLine();
				    		for(int i=0;i<4&&str!=null;i++){
				    			if(i==3){
				    				libor = str.trim();
				    			}
				    			str=br.readLine();
				    		}
				    		switch(type){
				    			case 0:{
				    				liborModel.setOneDay(libor);
				    				//System.out.println(type+":"+liborModel.getOneDay());
				    				break;
				    			}
				    			case 1:{
				    				liborModel.setOneWeek(libor);
				    				//System.out.println(type+":"+liborModel.getOneWeek());
				    				break;
				    			}
				    			case 2:{
				    				liborModel.setOneMonth(libor);
				    				//System.out.println(type+":"+liborModel.getOneMonth());
				    				break;
				    			}
				    			case 3:{
				    				liborModel.setTwoMonth(libor);
				    				//System.out.println(type+":"+liborModel.getTwoMonth());
				    				break;
				    			}
				    			case 4:{
				    				liborModel.setThreeMonth(libor);
				    				//System.out.println(type+":"+liborModel.getThreeMonth());
				    				break;
				    			}
				    			case 5:{
				    				liborModel.setSixMonth(libor);
				    				//System.out.println(type+":"+liborModel.getSixMonth());
				    				break;
				    			}
				    			case 6:{
				    				liborModel.setTwelveMonth(libor);
				    				//System.out.println(type+":"+liborModel.getTwelveMonth());
				    				break;
				    			}
				    			default:break;
				    		}
				    	}
				    	str=br.readLine();
				    }//while
				    if(isCcy){
				    	LiborTable.put(liborModel.getCcy(), liborModel);
			    	}
		    	}
	        }
		    //人民币shibor
		    urlStr = "http://www.fx678.com/rates/Shibor.html";
			url = new URL(urlStr);//读取url对应的页面
		    connection = url.openConnection();
		    connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		    is = connection.getInputStream();
		    isr = new InputStreamReader(is,"GBK");
		    br = new BufferedReader(isr);
		    str=null;
		    boolean isCcy = false;
		    while((str=br.readLine())!=null)//网页内容定位
	        {
//		    	System.out.println(str);
		    	LiborModel liborModel = new LiborModel();
		    	if(str.indexOf("<div class=\"Libor_London_box_s\">")>=0){
		    		liborModel.setCcy("CNY");
		    		isCcy = true;
		    		str=br.readLine();
				    while (str!=null&&str.indexOf("</table>")<0) {
				    	int type = -1;//libor类型
				    	if(isCcy&&str.indexOf("cycle=n")>=0){//隔夜
				    		type=0;
				    	}
				    	if(isCcy&&str.indexOf("cycle=w1")>=0){//1周
				    		type=1;
				    	}
				    	if(isCcy&&str.indexOf("cycle=m1")>=0){//1个月
				    		type=2;
				    	}
				    	if(isCcy&&str.indexOf("cycle=m2")>=0){//2个月
				    		type=3;
				    	}
				    	if(isCcy&&str.indexOf("cycle=m3")>=0){//3个月
				    		type=4;
				    	}
				    	if(isCcy&&str.indexOf("cycle=m6")>=0){//6个月
				    		type=5;
				    	}
				    	if(isCcy&&str.indexOf("cycle=m12")>=0){//12个月
				    		type=6;
				    	}
				    	if(type!=-1){
				    		String libor = "";
				    		str=br.readLine();
				    		for(int i=0;i<4&&str!=null;i++){
				    			if(i==3){
				    				libor = str.trim();
				    			}
				    			str=br.readLine();
				    		}
				    		switch(type){
				    			case 0:{
				    				liborModel.setOneDay(libor);
				    				//System.out.println(type+":"+liborModel.getOneDay());
				    				break;
				    			}
				    			case 1:{
				    				liborModel.setOneWeek(libor);
				    				//System.out.println(type+":"+liborModel.getOneWeek());
				    				break;
				    			}
				    			case 2:{
				    				liborModel.setOneMonth(libor);
				    				//System.out.println(type+":"+liborModel.getOneMonth());
				    				break;
				    			}
				    			case 3:{
				    				liborModel.setTwoMonth(libor);
				    				//System.out.println(type+":"+liborModel.getTwoMonth());
				    				break;
				    			}
				    			case 4:{
				    				liborModel.setThreeMonth(libor);
				    				//System.out.println(type+":"+liborModel.getThreeMonth());
				    				break;
				    			}
				    			case 5:{
				    				liborModel.setSixMonth(libor);
				    				//System.out.println(type+":"+liborModel.getSixMonth());
				    				break;
				    			}
				    			case 6:{
				    				liborModel.setTwelveMonth(libor);
				    				//System.out.println(type+":"+liborModel.getTwelveMonth());
				    				break;
				    			}
				    			default:break;
				    		}
				    	}
				    	str=br.readLine();
				    }//while
				    if(isCcy){
				    	LiborTable.put(liborModel.getCcy(), liborModel);
			    	}
		    	}
	        }//while
		} catch (Exception e){
			e.printStackTrace();
	  }
	}
	
	/**
	 * 获取人民币竞价买卖价
	 * @return
	 */
	public Collection getRmbJingJiaRate() {
		Collection collection = null;
		if(RmbJingJiaTable==null){
			genRmbJingJiaRate();
		}
		collection = RmbJingJiaTable.values();
		return sortAsc(collection);
	} 
	
	/**
	 * 获取人民币询价买卖价
	 * @return
	 */
	public Collection getRmbXunJiaRate() {
		Collection collection = null;
		if(RmbXunJiaTable==null){
			genRmbXunJiaRate();
		}
		collection = RmbXunJiaTable.values();
		return sortAsc(collection);
	} 
	
	/**
	 * 获取外币竞价买卖价
	 * @return
	 */
	public Collection getForJingJiaRate() {
		Collection collection = null;
		if(ForJingJiaTable==null||ForJingJiaTable.size()==0){
			genForJingJiaRate();
		}
		collection = ForJingJiaTable.values();
		return sortAsc(collection);
	} 
	
	/**
	 * 获取外币询价买卖价
	 * @return  
	 */
	public Collection getForXunJiaRate() {
		Collection collection = null;
		if(ForJingJiaTable==null){
			genForJingJiaRate();
		}
		collection = ForJingJiaTable.values();
		return sortAsc(collection);
	} 
	
	/**
	 * 获取保证金买卖价
	 * @return
	 */
	public Collection getMarginRate() {
		Collection collection = null;
		if(MarginTable==null){
			genMarginRate();
		}
		collection = MarginTable.values();
		return sortAsc(collection);
	} 
	
	/**
	 * 获取libor
	 * @return
	 */
	public Collection getLibor(){
		Collection collection = null;
		if(LiborTable==null){
			genLibor();
		}
		collection = LiborTable.values();
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
	
	public String getMoneyDivise(String money){
		double dMoney = Double.parseDouble(money);
		dMoney = dMoney/100;
		return String.valueOf(dMoney);
	}

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void startRatePolling(){
		System.out.println("------买卖价刷新开始------");
		RandomRate randomRate = new RandomRate();
		randomRate.refresh();
	}

	private String getValue(String str, int priceIndex){
		String value = "";
		for(int index = 0; index < str.length(); index++){
			String s = str.substring(priceIndex + index, priceIndex + index + 1);
			if(s.equals("<")){
				break;
			}
			else{
				value = value + s;
			}
		}
		return value;
	}
	
	/*public RateDao getRateDao() {
		return rateDao;
	}

	public void setRateDao(RateDao rateDao) {
		this.rateDao = rateDao;
	}*/
}
