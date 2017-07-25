package nc.pubimpl.mapub.materialpricebase.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bd.framework.base.CMValueCheck;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.framework.common.NCLocator;
import nc.cmpub.business.enumeration.CMPubPriceSourceEnum;
import nc.impl.mapub.materialpricebase.queryprice.MaterialPriceBaseQueryPrice;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.mapub.materialpricebase.pub.IMaterialPriceBasePubQueryServiceForPrice;
import nc.pubitf.org.cache.IAccountingBookPubService_C;
import nc.pubitf.org.cache.IStockOrgPubService_C;
import nc.pubitf.uapbd.CurrencyRateUtilHelper;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.vo.cmpub.business.aquireprice.AquirePriceParamVO;
import nc.vo.cmpub.business.enumeration.PriceResTypeEnum;
import nc.vo.cmpub.framework.assistant.CMMarAssInfoVO;
import nc.vo.mapub.framework.entity.acquireprice.AcquirePriceParam;
import nc.vo.mapub.framework.entity.acquireprice.AcquirePriceReturn;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseQueryPriceParamVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;

//import nc.impl.sca.pricebase.queryprice.PriceBaseQueryPrice;
//import nc.vo.sca.pricebase.entity.PriceBaseQueryPriceParamVO;

/**
 * <b> 查询价格库内的价格的实现 </b>
 * <p>
 * 查询价格库内的价格的实现
 * </p>
 * 创建日期:2010-6-2
 *
 * @author:jilu
 */
public class MaterialPriceBasePubQueryServiceForPriceImpl implements IMaterialPriceBasePubQueryServiceForPrice {

    /**
     * 提供的接口：根据价格库查询VO，查询返回子表的价格Map<key, 价格>，并不分查询的是什么价格 注： 1、查询VO中一定要设置来源类型：ResType，否则查不出该VO的价格
     * 2、结果Map中的key可用PriceBaseQueryPriceUtil类中的getMapKey()方法获取，价格如果没取到，则价格为null
     *
     * @param priceParamVOs
     *            查询参数VO数组
     * @return Map<String, UFDouble> Map<key, 价格>
     * @throws BusinessException
     *             异常
     */
    @Override
    public Map<String, UFDouble> getAllPriceMap(AquirePriceParamVO[] priceParamVOs) throws BusinessException {

        List<AquirePriceParamVO> paramVOsForStuff = new ArrayList<AquirePriceParamVO>();
        // List<PriceParamVO> paramVOsForActivity = new ArrayList<PriceParamVO>();
        // List<PriceParamVO> paramVOsForFactor = new ArrayList<PriceParamVO>();

        // 将传进来的priceParamVOs按照类型分别放入3个List中
        for (AquirePriceParamVO paramVO : priceParamVOs) {

            if (paramVO.getRestype() == null) {
                continue;
            }

            if (paramVO.getRestype().equals(PriceResTypeEnum.STUFF)) {
                paramVOsForStuff.add(paramVO);
            }
            // else if (paramVO.getRestype().equals(PriceResTypeEnum.ACTV)) {
            // paramVOsForActivity.add(paramVO);
            // }
            // else if (paramVO.getRestype().equals(PriceResTypeEnum.FEE)) {
            // paramVOsForFactor.add(paramVO);
            // }
        }

        // 三种查询采用一个PriceBaseQueryPriceBussiness，这样可以减少临时表建立次数
        MaterialPriceBaseQueryPrice queryPriceBussiness = new MaterialPriceBaseQueryPrice();
        // 将传进来的PriceParamVO转化成价格库查询专用VO
        MaterialPriceBaseQueryPriceParamVO[] queryPriceParamVOs = null;

        // 针对3种查询一一进行查询，并将结果组成一个Map
        Map<String, UFDouble> resultMap = new HashMap<String, UFDouble>();

        // 针对物料价格的查询
        if (paramVOsForStuff.size() > 0) {

            // 执行查询
            // 将传进来的PriceParamVO转化成价格库查询专用VO
            queryPriceParamVOs =
                    queryPriceBussiness.change2PriceParamVOs(paramVOsForStuff.toArray(new AquirePriceParamVO[0]));
            resultMap.putAll(queryPriceBussiness.queryPriceInMaterialPriceBase(queryPriceParamVOs,
                    PriceResTypeEnum.STUFF));
        }

        // // 针对作业价格的查询
        // if (paramVOsForActivity.size() > 0) {
        //
        // // 将传进来的PriceParamVO转化成价格库查询专用VO
        // queryPriceParamVOs =
        // queryPriceBussiness.change2PriceParamVOs(paramVOsForActivity.toArray(new PriceParamVO[0]));
        // resultMap.putAll(queryPriceBussiness.queryPriceInPriceBase(queryPriceParamVOs, PriceResTypeEnum.ACTV));
        // }
        //
        // // 针对费用价格的查询
        // if (paramVOsForFactor.size() > 0) {
        //
        // // 将传进来的PriceParamVO转化成价格库查询专用VO
        // queryPriceParamVOs =
        // queryPriceBussiness.change2PriceParamVOs(paramVOsForFactor.toArray(new PriceParamVO[0]));
        // resultMap.putAll(queryPriceBussiness.queryPriceInPriceBase(queryPriceParamVOs, PriceResTypeEnum.FEE));
        // }

        return resultMap;
    }

