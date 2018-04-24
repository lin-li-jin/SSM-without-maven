package com.talent.forex.modules.rateFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.talent.forex.bean.model.CcyModel;
import com.talent.forex.bean.model.LiborModel;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetRateUtil;

/**
 * 
 * <p>
 * Title:�������������
 * </p>
 * <p>
 * Description:����ҳ��ȡ����������Ϊ��ʼ���ݣ�����ÿ��ȡһ�Σ�ʹ��������������С���ﵽ�仯��Ч��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: Talent Information Solutions Ltd.
 * </p>
 * 
 * @author gary
 *
 */
public class RandomRate extends Rate implements Comparator{
	private static Hashtable RmbJingJiaTable = null;
	private static Hashtable RmbXunJiaTable = null;
	private static Hashtable ForJingJiaTable = null;
	private static Hashtable ForXunJiaTable = null;
	private static Hashtable MarginTable = null;
	private static Hashtable LiborTable = null;
	
	public void refresh(){
		genRmbJingJiaRate();
		genRmbXunJiaRate();
		genForJingJiaRate();
		genMarginRate();
		genLibor();
	}
	
	/**
	 * ��ȡ����Ҿ��۽�������������
	 */
	public void genRmbJingJiaRate(){
		Collection col = null;
		if(RmbJingJiaTable == null){
			RmbJingJiaTable = new Hashtable();
			col = (Collection)GetRateUtil.getInstance().getRmbJingJiaRate();
		}else{
			Hashtable table = (Hashtable) RmbJingJiaTable.clone();
			col = table.values();
		}
		Iterator it = col.iterator();
		String askValue = null;
		int value = -1;
		int randomValue = -1;
		//������ɼ۸�
		while(it.hasNext()){
			CcyModel model = (CcyModel)it.next();
			//�ı�ask�۸�
			int direction = getRandomDirection();
			String newAskValue = getValue(model.getAskValue(),direction);
			if(model.getAskValue().equals(newAskValue)){
				model.setAskFlag("0");
			}else{
				if(direction == 0){
					model.setAskFlag("-1");
				}else{
					model.setAskFlag("1");
				}
			}
			model.setAskValue(FormatParamUtil.getAmountAndPriceFmt(newAskValue));
			//�ı�bid�۸�
			//direction = getRandomDirection();
			String newBidValue = getValue(model.getBidValue(),direction);
			if(model.getBidValue().equals(newBidValue)){
				model.setBidFlag("0");
			}else{
				if(direction == 0){
					model.setBidFlag("-1");
				}else{
					model.setBidFlag("1");
				}
			}
			model.setBidValue(FormatParamUtil.getAmountAndPriceFmt(newBidValue));
			if(RmbJingJiaTable.containsKey(model.getCcy())){
				RmbJingJiaTable.remove(model.getCcy());
				RmbJingJiaTable.put(model.getCcy(), model);
			}else{
				RmbJingJiaTable.put(model.getCcy(), model);
			}
		}
	}
	
	/**
	 * ��ȡ�����ѯ�۽�������������
	 */
	public void genRmbXunJiaRate(){
		Collection col = null;
		if(RmbXunJiaTable == null){
			RmbXunJiaTable = new Hashtable();
			col = (Collection)GetRateUtil.getInstance().getRmbXunJiaRate();
		}else{
			Hashtable table = (Hashtable) RmbXunJiaTable.clone();
			col = table.values();
		}
		Iterator it = col.iterator();
		String askValue = null;
		int value = -1;
		int randomValue = -1;
		//������ɼ۸�
		while(it.hasNext()){
			CcyModel model = (CcyModel)it.next();
			//�ı�ask�۸�
			int direction = getRandomDirection();
			String newAskValue = getValue(model.getAskValue(),direction);
			if(model.getAskValue().equals(newAskValue)){
				model.setAskFlag("0");
			}else{
				if(direction == 0){
					model.setAskFlag("-1");
				}else{
					model.setAskFlag("1");
				}
			}
			model.setAskValue(FormatParamUtil.getAmountAndPriceFmt(newAskValue));
			//�ı�bid�۸�
			//direction = getRandomDirection();
			String newBidValue = getValue(model.getBidValue(),direction);
			if(model.getBidValue().equals(newBidValue)){
				model.setBidFlag("0");
			}else{
				if(direction == 0){
					model.setBidFlag("-1");
				}else{
					model.setBidFlag("1");
				}
			}
			model.setBidValue(FormatParamUtil.getAmountAndPriceFmt(newBidValue));
			if(RmbXunJiaTable.containsKey(model.getCcy())){
				RmbXunJiaTable.remove(model.getCcy());
				RmbXunJiaTable.put(model.getCcy(), model);
			}else{
				RmbXunJiaTable.put(model.getCcy(), model);
			}
		}
	}

