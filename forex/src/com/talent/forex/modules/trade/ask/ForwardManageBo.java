package com.talent.forex.modules.trade.ask;

import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.mortbay.servlet.Forward;

import com.talent.auth.bean.domain.Users;
import com.talent.auth.dao.UsersDao;
import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.AnalogueMag;
import com.talent.forex.bean.domain.OtcForwardInfo;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.AnalogueMagDao;
import com.talent.forex.dao.OtcForwardDao;
import com.talent.forex.util.CalculateUtil;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.ForwardUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.SequenceUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS007
 * Create By    : 陈整队
 * Description  : 远期询价交易
 * Modify Date  : 2014-07-24
 */
public class ForwardManageBo extends BaseBo {

	private AnalogueMagDao analogueMagDao;
	
	private UsersDao userDao;
	
	private OtcForwardDao otcForwardDao;
	
	private AccInfoDao accInfoDao;
	
	//通过用户的所属组号取得所有对手方的组号
	public Collection<?> getOpponentGroupQuery(String initGroup){
		AnalogueMag analogueMag=new AnalogueMag();
		analogueMag.setInitGroup(initGroup);
		return analogueMagDao.getBeansByBean(analogueMag,MatchMode.ANYWHERE);
	}
	//通过组号取得同一组号的对手方
	public Collection<?> getOpponentQuery(String groupTwoId){
		Users user=new Users();
		user.setGroupTwo(groupTwoId);
		return analogueMagDao.getBeansByBean(user,MatchMode.ANYWHERE);
	}
	
	public String otcForwardTradeAdd(String tranNo, String opponent, String ccy1, String ccy2, String oldPrice, String direct, String amount, String valueDate, String point){
		try {
			Double price = Double.parseDouble(oldPrice) + Double.parseDouble(point) / 10000;//待计算的汇率
			
			String userNum = UserModelUtil.getUser().getUserId();
			// 添加新的交易
			if(tranNo == null || tranNo.equals("")){//首次交易则新增一条记录
				tranNo = SequenceUtil.getNextTranSeq("FD");//生成新的交易编号
				//发起方的交易记录
				OtcForwardInfo otcForwardInfo=new OtcForwardInfo();
				AccInfo accInfo = new AccInfo();
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
				if(weCcy.equals("CNY")||anaCcy.equals("CNY")){
					otcForwardInfo.setTranType("C");//人民币交易
					accInfo.setAccType("C");
				}else {
					otcForwardInfo.setTranType("W");//外币交易
					accInfo.setAccType("W");
				}
				accInfo.setUserNum(userNum);
				accInfo.setCcy(weCcy);
				Double needAmount = 0.0d;//进行交易所需要的金额！
				//将卖出的币种存入本方，将买入的币种存入对手方
				if(direct.equals("1")){//交易方向为买
					needAmount=Double.parseDouble(CalculateUtil.getAmountAfterTrade(amount, price+""));
				}
				else {
					needAmount=Double.parseDouble(amount);
				}
				//判断本方卖出的币种相应的余额是否足够！
				AccInfo accInfo2=accInfoDao.getBeanByBean(accInfo, MatchMode.ANYWHERE);
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
				otcForwardInfo.setTranNo(tranNo);//交易编号
				otcForwardInfo.setUserNum(userNum);
				otcForwardInfo.setProvider(opponent);
				otcForwardInfo.setWeCcy(weCcy);
				otcForwardInfo.setAnaCcy(anaCcy);
				otcForwardInfo.setAmount(FormatParamUtil.getAmountAndPriceFmt(amount));
				otcForwardInfo.setCreateDatetime(GetDateTimeUtil.getCurrentDateTimeToMinute());			
				otcForwardInfo.setPrice(oldPrice);
				otcForwardInfo.setPoint(point);
				otcForwardInfo.setValueDate(GetDateTimeUtil.dateTransFmt2(valueDate));
				otcForwardInfo.setIsInit("1");
				otcForwardInfo.setStatue("P");
				otcForwardInfo.setDirection(direct);
				otcForwardInfo.setTimes("0");
				
				otcForwardDao.addBean(otcForwardInfo);
				OtcForwardInfo bean = otcForwardDao.getBeanByBean(otcForwardInfo, MatchMode.EXACT);
				ForwardUtil.getInstance().forwardAdd(bean);
				
				//对手方的交易记录
				OtcForwardInfo otcForwardInfo2 = new OtcForwardInfo();
				otcForwardInfo2.setTranType(otcForwardInfo.getTranType());//人民币交易
				otcForwardInfo2.setTranNo(tranNo);//交易编号
				otcForwardInfo2.setUserNum(opponent);
				otcForwardInfo2.setProvider(userNum);
				otcForwardInfo2.setAmount(FormatParamUtil.getAmountAndPriceFmt(amount));
				otcForwardInfo2.setWeCcy(otcForwardInfo.getAnaCcy());
				otcForwardInfo2.setAnaCcy(otcForwardInfo.getWeCcy());
				otcForwardInfo2.setCreateDatetime(GetDateTimeUtil.getCurrentDateTimeToMinute());
				otcForwardInfo2.setPrice(oldPrice);
				otcForwardInfo2.setPoint(point);
				otcForwardInfo2.setValueDate(GetDateTimeUtil.dateTransFmt2(valueDate));
				otcForwardInfo2.setIsInit("0");
				otcForwardInfo2.setStatue("R");
				otcForwardInfo2.setTimes("0");
				if(direct.equals("1")){
					otcForwardInfo2.setDirection("0");
				}else{
					otcForwardInfo2.setDirection("1");
				}
				
				otcForwardDao.addBean(otcForwardInfo2);
				OtcForwardInfo bean2 = otcForwardDao.getBeanByBean(otcForwardInfo2, MatchMode.EXACT);
				ForwardUtil.getInstance().forwardAdd(bean2);
			}
			else {
				// 交易的价格记得修改！
				otcForwardTradeUpdate(tranNo, userNum, point, "send");
			}
			return tranNo;
		} catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("远期询价交易的信息没有成功发送给对手方！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcForwardTradeAdd");
			be.setExceptionType("远期询价交易的信息没有成功发送给对手方！");
			throw be;
		}
	}	
	
	//通告发起方账号,交易的币种类型和对手方账号取得交易编号！
