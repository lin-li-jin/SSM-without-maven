use eems;
set names gbk;
insert into TEACHER(TEACHER_NUM,TEACHER_NAME,ORGCODE,STATUS,PWD,EXPIRE) values('100101','系统管理员','155','1','888888','0');


insert into USER_POST(USER_NUM,POST_NUM,STATE,BEGIN_DATE,END_DATE) values('100101','1','1','20110124',NULL);
insert into USER_POST(USER_NUM,POST_NUM,STATE,BEGIN_DATE,END_DATE) values('100101','2','1','20110124',NULL);
insert into USER_POST(USER_NUM,POST_NUM,STATE,BEGIN_DATE,END_DATE) values('100101','3','1','20110124',NULL);



insert into POST(POST_ID,POST_NAME,PERMISSION) values('3','SYSMANAGER','eReport_add,tp_compose,tp_assign,tec_invigi,score_count,kaoshi_mng,tp_mng,exppro_mng,eReport_mng,exppro_add,ceType_add,nce_model,nce_mp_model,ce_model,nce_model,ce_model,nce_compose,ce_compose,score_mng,tp_refresh,');
