package com.talent.forex.util;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.criterion.MatchMode;

import com.talent.forex.bean.domain.Rate;
import com.talent.forex.bean.model.CcyModel;
import com.talent.forex.dao.RateDao;
import com.talent.forex.modules.rateFactory.RateReceive;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: Talent Information Solutions Ltd.
 * </p>
 * 
 * @author lcw
 *
 */
public class RateUtil {
	
	/**
	 * ��ȡʵʱ���ʣ���Ҫ���ڼ���ӯ�����ӯ����(֤ʵ��Ч)
	 * @param tradeType ��������(1λ��д�ַ���)
	 * @param ccy ����(ֻ����3λ���ַ���)
	 * @return ����
	 */
	public static double getRateByCcy(String tradeType, String ccy){
		double r = 0d;
		Collection<?> origin = null;
		if(tradeType.equals("C")){
			origin = RateReceive.getInstance().getRmbJingJiaRate();
			Iterator<?> iterator = origin.iterator();
			while(iterator.hasNext()){
				CcyModel ccyModel = (CcyModel) iterator.next();
				if(ccyModel.getCcy().equals(ccy.substring(0, 3))){ 
					r = Double.parseDouble(ccyModel.getBidValue());
					break;
				}
			}
		}
		else if(tradeType.equals("W")){
			origin = RateReceive.getInstance().getForJingJiaRate();
			Iterator<?> iterator = origin.iterator();
			while(iterator.hasNext()){
				CcyModel ccyModel = (CcyModel) iterator.next();
				String ccy2 = null;
				if(ccy.equals("EUR") || ccy.equals("GBP") || ccy.equals("AUD")){
					ccy2 = ccy + "USD";
				}
				else if(ccy.equals("JPY") || ccy.equals("CAD")){
					ccy2 = "USD" + ccy;
					/*if(ccyModel.getCcy().equals(ccy2)){
						r = Double.parseDouble(ccyModel.getAskValue());
						r = 1.0 / r;
						break;
					}*/
				}
				if(ccyModel.getCcy().equals(ccy2)){
					r = Double.parseDouble(ccyModel.getBidValue());
					break;
				}
			}
		}
		else if(tradeType.equals("B")){
			origin = RateReceive.getInstance().getMarginRate();
			Iterator<?> iterator = origin.iterator();
			while(iterator.hasNext()){
				CcyModel ccyModel = (CcyModel) iterator.next();
				String ccy2 = null;
				if(ccy.equals("EUR") || ccy.equals("GBP") || ccy.equals("AUD")){
					ccy2 = ccy + "USD";
				}
				else if(ccy.equals("JPY") || ccy.equals("CAD")){
					ccy2 = "USD" + ccy;
					/*if(ccyModel.getCcy().equals(ccy2)){
						r = Double.parseDouble(ccyModel.getAskValue());
						r = 1.0 / r;
						break;
					}*/
				}
				if(ccyModel.getCcy().equals(ccy2)){
					r = Double.parseDouble(ccyModel.getBidValue());
					break;
				}
			}
		}
		return r;
	}
	
	/**
	 * ��ȡ�����ʵʱ����(֤ʵ��Ч)
	 * @param type ����(B)��ѯ��(A)
	 * @param ccy ����(ֻ����3λ���ַ���)
	 * @param direction ��(1)����(0)
	 * @return ����
	 */
	public static double getCNYRateByCcy(String type, String ccy, String direction){
		double r = 0d;
		Collection<?> origin = null;
		if(type.equals("B")){
			origin = RateReceive.getInstance().getRmbJingJiaRate();
		}
		else if(type.equals("A")){
			origin = RateReceive.getInstance().getRmbXunJiaRate();
		}
		Iterator<?> iterator = origin.iterator();
		while(iterator.hasNext()){
			CcyModel ccyModel = (CcyModel) iterator.next();
			if(ccyModel.getCcy().equals(ccy.substring(0, 3))){ 
				if(direction.equals("1")){//��
					r = Double.parseDouble(ccyModel.getBidValue());
				}
				else if(direction.equals("0")){//��
					r = Double.parseDouble(ccyModel.getAskValue());
				}
				break;
			}
		}
		return r;
	}
	
