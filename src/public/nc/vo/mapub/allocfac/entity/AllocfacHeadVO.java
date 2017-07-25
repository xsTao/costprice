/**
 * 
 */
package nc.vo.mapub.allocfac.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
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
     * 属性callocfacid的Getter方法.属性名：分配系数
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCallocfacid() {
        return (java.lang.String) this.getAttributeValue("callocfacid");
    }

    /**
     * 属性callocfacid的Setter方法.属性名：分配系数
     * 创建日期:
     * 
     * @param newCallocfacid java.lang.String
     */
    public void setCallocfacid(java.lang.String newCallocfacid) {
        this.setAttributeValue("callocfacid", newCallocfacid);
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
     * 属性pk_org的Getter方法.属性名：工厂
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getPk_org() {
        return (java.lang.String) this.getAttributeValue("pk_org");
    }

    /**
     * 属性pk_org的Setter方法.属性名：工厂
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
     * 属性vcode的Getter方法.属性名：编码
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVcode() {
        return (java.lang.String) this.getAttributeValue("vcode");
    }

    /**
     * 属性vcode的Setter方法.属性名：编码
     * 创建日期:
     * 
     * @param newVcode java.lang.String
     */
    public void setVcode(java.lang.String newVcode) {
        this.setAttributeValue("vcode", newVcode);
    }

    /**
     * 属性vname的Getter方法.属性名：名称
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVname() {
        return (java.lang.String) this.getAttributeValue("vname");
    }

    /**
     * 属性vname的Setter方法.属性名：名称
     * 创建日期:
     * 
     * @param newVname java.lang.String
     */
    public void setVname(java.lang.String newVname) {
        this.setAttributeValue("vname", newVname);
    }

    /**
     * 属性vnote的Getter方法.属性名：备注
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVnote() {
        return (java.lang.String) this.getAttributeValue("vnote");
    }

    /**
     * 属性vnote的Setter方法.属性名：备注
     * 创建日期:
     * 
     * @param newVnote java.lang.String
     */
    public void setVnote(java.lang.String newVnote) {
        this.setAttributeValue("vnote", newVnote);
    }

    /**
     * 属性creator的Getter方法.属性名：创建人
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCreator() {
        return (java.lang.String) this.getAttributeValue("creator");
    }

    /**
     * 属性creator的Setter方法.属性名：创建人
     * 创建日期:
     * 
     * @param newCreator java.lang.String
     */
    public void setCreator(java.lang.String newCreator) {
        this.setAttributeValue("creator", newCreator);
    }

    /**
     * 属性creationtime的Getter方法.属性名：创建时间
     * 创建日期:
     * 
     * @return nc.vo.pub.lang.UFDateTime
     */
    public nc.vo.pub.lang.UFDateTime getCreationtime() {
        return (nc.vo.pub.lang.UFDateTime) this.getAttributeValue("creationtime");
    }

    /**
     * 属性creationtime的Setter方法.属性名：创建时间
     * 创建日期:
     * 
     * @param newCreationtime nc.vo.pub.lang.UFDateTime
     */
    public void setCreationtime(nc.vo.pub.lang.UFDateTime newCreationtime) {
        this.setAttributeValue("creationtime", newCreationtime);
    }

    /**
     * 属性modifier的Getter方法.属性名：最后修改人
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getModifier() {
        return (java.lang.String) this.getAttributeValue("modifier");
    }

    /**
     * 属性modifier的Setter方法.属性名：最后修改人
     * 创建日期:
     * 
     * @param newModifier java.lang.String
     */
    public void setModifier(java.lang.String newModifier) {
        this.setAttributeValue("modifier", newModifier);
    }

    /**
     * 属性modifiedtime的Getter方法.属性名：最后修改时间
     * 创建日期:
     * 
     * @return nc.vo.pub.lang.UFDateTime
     */
    public nc.vo.pub.lang.UFDateTime getModifiedtime() {
        return (nc.vo.pub.lang.UFDateTime) this.getAttributeValue("modifiedtime");
    }

    /**
     * 属性modifiedtime的Setter方法.属性名：最后修改时间
     * 创建日期:
     * 
     * @param newModifiedtime nc.vo.pub.lang.UFDateTime
     */
    public void setModifiedtime(nc.vo.pub.lang.UFDateTime newModifiedtime) {
        this.setAttributeValue("modifiedtime", newModifiedtime);
    }

    /**
     * 属性ialloctype的Getter方法.属性名：分配内容
     * 创建日期:
     * 
     * @return java.lang.Integer
     */
    public java.lang.Integer getIalloctype() {
        return (java.lang.Integer) this.getAttributeValue("ialloctype");
    }

    /**
     * 属性ialloctype的Setter方法.属性名：分配内容
     * 创建日期:
     * 
     * @param newIalloctype java.lang.Integer
     */
    public void setIalloctype(java.lang.Integer newIalloctype) {
        this.setAttributeValue("ialloctype", newIalloctype);
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
        return null;
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
        return "callocfacid";
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
        return "cm_allocfac";
    }

    public static java.lang.String getDefaultTableName() {
        return "cm_allocfac";
    }

    /**
     * 按照默认方式创建构造子.
     * 创建日期:
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