	/**
	 * ��ȡ��Ҿ��۽�������������
	 */
	public void genForJingJiaRate(){
		Collection col = null;
		if(ForJingJiaTable == null||ForJingJiaTable.size()==0){
			ForJingJiaTable = new Hashtable();
			col = (Collection)GetRateUtil.getInstance().getForJingJiaRate();
		}else{
			Hashtable table = (Hashtable) ForJingJiaTable.clone();
			col = table.values();
		}
		Iterator it = col.iterator();
		String askValue = null;
		int value = -1;
		int randomValue = -1;
		//������ɼ۸�
		while(it.hasNext()){
			CcyModel model = (CcyModel)it.next();
			//�ı�ask�۸�
			int direction = getRandomDirection();
			String newAskValue = getValue(model.getAskValue(),direction);
			if(model.getAskValue().equals(newAskValue)){
				model.setAskFlag("0");
			}else{
				if(direction == 0){
					model.setAskFlag("-1");
				}else{
					model.setAskFlag("1");
				}
			}
			model.setAskValue(FormatParamUtil.getAmountAndPriceFmt(newAskValue));
			//�ı�bid�۸�
			//direction = getRandomDirection();
			String newBidValue = getValue(model.getBidValue(),direction);
			if(model.getBidValue().equals(newBidValue)){
				model.setBidFlag("0");
			}else{
				if(direction == 0){
					model.setBidFlag("-1");
				}else{
					model.setBidFlag("1");
				}
			}
			model.setBidValue(FormatParamUtil.getAmountAndPriceFmt(newBidValue));
			if(ForJingJiaTable.containsKey(model.getCcy())){
				ForJingJiaTable.remove(model.getCcy());
				ForJingJiaTable.put(model.getCcy(), model);
			}else{
				ForJingJiaTable.put(model.getCcy(), model);
			}
		}
	}
	
	/**
	 * ��ȡ���ѯ�۽�������������
	 */
	public void genForXunJiaRate(){
		Collection col = null;
		if(ForXunJiaTable == null){
			ForXunJiaTable = new Hashtable();
			col = (Collection)GetRateUtil.getInstance().getForXunJiaRate();
		}else{
			Hashtable table = (Hashtable) ForXunJiaTable.clone();
			col = table.values();
		}
		Iterator it = col.iterator();
		String askValue = null;
		int value = -1;
		int randomValue = -1;
		//������ɼ۸�
		while(it.hasNext()){
			CcyModel model = (CcyModel)it.next();
			//�ı�ask�۸�
			int direction = getRandomDirection();
			String newAskValue = getValue(model.getAskValue(),direction);
			if(model.getAskValue().equals(newAskValue)){
				model.setAskFlag("0");
			}else{
				if(direction == 0){
					model.setAskFlag("-1");
				}else{
					model.setAskFlag("1");
				}
			}
			model.setAskValue(FormatParamUtil.getAmountAndPriceFmt(newAskValue));
			//�ı�bid�۸�
			//direction = getRandomDirection();
			String newBidValue = getValue(model.getBidValue(),direction);
			if(model.getBidValue().equals(newBidValue)){
				model.setBidFlag("0");
			}else{
				if(direction == 0){
					model.setBidFlag("-1");
				}else{
					model.setBidFlag("1");
				}
			}
			model.setBidValue(FormatParamUtil.getAmountAndPriceFmt(newBidValue));
			if(ForXunJiaTable.containsKey(model.getCcy())){
				ForXunJiaTable.remove(model.getCcy());
				ForXunJiaTable.put(model.getCcy(), model);
			}else{
				ForXunJiaTable.put(model.getCcy(), model);
			}
		}
	}
	
