/***************************************************************\
 *     The skeleton of this class is generated by an automatic *
 * code generator for NC product. It is based on Velocity.     *
\***************************************************************/
package nc.vo.mapub.materialpricebase.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * 材料价格库
 * 表体VO
 * 
 * @since 6.36
 * @version 2014-11-6 上午10:30:25
 * @author zhangchd
 */
@SuppressWarnings("serial")
public class MaterialPriceBaseBodyVO extends SuperVO {

    public static final String CMATERIALPRICE_BID = "cmaterialprice_bid";

    public static final String CMATERIALPRICEID = "cmaterialpriceid";

    public static final String PK_GROUP = "pk_group";

    public static final String PK_ORG = "pk_org";

    public static final String PK_ORG_V = "pk_org_v";

    public static final String CMATERIALID = "cmaterialid";

    public static final String CMATERIALVID = "cmaterialvid";

    public static final String CMEASDOCID = "cmeasdocid";

    public static final String CPROJECTID = "cprojectid";

    public static final String CVENDORID = "cvendorid";

    public static final String CPRODUCTORID = "cproductorid";

    public static final String CCUSTOMERID = "ccustomerid";

    public static final String VBFREE1 = "vbfree1";

    public static final String VBFREE2 = "vbfree2";

    public static final String VBFREE3 = "vbfree3";

    public static final String VBFREE4 = "vbfree4";

    public static final String VBFREE5 = "vbfree5";

    public static final String VBFREE6 = "vbfree6";

    public static final String VBFREE7 = "vbfree7";

    public static final String VBFREE8 = "vbfree8";

    public static final String VBFREE9 = "vbfree9";

    public static final String VBFREE10 = "vbfree10";

    public static final String VPRICESOURCE = "vpricesource";

    public static final String VPRICESOURCENUM = "vpricesourcenum";

    // 实际价格来源
    public static final String VPRICESOURCEREAL = "vpricesourcereal";

    // 实际价格来源数
    public static final String VPRICESOURCEREALNUM = "vpricesourcerealnum";

    public static final String NPRICE = "nprice";

    /**
     * 属性cmaterialprice_bid的Getter方法.属性名：价格库材料明细
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCmaterialprice_bid() {
        return (java.lang.String) this.getAttributeValue("cmaterialprice_bid");
    }

    /**
     * 属性cmaterialprice_bid的Setter方法.属性名：价格库材料明细
     * 创建日期:
     * 
     * @param newCmaterialprice_bid java.lang.String
     */
    public void setCmaterialprice_bid(java.lang.String newCmaterialprice_bid) {
        this.setAttributeValue("cmaterialprice_bid", newCmaterialprice_bid);
    }

