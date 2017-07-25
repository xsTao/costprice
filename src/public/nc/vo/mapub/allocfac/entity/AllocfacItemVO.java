package nc.vo.mapub.allocfac.entity;

import nc.vo.cmpub.framework.assistant.CMAssInfoItemVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 在此处简要描述此类的功能 </b>
 * <p>
 * 在此处添加此类的描述信息
 * </p>
 * 创建日期:
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
     * 属性callocfacid的Getter方法.属性名：parentPK
     * 创建日期:
     *
     * @return java.lang.String
     */
    public java.lang.String getCallocfacid() {
        return (java.lang.String) this.getAttributeValue("callocfacid");
    }

    /**
     * 属性callocfacid的Setter方法.属性名：parentPK
     * 创建日期:
     *
     * @param newCallocfacid java.lang.String
     */
    public void setCallocfacid(java.lang.String newCcallocfacid) {
        this.setAttributeValue("callocfacid", newCcallocfacid);
    }

    /**
     * 属性callocfac_bid的Getter方法.属性名：子表主键
     * 创建日期:
     *
     * @return java.lang.String
     */
    public java.lang.String getCallocfac_bid() {
        return (java.lang.String) this.getAttributeValue("callocfac_bid");
    }

    /**
     * 属性callocfac_bid的Setter方法.属性名：子表主键
     * 创建日期:
     *
     * @param newCallocfac_bid java.lang.String
     */
    public void setCallocfac_bid(java.lang.String newCallocfac_bid) {
        this.setAttributeValue("callocfac_bid", newCallocfac_bid);
    }

    /**
     * 属性ccostcenterid的Getter方法.属性名：成本中心
     * 创建日期:
     *
     * @return java.lang.String
     */
    public java.lang.String getCcostcenterid() {
        return (java.lang.String) this.getAttributeValue("ccostcenterid");
    }

    /**
     * 属性ccostcenterid的Setter方法.属性名：成本中心
     * 创建日期:
     *
     * @param newCcostcenterid java.lang.String
     */
    public void setCcostcenterid(java.lang.String newCcostcenterid) {
        this.setAttributeValue("ccostcenterid", newCcostcenterid);
    }

    /**
     * 属性cmaterialid的Getter方法.属性名：产品
     * 创建日期:
     *
     * @return java.lang.String
     */
    public java.lang.String getCmaterialid() {
        return (java.lang.String) this.getAttributeValue("cmaterialid");
    }

    /**
     * 属性cmaterialid的Setter方法.属性名：产品
     * 创建日期:
     *
     * @param newCmaterialid java.lang.String
     */
    public void setCmaterialid(java.lang.String newCmaterialid) {
        this.setAttributeValue("cmaterialid", newCmaterialid);
    }

    /**
     * 属性cmarcostclassid的Getter方法.属性名：成本分类
     * 创建日期:
     *
     * @return java.lang.String
     */
    public java.lang.String getCmarcostclassid() {
        return (java.lang.String) this.getAttributeValue("cmarcostclassid");
    }

    /**
     * 属性cmarcostclassid的Setter方法.属性名：成本分类
     * 创建日期:
     *
     * @param newCmarcostclassid java.lang.String
     */
    public void setCmarcostclassid(java.lang.String newCmarcostclassid) {
        this.setAttributeValue("cmarcostclassid", newCmarcostclassid);
    }

    /**
     * 属性cmarbaseclassid的Getter方法.属性名：成本分类
     * 创建日期:
     *
     * @return java.lang.String
     */
    public java.lang.String getCmarbaseclassid() {
        return (java.lang.String) this.getAttributeValue("cmarbaseclassid");
    }

    /**
     * 属性cmarbaseclassid的Setter方法.属性名：成本分类
     * 创建日期:
     *
     * @param newCmarbaseclassid java.lang.String
     */
    public void setCmarbaseclassid(java.lang.String newCmarbaseclassid) {
        this.setAttributeValue("cmarbaseclassid", newCmarbaseclassid);
    }

    /**
     * 属性cactivityid的Getter方法.属性名：作业
     * 创建日期:
     *
     * @return java.lang.String
     */
    public java.lang.String getCactivityid() {
        return (java.lang.String) this.getAttributeValue("cactivityid");
    }

    /**
     * 属性cactivityid的Setter方法.属性名：作业
     * 创建日期:
     *
     * @param newCactivityid java.lang.String
     */
    public void setCactivityid(java.lang.String newCactivityid) {
        this.setAttributeValue("cactivityid", newCactivityid);
    }

    /**
     * 属性nfactor的Getter方法.属性名：系数
     * 创建日期:
     *
     * @return nc.vo.pub.lang.UFDouble
     */
    public nc.vo.pub.lang.UFDouble getNfactor() {
        return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("nfactor");
    }

    /**
     * 属性nfactor的Setter方法.属性名：系数
     * 创建日期:
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
     * 属性pk_group的Getter方法.属性名：集团主键
     * 创建日期:
     *
     * @return java.lang.String
     */
    public java.lang.String getPk_group() {
        return (java.lang.String) this.getAttributeValue("pk_group");
    }

    /**
     * 属性pk_group的Setter方法.属性名：集团主键
     * 创建日期:
     *
     * @param newPk_group java.lang.String
     */
    public void setPk_group(java.lang.String newPk_group) {
        this.setAttributeValue("pk_group", newPk_group);
    }

    /**
     * 属性pk_org的Getter方法.属性名：工厂最新版本
     * 创建日期:
     *
     * @return java.lang.String
     */
    public java.lang.String getPk_org() {
        return (java.lang.String) this.getAttributeValue("pk_org");
    }

    /**
     * 属性pk_org的Setter方法.属性名：工厂最新版本
     * 创建日期:
     *
     * @param newPk_org java.lang.String
     */
    public void setPk_org(java.lang.String newPk_org) {
        this.setAttributeValue("pk_org", newPk_org);
    }

    /**
     * 属性pk_org_v的Getter方法.属性名：工厂
     * 创建日期:
     *
     * @return java.lang.String
     */
    public java.lang.String getPk_org_v() {
        return (java.lang.String) this.getAttributeValue("pk_org_v");
    }

    /**
     * 属性pk_org_v的Setter方法.属性名：工厂
     * 创建日期:
     *
     * @param newPk_org_v java.lang.String
     */
    public void setPk_org_v(java.lang.String newPk_org_v) {
        this.setAttributeValue("pk_org_v", newPk_org_v);
    }

    /**
     * 属性dr的Getter方法.属性名：dr
     * 创建日期:
     *
     * @return java.lang.Integer
     */
    public java.lang.Integer getDr() {
        return (java.lang.Integer) this.getAttributeValue("dr");
    }

    /**
     * 属性dr的Setter方法.属性名：dr
     * 创建日期:
     *
     * @param newDr java.lang.Integer
     */
    public void setDr(java.lang.Integer newDr) {
        this.setAttributeValue("dr", newDr);
    }

    /**
     * 属性ts的Getter方法.属性名：ts
     * 创建日期:
     *
     * @return nc.vo.pub.lang.UFDateTime
     */
    public nc.vo.pub.lang.UFDateTime getTs() {
        return (nc.vo.pub.lang.UFDateTime) this.getAttributeValue("ts");
    }

    /**
     * 属性ts的Setter方法.属性名：ts
     * 创建日期:
     *
     * @param newTs nc.vo.pub.lang.UFDateTime
     */
    public void setTs(nc.vo.pub.lang.UFDateTime newTs) {
        this.setAttributeValue("ts", newTs);
    }

    /**
     * <p>
     * 取得父VO主键字段.
     * <p>
     * 创建日期:
     *
     * @return java.lang.String
     */
    @Override
    public java.lang.String getParentPKFieldName() {
        return "callocfacid";
    }

    /**
     * <p>
     * 取得表主键.
     * <p>
     * 创建日期:
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
     * 返回表名称.
     * <p>
     * 创建日期:
     *
     * @return java.lang.String
     */
    @Override
    public java.lang.String getTableName() {
        return "cm_allocfac_b";
    }

    /**
     * 按照默认方式创建构造子.
     * 创建日期:
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
