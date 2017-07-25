package nc.impl.mapub.materialpricebase.queryprice;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;import nc.bd.framework.db.CMSqlBuilder;import nc.bd.framework.db.CMTempTable;import nc.bd.framework.db.CMTempTableFactory;import nc.bs.framework.common.NCLocator;import nc.bs.pubapp.AppBsContext;import nc.itf.mapub.materialpricebase.IMaterialPriceBasePrepareParam;import nc.itf.uap.IUAPQueryBS;import nc.jdbc.framework.processor.ArrayListProcessor;import nc.vo.cmpub.business.aquireprice.AquirePriceParamVO;import nc.vo.cmpub.business.enumeration.PriceResTypeEnum;import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseQueryPriceParamVO;import nc.vo.pub.BusinessException;import nc.vo.pub.JavaType;import nc.vo.pub.lang.UFDate;import nc.vo.pub.lang.UFDouble;import nc.vo.trade.checkrule.VOChecker;;/** * <b> 根据价格查询参数VO，查询价格库子表中价格的业务实现类 </b> * <p> * 详细描述功能 * </p> * 创建日期:2010-8-11 * * @author:jilu */public class MaterialPriceBaseQueryPrice {    /**     * 临时表     */    private CMTempTable tempTable = null;    /**     * 建立临时表的表名     */    private static final String TMPTABLENAME = "tmp_materialpricebase";    /**     * itemID，针对查询物料，为物料ID     */    private static final String ITEMID = AquirePriceParamVO.ITEMID;    /**     * 临时表里的列:组织ID、价格库ID、成本中心ID、itemID     */    private static final String[] TMPTABLE_COLUMSNAME = {        MaterialPriceBaseBodyVO.PK_ORG, MaterialPriceBaseBodyVO.CMATERIALPRICEID, MaterialPriceBaseQueryPrice.ITEMID    };    /**     * 临时表中的列Java类型：都是String类型     */    private static final JavaType[] TMPTABLE_JAVATYPE = {        JavaType.String, JavaType.String, JavaType.String    };    /**     * 临时表中的列数据类型：都是char(20)     */    private static final String[] TMPTABLE_COLUMSTYPE = {        "char(20)", "char(20)", "char(20)"    };    /**     * 查询价格的主要方法体     *     * @param priceParamVOs     *            价格查询参数VO     * @param priceQueryType     *            查询价格的类型，PriceResTypeEnum中定义     * @return Map<String, UFDouble> Map<key, 价格>     * @throws BusinessException     *             异常     */    @SuppressWarnings("unchecked")    public Map<String, UFDouble> queryPriceInMaterialPriceBase(MaterialPriceBaseQueryPriceParamVO[] priceParamVOs,            PriceResTypeEnum priceQueryType) throws BusinessException {        // 1、创建/返回临时表，清空表中内容，向临时表中插入数据，准备进行表连接查询        this.initTempTable();        this.insertDatasToTempTable(priceParamVOs);        // 2、根据得到的fields内容，返回SQL语句        IMaterialPriceBasePrepareParam pricePrepareParam =                MaterialPriceBaseQueryFactory.getPricePrepareParam(priceQueryType);        // 如果返回的实现类为空，则返回空结果        if (pricePrepareParam == null) {            return new HashMap<String, UFDouble>();        }        String tempTableName = this.getTempTableName(); // 临时表表名        String tableName = pricePrepareParam.getTableName(); // 待查询表表名        String[] fields = pricePrepareParam.getFieldsForSQL(); // 待查询的结果域        String querySQL = this.getQuerySQL(tempTableName, tableName, fields);        // 3、采用临时表进行连接查询出所有结果        if (querySQL == null) {            return new HashMap<String, UFDouble>();        }        ArrayListProcessor processlist = new ArrayListProcessor();        ArrayList<Object[]> resultlist =                (ArrayList<Object[]>) this.getUAPQueryService().executeQuery(querySQL, processlist);        // 4、组装最终的返回结果        if (resultlist == null || resultlist.isEmpty()) {            return new HashMap<String, UFDouble>();        }        Map<String, UFDouble> resultReturnMap = this.buildMapResult(fields, resultlist);        return resultReturnMap;    }    /**     * 根据查询结果组装成Map     *     * @param fields     *            查询的fields     * @param resultlist     *            查询结果     * @return Map     */    private Map<String, UFDouble> buildMapResult(String[] fields, ArrayList<Object[]> resultlist) {        Map<String, UFDouble> resultReturnMap = new HashMap<String, UFDouble>();        if (resultlist == null || resultlist.size() <= 0) {            return resultReturnMap;        }        for (Object[] values : resultlist) {            // 将key和价格装载在map中，其中Object[]中的最后一项是价格            String key = this.getMapKeyThreeTable(values);            if (key == null) {                continue;            }            UFDouble price = new UFDouble(values[fields.length - 1].toString());            resultReturnMap.put(key, price);        }        return resultReturnMap;    }    /**     * 清空临时表中的数据     */    private void clearTempTableData() {        if (this.tempTable == null) {            return;        }        this.tempTable.clearData();    }    /**     * 初始化临时表，包含4列pkorg, cpricebaseid, ccostCenterid, itemid;与是材料、作业、费用子表无关     * pkorg： 组织ID     * cpricebaseid： 价格库ID     * ccostCenterid： 成本中心ID     * itemID： 子项ID（在查询材料、作业、费用价格时，分别是材料ID、作业ID、核算要素ID）     */    private void initTempTable() {        if (this.tempTable == null) {            // 创建临时表            this.tempTable =                    CMTempTableFactory.createTempTable(MaterialPriceBaseQueryPrice.TMPTABLENAME,                            MaterialPriceBaseQueryPrice.TMPTABLE_COLUMSNAME,                            MaterialPriceBaseQueryPrice.TMPTABLE_COLUMSTYPE,                            MaterialPriceBaseQueryPrice.TMPTABLE_JAVATYPE);        }        else {            this.clearTempTableData();        }    }    /**     * 返回临时表，包含4列pkorg, cpricebaseid, ccostCenterid, itemid;与是材料、作业、费用子表无关     * pkorg： 组织ID     * cpricebaseid： 价格库ID     * ccostCenterid： 成本中心ID     * itemID： 子项ID（在查询材料、作业、费用价格时，分别是材料ID、作业ID、核算要素ID）     *     * @return MMTempTable     */    private CMTempTable getTempTable() {        if (this.tempTable == null) {            this.initTempTable();            return this.tempTable;        }        return this.tempTable;    }    /**     * 返回临时表的真正表名     *     * @return 临时表表名     */    private String getTempTableName() {        return this.getTempTable().getRealTableName();    }    /**     * 向临时表中插入数据     *     * @param priceParamVOs     *            查询参数VO     */    private void insertDatasToTempTable(MaterialPriceBaseQueryPriceParamVO[] priceParamVOs) {        if (priceParamVOs == null || priceParamVOs.length <= 0) {            return;        }        // 构建数据，并向临时表中插入数据        List<Object> lineData;        List<List<Object>> tableData = new ArrayList<List<Object>>();        for (int i = 0; i < priceParamVOs.length; i++) {            lineData = new ArrayList<Object>();            lineData.add(priceParamVOs[i].getPkOrg());            lineData.add(priceParamVOs[i].getCmaterialpricebaseid());            lineData.add(priceParamVOs[i].getItemid());            tableData.add(lineData);        }        this.getTempTable().insert(tableData);    }    /**     * 返回最终的sql语句     *     * @param tempTableName     *            临时表名称     * @param tableName     *            查询的表名     * @param fields     *            查询字段     * @return sql语句     */    private String getQuerySQL(String tempTableName, String tableName, String[] fields) {        /**         * SELECT cm_pricebase_s.pk_org,         * cm_pricebase_s.cpricebaseid,         * cm_pricebase_s.cmaterialid,         * cm_pricebase_s.nprice         * FROM cm_pricebase_s         * INNER JOIN tmp_pricebase ON cm_pricebase_s.pk_org = tmp_pricebase.pk_org         * And cm_pricebase_s.cpricebaseid = tmp_pricebase.cpricebaseid         * And cm_pricebase_s.cmaterialid = tmp_pricebase.itemid         * WHERE cm_pricebase_s.dr = 0         */        /**         * SELECT cm_pricebase_a.pk_org,         * cm_pricebase_a.cpricebaseid,         * cm_pricebase_a.ccostcenterid,         * cm_pricebase_a.cactivityid,         * cm_pricebase_a.nprice         * FROM cm_pricebase_a         * INNER JOIN tmp_pricebase ON (cm_pricebase_a.pk_org = tmp_pricebase.pk_org         * AND cm_pricebase_a.cpricebaseid = tmp_pricebase.cpricebaseid         * AND cm_pricebase_a.ccostcenterid = tmp_pricebase.ccostcenterid         * AND cm_pricebase_a.cactivityid = tmp_pricebase.itemid)         * OR (cm_pricebase_a.pk_org = tmp_pricebase.pk_org         * AND cm_pricebase_a.cpricebaseid = tmp_pricebase.cpricebaseid         * AND cm_pricebase_a.cactivityid = tmp_pricebase.itemid         * AND cm_pricebase_a.ccostcenterid is null         * AND tmp_pricebase.ccostcenterid is null)         */        // 判空处理        if (tempTableName == null || tableName == null || VOChecker.isEmpty(fields)) {            return null;        }        CMSqlBuilder querySQL = new CMSqlBuilder();        // select 后面的内容；加上表名的（如cm_pricebase_f.pk_org）        String[] fieldsWithTable = this.getFieldsWithTable(tableName, fields);        // 拼成完整的SQL语句        querySQL.select();        querySQL.select(null, fieldsWithTable);        querySQL.from(tableName);        querySQL.append(" inner join ");        querySQL.append(tempTableName);        querySQL.on();        querySQL.append(this.getElStr(tableName, tempTableName, fields));        // 处理价格库的生效日期、失效日期问题 modify by zhangweix 2012-08-02        querySQL.append(" inner join ");        querySQL.append("sca_materialpricebase");        querySQL.on("sca_materialpricebase", MaterialPriceBaseHeadVO.CMATERIALPRICEID, tempTableName,                MaterialPriceBaseBodyVO.CMATERIALPRICEID);        querySQL.where();        querySQL.append(tableName + ".dr=0 ");        // 前台业务日期        UFDate business = AppBsContext.getInstance().getBusiDate();        querySQL.and();        querySQL.append(MaterialPriceBaseHeadVO.DBEGINDATE);        querySQL.append("<='");        querySQL.append(business.toString() + "'");        querySQL.and();        querySQL.append(MaterialPriceBaseHeadVO.DENDDATE);        querySQL.append(">='");        querySQL.append(business.toString() + "'");        return querySQL.toString();    }    /**     * 将fields加上表名，如pricebase_f.costcenterid     *     * @param tableName     *            表名     * @param fields     *            fields     * @return String[]加完表名的     */    private String[] getFieldsWithTable(String tableName, String[] fields) {        String[] fieldsWithTable = new String[fields.length];        for (int i = 0; i < fields.length; i++) {            fieldsWithTable[i] = tableName + "." + fields[i];        }        return fieldsWithTable;    }    /**     * 返回 内连接 中的 on 后面的内容     *     * @param tableNameL     *            连接左边的表名     * @param tableNameR     *            连接右边的表名(临时表)     * @param fields     *            属性     * @return String     */    private String getElStr(String tableNameL, String tableNameR, String[] fields) {        // 判空处理在调用它的方法中做了        // 用来放or之前的SQL(成本中心不为空，进行比较的情况)        CMSqlBuilder strCostCenterNotNull = new CMSqlBuilder();        // 用来放or之后的SQL(成本中心同时为空的情况)        CMSqlBuilder strCostCenterNull = new CMSqlBuilder();        String strOn = null;        boolean containCostCenter = false; // 判断该fields中是否包含ccostcenterid的标识符        for (int i = 0; i < fields.length; i++) {            // 如果fields是价格或者费率，则不用进行匹配            if (fields[i].equals(MaterialPriceBaseBodyVO.NPRICE)) {                continue;            }            // // 如果是材料/作业/费用ID，则将相应ID与临时表的itemid进行连接            // if (fields[i].equals(PriceBaseItemSVO.CMATERIALID) || fields[i].equals(PriceBaseItemAVO.CACTIVITYID)            // || fields[i].equals(PriceBaseItemFVO.CELEMENTID)) {            //            // strOn = tableNameL + "." + fields[i] + " = " + tableNameR + "." + PriceBaseQueryPrice.ITEMID + " ";            // }            // 如果是其他字段，则直接添加上            strOn = tableNameL + "." + fields[i] + " = " + tableNameR + "." + fields[i] + " ";            strCostCenterNotNull.append(strOn);            strCostCenterNotNull.and();            strCostCenterNull.append(strOn);            strCostCenterNull.and();        }        // 组装最终的SQL        String str = this.buildFinalSQL(strCostCenterNotNull, strCostCenterNull, containCostCenter);        return str;    }    /**     * 根据or前后两个SQL，及是否包含costcenter的情况进行最后sql的组装     *     * @param strCostCenterNotNull     *            or前面的SQL     * @param strCostCenterNull     *            or后面的SQL     * @param containCostCenter     *            是否包含costcenter     * @return 最终SQL     */    private String buildFinalSQL(CMSqlBuilder strCostCenterNotNull, CMSqlBuilder strCostCenterNull,            boolean containCostCenter) {        // 删除最后一个“And”        StringBuilder strCostCenterNotNullClone = new StringBuilder(strCostCenterNotNull.toString());        StringBuilder strCostCenterNullClone = new StringBuilder(strCostCenterNull.toString());        strCostCenterNotNullClone.delete(strCostCenterNotNullClone.lastIndexOf(" And "),                strCostCenterNotNullClone.length());        strCostCenterNullClone.delete(strCostCenterNullClone.lastIndexOf(" And "), strCostCenterNullClone.length());        CMSqlBuilder str = new CMSqlBuilder();        if (containCostCenter) {            // 如果该fields中包含ccostcenterid，则将or前后的SQL连接            str.append(" ( " + strCostCenterNotNullClone.toString() + " ) ");            str.or();            str.append(" ( " + strCostCenterNullClone.toString() + " ) ");        }        else {            // 如果该fields中不包含ccostcenterid，则直接返回or前面的部分            str.append(strCostCenterNotNullClone.toString());        }        return str.toString();    }    /**     * 返回UAP查询接口     *     * @return IUAPQueryBS     */    private IUAPQueryBS getUAPQueryService() {        return NCLocator.getInstance().lookup(IUAPQueryBS.class);    }    /**     * 返回材料、作业、费用子表拼成的key     *     * @param values     *            查询结果集     * @return key     */    private String getMapKeyThreeTable(Object[] values) {        if (values == null) {            return null;        }        StringBuilder str = new StringBuilder();        // 将属性中除了价格的其他值拼在一起作为key，其中String[]的最后一个值是价格        for (int i = 0; i < values.length - 1; i++) {            if (values[i] != null && values[i] != "~") {                str.append(values[i]);            }        }        return str.toString();    }    /**     * 将nc.cm.pub.price.PriceParamVO转化成价格库查询价格专用VO     *     * @param priceParamVO     *            nc.cm.pub.price.PriceParamVO     * @return 价格库查询价格专用VO     */    public MaterialPriceBaseQueryPriceParamVO change2PriceParamVO(AquirePriceParamVO priceParamVO) {        MaterialPriceBaseQueryPriceParamVO newPriceParamVO = new MaterialPriceBaseQueryPriceParamVO();        newPriceParamVO.setPkOrg(priceParamVO.getPkOrg());        newPriceParamVO.setCpricebaseid(priceParamVO.getPricebasepk());        newPriceParamVO.setItemid(priceParamVO.getItemid());        return newPriceParamVO;    }    /**     * 将nc.cm.pub.price.PriceParamVO数组转化成价格库查询价格专用VO数组     *     * @param priceParamVOs     *            nc.cm.pub.price.PriceParamVO[]     * @return 价格库查询价格专用VOs     */    public MaterialPriceBaseQueryPriceParamVO[] change2PriceParamVOs(AquirePriceParamVO[] priceParamVOs) {        MaterialPriceBaseQueryPriceParamVO[] newPriceParamVOs =                new MaterialPriceBaseQueryPriceParamVO[priceParamVOs.length];        for (int i = 0; i < priceParamVOs.length; i++) {            newPriceParamVOs[i] = this.change2PriceParamVO(priceParamVOs[i]);        }        return newPriceParamVOs;    }    /**     * 拼成map的key，调用该方法来得到查询价格的结果(Map<key, double> 中根据该方法获得key，从而得到相应价格)     *     * @param priceParamVO     *            参数vo     * @return key值     */    public static String getMapKey(AquirePriceParamVO priceParamVO) {        if (priceParamVO.getPkCostCenter() == null) {            // 如果成本中心为空，则不将其加入key字符串中            return priceParamVO.getPkOrg() + priceParamVO.getPricebasepk() + priceParamVO.getItemid();        }        // 否则，将所有都加入key字符串中        return priceParamVO.getPkOrg() + priceParamVO.getPricebasepk() + priceParamVO.getPkCostCenter()                + priceParamVO.getItemid();    }}