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
 * ���ϼ۸��
 * ȡ�۲���������
 *
 * @since 6.36
 * @version 2014-12-8 ����10:59:04
 * @author zhangchd
 */
public class MaterialPullPriceParamUtil {
    private MaterialPriceBaseHeadVO materialPriceBaseHeadVO;

    private MaterialPriceBaseBodyVO[] materialPriceSourcesBodyVO;

    private MaterialPriceBaseAggVO[] materialPriceBaseAggVO;

    /**
     * ���캯��
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
     * ��ȡȡ�۲���
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
                // ����۸���Դ
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
                    // ����ȡ�۲���
                    for (int i = 0; i < priceSourceList.size(); i++) {
                        // ȡ�۲���
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
                            priceParam.setPk_org(item.getPk_org()); // ҵ��Ԫ
                        }
                        else {
                            priceParam.setPk_org(pk_org);
                        }

                        if (String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(
                                String.valueOf(priceParam.getIsourcetype()))) {
                            AcquirePriceReturn acquirePriceReturn = new AcquirePriceReturn();
                            acquirePriceReturn.setNprice(item.getNprice());
                            acquirePriceReturn.setIsourcetype(PriceSourceEnum.MANUAL.toIntValue());
                            priceParam.setAcquirePriceReturn(acquirePriceReturn); // ���ֹ�¼�롿����Ԥ�Ƽ�
                        }

                        priceParam.setPk_group(item.getPk_group()); // ����
                        priceParam.setCmaterialpriceid(this.materialPriceBaseHeadVO.getCmaterialpriceid()); // ��ͷ����

                        priceParam.setAcquireType(AcquireTypeEnum.MATPRICEBASE);// ȡ�����͡����ϼ۸�⡿
                        priceParam.setPrioritylevel(String.valueOf(i + 1)); // ���ȼ�
                        priceParam.setPk_currtype(this.materialPriceBaseHeadVO.getCcrrencyid()); // ����

                        if (String.valueOf(PriceSourceEnum.STDCOST.toIntValue()).equals(
                                String.valueOf(priceParam.getIsourcetype()))) {
                            priceParam.setCcosttypeid(priceParamVO.getParentVO().getCosttype());// �ɱ����͡���׼�ɱ�ȡ�ۡ�
                        }
                        else if (String.valueOf(PriceSourceEnum.PINGJUNCAIGOURUKU.toIntValue()).equals(
                                String.valueOf(priceParam.getIsourcetype()))) {
                            priceParam.setBegindate(priceParamVO.getParentVO().getCbegindate());// ��ʼ�ڼ䡾ƽ���ɹ���ⵥ��ȡ�ۡ�
                            priceParam.setEnddate(priceParamVO.getParentVO().getCenddate());// ��ֹ�ڼ䡾ƽ���ɹ���ⵥ��ȡ�ۡ�
                            priceParam.setCperiodScheme(priceParamVO.getParentVO().getCperiodscheme()); // �ڼ䷽����ƽ���ɹ���ⵥ��ȡ�ۡ�
                        }
                        else if (String.valueOf(PriceSourceEnum.FORWARD.toIntValue()).equals(
                                String.valueOf(priceParam.getIsourcetype()))) {
                            priceParam.setCperiod(priceParamVO.getParentVO().getCperiod());// ��ʼ�ڼ䡾���½��ȡ�ۡ�
                            priceParam.setCperiodScheme(priceParamVO.getParentVO().getCperiodscheme()); // �ڼ䷽�������½��ȡ�ۡ�
                        }

                        // ��չ����
                        CMAssInfoParamVO assInfoParam = new CMAssInfoParamVO();
                        // ��������
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
     * ��ȡ���ؽ��
     *
     * @param updatematerialPriceBaseAggVO
     * @return MaterialPullPriceResult
     */
    public MaterialPullPriceResult getMaterialPullPriceResult(MaterialPriceBaseAggVO[] updatematerialPriceBaseAggVO) {
        MaterialPullPriceResult materialPullPriceResult = new MaterialPullPriceResult();

        if (CMValueCheck.isNotEmpty(updatematerialPriceBaseAggVO)) {
            materialPullPriceResult.setMaterialPriceBaseAggVO(updatematerialPriceBaseAggVO);

            // 1�� ������Ϣ
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
                        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0327")/* ȡ�����, �� */
                                + errorCount
                                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0328")/*
                                                                                                                      * ������ȡ��δ�ɹ�
                                                                                                                      * ��
                                                                                                                      */;
            }
            else {
                errorInfo = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0329")/* ȡ����ɣ� */;
            }

            // 2��������־
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

            materialPullPriceResult.setAcquirePriceLogVO(acquirePriceLogVO); // ������־
            materialPullPriceResult.setResultInfo(errorInfo);// �����Ϣ

        }

        return materialPullPriceResult;
    }

    /**
     * ɾ��������־
     *
     * @param priceParams
     * @throws BusinessException
     */
    public void deleteErrorLog() throws BusinessException {
        IAcquirePricelogPubService logService = NCLocator.getInstance().lookup(IAcquirePricelogPubService.class);
        logService.deletePriceLog();
    }

    /**
     * ��ȡ���¼۸���Aggvo
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

        // �����帳ֵ����
        for (MaterialPriceBaseBodyVO item : this.materialPriceSourcesBodyVO) {
            AcquirePriceReturn acquirePriceReturn = stringByPriceReturn.get(this.convertString(item));
            // �ֹ�¼��Ĳ������
            if (CMValueCheck.isEmpty(acquirePriceReturn)) {
                // 1.����۸���Դ��ֵ
                Object priceSourceObject = item.getVpricesourcenum();
                if (CMValueCheck.isNotEmpty(priceSourceObject)) {
                    if (!String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(priceSourceObject.toString())) {
                        item.setNprice(null);
                        item.setVpricesourcerealnum(null);
                        item.setVpricesourcereal(null);
                    }
                }
                else {
                    // 2.����۸���Դûֵ���߱�ͷ
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
                // �ֹ�¼��ʱ��ȡ�۲���ֵ
                if (PriceSourceEnum.MANUAL.toIntValue() != acquirePriceReturn.getIsourcetype()) {
                    item.setNprice(acquirePriceReturn.getNprice()); // ����
                    // ʵ�ʼ۸���Դ
                    String priceSourcesReal = null;
                    // ҵ��Ԫ
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
                    item.setVpricesourcerealnum(priceSourcesReal); // ʵ�ʼ۸���Դ
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
     * ת���ַ���
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
     * ������Ϣ
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
