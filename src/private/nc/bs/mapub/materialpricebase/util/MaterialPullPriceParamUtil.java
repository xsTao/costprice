package nc.bs.mapub.materialpricebase.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bd.framework.base.CMValueCheck;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.mapub.acquirepricelog.pub.IAcquirePricelogPubService;
import nc.vo.cmpub.framework.assistant.CMAssInfoItemVO;
import nc.vo.cmpub.framework.assistant.CMAssInfoParamVO;
import nc.vo.cmpub.framework.assistant.CMMarAssInfoVO;
import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.mapub.framework.entity.acquireprice.AcquirePriceParam;
import nc.vo.mapub.framework.entity.acquireprice.AcquirePriceReturn;
import nc.vo.mapub.framework.entity.acquireprice.AcquireTypeEnum;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceResult;
import nc.vo.mapub.materialpricebase.enumeration.PriceSourceEnum;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * 物料价格库
 * 取价参数构造类
 *
 * @since 6.36
 * @version 2014-12-8 上午10:59:04
 * @author zhangchd
 */
public class MaterialPullPriceParamUtil {
    private MaterialPriceBaseHeadVO materialPriceBaseHeadVO;

    private MaterialPriceBaseBodyVO[] materialPriceSourcesBodyVO;

    private MaterialPriceBaseAggVO[] materialPriceBaseAggVO;

    /**
     * 构造函数
     *
     * @param materialPriceBaseAggVO
     * @param materialPriceBaseHeadVO
     * @param materialPriceSourcesBodyVO
     */
    public MaterialPullPriceParamUtil(MaterialPriceBaseAggVO[] materialPriceBaseAggVO,
            MaterialPriceBaseHeadVO materialPriceBaseHeadVO, MaterialPriceBaseBodyVO[] materialPriceSourcesBodyVO) {
        this.materialPriceBaseAggVO = materialPriceBaseAggVO;
        this.materialPriceBaseHeadVO = materialPriceBaseHeadVO;
        this.materialPriceSourcesBodyVO = materialPriceSourcesBodyVO;
    }

