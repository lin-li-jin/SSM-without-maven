
package com.talent.forex.modules.trade.cash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.talent.auth.bean.model.UserModel;
import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.MarginForwardInfo;
import com.talent.forex.bean.model.MarginForwardInfoModel;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.BTranFlowMappingDao;
import com.talent.forex.dao.MarginEnlargeDao;
import com.talent.forex.dao.MarginForwardInfoDao;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.GetFixWordUtil;
import com.talent.forex.util.SequenceUtil;
import com.talent.forex.util.UserModelUtil;

/*
 * Amendment No.: FOEXAS013
 * Create By    : atggdsaiDong
 * Description  : 保证金远期交易
 * Modify Date  : 2014-07-28
 */
public class ForwardCashBo extends BaseBo{
	
	public ForwardCashBo(){}
	
	private MarginForwardInfoDao marginForwardInfoDao;
	private MarginEnlargeDao marginEnlargeDao;
	private BTranFlowMappingDao bTranFlowMappingDao;
	private AccInfoDao accInfoDao;

	/**
	 * 查询anaccy已经生效的远期交易
	 */
	public double queryAllDone(String anaccy,String userNum){
		MarginForwardInfo marginForwardInfo=new MarginForwardInfo();
		marginForwardInfo.setStatue("D");
		marginForwardInfo.setUserNum(userNum);
		marginForwardInfo.setAnaCcy(anaccy);
		List<MarginForwardInfo> marginForwardInfos=
				(List<MarginForwardInfo>) marginForwardInfoDao.getBeansByBean(marginForwardInfo,MatchMode.ANYWHERE);
		double d=0;
		if (marginForwardInfos!=null && !marginForwardInfos.isEmpty()){
			for (MarginForwardInfo info:marginForwardInfos){
				//由于dealAmt保存的是买入的资金，需要乘以价格
				d+=Double.valueOf(info.getDealAmt())*Double.valueOf(info.getPrice());
			}
		}
		return d;
	}
	

	/**
	 * 根据用户的登录账号和账户类型查询对应账户类型的信息
	 * 对数据库的查询,不加try catch,事务不用回滚
	 * @param userNo
	 * @param accType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AccInfo> getAccInfoListByUserNoQuery(String userNo,String accType){
		AccInfo accInfo = new  AccInfo();
		accInfo.setUserNum(userNo);
		accInfo.setAccType(accType);
		return (List<AccInfo>) marginForwardInfoDao.getBeansByBean(accInfo, MatchMode.ANYWHERE);
	}
	
	/**
	 * 新增一条保证金远期交易记录
	 * @param model
	 */
	public void newForwardCashAdd(MarginForwardInfoModel model){
		
		try{		
			UserModel user = (UserModel)UserModelUtil.getUser();
			MarginForwardInfo bean = new MarginForwardInfo();
			
			//这里是要判断用户输入的金额是否大于账户余额的，已确定都是从交易员的角度去理解买卖价
			//因为无论是买或者卖  都是weCcy币种对应的账户在减少  anaCcy币种对应的账户金额在增加
			//所以在判断余额是否足够用来交易,只需要判断weCcy币种对应的账户与用户输入的金额大小就行了
			//例如CNY/USD,当以买价交易,则是交易员买入CNY,卖出USD，用户的CNY增加,USD减少,所以把用户输入的金额与保证金账户里的USD币种比较
			AccInfo accInfo = new AccInfo();//要通过用户账户,账户类型,还有账户币种才能确定唯一的一条记录
			accInfo.setUserNum(user.getUserId());
			accInfo.setAccType("B");
			accInfo.setCcy(model.getWeCcy());
			accInfo = accInfoDao.getBeanByBean(accInfo, MatchMode.ANYWHERE);
			
			
			// start 先判断用户账户是否已被冻结 
			if(accInfo.getActiveDate().equals("--------")){
				logger.error("用户：" + user.getUserId() + " 添加远期交易失败！你的保证金账户已被冻结！");
				BoException be = new BoException("forwardInfoAdd");
					be.setExceptionType("添加远期交易失败！你的保证金账户已被冻结！");
				throw be;
			}
			//end
			//判断当前是否足够扣掉保证金以及利息
			double profit=Double.valueOf(model.getAccountAmount());
			double dealAmt=Double.valueOf(model.getDealAmount());
			//利息=扩大的货币*基点
			//10个基点 由于此系统是4位小数，所以是0.0010
			double accrual=dealAmt*0.0010;
			//需扣金额=利息+保证金
			double charge=accrual+profit;

			//accInfo表里保存是实际金额   不是放大倍数的虚盘金额
			if(model.getDirection().equals("1")){
				//1代表买方向   在判断余额时  要把交易金额*价格再与账户余额比较大小
				if (charge > Double.parseDouble(accInfo.getAmount())){
					logger.error("用户：" + user.getUserId() + " 添加远期交易失败！账户"+ accInfo.getCcy() +"金额不足");
					BoException be = new BoException("forwardInfoAdd");
						be.setExceptionType("保证金账户"+ accInfo.getCcy() +"金额不足,交易失败！当前余额为:"+accInfo.getAmount()+"!进行此次交易需要"+accInfo.getCcy()+charge+"元," +
								"其中利息:"+accrual+",保证金:"+profit);
					throw be;
				}
			}else{
				//model.getDirection().equals("0")
				//1代表卖方向  
				if (Double.parseDouble(model.getAccountAmount()) > Double.parseDouble(accInfo.getAmount())){
					logger.error("用户：" + user.getUserId() + " 添加远期交易失败！账户"+ accInfo.getCcy() +"金额不足");
					BoException be = new BoException("forwardInfoAdd");
						be.setExceptionType("保证金账户"+ accInfo.getCcy() +"金额不足,交易失败！当前余额为:"+accInfo.getAmount()+"!进行此次交易需要"+accInfo.getCcy()+(Double.parseDouble(model.getAccountAmount()))+"元");
					throw be;
				}
			}


			int days = 0;
			if("3d".equals(model.getValueDate())){
				days = 3;
			}else if("5d".equals(model.getValueDate())){
				days = 5;
			}else {
				days = 30;
			}
			String date = GetDateTimeUtil.getCurrentDateTimeToMinute();
			String afterDate = GetDateTimeUtil.getDates(GetDateTimeUtil.dateTransFmt(date), days);//远期执行的日期
			bean.setTranType("B");//保证金交易都属于外币交易,用B表示
			bean.setTranNo(SequenceUtil.getNextTranSeq("FC"));//在BO里操作流水号为了出异常的时候,流水表的也能回滚
			bean.setUserNum(user.getUserId());
			bean.setValueDate(GetDateTimeUtil.dateTransFmt2(afterDate)); 
			bean.setWeCcy(model.getWeCcy());
			bean.setAnaCcy(model.getAnaCcy());
			bean.setAccount(model.getAccount());
			bean.setAccAmount(FormatParamUtil.getAmountAndPriceFmt(model.getAccountAmount()));
			bean.setDealAmt(FormatParamUtil.getAmountAndPriceFmt(model.getDealAmount()));
			bean.setPrice(model.getPrice());
			bean.setStatue("A");
			bean.setCreateDatetime(date);
			bean.setDirection(model.getDirection());
			marginForwardInfoDao.makePersistent(bean, false);
		}catch(Exception e){
			if (e instanceof BoException) {
				throw (BoException) e;
			}
			logger.error("新增保证金远期交易失败：");
			logger.error(e.getMessage(), e);
			BoException be = new BoException("newForwardCashAdd");
			be.setExceptionType("新增保证金远期交易失败：");
			throw be;
		}
	}
	

	
	