//	public OtcForwardInfo otcForwardInfoGet(String tranNo, String userNum){
//		try {
//			OtcForwardInfo otcForwardInfo=new OtcForwardInfo();
//			if(tranNo == null){
//				tranNo = "";
//			}
//			otcForwardInfo.setTranNo(tranNo);
//			otcForwardInfo.setUserNum(userNum);
//			/*otcForwardInfo.setProvider(provider);
//			otcForwardInfo.setWeCcy(weCcy);
//			otcForwardInfo.setAnaCcy(anaCcy);*/
//			Collection<?> collection = otcForwardDao.getBeansByBean(otcForwardInfo, MatchMode.ANYWHERE);
//			Iterator<?> it=collection.iterator();
//			//获得交易状态不是DONE的记录
//			while(it.hasNext()){
//				OtcForwardInfo otcForwardInfo2=(OtcForwardInfo) it.next();
//				if(otcForwardInfo2.getStatue().equals("P")||otcForwardInfo2.getStatue().equals("R")){
//					return otcForwardInfo2;
//				}
//			}
//			return null;			
//		} catch (Exception e) {
//			if (e instanceof BoException){
//				throw (BoException) e;
//			}
//			logger.error("查询失败！用户：" + UserModelUtil.getUser().getUserId());
//			logger.error(e.getMessage(), e);
//			BoException be = new BoException("otcForwardInfoGet");
//			be.setExceptionType("查询失败！");
//			throw be;
//		}
//	}
	//修改交易信息！
	public void otcForwardTradeUpdate(String tranNo, String userNum, String point, String type){
		try {
			if(type.equals("send")){
				OtcForwardInfo otcForwardInfo = ForwardUtil.getInstance().getForwardByParams(tranNo, userNum);
				otcForwardInfo.setPoint(point);
				otcForwardInfo.setStatue("P");
				int times = Integer.parseInt(otcForwardInfo.getTimes());
				times++;
				otcForwardInfo.setTimes(String.valueOf(times));
				
				String opponent = otcForwardInfo.getProvider();
				OtcForwardInfo provider = ForwardUtil.getInstance().getForwardByParams(tranNo, opponent);
				provider.setPoint(point);
				provider.setStatue("R");
				//修改发起方的状态
//				if((otcForwardInfo.getUserNum()).equals((UserModelUtil.getUser().getUserId()))){
//					otcForwardInfo.setPoint(point);
//					otcForwardInfo.setStatue("P");
//					int times = Integer.parseInt(otcForwardInfo.getTimes()) + 1;
//					otcForwardInfo.setTimes(times + "");
//					otcForwardDao.updateBean(otcForwardInfo);
//				}else{//修改对手方的状态
//					otcForwardInfo.setStatue("R");
//					otcForwardInfo.setPoint(point);
//					otcForwardDao.updateBean(otcForwardInfo);
//				}
			}else if(type.equals("accept")){
				OtcForwardInfo otcForwardInfo = ForwardUtil.getInstance().getForwardByParams(tranNo, userNum);
				otcForwardInfo.setPoint(point);
				otcForwardInfo.setStatue("A");
				otcForwardInfo.setDate(GetDateTimeUtil.getCurrentDate());
				otcForwardInfo.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
				//交易执行后的金额
				Double finalPrice = Double.parseDouble(otcForwardInfo.getPrice()) + Double.parseDouble(point) / 10000;
				otcForwardInfo.setLAmount(CalculateUtil.getAmountAfterTrade(otcForwardInfo.getAmount(), String.valueOf(finalPrice)));
				otcForwardDao.updateBean(otcForwardInfo);
				
				String opponent = otcForwardInfo.getProvider();
				OtcForwardInfo provider = ForwardUtil.getInstance().getForwardByParams(tranNo, opponent);
				provider.setPoint(point);
				provider.setStatue("A");
				provider.setDate(GetDateTimeUtil.getCurrentDate());
				provider.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
				//交易执行后的金额
				provider.setLAmount(CalculateUtil.getAmountAfterTrade(provider.getAmount(), String.valueOf(finalPrice)));
				otcForwardDao.updateBean(provider);
//				otcForwardInfo.setStatue("A");
//				otcForwardInfo.setPoint(point);
//				otcForwardInfo.setDate(GetDateTimeUtil.getCurrentDate());
//				otcForwardInfo.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
//				//交易执行后的金额
//				Double finalPrice = Double.parseDouble(otcForwardInfo.getPrice()) + Double.parseDouble(point) / 10000;
//				Double lAmount = Double.parseDouble(otcForwardInfo.getAmount()) * finalPrice;
//				otcForwardInfo.setLAmount(lAmount+"");
//				otcForwardDao.updateBean(otcForwardInfo);
			}
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("交易信息修改失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcForwardTradeUpdate");
			be.setExceptionType("交易信息修改失败！");
			throw be;
		}
	}
	//仅限于对手方终止交易时，需要删除交易！
	public String otcForwardTradeDel(String tranNo){
		try {
			OtcForwardInfo bean = new OtcForwardInfo();
			bean.setTranNo(tranNo);
			Collection collection = otcForwardDao.getBeansByBean(bean, MatchMode.EXACT);
			otcForwardDao.batchDelete(collection);
			String userNum = UserModelUtil.getUser().getUserId();
			OtcForwardInfo otcForwardInfo = ForwardUtil.getInstance().getForwardByParams(tranNo, userNum);
			String provider = otcForwardInfo.getProvider();
			ForwardUtil.getInstance().forwardDestroy(tranNo, userNum);
			ForwardUtil.getInstance().forwardDestroy(tranNo, provider);
			
			return "1";
			/*//删除本方的记录
			OtcForwardInfo otcForwardInfo=new OtcForwardInfo();
			otcForwardInfo.setUserNum(userNum);
			otcForwardInfo.setWeCcy(weCcy);
			otcForwardInfo.setAnaCcy(anaCcy);
			collection=(List<OtcForwardInfo>)otcForwardDao.getBeansByBean(otcForwardInfo, MatchMode.ANYWHERE);
			//删除对手方的记录
			OtcForwardInfo otcForwardInfo2=new OtcForwardInfo();
			otcForwardInfo2.setProvider(userNum);
			otcForwardInfo2.setWeCcy(anaCcy);
			otcForwardInfo2.setAnaCcy(weCcy);
			collection.addAll(otcForwardDao.getBeansByBean(otcForwardInfo2, MatchMode.ANYWHERE));
			for(int i=0;i<collection.size();i++){
				if(collection.get(i).getStatue().equals("D")){
					collection.remove(i);
				}
			}
			otcForwardDao.batchDelete(collection);*/
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("交易信息删除失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSpotTradeUpdate");
			be.setExceptionType("交易信息删除失败！");
			throw be;
		}
	}
	
	//修改账户信息表的金额
	public boolean acceptDone(String tranNo, String provider, String point){
		try {
			String userNum = UserModelUtil.getUser().getUserId();
			// 取得发起方的交易记录
			OtcForwardInfo otcForwardInfo = ForwardUtil.getInstance().getForwardByParams(tranNo, userNum);
			// 取得对手方的交易记录
			OtcForwardInfo otcForwardInfo2 = ForwardUtil.getInstance().getForwardByParams(tranNo, provider);
			if(otcForwardInfo != null && otcForwardInfo2 != null){
				otcForwardTradeUpdate(tranNo, userNum, point, "accept");
				//接下来的交易执行过程只能等到轮询日期到了才去做
				
				//删除table中的交易记录！但不是删除数据库中的交易记录！
				ForwardUtil.getInstance().forwardDestroy(tranNo, userNum);
				ForwardUtil.getInstance().forwardDestroy(tranNo, provider);
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("交易信息删除失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSpotTradeUpdate");
			be.setExceptionType("交易信息删除失败！");
			throw be;
		}
	}
	
	public String otcForwardTradeCheckPoint(String tranNo, String opponent){
		String userNum = UserModelUtil.getUser().getUserId();
		OtcForwardInfo otcForwardInfo = ForwardUtil.getInstance().getForwardByParams(tranNo, userNum);
		if(otcForwardInfo.getStatue().equals("C")){
			return "C";//取消交易
		}
		else if(otcForwardInfo.getStatue().equals("F")) {
			return "F";//同意价格
		}
		else{
			return otcForwardInfo.getPoint();//修改后的基本点
		}
		//String userNum = UserModelUtil.getUser().getUserId();
//		OtcForwardInfo bean = new OtcForwardInfo();
//		bean.setTranNo(tranNo);
		//bean.setUserNum(userNum);
//		bean.setProvider(opponent);
//		
//		OtcForwardInfo otcForwardInfo = otcForwardDao.getBeanByBean(bean, MatchMode.ANYWHERE);
//		String point = otcForwardInfo.getPoint();
	}
	
	public void otcForwardTradeWithdraw(String tranNo){
		if(tranNo.startsWith("FD")){
			otcForwardTradeDel(tranNo);
		}
	}
	
	//修改账户的交易统计表
	public AccInfoDao getAccInfoDao() {
		return accInfoDao;
	}
	public void setAccInfoDao(AccInfoDao accInfoDao) {
		this.accInfoDao = accInfoDao;
	}
	
	public OtcForwardDao getOtcForwardDao() {
		return otcForwardDao;
	}
	public void setOtcForwardDao(OtcForwardDao otcForwardDao) {
		this.otcForwardDao = otcForwardDao;
	}
	public UsersDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UsersDao userDao) {
		this.userDao = userDao;
	}
	public AnalogueMagDao getAnalogueMagDao() {
		return analogueMagDao;
	}
	public void setAnalogueMagDao(AnalogueMagDao analogueMagDao) {
		this.analogueMagDao = analogueMagDao;
	}
}