    /**
     * 获取取价参数
     *
     * @param priceParamVO
     * @return List
     */
    @SuppressWarnings("boxing")
    public List<AcquirePriceParam> getMaterialPullPriceParam(MaterialPullPriceAggVO priceParamVO) {
        List<AcquirePriceParam> matAssPriceSource = new ArrayList<AcquirePriceParam>();

        if (CMValueCheck.isNotEmpty(this.materialPriceBaseHeadVO)
                && CMValueCheck.isNotEmpty(this.materialPriceSourcesBodyVO)) {

            for (MaterialPriceBaseBodyVO item : this.materialPriceSourcesBodyVO) {
                // 表体价格来源
                String vpricesourcenum = item.getVpricesourcenum();
                List<String> priceSourceList = new ArrayList<String>();
                if (CMValueCheck.isNotEmpty(vpricesourcenum)) {
                    String[] priceSources = vpricesourcenum.split("\\,");
                    for (String priceSource : priceSources) {
                        // if (priceSource.endsWith("]")) {
                        // String[] splitStrings = priceSource.split("\\[");
                        //
                        // String priceSourceVO = splitStrings[0];
                        // priceSourceList.add(priceSourceVO);
                        // }
                        // else {
                        priceSourceList.add(priceSource);
                        // }
                    }
                }
                else if (CMValueCheck.isNotEmpty(this.materialPriceBaseHeadVO.getVpricesourcenum())) {
                    String[] priceSourcesHead = this.materialPriceBaseHeadVO.getVpricesourcenum().split("\\,");
                    for (String priceSource : priceSourcesHead) {
                        // if (priceSource.endsWith("]")) {
                        // String[] splitStrings = priceSource.split("\\[");
                        //
                        // String priceSourceVO = splitStrings[0];
                        // priceSourceList.add(priceSourceVO);
                        // }
                        // else {
                        priceSourceList.add(priceSource);
                        // }
                    }
                }

                if (priceSourceList.size() > 0) {
                    // 构建取价参数
                    for (int i = 0; i < priceSourceList.size(); i++) {
                        // 取价参数
                        AcquirePriceParam priceParam = new AcquirePriceParam();
                        String pk_org = null;

                        if (priceSourceList.get(i).indexOf("[") > 0) {
                            String[] splitStrings = priceSourceList.get(i).split("\\[");
                            pk_org = splitStrings[1].substring(0, splitStrings[1].lastIndexOf("]"));

                            priceParam.setIsourcetype(Integer.valueOf(splitStrings[0]));
                        }
                        else {
                            priceParam.setIsourcetype(Integer.valueOf(priceSourceList.get(i)));
                        }

                        if (String.valueOf(PriceSourceEnum.STDCOST.toIntValue()).equals(
                                String.valueOf(priceParam.getIsourcetype()))
                                || String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(
                                        String.valueOf(priceParam.getIsourcetype()))) {
                            priceParam.setPk_org(item.getPk_org()); // 业务单元
                        }
                        else {
                            priceParam.setPk_org(pk_org);
                        }

                        if (String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(
                                String.valueOf(priceParam.getIsourcetype()))) {
                            AcquirePriceReturn acquirePriceReturn = new AcquirePriceReturn();
                            acquirePriceReturn.setNprice(item.getNprice());
                            acquirePriceReturn.setIsourcetype(PriceSourceEnum.MANUAL.toIntValue());
                            priceParam.setAcquirePriceReturn(acquirePriceReturn); // 【手工录入】设置预制价
                        }

                        priceParam.setPk_group(item.getPk_group()); // 集团
                        priceParam.setCmaterialpriceid(this.materialPriceBaseHeadVO.getCmaterialpriceid()); // 表头主键

                        priceParam.setAcquireType(AcquireTypeEnum.MATPRICEBASE);// 取价类型【物料价格库】
                        priceParam.setPrioritylevel(String.valueOf(i + 1)); // 优先级
                        priceParam.setPk_currtype(this.materialPriceBaseHeadVO.getCcrrencyid()); // 币种

                        if (String.valueOf(PriceSourceEnum.STDCOST.toIntValue()).equals(
                                String.valueOf(priceParam.getIsourcetype()))) {
                            priceParam.setCcosttypeid(priceParamVO.getParentVO().getCosttype());// 成本类型【标准成本取价】
                        }
                        else if (String.valueOf(PriceSourceEnum.PINGJUNCAIGOURUKU.toIntValue()).equals(
                                String.valueOf(priceParam.getIsourcetype()))) {
                            priceParam.setBegindate(priceParamVO.getParentVO().getCbegindate());// 开始期间【平均采购入库单价取价】
                            priceParam.setEnddate(priceParamVO.getParentVO().getCenddate());// 截止期间【平均采购入库单价取价】
                            priceParam.setCperiodScheme(priceParamVO.getParentVO().getCperiodscheme()); // 期间方案【平均采购入库单价取价】
                        }
                        else if (String.valueOf(PriceSourceEnum.FORWARD.toIntValue()).equals(
                                String.valueOf(priceParam.getIsourcetype()))) {
                            priceParam.setCperiod(priceParamVO.getParentVO().getCperiod());// 开始期间【最新结存取价】
                            priceParam.setCperiodScheme(priceParamVO.getParentVO().getCperiodscheme()); // 期间方案【最新结存取价】
                        }

                        // 扩展属性
                        CMAssInfoParamVO assInfoParam = new CMAssInfoParamVO();
                        // 表体属性
                        assInfoParam.setItemMarAssInfoVO(this.convertItemMarAssInfo(item));
                        priceParam.setAssInfoParamVO(assInfoParam);

                        matAssPriceSource.add(priceParam);
                    }
                }
            }
        }

        return matAssPriceSource;
    }

    /**
     * 获取返回结果
     *
     * @param updatematerialPriceBaseAggVO
     * @return MaterialPullPriceResult
     */
    public MaterialPullPriceResult getMaterialPullPriceResult(MaterialPriceBaseAggVO[] updatematerialPriceBaseAggVO) {
        MaterialPullPriceResult materialPullPriceResult = new MaterialPullPriceResult();

        if (CMValueCheck.isNotEmpty(updatematerialPriceBaseAggVO)) {
            materialPullPriceResult.setMaterialPriceBaseAggVO(updatematerialPriceBaseAggVO);

            // 1、 错误信息
            int errorCount = 0;
            String errorInfo = null;
            for (MaterialPriceBaseAggVO aggVO : updatematerialPriceBaseAggVO) {
                MaterialPriceBaseBodyVO[] itemVO = (MaterialPriceBaseBodyVO[]) aggVO.getAllChildrenVO();
                if (CMValueCheck.isNotEmpty(itemVO)) {
                    for (MaterialPriceBaseBodyVO item : itemVO) {
                        if (CMValueCheck.isEmpty(item.getNprice())) {
                            errorCount++;
                        }
                    }
                }
            }

            if (errorCount >= 1) {
                errorInfo =
                        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0327")/* 取价完成, 有 */
                                + errorCount
                                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0328")/*
                                                                                                                      * 行物料取价未成功
                                                                                                                      * ！
                                                                                                                      */;
            }
            else {
                errorInfo = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0329")/* 取价完成！ */;
            }

            // 2、错误日志
            CMSqlBuilder whereSql = new CMSqlBuilder();
            whereSql.append(" and ");
            whereSql.append(AcquirePriceLogVO.VACQUIRETYPE, "MATPRICEBASE");
            whereSql.append(" and ");
            whereSql.append(AcquirePriceLogVO.CMATERIALPRICEID, this.materialPriceBaseHeadVO.getCmaterialpriceid());
            // String orderby = "  order by cmaterialid ";

            // String orderby =
            // "  order by cmaterialid,cprojectid,cvendorid,cproductorid,ccustomerid,vfree1,vfree2,vfree3,vfree4,vfree5,vfree6,vfree7,vfree8,vfree9,vfree10 ";

            VOQuery<AcquirePriceLogVO> voquery = new VOQuery<AcquirePriceLogVO>(AcquirePriceLogVO.class);
            AcquirePriceLogVO[] acquirePriceLogVO = voquery.query(whereSql.toString(), null);

            materialPullPriceResult.setAcquirePriceLogVO(acquirePriceLogVO); // 错误日志
            materialPullPriceResult.setResultInfo(errorInfo);// 结果信息

        }

        return materialPullPriceResult;
    }

