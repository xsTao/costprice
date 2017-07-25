package nc.vo.mapub.materialpricebase.entity;

/**
 * <b> 价格库进行子表价格查询时专用的VO</b>
 * <p>
 * 如果需要进行物料/作业/费用子表的价格查询，需要转化成该VO
 * </p>
 * 创建日期:2010-9-11
 * 
 * @author:jilu
 */
public class MaterialPriceBaseQueryPriceParamVO {

    /**
     * 组织ID
     */
    private String pkOrg;

    /**
     * 材料价格库主键
     */
    private String cmaterialpricebaseid;

    /**
     * 物料id
     */
    private String itemid;

    /**
     * get方法
     * 
     * @return String
     */
    public String getPkOrg() {
        return this.pkOrg;
    }

    /**
     * set方法
     * 
     * @param pkOrg
     *            String
     */
    public void setPkOrg(String pkOrg) {
        this.pkOrg = pkOrg;
    }

    /**
     * get方法
     * 
     * @return String
     */
    public String getCmaterialpricebaseid() {
        return this.cmaterialpricebaseid;
    }

    /**
     * set方法
     * 
     * @param cpricebaseid
     *            String
     */
    public void setCpricebaseid(String cmaterialpricebaseid) {
        this.cmaterialpricebaseid = cmaterialpricebaseid;
    }

    /**
     * get方法
     * 
     * @return String
     */
    public String getItemid() {
        return this.itemid;
    }

    /**
     * set方法
     * 
     * @param itemid
     *            String
     */
    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    /**
     * 拼成map的key，调用该方法来得到查询价格的结果(Map<key, double> 中根据该方法获得key)
     * 
     * @return key值
     */
    public String getMapKey() {
        // 如果成本中心为空，则不将其加入key字符串中
        return this.getPkOrg() + this.getCmaterialpricebaseid() + this.getItemid();
    }

}
