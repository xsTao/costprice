package nc.vo.mapub.materialpricebase.entity;

/**
 * <b> �۸������ӱ�۸��ѯʱר�õ�VO</b>
 * <p>
 * �����Ҫ��������/��ҵ/�����ӱ�ļ۸��ѯ����Ҫת���ɸ�VO
 * </p>
 * ��������:2010-9-11
 * 
 * @author:jilu
 */
public class MaterialPriceBaseQueryPriceParamVO {

    /**
     * ��֯ID
     */
    private String pkOrg;

    /**
     * ���ϼ۸������
     */
    private String cmaterialpricebaseid;

    /**
     * ����id
     */
    private String itemid;

    /**
     * get����
     * 
     * @return String
     */
    public String getPkOrg() {
        return this.pkOrg;
    }

    /**
     * set����
     * 
     * @param pkOrg
     *            String
     */
    public void setPkOrg(String pkOrg) {
        this.pkOrg = pkOrg;
    }

    /**
     * get����
     * 
     * @return String
     */
    public String getCmaterialpricebaseid() {
        return this.cmaterialpricebaseid;
    }

    /**
     * set����
     * 
     * @param cpricebaseid
     *            String
     */
    public void setCpricebaseid(String cmaterialpricebaseid) {
        this.cmaterialpricebaseid = cmaterialpricebaseid;
    }

    /**
     * get����
     * 
     * @return String
     */
    public String getItemid() {
        return this.itemid;
    }

    /**
     * set����
     * 
     * @param itemid
     *            String
     */
    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    /**
     * ƴ��map��key�����ø÷������õ���ѯ�۸�Ľ��(Map<key, double> �и��ݸ÷������key)
     * 
     * @return keyֵ
     */
    public String getMapKey() {
        // ����ɱ�����Ϊ�գ��򲻽������key�ַ�����
        return this.getPkOrg() + this.getCmaterialpricebaseid() + this.getItemid();
    }

}