	/**
	 * ��ȡ��֤��������������
	 */
	public void genMarginRate(){
		Collection col = null;
		if(MarginTable == null){
			MarginTable = new Hashtable();
			col = (Collection)GetRateUtil.getInstance().getMarginRate();
		}else{
			Hashtable table = (Hashtable) MarginTable.clone();
			col = table.values();
		}
		Iterator it = col.iterator();
		String askValue = null;
		int value = -1;
		int randomValue = -1;
		//������ɼ۸�
		while(it.hasNext()){
			CcyModel model = (CcyModel)it.next();
			//�ı�ask�۸�
			int direction = getRandomDirection();
			String newAskValue = getValue(model.getAskValue(),direction);
			if(model.getAskValue().equals(newAskValue)){
				model.setAskFlag("0");
			}else{
				if(direction == 0){
					model.setAskFlag("-1");
				}else{
					model.setAskFlag("1");
				}
			}
			model.setAskValue(FormatParamUtil.getAmountAndPriceFmt(newAskValue));
			//�ı�bid�۸�
			//direction = getRandomDirection();
			String newBidValue = getValue(model.getBidValue(),direction);
			if(model.getBidValue().equals(newBidValue)){
				model.setBidFlag("0");
			}else{
				if(direction == 0){
					model.setBidFlag("-1");
				}else{
					model.setBidFlag("1");
				}
			}
			model.setBidValue(FormatParamUtil.getAmountAndPriceFmt(newBidValue));
			if(MarginTable.containsKey(model.getCcy())){
				MarginTable.remove(model.getCcy());
				MarginTable.put(model.getCcy(), model);
			}else{
				MarginTable.put(model.getCcy(), model);
			}
		}
	}
	
	public void genLibor(){
		Collection col = null;
		if(LiborTable == null){
			LiborTable = new Hashtable();
			col = (Collection)GetRateUtil.getInstance().getLibor();
		}else{
			Hashtable table = (Hashtable) LiborTable.clone();
			col = table.values();
		}
		Iterator it = col.iterator();
		while(it.hasNext()){
			LiborModel model = (LiborModel)it.next();
			if(LiborTable.containsKey(model.getCcy())){
				LiborTable.remove(model.getCcy());
				LiborTable.put(model.getCcy(), model);
			}else{
				LiborTable.put(model.getCcy(), model);
			}
		}
	}
	
	/**
	 * ��ȡ����Ҿ���������
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
	 * ��ȡ�����ѯ��������
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
	 * ��ȡ��Ҿ���������
	 * @return
	 */
	public Collection getForJingJiaRate() {
		Collection collection = null;
		if(ForJingJiaTable==null){
			genForJingJiaRate();
		}
		collection = ForJingJiaTable.values();
		return sortAsc(collection);
	} 
	
	/**
	 * ��ȡ���ѯ��������
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
	 * ��ȡ��֤��������
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
	 * ��ȡlibor
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
	
	/**
	 * ������ɼ۸�仯���ȣ�0~9
	 * @return
	 */
	private int getRandomNum(){
		Random random = new Random();
		int Num = Math.abs(random.nextInt())%10;
		return Num;
	}
	
	/**
	 * ������ɱ仯����
	 * ��0����ʾ��
	 * ��1����ʾ��
	 * @return
	 */
	private int getRandomDirection(){
		Random random = new Random();
		int Num = random.nextInt(2);
		return Num;
	}
	
	/**
	 * ���ؼ۸�仯���
	 * @param value
	 * @param direction
	 * @return
	 */
	private String getValue(String value,int direction){
		int result = -1;
		int sourceValue = Integer.valueOf(value.substring(value.length()-2, value.length()));//��ȡ����λ
		int range = getRandomNum();
		//�仯�������<0��>100
		if(direction == 0){
			result = (sourceValue<range)?sourceValue:(sourceValue-range);
		}else{
			result = (sourceValue+range>=100)?sourceValue:(sourceValue+range);
		}
		//���ؽ��
		if(result>=0&&result<=9){
			return value.substring(0, value.length()-2)+"0"+String.valueOf(result);
		}else{
			return value.substring(0, value.length()-2)+String.valueOf(result);
		}
	}

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
