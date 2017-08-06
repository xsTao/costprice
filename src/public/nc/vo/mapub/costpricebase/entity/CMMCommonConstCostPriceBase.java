/**
 *
 */
package nc.vo.mapub.costpricebase.entity;

/**
 * @since v6.3
 * @version 2017年7月20日 下午7:44:07
 * @author Administrator
 */
public class CMMCommonConstCostPriceBase {

    public static final String CCOSTPRICEID = "ccostpriceid";

    public static final String PK_GROUP = "pk_group";

    public static final String PK_ORG = "pk_org";

    public static final String PK_ORG_V = "pk_org_v";

    public static final String VPRICELIBCODE = "vpricelibcode";

    public static final String VPRICELIBNAME = "vpricelibname";

    public static final String ANNUAL = "annual";

    public static final String VPERIOD = "vperiod";

    public static final String VPRODUCTCODE = "vproductcode";

    public static final String VPRODUCTNAME = "vproductname";

    public static final String VREMARK = "vremark";

    public static final String CREATOR = "creator";

    public static final String CREATETIME = "createtime";

    public static final String MODIFIER = "modifier";

    public static final String MODIFIEDTIME = "modifiedtime";

    public static final String CCOSTPRICEBID = "itempks.ccostprice_bid";// 费用价格库明细

    public static final String CELEMENTID = "itempks.celementid";// 核算要素编码

    /** 核算要素组 */
    public static final String CELEMENTS = "celements";

    public static final String DPRICE = "itempks.dprice";// 金额

    public static final String VBFREE1 = "itempks.vbfree1";

    public static final String VBFREE2 = "itempks.vbfree2";

    public static final String VBFREE3 = "itempks.vbfree3";

    public static final String VBFREE4 = "itempks.vbfree4";

    /** 管控范围控制的查询条件 */
    public static final String[] ALL_QUERY = new String[] {
        CMMCommonConstCostPriceBase.CCOSTPRICEBID, CMMCommonConstCostPriceBase.CCOSTPRICEID,
        CMMCommonConstCostPriceBase.ANNUAL, CMMCommonConstCostPriceBase.CELEMENTID,
        CMMCommonConstCostPriceBase.CREATETIME, CMMCommonConstCostPriceBase.CREATOR,
        CMMCommonConstCostPriceBase.MODIFIEDTIME, CMMCommonConstCostPriceBase.MODIFIER,
        CMMCommonConstCostPriceBase.CELEMENTS, CMMCommonConstCostPriceBase.DPRICE, CMMCommonConstCostPriceBase.PK_ORG,
        CMMCommonConstCostPriceBase.PK_GROUP, CMMCommonConstCostPriceBase.VBFREE1, CMMCommonConstCostPriceBase.VBFREE2,
        CMMCommonConstCostPriceBase.VBFREE3, CMMCommonConstCostPriceBase.VBFREE4, CMMCommonConstCostPriceBase.VPERIOD,
        CMMCommonConstCostPriceBase.VPRICELIBCODE, CMMCommonConstCostPriceBase.VPRICELIBNAME,
        CMMCommonConstCostPriceBase.VPRODUCTCODE, CMMCommonConstCostPriceBase.VPRODUCTNAME,
        CMMCommonConstCostPriceBase.VREMARK, CMMCommonConstCostPriceBase.PK_ORG_V
    };
}
