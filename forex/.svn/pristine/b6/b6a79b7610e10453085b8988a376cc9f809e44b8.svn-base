package com.talent.forex.modules.teach_mng.dataInput;
/**
 * GJSY130004
 * 导入学生学号取消定制化
 * 由excel导入的时候直接输入系和专业
 * 杜之晨
 * 2013.7.17
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;


import com.talent.auth.bean.domain.TClass;
import com.talent.auth.bean.domain.TeacherClass;
import com.talent.auth.bean.model.UserModel;

import com.talent.auth.dao.TClassDao;
import com.talent.auth.dao.TeachClassDao;
import com.talent.forex.common.Md5PwdEncoder;
import com.talent.hibernate.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;

import com.talent.auth.bean.domain.Users;
import com.talent.auth.dao.UsersDao;
import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.BTranFlowMapping;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.GroupMng;
import com.talent.forex.bean.domain.GroupSysParam;
import com.talent.forex.bean.domain.MarginEnlarge;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.bean.model.FileUploadModel;
import com.talent.forex.bean.model.StuInputResultModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.BTranFlowMappingDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.GroupMngDao;
import com.talent.forex.dao.GroupSysParamDao;
import com.talent.forex.dao.MarginEnlargeDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.PwdCryptUtil;
import com.talent.forex.util.ReadExcelFileUtil;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:数据批量导入
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: Talent Information Solutions Ltd.
 * </p>
 * 
 * @author zhenzhen
 * 
 */
public class DataInputBo extends BaseBo {
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(DataInputBo.class
			.getName());
	//教师-班级 2017/08/16
	private TeachClassDao teachClassDao;
	//学生-班级 2017/08/16
	//班级
	private TClassDao tClassDao;

	private UsersDao usersDao;
	private AccInfoDao accInfoDao;
	//private TranFlowMappingDao tranFlowMappingDao;
	private MarginEnlargeDao marginEnlargeDao;
	private GroupSysParamDao groupSysParamDao;
	private GroupMngDao groupMngDao;
	private BTranFlowMappingDao bTranFlowMappingDao;
	private CTranFlowMappingDao cTranFlowMappingDao;
	private WTranFlowMappingDao wTranFlowMappingDao;
	
	private static String[] C_CCYS={//人民币账户币种
			"CNY",
			"USD",
			"EUR",
			"AUD",
			"GBP",
			"JPY",
			"CAD"
	};
	
	private static String[] W_CCYS={//外币账户币种
			"USD",
			"EUR",
			"AUD",
			"GBP",
			"JPY",
			"CAD"
	};
	
