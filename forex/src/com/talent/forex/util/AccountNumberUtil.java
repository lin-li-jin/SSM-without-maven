package com.talent.forex.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.talent.forex.bean.domain.GroupSysParam;
import com.talent.forex.bean.model.AccInfoModel;
import com.talent.forex.bean.model.TranFlowMappingModel;
/*
 * Create By    : lzc
 * Description  : �û��˻�����
 * Modify Date  : 2014-08-07
 */
public class AccountNumberUtil {

	/**
	 * ���˻���Ϣ�б��еĽ��׽������
	 */
	public static void setAmountRank(List<TranFlowMappingModel> accountNumberList, List<TranFlowMappingModel> amountList){
		for (int i = 0; i < amountList.size(); i++){
			for (int j = 0; j < accountNumberList.size(); j++){
				if (accountNumberList.get(j).getUserNum().equals(amountList.get(i).getUserNum())){
					accountNumberList.get(j).setTradeAmountRank(i + 1);
					break;
				}
			}
		}
	}
	
	/**
	 * ���˻���Ϣ�б��еĽ��״�������
	 */
	public static void setNumberRank(List<TranFlowMappingModel> list, List<TranFlowMappingModel> list2){
		for (int i=0; i<list2.size(); i++){
			for (int j=0; j<list.size(); j++){
				if (list.get(j).getUserNum().equals(list2.get(i).getUserNum())){
					list.get(j).setTradeNumberRank(i + 1);
					break;
				}
			}
		}
	}
	
	/**
	 * ���˻���Ϣ�б��е�ӯ��������
	 */
	public static void setRateRank(List<TranFlowMappingModel> list, int[] a){
		for (int i=0; i<a.length; i++){
			list.get(a[i]).setRateRank(i + 1);
		}
	}
	
	/**
	 * ������õ��˻�ӯ���ʼ��뵽�˻���Ϣ�б���
	 */
	public static void setRate(List<TranFlowMappingModel> list, Map<String,Double> map){
		for (TranFlowMappingModel model : list){
			model.setRate(map.get(model.getUserNum()));
		}
	}
	
