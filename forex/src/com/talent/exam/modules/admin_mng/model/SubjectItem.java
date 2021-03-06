package com.talent.exam.modules.admin_mng.model;

import com.talent.exam.domain.ExamContent;
import com.talent.exam.model.*;

/**
 * 考试题目
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/9/11.
 */
public class SubjectItem {

    /*题目内容
     */
    private String examContent;

    /*交易类型*/
    private String accTypeNo;

    /*试卷编号*/
    private Integer examNo; 

    /*步骤类型*/
    private String type;

    /*通用*/
    private String Direction;  //买卖方向
    private String Acc;  //买卖币种

    /*stop loss*/
    private String stopLossAccMount; //stop loss买卖数量
    private String stopLossPrice; //stop loss价格
    private String stopLossAccmonitorPrice;  //模拟类型
    private String stopLossGoodFrom; //stop loss Good From
    private String stopLossGoodTill; //stop loss Good Till
    private StopLossScore stopLossScore; //stop loss score;

    /*take profit*/
    private String takeProfitAccMount; //take profit买卖数量
    private String takeProfitPrice; //take profit价格
    private String takeProfitGoodFrom; //take profit Good From
    private String takeProfitGoodTill; //take profit Good Till
    private TakeProfitScore takeProfitScore; //take profit score

    /*oco*/
    private String ocoStopLossAccMount; //oco stop loss买卖数量
    private String ocoStopLossPrice; //oco stop loss价格
    private String ocoStopLossAccmonitorPrice;  //模拟类型
    private String ocoTakeProfitAccMount; //oco take profit买卖数量
    private String ocoTakeProfitPrice; //oco take profit价格
    private String ocoGoodFrom; //oco Good From
    private String ocoGoodTill; //oco Good Till
    private OcoScore ocoScore; //oco score

    /*market breakout*/
    private String mbktStopLossAccMount1; //market breakout stop loss买卖数量
    private String mbktStopLossPrice1; //market breakout stop loss价格
    private String mbktStopLossAccMount2; //market breakout stop loss买卖数量
    private String mbktStopLossPrice2; //market breakout stop loss价格
    private String mbkStopLossAccmonitorPrice;  //模拟类型
    private String mbkGoodFrom; //market breakout Good From
    private String mbkGoodTill; //market breakout Good Till
    private MarketBreakoutScore marketBreakoutScore; //market breakout score

    /*one click*/
    private String oneClickAccMount; //one click买卖数量
    private OneClickScore oneClickScore; //one click score

    /*spot*/
    private String spotAccMount; //买卖数量
    private String spotPrice; //spot 交易基点
    private Integer spotProviderNo; //spot 对手方
    private SpotScore spotScore; //spot score

    /*forward*/
    private String forwardAccMount; //买卖数量
    private String forwardPrice; //spot 交易基点
    private String forwardValueDate;
    private Integer forwardProviderNo; //spot 对手方
    private ForwardScore forwardScore; //forward score

    /* swap */
	private String swapAccAmount;//swap买卖数量
	private String swapSpot;//是否为即期对远期掉期询价交易
	private String swapStartDate;//开始交易日期
	private String swapMaturityDate;//到期交易日期
	private String swapFixedType;//固定利率类型（receive或pay）
	private Integer swapFixedRate;//固定利率
	private Integer swapCbasis;//近端掉期点
	private Integer swapFbasis;//远端掉期点
	private Integer swapFrequency;//近端付息类型
	private Integer swapLibor;//利率类型
	private Integer swapProviderNo;//swap 对手方
	private Integer swapPoint;//基点
	private SwapScore swapScore;// swap score

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    public String getAcc() {
        return Acc;
    }

    public void setAcc(String acc) {
        Acc = acc;
    }

    public String getStopLossAccMount() {
        return stopLossAccMount;
    }

    public void setStopLossAccMount(String stopLossAccMount) {
        this.stopLossAccMount = stopLossAccMount;
    }

    public String getStopLossPrice() {
        return stopLossPrice;
    }

    public void setStopLossPrice(String stopLossPrice) {
        this.stopLossPrice = stopLossPrice;
    }

    public String getStopLossAccmonitorPrice() {
        return stopLossAccmonitorPrice;
    }

    public void setStopLossAccmonitorPrice(String stopLossAccmonitorPrice) {
        this.stopLossAccmonitorPrice = stopLossAccmonitorPrice;
    }

    public String getStopLossGoodFrom() {
        return stopLossGoodFrom;
    }

    public void setStopLossGoodFrom(String stopLossGoodFrom) {
        this.stopLossGoodFrom = stopLossGoodFrom;
    }

    public String getStopLossGoodTill() {
        return stopLossGoodTill;
    }

    public void setStopLossGoodTill(String stopLossGoodTill) {
        this.stopLossGoodTill = stopLossGoodTill;
    }

    public String getTakeProfitAccMount() {
        return takeProfitAccMount;
    }

    public void setTakeProfitAccMount(String takeProfitAccMount) {
        this.takeProfitAccMount = takeProfitAccMount;
    }

