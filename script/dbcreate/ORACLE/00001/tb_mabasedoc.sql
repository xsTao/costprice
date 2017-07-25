/* tablename: ���ϼ۸�� */
create table mapub_materialpricebase (cmaterialpriceid char(20) not null 
/*���ϼ۸��*/,
pk_group varchar2(20) default '~' null 
/*����*/,
pk_org varchar2(20) default '~' null 
/*ҵ��Ԫ*/,
pk_org_v varchar2(20) default '~' null 
/*ҵ��Ԫ�汾*/,
vpricecode varchar2(200) null 
/*�۸�����*/,
vpricename varchar2(50) null 
/*�۸������*/,
ccrrencyid varchar2(20) default '~' null 
/*����*/,
vpricesourcenum varchar2(1000) null 
/*�۸���Դ��*/,
dbegindate char(19) null 
/*��Ч����*/,
denddate char(19) null 
/*ʧЧ����*/,
vnote varchar2(181) null 
/*��ע*/,
blockingflag char(1) null 
/*����*/,
creator varchar2(20) default '~' null 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
modifier varchar2(20) default '~' null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
 constraint pk_terialpricebase primary key (cmaterialpriceid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���ϼ۸����ϸ */
create table mapub_materialpricebase_b (cmaterialprice_bid char(20) not null 
/*�۸��������ϸ*/,
cmaterialpriceid char(20) null 
/*���ϼ۸��*/,
pk_group varchar2(20) default '~' null 
/*����*/,
pk_org varchar2(20) default '~' null 
/*ҵ��Ԫ*/,
pk_org_v varchar2(20) default '~' null 
/*ҵ��Ԫ�汾*/,
cmaterialid varchar2(20) default '~' null 
/*����*/,
cmaterialvid varchar2(20) default '~' null 
/*���϶�汾*/,
cmeasdocid varchar2(20) default '~' null 
/*������λ*/,
cprojectid varchar2(20) default '~' null 
/*��Ŀ*/,
cvendorid varchar2(20) default '~' null 
/*��Ӧ��*/,
cproductorid varchar2(20) default '~' null 
/*��������*/,
ccustomerid varchar2(20) default '~' null 
/*�ͻ�*/,
vbfree1 varchar2(101) null 
/*���ɸ�������1*/,
vbfree2 varchar2(101) null 
/*���ɸ�������2*/,
vbfree3 varchar2(101) null 
/*���ɸ�������3*/,
vbfree4 varchar2(101) null 
/*���ɸ�������4*/,
vbfree5 varchar2(101) null 
/*���ɸ�������5*/,
vbfree6 varchar2(101) null 
/*���ɸ�������6*/,
vbfree7 varchar2(101) null 
/*���ɸ�������7*/,
vbfree8 varchar2(101) null 
/*���ɸ�������8*/,
vbfree9 varchar2(101) null 
/*���ɸ�������9*/,
vbfree10 varchar2(101) null 
/*���ɸ�������10*/,
vpricesourcenum varchar2(1000) null 
/*�۸���Դ��*/,
vpricesourcerealnum varchar2(500) null 
/*ʵ�ʼ۸���Դ��*/,
nprice number(28,8) null 
/*����*/,
 constraint pk_rialpricebase_b primary key (cmaterialprice_bid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ����ϵ�� */
create table cm_allocfac (callocfacid char(20) not null 
/*����ϵ��*/,
vname6 varchar2(300) null 
/*����*/,
vname5 varchar2(300) null 
/*����*/,
vname4 varchar2(300) null 
/*����*/,
vname3 varchar2(300) null 
/*����*/,
vname2 varchar2(300) null 
/*����*/,
vname varchar2(300) null 
/*����*/,
pk_group varchar2(20) default '~' null 
/*��������*/,
pk_org varchar2(20) default '~' null 
/*����*/,
pk_org_v varchar2(20) default '~' null 
/*����*/,
vcode varchar2(50) null 
/*����*/,
vnote varchar2(181) null 
/*��ע*/,
creator varchar2(20) default '~' null 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
modifier varchar2(20) default '~' null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
ialloctype integer null 
/*��������*/,
 constraint pk_cm_allocfac primary key (callocfacid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ����ϵ����ϸ */
create table cm_allocfac_b (callocfacid char(20) null 
/*��������*/,
callocfac_bid char(20) not null 
/*�ӱ�����*/,
ccostcenterid varchar2(20) default '~' null 
/*�ɱ�����*/,
cmaterialid varchar2(20) default '~' null 
/*��Ʒ*/,
cmaterialvid varchar2(20) default '~' null 
/*����*/,
cmarcostclassid varchar2(20) default '~' null 
/*�ɱ�����*/,
cactivityid varchar2(20) default '~' null 
/*��ҵ*/,
nfactor number(28,8) null 
/*ϵ��*/,
ccostobjectid varchar2(20) default '~' null 
/*�ɱ�����*/,
pk_group varchar2(20) default '~' null 
/*��������*/,
pk_org varchar2(20) default '~' null 
/*�������°汾*/,
pk_org_v varchar2(20) default '~' null 
/*����*/,
cprojectid varchar2(20) default '~' null 
/*��Ŀ*/,
cproductorid varchar2(20) default '~' null 
/*��������*/,
cvendorid varchar2(20) default '~' null 
/*��Ӧ��*/,
ccustomerid varchar2(20) default '~' null 
/*�ͻ�*/,
vbfree1 varchar2(101) null 
/*���ɸ�������1*/,
vbfree2 varchar2(101) null 
/*���ɸ�������2*/,
vbfree3 varchar2(101) null 
/*���ɸ�������3*/,
vbfree4 varchar2(101) null 
/*���ɸ�������4*/,
vbfree5 varchar2(101) null 
/*���ɸ�������5*/,
vbfree6 varchar2(101) null 
/*���ɸ�������6*/,
vbfree7 varchar2(101) null 
/*���ɸ�������7*/,
vbfree8 varchar2(101) null 
/*���ɸ�������8*/,
vbfree9 varchar2(101) null 
/*���ɸ�������9*/,
vbfree10 varchar2(101) null 
/*���ɸ�������10*/,
cstuffid varchar2(20) default '~' null 
/*����*/,
cmarbaseclassid varchar2(20) default '~' null 
/*��������*/,
 constraint pk_cm_allocfac_b primary key (callocfac_bid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���ϼ۸���Դ */
create table mapub_materialpricesources (cpricesourcesid char(20) not null 
/*���ϼ۸���Դ*/,
 constraint pk_ialpricesources primary key (cpricesourcesid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���ϼ۸���Դ��ϸ */
create table mapub_materialpricesources_b (cpricesources_bid char(20) not null 
/*���ϼ۸���Դ��ϸ*/,
cpricesourcesid char(20) null 
/*���ϼ۸���Դ*/,
vpricesource integer null 
/*�۸���Դ*/,
pk_org varchar2(20) default '~' null 
/*ҵ��Ԫ*/,
corgcode varchar2(50) null 
/*ҵ��Ԫ����*/,
 constraint pk_lpricesources_b primary key (cpricesources_bid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ???��ȡ�� */
create table mapub_materialpullprice (cmaterialpullpriceid char(20) not null 
/*����ȡ��*/,
cperiod varchar2(20) default '~' null 
/*����ڼ䷽��*/,
costtype varchar2(20) default '~' null 
/*�ɱ�����*/,
cperiod1 varchar2(20) default '~' null 
/*����ڼ�*/,
cbegindate varchar2(20) default '~' null 
/*ƽ���ɹ���⿪ʼ�ڼ�*/,
cenddate varchar2(20) default '~' null 
/*ƽ���ɹ�����ֹ�ڼ�*/,
pk_org varchar2(50) null 
/*ҵ��Ԫ*/,
pk_group varchar2(50) null 
/*����*/,
 constraint pk_terialpullprice primary key (cmaterialpullpriceid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ����ȡ����ϸ */
create table mapub_materialpullprice_b (cmaterialpullprice_bid char(20) not null 
/*����ȡ����ϸ*/,
cmaterialpullpriceid char(20) null 
/*����ȡ��*/,
 constraint pk_rialpullprice_b primary key (cmaterialpullprice_bid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ȡ�۴�����־ */
create table mapub_acquirepricelog (pk_log char(20) not null 
/*ȡ�۴�����־*/,
vacquiretype varchar2(50) not null 
/*ȡ������*/,
pk_group varchar2(20) default '~' null 
/*ȡ�ۼ���*/,
pk_org varchar2(20) default '~' null 
/*ȡ�۹���*/,
ccosttypeid varchar2(20) default '~' null 
/*�ɱ�����*/,
cperiod varchar2(20) default '~' null 
/*����ڼ�*/,
begindate varchar2(20) default '~' null 
/*��ʼ�ڼ�*/,
enddate varchar2(20) default '~' null 
/*��ֹ�ڼ�*/,
isourcetype integer null 
/*��Դ����*/,
prioritylevel varchar2(50) null 
/*���ȼ�*/,
cmaterialpriceid varchar2(20) default '~' null 
/*�۸��*/,
pk_currtype varchar2(20) default '~' null 
/*��Դ����*/,
finstoragetype integer null 
/*�������*/,
ccostcenterid varchar2(20) default '~' null 
/*����Ʒ�ɱ�����*/,
cmaterialid varchar2(20) default '~' null 
/*����Ʒ*/,
cprojectid varchar2(20) default '~' null 
/*��Ŀ*/,
cvendorid varchar2(20) default '~' null 
/*��Ӧ��*/,
cproductorid varchar2(20) default '~' null 
/*��������*/,
ccustomerid varchar2(20) default '~' null 
/*�ͻ�*/,
vfree1 varchar2(101) null 
/*���ɸ�������1*/,
vfree2 varchar2(101) null 
/*���ɸ�������2*/,
vfree3 varchar2(101) null 
/*���ɸ�������3*/,
vfree4 varchar2(101) null 
/*���ɸ�������4*/,
vfree5 varchar2(101) null 
/*���ɸ�������5*/,
vfree6 varchar2(101) null 
/*���ɸ�������6*/,
vfree7 varchar2(101) null 
/*���ɸ�������7*/,
vfree8 varchar2(101) null 
/*���ɸ�������8*/,
vfree9 varchar2(101) null 
/*���ɸ�������9*/,
vfree10 varchar2(101) null 
/*���ɸ�������10*/,
cbcostcenterid varchar2(20) default '~' null 
/*���ϳɱ�����*/,
cbmaterialid varchar2(20) default '~' null 
/*����*/,
cbprojectid varchar2(20) default '~' null 
/*��Ŀ*/,
cbvendorid varchar2(20) default '~' null 
/*��Ӧ��*/,
cbproductorid varchar2(20) default '~' null 
/*��������*/,
cbcustomerid varchar2(20) default '~' null 
/*�ͻ�*/,
vbfree1 varchar2(50) null 
/*���ɸ�������1*/,
vbfree2 varchar2(50) null 
/*���ɸ�������2*/,
vbfree3 varchar2(50) null 
/*���ɸ�������3*/,
vbfree4 varchar2(50) null 
/*���ɸ�������4*/,
vbfree5 varchar2(50) null 
/*���ɸ�������5*/,
vbfree6 varchar2(50) null 
/*���ɸ�������6*/,
vbfree7 varchar2(50) null 
/*���ɸ�������7*/,
vbfree8 varchar2(50) null 
/*���ɸ�������8*/,
vbfree9 varchar2(50) null 
/*���ɸ�������9*/,
vbfree10 varchar2(50) null 
/*���ɸ�������10*/,
celementid varchar2(20) default '~' null 
/*����Ҫ��*/,
cactivityid varchar2(20) default '~' null 
/*��ҵ*/,
pk_group_actual varchar2(20) default '~' null 
/*ʵ��ȡ�ۼ���*/,
pk_org_actual varchar2(20) default '~' null 
/*ʵ��ȡ�۹���*/,
isourcetype_actual integer null 
/*ʵ��ȡ����Դ*/,
cmaterialpriceid_actual varchar2(20) default '~' null 
/*ʵ�ʼ۸��*/,
pk_currtype_actual varchar2(20) default '~' null 
/*ʵ�ʱ���*/,
nprice number(28,8) null 
/*�۸�*/,
errorinfo varchar2(500) null 
/*����ԭ��*/,
solveplan varchar2(500) null 
/*�������*/,
 constraint pk_acquirepricelog primary key (pk_log),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �ɱ����� */
create table cm_driver (vname varchar2(300) null 
/*����*/,
vname2 varchar2(300) null 
/*����*/,
vname3 varchar2(300) null 
/*����*/,
vname4 varchar2(300) null 
/*����*/,
cdriverid char(20) not null 
/*�ɱ���������*/,
vname6 varchar2(300) null 
/*����*/,
vname5 varchar2(300) null 
/*����*/,
pk_group varchar2(20) default '~' null 
/*��������*/,
pk_org varchar2(20) default '~' null 
/*�����֯����*/,
pk_org_v varchar2(20) default '~' null 
/*�����֯�汾����*/,
vcode varchar2(50) null 
/*����*/,
creator varchar2(20) default '~' null 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
modifier varchar2(20) default '~' null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
vformulavalue varchar2(750) null 
/*��ʽ*/,
vjavapath varchar2(50) null 
/*�Զ���JAVA��*/,
vformulacode varchar2(500) null 
/*��ʽcode*/,
vnote varchar2(181) null 
/*��ע*/,
vformulavalue2 varchar2(500) null 
/*��ʽ2*/,
vformulavalue3 varchar2(500) null 
/*��ʽ3*/,
vformulavalue4 varchar2(500) null 
/*��ʽ4*/,
vformulavalue5 varchar2(500) null 
/*��ʽ5*/,
vformulavalue6 varchar2(500) null 
/*��ʽ6*/,
 constraint pk_cm_driver primary key (cdriverid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �ɱ����� */
create table mapub_costtype (ccosttypeid char(20) not null 
/*�ɱ���������*/,
vcosttypename varchar2(300) null 
/*�ɱ���������*/,
vcosttypename2 varchar2(300) null 
/*�ɱ���������2*/,
vcosttypename3 varchar2(300) null 
/*�ɱ���������3*/,
vcosttypename4 varchar2(300) null 
/*�ɱ���������4*/,
vcosttypename5 varchar2(300) null 
/*�ɱ���������5*/,
vcosttypename6 varchar2(300) null 
/*�ɱ���������6*/,
pk_group varchar2(20) default '~' null 
/*����*/,
pk_org varchar2(20) default '~' null 
/*����*/,
vcosttypecode varchar2(40) null 
/*�ɱ����ͱ���*/,
bscrapfactor char(1) null 
/*�Ƿ��Ƿ�Ʒϵ��*/,
bshrinkfactor char(1) null 
/*�Ƿ������ϵ��*/,
pk_elementsystem varchar2(20) null 
/*Ҫ����ϵ*/,
pk_factorchart varchar2(20) null 
/*����Ҫ�ر�*/,
pk_materialdocview varchar2(20) null 
/*Ҫ�������϶��ձ�*/,
pk_activitydocview varchar2(20) null 
/*Ҫ������ҵ���ձ�*/,
vmaterialpricesourcenum varchar2(500) null 
/*���ϼ۸���Դ��*/,
vcostpricesourcenum varchar2(500) null 
/*���ü۸���Դ��*/,
cbeginmonth varchar2(50) null 
/*��Ч�ڼ�*/,
cendmonth varchar2(50) null 
/*ʧЧ�ڼ�*/,
bdefault char(1) null 
/*�Ƿ�Ĭ��*/,
bcompute char(1) null 
/*�Ƿ��Ѽ���*/,
bcreatetable char(1) null 
/*�Ƿ������ɿ�*/,
creator varchar2(20) default '~' null 
/*������*/,
modifier varchar2(20) default '~' null 
/*�޸���*/,
creationtime char(19) null 
/*����ʱ��*/,
modifiedtime char(19) null 
/*�޸�ʱ��*/,
vnote varchar2(1024) null 
/*��ע*/,
 constraint pk_mapub_costtype primary key (ccosttypeid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ����Ʒ���ϵ����ϸ */
create table mapub_coprodcoef_b (ccoprodcoefbid char(20) not null 
/*����Ʒ���ϵ����ϸ*/,
ccoprodcoefid char(20) null 
/*����Ʒ���ϵ��*/,
pk_group varchar2(20) default '~' null 
/*����*/,
pk_org varchar2(20) default '~' null 
/*ҵ��Ԫ*/,
pk_org_v varchar2(20) default '~' null 
/*ҵ��Ԫ�汾*/,
cmaterialid varchar2(20) default '~' not null 
/*�������°汾*/,
cmaterialvid varchar2(20) default '~' null 
/*����*/,
iproducttype integer not null 
/*��Ʒ����*/,
ccostcenterid varchar2(20) default '~' null 
/*�ɱ�����*/,
ccosttypeid varchar2(20) default '~' null 
/*�ɱ�����*/,
celementid varchar2(20) default '~' null 
/*����Ҫ�ر���*/,
nrelcoefficient number(28,8) not null 
/*���ϵ��*/,
 constraint pk_mapub_coef_b primary key (ccoprodcoefbid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ����Ʒ���ϵ�� */
create table mapub_coprodcoef (ccoprodcoefid char(20) not null 
/*����Ʒ���ϵ��*/,
pk_group varchar2(20) default '~' null 
/*����*/,
pk_org varchar2(20) default '~' not null 
/*ҵ��Ԫ*/,
pk_org_v varchar2(20) default '~' null 
/*ҵ��Ԫ�汾*/,
cmaterialid varchar2(20) default '~' not null 
/*�������°汾*/,
cmaterialvid varchar2(20) default '~' null 
/*����*/,
cbomid varchar2(20) default '~' null 
/*����BOM�汾*/,
crtid varchar2(20) default '~' null 
/*����·��*/,
pk_elementsystem varchar2(20) default '~' null 
/*Ҫ����ϵ*/,
pk_factorchart varchar2(20) default '~' null 
/*����Ҫ�ر�*/,
creator varchar2(20) default '~' null 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
modifier varchar2(20) default '~' null 
/*�޸���*/,
modifiedtime char(19) null 
/*�޸�ʱ��*/,
ibillstatus integer null 
/*����״̬*/,
 constraint pk_mapub_coef primary key (ccoprodcoefid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

