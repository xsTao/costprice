package nc.vo.mapub.materialpricebase.entity;

public class CMMCommonConstMaterialPriceBase {
    /** ����ҳǩ */
    public static final String CMATERIAL = "itempks";

    /** ���ϱ�ʶ */
    public static final String CMATERIALSIGN = "cmaterial";

    /** �ܿط�Χ */
    public static final String CONTROLAREA = "controlarea";

    /** ���� */
    public static final String PK_ORG = "pk_org";

    /** �۸����� */
    public static final String VPRICECODE = "vpricecode";

    /** �۸������ */
    public static final String VPRICENAME = "vpricename";

    /** ��Ч���� */
    public static final String DBEGINDATE = "dbegindate";

    /** ʧЧ���� */
    public static final String DENDDATE = "denddate";

    /** ���� */
    public static final String CMATERIALID = "itempks.cmaterialid";

    /** ���ϻ������� */
    public static final String PK_MARBASCLASS = "itempks.cmaterialid.pk_marbasclass";

    /** ���ϳɱ����� */
    public static final String PK_MARCOSTCLASS = "itempks.cmaterialvid.materialprod.pk_marcostclass";

    /** �۸� */
    public static final String NPRICE = "itempks.nprice";

    /** ��ע */
    public static final String VNOTE = "vnote";

    /** ������ */
    public static final String CREATOR = "creator";

    /** ����ʱ�� */
    public static final String CREATIONTIME = "creationtime";

    /** �޸��� */
    public static final String MODIFIER = "modifier";

    /** �޸�ʱ�� */
    public static final String MODIFIEDTIME = "modifiedtime";

    /** �ܿط�Χ���ƵĲ�ѯ���� */
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