    /**
     * 删除错误日志
     *
     * @param priceParams
     * @throws BusinessException
     */
    public void deleteErrorLog() throws BusinessException {
        IAcquirePricelogPubService logService = NCLocator.getInstance().lookup(IAcquirePricelogPubService.class);
        logService.deletePriceLog();
    }

    /**
     * 获取更新价格后的Aggvo
     *
     * @param acquirePriceCurrentResult
     * @return MaterialPriceBaseAggVO
     */
    @SuppressWarnings("boxing")
    public MaterialPriceBaseAggVO[] getUpdatematerialPriceBaseAggVO(
            Map<CMAssInfoParamVO, AcquirePriceReturn> acquirePriceCurrentResult) {

        Map<String, AcquirePriceReturn> stringByPriceReturn = new HashMap<String, AcquirePriceReturn>();

        for (Map.Entry<CMAssInfoParamVO, AcquirePriceReturn> map : acquirePriceCurrentResult.entrySet()) {
            String materialAndAssString = this.convertMarAssInfoString(map.getKey().getItemMarAssInfoVO());
            stringByPriceReturn.put(materialAndAssString, map.getValue());
        }

        // 给表体赋值单价
        for (MaterialPriceBaseBodyVO item : this.materialPriceSourcesBodyVO) {
            AcquirePriceReturn acquirePriceReturn = stringByPriceReturn.get(this.convertString(item));
            // 手工录入的不能清空
            if (CMValueCheck.isEmpty(acquirePriceReturn)) {
                // 1.表体价格来源有值
                Object priceSourceObject = item.getVpricesourcenum();
                if (CMValueCheck.isNotEmpty(priceSourceObject)) {
                    if (!String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(priceSourceObject.toString())) {
                        item.setNprice(null);
                        item.setVpricesourcerealnum(null);
                        item.setVpricesourcereal(null);
                    }
                }
                else {
                    // 2.表体价格来源没值，走表头
                    Object priceSourceHeadObject = this.materialPriceBaseHeadVO.getVpricesourcenum();
                    if (CMValueCheck.isNotEmpty(priceSourceHeadObject)) {
                        if (!String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(
                                priceSourceHeadObject.toString())) {
                            item.setNprice(null);
                            item.setVpricesourcerealnum(null);
                            item.setVpricesourcereal(null);
                        }
                    }
                }
            }
            else {
                // 手工录入时，取价不赋值
                if (PriceSourceEnum.MANUAL.toIntValue() != acquirePriceReturn.getIsourcetype()) {
                    item.setNprice(acquirePriceReturn.getNprice()); // 单价
                    // 实际价格来源
                    String priceSourcesReal = null;
                    // 业务单元
                    String org = acquirePriceReturn.getPk_org();
                    if (CMValueCheck.isNotEmpty(org)) {
                        if (PriceSourceEnum.STDCOST.toIntValue() != acquirePriceReturn.getIsourcetype()) {
                            priceSourcesReal = acquirePriceReturn.getIsourcetype() + "[" + org + "]";
                        }
                        else {
                            priceSourcesReal = String.valueOf(acquirePriceReturn.getIsourcetype());
                        }
                    }
                    else {
                        priceSourcesReal = String.valueOf(acquirePriceReturn.getIsourcetype());
                    }
                    item.setVpricesourcerealnum(priceSourcesReal); // 实际价格来源
                }
            }
        }

        return this.materialPriceBaseAggVO;
    }

