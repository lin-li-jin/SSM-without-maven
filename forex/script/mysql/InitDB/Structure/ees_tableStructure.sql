use eems;
DROP TABLE IF EXISTS ASM_ELEMENTS;
CREATE TABLE ASM_ELEMENTS(
	 	`ELEMENT_ID`    int(12) auto_increment NOT NULL,
    `ASM_POINT_NUM` varchar(12)   NOT NULL,
    `PRCSCOD`       varchar(8)    NOT NULL,
    `ELEMENT_NAME`  varchar(24)   NOT NULL,
    `TAG`           varchar(20)   NULL,
    `DEFSELECT`     varchar(1)    NULL,
    `OPERORDER`     varchar(2)    NULL,
    `WINPARM_TYPE`  varchar(20)   NULL,
    `CHANGABLE`     varchar(1)    NULL,
    `RESERVE`       varchar(12)   NULL,
    CONSTRAINT PK_ASM_ELEMENTS
    PRIMARY KEY CLUSTERED (ELEMENT_ID)
	 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS ASM_MULTIPOINT;
CREATE TABLE ASM_MULTIPOINT(
	 	ID              int(12) auto_increment NOT NULL,
    MULTIPOINT_NAME varchar(64)   NOT NULL,
    MULTIPOINT_NUM  varchar(12)   NULL,
    MULTIPOINT_DSCR varchar(256)  NOT NULL,
    TEACHER_NUM     varchar(18)   NOT NULL,
    TEACHER_NAME    varchar(30)   NOT NULL,
    CONSTRAINT PK_ASM_MULTIPOINT
    PRIMARY KEY CLUSTERED (ID)	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS ASM_POINT;
CREATE TABLE ASM_POINT(
	 	ASM_POINT_ID   int(12) auto_increment NOT NULL,
    ASM_SYS_NUM    varchar(12)   NOT NULL,
    ASM_POINT_NUM  varchar(12)   NULL,
    ASM_POINT_NAME varchar(60)   NOT NULL,
    PRCSCOD        varchar(60)   NULL,
    PARENT_ID      varchar(12)   NOT NULL,
    ELEMENT_NUMBER varchar(4)    NULL,
    ASM_POINT_TYPE varchar(1)    NULL,
    RESERVE        varchar(60)   NULL,
    CONSTRAINT PK_ASM_POINT
    PRIMARY KEY CLUSTERED (ASM_POINT_ID)	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS ASM_POINT_DESCR;
CREATE TABLE ASM_POINT_DESCR(
		ID            int(12) auto_increment NOT NULL,
    ASM_POINT_NUM varchar(12)   NOT NULL,
    DESCR         text          NOT NULL,
    CONSTRAINT PK_ASM_POINT_DESCR
    PRIMARY KEY CLUSTERED (ID)
    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS ASM_SYS;
CREATE TABLE ASM_SYS(
		ASM_SYS_ID int(12) auto_increment NOT NULL,
    SYS_NUM    varchar(12)   NOT NULL,
    RESERVE    varchar(60)   NULL,
    CONSTRAINT PK_ASM_SYS
    PRIMARY KEY CLUSTERED (ASM_SYS_ID)
    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS ASM_WINPARM;
CREATE TABLE ASM_WINPARM(
		ID           int(12) auto_increment NOT NULL,
    WINPARM_TYPE varchar(20)   NOT NULL,
    OPTION_VALUE varchar(8)    NULL,
    OPTION_NAME  varchar(64)   NULL,
    CONSTRAINT PK_ASM_WINPARM
    PRIMARY KEY CLUSTERED (ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS TEST_CODE;
CREATE TABLE TEST_CODE(		
    CODE_TYPE_ID varchar(12) NOT NULL,
    CODE_ID      varchar(12) NOT NULL,
    CODE_DESRC   varchar(60) NULL,
    BEGIN_DATE   varchar(8)  NULL,
    END_DATE     varchar(8)  NULL,
    RESERVE      varchar(60) NULL,
    CONSTRAINT PK_TEST_CODE
    PRIMARY KEY CLUSTERED (CODE_TYPE_ID,CODE_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS TEST_CODE_TYPE;
CREATE TABLE TEST_CODE_TYPE(		
    CODE_TYPE_ID varchar(12) NOT NULL,
    CODE_TYPE    varchar(12) NOT NULL,
    LENGTH       varchar(2)  NULL,
    RESERVE      varchar(60) NULL,
    CONSTRAINT PK_TEST_CODE_TYPE
    PRIMARY KEY CLUSTERED (CODE_TYPE_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS CEE_MAPPING;
CREATE TABLE CEE_MAPPING(		
    MAPING_ID     int(12) auto_increment NOT NULL,
    EXERCISES_ID  numeric(12,0) NOT NULL,
    SERIAL_ID     int           NOT NULL,
    NCE_MODEL_ID  numeric(12,0) NULL,
    SYS_NUM       varchar(4)    NOT NULL,
    MP_ID         numeric(12,0) NULL,
    ASM_POINT_NUM varchar(12)   NULL,
    USER_ID       numeric(12,0) NULL,
    MODEL         text          NOT NULL,
    LIM_TIME      varchar(8)    NULL,
    CRE_DATE      varchar(8)    NULL,
    STATUS        varchar(1)    NULL,
    LEVELS        varchar(1)    NULL,
    UPDATE_TIMES  varchar(4)    NULL,
    RESERVE       varchar(60)   NULL,
    PKG_NUM       varchar(32)   NOT NULL,
    CONSTRAINT PK_CEMIN_MAPPING
    PRIMARY KEY CLUSTERED (MAPING_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS CEMIN_MAPPING;
CREATE TABLE CEMIN_MAPPING(		
    MAPING_ID     int(12) auto_increment NOT NULL,
    CE_MODEL_ID   numeric(12,0) NOT NULL,
    SERIAL_ID     numeric(12,0) NOT NULL,
    NCE_MODEL_ID  numeric(12,0) NOT NULL,
    SYS_NUM       varchar(12)   NOT NULL,
    MP_ID         numeric(12,0) NULL,
    ASM_POINT_NUM varchar(12)   NOT NULL,
    USER_ID       numeric(12,0) NULL,
    MODEL         text          NOT NULL,
    LIM_TIME      varchar(8)    NOT NULL,
    CRE_DATE      varchar(8)    NULL,
    STATUS        varchar(1)    NULL,
    LEVELS        varchar(1)    NOT NULL,
    UPDATE_TIMES  varchar(4)    NULL,
    RESERVE       varchar(60)   NULL,
    CONSTRAINT PK_CEMIN_MAPPING
    PRIMARY KEY CLUSTERED (MAPING_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS CE_ELEMENT;
CREATE TABLE CE_ELEMENT(		
    MAPING_ID    int(12) auto_increment NOT NULL,
    CE_MODEL_ID  numeric(12,0) NOT NULL,
    SERIAL_ID    varchar(4)    NOT NULL,
    NCE_MODEL_ID numeric(12,0) NOT NULL,
    ELEMENT_ID   varchar(12)   NOT NULL,
    CONSTRAINT PK_CE_ELEMENT
    PRIMARY KEY CLUSTERED (MAPING_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS CE_EXERCISES;
CREATE TABLE CE_EXERCISES(		
    EXERCISES_ID    int(12) auto_increment NOT NULL,
    SYS_NUM         varchar(12)   NULL,
    EXERCISES_LABEL text          NULL,
    MULTIPOINT_ID   numeric(12,0) NOT NULL,
    USER_ID         numeric(12,0) NULL,
    ELEMENTS        text          NULL,
    EXERCISES       text          NOT NULL,
    LIM_TIME        int           NOT NULL,
    POINTS          int           NULL,
    CRE_DATE        varchar(8)    NULL,
    STATUS          varchar(1)    NULL,
    LEVELS          varchar(1)    NOT NULL,
    NUMBERS         int           NULL,
    RESERVE         varchar(60)   NULL,
    PKG_NUM         varchar(32)   NOT NULL,
    CONSTRAINT PK_CE_EXERCISES
    PRIMARY KEY CLUSTERED (EXERCISES_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS CE_MODEL;
CREATE TABLE CE_MODEL(		 
    MODEL_ID      int(12) auto_increment NOT NULL,
    MP_ID         numeric(12,0) NULL,
    USER_ID       numeric(12,0) NULL,
    LIM_TIME      varchar(12)   NOT NULL,
    CRE_DATE      varchar(8)    NULL,
    MODEL_NAME    varchar(240)  NULL,
    STATUS        varchar(1)    NULL,
    LEVELS        varchar(1)    NOT NULL,
    NUMBERS       varchar(4)    NULL,
    RESERVE       varchar(60)   NULL,
    MULTIPOINT_ID numeric(12,0) NOT NULL,
    CONSTRAINT PK_CE_MODEL
    PRIMARY KEY CLUSTERED (MODEL_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS CE_MP;
CREATE TABLE CE_MP(		 
    MP_ID              int(12) auto_increment NOT NULL,
    EXERCISES_ID       numeric(12,0) NULL,
    SERIAL_ID          varchar(4)    NULL,
    CRE_DATE           varchar(8)    NULL,
    LIM_TIME           varchar(8)    NOT NULL,
    TIME_GRADE_PERCENT varchar(4)    NULL,
    RANGE              text          NULL,
    RANG_GRADE_PERCENT varchar(4)    NULL,
    DATA               text          NULL,
    NUMBER             varchar(4)    NULL,
    RESERVE            varchar(60)   NULL,
    TIME_PRIORITY      varchar(4)    NULL,
    RANGE_PRIORITY     varchar(4)    NULL,
    CONSTRAINT PK_CE_MP
    PRIMARY KEY CLUSTERED (MP_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS CE_MP_MODEL;
CREATE TABLE CE_MP_MODEL(		 
    CE_MP_ID           int(12) auto_increment NOT NULL,
    CE_MODEL_ID        varchar(12)   NOT NULL,
    SERIAL_ID          varchar(2)    NOT NULL,
    CRE_DATE           varchar(8)    NULL,
    LIM_TIME           varchar(8)    NOT NULL,
    TIME_PRIORITY      varchar(2)    NULL,
    TIME_GRADE_PERCENT varchar(4)    NOT NULL,
    PRECISE_FLAG       varchar(2)    NULL,
    RANGE_PRIORITY     varchar(2)    NULL,
    RANGE              varchar(120)  NOT NULL,
    RANG_GRADE_PERCENT varchar(4)    NOT NULL,
    DATA               varchar(120)  NOT NULL,
    NUMBER             varchar(4)    NULL,
    RESERVE            varchar(60)   NULL,
    CONSTRAINT PK_NCE_MP_MODEL
    PRIMARY KEY CLUSTERED (CE_MP_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS EXPPRO_AGENDA;
CREATE TABLE EXPPRO_AGENDA(		 
    ID               int(12) auto_increment NOT NULL,
    EXPPRO_ID        numeric(12,0) NOT NULL,
    COURSE_AGENDA_ID numeric(12,0) NOT NULL,
    TEST_PAPER_ID    numeric(12,0) NOT NULL,
    CRE_DATE         varchar(8)    NOT NULL,
    USER_ID          numeric(12,0) NOT NULL,
    USER_NUM         numeric(12,0) NOT NULL,
    STATUS           varchar(1)    NULL,
    USESTATUS        varchar(1)    NULL,
    RESERVE          varchar(60)   NULL,
    CONSTRAINT PK_EXPPRO_AGENDA
    PRIMARY KEY CLUSTERED (ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS EXPPRO_REPORT;
CREATE TABLE EXPPRO_REPORT(		 
    EXPPRO_ID        int(12) auto_increment NOT NULL,
    EXPERIMENT_NAME  varchar(128)  NULL,
    CREATE_DATE      varchar(8)    NULL,
    EXPPRO_TARGET    text          NULL,
    EXPPRO_THEORY    text          NULL,
    EXPPRO_MATERIAL  text          NULL,
    ELE4_VALUE       text          NULL,
    ELE5_VALUE       text          NULL,
    ELE6_VALUE       text          NULL,
    ELSE_CONTENT     text          NULL,
    COURSE_AGENDA_ID numeric(12,0) NULL,
    CAGENDA_STATUS   varchar(1)    NOT NULL,
    TAGENDA_STATUS   varchar(1)    NOT NULL,
    RESERVE          varchar(60)   NULL,
    CONSTRAINT PK_EXPPRO_REPORT
    PRIMARY KEY CLUSTERED (EXPPRO_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS EXP_REPORT_MODEL;
CREATE TABLE EXP_REPORT_MODEL(		 
    MODEL_ID    int(12) auto_increment NOT NULL,
    MODEL_NAME  varchar(128)  NOT NULL,
    MODEL_FOR   varchar(1)    NOT NULL,
    USER_ID     numeric(12,0) NULL,
    CREDATE     varchar(8)    NULL,
    EXP_TARGET  text          NULL,
    EXP_CONTENT text          NULL,
    EXP_PROCESS text          NULL,
    OPTION_NUM  int           DEFAULT 0 NULL,
    OPTION1     varchar(64)   NULL,
    VALUE1      text          NULL,
    OPTION2     varchar(64)   NULL,
    VALUE2      text          NULL,
    OPTION3     varchar(64)   NULL,
    VALUE3      text          NULL,
    OPTION4     varchar(64)   NULL,
    VALUE4      text          NULL,
    CONSTRAINT PK_EXP_REPORT_MODEL
    PRIMARY KEY CLUSTERED (MODEL_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS NCE_ELEMENT;
CREATE TABLE NCE_ELEMENT(		 
    NCE_ELEMENT_ID int(12) auto_increment NOT NULL,
    MODEL_ID       numeric(12,0) NOT NULL,
    MP_ID          numeric(12,0) NULL,
    ELEMENT_ID     numeric(12,0) NOT NULL,
    RESERVE        varchar(12)   NULL,
    CONSTRAINT PK_NCE_ELEMENT
    PRIMARY KEY CLUSTERED (NCE_ELEMENT_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS NCE_EXERCISES;
CREATE TABLE NCE_EXERCISES(		 
    EXERCISES_ID  int(12) auto_increment NOT NULL,
    SYS_NUM       varchar(12)   NOT NULL,
    MP_ID         numeric(12,0) NULL,
    ASM_POINT_NUM varchar(12)   NOT NULL,
    USER_ID       numeric(12,0) NULL,
    ELEMENTS      varchar(512)  NOT NULL,
    EXERCISES     text          NOT NULL,
    LIM_TIME      int           NOT NULL,
    POINTS        varchar(4)    NULL,
    CRE_DATE      varchar(8)    NULL,
    STATUS        varchar(1)    NULL,
    LEVELS        varchar(1)    NOT NULL,
    RESERVE       varchar(4)    NULL,
    PKG_NUM       varchar(32)   NOT NULL,
    CONSTRAINT PK_NCE_EXERCISES
    PRIMARY KEY CLUSTERED (EXERCISES_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS NCE_MODEL;
CREATE TABLE NCE_MODEL(		 
    MODEL_ID      int(12) auto_increment NOT NULL,
    SYS_NUM       varchar(12)   NOT NULL,
    MP_ID         numeric(12,0) NULL,
    ASM_POINT_NUM varchar(12)   NOT NULL,
    USER_ID       numeric(12,0) NULL,
    MODEL         text          NOT NULL,
    LIM_TIME      varchar(8)    NOT NULL,
    CRE_DATE      varchar(8)    NULL,
    STATUS        varchar(1)    NULL,
    LEVELS        varchar(1)    NOT NULL,
    UPDATE_TIMES  varchar(4)    NULL,
    RESERVE       varchar(60)   NULL,
    CONSTRAINT PK_NCE_MODEL
    PRIMARY KEY CLUSTERED (MODEL_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS NCE_MP;
CREATE TABLE NCE_MP(		 
    MP_ID              int(12) auto_increment NOT NULL,
    CRE_DATE           varchar(8)    NULL,
    LIM_TIME           varchar(8)    NOT NULL,
    RANGE              varchar(300)  NOT NULL,
    DATA               varchar(300)  NOT NULL,
    NUMBER             varchar(4)    NULL,
    TIME_GRADE_PERCENT varchar(4)    NULL,
    RANG_GRADE_PERCENT varchar(4)    NULL,
    TIME_PRIORITY      varchar(4)    NULL,
    RANGE_PRIORITY     varchar(4)    NULL,
    RESERVE            varchar(60)   NULL,
    CONSTRAINT PK_NCE_MP
    PRIMARY KEY CLUSTERED (MP_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS NCE_MP_MODEL;
CREATE TABLE NCE_MP_MODEL(		 
    MP_ID              int(12) auto_increment NOT NULL,
    CRE_DATE           varchar(8)    NULL,
    LIM_TIME           varchar(8)    NOT NULL,
    TIME_PRIORITY      varchar(2)    NULL,
    TIME_GRADE_PERCENT varchar(4)    NOT NULL,
    RANGE_PRIORITY     varchar(2)    NULL,
    RANGE              varchar(120)  NOT NULL,
    RANG_GRADE_PERCENT varchar(4)    NOT NULL,
    DATA               varchar(120)  NOT NULL,
    NUMBER             varchar(4)    NULL,
    RESERVE            varchar(60)   NULL,
    CONSTRAINT PK_NCE_MP_MODEL
    PRIMARY KEY CLUSTERED (MP_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS ORGINFO;
CREATE TABLE ORGINFO(		 
    ID       int(12) auto_increment NOT NULL,
    TYPE     char(1)       NOT NULL,
    CODE     varchar(12)   NOT NULL,
    NAME     varchar(60)   NOT NULL,
    SUP_CODE varchar(12)   NOT NULL,
    CONSTRAINT PK_ORGINFO
    PRIMARY KEY CLUSTERED (ID),
    CONSTRAINT ORGINFO_UK
    UNIQUE NONCLUSTERED (CODE)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS TEACHER;
CREATE TABLE TEACHER(		 
    ID           int(12) auto_increment NOT NULL,
    TEACHER_NUM  varchar(18)   NOT NULL,
    TEACHER_NAME varchar(30)   NOT NULL,
    ORGCODE      varchar(12)   NOT NULL,
    STATUS       char(1)       NOT NULL,
    PWD          varchar(18)   NOT NULL,
    EXPIRE       varchar(3)    NULL,
    CONSTRAINT PK_TEACHER
    PRIMARY KEY CLUSTERED (ID),
    CONSTRAINT TEACHER_UK
    UNIQUE NONCLUSTERED (TEACHER_NUM)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS STUDENT;
CREATE TABLE STUDENT(		 
    ID           int(12) auto_increment NOT NULL,
    STUDENT_NUM  varchar(18)   NOT NULL,
    STUDENT_NAME varchar(30)   NOT NULL,
    DEPARTMENT   varchar(12)   NULL,
    MAJOR        varchar(12)   NULL,
    GRADE        varchar(12)   NULL,
    CLASS_NO     varchar(12)   NULL,
    PWD          varchar(16)   NOT NULL,
    STATUS       char(1)       NOT NULL,
    EXPIRE       varchar(3)    NULL,
    CONSTRAINT PK_STUDENT
    PRIMARY KEY CLUSTERED (ID),
    CONSTRAINT STUDENT_UK
    UNIQUE NONCLUSTERED (STUDENT_NUM)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS POST;
CREATE TABLE POST(		 
    POST_ID    varchar(12) NOT NULL,
    POST_NAME  varchar(20) NULL,
    PERMISSION text        NULL,
    CONSTRAINT PK_POST
    PRIMARY KEY CLUSTERED (POST_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS USER_POST;
CREATE TABLE USER_POST(		 
    USER_POST_ID int(12) auto_increment NOT NULL,
    USER_NUM     varchar(12)   NOT NULL,
    POST_NUM     varchar(12)   NULL,
    STATE        varchar(12)   NULL,
    BEGIN_DATE   varchar(14)   NULL,
    END_DATE     varchar(14)   NULL,
    CONSTRAINT PK_USER_POST
    PRIMARY KEY CLUSTERED (USER_POST_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS GROUP_INFO;
CREATE TABLE GROUP_INFO(		 
    GROUP_ID varchar(12) NOT NULL,
    NAME     varchar(16) NULL,
    STATUS   varchar(1)  NULL,
    DESCR    varchar(60) NULL,
    CONSTRAINT PK_GROUP_INFO
    PRIMARY KEY CLUSTERED (GROUP_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS TEACHER_COURSE;
CREATE TABLE TEACHER_COURSE(		 
    TEACHER_NUM varchar(12) NOT NULL,
    COURSE_NUM  varchar(12) NOT NULL,
    BEGIN_DATE  varchar(8)  NULL,
    END_DATE    varchar(8)  NULL,
    CONSTRAINT PK_TEACHER_CLASS
    PRIMARY KEY CLUSTERED (TEACHER_NUM,COURSE_NUM)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS COURSE;
CREATE TABLE COURSE(		 
    ID          numeric(12,0) NOT NULL,
    COURSE_NUM  varchar(12)   NOT NULL,
    COURSE_NAME varchar(60)   NOT NULL,
    DEPARTMENT  varchar(12)   NULL,
    MAJOR       varchar(12)   NULL,
    CONSTRAINT PK_COURSE
    PRIMARY KEY CLUSTERED (ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS COURSE_AGENDA;
CREATE TABLE COURSE_AGENDA(		 
    ID                numeric(12,0) NOT NULL,
    COURSE_AGENDA_NUM varchar(32)   NOT NULL,
    COURSE_NUM        varchar(12)   NOT NULL,
    SCOR_FOR          char(1)       NOT NULL,
    TEACHER_NUM       varchar(18)   NOT NULL,
    COURSE_DATE       varchar(8)    NOT NULL,
    STATUS            char(1)       NOT NULL,
    COURSE_PHASE      char(1)       NOT NULL,
    CLASS_NO          varchar(32)   NULL,
    CLASS_NUM         varchar(2)    NOT NULL,
    WEEK_NO           varchar(2)    NOT NULL,
    CONSTRAINT PK_COURSE_AGENDA
    PRIMARY KEY CLUSTERED (ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS EDU_CLASS_STU_MAPPING;
CREATE TABLE EDU_CLASS_STU_MAPPING(		 
    CLASS_NO    varchar(32) NOT NULL,
    STUDENT_NUM varchar(18) NOT NULL,
    DESCR       varchar(60) NULL,
    CONSTRAINT PK_EDU_CLASS_STU_MAPPING
    PRIMARY KEY CLUSTERED (CLASS_NO,STUDENT_NUM)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS TEST_PAPER;
CREATE TABLE TEST_PAPER(		 
    TEST_PAPER_ID    int(12) auto_increment NOT NULL,
    COURSE_ID        numeric(12,0) NULL,
    TEST_PAPER_TITLE varchar(300)  NOT NULL,
    USER_ID          varchar(12)   NOT NULL,
    USER_NAME        varchar(60)   NULL,
    MODEL_ID         numeric(12,0) NULL,
    PAPER_FOR        varchar(2)    NOT NULL,
    CRE_DATE         varchar(8)    NULL,
    CHECK_STATUS     varchar(1)    NULL,
    TEST_STATUS      varchar(1)    NOT NULL,
    ASSIGN_STATUS    varchar(1)    NULL,
    TEST_TERM        varchar(20)   NOT NULL,
    TOTAL_COUNT      varchar(4)    NULL,
    TOTAL_POINTS     varchar(4)    NOT NULL,
    RESERVE          varchar(60)   NULL,
    TOTAL_TIME       varchar(6)    NULL,
    PKG_NUM          varchar(32)   NOT NULL,
    CONSTRAINT PK_TEST_PAPER
    PRIMARY KEY CLUSTERED (TEST_PAPER_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS TEST_PAPER_MP;
CREATE TABLE TEST_PAPER_MP(		 
    TEST_PAPER_MP_ID   int(12) auto_increment NOT NULL,
    TEST_PAPER_ID      numeric(12,0) NOT NULL,
    EXERCISES_ID       numeric(12,0) NOT NULL,
    SERIAL_ID          int           NOT NULL,
    MP_ID              numeric(12,0) NOT NULL,
    CRE_DATE           varchar(8)    NULL,
    LIM_TIME           int           NOT NULL,
    TIME_GRADE_PERCENT varchar(4)    NOT NULL,
    RANGE              varchar(300)  NOT NULL,
    RANG_GRADE_PERCENT varchar(4)    NOT NULL,
    DATA               varchar(300)  NOT NULL,
    SYS_NUM            varchar(2)    DEFAULT 01 NULL,
    MIN_SERIAL_ID      int           DEFAULT 0  NULL,
    ASM_POINT_NUM      varchar(72)   DEFAULT 0  NULL,
    HAVE_POINTS        int           DEFAULT 0  NULL,
    CONSTRAINT PK_TEST_PAPER_MP
    PRIMARY KEY CLUSTERED (TEST_PAPER_MP_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS PAPER_EXERCISES_MAPPING;
CREATE TABLE PAPER_EXERCISES_MAPPING(		 
    MAPPING_ID    int(12) auto_increment NOT NULL,
    TEST_PAPER_ID numeric(12,0) NOT NULL,
    EXERCISES_ID  numeric(12,0) NOT NULL,
    SERIAL_ID     int           NOT NULL,
    CRE_DATE      varchar(8)    NULL,
    RESERVE       varchar(60)   NULL,
    POINTS        int           NULL,
    ASM_POINT_NUM varchar(72)   NULL,
    MIN_SERIAL_ID int           NULL,
    HAVE_TIMES    int           NULL,
    HAVE_MIN      int           NULL,
    MULTIPOINT_ID numeric(12,0) NULL,
    CONSTRAINT PK_PAPER_EXERCISES_MAPPING
    PRIMARY KEY CLUSTERED (MAPPING_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS TEST_PAPER_ASSIGN;
CREATE TABLE TEST_PAPER_ASSIGN(		 
    TP_ASSIGN_ID           int(12) auto_increment NOT NULL,
    TEST_PAPER_ID          numeric(12,0) NOT NULL,
    MODEL_ID               numeric(12,0) NULL,
    EXPPRO_ID              numeric(12,0) NULL,
    COURSE_NUM             varchar(12)   NULL,
    TEST_GRADES            varchar(60)   NOT NULL,
    PAPER_FOR              varchar(2)    NOT NULL,
    TEST_TERM              varchar(20)   NOT NULL,
    USUALLY_SCORE_PERCENT  varchar(4)    NULL,
    ATTITUDE_SCORE_PERCENT varchar(4)    NULL,
    PAPER_SCORE_PERCENT    varchar(4)    NULL,
    CRE_DATE               varchar(8)    NULL,
    HAVE_SCORE             varchar(1)    NULL,
    HAVE_ATTITUDE          varchar(1)    NULL,
    HAVE_DAILY             varchar(1)    NULL,
    HAVE_MINPOINT          varchar(1)    NULL,
    INVIGILATE_STATUS      varchar(1)    NOT NULL,
    STATUS                 varchar(1)    NULL,
    RETEST                 varchar(1)    NULL,
    RESERVE                varchar(60)   NULL,
    ASSIGN_LABEL           varchar(256)  NULL,
    CONSTRAINT PK_TEST_PAPER_ASSIGN
    PRIMARY KEY CLUSTERED (TP_ASSIGN_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS COM_TESTPAPER_MODE;
CREATE TABLE COM_TESTPAPER_MODE(        
    ID                 int(12) auto_increment NOT NULL,
    MODE_NAME          varchar(64)   NOT NULL,
    NCE_NUM            int           NOT NULL,
    CE_NUM             int           NOT NULL,
    NCE_ASMPOINT_MUCH  text          NULL,
    CE_MULTIPOINT_MUCH text          NULL,
    NCE_ASMPOINT_NUM   text          NULL,
    CE_MULTIPOINT_NUM  text          NULL,
    EASY               int           NOT NULL,
    ZHONGDENG          int           NOT NULL,
    NAN                int           NOT NULL,
    TEST_PAPER_ID      numeric(12,0) NOT NULL,
    SUIJI              char(1)       NOT NULL,
    NCE_NANYI_FENBU    text          NOT NULL,
    CE_NANYI_FENBU     text          NOT NULL,
    TEACHER_NUM        varchar(18)   NOT NULL,
    TEACHER_NAME       varchar(30)   NOT NULL,
    CONSTRAINT PK_COM_TESTPAPER_MODE
    PRIMARY KEY CLUSTERED (ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS AUTO_COM_TESTPAPER_INFO;
CREATE TABLE AUTO_COM_TESTPAPER_INFO(        
    ID                 int(12) auto_increment NOT NULL,
    TEST_PAPER_ID      numeric(12,0) NOT NULL,
    SUIJI              char(1)       NOT NULL,
    NCE_NUM            int           NOT NULL,
    CE_NUM             int           NOT NULL,
    NCE_ASMPOINT_MUCH  text          NULL,
    CE_MULTIPOINT_MUCH text          NULL,
    NCE_ASMPOINT_NUM   text          NULL,
    CE_MULTIPOINT_NUM  text          NULL,
    EASY               int           NOT NULL,
    ZHONGDENG          int           NOT NULL,
    NAN                int           NOT NULL,
    NCE_NANYI_FENBU    text          NOT NULL,
    CE_NANYI_FENBU     text          NOT NULL,
    CONSTRAINT PK_AUTO_COM_TESTPAPER_MODE
    PRIMARY KEY CLUSTERED (ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS COMPARE_LOG;
CREATE TABLE COMPARE_LOG(
    COMPARE_LOG_ID int(12) auto_increment NOT NULL,
    STUDENT_ID     numeric(12,0) NOT NULL,
    STUDENT_NUM    varchar(12)   NOT NULL,
    USER_ID        numeric(12,0) NOT NULL,
    TEST_PAPER_ID  numeric(12,0) NOT NULL,
    EXERCISES_ID   numeric(12,0) NULL,
    SERIAL_ID      int           NOT NULL,
    MINSERIAL_ID   int           NOT NULL,
    ASM_POINT_NUM  varchar(72)   NOT NULL,
    SYS_NUM        varchar(12)   NOT NULL,
    COMPARE_DESCR  text          NOT NULL,
    MP_ID          numeric(12,0) NOT NULL,
    HAVE_POINTS    int           NULL,
    HAVE_TIME      int           NULL,
    HAVE_RANGE     text          NULL,
    HAVE_DATA      text          NULL,
    OPERATE_TIME   varchar(4)    NULL,
    OPERATE_RANGE  text          NULL,
    OPERATE_DATA   text          NULL,
    RESULT_TIME    varchar(20)   NULL,
    RESULT_RANGE   varchar(20)   NULL,
    RESULT_DATA    varchar(20)   NULL,
    GET_POINTS     varchar(4)    NULL,
    COMPARE_DATE   varchar(20)   NULL,
    COMPARE_END    varchar(20)   NULL,
    MULTIPOINT_ID  numeric(12,0) NULL,
    ERROR_ID       varchar(4)    NULL,
    STATUS_FLAG    varchar(2)    NULL,
    RECOMPARE_FLAG varchar(2)    NULL,
    RESERVE        varchar(60)   NULL,
    CONSTRAINT PK_COMPARE_LOG
    PRIMARY KEY CLUSTERED (COMPARE_LOG_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS COMPARE_REPORT;
CREATE TABLE COMPARE_REPORT(
    REPORT_ID          int(12) auto_increment NOT NULL,
    STUDENT_ID         numeric(12,0) NOT NULL,
    STUDENT_NUM        varchar(12)   NOT NULL,
    USER_ID            numeric(12,0) NOT NULL,
    CLASS_ID           varchar(12)   NOT NULL,
    PARPER_ID          numeric(12,0) NOT NULL,
    TOTAL_POINTS       varchar(4)    NOT NULL,
    GET_POINTS         varchar(4)    NULL,
    TOTAL_TIME         varchar(4)    NULL,
    OPRERATE_TIME      varchar(4)    NULL,
    CLASS_TIME_PERSENT varchar(4)    NULL,
    ALL_ASM_POINT      text          NULL,
    ASM_PERSENT        text          NULL,
    RANGE_COMPARE      varchar(512)  NULL,
    DATA_COMPARE       varchar(512)  NULL,
    TEACHER_COMMENT    varchar(300)  NULL,
    STUDENT_COMMENT    varchar(300)  NULL,
    ADD_1              varchar(512)  NULL,
    ADD_1_VALUE        varchar(512)  NULL,
    ADD_2              varchar(512)  NULL,
    ADD_2_VALUE        varchar(512)  NULL,
    ADD_3              varchar(512)  NULL,
    ADD_3_VALUE        varchar(512)  NULL,
    CHECK_CODE         varchar(8)    NULL,
    CONSTRAINT PK_COMPARE_REPORT
    PRIMARY KEY CLUSTERED (REPORT_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS EXP_ATTITUDE_SCORE;
CREATE TABLE EXP_ATTITUDE_SCORE(
    SCORE_ID    int(12) auto_increment NOT NULL,
    STUDENT_ID  numeric(12,0) NOT NULL,
    STUDENT_NUM varchar(20)   NOT NULL,
    COURESE_ID  numeric(12,0) NOT NULL,
    SCORE       int           NOT NULL,
    CREDATE     varchar(8)    NOT NULL,
    RESERVE     varchar(60)   NULL,
    CONSTRAINT PK_EXP_ATTITUDE_SCORE
    PRIMARY KEY CLUSTERED (SCORE_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS EXP_DAILY_SCORE;
CREATE TABLE EXP_DAILY_SCORE(
    SCORE_ID    int(12) auto_increment NOT NULL,
    STUDENT_ID  numeric(12,0) NOT NULL,
    STUDENT_NUM varchar(20)   NOT NULL,
    COURESE_ID  numeric(12,0) NOT NULL,
    SCORE       int           NOT NULL,
    CREDATE     varchar(8)    NOT NULL,
    RESERVE     varchar(60)   NULL,
    CONSTRAINT PK_EXP_DAILY_SCORE
    PRIMARY KEY CLUSTERED (SCORE_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS EXP_REPORT;
CREATE TABLE EXP_REPORT(
    REPORT_ID     int(12) auto_increment NOT NULL,
    MODEL_ID      numeric(12,0) NOT NULL,
    TEST_PAPER_ID numeric(12,0) NOT NULL,
    STUDENT_NAME  varchar(8)    NOT NULL,
    STUDENT_ID    varchar(12)   NULL,
    EXP_TIME      varchar(10)   NULL,
    EXP_SCORE     varchar(4)    NULL,
    EXP_NAME      varchar(20)   NULL,
    CREATE_DATE   varchar(10)   NULL,
    REPORT_EVALUE text          NULL,
    CONTENT       text          NOT NULL,
    RESERVE       varchar(60)   NULL,
    CONSTRAINT PK_EXP_REPORT
    PRIMARY KEY CLUSTERED (REPORT_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS EXP_STUDENT_MINPOINT;
CREATE TABLE EXP_STUDENT_MINPOINT(
    POINT_ID        int(12) auto_increment NOT NULL,
    TEST_PAPER_ID   numeric(12,0) NOT NULL,
    CLASS_NUM       varchar(16)   NULL,
    STUDENT_ID      numeric(12,0) NOT NULL,
    STUDENT_NUM     varchar(20)   NOT NULL,
    OVER_RATE       varchar(20)   NULL,
    TOTAL_SCORE     varchar(4)    NULL,
    ASM_RATE        text          NULL,
    TIME_RATE       varchar(20)   NULL,
    TIME_CLASS_SORT varchar(4)    NULL,
    IMPROVE_POINT   text          NULL,
    COMPARE_FINAL   char(1)       NULL,
    MESSAGE         text          NULL,
    RESERVE         varchar(64)   NULL,
    CONSTRAINT PK_EXP_STUDENT_MINPOINT
    PRIMARY KEY CLUSTERED (POINT_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS IBFE_TARGET_LOG;
CREATE TABLE IBFE_TARGET_LOG(
    LOG_ID        int(12) auto_increment NOT NULL,
    STUDENT_NUM   varchar(18)   NOT NULL,
    TEST_PAPER_ID numeric(12,0) NOT NULL,
    SYS_NUM       varchar(12)   NOT NULL,
    LOG_ISOMUX    numeric(12,0) NULL,
    LOG_STATUS    varchar(2)    NOT NULL,
    LOG_DESCR     text          NOT NULL,
    RESERVE       varchar(60)   NULL,
    PRCSCOD       varchar(8)    NOT NULL,
    CONSTRAINT PK_IBFE_TARGET_LOG
    PRIMARY KEY CLUSTERED (LOG_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS OPERATION_LOG;
CREATE TABLE OPERATION_LOG(
    OPERATE_ID       int(12) auto_increment NOT NULL,
    USER_ID          numeric(12,0) NULL,
    OPERATE_CONTENT  varchar(1024) NULL,
    START_DATE       varchar(20)   NULL,
    END_DATE         varchar(20)   NULL,
    OPERATE_RESULT   varchar(20)   NULL,
    SYS_NUM          varchar(12)   NULL,
    OPERATE_PROPERTY varchar(1)    NULL,
    ERROR_ID         numeric(12,0) NULL,
    RESERVE          varchar(60)   NULL,
    CONSTRAINT PK_OPERATION_LOG
    PRIMARY KEY CLUSTERED (OPERATE_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS STUDENT_EXERCISES_LOG;
CREATE TABLE STUDENT_EXERCISES_LOG(
    LOG_ID        int(12) auto_increment NOT NULL,
    STUDENT_ID    numeric(12,0) NOT NULL,
    STUDENT_NUM   varchar(12)   NOT NULL,
    TEST_PAPER_ID numeric(12,0) NOT NULL,
    EXERCISES_ID  numeric(12,0) NOT NULL,
    SERIAL_ID     int           NOT NULL,
    MINSERIAL_ID  int           DEFAULT 0   NULL,
    TEST_TYPE     varchar(4)    NOT NULL,
    SYS_NUM       varchar(4)    NOT NULL,
    TEST_DATE     varchar(8)    NOT NULL,
    START_TIME    varchar(12)   NULL,
    END_TIME      varchar(12)   NULL,
    FINISH_STATUS varchar(1)    DEFAULT 'n' NULL,
    REDO          int           DEFAULT 0   NULL,
    LOG_STATUS    varchar(2)    NULL,
    MAX_ISOMUX    numeric(12,0) NULL,
    MIN_ISOMUX    numeric(12,0) NULL,
    ECBS_PRCSCOD  varchar(8)    NULL,
    ECBS_OPERTOR  varchar(12)   NULL,
    ECBS_STATIME  varchar(30)   NULL,
    EIAS_ISOMUX   varchar(20)   NULL,
    EIAS_OPERTOR  varchar(12)   NULL,
    EIAS_AREAID   varchar(12)   NULL,
    RESERVE       varchar(60)   NULL,
    CONSTRAINT PK_STUDENT_EXERCISES_LOG
    PRIMARY KEY CLUSTERED (LOG_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS STUDENT_TESTPAPER_LOG;
CREATE TABLE STUDENT_TESTPAPER_LOG(
    LOG_ID        int(12) auto_increment NOT NULL,
    TEST_PAPER_ID numeric(12,0) NOT NULL,
    STUDENT_ID    numeric(12,0) NOT NULL,
    STUDENT_NUM   varchar(12)   NOT NULL,
    START_TIME    varchar(16)   NULL,
    END_TIME      varchar(16)   NULL,
    FINISH_STATUS varchar(1)    DEFAULT 'n' NOT NULL,
    NOW_SERIAL_ID int           NULL,
    CONSTRAINT PK_STUDENT_TESTPAPER_LOG
    PRIMARY KEY CLUSTERED (LOG_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS OPERATION_LOG_HIST;
CREATE TABLE OPERATION_LOG_HIST(
    LOG_HIST_ID     int(12) auto_increment NOT NULL,
    OPERATE_LOG_ID  numeric(12,0) NOT NULL,
    USER_ID         numeric(12,0) NULL,
    OPERATE_CONTENT varchar(1024) NULL,
    START_DATE      varchar(20)   NULL,
    END_DATE        varchar(20)   NULL,
    OPERATE_RESULT  varchar(20)   NULL,
    SYS_NUM         varchar(12)   NULL,
    HIST_DATE       varchar(14)   NULL,
    CONSTRAINT PK_OPERATION_LOG_HIST
    PRIMARY KEY CLUSTERED (LOG_HIST_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS SCORE;
CREATE TABLE SCORE(
    SCORE_ID               int(12) auto_increment NOT NULL,
    STUDENT_ID             numeric(12,0) NOT NULL,
    STUDENT_NUM            varchar(12)   NOT NULL,
    TEST_PAPER_ID          numeric(12,0) NOT NULL,
    SCOR_FOR               varchar(12)   NOT NULL,
    SCORE                  int           NULL,
    USUALLY_SCORE          varchar(4)    NULL,
    USUALLY_SCORE_PERCENT  varchar(4)    NULL,
    PAPER_SCORE            varchar(4)    NULL,
    PAPER_SCORE_PERCENT    varchar(4)    NULL,
    ATTITUDE_SCORE         varchar(4)    NULL,
    ATTITUDE_SCORE_PERCENT varchar(4)    NULL,
    CHECK_CODE             varchar(8)    NULL,
    STATUS                 varchar(2)    NOT NULL,
    RESERVE                varchar(60)   NULL,
    CONSTRAINT PK_SCORE
    PRIMARY KEY CLUSTERED (SCORE_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS SCORE_HIST;
CREATE TABLE SCORE_HIST(
    SCORE_HIST_ID          int(12) auto_increment NOT NULL,
    SCORE_ID               numeric(12,0) NOT NULL,
    STUDENT_ID             numeric(12,0) NOT NULL,
    STUDENT_NUM            varchar(12)   NOT NULL,
    TEST_PAPER_ID          numeric(12,0) NOT NULL,
    SCOR_FOR               varchar(12)   NOT NULL,
    SCORE                  varchar(6)    NULL,
    USUALLY_SCORE          varchar(4)    NULL,
    USUALLY_SCORE_PERCENT  varchar(4)    NULL,
    PAPER_SCORE            varchar(4)    NULL,
    PAPER_SCORE_PERCENT    varchar(4)    NULL,
    ATTITUDE_SCORE         varchar(4)    NULL,
    ATTITUDE_SCORE_PERCENT varchar(4)    NULL,
    CHECK_CODE             varchar(8)    NULL,
    STATUS                 varchar(2)    NOT NULL,
    RESERVE                varchar(60)   NULL,
    HIST_DATE              varchar(14)   NULL,
    CONSTRAINT PK_SCORE_HIST
    PRIMARY KEY CLUSTERED (SCORE_HIST_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS STUDENT_STRESS_TEST;
CREATE TABLE STUDENT_STRESS_TEST(
    ID           int(12) auto_increment NOT NULL,
    CLASS_NO     varchar(32)   NOT NULL,
    STUDENT_NUM  varchar(18)   NOT NULL,
    DBID         varchar(20)   NULL,
    GROUP_ID     varchar(20)   NULL,
    ECBS_OPERTOR varchar(20)   NULL,
    EIAS_OPERTOR varchar(20)   NULL,
    EIAS_AREAID  varchar(20)   NULL,
    DESCR        varchar(60)   NULL,
    CONSTRAINT PK_EDU_CLASS_STU_MAPPING
    PRIMARY KEY CLUSTERED (ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS CISClearData;
CREATE TABLE CISClearData(
    SysDate         char(10)     NOT NULL,
    SysRound        tinyint      NOT NULL,
    CurrencyID      char(3)      NOT NULL,
    SN              int          NOT NULL,
    AreaNo          varchar(5)   NOT NULL,
    BranchID        varchar(5)   NOT NULL,
    MsgDate         char(10)     NOT NULL,
    MsgSN           varchar(8)   NOT NULL,
    DebitAccount    varchar(32)  NULL,
    AgentDate       char(10)     NULL,
    CISSendBankID   char(12)     NULL,
    TxnSN           varchar(10)  NULL,
    PayeeBankID     char(12)     NULL,
    PayeeAccount    varchar(32)  NULL,
    PayeeName       nvarchar(60) NULL,
    RemitterBankID  char(12)     NULL,
    RemitterAccount varchar(32)  NULL,
    RemitterName    nvarchar(60) NULL,
    Amount          varchar(20)  NULL,
    ChkType         char(2)      NULL,
    ItemNo          varchar(12)  NULL,
    ItemDate        char(10)     NULL,
    PfpDate         char(10)     NULL,
    PayCode         varchar(30)  NULL,
    RttlDate        char(10)     NULL,
    Reserve1        varchar(20)  NULL,
    Reserve2        varchar(20)  NULL,
    Reserve3        varchar(20)  NULL,
    CONSTRAINT PK_CISClearData
    PRIMARY KEY CLUSTERED (SysDate,SysRound,CurrencyID,SN),
    CONSTRAINT UK_CISClearData
    UNIQUE NONCLUSTERED (AreaNo,BranchID,MsgDate,MsgSN)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS CISItemAffixData;
CREATE TABLE CISItemAffixData(
    SysDate      char(10)      NOT NULL,
    SysRound     tinyint       NOT NULL,
    CurrencyID   char(3)       NOT NULL,
    SN           int           NOT NULL,
    BankBillPass varchar(20)   NULL,
    BillAmount   varchar(20)   NULL,
    BillFiDate   char(10)      NULL,
    AccAgmSN     varchar(20)   NULL,
    PayNo        varchar(20)   NULL,
    AccDate      char(10)      NULL,
    AccBankID    char(12)      NULL,
    Acceptor     varchar(60)   NULL,
    Purpose      varchar(60)   NULL,
    Remark       varchar(120)  NULL,
    BobTimes     varchar(5)    NULL,
    BobList      varchar(1500) NULL,
    Reserve1     varchar(20)   NULL,
    Reserve2     varchar(20)   NULL,
    Reserve3     varchar(20)   NULL,
    CONSTRAINT PK_CISItemAffixData
    PRIMARY KEY CLUSTERED (SysDate,SysRound,CurrencyID,SN)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS CISItemData;
CREATE TABLE CISItemData(
    SysDate            char(10)     NOT NULL,
    SysRound           tinyint      NOT NULL,
    CurrencyID         char(3)      NOT NULL,
    SN                 int          NOT NULL,
    AreaNo             varchar(5)   NOT NULL,
    BranchID           varchar(5)   NOT NULL,
    MsgDate            char(10)     NOT NULL,
    MsgSN              varchar(8)   NOT NULL,
    WorkDate           char(10)     NOT NULL,
    BatchNo            varchar(5)   NOT NULL,
    MsgType            varchar(5)   NULL,
    ImgID              varchar(32)  NULL,
    ImgMac             varchar(32)  NULL,
    DebitAccount       varchar(32)  NULL,
    AgentDate          char(10)     NULL,
    CISSendBankID      char(12)     NULL,
    TxnSN              varchar(10)  NULL,
    PayeeBankID        char(12)     NULL,
    PayeeAccount       varchar(32)  NULL,
    PayeeName          nvarchar(60) NULL,
    RemitterBankID     char(12)     NULL,
    RemitterAccount    varchar(32)  NULL,
    RemitterName       nvarchar(60) NULL,
    Amount             varchar(20)  NULL,
    ChkType            char(2)      NULL,
    ItemNo             varchar(12)  NULL,
    ItemDate           char(10)     NULL,
    PfpDate            char(10)     NULL,
    PayCode            varchar(30)  NULL,
    RttlDate           char(10)     NULL,
    SpecProcFlag       char(1)      NULL,
    Reserve1           varchar(20)  NULL,
    Reserve2           varchar(20)  NULL,
    Reserve3           varchar(20)  NULL,
    SendTxnSN          varchar(20)  NULL,
    AccountTxnSN       varchar(20)  NULL,
    ImportOpr          varchar(11)  NULL,
    ImportTime         datetime     NULL,
    InputStatus        char(1)      NULL,
    InputRetcd         char(4)      NULL,
    InputOpr           varchar(11)  NULL,
    InputTime          datetime     NULL,
    CheckOpr           varchar(11)  NULL,
    CheckTime          datetime     NULL,
    SpecCheckOpr       varchar(11)  NULL,
    SpecCheckTime      datetime     NULL,
    BAuditFlag         char(1)      NULL,
    AuditStatus        char(1)      NULL,
    AuditRetcd         char(4)      NULL,
    AuditOpr           varchar(11)  NULL,
    AuditTime          datetime     NULL,
    DoubleAuditOpr     varchar(11)  NULL,
    DoubleAuditTime    datetime     NULL,
    VerifyStatus       char(1)      NULL,
    VerifyRetcd        char(4)      NULL,
    VerifyOpr          varchar(11)  NULL,
    VerifyTime         datetime     NULL,
    DoubleVerifyOpr    varchar(11)  NULL,
    DoubleVerifyTime   datetime     NULL,
    BatchVerifyStatus  char(1)      NULL,
    BatchVerifyRetcd   char(4)      NULL,
    BatchVerifyTime    datetime     NULL,
    BatchVerifyCount   int          NULL,
    VerifyConfirmOpr   varchar(11)  NULL,
    VerifyConfirmTime  datetime     NULL,
    RejectFlag         char(1)      NULL,
    RejectReasonID     varchar(15)  NULL,
    RejectReason       nvarchar(64) NULL,
    RejectOpr          varchar(11)  NULL,
    RejectTime         datetime     NULL,
    AccountStatus      char(1)      NULL,
    AccountRetcd       char(4)      NULL,
    AccountOpr         varchar(11)  NULL,
    AccountTime        datetime     NULL,
    ConfirmAuthOpr     varchar(11)  NULL,
    ConfirmAuthTime    datetime     NULL,
    ManualFlag         char(1)      NULL,
    SendFlag           char(1)      NULL,
    SendTime           datetime     NULL,
    StrikeAccountTimes int          NULL,
    StrikeAccountOpr   varchar(11)  NULL,
    StrikeAccountTime  datetime     NULL,
    StrikeAccountFlag  char(1)      NULL,
    StrikeAccountRetcd char(4)      NULL,
    TxnProcFlag        varchar(2)   NULL,
    TxnProcRetcd       char(5)      NULL,
    TxnProcDesc        nvarchar(30) NULL,
    Status             char(1)      NOT NULL,
    Opr                varchar(11)  NULL,
    VerifyInputOpr     varchar(11)  NULL,
    VerifyInputTime    datetime     NULL,
    VerifyItemDate     varchar(10)  NULL,
    VerifyAccount      varchar(32)  NULL,
    BAuditResult       char(1)      NULL,
    InitAcctNo         varchar(32)  NULL,
    InitAcctType       char(1)      NULL,
    PayFlag            char(1)      NULL,
    InitAcctBalFlag    char(1)      NULL,
    InitAcctName       varchar(60)  NULL,
    AppReserve1        varchar(5)   NULL,
    AppReserve2        varchar(5)   NULL,
    AppReserve3        varchar(5)   NULL,
    LocReserve1        varchar(10)  NULL,
    LocReserve2        varchar(10)  NULL,
    LocReserve3        varchar(10)  NULL,
    InitAmount         varchar(20)  NULL,
    DataExportFlag     char(1)      NULL,
    AcctBalAmount      varchar(20)  NULL,
    AccountDate        varchar(20)  NULL,
    CONSTRAINT PK_CISItemData
    PRIMARY KEY CLUSTERED (SysDate,SysRound,CurrencyID,SN),
    CONSTRAINT UK_CISItemData
    UNIQUE NONCLUSTERED (AreaNo,BranchID,MsgDate,MsgSN)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS TEACHER_TESTPAPER_LOG;
CREATE TABLE TEACHER_TESTPAPER_LOG(
    LOG_ID        int(12) auto_increment NOT NULL,
    TEST_PAPER_ID numeric(12,0) NOT NULL,
    TPAGENDA_ID   numeric(12,0) NOT NULL,
    TEST_DATE     varchar(8)    NOT NULL,
    START_TIME    varchar(16)   NULL,
    END_TIME      varchar(16)   NULL,
    PAST_TIME     varchar(4)    NULL,
    ORIGN_NUM     int           NULL,
    JOIN_NUM      int           NULL,
    COMMIT_NUM    int           NULL,
    REDO          int           DEFAULT 0   NOT NULL,
    FINISH_STATUS varchar(1)    DEFAULT 'n' NOT NULL,
    CONSTRAINT PK_TEACHER_TESTPAPER_LOG
    PRIMARY KEY CLUSTERED (LOG_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS MO_TXINFO;
CREATE TABLE MO_TXINFO(
    LOG_ID  int(12) auto_increment NOT NULL,
    PKGSEQ  varchar(12)   NOT NULL,
    STATIME datetime      NULL,
    ENDTIME datetime      NULL,
    PROCCOD char(4)       NULL,
    PRCSCOD char(8)       NULL,
    USERID  char(7)       NULL,
    ERRTYPE char(2)       NULL,
    ERRPSEQ varchar(100)  NULL,
    ERRPTIM varchar(255)  NULL,
    ERRCODE char(2)       NULL,
    ERRTEXT varchar(300)  NULL,
    ERRSCPT varchar(500)  NULL,
    PKGDATA text          NULL,
    REPFLAG char(1)       NULL,
    FAILFLG char(1)       NULL,
    REDOFLG char(1)       NULL,
    PKGNUM  varchar(32)   NULL,
    CONSTRAINT MO_TXINFO_PK
    PRIMARY KEY CLUSTERED (LOG_ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS MY_MOTXINFO;
CREATE TABLE MY_MOTXINFO(
    ID           int(12) auto_increment NOT NULL,
    SYSTEM_ID    char(2)       NOT NULL,
    PKG_NUM      varchar(32)   NULL,
    USE_FOR      char(1)       NOT NULL,
    CORE_PKGSEQ  char(12)      NOT NULL,
    CORE_PRCSCOD char(8)       NULL,
    CORE_TIME    datetime      NULL,
    TAG          text          NULL,
    DATA         text          NULL,
    DESCR        text          NULL,
    VALUE_1      varchar(256)  NULL,
    VALUE_2      varchar(256)  NULL,
    VALUE_3      varchar(256)  NULL,
    VALUE_4      varchar(256)  NULL,
    VALUE_5      varchar(256)  NULL,
    VALUE_6      varchar(256)  NULL,
    VALUE_7      varchar(256)  NULL,
    VALUE_8      varchar(256)  NULL,
    CONSTRAINT PK_MY_MOTXINFO
    PRIMARY KEY CLUSTERED (ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS TAG_CONFIG;
CREATE TABLE TAG_CONFIG(
    ID        int(12) auto_increment NOT NULL,
    SYSTEM_ID char(2)       NOT NULL,
    TAG       nvarchar(30)  NOT NULL,
    TAG_DESC  nvarchar(60)  NOT NULL,
    VALUE     nvarchar(30)  NOT NULL,
    DESCR     nvarchar(60)  NOT NULL,
    CONSTRAINT PK_TAG_CONFIG
    PRIMARY KEY CLUSTERED (ID)
		    	 		 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS SERVER_INFO;
CREATE TABLE SERVER_INFO ( 	
	SRV_KEY			VARCHAR(12)    not null ,-- 服务器序号	TRUE	pk            
	SRV_IP			VARCHAR(16)  not null,-- 服务器ip	TRUE	***.***.***.***
	SRV_PORT		VARCHAR(8)	 not null,-- 端口	TRUE		
	SRV_NAME		VARCHAR(60)  not null,-- 服务器名	TRUE	
	DB_USER			VARCHAR(12)   null,-- 数据库登录用户	FALSE	
	DB_PWD			VARCHAR(12)   null,-- 数据库登录密码	FALSE	
	TUXEDO_IP		VARCHAR(16)   null,-- 中间件ip	FALSE	
	DESCR				VARCHAR(60)   null,-- 描述	FALSE	
	USER_FOR			VARCHAR(4)	not null,-- 用途	TRUE	0：默认数据库服务器1：考核数据库服务器 2：App服务器 3：中间件服务器
	STATUS					VARCHAR(1)			NULL,-- 状态  1 有效 0无效
	SID_NUM					VARCHAR(12)			NULL,-- 开设实例个数
	
	constraint SERVER_INFO PRIMARY KEY ( SRV_KEY )
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

