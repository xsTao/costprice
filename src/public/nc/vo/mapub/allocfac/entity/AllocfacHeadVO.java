/**
 * 
 */
package nc.vo.mapub.allocfac.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
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
public class AllocfacHeadVO extends SuperVO {
    public static final String CALLOCFACID = "callocfacid";

    public static final String PK_GROUP = "pk_group";

    public static final String PK_ORG = "pk_org";

    public static final String PK_ORG_V = "pk_org_v";

    public static final String VCODE = "vcode";

    public static final String VNAME = "vname";

    public static final String VNOTE = "vnote";

    public static final String CREATOR = "creator";

    public static final String CREATIONTIME = "creationtime";

    public static final String MODIFIER = "modifier";

    public static final String MODIFIEDTIME = "modifiedtime";

    public static final String IALLOCTYPE = "ialloctype";

    /**
     * ����callocfacid��Getter����.������������ϵ��
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCallocfacid() {
        return (java.lang.String) this.getAttributeValue("callocfacid");
    }

    /**
     * ����callocfacid��Setter����.������������ϵ��
     * ��������:
     * 
     * @param newCallocfacid java.lang.String
     */
    public void setCallocfacid(java.lang.String newCallocfacid) {
        this.setAttributeValue("callocfacid", newCallocfacid);
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
     * ����pk_org��Getter����.������������
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getPk_org() {
        return (java.lang.String) this.getAttributeValue("pk_org");
    }

    /**
     * ����pk_org��Setter����.������������
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
     * ����vcode��Getter����.������������
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVcode() {
        return (java.lang.String) this.getAttributeValue("vcode");
    }

    /**
     * ����vcode��Setter����.������������
     * ��������:
     * 
     * @param newVcode java.lang.String
     */
    public void setVcode(java.lang.String newVcode) {
        this.setAttributeValue("vcode", newVcode);
    }

    /**
     * ����vname��Getter����.������������
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVname() {
        return (java.lang.String) this.getAttributeValue("vname");
    }

    /**
     * ����vname��Setter����.������������
     * ��������:
     * 
     * @param newVname java.lang.String
     */
    public void setVname(java.lang.String newVname) {
        this.setAttributeValue("vname", newVname);
    }

    /**
     * ����vnote��Getter����.����������ע
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVnote() {
        return (java.lang.String) this.getAttributeValue("vnote");
    }

    /**
     * ����vnote��Setter����.����������ע
     * ��������:
     * 
     * @param newVnote java.lang.String
     */
    public void setVnote(java.lang.String newVnote) {
        this.setAttributeValue("vnote", newVnote);
    }

    /**
     * ����creator��Getter����.��������������
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCreator() {
        return (java.lang.String) this.getAttributeValue("creator");
    }

    /**
     * ����creator��Setter����.��������������
     * ��������:
     * 
     * @param newCreator java.lang.String
     */
    public void setCreator(java.lang.String newCreator) {
        this.setAttributeValue("creator", newCreator);
    }

    /**
     * ����creationtime��Getter����.������������ʱ��
     * ��������:
     * 
     * @return nc.vo.pub.lang.UFDateTime
     */
    public nc.vo.pub.lang.UFDateTime getCreationtime() {
        return (nc.vo.pub.lang.UFDateTime) this.getAttributeValue("creationtime");
    }

    /**
     * ����creationtime��Setter����.������������ʱ��
     * ��������:
     * 
     * @param newCreationtime nc.vo.pub.lang.UFDateTime
     */
    public void setCreationtime(nc.vo.pub.lang.UFDateTime newCreationtime) {
        this.setAttributeValue("creationtime", newCreationtime);
    }

    /**
     * ����modifier��Getter����.������������޸���
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getModifier() {
        return (java.lang.String) this.getAttributeValue("modifier");
    }

    /**
     * ����modifier��Setter����.������������޸���
     * ��������:
     * 
     * @param newModifier java.lang.String
     */
    public void setModifier(java.lang.String newModifier) {
        this.setAttributeValue("modifier", newModifier);
    }

    /**
     * ����modifiedtime��Getter����.������������޸�ʱ��
     * ��������:
     * 
     * @return nc.vo.pub.lang.UFDateTime
     */
    public nc.vo.pub.lang.UFDateTime getModifiedtime() {
        return (nc.vo.pub.lang.UFDateTime) this.getAttributeValue("modifiedtime");
    }

    /**
     * ����modifiedtime��Setter����.������������޸�ʱ��
     * ��������:
     * 
     * @param newModifiedtime nc.vo.pub.lang.UFDateTime
     */
    public void setModifiedtime(nc.vo.pub.lang.UFDateTime newModifiedtime) {
        this.setAttributeValue("modifiedtime", newModifiedtime);
    }

    /**
     * ����ialloctype��Getter����.����������������
     * ��������:
     * 
     * @return java.lang.Integer
     */
    public java.lang.Integer getIalloctype() {
        return (java.lang.Integer) this.getAttributeValue("ialloctype");
    }

    /**
     * ����ialloctype��Setter����.����������������
     * ��������:
     * 
     * @param newIalloctype java.lang.Integer
     */
    public void setIalloctype(java.lang.Integer newIalloctype) {
        this.setAttributeValue("ialloctype", newIalloctype);
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
        return null;
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
        return "callocfacid";
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
        return "cm_allocfac";
    }

    public static java.lang.String getDefaultTableName() {
        return "cm_allocfac";
    }

    /**
     * ����Ĭ�Ϸ�ʽ����������.
     * ��������:
     */
    public AllocfacHeadVO() {
        super();
    }

    @Override
    public IVOMeta getMetaData() {
        IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("cm.cm_allocfac");
        return meta;

    }
}
