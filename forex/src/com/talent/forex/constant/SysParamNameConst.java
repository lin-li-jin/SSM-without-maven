package com.talent.forex.constant;
public class SysParamNameConst {
	
	public final static String LOGIN_PAGE="/jsp/global/login/to.jsp";
	public final static String SCORE_QUERY_PAGE="/jsp/local/scoreQuery/ScoreQueryToPage.jsp";
	public final static String NAN="1";
	public final static String TEST_PAPER_SCORE_0="0";
	public final static String TEST_PAPER_SCORE_1="1";
	
	/*teacher*/
	public final static String TEA_STATUS_1="1";//�û���Ч
	public final static String TEA_STATUS_0="0";//�û���Ч
	public final static String SUPER_MANAGER_SCHOOLNUM="ALL";//���������У������ʶΪALL
	public final static String TEA_EXPIRE_0="0";

	//public final static String ORGINFO_G= "G";//G-�꼶

	/**�ļ����ͣ���ʦ��Ϣ teacher**/
	public static final String TEACHER_OBJECT = "teacher";
	/**�ļ����ͣ�������ѧ����Ϣ student**/
	public final static String STUDENT_OBJECT ="student";
	public static final String MANAGER_NAME ="ѧУ����Ա";
	public static final String STUDENT_NAME ="ѧ��";
	public static final String TEACHER_NAME ="��ʦ";
	public static final String COURSER_NAME ="�γ̸�����";
	
	public static final String ADMIN = "A";//ϵͳ����Ա
	public final static String TEACHER="T";//��ʦ
	public final static String COURSER="C";//�δ���
	public final static String STUDENT = "S";//ѧ��
	public final static String POST_NAME_1="xs";//��λ����--ѧ��
	public final static String CODE_CREDIT_SUC="0000";//�Ŵ����ؽ���ɹ��ı�־

	
	
	//sysparam ���е�////////////////////////////////////////////////////
	public final static String EMS_VERSION= "ems.version";//EMS�İ汾
	public static String APP_GROUPS ="APP_GROUPS";//�༶ Ⱥ�� ���� ����
	//public final static String PWD_MODIFY_FIRST="pwd_modify_first";//1:ǿ�Ƶ�һ�ε�½�޸�����,false:��һ�ε�½�����޸�����
	public final static String STUDENT_EXAMPLE="091559999";//����˵��
	public final static String STUDENT_NUM_TERM_MONTH="0101";//ѧ���˺ŵ����·ݣ�1��1��
	public final static String GROUP_STATUS_C="C";
	public final static String GROUP_STATUS_A="A";
	public final static String GROUP_STATUS_N="N";
	public static String group_class_count ="group_class_count";//�༶ Ⱥ�� ���� ����

	public final static int groupNum=2;//ѧ�������������Ⱥ����
	public final static int insertStuNum=78;
	public final static int insertLeastStuNum=3;
	public final static String EDUCLASSNO_LIST= "eduClassNoList";
	public final static String onlndbBK_dmp_path="onlndbBK.dmp.path";//���˿⻹ԭ�ļ����·
	public final static String onlndbBK_dmp_file="onlndbBK.dmp.file";//���˿⻹ԭ�ļ�
	public final static String bat_path="bat.file.path"; 
	public final static String bat_file="bat.file"; 
	public final static String load_path="load.file.path"; 
	public final static String load_file="load.file"; 
	public static String checkSidNameSQL="checkSidNameSQL";
	public static String exec_sp_bindcache ="exec_sp_bindcache";
	public static String exec_sp_bindcache_1 ="1";//�󶨸��ٻ���
	public static String SRV_ADDR="srvAddr";//������ip ���ڷ���IE�İ�ȫ����
	
	public final static String EMS_DMP_URL = "ems.dmp.url";
	public final static String EMS_DMP_USER = "ems.dmp.user";
	public final static String EMS_DMP_PWD = "ems.dmp.pwd";
	public final static String EMS_DMP_BKDIR_dmp= "ems.dmp.bkDir";//����dump
	public final static String EMS_DMP_BKDIR= "ems.dmp.castpath";//	ems.dmp.castpath=Z:/ ����copy

