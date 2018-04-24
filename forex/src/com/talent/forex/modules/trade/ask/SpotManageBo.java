package com.talent.forex.modules.trade.ask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.talent.auth.bean.domain.Users;
import com.talent.auth.bean.model.UserModel;
import com.talent.auth.dao.UsersDao;
import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.AnalogueMag;
import com.talent.forex.bean.domain.BTranFlowMapping;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.OtcForwardInfo;
import com.talent.forex.bean.domain.OtcSpotInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.bean.model.SpotTradeModel;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.AnalogueMagDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.OtcSpotDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.forex.util.CalculateUtil;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.SequenceUtil;
import com.talent.forex.util.SpotUtil;
import com.talent.forex.util.UserModelUtil;
import com.talent.hibernate.base.DaoException;
/*
 * Amendment No.: FOEXAS006
 * Create By    : 陈整队
 * Description  : 即期询价交易
 * Modify Date  : 2014-07-24
 */
public class SpotManageBo extends BaseBo {
	
	private AnalogueMagDao analogueMagDao;
	
	private UsersDao userDao;
	
	private OtcSpotDao otcSpotDao;
	
	private AccInfoDao accInfoDao;
	
	private CTranFlowMappingDao cTranFlowMappingDao;
	private WTranFlowMappingDao wTranFlowMappingDao;

	/**
	 * 通过用户的所属组号取得所有对手方的组号(整队写的，暂时好像用不上，但还是不理它了)
	 * @return
	 */
	public Collection getOpponentGroupQuery(){
		String initGroup = UserModelUtil.getUser().getGroupTwoId();
		AnalogueMag analogueMag=new AnalogueMag();
		analogueMag.setInitGroup(initGroup);
		return analogueMagDao.getBeansByBean(analogueMag,MatchMode.ANYWHERE);
	}
	
	/**
	 * 通过组号取得同一组号的对手方(整队写的，暂时好像用不上，但还是不理它了)
	 * @param groupTwoId
	 * @return
	 */
	public Collection getOpponentQuery(String groupTwoId){
		Users user=new Users();
		user.setGroupTwo(groupTwoId);
		return analogueMagDao.getBeansByBean(user,MatchMode.EXACT);
	}
	
