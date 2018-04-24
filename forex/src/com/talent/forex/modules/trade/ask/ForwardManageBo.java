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
 * Create By    : ������
 * Description  : Զ��ѯ�۽���
 * Modify Date  : 2014-07-24
 */
public class ForwardManageBo extends BaseBo {

	private AnalogueMagDao analogueMagDao;
	
	private UsersDao userDao;
	
	private OtcForwardDao otcForwardDao;
	
	private AccInfoDao accInfoDao;
	
	//ͨ���û����������ȡ�����ж��ַ������
	public Collection<?> getOpponentGroupQuery(String initGroup){
		AnalogueMag analogueMag=new AnalogueMag();
		analogueMag.setInitGroup(initGroup);
		return analogueMagDao.getBeansByBean(analogueMag,MatchMode.ANYWHERE);
	}
	//ͨ�����ȡ��ͬһ��ŵĶ��ַ�
	public Collection<?> getOpponentQuery(String groupTwoId){
		Users user=new Users();
		user.setGroupTwo(groupTwoId);
		return analogueMagDao.getBeansByBean(user,MatchMode.ANYWHERE);
	}
	
	public String otcForwardTradeAdd(String tranNo, String opponent, String ccy1, String ccy2, String oldPrice, String direct, String amount, String valueDate, String point){
		try {
			Double price = Double.parseDouble(oldPrice) + Double.parseDouble(point) / 10000;//������Ļ���
			
			String userNum = UserModelUtil.getUser().getUserId();
			// ����µĽ���
			if(tranNo == null || tranNo.equals("")){//�״ν���������һ����¼
				tranNo = SequenceUtil.getNextTranSeq("FD");//�����µĽ��ױ��
				//���𷽵Ľ��׼�¼
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
					otcForwardInfo.setTranType("C");//����ҽ���
					accInfo.setAccType("C");
				}else {
					otcForwardInfo.setTranType("W");//��ҽ���
					accInfo.setAccType("W");
				}
				accInfo.setUserNum(userNum);
				accInfo.setCcy(weCcy);
				Double needAmount = 0.0d;//���н�������Ҫ�Ľ�
				//�������ı��ִ��뱾����������ı��ִ�����ַ�
				if(direct.equals("1")){//���׷���Ϊ��
					needAmount=Double.parseDouble(CalculateUtil.getAmountAfterTrade(amount, price+""));
				}
				else {
					needAmount=Double.parseDouble(amount);
				}
				//�жϱ��������ı�����Ӧ������Ƿ��㹻��
				AccInfo accInfo2=accInfoDao.getBeanByBean(accInfo, MatchMode.ANYWHERE);
				if (needAmount > Double.parseDouble(accInfo2.getAmount())){
					logger.error("�û���" + userNum + " ��Ӽ���ѯ�۽���ʧ�ܣ��˻�"+ accInfo.getCcy() +"����");
//					BoException be = new BoException("otcSpotTradeAdd");
//					if (accInfo.getAccType().equals("C"))
//						be.setExceptionType("������˻�"+ accInfo.getCcy() +"����,����ѯ�۽������ύʧ�ܣ�");
//					else
//						be.setExceptionType("����˻�"+ accInfo.getCcy() +"����,����ѯ�۽����ύʧ�ܣ�");
//					throw be;
					return "fail";
				}
				otcForwardInfo.setTranNo(tranNo);//���ױ��
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
				
				//���ַ��Ľ��׼�¼
				OtcForwardInfo otcForwardInfo2 = new OtcForwardInfo();
				otcForwardInfo2.setTranType(otcForwardInfo.getTranType());//����ҽ���
				otcForwardInfo2.setTranNo(tranNo);//���ױ��
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
				// ���׵ļ۸�ǵ��޸ģ�
				otcForwardTradeUpdate(tranNo, userNum, point, "send");
			}
			return tranNo;
		} catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("Զ��ѯ�۽��׵���Ϣû�гɹ����͸����ַ����û���" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcForwardTradeAdd");
			be.setExceptionType("Զ��ѯ�۽��׵���Ϣû�гɹ����͸����ַ���");
			throw be;
		}
	}	
	
	//ͨ�淢���˺�,���׵ı������ͺͶ��ַ��˺�ȡ�ý��ױ�ţ�
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
//			//��ý���״̬����DONE�ļ�¼
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
//			logger.error("��ѯʧ�ܣ��û���" + UserModelUtil.getUser().getUserId());
//			logger.error(e.getMessage(), e);
//			BoException be = new BoException("otcForwardInfoGet");
//			be.setExceptionType("��ѯʧ�ܣ�");
//			throw be;
//		}
//	}
	//�޸Ľ�����Ϣ��
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
				//�޸ķ��𷽵�״̬