    public String getTakeProfitPrice() {
        return takeProfitPrice;
    }

    public void setTakeProfitPrice(String takeProfitPrice) {
        this.takeProfitPrice = takeProfitPrice;
    }

    public String getTakeProfitGoodFrom() {
        return takeProfitGoodFrom;
    }

    public void setTakeProfitGoodFrom(String takeProfitGoodFrom) {
        this.takeProfitGoodFrom = takeProfitGoodFrom;
    }

    public String getTakeProfitGoodTill() {
        return takeProfitGoodTill;
    }

    public void setTakeProfitGoodTill(String takeProfitGoodTill) {
        this.takeProfitGoodTill = takeProfitGoodTill;
    }

    public String getOcoStopLossAccMount() {
        return ocoStopLossAccMount;
    }

    public void setOcoStopLossAccMount(String ocoStopLossAccMount) {
        this.ocoStopLossAccMount = ocoStopLossAccMount;
    }

    public String getOcoStopLossPrice() {
        return ocoStopLossPrice;
    }

    public void setOcoStopLossPrice(String ocoStopLossPrice) {
        this.ocoStopLossPrice = ocoStopLossPrice;
    }

    public String getOcoStopLossAccmonitorPrice() {
        return ocoStopLossAccmonitorPrice;
    }

    public void setOcoStopLossAccmonitorPrice(String ocoStopLossAccmonitorPrice) {
        this.ocoStopLossAccmonitorPrice = ocoStopLossAccmonitorPrice;
    }

    public String getOcoTakeProfitAccMount() {
        return ocoTakeProfitAccMount;
    }

    public void setOcoTakeProfitAccMount(String ocoTakeProfitAccMount) {
        this.ocoTakeProfitAccMount = ocoTakeProfitAccMount;
    }

    public String getOcoTakeProfitPrice() {
        return ocoTakeProfitPrice;
    }

    public void setOcoTakeProfitPrice(String ocoTakeProfitPrice) {
        this.ocoTakeProfitPrice = ocoTakeProfitPrice;
    }

    public String getOcoGoodFrom() {
        return ocoGoodFrom;
    }

    public void setOcoGoodFrom(String ocoGoodFrom) {
        this.ocoGoodFrom = ocoGoodFrom;
    }

    public String getMbktStopLossAccMount1() {
        return mbktStopLossAccMount1;
    }

    public void setMbktStopLossAccMount1(String mbktStopLossAccMount1) {
        this.mbktStopLossAccMount1 = mbktStopLossAccMount1;
    }

    public String getMbktStopLossPrice1() {
        return mbktStopLossPrice1;
    }

    public void setMbktStopLossPrice1(String mbktStopLossPrice1) {
        this.mbktStopLossPrice1 = mbktStopLossPrice1;
    }

    public String getMbktStopLossAccMount2() {
        return mbktStopLossAccMount2;
    }

    public void setMbktStopLossAccMount2(String mbktStopLossAccMount2) {
        this.mbktStopLossAccMount2 = mbktStopLossAccMount2;
    }

    public String getMbktStopLossPrice2() {
        return mbktStopLossPrice2;
    }

    public void setMbktStopLossPrice2(String mbktStopLossPrice2) {
        this.mbktStopLossPrice2 = mbktStopLossPrice2;
    }

    public String getMbkStopLossAccmonitorPrice() {
        return mbkStopLossAccmonitorPrice;
    }

    public void setMbkStopLossAccmonitorPrice(String mbkStopLossAccmonitorPrice) {
        this.mbkStopLossAccmonitorPrice = mbkStopLossAccmonitorPrice;
    }

    public String getMbkGoodFrom() {
        return mbkGoodFrom;
    }

    public void setMbkGoodFrom(String mbkGoodFrom) {
        this.mbkGoodFrom = mbkGoodFrom;
    }

    public String getMbkGoodTill() {
        return mbkGoodTill;
    }

    public void setMbkGoodTill(String mbkGoodTill) {
        this.mbkGoodTill = mbkGoodTill;
    }

    public String getOneClickAccMount() {
        return oneClickAccMount;
    }

    public void setOneClickAccMount(String oneClickAccMount) {
        this.oneClickAccMount = oneClickAccMount;
    }

    public String getOcoGoodTill() {
        return ocoGoodTill;
    }

    public void setOcoGoodTill(String ocoGoodTill) {
        this.ocoGoodTill = ocoGoodTill;
    }

    public String getSpotAccMount() {
        return spotAccMount;
    }

    public void setSpotAccMount(String spotAccMount) {
        this.spotAccMount = spotAccMount;
    }

    public String getSpotPrice() {
        return spotPrice;
    }

    public void setSpotPrice(String spotPrice) {
        this.spotPrice = spotPrice;
    }

    public Integer getSpotProviderNo() {
        return spotProviderNo;
    }

    public void setSpotProviderNo(Integer spotProviderNo) {
        this.spotProviderNo = spotProviderNo;
    }

