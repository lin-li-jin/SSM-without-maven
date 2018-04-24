package com.talent.forex.modules.trade.ask;

import java.util.Collection;

import org.hibernate.criterion.MatchMode;

import com.talent.auth.bean.domain.Users;
import com.talent.auth.dao.UsersDao;
import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.AnalogueMag;
import com.talent.forex.bean.domain.OtcSwapInfo;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.AnalogueMagDao;
import com.talent.forex.dao.OtcSwapDao;
import com.talent.forex.util.CalculateUtil;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.SequenceUtil;
import com.talent.forex.util.SwapUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS007
 * Create By    : 陈整队
 * Description  : 掉期询价交易
 * Modify Date  : 2014-07-24
 */
public class SwapManageBo extends BaseBo {


	private AnalogueMagDao analogueMagDao;
	
	private UsersDao userDao;
	
	private OtcSwapDao otcSwapDao;
	
	private AccInfoDao accInfoDao;
	
	
	//通告用户的所属组号取得所有对手方的组号
	public Collection getOpponentGroupQuery(String initGroup){
		AnalogueMag analogueMag=new AnalogueMag();
		analogueMag.setInitGroup(initGroup);
		return analogueMagDao.getBeansByBean(analogueMag,MatchMode.ANYWHERE);
	}
	
	//通过组号取得同一组号的对手方
	public Collection getOpponentQuery(String groupTwoId){
		Users user=new Users();
		user.setGroupTwo(groupTwoId);
		return analogueMagDao.getBeansByBean(user,MatchMode.ANYWHERE);
	}
	
