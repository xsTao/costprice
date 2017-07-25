package nc.vo.mapub.driver.entity;

import nc.vo.pub.SuperVO;

/**
 * <b> 查询条件 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-4-15
 * 
 * @author:wangtf
 */
public class DriverQueryCondition extends SuperVO {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 2151363434134551410L;

    /**
     * 组织主键
     */
    private String pk_org;

    /**
     * 集团主键
     */
    private String pk_group;

    /**
     * 获取组织主键
     * 
     * @return 组织主键
     */
    public String getPk_org() {
        return this.pk_org;
    }

    /**
     * 设置组织主键
     * 
     * @param pk_org 组织主键
     */
    public void setPk_org(String pk_org) {
        this.pk_org = pk_org;
    }

    /**
     * 获取集团主键
     * 
     * @return 集团主键
     */
    public String getPk_group() {
        return this.pk_group;
    }

    /**
     * 设置集团主键
     * 
     * @param pkGroup 集团主键
     */
    public void setPk_group(String pkGroup) {
        this.pk_group = pkGroup;
    }

}