	/**
	 * ����ÿ���û����˻�ӯ����
	 * type:�˻�����
	 */
	public static Map<String,Double> countRateList(List<AccInfoModel> list,String type){
		double sum = 0.0;
		int i = 0,j = 0;
		String flag="";
		Map<String,Double> map = new HashMap<String,Double>();
		while(i<list.size()){
			AccInfoModel accInfo=list.get(i);
			sum = Double.parseDouble(accInfo.getAmount()); 
			for (j=i; j<list.size(); j++){
				if (j == list.size() -1){
					j++;
					break;
				}
				if (list.get(j).getUserNum().equals(list.get(j+1).getUserNum())){
					/*����Ӧ�˻��ĸ�������ת��Ϊͳһ�ı��֣�
					 * ���磺������˻��Ļ����ͽ�������˻��еĸ������ֶ�ת��Ϊ�����������ӯ���ʣ�
					 * ����˻��Ļ����ͽ�����˻��еĸ������ֶ�ת��Ϊ��Ԫ������ӯ���ʣ�
					 * ��֤���˻��Ļ����ͽ���֤���˻��еĸ������ֶ�ת��Ϊ��Ԫ������ӯ���ʣ�
					 * */
					if(type.equals("C")){
						Double rate = RateUtil.getRateByCcy("C", list.get(j+1).getCcy());
						//Double rate=RateUtil.getRate(, "CNY");
						sum += Double.parseDouble(list.get(j+1).getAmount())*rate;
					}else if(type.equals("W")){
						Double rate = RateUtil.getRateByCcy("W", list.get(j+1).getCcy());
						//Double rate=RateUtil.getRate(list.get(j+1).getCcy(), "USD");
						sum += Double.parseDouble(list.get(j+1).getAmount())*rate;
						
					}else if(type.equals("B")){
						Double rate = RateUtil.getRateByCcy("B", list.get(j+1).getCcy());
						//Double rate=RateUtil.getRate(list.get(j+1).getCcy(), "USD");
						sum += Double.parseDouble(list.get(j+1).getAmount())*rate;
					}
				}
				else{
					j++;
					break;
				}
			}
			//��ӯ���ʱ�����λС����
			flag=FormatParamUtil.getAmountAndPriceFmt(""+sum/Double.parseDouble(list.get(i).getOriginalAmt()));
			map.put(list.get(i).getUserNum(), Double.parseDouble(flag));
			i = j;
		}
		return map;
	}
	
	
	/**
	 * ����ÿ���û����˻�ӯ����
	 * type:�˻�����
	 * return �����˻���Ӧ��ӯ����
	 */
	public static Map<String,Double> getCountRateList(List<AccInfoModel> list, String type){
		double sum = 0.0;
		String flag = "";
		Map<String,Double> mapAmount = new HashMap<String,Double>();//�˻��ܽ���hashMap
		Map<String,Double> mapOriginalAmt = new HashMap<String,Double>();//�˻���ʼ�ܽ���hashMap
		Map<String,Double> map = new HashMap<String,Double>();//�˻�ӯ���ʵ�hashMap
		for(int i = 0; i < list.size(); i++){
			String userNum = list.get(i).getUserNum();
			String amount = list.get(i).getAmount();
			String originalAmt = list.get(i).getOriginalAmt();
			double tempSum = 0.0;//��ʱ����
			
			//���hashmap�Ƿ��м�¼�������ԭ�����ӣ�������Ӽ�¼
			if(mapAmount.get(userNum) == null){
				mapAmount.put(userNum, getPriceByTypeAndCcy(list.get(i).getCcy(), type, amount));
			}else{
				tempSum=mapAmount.get(userNum)+getPriceByTypeAndCcy(list.get(i).getCcy(), type, amount);
				mapAmount.put(userNum, tempSum);
			}
			if(mapOriginalAmt.get(userNum)==null){
				mapOriginalAmt.put(userNum, getPriceByTypeAndCcy(list.get(i).getCcy(), type, originalAmt));
			}else{
				tempSum=mapOriginalAmt.get(userNum)+getPriceByTypeAndCcy(list.get(i).getCcy(), type, originalAmt);
				mapOriginalAmt.put(userNum, tempSum);
			}
		}
		//����ӯ����
		Iterator mapAmoutInterator=mapAmount.entrySet().iterator();
		while(mapAmoutInterator.hasNext()){
			Map.Entry<String, Double> entry = (Map.Entry<String,Double>)mapAmoutInterator.next();
			String userNum = entry.getKey();
			Double sumAmount = entry.getValue();
			Double sumOriginalAmt = mapOriginalAmt.get(userNum);
			double profitLossRate = 1;
			if(sumOriginalAmt == 0){
				map.put(userNum, profitLossRate);
			}
			else{
				String temp = FormatParamUtil.getAmountAndPriceFmt("" + sumAmount / sumOriginalAmt);
				profitLossRate = Double.parseDouble(temp);
				map.put(userNum, profitLossRate);
			}
		}
		return map;
		
	}
	
	/**
	 * ����ccy��ת�������ͣ���accType��ԭ���ͣ�����ʼ��� ����ת������
	 * @param ccy
	 * @param type
	 * @param originalValue
	 * @return
	 */
	public static double getPriceByTypeAndCcy(String ccy, String type, String originalValue){
		double TransformValue = 0.0;
		double rate = RateUtil.getRateByCcy(type, ccy);
		TransformValue += Double.parseDouble(originalValue) * rate;
		return TransformValue;
		/*if(type.equals("C")){
			Double rate = RateUtil.getRate(ccy, "CNY");
			TransformValue += Double.parseDouble(originalValue) * rate;
		}else if(type.equals("W")){
			Double rate=RateUtil.getRate(ccy, "USD");
			TransformValue += Double.parseDouble(originalValue)*rate;
			
		}else if(type.equals("B")){
			Double rate=RateUtil.getRate(ccy, "USD");
			TransformValue += Double.parseDouble(originalValue)*rate;
		}*/
	}
	
	/**
	 * ��ʼ���û��˻���Ϣ�б�����һ�������б�����������
	 */
	public static int[] initCollection(List<TranFlowMappingModel> list, GroupSysParam bean){
		int a[] = new int[list.size()];
		for (int i=0; i<list.size(); i++){
			countAccount(list.get(i),bean);
			a[i] = i;
		}
		return a;
	}
	
	/**
	 * ����ÿ���û����˻�ֵ
	 */
	public static void countAccount(TranFlowMappingModel model, GroupSysParam bean){
		double sum = model.getTradeAmount() * Double.parseDouble(bean.getWAmount()) + 
				model.getTradeNumber() * Double.parseDouble(bean.getWQuantity()) + 
				model.getRate() * Double.parseDouble(bean.getWAmount());
		model.setAccount(sum);
	}
	