	public String otcSwapTradeAdd(String tranNo, String startTime, String endTime, String direct, String ccy1, String ccy2, String oldPrice, String provider, String amount, String basis1, String basis2, String fixedType, String fixedRate, String libor, String frequency, String point){
		try{
			Double price = Double.parseDouble(oldPrice) + Double.parseDouble(point) / 10000;//待计算的汇率
			String userNum = UserModelUtil.getUser().getUserId();
			
			//添加新的交易
			if(tranNo == null || tranNo.equals("")){//首次交易则新增一条记录
				tranNo = SequenceUtil.getNextTranSeq("SP");//生成新的交易编号
				//发起方的交易记录
				OtcSwapInfo otcSwapInfo=new OtcSwapInfo();
				AccInfo accInfo = new AccInfo();
				accInfo.setUserNum(userNum);
				String weCcy = "";
				String anaCcy = "";
				if (direct.equals("0")) {
					weCcy = ccy1;
					anaCcy = ccy2;
				} 
				else {
					anaCcy = ccy1;
					weCcy = ccy2;
				}
				if(weCcy.equals("CNY") || anaCcy.equals("CNY")){
					otcSwapInfo.setTranType("C");//人民币交易
					accInfo.setAccType("C");
				}else {
					otcSwapInfo.setTranType("W");//外币交易
					accInfo.setAccType("W");
				}
				accInfo.setCcy(weCcy);
				Double needAmount = 0.0d;//进行交易所需要的金额！
				//将卖出的币种存入本方，将买入的币种存入对手方
				if(direct.equals("1")){
					needAmount = Double.parseDouble(CalculateUtil.getAmountAfterTrade(amount, price+""));
				}
				else {
					needAmount = Double.parseDouble(amount);
				}
				AccInfo accInfo2 = accInfoDao.getBeanByBean(accInfo, MatchMode.ANYWHERE);
				if (needAmount > Double.parseDouble(accInfo2.getAmount())){
					logger.error("用户：" + userNum + " 添加即期询价交易失败！账户"+ accInfo.getCcy() +"金额不足");
//					BoException be = new BoException("otcSpotTradeAdd");
//					if (accInfo.getAccType().equals("C"))
//						be.setExceptionType("人民币账户"+ accInfo.getCcy() +"金额不足,即期询价交易易提交失败！");
//					else
//						be.setExceptionType("外币账户"+ accInfo.getCcy() +"金额不足,即期询价交易提交失败！");
//					throw be;
					return "fail";
				}
				otcSwapInfo.setTranNo(tranNo);
				otcSwapInfo.setUserNum(userNum);
				otcSwapInfo.setProvider(provider);
				otcSwapInfo.setWeCcy(weCcy);
				otcSwapInfo.setAnaCcy(anaCcy);
				otcSwapInfo.setStartDate(GetDateTimeUtil.dateTransFmt2(startTime));
				otcSwapInfo.setMaturityDate(GetDateTimeUtil.dateTransFmt2(endTime));
				otcSwapInfo.setDirection(direct);
				otcSwapInfo.setPrice(oldPrice);
				otcSwapInfo.setPoint(point);
				otcSwapInfo.setCAmount(FormatParamUtil.getAmountAndPriceFmt(amount));
				otcSwapInfo.setFAmount(FormatParamUtil.getAmountAndPriceFmt(amount));
				otcSwapInfo.setCBasis(basis1);
				otcSwapInfo.setFBasis(basis2);
				if(fixedType.equals("0")){//0为receive，即receive fixedRate
					otcSwapInfo.setReceiveRate(fixedRate);
				}
				else{//1为pay，即pay fixedRate
					otcSwapInfo.setPayRate(fixedRate);
				}
				otcSwapInfo.setFixedType(fixedType);
				otcSwapInfo.setLibor(libor);
				otcSwapInfo.setFrequency(frequency);
				otcSwapInfo.setCreateDatetime(GetDateTimeUtil.getCurrentDateTimeToMinute());
				otcSwapInfo.setIsInit("1");
				otcSwapInfo.setStatue("P");
				otcSwapInfo.setTimes("0");
				
				otcSwapDao.addBean(otcSwapInfo);
				OtcSwapInfo bean = otcSwapDao.getBeanByBean(otcSwapInfo, MatchMode.EXACT);
				SwapUtil.getInstance().swapAdd(bean);
				
				//对手方的交易记录
				OtcSwapInfo otcSwapInfo2 = new OtcSwapInfo();
				otcSwapInfo2.setTranType(otcSwapInfo.getTranType());
				otcSwapInfo2.setTranNo(tranNo);
				otcSwapInfo2.setUserNum(provider);
				otcSwapInfo2.setProvider(userNum);
				otcSwapInfo2.setWeCcy(anaCcy);
				otcSwapInfo2.setAnaCcy(weCcy);
				otcSwapInfo2.setStartDate(GetDateTimeUtil.dateTransFmt2(startTime));
				otcSwapInfo2.setMaturityDate(GetDateTimeUtil.dateTransFmt2(endTime));
				if(direct.equals("1")){
					otcSwapInfo2.setDirection("0");
				}
				else{
					otcSwapInfo2.setDirection("1");
				}
				otcSwapInfo2.setPrice(oldPrice);
				otcSwapInfo2.setPoint(point);
				otcSwapInfo2.setCAmount(FormatParamUtil.getAmountAndPriceFmt(amount));
				otcSwapInfo2.setFAmount(FormatParamUtil.getAmountAndPriceFmt(amount));
				otcSwapInfo2.setCBasis(basis1);
				otcSwapInfo2.setFBasis(basis2);
				if(fixedType.equals("0")){//0为pay，即pay fixedRate
					otcSwapInfo2.setFixedType("1");
					otcSwapInfo2.setPayRate(fixedRate);
				}
				else{//1为receive，即receive fixedRate
					otcSwapInfo2.setFixedType("0");
					otcSwapInfo2.setReceiveRate(fixedRate);
				}
				otcSwapInfo2.setLibor(libor);
				otcSwapInfo2.setFrequency(frequency);
				otcSwapInfo2.setCreateDatetime(GetDateTimeUtil.getCurrentDateTimeToMinute());
				otcSwapInfo2.setStatue("R");
				otcSwapInfo2.setIsInit("0");
				otcSwapInfo2.setTimes("0");
				
				otcSwapDao.addBean(otcSwapInfo2);
				OtcSwapInfo bean2 = otcSwapDao.getBeanByBean(otcSwapInfo2, MatchMode.EXACT);
				SwapUtil.getInstance().swapAdd(bean2);
			}
			else{
				//修改交易记录，主要是point
				otcSwapTradeUpdate(tranNo, userNum, point, "send");
			}
			return tranNo;
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("掉期询价交易的信息没有成功发送给对手方！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSwapTradeAdd");
			be.setExceptionType("掉期询价交易的信息没有成功发送给对手方！");
			throw be;
		}
	}
	