//				if((otcForwardInfo.getUserNum()).equals((UserModelUtil.getUser().getUserId()))){
//					otcForwardInfo.setPoint(point);
//					otcForwardInfo.setStatue("P");
//					int times = Integer.parseInt(otcForwardInfo.getTimes()) + 1;
//					otcForwardInfo.setTimes(times + "");
//					otcForwardDao.updateBean(otcForwardInfo);
//				}else{//�޸Ķ��ַ���״̬
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
				//����ִ�к�Ľ��
				Double finalPrice = Double.parseDouble(otcForwardInfo.getPrice()) + Double.parseDouble(point) / 10000;
				otcForwardInfo.setLAmount(CalculateUtil.getAmountAfterTrade(otcForwardInfo.getAmount(), String.valueOf(finalPrice)));
				otcForwardDao.updateBean(otcForwardInfo);
				
				String opponent = otcForwardInfo.getProvider();
				OtcForwardInfo provider = ForwardUtil.getInstance().getForwardByParams(tranNo, opponent);
				provider.setPoint(point);
				provider.setStatue("A");
				provider.setDate(GetDateTimeUtil.getCurrentDate());
				provider.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
				//����ִ�к�Ľ��
				provider.setLAmount(CalculateUtil.getAmountAfterTrade(provider.getAmount(), String.valueOf(finalPrice)));
				otcForwardDao.updateBean(provider);
//				otcForwardInfo.setStatue("A");
//				otcForwardInfo.setPoint(point);
//				otcForwardInfo.setDate(GetDateTimeUtil.getCurrentDate());
//				otcForwardInfo.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
//				//����ִ�к�Ľ��
//				Double finalPrice = Double.parseDouble(otcForwardInfo.getPrice()) + Double.parseDouble(point) / 10000;
//				Double lAmount = Double.parseDouble(otcForwardInfo.getAmount()) * finalPrice;
//				otcForwardInfo.setLAmount(lAmount+"");
//				otcForwardDao.updateBean(otcForwardInfo);
			}
		}catch(Exception e){
			if (e instanceof BoException){
				throw (BoException) e;
			}
			logger.error("������Ϣ�޸�ʧ�ܣ��û���" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcForwardTradeUpdate");
			be.setExceptionType("������Ϣ�޸�ʧ�ܣ�");
			throw be;
		}
	}
	//�����ڶ��ַ���ֹ����ʱ����Ҫɾ�����ף�
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
			/*//ɾ�������ļ�¼
			OtcForwardInfo otcForwardInfo=new OtcForwardInfo();
			otcForwardInfo.setUserNum(userNum);
			otcForwardInfo.setWeCcy(weCcy);
			otcForwardInfo.setAnaCcy(anaCcy);
			collection=(List<OtcForwardInfo>)otcForwardDao.getBeansByBean(otcForwardInfo, MatchMode.ANYWHERE);
			//ɾ�����ַ��ļ�¼
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
			logger.error("������Ϣɾ��ʧ�ܣ��û���" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSpotTradeUpdate");
			be.setExceptionType("������Ϣɾ��ʧ�ܣ�");
			throw be;
		}
	}
	
	//�޸��˻���Ϣ��Ľ��
	public boolean acceptDone(String tranNo, String provider, String point){
		try {
			String userNum = UserModelUtil.getUser().getUserId();
			// ȡ�÷��𷽵Ľ��׼�¼
			OtcForwardInfo otcForwardInfo = ForwardUtil.getInstance().getForwardByParams(tranNo, userNum);
			// ȡ�ö��ַ��Ľ��׼�¼
			OtcForwardInfo otcForwardInfo2 = ForwardUtil.getInstance().getForwardByParams(tranNo, provider);
			if(otcForwardInfo != null && otcForwardInfo2 != null){
				otcForwardTradeUpdate(tranNo, userNum, point, "accept");
				//�������Ľ���ִ�й���ֻ�ܵȵ���ѯ���ڵ��˲�ȥ��
				
				//ɾ��table�еĽ��׼�¼��������ɾ�����ݿ��еĽ��׼�¼��
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
			logger.error("������Ϣɾ��ʧ�ܣ��û���" + UserModelUtil.getUser().getUserId());
			logger.error(e.getMessage(), e);
			BoException be = new BoException("otcSpotTradeUpdate");
			be.setExceptionType("������Ϣɾ��ʧ�ܣ�");
			throw be;
		}
	}
	
	public String otcForwardTradeCheckPoint(String tranNo, String opponent){
		String userNum = UserModelUtil.getUser().getUserId();
		OtcForwardInfo otcForwardInfo = ForwardUtil.getInstance().getForwardByParams(tranNo, userNum);
		if(otcForwardInfo.getStatue().equals("C")){
			return "C";//ȡ������
		}
		else if(otcForwardInfo.getStatue().equals("F")) {
			return "F";//ͬ��۸�
		}
		else{
			return otcForwardInfo.getPoint();//�޸ĺ�Ļ�����
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
	
	//�޸��˻��Ľ���ͳ�Ʊ�
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