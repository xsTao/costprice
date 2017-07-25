/* tablename: 物料价格库 */
create table mapub_materialpricebase (cmaterialpriceid char(20) not null 
/*物料价格库*/,
pk_group varchar(20) null default '~' 
/*集团*/,
pk_org varchar(20) null default '~' 
/*业务单元*/,
pk_org_v varchar(20) null default '~' 
/*业务单元版本*/,
vpricecode varchar(200) null 
/*价格库编码*/,
vpricename varchar(50) null 
/*价格库名称*/,
ccrrencyid varchar(20) null default '~' 
/*币种*/,
vpricesourcenum varchar(1000) null 
/*价格来源数*/,
dbegindate char(19) null 
/*生效日期*/,
denddate char(19) null 
/*失效日期*/,
vnote varchar(181) null 
/*备注*/,
blockingflag char(1) null 
/*锁定*/,
creator varchar(20) null default '~' 
/*创建人*/,
creationtime char(19) null 
/*创建时间*/,
modifier varchar(20) null default '~' 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
 constraint pk_terialpricebase primary key (cmaterialpriceid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 物料价格库明细 */
create table mapub_materialpricebase_b (cmaterialprice_bid char(20) not null 
/*价格库物料明细*/,
cmaterialpriceid char(20) null 
/*物料价格库*/,
pk_group varchar(20) null default '~' 
/*集团*/,
pk_org varchar(20) null default '~' 
/*业务单元*/,
pk_org_v varchar(20) null default '~' 
/*业务单元版本*/,
cmaterialid varchar(20) null default '~' 
/*物料*/,
cmaterialvid varchar(20) null default '~' 
/*物料多版本*/,
cmeasdocid varchar(20) null default '~' 
/*计量单位*/,
cprojectid varchar(20) null default '~' 
/*项目*/,
cvendorid varchar(20) null default '~' 
/*供应商*/,
cproductorid varchar(20) null default '~' 
/*生产厂商*/,
ccustomerid varchar(20) null default '~' 
/*客户*/,
vbfree1 varchar(101) null 
/*自由辅助属性1*/,
vbfree2 varchar(101) null 
/*自由辅助属性2*/,
vbfree3 varchar(101) null 
/*自由辅助属性3*/,
vbfree4 varchar(101) null 
/*自由辅助属性4*/,
vbfree5 varchar(101) null 
/*自由辅助属性5*/,
vbfree6 varchar(101) null 
/*自由辅助属性6*/,
vbfree7 varchar(101) null 
/*自由辅助属性7*/,
vbfree8 varchar(101) null 
/*自由辅助属性8*/,
vbfree9 varchar(101) null 
/*自由辅助属性9*/,
vbfree10 varchar(101) null 
/*自由辅助属性10*/,
vpricesourcenum varchar(1000) null 
/*价格来源数*/,
vpricesourcerealnum varchar(500) null 
/*实际价格来源数*/,
nprice numeric(28,8) null 
/*单价*/,
 constraint pk_rialpricebase_b primary key (cmaterialprice_bid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 分配系数 */
create table cm_allocfac (callocfacid char(20) not null 
/*分配系数*/,
vname6 varchar(300) null 
/*名称*/,
vname5 varchar(300) null 
/*名称*/,
vname4 varchar(300) null 
/*名称*/,
vname3 varchar(300) null 
/*名称*/,
vname2 varchar(300) null 
/*名称*/,
vname varchar(300) null 
/*名称*/,
pk_group varchar(20) null default '~' 
/*集团主键*/,
pk_org varchar(20) null default '~' 
/*工厂*/,
pk_org_v varchar(20) null default '~' 
/*工厂*/,
vcode varchar(50) null 
/*编码*/,
vnote varchar(181) null 
/*备注*/,
creator varchar(20) null default '~' 
/*创建人*/,
creationtime char(19) null 
/*创建时间*/,
modifier varchar(20) null default '~' 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
ialloctype integer null 
/*分配内容*/,
 constraint pk_cm_allocfac primary key (callocfacid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 分配系数明细 */
create table cm_allocfac_b (callocfacid char(20) null 
/*主表主键*/,
callocfac_bid char(20) not null 
/*子表主键*/,
ccostcenterid varchar(20) null default '~' 
/*成本中心*/,
cmaterialid varchar(20) null default '~' 
/*产品*/,
cmaterialvid varchar(20) null default '~' 
/*物料*/,
cmarcostclassid varchar(20) null default '~' 
/*成本分类*/,
cactivityid varchar(20) null default '~' 
/*作业*/,
nfactor numeric(28,8) null 
/*系数*/,
ccostobjectid varchar(20) null default '~' 
/*成本对象*/,
pk_group varchar(20) null default '~' 
/*集团主键*/,
pk_org varchar(20) null default '~' 
/*工厂最新版本*/,
pk_org_v varchar(20) null default '~' 
/*工厂*/,
cprojectid varchar(20) null default '~' 
/*项目*/,
cproductorid varchar(20) null default '~' 
/*生产厂商*/,
cvendorid varchar(20) null default '~' 
/*供应商*/,
ccustomerid varchar(20) null default '~' 
/*客户*/,
vbfree1 varchar(101) null 
/*自由辅助属性1*/,
vbfree2 varchar(101) null 
/*自由辅助属性2*/,
vbfree3 varchar(101) null 
/*自由辅助属性3*/,
vbfree4 varchar(101) null 
/*自由辅助属性4*/,
vbfree5 varchar(101) null 
/*自由辅助属性5*/,
vbfree6 varchar(101) null 
/*自由辅助属性6*/,
vbfree7 varchar(101) null 
/*自由辅助属性7*/,
vbfree8 varchar(101) null 
/*自由辅助属性8*/,
vbfree9 varchar(101) null 
/*自由辅助属性9*/,
vbfree10 varchar(101) null 
/*自由辅助属性10*/,
cstuffid varchar(20) null default '~' 
/*材料*/,
cmarbaseclassid varchar(20) null default '~' 
/*基本分类*/,
 constraint pk_cm_allocfac_b primary key (callocfac_bid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 物料价格来源 */
create table mapub_materialpricesources (cpricesourcesid char(20) not null 
/*物料价格来源*/,
 constraint pk_ialpricesources primary key (cpricesourcesid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 物料价格来源明细 */
create table mapub_materialpricesources_b (cpricesources_bid char(20) not null 
/*物料价格来源明细*/,
cpricesourcesid char(20) null 
/*物料价格来源*/,
vpricesource integer null 
/*价格来源*/,
pk_org varchar(20) null default '~' 
/*业务单元*/,
corgcode varchar(50) null 
/*业务单元编码*/,
 constraint pk_lpricesources_b primary key (cpricesources_bid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ???料取价 */
create table mapub_materialpullprice (cmaterialpullpriceid char(20) not null 
/*物料取价*/,
cperiod varchar(20) null default '~' 
/*会计期间方案*/,
costtype varchar(20) null default '~' 
/*成本类型*/,
cperiod1 varchar(20) null default '~' 
/*会计期间*/,
cbegindate varchar(20) null default '~' 
/*平均采购入库开始期间*/,
cenddate varchar(20) null default '~' 
/*平均采购入库截止期间*/,
pk_org varchar(50) null 
/*业务单元*/,
pk_group varchar(50) null 
/*集团*/,
 constraint pk_terialpullprice primary key (cmaterialpullpriceid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 物料取价明细 */
create table mapub_materialpullprice_b (cmaterialpullprice_bid char(20) not null 
/*物料取价明细*/,
cmaterialpullpriceid char(20) null 
/*物料取价*/,
 constraint pk_rialpullprice_b primary key (cmaterialpullprice_bid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 取价错误日志 */
create table mapub_acquirepricelog (pk_log char(20) not null 
/*取价错误日志*/,
vacquiretype varchar(50) not null 
/*取价类型*/,
pk_group varchar(20) null default '~' 
/*取价集团*/,
pk_org varchar(20) null default '~' 
/*取价工厂*/,
ccosttypeid varchar(20) null default '~' 
/*成本类型*/,
cperiod varchar(20) null default '~' 
/*会计期间*/,
begindate varchar(20) null default '~' 
/*开始期间*/,
enddate varchar(20) null default '~' 
/*截止期间*/,
isourcetype integer null 
/*来源类型*/,
prioritylevel varchar(50) null 
/*优先级*/,
cmaterialpriceid varchar(20) null default '~' 
/*价格库*/,
pk_currtype varchar(20) null default '~' 
/*来源币种*/,
finstoragetype integer null 
/*入库类型*/,
ccostcenterid varchar(20) null default '~' 
/*主产品成本中心*/,
cmaterialid varchar(20) null default '~' 
/*主产品*/,
cprojectid varchar(20) null default '~' 
/*项目*/,
cvendorid varchar(20) null default '~' 
/*供应商*/,
cproductorid varchar(20) null default '~' 
/*生产厂商*/,
ccustomerid varchar(20) null default '~' 
/*客户*/,
vfree1 varchar(101) null 
/*自由辅助属性1*/,
vfree2 varchar(101) null 
/*自由辅助属性2*/,
vfree3 varchar(101) null 
/*自由辅助属性3*/,
vfree4 varchar(101) null 
/*自由辅助属性4*/,
vfree5 varchar(101) null 
/*自由辅助属性5*/,
vfree6 varchar(101) null 
/*自由辅助属性6*/,
vfree7 varchar(101) null 
/*自由辅助属性7*/,
vfree8 varchar(101) null 
/*自由辅助属性8*/,
vfree9 varchar(101) null 
/*自由辅助属性9*/,
vfree10 varchar(101) null 
/*自由辅助属性10*/,
cbcostcenterid varchar(20) null default '~' 
/*材料成本中心*/,
cbmaterialid varchar(20) null default '~' 
/*材料*/,
cbprojectid varchar(20) null default '~' 
/*项目*/,
cbvendorid varchar(20) null default '~' 
/*供应商*/,
cbproductorid varchar(20) null default '~' 
/*生产厂商*/,
cbcustomerid varchar(20) null default '~' 
/*客户*/,
vbfree1 varchar(50) null 
/*自由辅助属性1*/,
vbfree2 varchar(50) null 
/*自由辅助属性2*/,
vbfree3 varchar(50) null 
/*自由辅助属性3*/,
vbfree4 varchar(50) null 
/*自由辅助属性4*/,
vbfree5 varchar(50) null 
/*自由辅助属性5*/,
vbfree6 varchar(50) null 
/*自由辅助属性6*/,
vbfree7 varchar(50) null 
/*自由辅助属性7*/,
vbfree8 varchar(50) null 
/*自由辅助属性8*/,
vbfree9 varchar(50) null 
/*自由辅助属性9*/,
vbfree10 varchar(50) null 
/*自由辅助属性10*/,
celementid varchar(20) null default '~' 
/*核算要素*/,
cactivityid varchar(20) null default '~' 
/*作业*/,
pk_group_actual varchar(20) null default '~' 
/*实际取价集团*/,
pk_org_actual varchar(20) null default '~' 
/*实际取价工厂*/,
isourcetype_actual integer null 
/*实际取价来源*/,
cmaterialpriceid_actual varchar(20) null default '~' 
/*实际价格库*/,
pk_currtype_actual varchar(20) null default '~' 
/*实际币种*/,
nprice numeric(28,8) null 
/*价格*/,
errorinfo varchar(500) null 
/*错误原因*/,
solveplan varchar(500) null 
/*解决方案*/,
 constraint pk_acquirepricelog primary key (pk_log),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 成本动因 */
create table cm_driver (vname varchar(300) null 
/*名称*/,
vname2 varchar(300) null 
/*名称*/,
vname3 varchar(300) null 
/*名称*/,
vname4 varchar(300) null 
/*名称*/,
cdriverid char(20) not null 
/*成本动因主键*/,
vname6 varchar(300) null 
/*名称*/,
vname5 varchar(300) null 
/*名称*/,
pk_group varchar(20) null default '~' 
/*集团主键*/,
pk_org varchar(20) null default '~' 
/*库存组织主键*/,
pk_org_v varchar(20) null default '~' 
/*库存组织版本主键*/,
vcode varchar(50) null 
/*编码*/,
creator varchar(20) null default '~' 
/*创建人*/,
creationtime char(19) null 
/*创建时间*/,
modifier varchar(20) null default '~' 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
vformulavalue varchar(750) null 
/*公式*/,
vjavapath varchar(50) null 
/*自定义JAVA类*/,
vformulacode varchar(500) null 
/*公式code*/,
vnote varchar(181) null 
/*备注*/,
vformulavalue2 varchar(500) null 
/*公式2*/,
vformulavalue3 varchar(500) null 
/*公式3*/,
vformulavalue4 varchar(500) null 
/*公式4*/,
vformulavalue5 varchar(500) null 
/*公式5*/,
vformulavalue6 varchar(500) null 
/*公式6*/,
 constraint pk_cm_driver primary key (cdriverid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 成本类型 */
create table mapub_costtype (ccosttypeid char(20) not null 
/*成本类型主键*/,
vcosttypename varchar(300) null 
/*成本类型名称*/,
vcosttypename2 varchar(300) null 
/*成本类型名称2*/,
vcosttypename3 varchar(300) null 
/*成本类型名称3*/,
vcosttypename4 varchar(300) null 
/*成本类型名称4*/,
vcosttypename5 varchar(300) null 
/*成本类型名称5*/,
vcosttypename6 varchar(300) null 
/*成本类型名称6*/,
pk_group varchar(20) null default '~' 
/*集团*/,
pk_org varchar(20) null default '~' 
/*工厂*/,
vcosttypecode varchar(40) null 
/*成本类型编码*/,
bscrapfactor char(1) null 
/*是否考虑废品系数*/,
bshrinkfactor char(1) null 
/*是否考虑损耗系数*/,
pk_elementsystem varchar(20) null 
/*要素体系*/,
pk_factorchart varchar(20) null 
/*核算要素表*/,
pk_materialdocview varchar(20) null 
/*要素与物料对照表*/,
pk_activitydocview varchar(20) null 
/*要素与作业对照表*/,
vmaterialpricesourcenum varchar(500) null 
/*物料价格来源数*/,
vcostpricesourcenum varchar(500) null 
/*费用价格来源数*/,
cbeginmonth varchar(50) null 
/*生效期间*/,
cendmonth varchar(50) null 
/*失效期间*/,
bdefault char(1) null 
/*是否默认*/,
bcompute char(1) null 
/*是否已计算*/,
bcreatetable char(1) null 
/*是否已生成库*/,
creator varchar(20) null default '~' 
/*创建人*/,
modifier varchar(20) null default '~' 
/*修改人*/,
creationtime char(19) null 
/*创建时间*/,
modifiedtime char(19) null 
/*修改时间*/,
vnote varchar(1024) null 
/*备注*/,
 constraint pk_mapub_costtype primary key (ccosttypeid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 联产品相对系数明细 */
create table mapub_coprodcoef_b (ccoprodcoefbid char(20) not null 
/*联产品相对系数明细*/,
ccoprodcoefid char(20) null 
/*联产品相对系数*/,
pk_group varchar(20) null default '~' 
/*集团*/,
pk_org varchar(20) null default '~' 
/*业务单元*/,
pk_org_v varchar(20) null default '~' 
/*业务单元版本*/,
cmaterialid varchar(20) not null default '~' 
/*物料最新版本*/,
cmaterialvid varchar(20) null default '~' 
/*物料*/,
iproducttype integer not null 
/*产品类型*/,
ccostcenterid varchar(20) null default '~' 
/*成本中心*/,
ccosttypeid varchar(20) null default '~' 
/*成本类型*/,
celementid varchar(20) null default '~' 
/*核算要素编码*/,
nrelcoefficient numeric(28,8) not null 
/*相对系数*/,
 constraint pk_mapub_coef_b primary key (ccoprodcoefbid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 联产品相对系数 */
create table mapub_coprodcoef (ccoprodcoefid char(20) not null 
/*联产品相对系数*/,
pk_group varchar(20) null default '~' 
/*集团*/,
pk_org varchar(20) not null default '~' 
/*业务单元*/,
pk_org_v varchar(20) null default '~' 
/*业务单元版本*/,
cmaterialid varchar(20) not null default '~' 
/*物料最新版本*/,
cmaterialvid varchar(20) null default '~' 
/*物料*/,
cbomid varchar(20) null default '~' 
/*生产BOM版本*/,
crtid varchar(20) null default '~' 
/*工艺路线*/,
pk_elementsystem varchar(20) null default '~' 
/*要素体系*/,
pk_factorchart varchar(20) null default '~' 
/*核算要素表*/,
creator varchar(20) null default '~' 
/*创建人*/,
creationtime char(19) null 
/*创建时间*/,
modifier varchar(20) null default '~' 
/*修改人*/,
modifiedtime char(19) null 
/*修改时间*/,
ibillstatus integer null 
/*单据状态*/,
 constraint pk_mapub_coef primary key (ccoprodcoefid),
 ts char(19) null,
dr smallint null default 0
)
;