    @Override
    public Map<AcquirePriceParam, AcquirePriceReturn> getPriceByMarAss(List<AcquirePriceParam> priceParams)
            throws BusinessException {
        String pk_org = priceParams.get(0).getPk_org();
        // 传入工厂对应的币种，用来校验传入工厂的币种与价格库币种是否相同，如果不相同，价格取不到并返回错误信息
        String pk_currency = this.getOrgCurrtype(pk_org);
        // 物料+辅助属性拼接好值，用来作为联合查询条件
        Set<String> marAssSet = new HashSet<String>();
        for (AcquirePriceParam priceParam : priceParams) {
            String comMarAssValue = "";
            for (int i = 0; i < CMMarAssInfoVO.CM_AssInfo_Item.length; i++) {
                Object marAssValue =
                        priceParam.getAssInfoParamVO().getItemMarAssInfoVO()
                                .getAttributeValue(CMMarAssInfoVO.CM_AssInfo_Item[i]);
                if (!CMValueCheck.isEmpty(marAssValue)) {
                    comMarAssValue = comMarAssValue + marAssValue;
                }
            }
            comMarAssValue = comMarAssValue + priceParam.getCmaterialpriceid();
            marAssSet.add(comMarAssValue);
        }
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append(" select ");
        sql.append(" mb.cmaterialid,mb.ccustomerid,mb.cvendorid,mb.cproductorid,mb.cprojectid,mb.vbfree1,mb.vbfree2,mb.vbfree3,mb.vbfree4,mb.vbfree5,mb.vbfree6,mb.vbfree7,mb.vbfree8,mb.vbfree9,mb.vbfree10,mb.nprice,mb.cmaterialpriceid,m.CCRRENCYID ");
        sql.append(" from mapub_materialpricebase m,mapub_materialpricebase_b mb");
        sql.append(" where m.CMATERIALPRICEID = mb.CMATERIALPRICEID and m.dr=0 and mb.dr=0 and ");
        sql.append(
                "replace(mb.cmaterialid||mb.ccustomerid||mb.cvendorid||mb.cproductorid||mb.cprojectid||mb.vbfree1||mb.vbfree2||mb.vbfree3||mb.vbfree4||mb.vbfree5||mb.vbfree6||mb.vbfree7||mb.vbfree8||mb.vbfree9||mb.vbfree10||mb.cmaterialpriceid ,'~','')",
                marAssSet.toArray(new String[marAssSet.size()]));
        DataAccessUtils util = new DataAccessUtils();
        IRowSet rowSet = util.query(sql.toString());
        Map<CMMarAssInfoVO, Map<String, AcquirePriceReturn>> acquireResult =
                new HashMap<CMMarAssInfoVO, Map<String, AcquirePriceReturn>>();
        while (rowSet.next()) {
            // if (rowSet.getObject(15) != null) {
            CMMarAssInfoVO marAssInfo = new CMMarAssInfoVO();
            for (int i = 0; i < CMMarAssInfoVO.CM_AssInfo_Item.length; i++) {
                marAssInfo.setAttributeValue(CMMarAssInfoVO.CM_AssInfo_Item[i], rowSet.getObject(i));
            }
            AcquirePriceReturn priceReturn = new AcquirePriceReturn();
            priceReturn.setCmaterialpriceid(rowSet.getString(16));
            priceReturn.setPk_currtype(rowSet.getString(17));
            if (rowSet.getString(17) != null && pk_currency.equals(rowSet.getString(17))) {
                priceReturn.setNprice(rowSet.getUFDouble(15));
            }
            else {
                priceReturn.setErrorInfo(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                        "03810006-0330")/* 币种不一致！ */);
                priceReturn.setSolvePlan(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                        "03810006-0331")/* 使用币种与工厂对应财务组织主账簿本位币相同的价格库！ */);
            }
            Map<String, AcquirePriceReturn> matPriceReturn = new HashMap<String, AcquirePriceReturn>();
            if (acquireResult.get(marAssInfo) != null) {
                matPriceReturn = acquireResult.get(marAssInfo);
            }
            else {
                matPriceReturn = new HashMap<String, AcquirePriceReturn>();
            }
            matPriceReturn.put(rowSet.getString(16), priceReturn);
            acquireResult.put(marAssInfo, matPriceReturn);
            // }
        }
        // 取价结果
        Map<AcquirePriceParam, AcquirePriceReturn> marAssInfoMap = new HashMap<AcquirePriceParam, AcquirePriceReturn>();
        for (AcquirePriceParam priceParam : priceParams) {
            AcquirePriceReturn priceReturn = null;
            if (acquireResult.get(priceParam.getAssInfoParamVO().getItemMarAssInfoVO()) != null) {
                priceReturn =
                        acquireResult.get(priceParam.getAssInfoParamVO().getItemMarAssInfoVO()).get(
                                priceParam.getCmaterialpriceid());
            }
            if (priceReturn == null) {
                priceReturn = new AcquirePriceReturn();
                priceReturn.setCmaterialpriceid(priceParam.getCmaterialpriceid());
                priceReturn.setErrorInfo(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                        "03810006-0332")/* 未找到价格！ */);
                priceReturn.setSolvePlan(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                        "03810006-0333")/* 维护材料价格库价格！ */);
            }
            else {
                if (CMValueCheck.isEmpty(priceReturn.getNprice())) {
                    priceReturn.setErrorInfo(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                            "03810006-0332")/*
                             * 未找到价格！
                             */);
                    priceReturn.setSolvePlan(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                            "03810006-0333")/*
                             * 维护材料价格库价格
                             * ！
                             */);
                }
            }
            priceReturn.setPk_group(priceParam.getPk_group());
            priceReturn.setPk_org(priceParam.getPk_org());
            priceReturn.setIsourcetype(priceParam.getIsourcetype());
            marAssInfoMap.put(priceParam, priceReturn);
        }
        return marAssInfoMap;
    }

    private String getOrgCurrtype(String pk_org) throws BusinessException {
        IStockOrgPubService_C orgPubService = NCLocator.getInstance().lookup(IStockOrgPubService_C.class);
        // 得到库存组织对应的财务组织Map<库存组织id, 财务组织id>
        Map<String, String> financeOrgIDs = orgPubService.queryFinanceOrgIDsByStockOrgIDs(new String[] {
            pk_org
        });
        if (CMValueCheck.isEmpty(financeOrgIDs)) {
            return null;
        }
        IAccountingBookPubService_C bookPubService = NCLocator.getInstance().lookup(IAccountingBookPubService_C.class);
        // 批量根据财务组织主键找主财务核算账簿主键UAP
        Map<String, String> pkMainAccountBookMap =
                bookPubService.queryAccountingBookIDByFinanceOrgIDWithMainAccountBook(new String[] {
                    financeOrgIDs.get(pk_org)
                });
        if (CMValueCheck.isEmpty(pkMainAccountBookMap)) {
            return null;
        }
        String pk_accountingbook = pkMainAccountBookMap.get(financeOrgIDs.get(pk_org));
        CurrencyRateUtilHelper currencyHelper = CurrencyRateUtilHelper.getInstance();
        // 取财务核算账簿本位币
        String pk_currency = currencyHelper.getLocalCurrtypeByAccountingbookID(pk_accountingbook);
        return pk_currency;
    }

    /**
     * 查询物料价格库表头VO
     * 成本类型、成本BOM使用
     *
     * @param pkorg 业务单元
     * @param group 集团
     * @param date 日期
     * @return VO数组
     * @throws BusinessException
     */
    @Override
    @SuppressWarnings("rawtypes")
    public List<DefaultConstEnum> queryMaterialPriceBaseHeadVO(String[] pkorg, String group, UFDate date)
            throws BusinessException {
        if (CMValueCheck.isEmpty(pkorg) || CMValueCheck.isEmpty(group)) {
            return null;
        }

        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append(" and ");
        sql.append(MaterialPriceBaseHeadVO.PK_GROUP, group);
        sql.append(" and ");
        sql.append(MaterialPriceBaseHeadVO.PK_ORG, pkorg);
        sql.append(" and dr=0 ");

        if (CMValueCheck.isNotEmpty(date)) {
            sql.append(" and '");
            sql.append(date);
            sql.append("' >= ");
            sql.append(MaterialPriceBaseHeadVO.DBEGINDATE);
            sql.append(" and '");
            sql.append(date);
            sql.append("' <= ");
            sql.append(MaterialPriceBaseHeadVO.DENDDATE);
        }
        VOQuery result = new VOQuery<MaterialPriceBaseHeadVO>(MaterialPriceBaseHeadVO.class);
        List<DefaultConstEnum> listEnum = new ArrayList<DefaultConstEnum>();
        String valueString = null;
        String nameString = null;
        MaterialPriceBaseHeadVO[] headVOs = (MaterialPriceBaseHeadVO[]) result.query(sql.toString(), null);
        for (MaterialPriceBaseHeadVO headVO : headVOs) {
            valueString =
                    CMPubPriceSourceEnum.PRICEBASE.getEnumValue().getValue() + "[" + headVO.getCmaterialpriceid() + "]";
            nameString =
                    CMPubPriceSourceEnum.PRICEBASE.getName() + "[" + headVO.getVpricecode() + headVO.getVpricename()
                    + "]";
            listEnum.add(new DefaultConstEnum(valueString, nameString));
        }
        return listEnum;
    }
}
