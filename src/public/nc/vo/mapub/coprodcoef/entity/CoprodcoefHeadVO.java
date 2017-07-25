/***************************************************************\
 *     The skeleton of this class is generated by an automatic *
 * code generator for NC product. It is based on Velocity.     *
\***************************************************************/
package nc.vo.mapub.coprodcoef.entity;

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
public class CoprodcoefHeadVO extends SuperVO {

    public static final String CCOPRODCOEFID = "ccoprodcoefid";

    public static final String PK_GROUP = "pk_group";

    public static final String PK_ORG = "pk_org";

    public static final String PK_ORG_V = "pk_org_v";

    public static final String CMATERIALID = "cmaterialid";

    public static final String CMATERIALVID = "cmaterialvid";

    public static final String CBOMID = "cbomid";

    public static final String CRTID = "crtid";

    public static final String PK_ELEMENTSYSTEM = "pk_elementsystem";

    public static final String PK_FACTORCHART = "pk_factorchart";

    public static final String CREATOR = "creator";

    public static final String CREATIONTIME = "creationtime";

    public static final String MODIFIER = "modifier";

    public static final String MODIFIEDTIME = "modifiedtime";

    public static final String IBILLSTATUS = "ibillstatus";

    /**
     * 属性ccoprodcoefid的Getter方法.属性名：联产品相对系数
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCcoprodcoefid() {
        return (java.lang.String) this.getAttributeValue("ccoprodcoefid");
    }

    /**
     * 属性ccoprodcoefid的Setter方法.属性名：联产品相对系数
     * 创建日期:
     * 
     * @param newCcoprodcoefid java.lang.String
     */
    public void setCcoprodcoefid(java.lang.String newCcoprodcoefid) {
        this.setAttributeValue("ccoprodcoefid", newCcoprodcoefid);
    }

    /**
     * 属性pk_group的Getter方法.属性名：集团
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getPk_group() {
        return (java.lang.String) this.getAttributeValue("pk_group");
    }

    /**
     * 属性pk_group的Setter方法.属性名：集团
     * 创建日期:
     * 
     * @param newPk_group java.lang.String
     */
    public void setPk_group(java.lang.String newPk_group) {
        this.setAttributeValue("pk_group", newPk_group);
    }

    /**
     * 属性pk_org的Getter方法.属性名：工厂版本
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getPk_org() {
        return (java.lang.String) this.getAttributeValue("pk_org");
    }

    /**
     * 属性pk_org的Setter方法.属性名：工厂版本
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
     * 属性cmaterialid的Getter方法.属性名：物料最新版本
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCmaterialid() {
        return (java.lang.String) this.getAttributeValue("cmaterialid");
    }

    /**
     * 属性cmaterialid的Setter方法.属性名：物料最新版本
     * 创建日期:
     * 
     * @param newCmaterialid java.lang.String
     */
    public void setCmaterialid(java.lang.String newCmaterialid) {
        this.setAttributeValue("cmaterialid", newCmaterialid);
    }

    /**
     * 属性cmaterialvid的Getter方法.属性名：物料
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCmaterialvid() {
        return (java.lang.String) this.getAttributeValue("cmaterialvid");
    }

    /**
     * 属性cmaterialvid的Setter方法.属性名：物料
     * 创建日期:
     * 
     * @param newCmaterialvid java.lang.String
     */
    public void setCmaterialvid(java.lang.String newCmaterialvid) {
        this.setAttributeValue("cmaterialvid", newCmaterialvid);
    }

    /**
     * 属性cbomid的Getter方法.属性名：BOM
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCbomid() {
        return (java.lang.String) this.getAttributeValue("cbomid");
    }

    /**
     * 属性cbomid的Setter方法.属性名：BOM
     * 创建日期:
     * 
     * @param newCbomid java.lang.String
     */
    public void setCbomid(java.lang.String newCbomid) {
        this.setAttributeValue("cbomid", newCbomid);
    }

    /**
     * 属性crtid的Getter方法.属性名：工艺路线
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCrtid() {
        return (java.lang.String) this.getAttributeValue("crtid");
    }

    /**
     * 属性crtid的Setter方法.属性名：工艺路线
     * 创建日期:
     * 
     * @param newCrtid java.lang.String
     */
    public void setCrtid(java.lang.String newCrtid) {
        this.setAttributeValue("crtid", newCrtid);
    }

    /**
     * 属性pk_elementsystem的Getter方法.属性名：要素体系
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getPk_elementsystem() {
        return (java.lang.String) this.getAttributeValue("pk_elementsystem");
    }

    /**
     * 属性pk_elementsystem的Setter方法.属性名：要素体系
     * 创建日期:
     * 
     * @param newPk_elementsystem java.lang.String
     */
    public void setPk_elementsystem(java.lang.String newPk_elementsystem) {
        this.setAttributeValue("pk_elementsystem", newPk_elementsystem);
    }

    /**
     * 属性pk_factorchart的Getter方法.属性名：核算要素表
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getPk_factorchart() {
        return (java.lang.String) this.getAttributeValue("pk_factorchart");
    }

    /**
     * 属性pk_factorchart的Setter方法.属性名：核算要素表
     * 创建日期:
     * 
     * @param newPk_factorchart java.lang.String
     */
    public void setPk_factorchart(java.lang.String newPk_factorchart) {
        this.setAttributeValue("pk_factorchart", newPk_factorchart);
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
     * 属性modifier的Getter方法.属性名：修改人
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getModifier() {
        return (java.lang.String) this.getAttributeValue("modifier");
    }

    /**
     * 属性modifier的Setter方法.属性名：修改人
     * 创建日期:
     * 
     * @param newModifier java.lang.String
     */
    public void setModifier(java.lang.String newModifier) {
        this.setAttributeValue("modifier", newModifier);
    }

    /**
     * 属性modifiedtime的Getter方法.属性名：修改时间
     * 创建日期:
     * 
     * @return nc.vo.pub.lang.UFDateTime
     */
    public nc.vo.pub.lang.UFDateTime getModifiedtime() {
        return (nc.vo.pub.lang.UFDateTime) this.getAttributeValue("modifiedtime");
    }

    /**
     * 属性modifiedtime的Setter方法.属性名：修改时间
     * 创建日期:
     * 
     * @param newModifiedtime nc.vo.pub.lang.UFDateTime
     */
    public void setModifiedtime(nc.vo.pub.lang.UFDateTime newModifiedtime) {
        this.setAttributeValue("modifiedtime", newModifiedtime);
    }

    /**
     * 属性ibillstatus的Getter方法.属性名：单据状态
     * 创建日期:
     * 
     * @return java.lang.Integer
     */
    public java.lang.Integer getIbillstatus() {
        return (java.lang.Integer) this.getAttributeValue("ibillstatus");
    }

    /**
     * 属性ibillstatus的Setter方法.属性名：单据状态
     * 创建日期:
     * 
     * @param newIbillstatus java.lang.Integer
     */
    public void setIbillstatus(java.lang.Integer newIbillstatus) {
        this.setAttributeValue("ibillstatus", newIbillstatus);
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
        return "ccoprodcoefid";
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
        return "mapub_coprodcoef";
    }

    /**
     * 按照默认方式创建构造子.
     * 创建日期:
     */
    public CoprodcoefHeadVO() {
        super();
    }

    @Override
    @nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO")
    public IVOMeta getMetaData() {
        IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("mapub.mapub_coprodcoef");
        return meta;
    }

    /**
     * @return
     */
    public static Object getDefaultTableName() {
        return "mapub_coprodcoef";
    }
}