	private static String[] B_CCYS={//保证金账户币种
			"USD",
			"EUR",
			"AUD",
			"GBP",
			"JPY",
			"CAD"
	};
	/**
	 * 读取文件
	 * 
	 */
	public String[][] getFile(FileUploadModel fileUploadModel){
		File[] fileArray = fileUploadModel.getFileArray();
        File f=fileArray[0];
        InputStream is;
        String[][] returnArray = null;
		try {
			is = new FileInputStream(f);
			returnArray= ReadExcelFileUtil.instance.readExcelFile(is);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return returnArray;
	}
	
	/**
	 * student 导入学生信息
	 * 
	 * @param fileUploadModel
	 * @param userModel
	 * @throws BoException
	 * @throws FileNotFoundException
	 */
	public StuInputResultModel genStudentFileDone(FileUploadModel fileUploadModel,
												  UserModel userModel) throws BoException,
			FileNotFoundException {
		String[][]returnArray=this.getFile(fileUploadModel);
		StuInputResultModel model=new StuInputResultModel();
		//保存数据库暂未存在的学号
		Hashtable stuTable=new Hashtable();
		//List stuListPage=new ArrayList();
		List list=new ArrayList();
		Hashtable stuEduTable=new Hashtable();
		Hashtable userPostTable=new Hashtable();
		Collection userList=new ArrayList();
		String pwdEncrypt=PwdCryptUtil.encrypt(SysParamNameConst.PWD);
		//查询教师身份 2017/08/16
		if(!SysParamNameConst.TEACHER.equals(userModel.getUserType())){
			StringBuilder result =new StringBuilder();
			result.append("权限不够，请联系管理员!");
			model.setResult(result.toString());
			return model;
		}
		//查询是否已选择班级
		if (fileUploadModel.getClassId()==null){
			StringBuilder result =new StringBuilder();
			result.append("请确认已选择再提交!");
			model.setResult(result.toString());
			return model;
		}
		//先查询是否为空
		if(SysParamNameConst.insertLeastStuNum>=returnArray.length){
			StringBuilder result =new StringBuilder();
			result.append("添加学生列表为空！");
			model.setResult(result.toString());
			return model;
		}
		//先查询是否超出范围
		if(SysParamNameConst.insertStuNum<returnArray.length){
			StringBuilder result =new StringBuilder();
			result.append("文件中的记录超过指定范围,").append("请重新审查再操作！");
			model.setResult(result.toString());
			return model;
		}
		try{
			HibernateUtil.beginTransaction();
			for(int i = 2;i<returnArray.length-1;i++){
				Users bean = null;
				Users bean2 = null;
				
				//查询一下是不是第一次导入
				String[] str = returnArray[i];
				String userNum=str[0];
				String userName=str[1];
				String groupOne=str[2];
				String groupTwo=str[3];
				
				//查询必填项是否为空
				if(null==userNum||("").equals(userNum)){
					StringBuilder result =new StringBuilder();
					result.append("文件中存在空学号的记录，请重新审查再操作！");
					model.setResult(result.toString());
					return model;
				}
				if(null==userName||("").equals(userName)){
					StringBuilder result =new StringBuilder();
					result.append("文件中存在姓名为空的记录，请重新审查再操作！");
					model.setResult(result.toString());
					return model;
				}
				if(null==groupOne||("").equals(groupOne)){
					StringBuilder result =new StringBuilder();
					result.append("文件中存在一级组为空的记录，请重新审查再操作！");
					model.setResult(result.toString());
					return model;
				}

				//查询groupOne是否存在班级里面
				TClass tClass=new TClass();
				tClass.setClassNo(groupOne);
				TClass tClass1=tClassDao.getBeanByBean(tClass,MatchMode.ANYWHERE);
				if (tClass1==null){
					StringBuilder result=new StringBuilder();
					result.append("文件中班级号暂不存在，请重新创建班级再操作");
					model.setResult(result.toString());
					return model;
				}
				
				//查询列表中是否存在该记录
				StringBuilder str2=new StringBuilder();
				str2.append(userNum).append(userName).append(groupOne);
				list.add(str2.toString());
				if(list.size()>1){
					Iterator it=list.iterator();
					while(it.hasNext()){
						String stu=(String)it.next();
						if(stu.equals(str2)){
							StringBuilder result =new StringBuilder();
							result.append("文件中存在相同记录,").append("请重新审查再操作！");
							model.setResult(result.toString());
							return model;
						}
					}
				}
				
				//先查询是否存在于Student表中
				ArrayList arr=new ArrayList();
				arr.add(userNum);
				bean2=usersDao.getBeanByParams("getUserInfo",arr);
				
				String flag=new StringBuilder().append(userNum).toString().trim();
				boolean flag1=true;
				char[] charUserNum=userNum.toCharArray();
				for(int f=0;f<charUserNum.length;f++){
					char ss=charUserNum[f];
					if(!((ss>='0'&&ss<='9')||(ss>='A'&&ss<='Z')||(ss>='a'&&ss<='z'))){
						flag1=false;
						break;
				}}
				
				if 	(userNum.length()>20){////GJSY130004 added
					model.setResult("学号长度与学校规定长度不一致,请重新审查再操作！");
					return model;
				} 
		
				if(!flag1){
					model.setResult("学号只能为英文或数字,请重新审查再操作！");
					return model;
				}
				
				//学生姓名只能是中英文
				char[] charUserName=userName.toCharArray();
				for(int f=0;f<charUserName.length;f++){
					char ss=charUserName[f];
					if(!(Character.isLetter(ss)||(ss>='A'&&ss<='Z')||(ss>='a'&&ss<='z'))){
						model.setResult("学生姓名格式错误,请重新审查再操作！");
						return model;
					}
				}
				bean=new Users();
				bean.setGroupOne(groupOne);
				bean.setGroupTwo(groupTwo);
				bean.setName(userName);
				bean.setPost("Q");
				bean.setStatus("1");
				bean.setUserNum(userNum);
				bean.setUserType("S");
				bean.setFalseTime(0);
				//设置默认密码888888
				Md5PwdEncoder md5PwdEncoder=new Md5PwdEncoder();
				bean.setPassword(md5PwdEncoder.encodePassword(SysParamNameConst.PWD));
				//标识是否有多条记录相同学号
				if(null==bean2&&((i==2)||(null==stuTable.get(flag)))){
					stuTable.put(flag,bean);
				}

				//假如学号相同，则更新该条数据
				if(null!=bean2){
					bean.setId(bean2.getId());
					usersDao.updataUserNum(bean);
				}

					/*if(null==bean2){
						userList.add(bean);
					}*/

			}
			boolean isbing=false;
			//绑定教师、班级实体 2017/08/16
			TeacherClass teacherClass=new TeacherClass();
			teacherClass.settId(userModel.getuId());
			teacherClass.setClassId(fileUploadModel.getClassId());
			//查询是否已经绑定 2017/08/16
			StringBuilder result =new StringBuilder();
			Collection collect=teachClassDao.getBeansByBean(teacherClass,MatchMode.EXACT);
			if (collect!=null&&!collect.isEmpty()){
				isbing=true;
				result.append("已绑定过该班级!");
			}
			//插入学生数据
			if(null!=stuTable&&stuTable.size()>0){
				usersDao.batchUpdateOrInsert(stuTable.values());
				insertInit(stuTable);
			}

			if (!isbing)
				teachClassDao.insertSingle(teacherClass);

			HibernateUtil.commitTransaction();
			logger.info("导入学生数据成功");
			model.setStuCol(userList);
			model.setResult(SysParamNameConst.DATA_INPUT_S);
		}catch (Exception e) {
			logger.info("数据导入失败，回滚数据");
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
			StringBuilder str=new StringBuilder();
			str.append("数据录入失败！");
			model.setResult(str.toString());
		}
		return model;
	}
	
	/**
	 * 初始化交易员信息
	 * @param stuTable
	 */
	public void insertInit(Hashtable stuTable){
		Enumeration e = stuTable.elements();
		String GroupOne = "";
		String GroupTwo = "";
		while( e.hasMoreElements() ){
			Users user = (Users)e.nextElement();
			GroupOne = user.getGroupOne();
			GroupTwo = user.getGroupTwo();
			//人民币账户数据初始化
			for(int i=0;i<C_CCYS.length;i++){
				AccInfo accInfo =new AccInfo();
				accInfo.setUserNum(user.getUserNum());
				accInfo.setAccno(user.getUserNum()+"100");
				accInfo.setAccType(SysParamNameConst.ACC_TYPE_C);
				accInfo.setCcy(C_CCYS[i]);
				if("CNY".equals(C_CCYS[i])){
					accInfo.setOriginalAmt(SysParamNameConst.ACC_TYPE_C_AMT);
					accInfo.setAmount(SysParamNameConst.ACC_TYPE_C_AMT);
				}else{
					accInfo.setOriginalAmt(SysParamNameConst.ACC_AMT);
					accInfo.setAmount(SysParamNameConst.ACC_AMT);
				}
				accInfo.setActiveDate(GetDateTimeUtil.getCurrentDateTimeToMinute());
				accInfo.setCreateDate(GetDateTimeUtil.getCurrentDateTimeToMinute());
				accInfoDao.makePersistent(accInfo, false);
			}
			//外币账户数据初始化
			for(int i=0;i<W_CCYS.length;i++){
				AccInfo accInfo =new AccInfo();
				accInfo.setUserNum(user.getUserNum());
				accInfo.setAccno(user.getUserNum()+"110");
				accInfo.setAccType(SysParamNameConst.ACC_TYPE_W);
				accInfo.setCcy(W_CCYS[i]);
				if("USD".equals(W_CCYS[i])){
					accInfo.setOriginalAmt(SysParamNameConst.ACC_TYPE_W_AMT);
					accInfo.setAmount(SysParamNameConst.ACC_TYPE_W_AMT);
				}else{
					accInfo.setOriginalAmt(SysParamNameConst.ACC_AMT);
					accInfo.setAmount(SysParamNameConst.ACC_AMT);
				}
				accInfo.setActiveDate(GetDateTimeUtil.getCurrentDateTimeToMinute());
				accInfo.setCreateDate(GetDateTimeUtil.getCurrentDateTimeToMinute());
				accInfoDao.makePersistent(accInfo, false);
			}
			//保证金账户数据初始化
			for(int i=0;i<B_CCYS.length;i++){
				AccInfo accInfo =new AccInfo();
				accInfo.setUserNum(user.getUserNum());
				accInfo.setAccno(user.getUserNum()+"120");
				accInfo.setAccType(SysParamNameConst.ACC_TYPE_B);
				accInfo.setCcy(B_CCYS[i]);
				if("USD".equals(B_CCYS[i])){
					accInfo.setOriginalAmt(SysParamNameConst.ACC_TYPE_B_AMT);
					accInfo.setAmount(SysParamNameConst.ACC_TYPE_B_AMT);
				}else{
					accInfo.setOriginalAmt(SysParamNameConst.ACC_AMT);
					accInfo.setAmount(SysParamNameConst.ACC_AMT);
				}
				accInfo.setActiveDate(GetDateTimeUtil.getCurrentDateTimeToMinute());
				accInfo.setCreateDate(GetDateTimeUtil.getCurrentDateTimeToMinute());
				accInfoDao.makePersistent(accInfo, false);
			}
			//账户交易统计表数据初始化
			//人民币
			CTranFlowMapping cTranFlowMapping = new CTranFlowMapping();
			cTranFlowMapping.setAmount((double) 0);
			cTranFlowMapping.setCount(0);
			cTranFlowMapping.setMarketBreakoutAmt("0");
			cTranFlowMapping.setMarketBreakoutQty("0");
			cTranFlowMapping.setOcoAmt("0");
			cTranFlowMapping.setOcoQty("0");
			cTranFlowMapping.setOneClickAmt("0");
			cTranFlowMapping.setOneClickQty("0");
			cTranFlowMapping.setOtcForwartAmt("0");
			cTranFlowMapping.setOtcForwartQty("0");
			cTranFlowMapping.setOtcSpotAmt("0");
			cTranFlowMapping.setOtcSpotQty("0");
			cTranFlowMapping.setOtcSwapAmt("0");
			cTranFlowMapping.setOtcSwapQty("0");
			cTranFlowMapping.setStopLossAmt("0");
			cTranFlowMapping.setStopLossQty("0");
			cTranFlowMapping.setTakeProfitAmt("0");
			cTranFlowMapping.setTakeProfitQty("0");
			cTranFlowMapping.setUserNum(user.getUserNum());
			cTranFlowMappingDao.makePersistent(cTranFlowMapping, false);
			//外币
			WTranFlowMapping wTranFlowMapping = new WTranFlowMapping();
			wTranFlowMapping.setAmount((double) 0);
			wTranFlowMapping.setCount(0);
			wTranFlowMapping.setMarketBreakoutAmt("0");
			wTranFlowMapping.setMarketBreakoutQty("0");
			wTranFlowMapping.setOcoAmt("0");
			wTranFlowMapping.setOcoQty("0");
			wTranFlowMapping.setOneClickAmt("0");
			wTranFlowMapping.setOneClickQty("0");
			wTranFlowMapping.setOtcForwartAmt("0");
			wTranFlowMapping.setOtcForwartQty("0");
			wTranFlowMapping.setOtcSpotAmt("0");
			wTranFlowMapping.setOtcSpotQty("0");
			wTranFlowMapping.setOtcSwapAmt("0");
			wTranFlowMapping.setOtcSwapQty("0");
			wTranFlowMapping.setStopLossAmt("0");
			wTranFlowMapping.setStopLossQty("0");
			wTranFlowMapping.setTakeProfitAmt("0");
			wTranFlowMapping.setTakeProfitQty("0");
			wTranFlowMapping.setUserNum(user.getUserNum());
			wTranFlowMappingDao.makePersistent(wTranFlowMapping, false);
			//保证金
			BTranFlowMapping bTranFlowMapping = new BTranFlowMapping();
			bTranFlowMapping.setAmount((double) 0);
			bTranFlowMapping.setCount(0);
			bTranFlowMapping.setMarginOptionAmt("0");
			bTranFlowMapping.setMarginOptionQty("0");
			bTranFlowMapping.setMarginSpotAmt("0");
			bTranFlowMapping.setMarginSpotQty("0");
			bTranFlowMapping.setUserNum(user.getUserNum());
			bTranFlowMappingDao.makePersistent(bTranFlowMapping, false);
		}
		if(!"".equals(GroupOne)&&null!=GroupOne){
			//保证金交易放大倍数
			MarginEnlarge marginEnlarge = new MarginEnlarge();
			marginEnlarge.setGroupId(GroupOne);
			marginEnlarge = marginEnlargeDao.getBeanByBean(marginEnlarge,MatchMode.ANYWHERE);
			//若没有保证金交易放大倍数，则插入默认数据
			if(null==marginEnlarge){
				marginEnlarge = new MarginEnlarge();
				marginEnlarge.setGroupId(GroupOne);
				marginEnlarge.setEnlarge(SysParamNameConst.MARGIN_ENLARGE);
				marginEnlargeDao.makePersistent(marginEnlarge, false);
			}
			
			//一级组别排名参数
			GroupSysParam groupSysParam = new GroupSysParam();
			groupSysParam.setGroupNum(GroupOne);
			groupSysParam = groupSysParamDao.getBeanByBean(groupSysParam, MatchMode.ANYWHERE);
			//若没有组别排名参数，则插入默认数据
			if(null==groupSysParam){
				groupSysParam = new GroupSysParam();
				groupSysParam.setGroupNum(GroupOne);
				groupSysParam.setWAmount(SysParamNameConst.W_AMOUNT);
				groupSysParam.setWQuantity(SysParamNameConst.W_QUANTIY);
				groupSysParam.setWRate(SysParamNameConst.W_RATE);
				groupSysParam.setAccRankNum(SysParamNameConst.ACC_RANK_NUM);
				groupSysParam.setWCnyAcc(SysParamNameConst.W_CNY_ACC);
				groupSysParam.setWForAcc(SysParamNameConst.W_FOR_ACC);
				groupSysParam.setWMarginAcc(SysParamNameConst.W_MARGIN_ACC);
				groupSysParam.setOverallRankNum(SysParamNameConst.OVERALL_RANK_NUM);
				groupSysParamDao.makePersistent(groupSysParam, false);
			}
			
			//一级组别管理
			GroupMng groupMng = new GroupMng();
			groupMng.setGroupId(GroupOne);
			groupMng = groupMngDao.getBeanByBean(groupMng, MatchMode.ANYWHERE);
			if(null==groupMng){
				groupMng = new GroupMng();
				groupMng.setGroupId(GroupOne);
				groupMng.setParentGroup("0");
				groupMng.setGroupType("O");
				groupMngDao.makePersistent(groupMng, false);
			}
			
			if(!"".equals(GroupTwo)&&null!=GroupTwo){
				//二级组别排名参数
				groupSysParam = new GroupSysParam();
				groupSysParam.setGroupNum(GroupTwo);
				groupSysParam = groupSysParamDao.getBeanByBean(groupSysParam, MatchMode.ANYWHERE);
				//若没有组别排名参数，则插入默认数据
				if(null==groupSysParam){
					groupSysParam = new GroupSysParam();
					groupSysParam.setGroupNum(GroupTwo);
					groupSysParam.setWAmount(SysParamNameConst.W_AMOUNT);
					groupSysParam.setWQuantity(SysParamNameConst.W_QUANTIY);
					groupSysParam.setWRate(SysParamNameConst.W_RATE);
					groupSysParam.setAccRankNum(SysParamNameConst.ACC_RANK_NUM);
					groupSysParam.setWCnyAcc(SysParamNameConst.W_CNY_ACC);
					groupSysParam.setWForAcc(SysParamNameConst.W_FOR_ACC);
					groupSysParam.setWMarginAcc(SysParamNameConst.W_MARGIN_ACC);
					groupSysParam.setOverallRankNum(SysParamNameConst.OVERALL_RANK_NUM);
					groupSysParamDao.makePersistent(groupSysParam, false);
				}
				
				//二级组别管理
				groupMng = new GroupMng();
				groupMng.setGroupId(GroupTwo);
				groupMng = groupMngDao.getBeanByBean(groupMng, MatchMode.ANYWHERE);
				if(null==groupMng){
					groupMng = new GroupMng();
					groupMng.setGroupId(GroupTwo);
					groupMng.setParentGroup(GroupOne);
					groupMng.setGroupType("T");
					groupMngDao.makePersistent(groupMng, false);
				}
			}
		}
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		DataInputBo.logger = logger;
	}

	public UsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public BTranFlowMappingDao getbTranFlowMappingDao() {
		return bTranFlowMappingDao;
	}

	public void setbTranFlowMappingDao(BTranFlowMappingDao bTranFlowMappingDao) {
		this.bTranFlowMappingDao = bTranFlowMappingDao;
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

	public MarginEnlargeDao getMarginEnlargeDao() {
		return marginEnlargeDao;
	}

	public void setMarginEnlargeDao(MarginEnlargeDao marginEnlargeDao) {
		this.marginEnlargeDao = marginEnlargeDao;
	}

	public GroupSysParamDao getGroupSysParamDao() {
		return groupSysParamDao;
	}

	public void setGroupSysParamDao(GroupSysParamDao groupSysParamDao) {
		this.groupSysParamDao = groupSysParamDao;
	}

	public GroupMngDao getGroupMngDao() {
		return groupMngDao;
	}

	public void setGroupMngDao(GroupMngDao groupMngDao) {
		this.groupMngDao = groupMngDao;
	}

	public AccInfoDao getAccInfoDao() {
		return accInfoDao;
	}

	public void setAccInfoDao(AccInfoDao accInfoDao) {
		this.accInfoDao = accInfoDao;
	}


	public TeachClassDao getTeachClassDao() {
		return teachClassDao;
	}

	public void setTeachClassDao(TeachClassDao teachClassDao) {
		this.teachClassDao = teachClassDao;
	}


	public TClassDao gettClassDao() {
		return tClassDao;
	}

	public void settClassDao(TClassDao tClassDao) {
		this.tClassDao = tClassDao;
	}
}
