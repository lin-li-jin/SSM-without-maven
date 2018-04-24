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
		System.out.println("定时任务正在执行！30S执行一次！开始时间"+beginTime);
		GetRateUtil.startRatePolling();
		//保证金轮询
		CashPollingUtil.checkOptionValueDate();
		CashPollingUtil.checkForwardValueDate();
		CashPollingUtil.checkCashBalance();
		//竞价
		StopLossPollingUtil.stopLossCheck();
		TakeProfitPollingUtil.takeProfitCheck();
		OcoPollingUtil.ocoCheck();
		MarketBreakoutPollingUtil.marketBreakoutCheck();
		//询价
		AskPollingUtil.checkForwardValueDate();
		AskPollingUtil.checkSwapStartDate();
		AskPollingUtil.checkSwapFrequencyDate();
		AskPollingUtil.checkSwapMaturityDate();
		long endTime = System.currentTimeMillis();
		long useTime = endTime - beginTime;
		System.out.println("定时任务正在执行！30S执行一次！结束时间"+endTime);
		System.out.println("总用时："+useTime);
		
   }
}
