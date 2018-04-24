package com.talent.forex.modules.trade.ask;

import java.util.List;
import org.hibernate.criterion.MatchMode;
import com.talent.base.BaseBo;
import com.talent.forex.bean.domain.OtcForwardInfo;
import com.talent.forex.bean.domain.OtcSpotInfo;
import com.talent.forex.bean.domain.OtcSwapInfo;
import com.talent.forex.dao.OtcForwardDao;
import com.talent.forex.dao.OtcSpotDao;
import com.talent.forex.dao.OtcSwapDao;
import com.talent.forex.util.ForwardUtil;
import com.talent.forex.util.SpotUtil;
import com.talent.forex.util.SwapUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS007
 * Create By    : 陈整队
 * Description  : 远期询价交易
 * Modify Date  : 2014-07-24
 */
public class TradeMessageBo extends BaseBo {

	private OtcSpotDao otcSpotDao;
	private OtcForwardDao otcForwardDao;
	private OtcSwapDao otcSwapDao;
	
	public List<OtcSpotInfo> getOtcSpotInfoQuery(){
//		OtcSpotInfo otcSpotInfo = new OtcSpotInfo();
//		otcSpotInfo.setUserNum(UserModelUtil.getUser().getUserId());
//		otcSpotInfo.setIsInit("0");
//		otcSpotInfo.setStatue("R");
//		return (List<OtcSpotInfo>) otcSpotDao.getBeansByBean(otcSpotInfo, MatchMode.ANYWHERE);
		String userNum = UserModelUtil.getUser().getUserId();
		return SpotUtil.getInstance().getSpotListByUserNum(userNum);
	}
	public List<OtcForwardInfo> getOtcForwardInfoQuery(){
//		OtcForwardInfo otcforwardInfo = new OtcForwardInfo();
//		otcforwardInfo.setUserNum(UserModelUtil.getUser().getUserId());
//		otcforwardInfo.setIsInit("0");
//		otcforwardInfo.setStatue("R");
//		return (List<OtcForwardInfo>) otcForwardDao.getBeansByBean(otcforwardInfo, MatchMode.ANYWHERE);
		String userNum = UserModelUtil.getUser().getUserId();
		return ForwardUtil.getInstance().getForwardListByUserNum(userNum);
	}
	public List<OtcSwapInfo> getOtcSwapInfoQuery(){
//		OtcSwapInfo otcSwapInfo = new OtcSwapInfo();
//		otcSwapInfo.setUserNum(UserModelUtil.getUser().getUserId());
//		otcSwapInfo.setIsInit("0");
//		otcSwapInfo.setStatue("R");
//		return (List<OtcSwapInfo>) otcSwapDao.getBeansByBean(otcSwapInfo, MatchMode.ANYWHERE);
		String userNum = UserModelUtil.getUser().getUserId();
		return SwapUtil.getInstance().getSwapListByUserNum(userNum);
	}
	public OtcSpotInfo otcSpotInfoGet(String tranNo,String provider){
		String userNum = UserModelUtil.getUser().getUserId();
//		OtcSpotInfo otcSpotInfo = new OtcSpotInfo();
//		otcSpotInfo.setTranNo(tranNo);
//		otcSpotInfo.setProvider(provider);
		return SpotUtil.getInstance().getSpotByParams(tranNo, userNum);
		//return otcSpotDao.getBeanByBean(otcSpotInfo, MatchMode.EXACT);
	}
	public OtcForwardInfo otcForwardInfoGet(String tranNo,String provider){
		String userNum = UserModelUtil.getUser().getUserId();
//		OtcForwardInfo otcForwardInfo=new OtcForwardInfo();
//		otcForwardInfo.setTranNo(tranNo);
//		otcForwardInfo.setProvider(provider);
		return ForwardUtil.getInstance().getForwardByParams(tranNo, userNum);
	}
	public OtcSwapInfo otcSwapInfoGet(String tranNo,String provider){
		String userNum = UserModelUtil.getUser().getUserId();
//		OtcSwapInfo otcSwapInfo=new OtcSwapInfo();
//		otcSwapInfo.setTranNo(tranNo);
//		otcSwapInfo.setProvider(provider);
		return SwapUtil.getInstance().getSwapByParams(tranNo, userNum);
	}
	
