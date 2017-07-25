package nc.vo.mapub.allocfac.entity;

import nc.vo.cmpub.framework.assistant.CMAssInfoItemVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �ڴ˴���Ҫ��������Ĺ��� </b>
 * <p>
 * �ڴ˴���Ӵ����������Ϣ
 * </p>
 * ��������:
 *
 * @author
 * @version NCPrj ??
 */
@SuppressWarnings("serial")
public class AllocfacItemVO extends CMAssInfoItemVO {
    public static final String META_DATA = "cm.cm_allocfac_b";

    public static final String CALLOCFACID = "callocfacid";

    public static final String CALLOCFAC_BID = "callocfac_bid";

    public static final String CCOSTCENTERID = "ccostcenterid";

    public static final String CMATERIALID = "cmaterialid";

    public static final String CMARCOSTCLASSID = "cmarcostclassid";

    public static final String CMARBASECLASSID = "cmarbaseclassid";

    public static final String CACTIVITYID = "cactivityid";

    public static final String NFACTOR = "nfactor";

    public static final String CSTUFFID = "cstuffid";

    public static final String PK_GROUP = "pk_group";

    public static final String PK_ORG = "pk_org";

    public static final String PK_ORG_V = "pk_org_v";

    /**
     * ����callocfacid��Getter����.��������parentPK
     * ��������:
     *
     * @return java.lang.String
     */
    public java.lang.String getCallocfacid() {
        return (java.lang.String) this.getAttributeValue("callocfacid");
    }

    /**
     * ����callocfacid��Setter����.��������parentPK
     * ��������:
     *
     * @param newCallocfacid java.lang.String
     */
    public void setCallocfacid(java.lang.String newCcallocfacid) {
        this.setAttributeValue("callocfacid", newCcallocfacid);
    }

    /**
     * ����callocfac_bid��Getter����.���������ӱ�����
     * ��������:
     *
     * @return java.lang.String
     */
    public java.lang.String getCallocfac_bid() {
        return (java.lang.String) this.getAttributeValue("callocfac_bid");
    }

    /**
     * ����callocfac_bid��Setter����.���������ӱ�����
     * ��������:
     *
     * @param newCallocfac_bid java.lang.String
     */
    public void setCallocfac_bid(java.lang.String newCallocfac_bid) {
        this.setAttributeValue("callocfac_bid", newCallocfac_bid);
    }

    /**
     * ����ccostcenterid��Getter����.���������ɱ�����
     * ��������:
     *
     * @return java.lang.String
     */
    public java.lang.String getCcostcenterid() {
        return (java.lang.String) this.getAttributeValue("ccostcenterid");
    }

    /**
     * ����ccostcenterid��Setter����.���������ɱ�����
     * ��������:
     *
     * @param newCcostcenterid java.lang.String
     */
    public void setCcostcenterid(java.lang.String newCcostcenterid) {
        this.setAttributeValue("ccostcenterid", newCcostcenterid);
    }

    /**
     * ����cmaterialid��Getter����.����������Ʒ
     * ��������:
     *
     * @return java.lang.String
     */
    public java.lang.String getCmaterialid() {
        return (java.lang.String) this.getAttributeValue("cmaterialid");
    }

    /**
     * ����cmaterialid��Setter����.����������Ʒ
     * ��������:
     *
     * @param newCmaterialid java.lang.String
     */
    public void setCmaterialid(java.lang.String newCmaterialid) {
        this.setAttributeValue("cmaterialid", newCmaterialid);
    }

    /**
     * ����cmarcostclassid��Getter����.���������ɱ�����
     * ��������:
     *
     * @return java.lang.String
     */
    public java.lang.String getCmarcostclassid() {
        return (java.lang.String) this.getAttributeValue("cmarcostclassid");
    }

    /**
     * ����cmarcostclassid��Setter����.���������ɱ�����
     * ��������:
     *
     * @param newCmarcostclassid java.lang.String
     */
    public void setCmarcostclassid(java.lang.String newCmarcostclassid) {
        this.setAttributeValue("cmarcostclassid", newCmarcostclassid);
    }

    /**
     * ����cmarbaseclassid��Getter����.���������ɱ�����
     * ��������:
     *
     * @return java.lang.String
     */
    public java.lang.String getCmarbaseclassid() {
        return (java.lang.String) this.getAttributeValue("cmarbaseclassid");
    }

    /**
     * ����cmarbaseclassid��Setter����.���������ɱ�����
     * ��������:
     *
     * @param newCmarbaseclassid java.lang.String
     */
    public void setCmarbaseclassid(java.lang.String newCmarbaseclassid) {
        this.setAttributeValue("cmarbaseclassid", newCmarbaseclassid);
    }

    /**
     * ����cactivityid��Getter����.����������ҵ
     * ��������:
     *
     * @return java.lang.String
     */
    public java.lang.String getCactivityid() {
        return (java.lang.String) this.getAttributeValue("cactivityid");
    }