	/**
	 * 首次发起交易则生成新的交易记录，若交易记录存在则更新交易记录
	 * @param tranNo 交易记录
	 * @param opponent 对手方
	 * @param ccy1  前台I sell ccy1 vs ccy2
	 * @param ccy2  前台I sell ccy1 vs ccy2
	 * @param price 发起方输入的价格
	 * @param direct 交易方向1(buy)/0(sell)
	 * @param amount 交易金额
	 * @return tranNo 无论新增交易记录，还是更新交易记录，都要返回交易记录
	 */
	public String otcSpotTradeAdd(String tranNo, String opponent, String ccy1, String ccy2, String price, String direct, String amount){
		try {			
			String userNum = UserModelUtil.getUser().getUserId();
			// 添加新的交易
			if (tranNo.equals("") || tranNo == null) {//首次发起交易则生成新的交易记录				
				tranNo = SequenceUtil.getNextTranSeq("ST");//生成新的交易编号
				//发起方的交易记录
				OtcSpotInfo otcSpotInfo = new OtcSpotInfo();
				AccInfo accInfo = new AccInfo();
				String weCcy = "";
				String anaCcy = "";
				if (direct.equals("0")) {
					weCcy = ccy1;
					anaCcy = ccy2;
				} else {
					anaCcy = ccy1;
					weCcy = ccy2;
				}
				if(weCcy.equals("CNY")||anaCcy.equals("CNY")){
					otcSpotInfo.setTranType("C");//人民币交易
					accInfo.setAccType("C");
				}else {
					otcSpotInfo.setTranType("W");//外币对交易
					accInfo.setAccType("W");
				}
				accInfo.setUserNum(userNum);
				accInfo.setCcy(weCcy);
				Double needAmount = 0.0d;//进行交易所需要的金额！
				//将卖出的币种存入本方，将买入的币种存入对手方
				if(direct.equals("1")){//交易方向为买
					needAmount = Double.parseDouble(CalculateUtil.getAmountAfterTrade(amount, price));
				}else {
					needAmount = Double.parseDouble(amount);
				}
				//判断本方卖出的币种相应的余额是否足够！
				AccInfo accInfo2=accInfoDao.getBeanByBean(accInfo, MatchMode.EXACT);
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
				otcSpotInfo.setTranNo(tranNo);//交易编号
				otcSpotInfo.setUserNum(userNum);
				otcSpotInfo.setProvider(opponent);
				otcSpotInfo.setWeCcy(weCcy);
				otcSpotInfo.setAnaCcy(anaCcy);
				otcSpotInfo.setAmount(FormatParamUtil.getAmountAndPriceFmt(amount));
				otcSpotInfo.setCreateDatetime(GetDateTimeUtil.getCurrentDateTimeToMinute());
				otcSpotInfo.setPrice(price);
				otcSpotInfo.setIsInit("1");
				otcSpotInfo.setStatue("P");
				otcSpotInfo.setDirection(direct);
				otcSpotInfo.setTimes("0");
				
				otcSpotDao.addBean(otcSpotInfo);
				OtcSpotInfo bean = otcSpotDao.getBeanByBean(otcSpotInfo, MatchMode.EXACT);
				SpotUtil.getInstance().spotAdd(bean);
				//对手方的交易记录
				OtcSpotInfo otcSpotInfo2 = new OtcSpotInfo();
				otcSpotInfo2.setTranType(otcSpotInfo.getTranType());//人民币交易
				otcSpotInfo2.setTranNo(tranNo);//交易编号
				otcSpotInfo2.setUserNum(opponent);
				otcSpotInfo2.setProvider(userNum);
				otcSpotInfo2.setAmount(FormatParamUtil.getAmountAndPriceFmt(amount));
				otcSpotInfo2.setWeCcy(anaCcy);
				otcSpotInfo2.setAnaCcy(weCcy);
				otcSpotInfo2.setCreateDatetime(GetDateTimeUtil.getCurrentDateTimeToMinute());
				otcSpotInfo2.setPrice(price);
				otcSpotInfo2.setIsInit("0");
				otcSpotInfo2.setStatue("R");
				otcSpotInfo2.setTimes("0");
				if(direct.equals("1")){
					otcSpotInfo2.setDirection("0");
				}else{
					otcSpotInfo2.setDirection("1");
				}
				
				otcSpotDao.addBean(otcSpotInfo2);
				OtcSpotInfo bean2 = otcSpotDao.getBeanByBean(otcSpotInfo2, MatchMode.EXACT);
				SpotUtil.getInstance().spotAdd(bean2);
			}
			else {
				otcSpotTradeUpdate(tranNo, userNum, price, "send");
			}
			return tranNo;
		} catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("即期询价交易的信息没有成功发送给对手方！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSpotTradeAdd");
			be.setExceptionType("即期询价交易的信息没有成功发送给对手方！");
			throw be;
		}
	}
	
	//通告发起方账号和对手方账号取得交易编号！
