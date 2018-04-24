package com.talent.forex.util;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.talent.forex.bean.domain.SysParam;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.dao.SysParamDao;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.hibernate.util.TransactionNestUtil;

public class SysParamUtil {
	private static Logger logger = Logger.getLogger(SysParamUtil.class
			.getName());
	private static SysParamUtil instance = new SysParamUtil();

	private static Hashtable table = null;

	public Hashtable getTable() {
		return table;
	}

	public void setTable(Hashtable table) {
		SysParamUtil.table = table;
	}

	public SysParamUtil() {

	}

	public static SysParamUtil getInstance() {
		if (table == null) {
			instance.genSysParam();
		}
		return instance;
	}

	public static void refresh() {
		table = null;
		getInstance();
	}

	public SysParam getSysParamByName(String paramName) {

		SysParam bean = null;
		if (paramName != null) {
			bean = (SysParam) table.get(paramName);
		}
		return bean;
	}

	/**
	 * 返回参数值
	 * 
	 * @param paramName
	 * @return
	 */
	public static String getCharValueByName(String paramName) {

		SysParam bean = null;
		if (table == null) {
			getInstance();
		}
		if (paramName != null) {
			bean = (SysParam) table.get(paramName);
		}
		if (bean == null) {
			return null;
		} else {
			return bean.getCharVal();
		}
	}