    private CMMarAssInfoVO convertItemMarAssInfo(CircularlyAccessibleValueObject itemvo) {
        CMMarAssInfoVO marAssinfo = new CMMarAssInfoVO();
        marAssinfo.setCmaterialid(this.getValue(itemvo.getAttributeValue("cmaterialid")));
        marAssinfo.setCcustomerid(this.getValue(itemvo.getAttributeValue("ccustomerid")));
        marAssinfo.setCvendorid(this.getValue(itemvo.getAttributeValue("cvendorid")));
        marAssinfo.setCproductorid(this.getValue(itemvo.getAttributeValue("cproductorid")));
        marAssinfo.setCprojectid(this.getValue(itemvo.getAttributeValue("cprojectid")));
        marAssinfo.setVfree1(this.getValue(itemvo.getAttributeValue("vbfree1")));
        marAssinfo.setVfree2(this.getValue(itemvo.getAttributeValue("vbfree2")));
        marAssinfo.setVfree3(this.getValue(itemvo.getAttributeValue("vbfree3")));
        marAssinfo.setVfree4(this.getValue(itemvo.getAttributeValue("vbfree4")));
        marAssinfo.setVfree5(this.getValue(itemvo.getAttributeValue("vbfree5")));
        marAssinfo.setVfree6(this.getValue(itemvo.getAttributeValue("vbfree6")));
        marAssinfo.setVfree7(this.getValue(itemvo.getAttributeValue("vbfree7")));
        marAssinfo.setVfree8(this.getValue(itemvo.getAttributeValue("vbfree8")));
        marAssinfo.setVfree9(this.getValue(itemvo.getAttributeValue("vbfree9")));
        marAssinfo.setVfree10(this.getValue(itemvo.getAttributeValue("vbfree10")));
        return marAssinfo;
    }

    private String getValue(Object value) {
        if (value == null) {
            return null;
        }
        return (String) value;
    }

    /**
     * @param item
     * @return String
     */
    @SuppressWarnings("boxing")
    private String convertString(MaterialPriceBaseBodyVO item) {
        StringBuffer string = new StringBuffer();
        for (int i = 0; i < MaterialPullPriceParamUtil.CM_AssInfo_Item.length; i++) {
            if (this.isNotNll(item.getAttributeValue(MaterialPullPriceParamUtil.CM_AssInfo_Item[i]))) {
                string.append(item.getAttributeValue(MaterialPullPriceParamUtil.CM_AssInfo_Item[i]));
            }
        }

        return string.toString();
    }

    /**
     * 转成字符串
     *
     * @param marAssInfo
     * @return String
     */
    @SuppressWarnings("boxing")
    private String convertMarAssInfoString(CMMarAssInfoVO marAssInfo) {
        StringBuffer string = new StringBuffer();
        for (int i = 0; i < CMMarAssInfoVO.CM_AssInfo_Item.length; i++) {
            if (this.isNotNll(marAssInfo.getAttributeValue(CMMarAssInfoVO.CM_AssInfo_Item[i]))) {
                string.append(marAssInfo.getAttributeValue(CMMarAssInfoVO.CM_AssInfo_Item[i]));
            }
        }

        return string.toString();
    }

    @SuppressWarnings({
        "boxing"
    })
    private Boolean isNotNll(Object s) {
        if (CMValueCheck.isNotEmpty(s)) {
            if (!s.toString().equals("~")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 辅助信息
     */
    public static final String[] CM_AssInfo_Item = new String[] {
        CMMarAssInfoVO.CMATERIALID, CMAssInfoItemVO.CCUSTOMERID, CMAssInfoItemVO.CVENDORID,
        CMAssInfoItemVO.CPRODUCTORID, CMAssInfoItemVO.CPROJECTID, CMAssInfoItemVO.VBFREE1, CMAssInfoItemVO.VBFREE2,
        CMAssInfoItemVO.VBFREE3, CMAssInfoItemVO.VBFREE4, CMAssInfoItemVO.VBFREE5, CMAssInfoItemVO.VBFREE6,
        CMAssInfoItemVO.VBFREE7, CMAssInfoItemVO.VBFREE8, CMAssInfoItemVO.VBFREE9, CMAssInfoItemVO.VBFREE10
    };

    public MaterialPriceBaseHeadVO getMaterialPriceBaseHeadVO() {
        return this.materialPriceBaseHeadVO;
    }

    public void setMaterialPriceBaseHeadVO(MaterialPriceBaseHeadVO materialPriceBaseHeadVO) {
        this.materialPriceBaseHeadVO = materialPriceBaseHeadVO;
    }

    public MaterialPriceBaseBodyVO[] getMaterialPriceSourcesBodyVO() {
        return this.materialPriceSourcesBodyVO;
    }

    public void setMaterialPriceSourcesBodyVO(MaterialPriceBaseBodyVO[] materialPriceSourcesBodyVO) {
        this.materialPriceSourcesBodyVO = materialPriceSourcesBodyVO;
    }

    public MaterialPriceBaseAggVO[] getMaterialPriceBaseAggVO() {
        return this.materialPriceBaseAggVO;
    }

    public void setMaterialPriceBaseAggVO(MaterialPriceBaseAggVO[] materialPriceBaseAggVO) {
        this.materialPriceBaseAggVO = materialPriceBaseAggVO;
    }

}
