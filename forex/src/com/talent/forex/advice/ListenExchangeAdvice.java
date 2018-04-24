package com.talent.forex.advice;

import com.talent.exam.config.ExamConfig;
import com.talent.exam.constant.ExamConst;
import com.talent.exam.domain.*;
import com.talent.exam.modules.stu_exam.service.StuExamBo;
import com.talent.exam.modules.teach_mng.service.ExamBo;
import com.talent.forex.bean.model.ForwardTradeModel;
import com.talent.forex.bean.model.OcoAndMarketModel;
import com.talent.forex.bean.model.StopAndProfitModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/9/25.
 *
 */
public class ListenExchangeAdvice implements MethodInterceptor {

    private final static Logger logger=Logger.getLogger(ListenExchangeAdvice.class);

    private StuExamBo stuExamBo;

    //1.判断是否在考试状态，如果是，则监听，否则，则不监听
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        logger.info("invoke the method "+methodInvocation.getMethod().getName());
        Object result=methodInvocation.proceed();
        if (ExamConfig.getExamState()){//是否在考试状态
            dispatcherListen(methodInvocation);
        }
        return result;
    }


    /**
     * 分发监听
     */
    private void dispatcherListen(MethodInvocation methodInvocation){
        String methodName=methodInvocation.getMethod().getName();
        logger.info("it add listen"+methodName);
        if ("otcForwardTradeAdd".equals(methodName)){
            processOtcForwardTrade(methodInvocation);
        }else if ("stopLossInfoAdd".equals(methodName)){
            processStopAndProfitModel(methodInvocation);
        }else if ("takeProfitInfoAdd".equals(methodName)){
            processTakeProfit(methodInvocation);
        }else if ("ocoInfoAdd".equals(methodName)){
            processOco(methodInvocation);
        }else if ("marketBreakoutInfoAdd".equals(methodName)){
            processMK(methodInvocation);
        }else if ("oneClickInfoAdd".equals(methodName)){
            processOneClick(methodInvocation);
        }else if ("otcSpotTradeAdd".equals(methodName)){
            processSpot(methodInvocation);
        }else if("otcSwapTradeAdd".equals(methodName)){
            processSwap(methodInvocation);
        }
    }

    //swap
    private void processSwap(MethodInvocation methodInvocation){
        ExamSwap examSwap=new ExamSwap();
        Object[] arguments=methodInvocation.getArguments();
        if (arguments!=null && arguments.length==16){
            String startTime= (String) arguments[1];
            String endTime= (String) arguments[2];
            String direct= (String) arguments[3];
            String ccy1= (String) arguments[4];
            String ccy2= (String) arguments[5];
            String provider= (String) arguments[7];
            String amount= (String) arguments[8];
            String cBasis= (String) arguments[9];
            String fBasis= (String) arguments[10];
            String fixedType= (String) arguments[11];
            String fixedRate= (String) arguments[12];
            String libor= (String) arguments[13];
            String frequency= (String) arguments[14];
            String point= (String) arguments[15];
            examSwap.setDirection(direct);  //买卖方向
            //交易类型
            if ("CNY".equals(ccy1) || "CNY".equals(ccy2))
                examSwap.setAccTypeNo("C");
            else
                examSwap.setAccTypeNo("W");
            if ("0".equals(direct)) //买卖币种
                examSwap.setAcc(ccy1);
            else
                examSwap.setAcc(ccy2);
            examSwap.setAccAmount(FormatParamUtil.getAmountAndPriceFmt(amount));    //买卖数量
            //examSwap.setSpot();  //spot
            examSwap.setStartDate(GetDateTimeUtil.dateTransFmt2(startTime));//start time
            examSwap.setMaturityDate(GetDateTimeUtil.dateTransFmt2(endTime));//end time
            examSwap.setFixedType(fixedType);//fixed type
            examSwap.setFixedRate(Integer.valueOf(fixedRate));//fixed Rate
            examSwap.setcBasis(Integer.valueOf(cBasis));//CBasis
            examSwap.setfBasis(Integer.valueOf(fBasis));//fBasis
            examSwap.setLibor(Integer.valueOf(libor));  //libor
            examSwap.setFrequency(Integer.valueOf(frequency));
            examSwap.setProviderNo(Integer.valueOf(provider));
            examSwap.setPoint(Integer.valueOf(point));
            examSwap.setSpot("N");  //冗余字段
            stuExamBo.listenSwapExchange(examSwap);
        }
    }

    //spot
    private void processSpot(MethodInvocation methodInvocation){
        ExamSpot examSpot=new ExamSpot();
        Object[] arguments=methodInvocation.getArguments();
        if (arguments!=null && arguments.length==7){
            //买卖方向
            String direct= (String) arguments[5];
            String ccy1= (String) arguments[2];
            String ccy2= (String) arguments[3];
            String price= (String) arguments[4];
            String provider= (String) arguments[1];
            examSpot.setDirection(direct);
            //买卖币种
            if ("0".equals(direct))
                examSpot.setAcc(ccy1);
            else
                examSpot.setAcc(ccy2);
            //交易类型
            if ("CNY".equals(ccy1) || "CNY".equals(ccy2))
                examSpot.setAccTypeNo("C");
            else
                examSpot.setAccTypeNo("W");
            examSpot.setPrice(price);
            examSpot.setProviderNo(Integer.valueOf(provider));
            stuExamBo.listenSpotExchange(examSpot);
        }
    }

    //one click
    private void processOneClick(MethodInvocation methodInvocation){
        ExamOneClick examOneClick=new ExamOneClick();
        Object[] arguments=methodInvocation.getArguments();
        if (arguments!=null && arguments.length==5){
            String direction= (String) arguments[0];
            String ccy1= (String) arguments[1];
            String ccy2= (String) arguments[2];
            String amount= (String) arguments[3];
            examOneClick.setDirection(direction);   //买卖方向
            if ("0".equals(direction))  //买卖货币
                examOneClick.setAcc(ccy1);
            else
                examOneClick.setAcc(ccy2);
            //买卖类型
            if ("CNY".equals(ccy2))
                examOneClick.setAccTypeNo("C");
            else
                examOneClick.setAccTypeNo("W");
            examOneClick.setAccAmount(FormatParamUtil.getAmountAndPriceFmt(amount));   //买卖数量
            stuExamBo.listenOneClickExhange(examOneClick);
        }
    }

    //market breakout
    private void processMK(MethodInvocation methodInvocation){
        ExamMarketBreakout examMarketBreakout=new ExamMarketBreakout();
        Object[] arguments=methodInvocation.getArguments();
        if (arguments!=null && arguments[0] instanceof OcoAndMarketModel){
            OcoAndMarketModel marketbreakout= (OcoAndMarketModel) arguments[0];
            if (marketbreakout.getActiveTime().equals(""))  //Good From
                examMarketBreakout.setGoodFrom(GetDateTimeUtil.getCurrentDate());
            else
                examMarketBreakout.setGoodFrom(GetDateTimeUtil.dateTransFmt2(marketbreakout.getActiveTime()));
            if (marketbreakout.getCancelTime().equals(""))  //Good Till
                examMarketBreakout.setGoodTill(SysParamNameConst.UNLIMITED_TIME);
            else
                examMarketBreakout.setGoodTill(GetDateTimeUtil.dateTransFmt2(marketbreakout.getCancelTime()));
            //买卖方向
            examMarketBreakout.setDirection(marketbreakout.getTradeDirection());
            //买卖币种
            if ("0".equals(marketbreakout.getTradeDirection()))
                examMarketBreakout.setAcc(marketbreakout.getCcy1());
            else
                examMarketBreakout.setAcc(marketbreakout.getCcy2());
            //stop loss 1
            examMarketBreakout.setStopLossAmount1(FormatParamUtil.getAmountAndPriceFmt(marketbreakout.gettAmount()));
            examMarketBreakout.setStopLossPrice1(FormatParamUtil.getAmountAndPriceFmt(marketbreakout.gettPrice()));
            //stop loss 2
            examMarketBreakout.setStopLossAmount2(FormatParamUtil.getAmountAndPriceFmt(marketbreakout.getsAmount()));
            examMarketBreakout.setStopLossPrice2(FormatParamUtil.getAmountAndPriceFmt(marketbreakout.getsPrice()));
            //交易类型
            if (marketbreakout.getCcy2().equals("CNY"))
                examMarketBreakout.setAccTypeNo("C");
            else
                examMarketBreakout.setAccTypeNo("W");
            stuExamBo.listenMKExchange(examMarketBreakout);
        }
    }

    //oco
    private void processOco(MethodInvocation methodInvocation){
        ExamOco examOco=new ExamOco();
        Object[] arguments=methodInvocation.getArguments();
        if (arguments!=null && arguments[0] instanceof OcoAndMarketModel){
            OcoAndMarketModel oco= (OcoAndMarketModel) arguments[0];
            if ("".equals(oco.getActiveTime()))     //Good From
                examOco.setGoodFrom(GetDateTimeUtil.getCurrentDate());
            else
                examOco.setGoodFrom(GetDateTimeUtil.dateTransFmt2(oco.getActiveTime()));
            if ("".equals(oco.getCancelTime()))     //Good Till
                examOco.setGoodTill(SysParamNameConst.UNLIMITED_TIME);
            else
                examOco.setGoodTill(GetDateTimeUtil.dateTransFmt2(oco.getCancelTime()));
            examOco.setDirection(oco.getTradeDirection());//买卖方向
            if ("0".equals(oco.getTradeDirection()))    //买卖币种
                examOco.setAcc(oco.getCcy1());
            else
                examOco.setAcc(oco.getCcy2());
            //交易类型
            if ("CNY".equals(oco.getCcy2()))
                examOco.setAccTypeNo("C");
            else
                examOco.setAccTypeNo("W");
            //stop loss
            examOco.setStopLossPrice(FormatParamUtil.getAmountAndPriceFmt(oco.getsPrice()));
            examOco.setStopLossAmount(FormatParamUtil.getAmountAndPriceFmt(oco.getsAmount()));
            //take profit
            examOco.setTakeProfitPrice(FormatParamUtil.getAmountAndPriceFmt(oco.gettPrice()));
            examOco.setTakeProfitAmount(FormatParamUtil.getAmountAndPriceFmt(oco.gettAmount()));
            examOco.setMonitorPrice(oco.getMonitorPrice());
            stuExamBo.listenOcoExchange(examOco);
        }
    }

    //take profit
    private void processTakeProfit(MethodInvocation methodInvocation){
        ExamTakeProfit examTakeProfit=new ExamTakeProfit();
        Object[] arguments=methodInvocation.getArguments();
        if (arguments!=null && arguments[0] instanceof StopAndProfitModel){
            StopAndProfitModel takeprofit= (StopAndProfitModel) arguments[0];
            examTakeProfit.setAccAmount(FormatParamUtil.getAmountAndPriceFmt(takeprofit.getPrice()));
            if ("".equals(takeprofit.getActiveTime()))
                examTakeProfit.setGoodFrom(GetDateTimeUtil.getCurrentDate());
            else
                examTakeProfit.setGoodFrom(GetDateTimeUtil.dateTransFmt2(takeprofit.getActiveTime())); //Good From
            if ("".equals(takeprofit.getCancelTime()))
                examTakeProfit.setGoodTill(SysParamNameConst.UNLIMITED_TIME);
            else
                examTakeProfit.setGoodTill(GetDateTimeUtil.dateTransFmt2(takeprofit.getCancelTime())); //Good Till
            examTakeProfit.setDirection(takeprofit.getTradeDirection());    //买卖方向
            if ("0".equals(examTakeProfit.getDirection())){     //买卖货币
                examTakeProfit.setAcc(takeprofit.getCcy1());
            }else {
                examTakeProfit.setAcc(takeprofit.getCcy2());
            }
            //交易类型
            if ("CNY".equals(takeprofit.getCcy1()) || "CNY".equals(takeprofit.getCcy2())){
                examTakeProfit.setAccTypeNo("C");
            }else {
                examTakeProfit.setAccTypeNo("W");
            }
            examTakeProfit.setPrice(FormatParamUtil.getAmountAndPriceFmt(takeprofit.getPrice()));//交易价格
            stuExamBo.listenTakeProfitExchange(examTakeProfit);
        }
    }

    //forward
    private void processOtcForwardTrade(MethodInvocation methodInvocation){
        Object[] arguments=methodInvocation.getArguments();
        ExamForward examForward=new ExamForward();
        //对手方
        String provider= (String) arguments[1];
        examForward.setProviderNo(Integer.valueOf(provider));
        String ccy1= (String) arguments[2];
        String ccy2= (String) arguments[3];
        String direct= (String) arguments[5];   examForward.setDirection(direct);   //买卖方向
        if (direct.equals("0")) {       //买卖币种
            examForward.setAcc(ccy1);
        }
        else {
            examForward.setAcc(ccy2);
        }
        //交易类型
        if ("CNY".equals(ccy1) || "CNY".equals(ccy2)){
            examForward.setAccTypeNo("C");//人民币交易
        }else {
            examForward.setAccTypeNo("W");//外币对交易
        }
        String oldPrice= (String) arguments[4];
        String point= (String) arguments[8];
        Double price = Double.parseDouble(oldPrice) + Double.parseDouble(point) / 10000;//待计算的汇率
        //交易价格
        examForward.setPrice(String.valueOf(price));
        String amount= (String) arguments[6];
        examForward.setAccAmount(FormatParamUtil.getAmountAndPriceFmt(amount)); //交易数量
        String valueDate= (String) arguments[7];
        examForward.setValueDate(GetDateTimeUtil.dateTransFmt2(valueDate)); //交割日期
        stuExamBo.listtenForwardExchange(examForward);
    }

    //stop loss
    private void processStopAndProfitModel(MethodInvocation methodInvocation) {
        Object[] arguments=methodInvocation.getArguments();
        if (arguments!=null && arguments[0]!=null && arguments[0] instanceof StopAndProfitModel){
            StopAndProfitModel stopAndProfitModel= (StopAndProfitModel) arguments[0];
            stuExamBo.listenStopLossExchange(stopAndProfitModel);
        }
    }

    public StuExamBo getStuExamBo() {
        return stuExamBo;
    }

    public void setStuExamBo(StuExamBo stuExamBo) {
        this.stuExamBo = stuExamBo;
    }
}
