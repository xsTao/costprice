/* tablename: ���ϼ۸�� */
create table mapub_materialpricebase (cmaterialpriceid char(20) not null 
/*���ϼ۸��*/,
pk_group varchar(20) null default '~' 
/*����*/,
pk_org varchar(20) null default '~' 
/*ҵ��Ԫ*/,
pk_org_v varchar(20) null default '~' 
/*ҵ��Ԫ�汾*/,
vpricecode varchar(200) null 
/*�۸�����*/,
vpricename varchar(50) null 
/*�۸������*/,
ccrrencyid varchar(20) null default '~' 
/*����*/,
vpricesourcenum varchar(1000) null 
/*�۸���Դ��*/,
dbegindate char(19) null 
/*��Ч����*/,
denddate char(19) null 
/*ʧЧ����*/,
vnote varchar(181) null 
/*��ע*/,
blockingflag char(1) null 
/*����*/,
creator varchar(20) null default '~' 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
modifier varchar(20) null default '~' 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
 constraint pk_terialpricebase primary key (cmaterialpriceid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���ϼ۸����ϸ */
create table mapub_materialpricebase_b (cmaterialprice_bid char(20) not null 
/*�۸��������ϸ*/,
cmaterialpriceid char(20) null 
/*���ϼ۸��*/,
pk_group varchar(20) null default '~' 
/*����*/,
pk_org varchar(20) null default '~' 
/*ҵ��Ԫ*/,
pk_org_v varchar(20) null default '~' 
/*ҵ��Ԫ�汾*/,
cmaterialid varchar(20) null default '~' 
/*����*/,
cmaterialvid varchar(20) null default '~' 
/*���϶�汾*/,
cmeasdocid varchar(20) null default '~' 
/*������λ*/,
cprojectid varchar(20) null default '~' 
/*��Ŀ*/,
cvendorid varchar(20) null default '~' 
/*��Ӧ��*/,
cproductorid varchar(20) null default '~' 
/*��������*/,
ccustomerid varchar(20) null default '~' 
/*�ͻ�*/,
vbfree1 varchar(101) null 
/*���ɸ�������1*/,
vbfree2 varchar(101) null 
/*���ɸ�������2*/,
vbfree3 varchar(101) null 
/*���ɸ�������3*/,
vbfree4 varchar(101) null 
/*���ɸ�������4*/,
vbfree5 varchar(101) null 
/*���ɸ�������5*/,
vbfree6 varchar(101) null 
/*���ɸ�������6*/,
vbfree7 varchar(101) null 
/*���ɸ�������7*/,
vbfree8 varchar(101) null 
/*���ɸ�������8*/,
vbfree9 varchar(101) null 
/*���ɸ�������9*/,
vbfree10 varchar(101) null 
/*���ɸ�������10*/,
vpricesourcenum varchar(1000) null 
/*�۸���Դ��*/,
vpricesourcerealnum varchar(500) null 
/*ʵ�ʼ۸���Դ��*/,
nprice numeric(28,8) null 
/*����*/,
 constraint pk_rialpricebase_b primary key (cmaterialprice_bid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ����ϵ�� */
create table cm_allocfac (callocfacid char(20) not null 
/*����ϵ��*/,
vname6 varchar(300) null 
/*����*/,
vname5 varchar(300) null 
/*����*/,
vname4 varchar(300) null 
/*����*/,
vname3 varchar(300) null 
/*����*/,
vname2 varchar(300) null 
/*����*/,
vname varchar(300) null 
/*����*/,
pk_group varchar(20) null default '~' 
/*��������*/,
pk_org varchar(20) null default '~' 
/*����*/,
pk_org_v varchar(20) null default '~' 
/*����*/,
vcode varchar(50) null 
/*����*/,
vnote varchar(181) null 
/*��ע*/,
creator varchar(20) null default '~' 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
modifier varchar(20) null default '~' 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
ialloctype integer null 
/*��������*/,
 constraint pk_cm_allocfac primary key (callocfacid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ����ϵ����ϸ */
create table cm_allocfac_b (callocfacid char(20) null 
/*��������*/,
callocfac_bid char(20) not null 
/*�ӱ�����*/,
ccostcenterid varchar(20) null default '~' 
/*�ɱ�����*/,
cmaterialid varchar(20) null default '~' 
/*��Ʒ*/,
cmaterialvid varchar(20) null default '~' 
/*����*/,
cmarcostclassid varchar(20) null default '~' 
/*�ɱ�����*/,
cactivityid varchar(20) null default '~' 
/*��ҵ*/,
nfactor numeric(28,8) null 
/*ϵ��*/,
ccostobjectid varchar(20) null default '~' 
/*�ɱ�����*/,
pk_group varchar(20) null default '~' 
/*��������*/,
pk_org varchar(20) null default '~' 
/*�������°汾*/,
pk_org_v varchar(20) null default '~' 
/*����*/,
cprojectid varchar(20) null default '~' 
/*��Ŀ*/,
cproductorid varchar(20) null default '~' 
/*��������*/,
cvendorid varchar(20) null default '~' 
/*��Ӧ��*/,
ccustomerid varchar(20) null default '~' 
/*�ͻ�*/,
vbfree1 varchar(101) null 
/*���ɸ�������1*/,
vbfree2 varchar(101) null 
/*���ɸ�������2*/,
vbfree3 varchar(101) null 
/*���ɸ�������3*/,
vbfree4 varchar(101) null 
/*���ɸ�������4*/,
vbfree5 varchar(101) null 
/*���ɸ�������5*/,
vbfree6 varchar(101) null 
/*���ɸ�������6*/,
vbfree7 varchar(101) null 
/*���ɸ�������7*/,
vbfree8 varchar(101) null 
/*���ɸ�������8*/,
vbfree9 varchar(101) null 
/*���ɸ�������9*/,
vbfree10 varchar(101) null 
/*���ɸ�������10*/,
cstuffid varchar(20) null default '~' 
/*����*/,
cmarbaseclassid varchar(20) null default '~' 
/*��������*/,
 constraint pk_cm_allocfac_b primary key (callocfac_bid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���ϼ۸���Դ */
create table mapub_materialpricesources (cpricesourcesid char(20) not null 
/*���ϼ۸���Դ*/,
 constraint pk_ialpricesources primary key (cpricesourcesid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���ϼ۸���Դ��ϸ */
create table mapub_materialpricesources_b (cpricesources_bid char(20) not null 
/*���ϼ۸���Դ��ϸ*/,
cpricesourcesid char(20) null 
/*���ϼ۸���Դ*/,
vpricesource integer null 
/*�۸���Դ*/,
pk_org varchar(20) null default '~' 
/*ҵ��Ԫ*/,
corgcode varchar(50) null 
/*ҵ��Ԫ����*/,
 constraint pk_lpricesources_b primary key (cpricesources_bid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ???��ȡ�� */
create table mapub_materialpullprice (cmaterialpullpriceid char(20) not null 
/*����ȡ��*/,
cperiod varchar(20) null default '~' 
/*����ڼ䷽��*/,
costtype varchar(20) null default '~' 
/*�ɱ�����*/,
cperiod1 varchar(20) null default '~' 
/*����ڼ�*/,
cbegindate varchar(20) null default '~' 
/*ƽ���ɹ���⿪ʼ�ڼ�*/,
cenddate varchar(20) null default '~' 
/*ƽ���ɹ�����ֹ�ڼ�*/,
pk_org varchar(50) null 
/*ҵ��Ԫ*/,
pk_group varchar(50) null 
/*����*/,
 constraint pk_terialpullprice primary key (cmaterialpullpriceid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ����ȡ����ϸ */
create table mapub_materialpullprice_b (cmaterialpullprice_bid char(20) not null 
/*����ȡ����ϸ*/,
cmaterialpullpriceid char(20) null 
/*����ȡ��*/,
 constraint pk_rialpullprice_b primary key (cmaterialpullprice_bid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ȡ�۴�����־ */
create table mapub_acquirepricelog (pk_log char(20) not null 
/*ȡ�۴�����־*/,
vacquiretype varchar(50) not null 
/*ȡ������*/,
pk_group varchar(20) null default '~' 
/*ȡ�ۼ���*/,
pk_org varchar(20) null default '~' 
/*ȡ�۹���*/,
ccosttypeid varchar(20) null default '~' 
/*�ɱ�����*/,
cperiod varchar(20) null default '~' 
/*����ڼ�*/,
begindate varchar(20) null default '~' 
/*��ʼ�ڼ�*/,
enddate varchar(20) null default '~' 
/*��ֹ�ڼ�*/,
isourcetype integer null 
/*��Դ����*/,
prioritylevel varchar(50) null 
/*���ȼ�*/,
cmaterialpriceid varchar(20) null default '~' 
/*�۸��*/,
pk_currtype varchar(20) null default '~' 
/*��Դ����*/,
finstoragetype integer null 
/*�������*/,
ccostcenterid varchar(20) null default '~' 
/*����Ʒ�ɱ�����*/,
cmaterialid varchar(20) null default '~' 
/*����Ʒ*/,
cprojectid varchar(20) null default '~' 
/*��Ŀ*/,
cvendorid varchar(20) null default '~' 
/*��Ӧ��*/,
cproductorid varchar(20) null default '~' 
/*��������*/,
ccustomerid varchar(20) null default '~' 
/*�ͻ�*/,
vfree1 varchar(101) null 
/*���ɸ�������1*/,
vfree2 varchar(101) null 
/*���ɸ�������2*/,
vfree3 varchar(101) null 
/*���ɸ�������3*/,
vfree4 varchar(101) null 
/*���ɸ�������4*/,
vfree5 varchar(101) null 
/*���ɸ�������5*/,
vfree6 varchar(101) null 
/*���ɸ�������6*/,
vfree7 varchar(101) null 
/*���ɸ�������7*/,
vfree8 varchar(101) null 
/*���ɸ�������8*/,
vfree9 varchar(101) null 
/*���ɸ�������9*/,
vfree10 varchar(101) null 
/*���ɸ�������10*/,
cbcostcenterid varchar(20) null default '~' 
/*���ϳɱ�����*/,
cbmaterialid varchar(20) null default '~' 
/*����*/,
cbprojectid varchar(20) null default '~' 
/*��Ŀ*/,
cbvendorid varchar(20) null default '~' 
/*��Ӧ��*/,
cbproductorid varchar(20) null default '~' 
/*��������*/,
cbcustomerid varchar(20) null default '~' 
/*�ͻ�*/,
vbfree1 varchar(50) null 
/*���ɸ�������1*/,
vbfree2 varchar(50) null 
/*���ɸ�������2*/,
vbfree3 varchar(50) null 
/*���ɸ�������3*/,
vbfree4 varchar(50) null 
/*���ɸ�������4*/,
vbfree5 varchar(50) null 
/*���ɸ�������5*/,
vbfree6 varchar(50) null 
/*���ɸ�������6*/,
vbfree7 varchar(50) null 
/*���ɸ�������7*/,
vbfree8 varchar(50) null 
/*���ɸ�������8*/,
vbfree9 varchar(50) null 
/*���ɸ�������9*/,
vbfree10 varchar(50) null 
/*���ɸ�������10*/,
celementid varchar(20) null default '~' 
/*����Ҫ��*/,
cactivityid varchar(20) null default '~' 
/*��ҵ*/,
pk_group_actual varchar(20) null default '~' 
/*ʵ��ȡ�ۼ���*/,
pk_org_actual varchar(20) null default '~' 
/*ʵ��ȡ�۹���*/,
isourcetype_actual integer null 
/*ʵ��ȡ����Դ*/,
cmaterialpriceid_actual varchar(20) null default '~' 
/*ʵ�ʼ۸��*/,
pk_currtype_actual varchar(20) null default '~' 
/*ʵ�ʱ���*/,
nprice numeric(28,8) null 
/*�۸�*/,
errorinfo varchar(500) null 
/*����ԭ��*/,
solveplan varchar(500) null 
/*�������*/,
 constraint pk_acquirepricelog primary key (pk_log),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �ɱ����� */
create table cm_driver (vname varchar(300) null 
/*����*/,
vname2 varchar(300) null 
/*����*/,
vname3 varchar(300) null 
/*����*/,
vname4 varchar(300) null 
/*����*/,
cdriverid char(20) not null 
/*�ɱ���������*/,
vname6 varchar(300) null 
/*����*/,
vname5 varchar(300) null 
/*����*/,
pk_group varchar(20) null default '~' 
/*��������*/,
pk_org varchar(20) null default '~' 
/*�����֯����*/,
pk_org_v varchar(20) null default '~' 
/*�����֯�汾����*/,
vcode varchar(50) null 
/*����*/,
creator varchar(20) null default '~' 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
modifier varchar(20) null default '~' 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
vformulavalue varchar(750) null 
/*��ʽ*/,
vjavapath varchar(50) null 
/*�Զ���JAVA��*/,
vformulacode varchar(500) null 
/*��ʽcode*/,
vnote varchar(181) null 
/*��ע*/,
vformulavalue2 varchar(500) null 
/*��ʽ2*/,
vformulavalue3 varchar(500) null 
/*��ʽ3*/,
vformulavalue4 varchar(500) null 
/*��ʽ4*/,
vformulavalue5 varchar(500) null 
/*��ʽ5*/,
vformulavalue6 varchar(500) null 
/*��ʽ6*/,
 constraint pk_cm_driver primary key (cdriverid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �ɱ����� */
create table mapub_costtype (ccosttypeid char(20) not null 
/*�ɱ���������*/,
vcosttypename varchar(300) null 
/*�ɱ���������*/,
vcosttypename2 varchar(300) null 
/*�ɱ���������2*/,
vcosttypename3 varchar(300) null 
/*�ɱ���������3*/,
vcosttypename4 varchar(300) null 
/*�ɱ���������4*/,
vcosttypename5 varchar(300) null 
/*�ɱ���������5*/,
vcosttypename6 varchar(300) null 
/*�ɱ���������6*/,
pk_group varchar(20) null default '~' 
/*����*/,
pk_org varchar(20) null default '~' 
/*����*/,
vcosttypecode varchar(40) null 
/*�ɱ����ͱ���*/,
bscrapfactor char(1) null 
/*�Ƿ��Ƿ�Ʒϵ��*/,
bshrinkfactor char(1) null 
/*�Ƿ������ϵ��*/,
pk_elementsystem varchar(20) null 
/*Ҫ����ϵ*/,
pk_factorchart varchar(20) null 
/*����Ҫ�ر�*/,
pk_materialdocview varchar(20) null 
/*Ҫ�������϶��ձ�*/,
pk_activitydocview varchar(20) null 
/*Ҫ������ҵ���ձ�*/,
vmaterialpricesourcenum varchar(500) null 
/*���ϼ۸���Դ��*/,
vcostpricesourcenum varchar(500) null 
/*���ü۸���Դ��*/,
cbeginmonth varchar(50) null 
/*��Ч�ڼ�*/,
cendmonth varchar(50) null 
/*ʧЧ�ڼ�*/,
bdefault char(1) null 
/*�Ƿ�Ĭ��*/,
bcompute char(1) null 
/*�Ƿ��Ѽ���*/,
bcreatetable char(1) null 
/*�Ƿ������ɿ�*/,
creator varchar(20) null default '~' 
/*������*/,
modifier varchar(20) null default '~' 
/*�޸���*/,
creationtime char(19) null 
/*����ʱ��*/,
modifiedtime char(19) null 
/*�޸�ʱ��*/,
vnote varchar(1024) null 
/*��ע*/,
 constraint pk_mapub_costtype primary key (ccosttypeid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ����Ʒ���ϵ����ϸ */
create table mapub_coprodcoef_b (ccoprodcoefbid char(20) not null 
/*����Ʒ���ϵ����ϸ*/,
ccoprodcoefid char(20) null 
/*����Ʒ���ϵ��*/,
pk_group varchar(20) null default '~' 
/*����*/,
pk_org varchar(20) null default '~' 
/*ҵ��Ԫ*/,
pk_org_v varchar(20) null default '~' 
/*ҵ��Ԫ�汾*/,
cmaterialid varchar(20) not null default '~' 
/*�������°汾*/,
cmaterialvid varchar(20) null default '~' 
/*����*/,
iproducttype integer not null 
/*��Ʒ����*/,
ccostcenterid varchar(20) null default '~' 
/*�ɱ�����*/,
ccosttypeid varchar(20) null default '~' 
/*�ɱ�����*/,
celementid varchar(20) null default '~' 
/*����Ҫ�ر���*/,
nrelcoefficient numeric(28,8) not null 
/*���ϵ��*/,
 constraint pk_mapub_coef_b primary key (ccoprodcoefbid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ����Ʒ���ϵ�� */
create table mapub_coprodcoef (ccoprodcoefid char(20) not null 
/*����Ʒ���ϵ��*/,
pk_group varchar(20) null default '~' 
/*����*/,
pk_org varchar(20) not null default '~' 
/*ҵ��Ԫ*/,
pk_org_v varchar(20) null default '~' 
/*ҵ��Ԫ�汾*/,
cmaterialid varchar(20) not null default '~' 
/*�������°汾*/,
cmaterialvid varchar(20) null default '~' 
/*����*/,
cbomid varchar(20) null default '~' 
/*����BOM�汾*/,
crtid varchar(20) null default '~' 
/*����·��*/,
pk_elementsystem varchar(20) null default '~' 
/*Ҫ����ϵ*/,
pk_factorchart varchar(20) null default '~' 
/*����Ҫ�ر�*/,
creator varchar(20) null default '~' 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
modifier varchar(20) null default '~' 
/*�޸���*/,
modifiedtime char(19) null 
/*�޸�ʱ��*/,
ibillstatus integer null 
/*����״̬*/,
 constraint pk_mapub_coef primary key (ccoprodcoefid),
 ts char(19) null,
dr smallint null default 0
)
;