	//通告发起方账号,交易的币种类型和对手方账号取得交易编号！
//	public OtcSwapInfo otcSwapInfoGet(String tranNo, String userNum){
//		try {
//			OtcSwapInfo bean=new OtcSwapInfo();
//			bean.setTranNo(tranNo);
//			bean.setUserNum(userNum);
//			OtcSwapInfo otcSwapInfo = otcSwapDao.getBeanByBean(bean, MatchMode.ANYWHERE);
//			return otcSwapInfo;
//			/*otcSwapInfo.setProvider(provider);
//			otcSwapInfo.setWeCcy(weCcy);
//			otcSwapInfo.setAnaCcy(anaCcy);*/
//			/*Collection collection = otcSwapDao.getBeansByBean(otcSwapInfo, MatchMode.ANYWHERE);
//			Iterator it=collection.iterator();
//			//获得交易状态不是DONE的记录
//			while(it.hasNext()){
//				OtcSwapInfo otcSwapInfo2=(OtcSwapInfo) it.next();
//				if(otcSwapInfo2.getStatue().equals("R")||otcSwapInfo2.getStatue().equals("P")){
//					return otcSwapInfo2;
//				}
//			}*/
//		} catch (Exception e) {
//			if (e instanceof BoException){
//				throw (BoException) e;
//			}
//			logger.error("查询失败！用户：" + UserModelUtil.getUser().getUserId());
//			logger.error(e.getMessage(), e);
//			BoException be = new BoException("otcSwapInfoGet");
//			be.setExceptionType("查询失败！");
//			throw be;
//		}
//	}
	
	//修改交易信息！
	public void otcSwapTradeUpdate(String tranNo, String userNum, String point, String type){
		try {
			if(type.equals("send")){
				//修改发起方的状态
				OtcSwapInfo otcSwapInfo = SwapUtil.getInstance().getSwapByParams(tranNo, userNum);
				otcSwapInfo.setPoint(point);
				otcSwapInfo.setStatue("P");
				int times = Integer.parseInt(otcSwapInfo.getTimes()) + 1;
				otcSwapInfo.setTimes(times + "");
				//修改对手方的状态
				String opponent = otcSwapInfo.getProvider();
				OtcSwapInfo provider = SwapUtil.getInstance().getSwapByParams(tranNo, opponent);
				provider.setPoint(point);
				provider.setStatue("R");
			}else if(type.equals("accept")){
				OtcSwapInfo otcSwapInfo = SwapUtil.getInstance().getSwapByParams(tranNo, userNum);
				otcSwapInfo.setPoint(point);
				otcSwapInfo.setStatue("A");
				otcSwapInfo.setDate(otcSwapInfo.getStartDate());
				otcSwapInfo.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
				//交易执行后的金额
				Double finalPrice = Double.parseDouble(otcSwapInfo.getPrice()) + Double.parseDouble(point) / 10000;
				otcSwapInfo.setLcAmount(CalculateUtil.getAmountAfterTrade(otcSwapInfo.getCAmount(), String.valueOf(finalPrice)));
				otcSwapDao.updateBean(otcSwapInfo);
				
				String opponent = otcSwapInfo.getProvider();
				OtcSwapInfo provider = SwapUtil.getInstance().getSwapByParams(tranNo, opponent);
				provider.setPoint(point);
				provider.setStatue("A");
				provider.setDate(provider.getStartDate());
				provider.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
				//交易执行后的金额
				provider.setLcAmount(CalculateUtil.getAmountAfterTrade(provider.getCAmount(), String.valueOf(finalPrice)));
				otcSwapDao.updateBean(provider);
//				otcSwapInfo.setPoint(point);
//				otcSwapInfo.setStatue("A");//标识近期是否已经交易
//				otcSwapInfo.setDate(otcSwapInfo.getStartDate());
//				//otcSwapInfo.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
//				//otcSwapInfo.setIndexValue("A");//标识远期是否已经交易
//				otcSwapDao.updateBean(otcSwapInfo);
			}
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("交易信息修改失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSwapTradeUpdate");
			be.setExceptionType("交易信息修改失败！");
			throw be;
		}
	}
	
	//仅限于对手方终止交易时，需要删除交易！
	public String otcSwapTradeDel(String tranNo){
		//List<OtcSwapInfo> collection=new ArrayList();
		try {
			OtcSwapInfo bean = new OtcSwapInfo();
			bean.setTranNo(tranNo);
			Collection collection = otcSwapDao.getBeansByBean(bean, MatchMode.EXACT);
			otcSwapDao.batchDelete(collection);
			String userNum = UserModelUtil.getUser().getUserId();
			OtcSwapInfo otcSwapInfo = SwapUtil.getInstance().getSwapByParams(tranNo, userNum);
			String provider = otcSwapInfo.getProvider();
			SwapUtil.getInstance().swapDestroy(tranNo, userNum);
			SwapUtil.getInstance().swapDestroy(tranNo, provider);
			
			return "1";
			/*OtcSwapInfo otcSwapInfo=new OtcSwapInfo();
			otcSwapInfo.setStatue("R");
			otcSwapInfo.setTranNo(tranNo);
			collection=(List<OtcSwapInfo>) otcSwapDao.getBeansByBean(otcSwapInfo, MatchMode.ANYWHERE);
			otcSwapInfo.setStatue("P");
			collection.addAll(otcSwapDao.getBeansByBean(otcSwapInfo, MatchMode.ANYWHERE));
			otcSwapDao.batchDelete(collection);*/
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("交易信息删除失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSwapTradeDelete");
			be.setExceptionType("交易信息删除失败！");
			throw be;
		}
	}
	