	public final static String EIAS_SRC_IMGPATH = "eias.src.imgPath";//Ӱ�������Ŵ�
	public final static String EIAS_SRC_FRONT_IMG_NAME = "eias.src.front.img.name";//Ӱ��ͼƬԴ�ļ������棩�� 
	public final static String EIAS_SRC_BACK_IMG_NAME = "eias.src.back.img.name";//Ӱ��ͼƬԴ�ļ������棩��
	public final static String EIAS_TARGET_IMGPATH = "eias.target.imgPath";//����Ӱ����·��
	public final static String EIAS_IMG_SUFFIXNAME = "eias.img.suffixname";//Ӱ��ͼƬ��׺��
	public final static String EIAS_CRE_IMG_NAME_FRONT = "eias.src.img.name.front";//���ɵ���Ӱ��ͼƬ��������
	public final static String EIAS_CRE_IMG_NAME_BACK = "eias.src.img.name.back";//���ɵ���Ӱ��ͼƬ��������
	
	//��������
	public final static String PRATICE_GROUP_CNT ="PRATICE_GROUP_CNT";//�ճ���ϰȺ���е���������
	public final static String MODIFY_SIDPOOL ="MODIFY_SIDPOOL";//ʵ����Ϣ �޸�1����0������ Ĭ��Ϊ0
	public final static String 	ADD_SIDPOOL ="ADD_SIDPOOL";//ʵ����Ϣ 1����0������ Ĭ��Ϊ0
	public final static String LOG_TIME ="LOG_TIME";//--ǿ���˳�����㿼��ʱ���Ĭ��ʱ�� -10min
	public final static String SID_SEQ ="SID_SEQ";//ʵ�����
	public final static String BATCH_TIME ="BATCH_TIME";//��ʱͳ�ƿ��ڵ�ʱ��㣨24Сʱ�ƣ�
	public final static String SUM_TIME ="SUM_TIME";//��ʱͳ�ƿ��ڵ�ʱ��㣨�߳�����ʱ��㣩
	
	public final static String EFES_URL = "efes.url";
	public final static String EES_URL = "ees.url";
	public final static String EKMS_URL = "ekms.url";
	public final static String ZXJF_URL = "zxjf.url";//���Ļ���
	public final static String REPT_URL = "rept.url";//����
	public final static String TUXEDO_IP = "tuxedo.ip";	
	public final static String BCMS_URL ="bcms.Url";//�Ŵ�ϵͳ
	public final static String BTITS_URL ="btits.Url";//���ʽ���ϵͳ
	
	public final static String SYS_EMS="EM";//22ʵ�����
	public final static String SYS_EES="E";//20����
	public final static String SYS_EFES="U";//21����
	public final static String SYS_EKSS="K";//23֪ʶ�� 

	
	public final static String SYS_EMS_DESR="ʵ�����ϵͳ";//22ʵ�����
	public final static String SYS_EES_DESR="ʵ�鿼��ϵͳ";//20����
	public final static String SYS_EFES_DESR="����ҵ��ϵͳ";//21����
	public final static String SYS_EKSS_DESR="֪ʶ��";//23֪ʶ�� 
	public final static String SYS_ECBS_DESR="�ۺϻ�ƺ���ϵͳ";//01����
	public final static String SYS_EIAS_DESR="Ʊ��ҵ��ϵͳ";//10Ʊ��
	//global codetable���е�//////////////////////////////////////////////////
	
	/*signInfo*/
	public final static String lOOUT_zixing="0";//�����˳�
	public final static String lOOUT_qiangzhi="1";//ǿ���˳�
	
