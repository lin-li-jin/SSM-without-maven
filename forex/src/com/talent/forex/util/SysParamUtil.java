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
	 * ���ز���ֵ
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
	 * ǿ���˳�����㿼��ʱ���Ĭ��ʱ��
	 * @return
	 */
	public static String getLogTime() {
		return getCharValueByName(SysParamNameConst.LOG_TIME);
	}

	/**
	 * ��ȡ ��ʱʱ���
	 * 
	 * @return
	 */
	public static String getBatchTime() {
		return getCharValueByName(SysParamNameConst.BATCH_TIME);
	}

	/**
	 * ��ȡ �߳�ͳ��ʱ���
	 * 
	 * @return
	 */
//	public String getSumTime() {
//		return getCharValueByName(SysParamNameConst.SUM_TIME);
//	}
	/**
	 * �Ƿ����� �޸�ʵ����Ϣ
	 * @return
	 */
	public String getModSidPool() {
		return getCharValueByName(SysParamNameConst.MODIFY_SIDPOOL);
	}

//	public Double getGroupcnt() {
//		return getNumValueByName(SysParamNameConst.PRATICE_GROUP_CNT);
//	}
	/**
	 * �Ƿ���������ʵ��
	 * @return
	 */
	public String getAddSid() {
		return getCharValueByName(SysParamNameConst.ADD_SIDPOOL);
	}
	/**
	 * ���µİ汾
	 * @return
	 */
//	public Double getArticleVersion() {
//		return getNumValueByName(SysParamNameConst.ARTICLE_VERSION);
//	}
	
	/**
	 * ϵͳ�İ汾��
	 */
	public String getVersion(){
		return getCharValueByName(SysParamNameConst.EMS_VERSION);
	}
	/**
	 * ��ҳ ��ʾҳ�� ��֪ͨ ���µ����ӵ�����
	 * @return
	 */
//	public Double getArticleNum(String paramName) {
//		return getNumValueByName(paramName);
//	}
	/**
	 * ����ҵ��ϵͳurl
	 * @return
	 */
	public static String getEfesUrl() {
		return getCharValueByName(SysParamNameConst.EFES_URL);
	}
	/**
	 * ����ϵͳurl
	 * @return
	 */
	public static String getEesUrl() {
		return getCharValueByName(SysParamNameConst.EES_URL);
	}
	/**
	 * ֪ʶ�� url
	 * @return
	 */
	public static String getEkmsUrl() {
		return getCharValueByName(SysParamNameConst.EKMS_URL);
	}
	/**
	 * ���Ļ���url
	 * @return
	 */
	public static String getZxjfUrl() {
		return getCharValueByName(SysParamNameConst.ZXJF_URL);
	}
	/**
	 * ����ϵͳ url
	 * @return
	 */
	public static String getReptUrl() {
		return getCharValueByName(SysParamNameConst.REPT_URL);
	}
	/**
	 * ϵͳ��ʽ�汾
	 * @return
	 */
	public static String getSysVersion() {
		return getCharValueByName(SysParamNameConst.VERSION);
	}
	/**
	 * tuxedo��ip
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
	// getCharValueByName(SysParamNameConst.EIAS_SRC_FRONT_IMG_NAME);//Ӱ��ͼƬԴ�ļ������棩��
	// }
	// public static String getEiasBackImgName() {
	// return
	// getCharValueByName(SysParamNameConst.EIAS_SRC_BACK_IMG_NAME);//Ӱ��ͼƬԴ�ļ������棩��
	// }
	public static String getEiasTargetImgPath() {
		return getCharValueByName(SysParamNameConst.EIAS_TARGET_IMGPATH);// ����Ӱ����·��
	}

	public static String getEiasImgSuffixname() {
		return getCharValueByName(SysParamNameConst.EIAS_IMG_SUFFIXNAME);// Ӱ��ͼƬ��׺��
	}

	// public static String getEiasCreImgNameFront() {
	// return
	// getCharValueByName(SysParamNameConst.EIAS_CRE_IMG_NAME_FRONT);//���ɵ���Ӱ��ͼƬ��������
	// }
	// public static String getEiasCreImgNameBack() {
	// return
	// getCharValueByName(SysParamNameConst.EIAS_CRE_IMG_NAME_BACK);//���ɵ���Ӱ��ͼƬ��������
	// }

	/* ����ϵͳ��� */
	// public Collection getEesPortList() {
	// return getSysParamList(SysParamNameConst.EESPor);
	// }
	/**
	 * ���˿⻹ԭ  
	 * �ļ� dmp ��·��
	 */
	public static String getOnlndbPath() {
		return getCharValueByName(SysParamNameConst.onlndbBK_dmp_path);
	}
	/**
	 * ���˿⻹ԭ  
	 * �ļ� dmp ��
	 */
	public static String getOnlndbFile() {
		return getCharValueByName(SysParamNameConst.onlndbBK_dmp_file);
	}
	/**
	 * ���˿⻹ԭ  
	 * bat�ļ�·��
	 */
	public static String getBatPath() {
		return getCharValueByName(SysParamNameConst.bat_path);
	}
	/**
	 * ���˿⻹ԭ  
	 * �ļ� bat ��
	 */
	public static String getBatFile() {
		return getCharValueByName(SysParamNameConst.bat_file);
	}
	/**
	 * ���˿⻹ԭ  
	 * sql���ļ�·��
	 */
	public static String getLoadPath() {
		return getCharValueByName(SysParamNameConst.load_path);
	}
	/**
	 * ���˿⻹ԭ  
	 * sql���ļ���
	 */
	public static String getLoadFile() {
		return getCharValueByName(SysParamNameConst.load_file);
	}
	/**
	 * ���˿⻹ԭ  
	 * ��ѯ ʵ�������� sql���
	 */
	public static String getCheckSidNameSQL() {
		return getCharValueByName(SysParamNameConst.checkSidNameSQL);
	}
	/**
	 * ���˿⻹ԭ  
	 * ʵ���е� ���Ƿ�󶨸��ٻ���
	 */
	public static String getExecSPbindcache() {
		return getCharValueByName(SysParamNameConst.exec_sp_bindcache);
	}

	/**
	 * �༶Ⱥ�� ���� �������
	 */
	public static int getGroupClassCount() {
		return Integer.parseInt(getCharValueByName(SysParamNameConst.APP_GROUPS));
	}
	/**
	 * ���˵Ĺ���ҵ���λ
	 * @return
	 */
	public static String getEcbsExamPost() {
		return getCharValueByName(SysParamNameConst.ECBS_EXAM_POST);
	}
	/**
	 * ������ip
	 * @return
	 */
	public static String getSrvAddr() {
		return getCharValueByName(SysParamNameConst.SRV_ADDR);
	}
	
}
