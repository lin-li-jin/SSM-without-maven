package com.talent.forex.modules.trade.cash;

import com.talent.auth.bean.model.UserModel;
import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.MarginSpotInfo;
import com.talent.forex.bean.model.MarginSpotInfoModel;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.MarginSpotInfoDao;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.SequenceUtil;
import com.talent.forex.util.UserModelUtil;
import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;


/**
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/8/23.
 */
public class SpotCashBo extends BaseBo {

    Logger logger=Logger.getLogger(this.getClass());

    private AccInfoDao accInfoDao;
    private MarginSpotInfoDao marginSpotInfoDao;


    public double queryAllDone(String anaccy,String userNum){
        MarginSpotInfo marginSpotInfo=new MarginSpotInfo();
        marginSpotInfo.setStatue("D");
        marginSpotInfo.setAnaCcy(anaccy);
        marginSpotInfo.setUserNum(userNum);
        Collection<MarginSpotInfo> marginSpotInfos=
                marginSpotInfoDao.getBeansByBean(marginSpotInfo,MatchMode.ANYWHERE);
        double d=0;
        if (marginSpotInfo!=null && !marginSpotInfos.isEmpty()){
            for (MarginSpotInfo marginSpotInfo1:marginSpotInfos){
                d+=Double.valueOf(marginSpotInfo1.getDealAmt())
                        *Double.valueOf(marginSpotInfo1.getPrice());
            }
        }
        return d;
    }

    /**
     * �������ڽ��׼�¼
     * 1.�ж�weCcy����Ƿ��㹻����֤��
     * 2.�����˺����
     */
    public void newSpotCashAdd(MarginSpotInfoModel model){
        UserModel user=UserModelUtil.getUser();
        Assert.notNull(user.getUserId());
        //1�����û�id���˻������Լ���Ҫ���ɵı�֤����
        AccInfo accInfo=new AccInfo();
        accInfo.setUserNum(user.getUserId());
        accInfo.setCcy(model.getWeCcy());
        accInfo.setAccType("B");
        AccInfo accInfo1=accInfoDao.getBeanByBean(accInfo, MatchMode.ANYWHERE);
        //�ж��˺��Ƿ񱻶���
        if(accInfo1.getActiveDate().equals("--------")){
            logger.error("�û���" + user.getUserId() + " ����Զ�ڽ���ʧ�ܣ���ı�֤���˻��ѱ����ᣡ");
            BoException be = new BoException("forwardInfoAdd");
            be.setExceptionType("����Զ�ڽ���ʧ�ܣ���ı�֤���˻��ѱ����ᣡ");
            throw be;
        }
        //�жϵ�ǰ�Ƿ��㹻�۵���֤���Լ���Ϣ
        double profit=Double.valueOf(model.getAccAmount());
        double dealAmt=Double.valueOf(model.getDealAmt());
        //��Ϣ=����Ļ���*����
        //10������ ���ڴ�ϵͳ��4λС����������0.0010
        double accrual=dealAmt*0.0010;
        //��۽��=��Ϣ+��֤��
        double charge=accrual+profit;
        double amount=Double.valueOf(accInfo1.getAmount());
        //�жϵ�ǰ�˺�����Ƿ�����������ʱֻ����ķ���
        if(model.getDirection().equals("1")) {
            if (charge > amount) {
                logger.error("�û�" + user.getUserId() + "���Ӽ��ڱ�֤����ʧ��,�˻�" + accInfo1.getCcy() + "����");
                BoException bo = new BoException("newSpotCashAdd");
                bo.setExceptionType("��֤���˻�" +accInfo1.getCcy() + "����,����ʧ�ܣ���ǰ���Ϊ:" + accInfo1.getAmount() + "!���д˴ν�����Ҫ" +
                        accInfo1.getCcy() + ":" + charge + "Ԫ,���б�֤��:" + profit + ",��Ϣ:" + accrual);
                throw bo;
            }
        }
        //��ȡ��ǰ��ʱ��
        String date = GetDateTimeUtil.getCurrentDateTimeToMinute();
        //���ӵ����ڽ��׵ļ�¼��
        MarginSpotInfo marginSpotInfo=new MarginSpotInfo();
        marginSpotInfo.setUserNum(accInfo1.getUserNum());
        marginSpotInfo.setAccAmount(model.getAccAmount());
        marginSpotInfo.setAccrual(String.valueOf(accrual));
        marginSpotInfo.setAccount(FormatParamUtil.getAmountAndPriceFmt(accInfo1.getAccno()));
        marginSpotInfo.setWeCcy(model.getWeCcy());
        marginSpotInfo.setAnaCcy(model.getAnaCcy());
        marginSpotInfo.setDealAmt(FormatParamUtil.getAmountAndPriceFmt(model.getDealAmt()));
        marginSpotInfo.setCreateDatatime(date);
        marginSpotInfo.setPrice(model.getPrice());
        marginSpotInfo.setCreateDatatime(GetDateTimeUtil.getCurrentDateTimeToMinute());
        marginSpotInfo.setDirection(model.getDirection());
        marginSpotInfo.setTranType("B"); //���ý��׵�����:��֤��
        marginSpotInfo.setStatue("D");  //�����嵥��״̬ A:���� E:ƽ�� L:���� R:�黹 D:���
        marginSpotInfo.setTranNo(SequenceUtil.getNextTranSeq("MS")); //���ɼ��ڵ���ˮ��
        marginSpotInfoDao.makePersistent(marginSpotInfo,false);
        //�����˺���� ���=ԭ�������-(��֤��+��Ϣ)
        double updatedAccount=amount-charge;
        accInfo1.setAmount(FormatParamUtil.formatDouble(updatedAccount));
        accInfoDao.updateBean(accInfo1);
    }

    public MarginSpotInfo querySpotByTranNo(String tranNo){
        MarginSpotInfo info=new MarginSpotInfo();
        info.setTranNo(tranNo);
        return marginSpotInfoDao.getBeanByBean(info,MatchMode.ANYWHERE);
    }

    /**
     * ��ѯ��ʹ�õ����
     * @param userNo
     * @param accType
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<AccInfo> getAccInfoListByUserNoQuery(String userNo, String accType,String ccy){
        AccInfo accInfo = new  AccInfo();
        accInfo.setUserNum(userNo);
        accInfo.setAccType(accType);
        accInfo.setCcy(ccy);
        return (List<AccInfo>) marginSpotInfoDao.getBeansByBean(accInfo, MatchMode.ANYWHERE);
    }

    public AccInfoDao getAccInfoDao() {
        return accInfoDao;
    }

    public void setAccInfoDao(AccInfoDao accInfoDao) {
        this.accInfoDao = accInfoDao;
    }

    public MarginSpotInfoDao getMarginSpotInfoDao() {
        return marginSpotInfoDao;
    }

    public void setMarginSpotInfoDao(MarginSpotInfoDao marginSpotInfoDao) {
        this.marginSpotInfoDao = marginSpotInfoDao;
    }
}