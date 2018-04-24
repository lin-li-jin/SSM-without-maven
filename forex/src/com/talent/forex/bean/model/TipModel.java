package com.talent.forex.bean.model;

import java.util.Collection;
import java.util.Vector;

public class TipModel {
	
	private String processCode;
	private String tip;
	private Exception exception;
	private Collection params;
	
	public TipModel(){
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	public Collection getParams() {
		return params;
	}

	public void setParams(Collection params) {
		if(this.params == null){
			this.params = new Vector();
		}else{
			this.params.clear();
		}
		this.params.addAll(params);
	}
	
	public String getStackTrace(){
		StringBuffer buffer = new StringBuffer();
		if(exception != null){
			StackTraceElement[] trace = exception.getStackTrace();
            for (int i=0; i < trace.length; i++)
                buffer.append("\tat " + trace[i] + "<br>");
            
            Throwable cause = exception.getCause();
            if (cause != null){
            	appendStackTraceAsCause(buffer, trace , cause);
            }
		}
		return buffer.toString();
	}
	
	public void appendStackTraceAsCause(StringBuffer buffer, StackTraceElement[] causedTrace, Throwable cause){
		if(cause != null){
	        StackTraceElement[] trace = cause.getStackTrace();
	        int m = trace.length-1, n = causedTrace.length-1;
	        while (m >= 0 && n >=0 && trace[m].equals(causedTrace[n])) {
	            m--; n--;
	        }
	        int framesInCommon = trace.length - 1 - m;
	
	        buffer.append("Caused by: " + cause + "<br>");
	        for (int i=0; i <= m; i++)
	            buffer.append("\tat " + trace[i] + "<br>");
	        if (framesInCommon != 0)
	        	buffer.append("\t... " + framesInCommon + " more" + "<br>");
	
	        Throwable ourCause = cause.getCause();
	        if (ourCause != null){
	        	appendStackTraceAsCause(buffer,trace,ourCause);
	        }
		}	
	}

}