    public String getForwardAccMount() {
        return forwardAccMount;
    }

    public void setForwardAccMount(String forwardAccMount) {
        this.forwardAccMount = forwardAccMount;
    }

    public String getForwardPrice() {
        return forwardPrice;
    }

    public void setForwardPrice(String forwardPrice) {
        this.forwardPrice = forwardPrice;
    }

    public String getForwardValueDate() {
        return forwardValueDate;
    }

    public void setForwardValueDate(String forwardValueDate) {
        this.forwardValueDate = forwardValueDate;
    }

    public Integer getForwardProviderNo() {
        return forwardProviderNo;
    }

    public void setForwardProviderNo(Integer forwardProviderNo) {
        this.forwardProviderNo = forwardProviderNo;
    }

    public Integer getExamNo() {
        return examNo;
    }

    public void setExamNo(Integer examNo) {
        this.examNo = examNo;
    }

    public StopLossScore getStopLossScore() {
        return stopLossScore;
    }

    public void setStopLossScore(StopLossScore stopLossScore) {
        this.stopLossScore = stopLossScore;
    }

    public TakeProfitScore getTakeProfitScore() {
        return takeProfitScore;
    }

    public void setTakeProfitScore(TakeProfitScore takeProfitScore) {
        this.takeProfitScore = takeProfitScore;
    }

    public OcoScore getOcoScore() {
        return ocoScore;
    }

    public void setOcoScore(OcoScore ocoScore) {
        this.ocoScore = ocoScore;
    }

    public MarketBreakoutScore getMarketBreakoutScore() {
        return marketBreakoutScore;
    }

    public void setMarketBreakoutScore(MarketBreakoutScore marketBreakoutScore) {
        this.marketBreakoutScore = marketBreakoutScore;
    }

    public OneClickScore getOneClickScore() {
        return oneClickScore;
    }

    public void setOneClickScore(OneClickScore oneClickScore) {
        this.oneClickScore = oneClickScore;
    }

    public SpotScore getSpotScore() {
        return spotScore;
    }

    public void setSpotScore(SpotScore spotScore) {
        this.spotScore = spotScore;
    }

    public ForwardScore getForwardScore() {
        return forwardScore;
    }

    public void setForwardScore(ForwardScore forwardScore) {
        this.forwardScore = forwardScore;
    }

    public String getAccTypeNo() {
        return accTypeNo;
    }

    public void setAccTypeNo(String accTypeNo) {
        this.accTypeNo = accTypeNo;
    }

	public String getSwapAccAmount() {
		return swapAccAmount;
	}

	public void setSwapAccAmount(String swapAccAmount) {
		this.swapAccAmount = swapAccAmount;
	}

	public String getSwapSpot() {
		return swapSpot;
	}

	public void setSwapSpot(String swapSpot) {
		this.swapSpot = swapSpot;
	}

	public String getSwapStartDate() {
		return swapStartDate;
	}

	public void setSwapStartDate(String swapStartDate) {
		this.swapStartDate = swapStartDate;
	}

	public String getSwapMaturityDate() {
		return swapMaturityDate;
	}

	public void setSwapMaturityDate(String swapMaturityDate) {
		this.swapMaturityDate = swapMaturityDate;
	}

	public String getSwapFixedType() {
		return swapFixedType;
	}

	public void setSwapFixedType(String swapFixedType) {
		this.swapFixedType = swapFixedType;
	}

	public Integer getSwapFixedRate() {
		return swapFixedRate;
	}

	public void setSwapFixedRate(Integer swapFixedRate) {
		this.swapFixedRate = swapFixedRate;
	}

	public Integer getSwapCbasis() {
		return swapCbasis;
	}

	public void setSwapCbasis(Integer swapCbasis) {
		this.swapCbasis = swapCbasis;
	}

	public Integer getSwapFbasis() {
		return swapFbasis;
	}

	public void setSwapFbasis(Integer swapFbasis) {
		this.swapFbasis = swapFbasis;
	}

	public Integer getSwapFrequency() {
		return swapFrequency;
	}

	public void setSwapFrequency(Integer swapFrequency) {
		this.swapFrequency = swapFrequency;
	}

	public Integer getSwapLibor() {
		return swapLibor;
	}

	public void setSwapLibor(Integer swapLibor) {
		this.swapLibor = swapLibor;
	}

	public Integer getSwapProviderNo() {
		return swapProviderNo;
	}

	public void setSwapProviderNo(Integer swapProviderNo) {
		this.swapProviderNo = swapProviderNo;
	}

	public Integer getSwapPoint() {
		return swapPoint;
	}

	public void setSwapPoint(Integer swapPoint) {
		this.swapPoint = swapPoint;
	}

	public SwapScore getSwapScore() {
		return swapScore;
	}

	public void setSwapScore(SwapScore swapScore) {
		this.swapScore = swapScore;
	}

    public String getExamContent() {
        return examContent;
    }

    public void setExamContent(String examContent) {
        this.examContent = examContent;
    }
}