	public void sendBySpotModify(String tranNo, String provider, String price){
		//修改本方的记录
		//OtcSpotInfo otcSpotInfo=new OtcSpotInfo();
		//otcSpotInfo.setTranNo(tranNo);
		//otcSpotInfo.setProvider(provider);
		String userNum = UserModelUtil.getUser().getUserId();
		OtcSpotInfo otcSpotInfo2 = SpotUtil.getInstance().getSpotByParams(tranNo, userNum);
		//OtcSpotInfo otcSpotInfo2 = otcSpotDao.getBeanByBean(otcSpotInfo, MatchMode.ANYWHERE);
		if(otcSpotInfo2.getPrice().equals(price)){
			otcSpotInfo2.setStatue("F");//表示双方同意价格
		}
		else{//价格不同时
			otcSpotInfo2.setPrice(price);
			otcSpotInfo2.setStatue("P");
			int times = Integer.parseInt(otcSpotInfo2.getTimes());
			times++;
			otcSpotInfo2.setTimes(times + "");
		}
		//otcSpotDao.updateBean(otcSpotInfo2);
		//修改对手方的记录
//		OtcSpotInfo otcSpotInfo3 = new OtcSpotInfo();
//		otcSpotInfo3.setTranNo(tranNo);
//		otcSpotInfo3.setUserNum(provider);
		OtcSpotInfo otcSpotInfo4 = SpotUtil.getInstance().getSpotByParams(tranNo, provider);
		//OtcSpotInfo otcSpotInfo4 = otcSpotDao.getBeanByBean(otcSpotInfo3, MatchMode.ANYWHERE);
		if(otcSpotInfo4.getPrice().equals(price)){
			otcSpotInfo4.setStatue("F");//表示双方同意价格
		}
		else{//价格不同时
			otcSpotInfo4.setPrice(price);
			otcSpotInfo4.setStatue("R");
		}
		//otcSpotDao.updateBean(otcSpotInfo4);
	}
	
	public void closeBySpotDel(String tranNo,String provider){
		//中止交易时，不是删除交易记录，而是把状态修改为C，标志着取消交易
		//修改本方的记录
//		OtcSpotInfo bean = new OtcSpotInfo();
//		bean.setTranNo(tranNo);
//		bean.setProvider(provider);
		String userNum = UserModelUtil.getUser().getUserId();
		OtcSpotInfo otcSpotInfo = SpotUtil.getInstance().getSpotByParams(tranNo, userNum);
		otcSpotInfo.setStatue("C");
		//otcSpotDao.updateBean(otcSpotInfo);
		
		//修改对手方的记录
//		OtcSpotInfo bean2 = new OtcSpotInfo();
//		bean2.setTranNo(tranNo);
//		bean2.setUserNum(provider);
		String opponent = otcSpotInfo.getProvider();
		OtcSpotInfo otcSpotInfo2 = SpotUtil.getInstance().getSpotByParams(tranNo, opponent);
		otcSpotInfo2.setStatue("C");
		//otcSpotDao.updateBean(otcSpotInfo2);
	}
	
