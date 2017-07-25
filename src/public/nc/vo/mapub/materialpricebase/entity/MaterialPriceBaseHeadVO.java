/***************************************************************\
 *     The skeleton of this class is generated by an automatic *
 * code generator for NC product. It is based on Velocity.     *
\***************************************************************/
package nc.vo.mapub.materialpricebase.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * ���ϼ۸��
 * ��ͷVO
 * 
 * @since 6.36
 * @version 2014-11-6 ����10:27:46
 * @author zhangchd
 */
@SuppressWarnings("serial")
public class MaterialPriceBaseHeadVO extends SuperVO {

    public static final String CMATERIALPRICEID = "cmaterialpriceid";

    public static final String PK_GROUP = "pk_group";

    public static final String PK_ORG = "pk_org";

    public static final String PK_ORG_V = "pk_org_v";

    public static final String VPRICECODE = "vpricecode";

    public static final String VPRICENAME = "vpricename";

    public static final String CCRRENCYID = "ccrrencyid";

    public static final String VPRICESOURCE = "vpricesource";

    public static final String VPRICESOURCENUM = "vpricesourcenum";

    public static final String DBEGINDATE = "dbegindate";

    public static final String DENDDATE = "denddate";

    public static final String VNOTE = "vnote";

    public static final String BLOCKINGFLAG = "blockingflag";

    public static final String CREATOR = "creator";

    public static final String CREATIONTIME = "creationtime";

    public static final String MODIFIER = "modifier";

    public static final String MODIFIEDTIME = "modifiedtime";

    /**
     * ����cmaterialpriceid��Getter����.�����������ϼ۸��
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCmaterialpriceid() {
        return (java.lang.String) this.getAttributeValue("cmaterialpriceid");
    }

    /**
     * ����cmaterialpricebaseid��Setter����.�����������ϼ۸��
     * ��������:
     * 
     * @param newCmaterialpriceid java.lang.String
     */
    public void setCmaterialpriceid(java.lang.String newCmaterialpriceid) {
        this.setAttributeValue("cmaterialpriceid", newCmaterialpriceid);
    }

    /**
     * ����pk_group��Getter����.������������
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getPk_group() {
        return (java.lang.String) this.getAttributeValue("pk_group");
    }

    /**
     * ����pk_group��Setter����.������������
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
     * ����pk_org_v��Getter����.�������������汾
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getPk_org_v() {
        return (java.lang.String) this.getAttributeValue("pk_org_v");
    }

    /**
     * ����pk_org_v��Setter����.�������������汾
     * ��������:
     * 
     * @param newPk_org_v java.lang.String
     */
    public void setPk_org_v(java.lang.String newPk_org_v) {
        this.setAttributeValue("pk_org_v", newPk_org_v);
    }

    /**
     * ����vpricecode��Getter����.���������۸�����
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVpricecode() {
        return (java.lang.String) this.getAttributeValue("vpricecode");
    }

    /**
     * ����vpricecode��Setter����.���������۸�����
     * ��������:
     * 
     * @param newVpricecode java.lang.String
     */
    public void setVpricecode(java.lang.String newVpricecode) {
        this.setAttributeValue("vpricecode", newVpricecode);
    }

    /**
     * ����vpricename��Getter����.���������۸������
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVpricename() {
        return (java.lang.String) this.getAttributeValue("vpricename");
    }

    /**
     * ����vpricename��Setter����.���������۸������
     * ��������:
     * 
     * @param newVpricename java.lang.String
     */
    public void setVpricename(java.lang.String newVpricename) {
        this.setAttributeValue("vpricename", newVpricename);
    }

    /**
     * ����ccrrencyid��Getter����.������������
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCcrrencyid() {
        return (java.lang.String) this.getAttributeValue("ccrrencyid");
    }

    /**
     * ����ccrrencyid��Setter����.������������
     * ��������:
     * 
     * @param newCcrrencyid java.lang.String
     */
    public void setCcrrencyid(java.lang.String newCcrrencyid) {
        this.setAttributeValue("ccrrencyid", newCcrrencyid);
    }

    /**
     * ����vpricesource��Getter����.���������۸���Դ
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVpricesource() {
        return (java.lang.String) this.getAttributeValue("vpricesource");
    }

    /**
     * ����vpricesource��Setter����.���������۸���Դ
     * ��������:
     * 
     * @param newVpricesource java.lang.String
     */
    public void setVpricesource(java.lang.String newVpricesource) {
        this.setAttributeValue("vpricesource", newVpricesource);
    }

    /**
     * ����vpricesourcenum��Getter����.���������۸���Դ��
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVpricesourcenum() {
        return (java.lang.String) this.getAttributeValue("vpricesourcenum");
    }

    /**
     * ����vpricesourcenum��Setter����.���������۸���Դ��
     * ��������:
     * 
     * @param newVpricesourcenum java.lang.String
     */
    public void setVpricesourcenum(java.lang.String newVpricesourcenum) {
        this.setAttributeValue("vpricesourcenum", newVpricesourcenum);
    }

    /**
     * ����dbegindate��Getter����.����������Ч����
     * ��������:
     * 
     * @return nc.vo.pub.lang.UFDate
     */
    public nc.vo.pub.lang.UFDate getDbegindate() {
        return (nc.vo.pub.lang.UFDate) this.getAttributeValue("dbegindate");
    }

    /**
     * ����dbegindate��Setter����.����������Ч����
     * ��������:
     * 
     * @param newDbegindate nc.vo.pub.lang.UFDate
     */
    public void setDbegindate(nc.vo.pub.lang.UFDate newDbegindate) {
        this.setAttributeValue("dbegindate", newDbegindate);
    }

    /**
     * ����denddate��Getter����.��������ʧЧ����
     * ��������:
     * 
     * @return nc.vo.pub.lang.UFDate
     */
    public nc.vo.pub.lang.UFDate getDenddate() {
        return (nc.vo.pub.lang.UFDate) this.getAttributeValue("denddate");
    }

    /**
     * ����denddate��Setter����.��������ʧЧ����
     * ��������:
     * 
     * @param newDenddate nc.vo.pub.lang.UFDate
     */
    public void setDenddate(nc.vo.pub.lang.UFDate newDenddate) {
        this.setAttributeValue("denddate", newDenddate);
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
     * ����blockingflag��Getter����.������������
     * ��������:
     * 
     * @return nc.vo.pub.lang.UFBoolean
     */
    public nc.vo.pub.lang.UFBoolean getBlockingflag() {
        return (nc.vo.pub.lang.UFBoolean) this.getAttributeValue("blockingflag");
    }

    /**
     * ����blockingflag��Setter����.������������
     * ��������:
     * 
     * @param newBlockingflag nc.vo.pub.lang.UFBoolean
     */
    public void setBlockingflag(nc.vo.pub.lang.UFBoolean newBlockingflag) {
        this.setAttributeValue("blockingflag", newBlockingflag);
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
        return "cmaterialpriceid";
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
        return "mapub_materialpricebase";
    }

    public static java.lang.String getDefaultTableName() {
        return "mapub_materialpricebase";
    }

    /**
     * ����Ĭ�Ϸ�ʽ����������.
     * ��������:
     */
    public MaterialPriceBaseHeadVO() {
        super();
    }

    @Override
    @nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO")
    public IVOMeta getMetaData() {
        IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("mapub.mapub_materialpricebase");
        return meta;
    }
}