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
 * <b> ��ѯ�۸���ڵļ۸��ʵ�� </b>
 * <p>
 * ��ѯ�۸���ڵļ۸��ʵ��
 * </p>
 * ��������:2010-6-2
 *
 * @author:jilu
 */
public class MaterialPriceBasePubQueryServiceForPriceImpl implements IMaterialPriceBasePubQueryServiceForPrice {

    /**
     * �ṩ�Ľӿڣ����ݼ۸���ѯVO����ѯ�����ӱ�ļ۸�Map<key, �۸�>�������ֲ�ѯ����ʲô�۸� ע�� 1����ѯVO��һ��Ҫ������Դ���ͣ�ResType������鲻����VO�ļ۸�
     * 2�����Map�е�key����PriceBaseQueryPriceUtil���е�getMapKey()������ȡ���۸����ûȡ������۸�Ϊnull
     *
     * @param priceParamVOs
     *            ��ѯ����VO����
     * @return Map<String, UFDouble> Map<key, �۸�>
     * @throws BusinessException
     *             �쳣
     */
    @Override
    public Map<String, UFDouble> getAllPriceMap(AquirePriceParamVO[] priceParamVOs) throws BusinessException {

        List<AquirePriceParamVO> paramVOsForStuff = new ArrayList<AquirePriceParamVO>();
        // List<PriceParamVO> paramVOsForActivity = new ArrayList<PriceParamVO>();
        // List<PriceParamVO> paramVOsForFactor = new ArrayList<PriceParamVO>();

        // ����������priceParamVOs�������ͷֱ����3��List��
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

        // ���ֲ�ѯ����һ��PriceBaseQueryPriceBussiness���������Լ�����ʱ��������
        MaterialPriceBaseQueryPrice queryPriceBussiness = new MaterialPriceBaseQueryPrice();
        // ����������PriceParamVOת���ɼ۸���ѯר��VO
        MaterialPriceBaseQueryPriceParamVO[] queryPriceParamVOs = null;

        // ���3�ֲ�ѯһһ���в�ѯ������������һ��Map
        Map<String, UFDouble> resultMap = new HashMap<String, UFDouble>();

        // ������ϼ۸�Ĳ�ѯ
        if (paramVOsForStuff.size() > 0) {

            // ִ�в�ѯ
            // ����������PriceParamVOת���ɼ۸���ѯר��VO
            queryPriceParamVOs =
                    queryPriceBussiness.change2PriceParamVOs(paramVOsForStuff.toArray(new AquirePriceParamVO[0]));
            resultMap.putAll(queryPriceBussiness.queryPriceInMaterialPriceBase(queryPriceParamVOs,
                    PriceResTypeEnum.STUFF));
        }

        // // �����ҵ�۸�Ĳ�ѯ
        // if (paramVOsForActivity.size() > 0) {
        //
        // // ����������PriceParamVOת���ɼ۸���ѯר��VO
        // queryPriceParamVOs =
        // queryPriceBussiness.change2PriceParamVOs(paramVOsForActivity.toArray(new PriceParamVO[0]));
        // resultMap.putAll(queryPriceBussiness.queryPriceInPriceBase(queryPriceParamVOs, PriceResTypeEnum.ACTV));
        // }
        //
        // // ��Է��ü۸�Ĳ�ѯ
        // if (paramVOsForFactor.size() > 0) {
        //
        // // ����������PriceParamVOת���ɼ۸���ѯר��VO
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
        // ���빤����Ӧ�ı��֣�����У�鴫�빤���ı�����۸������Ƿ���ͬ���������ͬ���۸�ȡ���������ش�����Ϣ
        String pk_currency = this.getOrgCurrtype(pk_org);
        // ����+��������ƴ�Ӻ�ֵ��������Ϊ���ϲ�ѯ����
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
                        "03810006-0330")/* ���ֲ�һ�£� */);
                priceReturn.setSolvePlan(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                        "03810006-0331")/* ʹ�ñ����빤����Ӧ������֯���˲���λ����ͬ�ļ۸�⣡ */);
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
        // ȡ�۽��
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
                        "03810006-0332")/* δ�ҵ��۸� */);
                priceReturn.setSolvePlan(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                        "03810006-0333")/* ά�����ϼ۸��۸� */);
            }
            else {
                if (CMValueCheck.isEmpty(priceReturn.getNprice())) {
                    priceReturn.setErrorInfo(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                            "03810006-0332")/*
                             * δ�ҵ��۸�
                             */);
                    priceReturn.setSolvePlan(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                            "03810006-0333")/*
                             * ά�����ϼ۸��۸�
                             * ��
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
        // �õ������֯��Ӧ�Ĳ�����֯Map<�����֯id, ������֯id>
        Map<String, String> financeOrgIDs = orgPubService.queryFinanceOrgIDsByStockOrgIDs(new String[] {
            pk_org
        });
        if (CMValueCheck.isEmpty(financeOrgIDs)) {
            return null;
        }
        IAccountingBookPubService_C bookPubService = NCLocator.getInstance().lookup(IAccountingBookPubService_C.class);
        // �������ݲ�����֯����������������˲�����UAP
        Map<String, String> pkMainAccountBookMap =
                bookPubService.queryAccountingBookIDByFinanceOrgIDWithMainAccountBook(new String[] {
                    financeOrgIDs.get(pk_org)
                });
        if (CMValueCheck.isEmpty(pkMainAccountBookMap)) {
            return null;
        }
        String pk_accountingbook = pkMainAccountBookMap.get(financeOrgIDs.get(pk_org));
        CurrencyRateUtilHelper currencyHelper = CurrencyRateUtilHelper.getInstance();
        // ȡ��������˲���λ��
        String pk_currency = currencyHelper.getLocalCurrtypeByAccountingbookID(pk_accountingbook);
        return pk_currency;
    }

    /**
     * ��ѯ���ϼ۸���ͷVO
     * �ɱ����͡��ɱ�BOMʹ��
     *
     * @param pkorg ҵ��Ԫ
     * @param group ����
     * @param date ����
     * @return VO����
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
