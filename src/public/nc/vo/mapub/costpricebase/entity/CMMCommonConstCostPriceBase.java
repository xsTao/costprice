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

    public static String CCOSTPRICEID = "ccostpriceid";

    public static String PK_GROUP = "pk_group";

    public static String PK_ORG = "pk_org";

    public static String PK_ORG_V = "pk_org_v";

    public static String VPRICELIBCODE = "vpricelibcode";

    public static String VPRICELIBNAME = "vpricelibname";

    public static String ANNUAL = "annual";

    public static String VPERIOD = "vperiod";

    public static String VPRODUCTCODE = "vproductcode";

    public static String VPRODUCTNAME = "vproductname";

    public static String VREMARK = "vremark";

    public static String CREATOR = "creator";

    public static String CREATETIME = "createtime";

    public static String MODIFIER = "modifier";

    public static String MODIFIEDTIME = "modifiedtime";

    public static String CCOSTPRICEBID = "itempks.ccostprice_bid";// 费用价格库明细

    public static String CELEMENTID = "itempks.celementid";// 核算要素编码

    public static String DPRICE = "itempks.dprice";// 金额

    public static String VBFREE1 = "itempks.vbfree1";

    public static String VBFREE2 = "itempks.vbfree2";

    public static String VBFREE3 = "itempks.vbfree3";

    public static String VBFREE4 = "itempks.vbfree4";

    /** 管控范围控制的查询条件 */
    public static final String[] ALL_QUERY = new String[] {
        CMMCommonConstCostPriceBase.CCOSTPRICEBID, CMMCommonConstCostPriceBase.CCOSTPRICEID,
        CMMCommonConstCostPriceBase.ANNUAL, CMMCommonConstCostPriceBase.CELEMENTID,
        CMMCommonConstCostPriceBase.CREATETIME, CMMCommonConstCostPriceBase.CREATOR,
        CMMCommonConstCostPriceBase.MODIFIEDTIME, CMMCommonConstCostPriceBase.MODIFIER,
        CMMCommonConstCostPriceBase.DPRICE, CMMCommonConstCostPriceBase.PK_ORG, CMMCommonConstCostPriceBase.PK_GROUP,
        CMMCommonConstCostPriceBase.VBFREE1, CMMCommonConstCostPriceBase.VBFREE2, CMMCommonConstCostPriceBase.VBFREE3,
        CMMCommonConstCostPriceBase.VBFREE4, CMMCommonConstCostPriceBase.VPERIOD,
        CMMCommonConstCostPriceBase.VPRICELIBCODE, CMMCommonConstCostPriceBase.VPRICELIBNAME,
        CMMCommonConstCostPriceBase.VPRODUCTCODE, CMMCommonConstCostPriceBase.VPRODUCTNAME,
        CMMCommonConstCostPriceBase.VREMARK, CMMCommonConstCostPriceBase.PK_ORG_V
    };
}
