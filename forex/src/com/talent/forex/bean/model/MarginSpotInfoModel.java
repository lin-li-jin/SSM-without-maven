package com.talent.forex.bean.model;

/**
 * ���model��������ǰ�˵Ĳ���
 * ������һ�����ڱ�֤���׵�ʱ���ҳ�洫�����Ĳ�����װ��һ��model
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/8/25.
 */
public class MarginSpotInfoModel {

    private String accAmount;   //��֤����
    private String dealAmt;     //�����Ľ���λ�뱣֤����ͬ
    private String price;       //��ǰ�ļ۸�
    private String weCcy;       //���Ļ���
    private String anaCcy;      //��Ļ���
    private String direction;   //��ʱֻ����ģ���Ϊ�˷����Ժ�����



    public String getAccAmount() {
        return accAmount;
    }

    public void setAccAmount(String accAmount) {
        this.accAmount = accAmount;
    }

    public String getDealAmt() {
        return dealAmt;
    }

    public void setDealAmt(String dealAmt) {
        this.dealAmt = dealAmt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeCcy() {
        return weCcy;
    }

    public void setWeCcy(String weCcy) {
        this.weCcy = weCcy;
    }

    public String getAnaCcy() {
        return anaCcy;
    }

    public void setAnaCcy(String anaCcy) {
        this.anaCcy = anaCcy;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
