package com.talent.forex.timer;
import java.util.TimerTask;

import com.talent.forex.util.AskPollingUtil;
import com.talent.forex.util.CashPollingUtil;
import com.talent.forex.util.GetRateUtil;
import com.talent.forex.util.MarketBreakoutPollingUtil;
import com.talent.forex.util.OcoPollingUtil;
import com.talent.forex.util.StopLossPollingUtil;
import com.talent.forex.util.TakeProfitPollingUtil;


public class Task extends TimerTask {
	public void run(){
		long beginTime = System.currentTimeMillis();
		System.out.println("��ʱ��������ִ�У�30Sִ��һ�Σ���ʼʱ��"+beginTime);
		GetRateUtil.startRatePolling();
		//��֤����ѯ
		CashPollingUtil.checkOptionValueDate();
		CashPollingUtil.checkForwardValueDate();
		CashPollingUtil.checkCashBalance();
		//����
		StopLossPollingUtil.stopLossCheck();
		TakeProfitPollingUtil.takeProfitCheck();
		OcoPollingUtil.ocoCheck();
		MarketBreakoutPollingUtil.marketBreakoutCheck();
		//ѯ��
		AskPollingUtil.checkForwardValueDate();
		AskPollingUtil.checkSwapStartDate();
		AskPollingUtil.checkSwapFrequencyDate();
		AskPollingUtil.checkSwapMaturityDate();
		long endTime = System.currentTimeMillis();
		long useTime = endTime - beginTime;
		System.out.println("��ʱ��������ִ�У�30Sִ��һ�Σ�����ʱ��"+endTime);
		System.out.println("����ʱ��"+useTime);
		
   }
}