	/*GROUP_TXN_MAPPING*/	
	public final static String GROUP_TXN_STATUS_1="1";//Ⱥ��ҵ��״̬ ��Ч ���� �û���ҵ���λ
	public final static String GROUP_TXN_STATUS_0="0";//Ⱥ��ҵ��״̬ ʧЧ
	
	/*USER_STATUS*/
	public final static String USER_STATUS_1 = "1";//�û�״̬��Ч
	public final static String USER_STATUS_0 = "0";//�û�״̬��Ч
	
	/*USER_POST*/
	public final static String USER_POST_1="1";//�û���λ��Ч
	public final static String USER_POST_0="0";//�û���λ��Ч


	//public final static String ORGINFO_G= "G";//G-�꼶
	public final static String SCHOOL_SUPCODE="0";//ѧУ���ϼ�����
	/*groupInfo*/
	public final static String GROUP_STATUS_1="1";//Ⱥ��״̬ ��Ч
	public final static String GROUP_STATUS_0="0";//Ⱥ��״̬ ��Ч
	/*groupTxnMapping*/
	public final static String GROUP_DEFAULT_Y="Y";//Ⱥ��Ĭ�� Y:��
	public final static String GROUP_DEFAULT_N="N";//Ⱥ��Ĭ�� N:��
	public final static String GROUP_USER_1="1";//Ⱥ�� �û� ��Ч
	public final static String GROUP_USER_0="0";//Ⱥ�� �û� ��Ч
	
	/*groupSidMapping*/
	public final static String GROUP_SID_STATUS_1="1";//Ⱥ��ʵ��״̬ ��Ч
	public final static String GROUP_SID_STATUS_0="0";//Ⱥ��ʵ��״̬ ʧЧ
	
	/*sidPool*/
	public final static String DEV_LEVEL_H = "H";//L:���� M:�м� H:�߼�
	public final static String DEV_LEVEL_L="L";//
	public final static String DEV_LEVEL_M = "M";//
	
	public final static String SID_TYPE_C="C";//������ϰ
	public final static String SID_TYPE_D="D";//�ճ���ϰ
	public final static String SID_TYPE_E="E";//����
	
	public final static String SID_STATUS_1="1";//�ѷ���
	public final static String SID_STATUS_2="2";//û����
	
	public final static String SID_ON="1";//ʵ������
	public final static String SID_OFF="0";//ʵ������
	/**����**/
	public final static String SID_ON_DESCR = "����";
	/**����**/
	public final static String SID_OFF_DESCR = "����";
	/** �Ѽ���**/
	public final static String SID_LOADED="1";//1�������أ����ã�0����ж�أ����У�
	/** δ����**/
	public final static String SID_UNLOADED="0";
	
	public final static String SID_MODEL_1="1";//��Ȩ
	public final static String SID_MODEL_0="0";//����Ȩ
	
	/*SERVER_INFO*/
	public final static String SRV_0 = "0"  ;//��������; 0��Ĭ�����ݿ������ 1�����ݿ������ 2��App������ 3���м��������
	public final static String SRV_1 = "1"  ;//��������; 0��Ĭ�����ݿ������ 1�����ݿ������ 2��App������ 3���м��������
	public final static String SRV_2 = "2"  ;//��������; 0��Ĭ�����ݿ������ 1�����ݿ������ 2��App������ 3���м��������
	public final static String SRV_3 = "3"  ;//��������; 0��Ĭ�����ݿ������ 1�����ݿ������ 2��App������ 3���м��������
	public final static String SRV_STATUS_0="0";//������״̬��  0����Ч
	public final static String SRV_STATUS_1="1";//������״̬��  1����Ч
	
	/*SRV_SID_MAPPING*/
	public final static String SRV_SID_0 = "0"  ;// ������ʵ�� ״̬	TRUE	 0����Ч���˳���ע��Ⱥ�顢session��ʱ��1����Ч
	public final static String SRV_SID_1 = "1"  ;// 
	/*SCHOOL_SID_MAPPING*/
	public final static String SCHOOL_SID_0 = "0"  ;
	public final static String SCHOOL_SID_1 = "1"  ; 
	