    /**
     * ����cactivityid��Setter����.����������ҵ
     * ��������:
     *
     * @param newCactivityid java.lang.String
     */
    public void setCactivityid(java.lang.String newCactivityid) {
        this.setAttributeValue("cactivityid", newCactivityid);
    }

    /**
     * ����nfactor��Getter����.��������ϵ��
     * ��������:
     *
     * @return nc.vo.pub.lang.UFDouble
     */
    public nc.vo.pub.lang.UFDouble getNfactor() {
        return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("nfactor");
    }

    /**
     * ����nfactor��Setter����.��������ϵ��
     * ��������:
     *
     * @param newNfactor nc.vo.pub.lang.UFDouble
     */
    public void setNfactor(nc.vo.pub.lang.UFDouble newNfactor) {
        this.setAttributeValue("nfactor", newNfactor);
    }

    public java.lang.String getCstuffid() {
        return (java.lang.String) this.getAttributeValue("cstuffid");
    }

    public void setCstuffid(java.lang.String newCstuffid) {
        this.setAttributeValue("cstuffid", newCstuffid);
    }

    /**
     * ����pk_group��Getter����.����������������
     * ��������:
     *
     * @return java.lang.String
     */
    public java.lang.String getPk_group() {
        return (java.lang.String) this.getAttributeValue("pk_group");
    }

    /**
     * ����pk_group��Setter����.����������������
     * ��������:
     *
     * @param newPk_group java.lang.String
     */
    public void setPk_group(java.lang.String newPk_group) {
        this.setAttributeValue("pk_group", newPk_group);
    }

    /**
     * ����pk_org��Getter����.���������������°汾
     * ��������:
     *
     * @return java.lang.String
     */
    public java.lang.String getPk_org() {
        return (java.lang.String) this.getAttributeValue("pk_org");
    }

    /**
     * ����pk_org��Setter����.���������������°汾
     * ��������:
     *
     * @param newPk_org java.lang.String
     */
    public void setPk_org(java.lang.String newPk_org) {
        this.setAttributeValue("pk_org", newPk_org);
    }

    /**
     * ����pk_org_v��Getter����.������������
     * ��������:
     *
     * @return java.lang.String
     */
    public java.lang.String getPk_org_v() {
        return (java.lang.String) this.getAttributeValue("pk_org_v");
    }

    /**
     * ����pk_org_v��Setter����.������������
     * ��������:
     *
     * @param newPk_org_v java.lang.String
     */
    public void setPk_org_v(java.lang.String newPk_org_v) {
        this.setAttributeValue("pk_org_v", newPk_org_v);
    }

    /**
     * ����dr��Getter����.��������dr
     * ��������:
     *
     * @return java.lang.Integer
     */
    public java.lang.Integer getDr() {
        return (java.lang.Integer) this.getAttributeValue("dr");
    }

    /**
     * ����dr��Setter����.��������dr
     * ��������:
     *
     * @param newDr java.lang.Integer
     */
    public void setDr(java.lang.Integer newDr) {
        this.setAttributeValue("dr", newDr);
    }

    /**
     * ����ts��Getter����.��������ts
     * ��������:
     *
     * @return nc.vo.pub.lang.UFDateTime
     */
    public nc.vo.pub.lang.UFDateTime getTs() {
        return (nc.vo.pub.lang.UFDateTime) this.getAttributeValue("ts");
    }

    /**
     * ����ts��Setter����.��������ts
     * ��������:
     *
     * @param newTs nc.vo.pub.lang.UFDateTime
     */
    public void setTs(nc.vo.pub.lang.UFDateTime newTs) {
        this.setAttributeValue("ts", newTs);
    }

    /**
     * <p>
     * ȡ�ø�VO�����ֶ�.
     * <p>
     * ��������:
     *
     * @return java.lang.String
     */
    @Override
    public java.lang.String getParentPKFieldName() {
        return "callocfacid";
    }

    /**
     * <p>
     * ȡ�ñ�����.
     * <p>
     * ��������:
     *
     * @return java.lang.String
     */
    @Override
    public java.lang.String getPKFieldName() {
        return "callocfac_bid";
    }

    public static java.lang.String getDefaultTableName() {
        return "cm_allocfac_b";
    }

    /**
     * <p>
     * ���ر�����.
     * <p>
     * ��������:
     *
     * @return java.lang.String
     */
    @Override
    public java.lang.String getTableName() {
        return "cm_allocfac_b";
    }

    /**
     * ����Ĭ�Ϸ�ʽ����������.
     * ��������:
     */
    public AllocfacItemVO() {
        super();
    }

    @Override
    public IVOMeta getMetaData() {
        IVOMeta billMeta = VOMetaFactory.getInstance().getVOMeta(AllocfacItemVO.META_DATA);

        return billMeta;
    }
}
