package com.talent.forex.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MutiThreadBus {
	private  ExecutorService exec;   
    private static int cpuCoreNumber=0; 
    
    //默认起的线程数量是CPU的数量
    public MutiThreadBus() {
    	if(cpuCoreNumber<1){  		
    		cpuCoreNumber = Runtime.getRuntime().availableProcessors(); 
    		if(cpuCoreNumber>8){
    			cpuCoreNumber=cpuCoreNumber-2;
    		}
    		if(cpuCoreNumber>12){
    			cpuCoreNumber=cpuCoreNumber-4;
    		}
    	}
    	if(exec==null){
    		exec = Executors.newFixedThreadPool(cpuCoreNumber);
    	}	
    }
    public MutiThreadBus(int num){
    	if(exec==null){
    		exec = Executors.newFixedThreadPool(num);
    	}
    }

	public ExecutorService getExec() {
		return exec; 
	}

	public void setExec(ExecutorService exec) {
		this.exec = exec;
	}

	public static int getCpuCoreNumber() {
		return cpuCoreNumber;
	}

	public static void setCpuCoreNumber(int cpuCoreNumber) {
		MutiThreadBus.cpuCoreNumber = cpuCoreNumber;
	}
}
