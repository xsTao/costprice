/***************************************************************\
 *     The skeleton of this class is generated by an automatic *
 * code generator for NC product. It is based on Velocity.     *
\***************************************************************/
package nc.vo.mapub.materialpricebase.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> ȡ�۶Ի��� </b>
 * <p>
 * bodyVO
 * </p>
 * ��������:
 * 
 * @author
 * @version NCPrj ??
 */
@SuppressWarnings("serial")
public class MaterialPullPriceBodyVO extends SuperVO {

    public static final String CMATERIALPULLPRICE_BID = "cmaterialpullprice_bid";

    public static final String CMATERIALPULLPRICEID = "cmaterialpullpriceid";

    /**
     * ����cmaterialpullprice_bid��Getter����.������������ȡ����ϸ
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCmaterialpullprice_bid() {
        return (java.lang.String) this.getAttributeValue("cmaterialpullprice_bid");
    }

    /**
     * ����cmaterialpullprice_bid��Setter����.������������ȡ����ϸ
     * ��������:
     * 
     * @param newCmaterialpullprice_bid java.lang.String
     */
    public void setCmaterialpullprice_bid(java.lang.String newCmaterialpullprice_bid) {
        this.setAttributeValue("cmaterialpullprice_bid", newCmaterialpullprice_bid);
    }

    /**
     * ����cmaterialpullpriceid��Getter����.������������ȡ��
     * ��������:
     * 
     * @return java.lang.String
     */
    public java.lang.String getCmaterialpullpriceid() {
        return (java.lang.String) this.getAttributeValue("cmaterialpullpriceid");
    }

    /**
     * ����cmaterialpullpriceid��Setter����.������������ȡ��
     * ��������:
     * 
     * @param newCmaterialpullpriceid java.lang.String
     */
    public void setCmaterialpullpriceid(java.lang.String newCmaterialpullpriceid) {
        this.setAttributeValue("cmaterialpullpriceid", newCmaterialpullpriceid);
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
        return "cmaterialpullpriceid";
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
        return "cmaterialpullprice_bid";
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
        return "mapub_materialpullprice_b";
    }

    /**
     * ����Ĭ�Ϸ�ʽ����������.
     * ��������:
     */
    public MaterialPullPriceBodyVO() {
        super();
    }

    @Override
    @nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.mapub.materialpricebase.entity.MaterialPullPriceBodyVO")
    public IVOMeta getMetaData() {
        IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("mapub.mapub_materialpullprice_b");
        return meta;
    }
}