	public void sendByForwardModify(String tranNo, String provider, String point){
		//修改本方的记录
//		OtcForwardInfo otcForwardInfo=new OtcForwardInfo();
//		otcForwardInfo.setTranNo(tranNo);
//		otcForwardInfo.setProvider(provider);
		String userNum = UserModelUtil.getUser().getUserId();
		OtcForwardInfo otcForwardInfo2 = ForwardUtil.getInstance().getForwardByParams(tranNo, userNum);
		if(otcForwardInfo2.getPoint().equals(point)){
			otcForwardInfo2.setStatue("F");
		}
		else{
			otcForwardInfo2.setPoint(point);
			otcForwardInfo2.setStatue("P");
			int times = Integer.parseInt(otcForwardInfo2.getTimes());
			times++;
			otcForwardInfo2.setTimes(String.valueOf(times));
		}
		//otcForwardDao.updateBean(otcForwardInfo2);
		//修改对手方的记录
//		OtcForwardInfo otcForwardInfo3=new OtcForwardInfo();
//		otcForwardInfo3.setTranNo(tranNo);
//		otcForwardInfo3.setUserNum(provider);
		OtcForwardInfo otcForwardInfo4 = ForwardUtil.getInstance().getForwardByParams(tranNo, provider);
		if(otcForwardInfo4.getPoint().equals(point)){
			otcForwardInfo4.setStatue("F");
		}
		else{
			otcForwardInfo4.setPoint(point);
			otcForwardInfo4.setStatue("R");
		}
		//otcForwardDao.updateBean(otcForwardInfo4);
	}
	public void closeByForwardDel(String tranNo,String provider){
		//中止交易时，不是删除交易记录，而是把状态修改为C，标志着中止交易
		//修改本方的记录
//		OtcForwardInfo bean = new OtcForwardInfo();
//		bean.setTranNo(tranNo);
//		bean.setProvider(provider);
		String userNum = UserModelUtil.getUser().getUserId();
		OtcForwardInfo otcForwardInfo = ForwardUtil.getInstance().getForwardByParams(tranNo, userNum);
		otcForwardInfo.setStatue("C");
		//otcForwardDao.updateBean(otcForwardInfo);
		
		//修改对手方的记录
//		OtcForwardInfo bean2 = new OtcForwardInfo();
//		bean2.setTranNo(tranNo);
//		bean2.setUserNum(provider);
		OtcForwardInfo otcForwardInfo2 = ForwardUtil.getInstance().getForwardByParams(tranNo, provider);
		otcForwardInfo2.setStatue("C");
		//otcForwardDao.updateBean(otcForwardInfo2);
		
		//修改本方的记录
//		OtcForwardInfo otcForwardInfo=new OtcForwardInfo();
//		otcForwardInfo.setTranNo(tranNo);
		//otcForwardInfo.setProvider(provider);
//		Collection beans = otcSpotDao.getBeansByBean(otcForwardInfo, MatchMode.ANYWHERE);
		//修改对手方的记录
		/*OtcForwardInfo otcForwardInfo2=new OtcForwardInfo();
		otcForwardInfo2.setTranNo(tranNo);
		otcForwardInfo2.setUserNum(provider);
		beans.add(otcForwardDao.getBeanByBean(otcForwardInfo2, MatchMode.ANYWHERE));*/
//		otcForwardDao.deleteBean(beans);
	}
	public void sendBySwapModify(String tranNo, String provider, String point){
		//修改本方的记录
//		OtcSwapInfo otcSwapInfo=new OtcSwapInfo();
//		otcSwapInfo.setTranNo(tranNo);
//		otcSwapInfo.setProvider(provider);
		String userNum = UserModelUtil.getUser().getUserId();
		OtcSwapInfo otcSwapInfo2 = SwapUtil.getInstance().getSwapByParams(tranNo, userNum);
		if(otcSwapInfo2.getPoint().equals(point)){
			otcSwapInfo2.setStatue("F");
		}
		else{
			otcSwapInfo2.setPoint(point);
			otcSwapInfo2.setStatue("P");
			int times = Integer.parseInt(otcSwapInfo2.getTimes());
			times++;
			otcSwapInfo2.setTimes(String.valueOf(times));
		}
		//otcSwapDao.updateBean(otcSwapInfo2);
		//修改对手方的记录
//		OtcSwapInfo otcSwapInfo3=new OtcSwapInfo();
//		otcSwapInfo3.setTranNo(tranNo);
//		otcSwapInfo3.setUserNum(provider);
		OtcSwapInfo otcSwapInfo4 = SwapUtil.getInstance().getSwapByParams(tranNo, provider);
		if(otcSwapInfo4.getPoint().equals(point)){
			otcSwapInfo4.setStatue("F");
		}
		else{
			otcSwapInfo4.setPoint(point);
			otcSwapInfo4.setStatue("R");
		}
		//otcSwapDao.updateBean(otcSwapInfo4);
	}
	public void closeBySwapDel(String tranNo,String provider){
		//中止交易时，不是删除交易记录，而是把状态修改为C，标志着中止交易
		//修改本方的记录
//		OtcSwapInfo bean = new OtcSwapInfo();
//		bean.setTranNo(tranNo);
//		bean.setProvider(provider);
		String userNum = UserModelUtil.getUser().getUserId();
		OtcSwapInfo otcSwapInfo = SwapUtil.getInstance().getSwapByParams(tranNo, userNum);
		otcSwapInfo.setStatue("C");
		//otcSwapDao.updateBean(otcSwapInfo);
		
		//修改对手方的记录
//		OtcSwapInfo bean2 = new OtcSwapInfo();
//		bean2.setTranNo(tranNo);
//		bean2.setUserNum(provider);
		OtcSwapInfo otcSwapInfo2 = SwapUtil.getInstance().getSwapByParams(tranNo, provider);
		otcSwapInfo2.setStatue("C");
		//otcSwapDao.updateBean(otcSwapInfo2);
		
		//修改本方的记录
//		OtcSwapInfo otcSwapInfo=new OtcSwapInfo();
//		otcSwapInfo.setTranNo(tranNo);
		//otcSwapInfo.setProvider(provider);
//		Collection beans = otcSwapDao.getBeansByBean(otcSwapInfo, MatchMode.ANYWHERE);
		//修改对手方的记录
		/*OtcSwapInfo otcSwapInfo2=new OtcSwapInfo();
		otcSwapInfo2.setTranNo(tranNo);
		otcSwapInfo2.setUserNum(provider);
		beans.add(otcSwapDao.getBeanByBean(otcSwapInfo2, MatchMode.ANYWHERE));*/
//		otcSwapDao.deleteBean(beans);
	}
	
	public OtcSpotDao getOtcSpotDao() {
		return otcSpotDao;
	}
	public void setOtcSpotDao(OtcSpotDao otcSpotDao) {
		this.otcSpotDao = otcSpotDao;
	}
	public OtcForwardDao getOtcForwardDao() {
		return otcForwardDao;
	}
	public void setOtcForwardDao(OtcForwardDao otcForwardDao) {
		this.otcForwardDao = otcForwardDao;
	}
	public OtcSwapDao getOtcSwapDao() {
		return otcSwapDao;
	}
	public void setOtcSwapDao(OtcSwapDao otcSwapDao) {
		this.otcSwapDao = otcSwapDao;
	}
	
}