	public boolean acceptDone(String tranNo, String provider, String point){
		try {
			String userNum = UserModelUtil.getUser().getUserId();
			// 取得发起方的交易记录
			OtcSwapInfo otcSwapInfo = SwapUtil.getInstance().getSwapByParams(tranNo, userNum);
			// 取得对手方的交易记录
			OtcSwapInfo otcSwapInfo2 = SwapUtil.getInstance().getSwapByParams(tranNo, provider);
			if(otcSwapInfo != null && otcSwapInfo2 != null){
				otcSwapTradeUpdate(tranNo, userNum, point, "accept");
				//接下来的交易执行过程只能等到轮询日期到了才去做
				
				//删除table中的交易记录！但不是删除数据库中的交易记录！
				SwapUtil.getInstance().swapDestroy(tranNo, userNum);
				SwapUtil.getInstance().swapDestroy(tranNo, provider);
				return true;
			}
			else {
				return false;
			}
			/*String direction = swapTradeModel.getDirection();
			String weCcy = "";
			String anaCcy = "";
			if (direction.equals("0")) {
				weCcy = swapTradeModel.getWeCcy();
				anaCcy = swapTradeModel.getAnaCcy();
			} else {
				anaCcy = swapTradeModel.getWeCcy();
				weCcy = swapTradeModel.getAnaCcy();
			}*/
			
			/*if(otcSwapInfo!=null&&otcSwapInfo2!=null){
				otcSwapTradeUpdate(otcSwapInfo, point, "accept");
				otcSwapTradeUpdate(otcSwapInfo2, point, "accept");
				//删除其他相同交易编号但是没有成功交易的记录
				otcSwapTradeDel(otcSwapInfo.getTranNo());
				return true;
			}else {
				return false;
			}*/
		} catch (Exception e) {
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("交易信息删除失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSwapTradeDelete");
			be.setExceptionType("交易信息删除失败！");
			throw be;
		}
	}
	//修改账户交易统计信息表
	
	public String otcSwapTradeCheckPoint(String tranNo, String provider){
		String userNum = UserModelUtil.getUser().getUserId();
		OtcSwapInfo otcSwapInfo = SwapUtil.getInstance().getSwapByParams(tranNo, userNum);
		if(otcSwapInfo.getStatue().equals("C")){
			return "C";//取消交易
		}
		else if(otcSwapInfo.getStatue().equals("F")){
			return "F";//同意价格
		}
		else{
			return otcSwapInfo.getPoint();//修改后的基本点
		}
//		String userNum = UserModelUtil.getUser().getUserId();
//		OtcSwapInfo bean = new OtcSwapInfo();
//		bean.setTranNo(tranNo);
//		bean.setUserNum(userNum);
//		bean.setProvider(provider);
//		
//		OtcSwapInfo otcSwapInfo = otcSwapDao.getBeanByBean(bean, MatchMode.ANYWHERE);
//		String point = otcSwapInfo.getPoint();
	}
	
	public void otcSwapTradeWithdraw(String tranNo){
		if(tranNo.startsWith("SP")){
			otcSwapTradeDel(tranNo);
		}
	}
	
	public AnalogueMagDao getAnalogueMagDao() {
		return analogueMagDao;
	}
	public void setAnalogueMagDao(AnalogueMagDao analogueMagDao) {
		this.analogueMagDao = analogueMagDao;
	}
	public UsersDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UsersDao userDao) {
		this.userDao = userDao;
	}
	public OtcSwapDao getOtcSwapDao() {
		return otcSwapDao;
	}
	public void setOtcSwapDao(OtcSwapDao otcSwapDao) {
		this.otcSwapDao = otcSwapDao;
	}
	public AccInfoDao getAccInfoDao() {
		return accInfoDao;
	}
	public void setAccInfoDao(AccInfoDao accInfoDao) {
		this.accInfoDao = accInfoDao;
	}
}