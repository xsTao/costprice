/* tablename: ���ϼ۸�� */
create table mapub_materialpricebase (
cmaterialpriceid nchar(20) not null 
/*���ϼ۸��*/,
pk_group nvarchar(20) null default '~' 
/*����*/,
pk_org nvarchar(20) null default '~' 
/*ҵ��Ԫ*/,
pk_org_v nvarchar(20) null default '~' 
/*ҵ��Ԫ�汾*/,
vpricecode nvarchar(200) null 
/*�۸�����*/,
vpricename nvarchar(50) null 
/*�۸������*/,
ccrrencyid nvarchar(20) null default '~' 
/*����*/,
vpricesourcenum nvarchar(1000) null 
/*�۸���Դ��*/,
dbegindate nchar(19) null 
/*��Ч����*/,
denddate nchar(19) null 
/*ʧЧ����*/,
vnote nvarchar(181) null 
/*��ע*/,
blockingflag nchar(1) null 
/*����*/,
creator nvarchar(20) null default '~' 
/*������*/,
creationtime nchar(19) null 
/*����ʱ��*/,
modifier nvarchar(20) null default '~' 
/*����޸���*/,
modifiedtime nchar(19) null 
/*����޸�ʱ��*/,
 constraint pk_terialpricebase primary key (cmaterialpriceid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���ϼ۸����ϸ */
create table mapub_materialpricebase_b (
cmaterialprice_bid nchar(20) not null 
/*�۸��������ϸ*/,
cmaterialpriceid nchar(20) null 
/*���ϼ۸��*/,
pk_group nvarchar(20) null default '~' 
/*����*/,
pk_org nvarchar(20) null default '~' 
/*ҵ��Ԫ*/,
pk_org_v nvarchar(20) null default '~' 
/*ҵ��Ԫ�汾*/,
cmaterialid nvarchar(20) null default '~' 
/*����*/,
cmaterialvid nvarchar(20) null default '~' 
/*���϶�汾*/,
cmeasdocid nvarchar(20) null default '~' 
/*������λ*/,
cprojectid nvarchar(20) null default '~' 
/*��Ŀ*/,
cvendorid nvarchar(20) null default '~' 
/*��Ӧ��*/,
cproductorid nvarchar(20) null default '~' 
/*��������*/,
ccustomerid nvarchar(20) null default '~' 
/*�ͻ�*/,
vbfree1 nvarchar(101) null 
/*���ɸ�������1*/,
vbfree2 nvarchar(101) null 
/*���ɸ�������2*/,
vbfree3 nvarchar(101) null 
/*���ɸ�������3*/,
vbfree4 nvarchar(101) null 
/*���ɸ�������4*/,
vbfree5 nvarchar(101) null 
/*���ɸ�������5*/,
vbfree6 nvarchar(101) null 
/*���ɸ�������6*/,
vbfree7 nvarchar(101) null 
/*���ɸ�������7*/,
vbfree8 nvarchar(101) null 
/*���ɸ�������8*/,
vbfree9 nvarchar(101) null 
/*���ɸ�������9*/,
vbfree10 nvarchar(101) null 
/*���ɸ�������10*/,
vpricesourcenum nvarchar(1000) null 
/*�۸���Դ��*/,
vpricesourcerealnum nvarchar(500) null 
/*ʵ�ʼ۸���Դ��*/,
nprice numeric(28,8) null 
/*����*/,
 constraint pk_rialpricebase_b primary key (cmaterialprice_bid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ����ϵ�� */
create table cm_allocfac (
callocfacid nchar(20) not null 
/*����ϵ��*/,
vname6 nvarchar(300) null 
/*����*/,
vname5 nvarchar(300) null 
/*����*/,
vname4 nvarchar(300) null 
/*����*/,
vname3 nvarchar(300) null 
/*����*/,
vname2 nvarchar(300) null 
/*����*/,
vname nvarchar(300) null 
/*����*/,
pk_group nvarchar(20) null default '~' 
/*��������*/,
pk_org nvarchar(20) null default '~' 
/*����*/,
pk_org_v nvarchar(20) null default '~' 
/*����*/,
vcode nvarchar(50) null 
/*����*/,
vnote nvarchar(181) null 
/*��ע*/,
creator nvarchar(20) null default '~' 
/*������*/,
creationtime nchar(19) null 
/*����ʱ��*/,
modifier nvarchar(20) null default '~' 
/*����޸���*/,
modifiedtime nchar(19) null 
/*����޸�ʱ��*/,
ialloctype int null 
/*��������*/,
 constraint pk_cm_allocfac primary key (callocfacid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ����ϵ����ϸ */
create table cm_allocfac_b (
callocfacid nchar(20) null 
/*��������*/,
callocfac_bid nchar(20) not null 
/*�ӱ�����*/,
ccostcenterid nvarchar(20) null default '~' 
/*�ɱ�����*/,
cmaterialid nvarchar(20) null default '~' 
/*��Ʒ*/,
cmaterialvid nvarchar(20) null default '~' 
/*����*/,
cmarcostclassid nvarchar(20) null default '~' 
/*�ɱ�����*/,
cactivityid nvarchar(20) null default '~' 
/*��ҵ*/,
nfactor numeric(28,8) null 
/*ϵ��*/,
ccostobjectid nvarchar(20) null default '~' 
/*�ɱ�����*/,
pk_group nvarchar(20) null default '~' 
/*��������*/,
pk_org nvarchar(20) null default '~' 
/*�������°汾*/,
pk_org_v nvarchar(20) null default '~' 
/*����*/,
cprojectid nvarchar(20) null default '~' 
/*��Ŀ*/,
cproductorid nvarchar(20) null default '~' 
/*��������*/,
cvendorid nvarchar(20) null default '~' 
/*��Ӧ��*/,
ccustomerid nvarchar(20) null default '~' 
/*�ͻ�*/,
vbfree1 nvarchar(101) null 
/*���ɸ�������1*/,
vbfree2 nvarchar(101) null 
/*���ɸ�������2*/,
vbfree3 nvarchar(101) null 
/*���ɸ�������3*/,
vbfree4 nvarchar(101) null 
/*���ɸ�������4*/,
vbfree5 nvarchar(101) null 
/*���ɸ�������5*/,
vbfree6 nvarchar(101) null 
/*���ɸ�������6*/,
vbfree7 nvarchar(101) null 
/*���ɸ�������7*/,
vbfree8 nvarchar(101) null 
/*���ɸ�������8*/,
vbfree9 nvarchar(101) null 
/*���ɸ�������9*/,
vbfree10 nvarchar(101) null 
/*���ɸ�������10*/,
cstuffid nvarchar(20) null default '~' 
/*����*/,
cmarbaseclassid nvarchar(20) null default '~' 
/*��������*/,
 constraint pk_cm_allocfac_b primary key (callocfac_bid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���ϼ۸���Դ */
create table mapub_materialpricesources (
cpricesourcesid nchar(20) not null 
/*���ϼ۸���Դ*/,
 constraint pk_ialpricesources primary key (cpricesourcesid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���ϼ۸���Դ��ϸ */
create table mapub_materialpricesources_b (
cpricesources_bid nchar(20) not null 
/*���ϼ۸���Դ��ϸ*/,
cpricesourcesid nchar(20) null 
/*���ϼ۸���Դ*/,
vpricesource int null 
/*�۸���Դ*/,
pk_org nvarchar(20) null default '~' 
/*ҵ��Ԫ*/,
corgcode nvarchar(50) null 
/*ҵ��Ԫ����*/,
 constraint pk_lpricesources_b primary key (cpricesources_bid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ???��ȡ�� */
create table mapub_materialpullprice (
cmaterialpullpriceid nchar(20) not null 
/*����ȡ��*/,
cperiod nvarchar(20) null default '~' 
/*����ڼ䷽��*/,
costtype nvarchar(20) null default '~' 
/*�ɱ�����*/,
cperiod1 nvarchar(20) null default '~' 
/*����ڼ�*/,
cbegindate nvarchar(20) null default '~' 
/*ƽ���ɹ���⿪ʼ�ڼ�*/,
cenddate nvarchar(20) null default '~' 
/*ƽ���ɹ�����ֹ�ڼ�*/,
pk_org nvarchar(50) null 
/*ҵ��Ԫ*/,
pk_group nvarchar(50) null 
/*����*/,
 constraint pk_terialpullprice primary key (cmaterialpullpriceid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ����ȡ����ϸ */
create table mapub_materialpullprice_b (
cmaterialpullprice_bid nchar(20) not null 
/*����ȡ����ϸ*/,
cmaterialpullpriceid nchar(20) null 
/*����ȡ��*/,
 constraint pk_rialpullprice_b primary key (cmaterialpullprice_bid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ȡ�۴�����־ */
create table mapub_acquirepricelog (
pk_log nchar(20) not null 
/*ȡ�۴�����־*/,
vacquiretype nvarchar(50) not null 
/*ȡ������*/,
pk_group nvarchar(20) null default '~' 
/*ȡ�ۼ���*/,
pk_org nvarchar(20) null default '~' 
/*ȡ�۹���*/,
ccosttypeid nvarchar(20) null default '~' 
/*�ɱ�����*/,
cperiod nvarchar(20) null default '~' 
/*����ڼ�*/,
begindate nvarchar(20) null default '~' 
/*��ʼ�ڼ�*/,
enddate nvarchar(20) null default '~' 
/*��ֹ�ڼ�*/,
isourcetype int null 
/*��Դ����*/,
prioritylevel nvarchar(50) null 
/*���ȼ�*/,
cmaterialpriceid nvarchar(20) null default '~' 
/*�۸��*/,
pk_currtype nvarchar(20) null default '~' 
/*��Դ����*/,
finstoragetype int null 
/*�������*/,
ccostcenterid nvarchar(20) null default '~' 
/*����Ʒ�ɱ�����*/,
cmaterialid nvarchar(20) null default '~' 
/*����Ʒ*/,
cprojectid nvarchar(20) null default '~' 
/*��Ŀ*/,
cvendorid nvarchar(20) null default '~' 
/*��Ӧ��*/,
cproductorid nvarchar(20) null default '~' 
/*��������*/,
ccustomerid nvarchar(20) null default '~' 
/*�ͻ�*/,
vfree1 nvarchar(101) null 
/*���ɸ�������1*/,
vfree2 nvarchar(101) null 
/*���ɸ�������2*/,
vfree3 nvarchar(101) null 
/*���ɸ�������3*/,
vfree4 nvarchar(101) null 
/*���ɸ�������4*/,
vfree5 nvarchar(101) null 
/*���ɸ�������5*/,
vfree6 nvarchar(101) null 
/*���ɸ�������6*/,
vfree7 nvarchar(101) null 
/*���ɸ�������7*/,
vfree8 nvarchar(101) null 
/*���ɸ�������8*/,
vfree9 nvarchar(101) null 
/*���ɸ�������9*/,
vfree10 nvarchar(101) null 
/*���ɸ�������10*/,
cbcostcenterid nvarchar(20) null default '~' 
/*���ϳɱ�����*/,
cbmaterialid nvarchar(20) null default '~' 
/*����*/,
cbprojectid nvarchar(20) null default '~' 
/*��Ŀ*/,
cbvendorid nvarchar(20) null default '~' 
/*��Ӧ��*/,
cbproductorid nvarchar(20) null default '~' 
/*��������*/,
cbcustomerid nvarchar(20) null default '~' 
/*�ͻ�*/,
vbfree1 nvarchar(50) null 
/*���ɸ�������1*/,
vbfree2 nvarchar(50) null 
/*���ɸ�������2*/,
vbfree3 nvarchar(50) null 
/*���ɸ�������3*/,
vbfree4 nvarchar(50) null 
/*���ɸ�������4*/,
vbfree5 nvarchar(50) null 
/*���ɸ�������5*/,
vbfree6 nvarchar(50) null 
/*���ɸ�������6*/,
vbfree7 nvarchar(50) null 
/*���ɸ�������7*/,
vbfree8 nvarchar(50) null 
/*���ɸ�������8*/,
vbfree9 nvarchar(50) null 
/*���ɸ�������9*/,
vbfree10 nvarchar(50) null 
/*���ɸ�������10*/,
celementid nvarchar(20) null default '~' 
/*����Ҫ��*/,
cactivityid nvarchar(20) null default '~' 
/*��ҵ*/,
pk_group_actual nvarchar(20) null default '~' 
/*ʵ��ȡ�ۼ���*/,
pk_org_actual nvarchar(20) null default '~' 
/*ʵ��ȡ�۹���*/,
isourcetype_actual int null 
/*ʵ��ȡ����Դ*/,
cmaterialpriceid_actual nvarchar(20) null default '~' 
/*ʵ�ʼ۸��*/,
pk_currtype_actual nvarchar(20) null default '~' 
/*ʵ�ʱ���*/,
nprice numeric(28,8) null 
/*�۸�*/,
errorinfo nvarchar(500) null 
/*����ԭ��*/,
solveplan nvarchar(500) null 
/*�������*/,
 constraint pk_acquirepricelog primary key (pk_log),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �ɱ����� */
create table cm_driver (
vname nvarchar(300) null 
/*����*/,
vname2 nvarchar(300) null 
/*����*/,
vname3 nvarchar(300) null 
/*����*/,
vname4 nvarchar(300) null 
/*����*/,
cdriverid nchar(20) not null 
/*�ɱ���������*/,
vname6 nvarchar(300) null 
/*����*/,
vname5 nvarchar(300) null 
/*����*/,
pk_group nvarchar(20) null default '~' 
/*��������*/,
pk_org nvarchar(20) null default '~' 
/*�����֯����*/,
pk_org_v nvarchar(20) null default '~' 
/*�����֯�汾����*/,
vcode nvarchar(50) null 
/*����*/,
creator nvarchar(20) null default '~' 
/*������*/,
creationtime nchar(19) null 
/*����ʱ��*/,
modifier nvarchar(20) null default '~' 
/*����޸���*/,
modifiedtime nchar(19) null 
/*����޸�ʱ��*/,
vformulavalue nvarchar(750) null 
/*��ʽ*/,
vjavapath nvarchar(50) null 
/*�Զ���JAVA��*/,
vformulacode nvarchar(500) null 
/*��ʽcode*/,
vnote nvarchar(181) null 
/*��ע*/,
vformulavalue2 nvarchar(500) null 
/*��ʽ2*/,
vformulavalue3 nvarchar(500) null 
/*��ʽ3*/,
vformulavalue4 nvarchar(500) null 
/*��ʽ4*/,
vformulavalue5 nvarchar(500) null 
/*��ʽ5*/,
vformulavalue6 nvarchar(500) null 
/*��ʽ6*/,
 constraint pk_cm_driver primary key (cdriverid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �ɱ����� */
create table mapub_costtype (
ccosttypeid nchar(20) not null 
/*�ɱ���������*/,
vcosttypename nvarchar(300) null 
/*�ɱ���������*/,
vcosttypename2 nvarchar(300) null 
/*�ɱ���������2*/,
vcosttypename3 nvarchar(300) null 
/*�ɱ���������3*/,
vcosttypename4 nvarchar(300) null 
/*�ɱ���������4*/,
vcosttypename5 nvarchar(300) null 
/*�ɱ���������5*/,
vcosttypename6 nvarchar(300) null 
/*�ɱ���������6*/,
pk_group nvarchar(20) null default '~' 
/*����*/,
pk_org nvarchar(20) null default '~' 
/*����*/,
vcosttypecode nvarchar(40) null 
/*�ɱ����ͱ���*/,
bscrapfactor nchar(1) null 
/*�Ƿ��Ƿ�Ʒϵ��*/,
bshrinkfactor nchar(1) null 
/*�Ƿ������ϵ��*/,
pk_elementsystem nvarchar(20) null 
/*Ҫ����ϵ*/,
pk_factorchart nvarchar(20) null 
/*����Ҫ�ر�*/,
pk_materialdocview nvarchar(20) null 
/*Ҫ�������϶��ձ�*/,
pk_activitydocview nvarchar(20) null 
/*Ҫ������ҵ���ձ�*/,
vmaterialpricesourcenum nvarchar(500) null 
/*���ϼ۸���Դ��*/,
vcostpricesourcenum nvarchar(500) null 
/*���ü۸���Դ��*/,
cbeginmonth nvarchar(50) null 
/*��Ч�ڼ�*/,
cendmonth nvarchar(50) null 
/*ʧЧ�ڼ�*/,
bdefault nchar(1) null 
/*�Ƿ�Ĭ��*/,
bcompute nchar(1) null 
/*�Ƿ��Ѽ���*/,
bcreatetable nchar(1) null 
/*�Ƿ������ɿ�*/,
creator nvarchar(20) null default '~' 
/*������*/,
modifier nvarchar(20) null default '~' 
/*�޸���*/,
creationtime nchar(19) null 
/*����ʱ��*/,
modifiedtime nchar(19) null 
/*�޸�ʱ��*/,
vnote nvarchar(1024) null 
/*��ע*/,
 constraint pk_mapub_costtype primary key (ccosttypeid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ����Ʒ���ϵ����ϸ */
create table mapub_coprodcoef_b (
ccoprodcoefbid nchar(20) not null 
/*����Ʒ���ϵ����ϸ*/,
ccoprodcoefid nchar(20) null 
/*����Ʒ���ϵ��*/,
pk_group nvarchar(20) null default '~' 
/*����*/,
pk_org nvarchar(20) null default '~' 
/*ҵ��Ԫ*/,
pk_org_v nvarchar(20) null default '~' 
/*ҵ��Ԫ�汾*/,
cmaterialid nvarchar(20) not null default '~' 
/*�������°汾*/,
cmaterialvid nvarchar(20) null default '~' 
/*����*/,
iproducttype int not null 
/*��Ʒ����*/,
ccostcenterid nvarchar(20) null default '~' 
/*�ɱ�����*/,
ccosttypeid nvarchar(20) null default '~' 
/*�ɱ�����*/,
celementid nvarchar(20) null default '~' 
/*����Ҫ�ر���*/,
nrelcoefficient numeric(28,8) not null 
/*���ϵ��*/,
 constraint pk_mapub_coef_b primary key (ccoprodcoefbid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ����Ʒ���ϵ�� */
create table mapub_coprodcoef (
ccoprodcoefid nchar(20) not null 
/*����Ʒ���ϵ��*/,
pk_group nvarchar(20) null default '~' 
/*����*/,
pk_org nvarchar(20) not null default '~' 
/*ҵ��Ԫ*/,
pk_org_v nvarchar(20) null default '~' 
/*ҵ��Ԫ�汾*/,
cmaterialid nvarchar(20) not null default '~' 
/*�������°汾*/,
cmaterialvid nvarchar(20) null default '~' 
/*����*/,
cbomid nvarchar(20) null default '~' 
/*����BOM�汾*/,
crtid nvarchar(20) null default '~' 
/*����·��*/,
pk_elementsystem nvarchar(20) null default '~' 
/*Ҫ����ϵ*/,
pk_factorchart nvarchar(20) null default '~' 
/*����Ҫ�ر�*/,
creator nvarchar(20) null default '~' 
/*������*/,
creationtime nchar(19) null 
/*����ʱ��*/,
modifier nvarchar(20) null default '~' 
/*�޸���*/,
modifiedtime nchar(19) null 
/*�޸�ʱ��*/,
ibillstatus int null 
/*����״̬*/,
 constraint pk_mapub_coef primary key (ccoprodcoefid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