	/*COURSE_OPEN_STATUS*/
	public final static String COURSE_OPEN_STATUS_1="1";
	public final static String COURSE_OPEN_STATUS_0="0";
	
	/*COURSE_DATE_STATUS*/
	public final static String COURSE_DATE_STATUS_1="1";
	public final static String COURSE_DATE_STATUS_0="0";

	/***************************���ݿ�ʵ������********************************/	
//	
//	public final static String USER_STATUS_1="1";//�û���Ч
//	public final static String USER_STATUS_0="0";//�û���Ч

	public final static String DATA_INPUT_S="S"; //�ļ��ϴ�������ϴ��ɹ�
	public final static String DATA_INPUT_E="F";//�ļ��ϴ�������ϴ�ʧ�� 
	
	public final static String EDU_CLASSNO_LIST= "eduClassNoList";
	
//	public final static String SUM_1="1";//ͳ�Ʒ�ʽ��1��ѧ��ͳ�ơ�
//	public final static String SUM_2="2";//2����Ⱥ��ͳ�� 
//	public final static String SUM_3="3";//3�����γ�ͳ��	
//	public final static String EESPor = "EESPor"  ;//����ϵͳ���
	public final static String VERSION ="sys.version";//ϵͳ�汾 �е�����û����
	public final static String SABKCODE = "SABKCODE";//����������к�
	public final static String UPLOADFILEPATH = "uploadFilePath";
	

		
	
	public static final String PWD ="888888";//Ĭ�ϵ�����
	public static final String TEACHER_PWD="jiaoshi";//��ʦ��Ĭ������
	//ȫ�ֱ���//////////////////////////////////////
	public final static String TEA_GROUPS="teaGroups";//���̽�ʦ������Ⱥ��
	public final static String TEA_GROUP="teaGroup";//���̽�ʦ��һ��Ⱥ��	
	public final static String COURSE_MODEL="courseModel";//��ʦ�Ŀγ�
	public final static String COURSE_LIST= "courseList";
	public final static String ORGINFO_LIST= "orginfoList";//������Ϣ ����� S-У�𣨺�ѧԺ��D-ϵ��M-רҵ
	public final static String MAJOR_LIST= "majorList";
	public final static String GRADE_LIST= "gradeList";
	public final static String CLASSNO_LIST= "classNoList";
	public final static String SID_POOL_ECBS_LIST="sidPoolEcbsList";//���ĵ�ʵ������Ϣ
	public final static String INTEGRATED="Integrated";//���ۺ�ʵѵ�γ̱��
	
	public final static String EMS="ems";//22ʵ�����
	public final static String EES="ees";//20����
	public final static String EFES="efes";//21����
	
	
	/***********************��ҳ����****************************/
	public final static String ARTICLE_QUERY_LIST="articleQueryList";//�����б�
	
	//�������ͣ�1��֪ͨ2��֪ʶ����3��ʵ������4��֪ʶ���� 5:ϵͳ����
	public final static String ARTICLE_TYPE_NOTICE="1";
	public final static String ARTICLE_TYPE_KNOWLEDGE_LINK="2";
	public final static String ARTICLE_TYPE_EXP_CONTENTS="3";
	public final static String ARTICLE_TYPE_DOWN="4";
	public final static String ARTICLE_TYPE_INTRODUCE="5";
	public final static String ARTICLE_EFFECTIVE="1";//��Ч �������ʾ
	public final static String ARTICLE_INVALID="0";//��Ч ���治����ʾ
	
	//��������
	// �������µ� ������
	public final static String ARTICLE_NOTICE="ART_NOTICE";
	public final static String ARTICLE_KNOWLEDGE_LINK="ART_K_LINK";
	public final static String ARTICLE_EXP_CONTENTS="ART_EXP_CONTENTS";
	public final static String ARTICLE_DOWN="ART_DOWN";
	public final static String ARTICLE_INTRODUCE="ART_INTRODUCE";	
	public final static String ARTICLE_VERSION ="ART_VERSION";	//ϵͳ�������е� ���� �汾
	/***************************����ϵͳ�ĸ�λ********************************/