	/**
	 * 这个方法是查询所有的保证金远期交易记录,包括所有的状态
	 * 不能用getAll()方法,这样子把整个table的记录都拿出来了,只需要拿当前登录账户的记录
	 * 对数据库的查询,不加try catch,事务不用回滚
	 * @return
	 */
	public Collection<?> allForwardCashQuery(String hql,String tradeStatus){
		UserModel user = (UserModel)UserModelUtil.getUser();
		ArrayList<String> paraList = new ArrayList<String>();
		String status = tradeStatus.equals("0")?"":tradeStatus;
		if (status.equals(""))
			status = GetFixWordUtil.getLikeWords(status);
		paraList.add(user.getUserId());
		paraList.add(status);
		return marginForwardInfoDao.getBeansByParams(hql, paraList);
	}
	
	
	
	/**
	 * 根据tranNo查询唯一的远期交易记录
	 * @param tranNo
	 * @return
	 */
	public MarginForwardInfo queryForwardCashByTranNo(String tranNo){
		MarginForwardInfo marginForwardInfo = new MarginForwardInfo();
		marginForwardInfo.setTranNo(tranNo);
		return marginForwardInfoDao.getBeanByBean(marginForwardInfo, MatchMode.ANYWHERE);
	}


	public MarginForwardInfoDao getMarginForwardInfoDao() {
		return marginForwardInfoDao;
	}

	public void setMarginForwardInfoDao(MarginForwardInfoDao marginForwardInfoDao) {
		this.marginForwardInfoDao = marginForwardInfoDao;
	}

	public MarginEnlargeDao getMarginEnlargeDao() {
		return marginEnlargeDao;
	}

	public void setMarginEnlargeDao(MarginEnlargeDao marginEnlargeDao) {
		this.marginEnlargeDao = marginEnlargeDao;
	}

	public AccInfoDao getAccInfoDao() {
		return accInfoDao;
	}

	public void setAccInfoDao(AccInfoDao accInfoDao) {
		this.accInfoDao = accInfoDao;
	}

	public BTranFlowMappingDao getbTranFlowMappingDao() {
		return bTranFlowMappingDao;
	}

	public void setbTranFlowMappingDao(BTranFlowMappingDao bTranFlowMappingDao) {
		this.bTranFlowMappingDao = bTranFlowMappingDao;
	}
}