	/**
	 * ��ȡ���ʵʱ����(֤ʵ��Ч)
	 * @param ccy ����(ֻ����3λ���ַ���)
	 * @param direction ��(1)����(0)
	 * @return ����
	 */
	public static double getForRateByCcy(String ccy, String direction){
		double r = 0d;
		Collection<?> origin = null;
		origin = RateReceive.getInstance().getForJingJiaRate();//����ѯ�۶�һ���ļ۸�
		Iterator<?> iterator = origin.iterator();
		while(iterator.hasNext()){
			CcyModel ccyModel = (CcyModel) iterator.next();
			String ccy2 = null;
			if(ccy.equals("EUR") || ccy.equals("GBP") || ccy.equals("AUD")){
				ccy2 = ccy + "USD";
			}
			else if(ccy.equals("JPY") || ccy.equals("CAD")){
				ccy2 = "USD" + ccy;
			}
			if(ccyModel.getCcy().equals(ccy2)){ 
				if(direction.equals("1")){//��
					r = Double.parseDouble(ccyModel.getBidValue());
				}
				else if(direction.equals("0")){//��
					r = Double.parseDouble(ccyModel.getAskValue());
				}
				break;
			}
		}
		return r;
	}
	
	/**
	 * ��ȡ��֤��ʵʱ����(֤ʵ��Ч)
	 * @param ccy ����(ֻ����6λ���ַ�����2���ֺϲ�)
	 * @param direction ��(1)����(0)
	 * @return ����
	 */
	public static double getMarginRateByCcy(String ccy, String direction){
		double r = 0d;
		Collection<?> origin = null;
		origin = RateReceive.getInstance().getMarginRate();
		Iterator<?> iterator = origin.iterator();
		while(iterator.hasNext()){
			CcyModel ccyModel = (CcyModel) iterator.next();
			
			if(ccyModel.getCcy().equals(ccy)){ 
				if(direction.equals("1")){//��
					r = Double.parseDouble(ccyModel.getBidValue());
				}
				else if(direction.equals("0")){//��
					r = Double.parseDouble(ccyModel.getAskValue());
				}
				break;
			}
		}
		return r;
	}
	
	/**
	 * �������ʱ�Rate�ķ���(��ͣ��)
	 * @param source
	 * @param target
	 * @return
	 */
	/*public static double getRate(String source, String target){
		RateDao rateDao = new RateDao();
		Rate bean = new Rate();
		bean.setSourceCcy(source);
		bean.setTargetCcy(target);
		Rate rate = rateDao.getBeanByBean(bean, MatchMode.ANYWHERE);
		return Double.parseDouble(rate.getRate());
	}*/
	
	/**
	 * �õ���Ӧ����     ����һ�����Ҿ��۵������۸�
	 * @param weCcy
	 * @return
	 */
	public static CcyModel getRateByCCy(String tranType ,String ccy){
		Collection<?> origin;
		if(tranType.equals("C")){
			origin = RateReceive.getInstance().getRmbJingJiaRate();
			Iterator<?> iterator = origin.iterator();
			while(iterator.hasNext()){
				CcyModel ccyModel = (CcyModel) iterator.next();
				if(ccyModel.getCcy().equals(ccy.substring(0, 3))){ 
					return ccyModel;
				}
			}
		}else{
			origin = RateReceive.getInstance().getForJingJiaRate();
			Iterator<?> iterator = origin.iterator();
			while(iterator.hasNext()){
				CcyModel ccyModel = (CcyModel) iterator.next();
				if(ccyModel.getCcy().equals(ccy)){
					return ccyModel;
				}
			}
		}
		
		
		return null;//��ȷ��session���е�ǰ���ҶԼ۸�
	}

}