	public final static String ECBS_zxplgy="����������Ա";//0 
	public final static String ECBS_mr="";//1 Ĭ��	
	public final static String ECBS_zhgy="�ۺϹ�Ա";//C 
	public final static String ECBS_dggy="�Թ���Ա";//F 
	public final static String ECBS_zjywgy="�м�ҵ���Ա";//M 
	public final static String ECBS_wsyhgy="�������й�Ա";//N 
	public final static String ECBS_cxgy="�����Ա";//S	
	//���治�ܲ���
	public final static String ECBS_zxjfglgy="���Ļ��������Ա";//Z ���Ļ��������Ա
	public final static String ECBS_dhyhgy="�绰���й�Ա";//T �绰���й�Ա
	public final static String ECBS_posgy="POS��Ա";//P 
	public final static String ECBS_atmgy="ATM��Ա";//A 
	public final static String ECBS_zzgy="������Ա";//3

	public static final String ECBS_EXAM_POST = "ECBS_EXAM_POST";//����ʹ�õ� ��λ���� //Ҫ������
	
	public static final String EES_POST_ID_3="3";//����ͬ����������ʱ��ѧ����post_ID
	
	public static final String EES_POST_ID_1="1";//��ʦ��Ϣͬ����������ʹ�õ�
	public static final String SYN_FROM_TEACH_0="0";//ѧ����Ϣ��ʱ�ӽ���ϵͳ��ȡ��0������Ҫ
	public static final String SYN_FROM_TEACH_1="1";//ѧ����Ϣ��ʱ�ӽ���ϵͳ��ȡ��1����Ҫ

	public static final String TRADE_TYPE_LIST = "TRAN_TYPE";//���ۺ�ѯ�۽�������
	public static final String TRADE_TYPE_CASH_LIST = "TRAN_TYPE_CASH";//��֤��������
	public static final String TRADE_STATUS_LIST = "TRAN_STATUS";//����״̬
	public static final String CASH_TRADE_STATUS_LIST = "CASH_TRAN_STATUS";//��֤����״̬����Ϊֻ��5��״̬,���Զ�������
	public static final String ACCOUNT_TYPE_LIST = "ACC_TYPE";//�˻���������
	
	public static final String UNLIMITED_TIME = "99999999";
	
	/*�˻���Ϣ*/
	//�˻�����
	public static final String ACC_TYPE_C="C";//������˻�
	public static final String ACC_TYPE_W="W";//����˻�
	public static final String ACC_TYPE_B="B";//��֤���˻�
	//��ʼ�����
	public static final String ACC_TYPE_C_AMT="2000000.0000";
	public static final String ACC_TYPE_W_AMT="1000000.0000";
	public static final String ACC_TYPE_B_AMT="500000.0000";
	public static final String ACC_AMT="0.0000";
	//��֤��Ŵ���
	public static final String MARGIN_ENLARGE="5";
	
	/*�����������*/
	public static final String W_AMOUNT="33";
	public static final String W_QUANTIY="33";
	public static final String W_RATE="34";
	public static final String W_CNY_ACC="33";
	public static final String W_FOR_ACC="33";
	public static final String W_MARGIN_ACC="34";
	public static final String ACC_RANK_NUM="5";
	public static final String OVERALL_RANK_NUM="5";
	public static final String GROUP_ONE="O";//һ����
	public static final String GROUP_TWO="T";//������
	
	public static final String LOGIN_FALSE_TIMES="5";
	
	/*���ʻ�ȡ��ҳ��ַ*/
	public static final String RMB_JINGJIA_RATE_URL="http://fx.cmbchina.com/hq";
	public static final String RMB_XUNJIA_RATE_URL="http://www.boc.cn/sourcedb/whpj/index.html";
}