    /**
     * 属性cmaterialpriceid的Getter方法.属性名：材料价格库
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCmaterialpriceid() {
        return (java.lang.String) this.getAttributeValue("cmaterialpriceid");
    }

    /**
     * 属性cmaterialpriceid的Setter方法.属性名：材料价格库
     * 创建日期:
     * 
     * @param newCmaterialpriceid java.lang.String
     */
    public void setCmaterialpriceid(java.lang.String newCmaterialpriceid) {
        this.setAttributeValue("cmaterialpriceid", newCmaterialpriceid);
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
     * 属性pk_org_v的Getter方法.属性名：工厂版本
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getPk_org_v() {
        return (java.lang.String) this.getAttributeValue("pk_org_v");
    }

    /**
     * 属性pk_org_v的Setter方法.属性名：工厂版本
     * 创建日期:
     * 
     * @param newPk_org_v java.lang.String
     */
    public void setPk_org_v(java.lang.String newPk_org_v) {
        this.setAttributeValue("pk_org_v", newPk_org_v);
    }

    /**
     * 属性cmaterialid的Getter方法.属性名：物料
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCmaterialid() {
        return (java.lang.String) this.getAttributeValue("cmaterialid");
    }

    /**
     * 属性cmaterialid的Setter方法.属性名：物料
     * 创建日期:
     * 
     * @param newCmaterialid java.lang.String
     */
    public void setCmaterialid(java.lang.String newCmaterialid) {
        this.setAttributeValue("cmaterialid", newCmaterialid);
    }

    /**
     * 属性cmaterialvid的Getter方法.属性名：物料多版本
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCmaterialvid() {
        return (java.lang.String) this.getAttributeValue("cmaterialvid");
    }

    /**
     * 属性cmaterialvid的Setter方法.属性名：物料多版本
     * 创建日期:
     * 
     * @param newCmaterialvid java.lang.String
     */
    public void setCmaterialvid(java.lang.String newCmaterialvid) {
        this.setAttributeValue("cmaterialvid", newCmaterialvid);
    }

    /**
     * 属性cmeasdocid的Getter方法.属性名：计量单位
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCmeasdocid() {
        return (java.lang.String) this.getAttributeValue("cmeasdocid");
    }

    /**
     * 属性cmeasdocid的Setter方法.属性名：计量单位
     * 创建日期:
     * 
     * @param newCmeasdocid java.lang.String
     */
    public void setCmeasdocid(java.lang.String newCmeasdocid) {
        this.setAttributeValue("cmeasdocid", newCmeasdocid);
    }

    /**
     * 属性cprojectid的Getter方法.属性名：项目
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCprojectid() {
        return (java.lang.String) this.getAttributeValue("cprojectid");
    }

    /**
     * 属性cprojectid的Setter方法.属性名：项目
     * 创建日期:
     * 
     * @param newCprojectid java.lang.String
     */
    public void setCprojectid(java.lang.String newCprojectid) {
        this.setAttributeValue("cprojectid", newCprojectid);
    }

    /**
     * 属性cvendorid的Getter方法.属性名：供应商
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCvendorid() {
        return (java.lang.String) this.getAttributeValue("cvendorid");
    }

    /**
     * 属性cvendorid的Setter方法.属性名：供应商
     * 创建日期:
     * 
     * @param newCvendorid java.lang.String
     */
    public void setCvendorid(java.lang.String newCvendorid) {
        this.setAttributeValue("cvendorid", newCvendorid);
    }

    /**
     * 属性cproductorid的Getter方法.属性名：生产厂商
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCproductorid() {
        return (java.lang.String) this.getAttributeValue("cproductorid");
    }

    /**
     * 属性cproductorid的Setter方法.属性名：生产厂商
     * 创建日期:
     * 
     * @param newCproductorid java.lang.String
     */
    public void setCproductorid(java.lang.String newCproductorid) {
        this.setAttributeValue("cproductorid", newCproductorid);
    }

    /**
     * 属性ccustomerid的Getter方法.属性名：客户
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCcustomerid() {
        return (java.lang.String) this.getAttributeValue("ccustomerid");
    }

    /**
     * 属性ccustomerid的Setter方法.属性名：客户
     * 创建日期:
     * 
     * @param newCcustomerid java.lang.String
     */
    public void setCcustomerid(java.lang.String newCcustomerid) {
        this.setAttributeValue("ccustomerid", newCcustomerid);
    }

    /**
     * 属性vbfree1的Getter方法.属性名：自由辅助属性1
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVbfree1() {
        return (java.lang.String) this.getAttributeValue("vbfree1");
    }

    /**
     * 属性vbfree1的Setter方法.属性名：自由辅助属性1
     * 创建日期:
     * 
     * @param newVbfree1 java.lang.String
     */
    public void setVbfree1(java.lang.String newVbfree1) {
        this.setAttributeValue("vbfree1", newVbfree1);
    }

    /**
     * 属性vbfree2的Getter方法.属性名：自由辅助属性2
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVbfree2() {
        return (java.lang.String) this.getAttributeValue("vbfree2");
    }

    /**
     * 属性vbfree2的Setter方法.属性名：自由辅助属性2
     * 创建日期:
     * 
     * @param newVbfree2 java.lang.String
     */
    public void setVbfree2(java.lang.String newVbfree2) {
        this.setAttributeValue("vbfree2", newVbfree2);
    }

    /**
     * 属性vbfree3的Getter方法.属性名：自由辅助属性3
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVbfree3() {
        return (java.lang.String) this.getAttributeValue("vbfree3");
    }

    /**
     * 属性vbfree3的Setter方法.属性名：自由辅助属性3
     * 创建日期:
     * 
     * @param newVbfree3 java.lang.String
     */
    public void setVbfree3(java.lang.String newVbfree3) {
        this.setAttributeValue("vbfree3", newVbfree3);
    }

    /**
     * 属性vbfree4的Getter方法.属性名：自由辅助属性4
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVbfree4() {
        return (java.lang.String) this.getAttributeValue("vbfree4");
    }

    /**
     * 属性vbfree4的Setter方法.属性名：自由辅助属性4
     * 创建日期:
     * 
     * @param newVbfree4 java.lang.String
     */
    public void setVbfree4(java.lang.String newVbfree4) {
        this.setAttributeValue("vbfree4", newVbfree4);
    }

    /**
     * 属性vbfree5的Getter方法.属性名：自由辅助属性5
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVbfree5() {
        return (java.lang.String) this.getAttributeValue("vbfree5");
    }

    /**
     * 属性vbfree5的Setter方法.属性名：自由辅助属性5
     * 创建日期:
     * 
     * @param newVbfree5 java.lang.String
     */
    public void setVbfree5(java.lang.String newVbfree5) {
        this.setAttributeValue("vbfree5", newVbfree5);
    }

    /**
     * 属性vbfree6的Getter方法.属性名：自由辅助属性6
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVbfree6() {
        return (java.lang.String) this.getAttributeValue("vbfree6");
    }

    /**
     * 属性vbfree6的Setter方法.属性名：自由辅助属性6
     * 创建日期:
     * 
     * @param newVbfree6 java.lang.String
     */
    public void setVbfree6(java.lang.String newVbfree6) {
        this.setAttributeValue("vbfree6", newVbfree6);
    }

    /**
     * 属性vbfree7的Getter方法.属性名：自由辅助属性7
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVbfree7() {
        return (java.lang.String) this.getAttributeValue("vbfree7");
    }

    /**
     * 属性vbfree7的Setter方法.属性名：自由辅助属性7
     * 创建日期:
     * 
     * @param newVbfree7 java.lang.String
     */
    public void setVbfree7(java.lang.String newVbfree7) {
        this.setAttributeValue("vbfree7", newVbfree7);
    }

    /**
     * 属性vbfree8的Getter方法.属性名：自由辅助属性8
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVbfree8() {
        return (java.lang.String) this.getAttributeValue("vbfree8");
    }

    /**
     * 属性vbfree8的Setter方法.属性名：自由辅助属性8
     * 创建日期:
     * 
     * @param newVbfree8 java.lang.String
     */
    public void setVbfree8(java.lang.String newVbfree8) {
        this.setAttributeValue("vbfree8", newVbfree8);
    }

    /**
     * 属性vbfree9的Getter方法.属性名：自由辅助属性9
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVbfree9() {
        return (java.lang.String) this.getAttributeValue("vbfree9");
    }

    /**
     * 属性vbfree9的Setter方法.属性名：自由辅助属性9
     * 创建日期:
     * 
     * @param newVbfree9 java.lang.String
     */
    public void setVbfree9(java.lang.String newVbfree9) {
        this.setAttributeValue("vbfree9", newVbfree9);
    }

    /**
     * 属性vbfree10的Getter方法.属性名：自由辅助属性10
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVbfree10() {
        return (java.lang.String) this.getAttributeValue("vbfree10");
    }

    /**
     * 属性vbfree10的Setter方法.属性名：自由辅助属性10
     * 创建日期:
     * 
     * @param newVbfree10 java.lang.String
     */
    public void setVbfree10(java.lang.String newVbfree10) {
        this.setAttributeValue("vbfree10", newVbfree10);
    }

    /**
     * 属性vpricesource的Getter方法.属性名：价格来源
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVpricesource() {
        return (java.lang.String) this.getAttributeValue("vpricesource");
    }

    /**
     * 属性vpricesource的Setter方法.属性名：价格来源
     * 创建日期:
     * 
     * @param newVpricesource java.lang.String
     */
    public void setVpricesource(java.lang.String newVpricesource) {
        this.setAttributeValue("vpricesource", newVpricesource);
    }

    /**
     * 属性vpricesourcenum的Getter方法.属性名：价格来源数
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVpricesourcenum() {
        return (java.lang.String) this.getAttributeValue("vpricesourcenum");
    }

    /**
     * 属性vpricesourcenum的Setter方法.属性名：价格来源数
     * 创建日期:
     * 
     * @param newVpricesourcenum java.lang.String
     */
    public void setVpricesourcenum(java.lang.String newVpricesourcenum) {
        this.setAttributeValue("vpricesourcenum", newVpricesourcenum);
    }

    /**
     * 属性vpricesourcereal的Getter方法.属性名：实际价格来源
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVpricesourcereal() {
        return (java.lang.String) this.getAttributeValue("vpricesourcereal");
    }

    /**
     * 属性vpricesourcereal的Setter方法.属性名：实际价格来源
     * 创建日期:
     * 
     * @param newVpricesourcereal java.lang.String
     */
    public void setVpricesourcereal(java.lang.String newVpricesourcereal) {
        this.setAttributeValue("vpricesourcereal", newVpricesourcereal);
    }

    /**
     * 属性vpricesourcerealnum的Getter方法.属性名：实际价格来源数
     * 创建日期:
     * 
     * @return java.lang.String
     */
    public java.lang.String getVpricesourcerealnum() {
        return (java.lang.String) this.getAttributeValue("vpricesourcerealnum");
    }

    /**
     * 属性vpricesourcerealnum的Setter方法.属性名：实际价格来源数
     * 创建日期:
     * 
     * @param newVpricesourcerealnum java.lang.String
     */
    public void setVpricesourcerealnum(java.lang.String newVpricesourcerealnum) {
        this.setAttributeValue("vpricesourcerealnum", newVpricesourcerealnum);
    }

    /**
     * 属性nprice的Getter方法.属性名：单价
     * 创建日期:
     * 
     * @return nc.vo.pub.lang.UFDouble
     */
    public nc.vo.pub.lang.UFDouble getNprice() {
        return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("nprice");
    }

    /**
     * 属性nprice的Setter方法.属性名：单价
     * 创建日期:
     * 
     * @param newNprice nc.vo.pub.lang.UFDouble
     */
    public void setNprice(nc.vo.pub.lang.UFDouble newNprice) {
        this.setAttributeValue("nprice", newNprice);
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
        return "cmaterialpriceid";
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
        return "cmaterialprice_bid";
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
        return "mapub_materialpricebase_b";
    }

    /**
     * 按照默认方式创建构造子.
     * 创建日期:
     */
    public MaterialPriceBaseBodyVO() {
        super();
    }

    @Override
    @nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO")
    public IVOMeta getMetaData() {
        IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("mapub.mapub_materialpricebase_b");
        return meta;
    }
}