	/**
	 * ������С��
	 */
	public static void initAccountHeap(List<TranFlowMappingModel> list, int[] a){
		int i = (a.length-1)/2;
		for (;i >= 0;i--){
			accountSort(list,a,a.length,i);
		}
	} 
	
	/**
	 * ������
	 */
	public static void accountSort(List<TranFlowMappingModel> list, int[] a, int n, int row){
		int i = row;
		int j = 2*i+1;
		int temp = a[row];
		int flag = 0;
		while (j < n && flag == 0){
			if (j < n-1 && list.get(a[j]).getAccount() > list.get(a[j+1]).getAccount()){
				j++;
			}
			if (list.get(temp).getAccount() < list.get(a[j]).getAccount()){
				flag = 1;
			}
			else{
				a[i] = a[j];
				i = j;
				j = 2*i+1;
			}
		}
		a[i] = temp;
	}
	
	/**
	 * �˻�ֵ������
	 */
	public static void accountRankSort(List<TranFlowMappingModel> list, int[] a){
		initAccountHeap(list,a);
		for (int i = a.length-1 ; i > 0; i--){
			int temp = a[0];
			a[0] = a[i];
			a[i] = temp;
			accountSort(list,a,i,0);
		}
	}
	
	/**
	 * ������С��
	 */
	public static void initRateHeap(List<TranFlowMappingModel> list, int[] a){
		int i = (a.length-1)/2;
		for (;i >= 0;i--){
			accountSort(list,a,a.length,i);
		}
	} 
	
	/**
	 * ������
	 */
	public static void rateSort(List<TranFlowMappingModel> list, int[] a, int n, int row){
		int i = row;
		int j = 2*i+1;
		int temp = a[row];
		int flag = 0;
		while (j < n && flag == 0){
			if (j < n-1 && list.get(a[j]).getRate() > list.get(a[j+1]).getRate()){
				j++;
			}
			if (list.get(temp).getRate() < list.get(a[j]).getRate()){
				flag = 1;
			}
			else{
				a[i] = a[j];
				i = j;
				j = 2*i+1;
			}
		}
		a[i] = temp;
	}
	
	/**
	 * �˻�ӯ���ʶ�����
	 */
	public static void rateRankSort(List<TranFlowMappingModel> list, int[] a){
		initRateHeap(list,a);
		for (int i = a.length-1 ; i > 0; i--){
			int temp = a[0];
			a[0] = a[i];
			a[i] = temp;
			rateSort(list,a,i,0);
		}
	}
	
	/**
	 * ����һ���ź�������б�
	 */
	public static List<TranFlowMappingModel> getNewListAfterSort(List<TranFlowMappingModel> list, int[] a){
		List<TranFlowMappingModel> newList = new ArrayList<TranFlowMappingModel>();
		if (list.size() > 1){
			for (int i=0; i<a.length; i++){
				newList.add(list.get(a[i]));
				newList.get(i).setAccountRank(i+1);
			}
		}
		return newList;
	}
	
	/**
	 * ��ù̶������ź�������˻��б�
	 */
	public static List<TranFlowMappingModel> getLimitedNewListAfterSort(List<TranFlowMappingModel> list, int[] a, int size, String userNum){
		List<TranFlowMappingModel> newList = new ArrayList<TranFlowMappingModel>();
		int i;
		boolean flag = false;
		if (a == null || size > a.length)
			return null;
		for (i=0; i<size; i++){
			newList.add(list.get(a[i]));
			newList.get(i).setAccountRank(i+1);
			if (list.get(a[i]).getUserNum().equals(userNum))
				flag = true;
		}
		while(!flag){ 
			if (i >= a.length )
				break;
			if (list.get(a[i]).getUserNum().equals(userNum)){
				newList.add(list.get(a[i]));
				newList.get(newList.size()-1).setAccountRank(i+1);
				flag = true;
			}
			i++;
		}
		return newList;
	}
	
	
	/**
	 * ��ù̶������ź�������˻��б�(�޸�)
	 */
	public static List<TranFlowMappingModel> getListAfterSort(List<TranFlowMappingModel> list, int[] a, int size, String userNum){
		List<TranFlowMappingModel> newList = new ArrayList<TranFlowMappingModel>();
		int i;
		int length=a.length;
		if (a == null || length==0)
			return null;
		boolean flag = false;
		for (i=0; i<size; i++){
			if(length==0){
				break;
			}
			newList.add(list.get(a[i]));
			newList.get(i).setAccountRank(i+1);
			length--;
			if (list.get(a[i]).getUserNum().equals(userNum))
				flag = true;
		}
		while(!flag){ 
			if (i >= a.length )
				break;
			if (list.get(a[i]).getUserNum().equals(userNum)){
				newList.add(list.get(a[i]));
				newList.get(newList.size()-1).setAccountRank(i+1);
				flag = true;
			}
			i++;
		}
		return newList;
	}
	
