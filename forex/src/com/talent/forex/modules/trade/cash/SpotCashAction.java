package com.talent.forex.modules.trade.cash;

import com.talent.auth.bean.model.UserModel;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.model.MarginSpotInfoModel;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.util.UserModelUtil;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/8/23.
 */
public class SpotCashAction extends ForexBaseAction{

    Logger logger= Logger.getLogger(this.getClass());

    private MarginSpotInfoModel msim;
    private SpotCashBo spotCashBo;

    private String direction;
    private String ccy;
    private String price;

    /**
     * ��ʼ����֤���ڽ���ҳ�棬���û��ı�֤���˻���Ϣ����ȥ
     * @return
     */
    public String spotCashPageInit(){
        UserModel user = (UserModel) UserModelUtil.getUser();
        requestPut("direction", direction);
        requestPut("price",price);
        requestPut("ccy",ccy);//����ǰ̨Ϊ���ٴδ�����
        requestPut("radioValue",2);//ѡ����Ȩ���׵ı����1
        String weCcy="";    //���ڲ�ѯ��֤���Ӧ���˺����ʹ��
        if(direction.equals("1")){
            weCcy=ccy.substring(3, 6);
            requestPut("weCcy",weCcy);
            requestPut("anaCcy",ccy.substring(0, 3));
        }else{
            weCcy=ccy.substring(0, 3);
            requestPut("weCcy",weCcy);
            requestPut("anaCcy",ccy.substring(3, 6));
        }
        List<AccInfo> accInfoListB = spotCashBo.getAccInfoListByUserNoQuery(user.getUserId(),"B",weCcy);
        if (accInfoListB!=null && !accInfoListB.isEmpty())
            requestPut("amount",accInfoListB.get(0).getAmount());
        return SUCCESS;
    }

    /**
     * ����һ�����ڱ�֤��
     * @return
     */
    public String spotCashPageAdd(){
        spotCashBo.newSpotCashAdd(msim);
        return SUCCESS;
    }



    public SpotCashBo getSpotCashBo() {
        return spotCashBo;
    }

    public void setSpotCashBo(SpotCashBo spotCashBo) {
        this.spotCashBo = spotCashBo;
    }

    public MarginSpotInfoModel getMsim() {
        return msim;
    }

    public void setMsim(MarginSpotInfoModel msim) {
        this.msim = msim;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
