package nc.vo.mapub.driver.entity;

import nc.vo.pub.SuperVO;

/**
 * <b> ��ѯ���� </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-4-15
 * 
 * @author:wangtf
 */
public class DriverQueryCondition extends SuperVO {
    /**
     * ���л�id
     */
    private static final long serialVersionUID = 2151363434134551410L;

    /**
     * ��֯����
     */
    private String pk_org;

    /**
     * ��������
     */
    private String pk_group;

    /**
     * ��ȡ��֯����
     * 
     * @return ��֯����
     */
    public String getPk_org() {
        return this.pk_org;
    }

    /**
     * ������֯����
     * 
     * @param pk_org ��֯����
     */
    public void setPk_org(String pk_org) {
        this.pk_org = pk_org;
    }

    /**
     * ��ȡ��������
     * 
     * @return ��������
     */
    public String getPk_group() {
        return this.pk_group;
    }

    /**
     * ���ü�������
     * 
     * @param pkGroup ��������
     */
    public void setPk_group(String pkGroup) {
        this.pk_group = pkGroup;
    }

}