	/**
	 * ����ĳ������Ա���˻�ʵʱ�������
	 */
	public static List<TranFlowMappingModel> getOneNewListAfterSort(List<TranFlowMappingModel> list, int[] a, String userNum){
		List<TranFlowMappingModel> newList = new ArrayList<TranFlowMappingModel>();
		for (int i=0; i<a.length; i++){
			if (list.get(a[i]).getUserNum().equals(userNum)){
				newList.add(list.get(a[i]));
				newList.get(0).setAccountRank(i+1);
				break;
			}
		}
		return newList;
	}
	
	/**
	 * ����һ���źõ�������б�
	 */
	public static List<TranFlowMappingModel> getNewListAfterSortByDesc(List<TranFlowMappingModel> list, int[] a){
		List<TranFlowMappingModel> newList = new ArrayList<TranFlowMappingModel>();
		int i = a.length - 1;
		int j = 0;
		while(i>=0){
			newList.add(list.get(a[i]));
			newList.get(j).setAccountRank(i+1);
			i--;
			j++;
		}
		return newList;
	}
	
	/**
	 * ������õ�ÿ���˻���ӯ���ʷ��õ��ۺ������б���
	 */
	public static void setSynthesizeRate(List<TranFlowMappingModel> list, Map<String,Double> map, char type){
		if (type == 'C'){
			for (TranFlowMappingModel model : list){
				model.setCRate(map.get(model.getUserNum()));
			}
		}
		else if (type == 'W'){
			for (TranFlowMappingModel model : list){
				model.setWRate(map.get(model.getUserNum()));
			}
		}
		else{
			for (TranFlowMappingModel model : list){
				model.setBRate(map.get(model.getUserNum()));
			}
		}
	}
	
	/**
	 * �������˻�����ֵ����ۺ������б���
	 */
	public static void setSynthesizeAccRank(List<TranFlowMappingModel> list, List<TranFlowMappingModel> list2, int[] a, char type){
		if (type == 'C'){
			for (int i=0; i<a.length; i++){
				for (int j=0; j<list.size(); j++){
					if (list.get(j).getUserNum().equals(list2.get(a[i]).getUserNum())){
						list.get(j).setCRank(i + 1);
						break;
					}
				}
			}
		}
		else if (type == 'W'){
			for (int i=0; i<a.length; i++){
				for (int j=0; j<list.size(); j++){
					if (list.get(j).getUserNum().equals(list2.get(a[i]).getUserNum())){
						list.get(j).setWRank(i + 1);
						break;
					}
				}
			}
		}
		else{
			for (int i=0; i<a.length; i++){
				for (int j=0; j<list.size(); j++){
					if (list.get(j).getUserNum().equals(list2.get(a[i]).getUserNum())){
						list.get(j).setBRank(i + 1);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * ��ʼ���ۺ������б��������ۺ������˻�Ȩֵ
	 */
	public static int[] initSynthesizeList(List<TranFlowMappingModel> list, GroupSysParam bean){
		int a[] = new int[list.size()];
		for (int i=0; i<list.size(); i++){
			countSynthesizeAccount(list.get(i),bean);
			a[i] = i;
		}
		return a;
	}
	
	/**
	 * �����ۺ������˻�Ȩֵ
	 */
	public static void countSynthesizeAccount(TranFlowMappingModel model, GroupSysParam bean){
		double sum = model.getCRate() * Double.parseDouble(bean.getWCnyAcc()) + 
				model.getWRate() * Double.parseDouble(bean.getWForAcc()) + 
				model.getBRate() * Double.parseDouble(bean.getWMarginAcc());
		model.setAccount(sum);
	}
}
