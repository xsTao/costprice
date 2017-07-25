/* tablename: 物料价格库 */
create table mapub_materialpricebase (
cmaterialpriceid nchar(20) not null 
/*物料价格库*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
pk_org nvarchar(20) null default '~' 
/*业务单元*/,
pk_org_v nvarchar(20) null default '~' 
/*业务单元版本*/,
vpricecode nvarchar(200) null 
/*价格库编码*/,
vpricename nvarchar(50) null 
/*价格库名称*/,
ccrrencyid nvarchar(20) null default '~' 
/*币种*/,
vpricesourcenum nvarchar(1000) null 
/*价格来源数*/,
dbegindate nchar(19) null 
/*生效日期*/,
denddate nchar(19) null 
/*失效日期*/,
vnote nvarchar(181) null 
/*备注*/,
blockingflag nchar(1) null 
/*锁定*/,
creator nvarchar(20) null default '~' 
/*创建人*/,
creationtime nchar(19) null 
/*创建时间*/,
modifier nvarchar(20) null default '~' 
/*最后修改人*/,
modifiedtime nchar(19) null 
/*最后修改时间*/,
 constraint pk_terialpricebase primary key (cmaterialpriceid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 物料价格库明细 */
create table mapub_materialpricebase_b (
cmaterialprice_bid nchar(20) not null 
/*价格库物料明细*/,
cmaterialpriceid nchar(20) null 
/*物料价格库*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
pk_org nvarchar(20) null default '~' 
/*业务单元*/,
pk_org_v nvarchar(20) null default '~' 
/*业务单元版本*/,
cmaterialid nvarchar(20) null default '~' 
/*物料*/,
cmaterialvid nvarchar(20) null default '~' 
/*物料多版本*/,
cmeasdocid nvarchar(20) null default '~' 
/*计量单位*/,
cprojectid nvarchar(20) null default '~' 
/*项目*/,
cvendorid nvarchar(20) null default '~' 
/*供应商*/,
cproductorid nvarchar(20) null default '~' 
/*生产厂商*/,
ccustomerid nvarchar(20) null default '~' 
/*客户*/,
vbfree1 nvarchar(101) null 
/*自由辅助属性1*/,
vbfree2 nvarchar(101) null 
/*自由辅助属性2*/,
vbfree3 nvarchar(101) null 
/*自由辅助属性3*/,
vbfree4 nvarchar(101) null 
/*自由辅助属性4*/,
vbfree5 nvarchar(101) null 
/*自由辅助属性5*/,
vbfree6 nvarchar(101) null 
/*自由辅助属性6*/,
vbfree7 nvarchar(101) null 
/*自由辅助属性7*/,
vbfree8 nvarchar(101) null 
/*自由辅助属性8*/,
vbfree9 nvarchar(101) null 
/*自由辅助属性9*/,
vbfree10 nvarchar(101) null 
/*自由辅助属性10*/,
vpricesourcenum nvarchar(1000) null 
/*价格来源数*/,
vpricesourcerealnum nvarchar(500) null 
/*实际价格来源数*/,
nprice numeric(28,8) null 
/*单价*/,
 constraint pk_rialpricebase_b primary key (cmaterialprice_bid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 分配系数 */
create table cm_allocfac (
callocfacid nchar(20) not null 
/*分配系数*/,
vname6 nvarchar(300) null 
/*名称*/,
vname5 nvarchar(300) null 
/*名称*/,
vname4 nvarchar(300) null 
/*名称*/,
vname3 nvarchar(300) null 
/*名称*/,
vname2 nvarchar(300) null 
/*名称*/,
vname nvarchar(300) null 
/*名称*/,
pk_group nvarchar(20) null default '~' 
/*集团主键*/,
pk_org nvarchar(20) null default '~' 
/*工厂*/,
pk_org_v nvarchar(20) null default '~' 
/*工厂*/,
vcode nvarchar(50) null 
/*编码*/,
vnote nvarchar(181) null 
/*备注*/,
creator nvarchar(20) null default '~' 
/*创建人*/,
creationtime nchar(19) null 
/*创建时间*/,
modifier nvarchar(20) null default '~' 
/*最后修改人*/,
modifiedtime nchar(19) null 
/*最后修改时间*/,
ialloctype int null 
/*分配内容*/,
 constraint pk_cm_allocfac primary key (callocfacid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 分配系数明细 */
create table cm_allocfac_b (
callocfacid nchar(20) null 
/*主表主键*/,
callocfac_bid nchar(20) not null 
/*子表主键*/,
ccostcenterid nvarchar(20) null default '~' 
/*成本中心*/,
cmaterialid nvarchar(20) null default '~' 
/*产品*/,
cmaterialvid nvarchar(20) null default '~' 
/*物料*/,
cmarcostclassid nvarchar(20) null default '~' 
/*成本分类*/,
cactivityid nvarchar(20) null default '~' 
/*作业*/,
nfactor numeric(28,8) null 
/*系数*/,
ccostobjectid nvarchar(20) null default '~' 
/*成本对象*/,
pk_group nvarchar(20) null default '~' 
/*集团主键*/,
pk_org nvarchar(20) null default '~' 
/*工厂最新版本*/,
pk_org_v nvarchar(20) null default '~' 
/*工厂*/,
cprojectid nvarchar(20) null default '~' 
/*项目*/,
cproductorid nvarchar(20) null default '~' 
/*生产厂商*/,
cvendorid nvarchar(20) null default '~' 
/*供应商*/,
ccustomerid nvarchar(20) null default '~' 
/*客户*/,
vbfree1 nvarchar(101) null 
/*自由辅助属性1*/,
vbfree2 nvarchar(101) null 
/*自由辅助属性2*/,
vbfree3 nvarchar(101) null 
/*自由辅助属性3*/,
vbfree4 nvarchar(101) null 
/*自由辅助属性4*/,
vbfree5 nvarchar(101) null 
/*自由辅助属性5*/,
vbfree6 nvarchar(101) null 
/*自由辅助属性6*/,
vbfree7 nvarchar(101) null 
/*自由辅助属性7*/,
vbfree8 nvarchar(101) null 
/*自由辅助属性8*/,
vbfree9 nvarchar(101) null 
/*自由辅助属性9*/,
vbfree10 nvarchar(101) null 
/*自由辅助属性10*/,
cstuffid nvarchar(20) null default '~' 
/*材料*/,
cmarbaseclassid nvarchar(20) null default '~' 
/*基本分类*/,
 constraint pk_cm_allocfac_b primary key (callocfac_bid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 物料价格来源 */
create table mapub_materialpricesources (
cpricesourcesid nchar(20) not null 
/*物料价格来源*/,
 constraint pk_ialpricesources primary key (cpricesourcesid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 物料价格来源明细 */
create table mapub_materialpricesources_b (
cpricesources_bid nchar(20) not null 
/*物料价格来源明细*/,
cpricesourcesid nchar(20) null 
/*物料价格来源*/,
vpricesource int null 
/*价格来源*/,
pk_org nvarchar(20) null default '~' 
/*业务单元*/,
corgcode nvarchar(50) null 
/*业务单元编码*/,
 constraint pk_lpricesources_b primary key (cpricesources_bid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ???料取价 */
create table mapub_materialpullprice (
cmaterialpullpriceid nchar(20) not null 
/*物料取价*/,
cperiod nvarchar(20) null default '~' 
/*会计期间方案*/,
costtype nvarchar(20) null default '~' 
/*成本类型*/,
cperiod1 nvarchar(20) null default '~' 
/*会计期间*/,
cbegindate nvarchar(20) null default '~' 
/*平均采购入库开始期间*/,
cenddate nvarchar(20) null default '~' 
/*平均采购入库截止期间*/,
pk_org nvarchar(50) null 
/*业务单元*/,
pk_group nvarchar(50) null 
/*集团*/,
 constraint pk_terialpullprice primary key (cmaterialpullpriceid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 物料取价明细 */
create table mapub_materialpullprice_b (
cmaterialpullprice_bid nchar(20) not null 
/*物料取价明细*/,
cmaterialpullpriceid nchar(20) null 
/*物料取价*/,
 constraint pk_rialpullprice_b primary key (cmaterialpullprice_bid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 取价错误日志 */
create table mapub_acquirepricelog (
pk_log nchar(20) not null 
/*取价错误日志*/,
vacquiretype nvarchar(50) not null 
/*取价类型*/,
pk_group nvarchar(20) null default '~' 
/*取价集团*/,
pk_org nvarchar(20) null default '~' 
/*取价工厂*/,
ccosttypeid nvarchar(20) null default '~' 
/*成本类型*/,
cperiod nvarchar(20) null default '~' 
/*会计期间*/,
begindate nvarchar(20) null default '~' 
/*开始期间*/,
enddate nvarchar(20) null default '~' 
/*截止期间*/,
isourcetype int null 
/*来源类型*/,
prioritylevel nvarchar(50) null 
/*优先级*/,
cmaterialpriceid nvarchar(20) null default '~' 
/*价格库*/,
pk_currtype nvarchar(20) null default '~' 
/*来源币种*/,
finstoragetype int null 
/*入库类型*/,
ccostcenterid nvarchar(20) null default '~' 
/*主产品成本中心*/,
cmaterialid nvarchar(20) null default '~' 
/*主产品*/,
cprojectid nvarchar(20) null default '~' 
/*项目*/,
cvendorid nvarchar(20) null default '~' 
/*供应商*/,
cproductorid nvarchar(20) null default '~' 
/*生产厂商*/,
ccustomerid nvarchar(20) null default '~' 
/*客户*/,
vfree1 nvarchar(101) null 
/*自由辅助属性1*/,
vfree2 nvarchar(101) null 
/*自由辅助属性2*/,
vfree3 nvarchar(101) null 
/*自由辅助属性3*/,
vfree4 nvarchar(101) null 
/*自由辅助属性4*/,
vfree5 nvarchar(101) null 
/*自由辅助属性5*/,
vfree6 nvarchar(101) null 
/*自由辅助属性6*/,
vfree7 nvarchar(101) null 
/*自由辅助属性7*/,
vfree8 nvarchar(101) null 
/*自由辅助属性8*/,
vfree9 nvarchar(101) null 
/*自由辅助属性9*/,
vfree10 nvarchar(101) null 
/*自由辅助属性10*/,
cbcostcenterid nvarchar(20) null default '~' 
/*材料成本中心*/,
cbmaterialid nvarchar(20) null default '~' 
/*材料*/,
cbprojectid nvarchar(20) null default '~' 
/*项目*/,
cbvendorid nvarchar(20) null default '~' 
/*供应商*/,
cbproductorid nvarchar(20) null default '~' 
/*生产厂商*/,
cbcustomerid nvarchar(20) null default '~' 
/*客户*/,
vbfree1 nvarchar(50) null 
/*自由辅助属性1*/,
vbfree2 nvarchar(50) null 
/*自由辅助属性2*/,
vbfree3 nvarchar(50) null 
/*自由辅助属性3*/,
vbfree4 nvarchar(50) null 
/*自由辅助属性4*/,
vbfree5 nvarchar(50) null 
/*自由辅助属性5*/,
vbfree6 nvarchar(50) null 
/*自由辅助属性6*/,
vbfree7 nvarchar(50) null 
/*自由辅助属性7*/,
vbfree8 nvarchar(50) null 
/*自由辅助属性8*/,
vbfree9 nvarchar(50) null 
/*自由辅助属性9*/,
vbfree10 nvarchar(50) null 
/*自由辅助属性10*/,
celementid nvarchar(20) null default '~' 
/*核算要素*/,
cactivityid nvarchar(20) null default '~' 
/*作业*/,
pk_group_actual nvarchar(20) null default '~' 
/*实际取价集团*/,
pk_org_actual nvarchar(20) null default '~' 
/*实际取价工厂*/,
isourcetype_actual int null 
/*实际取价来源*/,
cmaterialpriceid_actual nvarchar(20) null default '~' 
/*实际价格库*/,
pk_currtype_actual nvarchar(20) null default '~' 
/*实际币种*/,
nprice numeric(28,8) null 
/*价格*/,
errorinfo nvarchar(500) null 
/*错误原因*/,
solveplan nvarchar(500) null 
/*解决方案*/,
 constraint pk_acquirepricelog primary key (pk_log),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 成本动因 */
create table cm_driver (
vname nvarchar(300) null 
/*名称*/,
vname2 nvarchar(300) null 
/*名称*/,
vname3 nvarchar(300) null 
/*名称*/,
vname4 nvarchar(300) null 
/*名称*/,
cdriverid nchar(20) not null 
/*成本动因主键*/,
vname6 nvarchar(300) null 
/*名称*/,
vname5 nvarchar(300) null 
/*名称*/,
pk_group nvarchar(20) null default '~' 
/*集团主键*/,
pk_org nvarchar(20) null default '~' 
/*库存组织主键*/,
pk_org_v nvarchar(20) null default '~' 
/*库存组织版本主键*/,
vcode nvarchar(50) null 
/*编码*/,
creator nvarchar(20) null default '~' 
/*创建人*/,
creationtime nchar(19) null 
/*创建时间*/,
modifier nvarchar(20) null default '~' 
/*最后修改人*/,
modifiedtime nchar(19) null 
/*最后修改时间*/,
vformulavalue nvarchar(750) null 
/*公式*/,
vjavapath nvarchar(50) null 
/*自定义JAVA类*/,
vformulacode nvarchar(500) null 
/*公式code*/,
vnote nvarchar(181) null 
/*备注*/,
vformulavalue2 nvarchar(500) null 
/*公式2*/,
vformulavalue3 nvarchar(500) null 
/*公式3*/,
vformulavalue4 nvarchar(500) null 
/*公式4*/,
vformulavalue5 nvarchar(500) null 
/*公式5*/,
vformulavalue6 nvarchar(500) null 
/*公式6*/,
 constraint pk_cm_driver primary key (cdriverid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 成本类型 */
create table mapub_costtype (
ccosttypeid nchar(20) not null 
/*成本类型主键*/,
vcosttypename nvarchar(300) null 
/*成本类型名称*/,
vcosttypename2 nvarchar(300) null 
/*成本类型名称2*/,
vcosttypename3 nvarchar(300) null 
/*成本类型名称3*/,
vcosttypename4 nvarchar(300) null 
/*成本类型名称4*/,
vcosttypename5 nvarchar(300) null 
/*成本类型名称5*/,
vcosttypename6 nvarchar(300) null 
/*成本类型名称6*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
pk_org nvarchar(20) null default '~' 
/*工厂*/,
vcosttypecode nvarchar(40) null 
/*成本类型编码*/,
bscrapfactor nchar(1) null 
/*是否考虑废品系数*/,
bshrinkfactor nchar(1) null 
/*是否考虑损耗系数*/,
pk_elementsystem nvarchar(20) null 
/*要素体系*/,
pk_factorchart nvarchar(20) null 
/*核算要素表*/,
pk_materialdocview nvarchar(20) null 
/*要素与物料对照表*/,
pk_activitydocview nvarchar(20) null 
/*要素与作业对照表*/,
vmaterialpricesourcenum nvarchar(500) null 
/*物料价格来源数*/,
vcostpricesourcenum nvarchar(500) null 
/*费用价格来源数*/,
cbeginmonth nvarchar(50) null 
/*生效期间*/,
cendmonth nvarchar(50) null 
/*失效期间*/,
bdefault nchar(1) null 
/*是否默认*/,
bcompute nchar(1) null 
/*是否已计算*/,
bcreatetable nchar(1) null 
/*是否已生成库*/,
creator nvarchar(20) null default '~' 
/*创建人*/,
modifier nvarchar(20) null default '~' 
/*修改人*/,
creationtime nchar(19) null 
/*创建时间*/,
modifiedtime nchar(19) null 
/*修改时间*/,
vnote nvarchar(1024) null 
/*备注*/,
 constraint pk_mapub_costtype primary key (ccosttypeid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 联产品相对系数明细 */
create table mapub_coprodcoef_b (
ccoprodcoefbid nchar(20) not null 
/*联产品相对系数明细*/,
ccoprodcoefid nchar(20) null 
/*联产品相对系数*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
pk_org nvarchar(20) null default '~' 
/*业务单元*/,
pk_org_v nvarchar(20) null default '~' 
/*业务单元版本*/,
cmaterialid nvarchar(20) not null default '~' 
/*物料最新版本*/,
cmaterialvid nvarchar(20) null default '~' 
/*物料*/,
iproducttype int not null 
/*产品类型*/,
ccostcenterid nvarchar(20) null default '~' 
/*成本中心*/,
ccosttypeid nvarchar(20) null default '~' 
/*成本类型*/,
celementid nvarchar(20) null default '~' 
/*核算要素编码*/,
nrelcoefficient numeric(28,8) not null 
/*相对系数*/,
 constraint pk_mapub_coef_b primary key (ccoprodcoefbid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 联产品相对系数 */
create table mapub_coprodcoef (
ccoprodcoefid nchar(20) not null 
/*联产品相对系数*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
pk_org nvarchar(20) not null default '~' 
/*业务单元*/,
pk_org_v nvarchar(20) null default '~' 
/*业务单元版本*/,
cmaterialid nvarchar(20) not null default '~' 
/*物料最新版本*/,
cmaterialvid nvarchar(20) null default '~' 
/*物料*/,
cbomid nvarchar(20) null default '~' 
/*生产BOM版本*/,
crtid nvarchar(20) null default '~' 
/*工艺路线*/,
pk_elementsystem nvarchar(20) null default '~' 
/*要素体系*/,
pk_factorchart nvarchar(20) null default '~' 
/*核算要素表*/,
creator nvarchar(20) null default '~' 
/*创建人*/,
creationtime nchar(19) null 
/*创建时间*/,
modifier nvarchar(20) null default '~' 
/*修改人*/,
modifiedtime nchar(19) null 
/*修改时间*/,
ibillstatus int null 
/*单据状态*/,
 constraint pk_mapub_coef primary key (ccoprodcoefid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