//	public OtcSpotInfo otcSpotInfoGet(String tranNo, String userNum){
//		OtcSpotInfo bean = new OtcSpotInfo();
//		bean.setTranNo(tranNo);//tranNo必须是已经存在的！
//		bean.setUserNum(userNum);
//		OtcSpotInfo otcSpotInfo = otcSpotDao.getBeanByBean(bean, MatchMode.EXACT);
//		return otcSpotInfo;
//		/*otcSpotInfo.setProvider(provider);
//		otcSpotInfo.setWeCcy(weCcy);
//		otcSpotInfo.setAnaCcy(anaCcy);*/
//		/*Collection collection = otcSpotDao.getBeansByBean(otcSpotInfo, MatchMode.ANYWHERE);
//		Iterator it=collection.iterator();
//		//获得交易状态不是DONE的记录
//		while(it.hasNext()){
//			OtcSpotInfo otcSpotInfo2=(OtcSpotInfo) it.next();
//			if(otcSpotInfo2.getStatue().equals("P")||otcSpotInfo2.getStatue().equals("R")){
//				return otcSpotInfo2;
//			}
//		}
//		return null;*/
//	}
	
	/**
	 * 更新交易信息！
	 * @param tranNo 交易记录
	 * @param userNum 用户帐号，如1115442
	 * @param price 发起方修改后的价格
	 * @param type send(发送，交易未结束)/accept(接受，交易结束才用)
	 */
	public void otcSpotTradeUpdate(String tranNo, String userNum, String price, String type){
		try {
			if(type.equals("send")){
				//修改发起方的状态
//				OtcSpotInfo bean = new OtcSpotInfo();
//				bean.setTranNo(tranNo);
//				bean.setUserNum(userNum);
				//OtcSpotInfo otcSpotInfo = otcSpotDao.getBeanByBean(bean, MatchMode.EXACT);
				OtcSpotInfo otcSpotInfo = SpotUtil.getInstance().getSpotByParams(tranNo, userNum);
				otcSpotInfo.setPrice(price);
				otcSpotInfo.setStatue("P");
				int times = Integer.parseInt(otcSpotInfo.getTimes());
				times++;
				otcSpotInfo.setTimes(String.valueOf(times));
				//otcSpotDao.updateBean(otcSpotInfo);
				//修改对手方的状态
//				OtcSpotInfo bean2 = new OtcSpotInfo();
//				bean2.setTranNo(tranNo);
//				bean2.setProvider(userNum);
				String opponent = otcSpotInfo.getProvider();
				OtcSpotInfo provider = SpotUtil.getInstance().getSpotByParams(tranNo, opponent);
				provider.setPrice(price);
				provider.setStatue("R");
				//otcSpotDao.updateBean(provider);
				
//				if((otcSpotInfo.getUserNum()).equals((UserModelUtil.getUser().getUserId()))){
//					otcSpotInfo.setPrice(price);
//					otcSpotInfo.setStatue("P");
//					int times = Integer.parseInt(otcSpotInfo.getTimes());
//					times++;
//					otcSpotInfo.setTimes(String.valueOf(times));
//					otcSpotDao.updateBean(otcSpotInfo);
//				}
//				else{
//					otcSpotInfo.setStatue("R");
//					otcSpotInfo.setPrice(price);
//					otcSpotDao.updateBean(otcSpotInfo);
//				}
			}
			else if(type.equals("accept")){
				//修改本方交易记录
//				OtcSpotInfo bean = new OtcSpotInfo();
//				bean.setTranNo(tranNo);
//				bean.setUserNum(userNum);
//				OtcSpotInfo otcSpotInfo = otcSpotDao.getBeanByBean(bean, MatchMode.EXACT);
				OtcSpotInfo otcSpotInfo = SpotUtil.getInstance().getSpotByParams(tranNo, userNum);
				otcSpotInfo.setPrice(price);
				otcSpotInfo.setStatue("D");
				otcSpotInfo.setDate(GetDateTimeUtil.getCurrentDate());
				otcSpotInfo.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
				//交易执行后的金额
				otcSpotInfo.setLAmount(CalculateUtil.getAmountAfterTrade(otcSpotInfo.getAmount(), price));
				otcSpotDao.updateBean(otcSpotInfo);
				//修改对手方的状态
//				OtcSpotInfo bean2 = new OtcSpotInfo();
//				bean2.setTranNo(tranNo);
//				bean2.setProvider(userNum);
//				OtcSpotInfo provider = otcSpotDao.getBeanByBean(bean2, MatchMode.EXACT);
				String opponent = otcSpotInfo.getProvider();
				OtcSpotInfo provider = SpotUtil.getInstance().getSpotByParams(tranNo, opponent);
				provider.setPrice(price);
				provider.setStatue("D");
				provider.setDate(GetDateTimeUtil.getCurrentDate());
				provider.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
				//交易执行后的金额
				provider.setLAmount(CalculateUtil.getAmountAfterTrade(provider.getAmount(), price));
				otcSpotDao.updateBean(provider);
			}
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("交易信息修改失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSpotTradeUpdate");
			be.setExceptionType("交易信息修改失败！");
			throw be;
		}
	}
	
	/**
	 * 仅限于对手方终止交易时，需要删除交易！
	 * @param tranNo 只需要交易编号就能删除了
	 * @return 标志，代表删除成功！
	 */
	public String otcSpotTradeDel(String tranNo){
		try {
			OtcSpotInfo bean = new OtcSpotInfo();
			bean.setTranNo(tranNo);
			Collection collection = otcSpotDao.getBeansByBean(bean, MatchMode.ANYWHERE);
			otcSpotDao.batchDelete(collection);
			String userNum = UserModelUtil.getUser().getUserId();
			OtcSpotInfo otcSpotInfo = SpotUtil.getInstance().getSpotByParams(tranNo, userNum);
			String provider = otcSpotInfo.getProvider();
			SpotUtil.getInstance().spotDestroy(tranNo, userNum);
			SpotUtil.getInstance().spotDestroy(tranNo, provider);
			
			return "1";
			/*//删除本方的记录
			OtcSpotInfo otcSpotInfo=new OtcSpotInfo();
			otcSpotInfo.setTranNo(tranNo);
			otcSpotInfo.setUserNum(userNum);
			otcSpotInfo.setWeCcy(weCcy);
			otcSpotInfo.setAnaCcy(anaCcy);
			collection=(List<OtcSpotInfo>)otcSpotDao.getBeansByBean(otcSpotInfo, MatchMode.ANYWHERE);
			//删除对手方的记录
			OtcSpotInfo otcSpotInfo2=new OtcSpotInfo();
			otcSpotInfo.setTranNo(tranNo);
			otcSpotInfo2.setProvider(userNum);
			otcSpotInfo2.setWeCcy(anaCcy);
			otcSpotInfo2.setAnaCcy(weCcy);
			collection.addAll(otcSpotDao.getBeansByBean(otcSpotInfo2, MatchMode.ANYWHERE));
			for(int i=0;i<collection.size();i++){
				if(collection.get(i).getStatue().equals("D")){
					collection.remove(i);
				}
			}
			otcSpotDao.batchDelete(collection);*/
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("交易信息删除失败！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSpotTradeDelete");
			be.setExceptionType("交易信息删除失败！");
			throw be;
		}
	}

	/**
	 * 交易成功后的操作！
	 * @param tranNo 确认交易后的交易编号
	 * @param provider 确定交易的对手方
	 * @param price 最终价格
	 * @return true则交易成功，false则交易失败
	 */
	public boolean acceptDone(String tranNo, String provider, String price){
		try {
			// 取得发起方的交易记录
			String userNum = UserModelUtil.getUser().getUserId();
//			OtcSpotInfo bean = new OtcSpotInfo();
//			bean.setTranNo(tranNo);
//			bean.setUserNum(userNum);
			OtcSpotInfo otcSpotInfo = SpotUtil.getInstance().getSpotByParams(tranNo, userNum);
			// 取得对手方的交易记录
			OtcSpotInfo otcSpotInfo2 = SpotUtil.getInstance().getSpotByParams(tranNo, provider);
			if (otcSpotInfo != null && otcSpotInfo2 != null) {
				// 修改发起方和对手方的交易记录
				otcSpotTradeUpdate(tranNo, userNum, price, "accept");
				//otcSpotTradeUpdate(otcSpotInfo2, price, "accept");
				Double price1 = Double.parseDouble(price);//交易的价格
				Double amt = Double.parseDouble(otcSpotInfo.getAmount());//交易的金额

				// 修改本方账户信息表中对应币种的金额
				//币种减少的账户
				AccInfo accInfo = new AccInfo();
				accInfo.setUserNum(userNum);
				accInfo.setAccType(otcSpotInfo.getTranType());
				accInfo.setCcy(otcSpotInfo.getWeCcy());// 卖出币种
				AccInfo weAccInfo = accInfoDao.getBeanByBean(accInfo, MatchMode.EXACT);

				//币种增加的账户
				AccInfo accInfo2 = new AccInfo();
				accInfo2.setUserNum(userNum);
				accInfo2.setAccType(otcSpotInfo.getTranType());
				accInfo2.setCcy(otcSpotInfo.getAnaCcy());//买入币种
				AccInfo anaAccInfo = accInfoDao.getBeanByBean(accInfo2, MatchMode.EXACT);

				//acc_info表更新的金额用的是实际金额
				Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());//weCcy币种对应的账户余额
				Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());//anaCcy币种对应的账户余额

				//weCcy币种对应的账户金额     = 余额-实际交易金额(以交易员的角度，weCcy存储的永远是卖出去的币种)
				//anaCcy币种对应的账户金额   = 余额+实际交易金额*价格(以交易员的角度，anaCcy存储的永远是买入的币种)
				if (otcSpotInfo.getDirection().equals("0")) {
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney - amt));
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney + price1*amt));
				} else {
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney - price1*amt));
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney + amt));
				}
				accInfoDao.updateBean(weAccInfo);
				accInfoDao.updateBean(anaAccInfo);

				//修改对手方账户的信息表对应币种的金额
				//币种增加的账户
				AccInfo accInfo3 = new AccInfo();
				accInfo3.setUserNum(provider);
				accInfo3.setAccType(otcSpotInfo.getTranType());
				accInfo3.setCcy(otcSpotInfo.getWeCcy());// 买入币种
				AccInfo weAccInfo1 = accInfoDao.getBeanByBean(accInfo3,MatchMode.EXACT);

				//币种减少的账户
				AccInfo accInfo4 = new AccInfo();
				accInfo4.setUserNum(provider);
				accInfo4.setAccType(otcSpotInfo.getTranType());
				accInfo4.setCcy(otcSpotInfo.getAnaCcy());//卖出币种
				AccInfo anaAccInfo1 = accInfoDao.getBeanByBean(accInfo4,MatchMode.EXACT);

				//acc_info表更新的金额用的是实际金额
				Double weCcyMoney1 = Double.parseDouble(weAccInfo1.getAmount());//weCcy币种对应的账户余额
				Double anaCcyMoney1 = Double.parseDouble(anaAccInfo1.getAmount());//anaCcy币种对应的账户余额

				//weCcy币种对应的账户金额     = 余额+实际交易金额*价(以对手方的角度来看，weCcy存储的永远是买入的币种)
				//anaCcy币种对应的账户金额   = 余额-实际交易金额格(对手方的角度来看，anaCcy存储的永远是卖出的币种)
				if (otcSpotInfo.getDirection().equals("0")) {
					weAccInfo1.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney1 + amt));
					anaAccInfo1.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney1 - price1 * amt));
				} else {
					weAccInfo1.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney1 + price1 * amt));
					anaAccInfo1.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney1 - amt));
				}
				accInfoDao.updateBean(weAccInfo1);
				accInfoDao.updateBean(anaAccInfo1);
				//更新交易流水表c_tran_flow_mapping
				if (otcSpotInfo.getTranType().equals("C")) {
					//本方
					CTranFlowMapping c = new CTranFlowMapping();
					c.setUserNum(userNum);
					CTranFlowMapping cTranFlowMapping = cTranFlowMappingDao.getBeanByBean(c, MatchMode.EXACT);
					//交易次数加1，交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
					cTranFlowMapping.setOtcSpotQty((Integer.parseInt(cTranFlowMapping.getOtcSpotQty()) + 1)+ "");
					cTranFlowMapping.setOtcSpotAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(cTranFlowMapping.getOtcSpotAmt())+amt));
					cTranFlowMapping.setCount(cTranFlowMapping.getCount() + 1);
					cTranFlowMapping.setAmount(cTranFlowMapping.getAmount()+ amt);

					//对手方
					CTranFlowMapping c1 = new CTranFlowMapping();
					c1.setUserNum(provider);
					CTranFlowMapping cTranFlowMapping1 = cTranFlowMappingDao.getBeanByBean(c1, MatchMode.EXACT);
					//交易次数加1，交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
					cTranFlowMapping1.setOtcSpotQty((Integer.parseInt(cTranFlowMapping1.getOtcSpotQty()) + 1)+ "");
					cTranFlowMapping1.setOtcSpotAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(cTranFlowMapping1.getOtcSpotAmt())+ amt));
					cTranFlowMapping1.setCount(cTranFlowMapping1.getCount() + 1);
					cTranFlowMapping1.setAmount(cTranFlowMapping1.getAmount()+ amt);
					cTranFlowMappingDao.updateBean(cTranFlowMapping);
					cTranFlowMappingDao.updateBean(cTranFlowMapping1);
				} else {
					//本方
					WTranFlowMapping w = new WTranFlowMapping();
					w.setUserNum(userNum);
					WTranFlowMapping wTranFlowMapping = wTranFlowMappingDao.getBeanByBean(w, MatchMode.EXACT);
					//交易次数加1，交易量加金额 ， 总交易次数加一，总交易量加金额
					String qty = wTranFlowMapping.getOtcSpotQty();
					int otcSpotQty = Integer.parseInt(qty);
					otcSpotQty++;
					wTranFlowMapping.setOtcSpotQty(otcSpotQty + "");
					wTranFlowMapping.setOtcSpotAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wTranFlowMapping.getOtcSpotAmt())+ amt));
					wTranFlowMapping.setCount(wTranFlowMapping.getCount() + 1);
					wTranFlowMapping.setAmount(wTranFlowMapping.getAmount()+ amt);

					//对手方
					WTranFlowMapping w1 = new WTranFlowMapping();
					w1.setUserNum(provider);
					WTranFlowMapping wTranFlowMapping1 = wTranFlowMappingDao.getBeanByBean(w1, MatchMode.EXACT);
					//交易次数加1，交易量加金额 ， 总交易次数加一，总交易量加金额
					String qty1 = wTranFlowMapping1.getOtcSpotQty();
					int otcSpotQty1 = Integer.parseInt(qty1);
					otcSpotQty1++;
					wTranFlowMapping1.setOtcSpotQty(otcSpotQty1 + "");
					wTranFlowMapping1.setOtcSpotAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wTranFlowMapping1.getOtcSpotAmt())+ amt));
					wTranFlowMapping1.setCount(wTranFlowMapping1.getCount() + 1);
					wTranFlowMapping1.setAmount(wTranFlowMapping1.getAmount()+ amt);
					wTranFlowMappingDao.updateBean(wTranFlowMapping);
					wTranFlowMappingDao.updateBean(wTranFlowMapping1);
					
					//删除table中的交易记录！但不是删除数据库中的交易记录！
					SpotUtil.getInstance().spotDestroy(tranNo, userNum);
					SpotUtil.getInstance().spotDestroy(tranNo, provider);
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("即期询价交易不成功！用户：" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("acceptDone");
			be.setExceptionType("即期询价交易不成功！");
			throw be;
		}
	}
	
	/**
	 * 不断地查询table的价格变化情况
	 * @param tranNo 待查询的交易编号
	 * @param provider 待查询的对手方
	 * @return 状态或者价格，主要是控制前台的价格更变情况
	 */
	public String otcSpotTradeCheckPrice(String tranNo, String provider){
		//OtcSpotInfo otcSpotInfo = otcSpotDao.getBeanByBean2(tranNo, provider);
		String userNum = UserModelUtil.getUser().getUserId();
		OtcSpotInfo otcSpotInfo = SpotUtil.getInstance().getSpotByParams(tranNo, userNum);
		if(otcSpotInfo.getStatue().equals("C")){
			return "C";//取消交易
		}
		else if(otcSpotInfo.getStatue().equals("F")) {
			return "F";//同意价格
		}
		else{
			return otcSpotInfo.getPrice();//修改后的价格
		}
		//String userNum = UserModelUtil.getUser().getUserId();
//		OtcSpotInfo bean = new OtcSpotInfo();
//		bean.setTranNo(tranNo);
//		//bean.setUserNum(userNum);
//		bean.setProvider(provider);
//		OtcSpotInfo otcSpotInfo = otcSpotDao.getBeanByBean(bean, MatchMode.EXACT);
//		String price = otcSpotInfo.getPrice();
		//System.out.println("----------price-------------"+price);
	}
	
	/**
	 * 点击withdraw后，则通过该方法来删除所有的交易记录
	 * @param tranNo 待删除的交易记录
	 */
	public void otcSpotTradeWithdraw(String tranNo){
		if(tranNo.startsWith("ST")){
			otcSpotTradeDel(tranNo);
		}
	}
	
	public AccInfoDao getAccInfoDao() {
		return accInfoDao;
	}
	public void setAccInfoDao(AccInfoDao accInfoDao) {
		this.accInfoDao = accInfoDao;
	}
	public OtcSpotDao getOtcSpotDao() {
		return otcSpotDao;
	}
	public void setOtcSpotDao(OtcSpotDao otcSpotDao) {
		this.otcSpotDao = otcSpotDao;
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
	public CTranFlowMappingDao getcTranFlowMappingDao() {
		return cTranFlowMappingDao;
	}
	public void setcTranFlowMappingDao(CTranFlowMappingDao cTranFlowMappingDao) {
		this.cTranFlowMappingDao = cTranFlowMappingDao;
	}
	public WTranFlowMappingDao getwTranFlowMappingDao() {
		return wTranFlowMappingDao;
	}
	public void setwTranFlowMappingDao(WTranFlowMappingDao wTranFlowMappingDao) {
		this.wTranFlowMappingDao = wTranFlowMappingDao;
	}
}