	private void genSysParam() {
		String sfName = null;
		String ref = null;
		try {
			SysParamDao dao = new SysParamDao();
			ref = TransactionNestUtil.reference();

			Collection collection = dao.getAll();
			TransactionNestUtil.releaseRef(ref);

			if (collection != null) {
				synchronized (instance) {
					if (table == null) {
						table = new Hashtable();
						Iterator it = collection.iterator();
						while (it.hasNext()) {
							SysParam bean = (SysParam) it.next();
							if (bean != null) {
								table.put(bean.getParamName(), bean);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			TransactionNestUtil.releaseRef(ref);
		} finally {

			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();
			}
		}
	}

	private Collection getSysParamList(String paraName) {
		Collection collection = null;
		if (paraName != null) {
			Hashtable valTable = (Hashtable) table.get(paraName);
			if (valTable != null) {
				collection = valTable.values();
			}
		}
		return collection;
	}

	public Collection getSysParamList() {
		return table.values();
	}
	/**
	 * 强制退出后计算考勤时间的默认时间
	 * @return
	 */
	public static String getLogTime() {
		return getCharValueByName(SysParamNameConst.LOG_TIME);
	}

	/**
	 * 获取 计时时间点
	 * 
	 * @return
	 */
	public static String getBatchTime() {
		return getCharValueByName(SysParamNameConst.BATCH_TIME);
	}

	/**
	 * 获取 线程统计时间点
	 * 
	 * @return
	 */
//	public String getSumTime() {
//		return getCharValueByName(SysParamNameConst.SUM_TIME);
//	}
	/**
	 * 是否允许 修改实例信息
	 * @return
	 */
	public String getModSidPool() {
		return getCharValueByName(SysParamNameConst.MODIFY_SIDPOOL);
	}

//	public Double getGroupcnt() {
//		return getNumValueByName(SysParamNameConst.PRATICE_GROUP_CNT);
//	}
	/**
	 * 是否允许新增实例
	 * @return
	 */
	public String getAddSid() {
		return getCharValueByName(SysParamNameConst.ADD_SIDPOOL);
	}
	/**
	 * 文章的版本
	 * @return
	 */
//	public Double getArticleVersion() {
//		return getNumValueByName(SysParamNameConst.ARTICLE_VERSION);
//	}
	
	/**
	 * 系统的版本号
	 */
	public String getVersion(){
		return getCharValueByName(SysParamNameConst.EMS_VERSION);
	}
	/**
	 * 首页 显示页面 的通知 文章等连接的数量
	 * @return
	 */
//	public Double getArticleNum(String paramName) {
//		return getNumValueByName(paramName);
//	}
	/**
	 * 跨行业务系统url
	 * @return
	 */
	public static String getEfesUrl() {
		return getCharValueByName(SysParamNameConst.EFES_URL);
	}
	/**
	 * 考核系统url
	 * @return
	 */
	public static String getEesUrl() {
		return getCharValueByName(SysParamNameConst.EES_URL);
	}
	/**
	 * 知识库 url
	 * @return
	 */
	public static String getEkmsUrl() {
		return getCharValueByName(SysParamNameConst.EKMS_URL);
	}
	/**
	 * 中心机房url
	 * @return
	 */
	public static String getZxjfUrl() {
		return getCharValueByName(SysParamNameConst.ZXJF_URL);
	}
	/**
	 * 报表系统 url
	 * @return
	 */
	public static String getReptUrl() {
		return getCharValueByName(SysParamNameConst.REPT_URL);
	}
	/**
	 * 系统样式版本
	 * @return
	 */
	public static String getSysVersion() {
		return getCharValueByName(SysParamNameConst.VERSION);
	}
	/**
	 * tuxedo的ip
	 * @return
	 */
	public static String getTuxedoIP() {
		return getCharValueByName(SysParamNameConst.TUXEDO_IP);
	}

	// public static String getStuRole() {
	// return getCharValueByName(SysParamNameConst.STUNO_ROLE);
	// }
	public String getUploadFilePath() {
		return getCharValueByName(SysParamNameConst.UPLOADFILEPATH);
	}

	public static String getEmsDmpUrl() {
		return getCharValueByName(SysParamNameConst.EMS_DMP_URL);
	}

	public static String getEmsDmpUser() {
		return getCharValueByName(SysParamNameConst.EMS_DMP_USER);
	}

	public static String getEmsDmpPwd() {
		return getCharValueByName(SysParamNameConst.EMS_DMP_PWD);
	}

	public static String getEmsDmpBkdirDmp() {
		return getCharValueByName(SysParamNameConst.EMS_DMP_BKDIR_dmp);
	}

//	public static String getEmsDmpBkdirCopy() {
//		return getCharValueByName(SysParamNameConst.EMS_DMP_BKDIR_copy);
//	}

	public String getEiasSrcImgPath() {
		return getCharValueByName(SysParamNameConst.EIAS_SRC_IMGPATH);
	}

	// public static String getEiasFrontImgName() {
	// return
	// getCharValueByName(SysParamNameConst.EIAS_SRC_FRONT_IMG_NAME);//影像图片源文件（正面）名
	// }
	// public static String getEiasBackImgName() {
	// return
	// getCharValueByName(SysParamNameConst.EIAS_SRC_BACK_IMG_NAME);//影像图片源文件（背面）名
	// }
	public static String getEiasTargetImgPath() {
		return getCharValueByName(SysParamNameConst.EIAS_TARGET_IMGPATH);// 生成影像存放路径
	}

	public static String getEiasImgSuffixname() {
		return getCharValueByName(SysParamNameConst.EIAS_IMG_SUFFIXNAME);// 影像图片后缀名
	}

	// public static String getEiasCreImgNameFront() {
	// return
	// getCharValueByName(SysParamNameConst.EIAS_CRE_IMG_NAME_FRONT);//生成单个影像图片正面名字
	// }
	// public static String getEiasCreImgNameBack() {
	// return
	// getCharValueByName(SysParamNameConst.EIAS_CRE_IMG_NAME_BACK);//生成单个影像图片背面名字
	// }

	/* 考核系统入口 */
	// public Collection getEesPortList() {
	// return getSysParamList(SysParamNameConst.EESPor);
	// }
	/**
	 * 考核库还原  
	 * 文件 dmp 的路径
	 */
	public static String getOnlndbPath() {
		return getCharValueByName(SysParamNameConst.onlndbBK_dmp_path);
	}
	/**
	 * 考核库还原  
	 * 文件 dmp 名
	 */
	public static String getOnlndbFile() {
		return getCharValueByName(SysParamNameConst.onlndbBK_dmp_file);
	}
	/**
	 * 考核库还原  
	 * bat文件路径
	 */
	public static String getBatPath() {
		return getCharValueByName(SysParamNameConst.bat_path);
	}
	/**
	 * 考核库还原  
	 * 文件 bat 名
	 */
	public static String getBatFile() {
		return getCharValueByName(SysParamNameConst.bat_file);
	}
	/**
	 * 考核库还原  
	 * sql的文件路径
	 */
	public static String getLoadPath() {
		return getCharValueByName(SysParamNameConst.load_path);
	}
	/**
	 * 考核库还原  
	 * sql的文件名
	 */
	public static String getLoadFile() {
		return getCharValueByName(SysParamNameConst.load_file);
	}
	/**
	 * 考核库还原  
	 * 查询 实例库名的 sql语句
	 */
	public static String getCheckSidNameSQL() {
		return getCharValueByName(SysParamNameConst.checkSidNameSQL);
	}
	/**
	 * 考核库还原  
	 * 实例中的 表是否绑定高速缓存
	 */
	public static String getExecSPbindcache() {
		return getCharValueByName(SysParamNameConst.exec_sp_bindcache);
	}

	/**
	 * 班级群组 允许 分配个数
	 */
	public static int getGroupClassCount() {
		return Integer.parseInt(getCharValueByName(SysParamNameConst.APP_GROUPS));
	}
	/**
	 * 考核的柜面业务岗位
	 * @return
	 */
	public static String getEcbsExamPost() {
		return getCharValueByName(SysParamNameConst.ECBS_EXAM_POST);
	}
	/**
	 * 服务器ip
	 * @return
	 */
	public static String getSrvAddr() {
		return getCharValueByName(SysParamNameConst.SRV_ADDR);
	}
	
}
