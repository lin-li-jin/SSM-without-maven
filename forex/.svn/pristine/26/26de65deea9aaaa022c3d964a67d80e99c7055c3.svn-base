package com.talent.forex.modules.code;
/*
 * Amendment No.: ISAS130008
 * Modify By    : sunyan
 * Description  : 报文部分
 * Modify Date  : 2013-7-3
 * 
 */
import com.talent.base.BaseAction;

public class CodeAction  extends BaseAction{
	
	private CodeManageBo codeManageBo;
	
	public CodeAction(){}
	
	public String codeQueryInit(){
		requestPut("codeList", null);
		requestPut("codeTable", null);
		return SUCCESS;
	}
	/*
	//ISAS130008 begin
	public String testInit(){
		//test 测试生成报文
		//SwiftInfoModel mm=new SwiftInfoModel();
//		HashMap<String,String> map=new HashMap();
//		map.put("57A", "622010100100100");
//		map.put("59", "Google");
//		map.put("70","INV/18042-990125");
//		map.put("71A", "USD10");
//		SwiftInfoModel model=new SwiftInfoModel();
//		model.setSwiftNo("MT103");
//		model.setSender("SPDBCNSHXXX");
//		model.setReceiver("HASEHKHHXXX");
//		model.setDateTime("20120918");
//		model.setFlag("1");
//		model.setTranNo("TT13070500001");
//		if(GetPacketsUtil.proPacket(map,model)){
//			mm=GetPacketsUtil.getPacketShow("TT13070500001", "MT103");
//		}
//		requestPut("swiftModel",mm);
		//展示报文
		requestPut("swiftModel",GetPacketsUtil.getPacketShow("TT13070500001", "MT103"));
		return SUCCESS;
	}
	////ISAS130008 end
	 
	 */
	
	public String codeAddInit(){	
		return SUCCESS;
	}
	
	public CodeManageBo getCodeManageBo() {
		return codeManageBo;
	}

	public void setCodeManageBo(CodeManageBo codeManageBo) {
		this.codeManageBo = codeManageBo;
	}
	
}
