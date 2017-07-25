package nc.vo.mapub.materialpricebase.entity;

public class CMMCommonConstMaterialPriceBase {
    /** 材料页签 */
    public static final String CMATERIAL = "itempks";

    /** 材料标识 */
    public static final String CMATERIALSIGN = "cmaterial";

    /** 管控范围 */
    public static final String CONTROLAREA = "controlarea";

    /** 工厂 */
    public static final String PK_ORG = "pk_org";

    /** 价格库编码 */
    public static final String VPRICECODE = "vpricecode";

    /** 价格库名称 */
    public static final String VPRICENAME = "vpricename";

    /** 生效日期 */
    public static final String DBEGINDATE = "dbegindate";

    /** 失效日期 */
    public static final String DENDDATE = "denddate";

    /** 物料 */
    public static final String CMATERIALID = "itempks.cmaterialid";

    /** 物料基本分类 */
    public static final String PK_MARBASCLASS = "itempks.cmaterialid.pk_marbasclass";

    /** 物料成本分类 */
    public static final String PK_MARCOSTCLASS = "itempks.cmaterialvid.materialprod.pk_marcostclass";

    /** 价格 */
    public static final String NPRICE = "itempks.nprice";

    /** 备注 */
    public static final String VNOTE = "vnote";

    /** 创建人 */
    public static final String CREATOR = "creator";

    /** 创建时间 */
    public static final String CREATIONTIME = "creationtime";

    /** 修改人 */
    public static final String MODIFIER = "modifier";

    /** 修改时间 */
    public static final String MODIFIEDTIME = "modifiedtime";

    /** 管控范围控制的查询条件 */
    public static final String[] ALL_QUERY = new String[] {
        CMMCommonConstMaterialPriceBase.PK_MARBASCLASS, CMMCommonConstMaterialPriceBase.PK_MARCOSTCLASS,
        CMMCommonConstMaterialPriceBase.CMATERIALID, CMMCommonConstMaterialPriceBase.DBEGINDATE,
        CMMCommonConstMaterialPriceBase.DENDDATE, CMMCommonConstMaterialPriceBase.CREATIONTIME,
        CMMCommonConstMaterialPriceBase.CREATOR, CMMCommonConstMaterialPriceBase.MODIFIEDTIME,
        CMMCommonConstMaterialPriceBase.MODIFIER, CMMCommonConstMaterialPriceBase.PK_ORG,
        CMMCommonConstMaterialPriceBase.VNOTE, CMMCommonConstMaterialPriceBase.VPRICECODE,
        CMMCommonConstMaterialPriceBase.VPRICENAME, CMMCommonConstMaterialPriceBase.NPRICE,
    };
}
