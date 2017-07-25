package nc.pubitf.coprodcoef.pub;

import java.io.Serializable;
import java.util.List;

/**
 * 联副系数查询结果
 * 
 * @author ligq
 */

@SuppressWarnings("serial")
public class QueryCofficientInfoDTO implements Serializable {
    /**
     * 主产品
     */
    private String pk_org;// 工厂

    private String m_material;// 物料（主产品）

    // private UFDouble m_Cofficient;// 主产品联副系数
    private List<QueryDetailinfo> m_Cofficient;// 主产品

    private List<QueryDetailinfo> l_material;// 联产品

    private List<QueryDetailinfo> f_material;// 副产品

    /*
     * public class QueryDetailinfo {
     * private String material;// 物料
     * private Double Cofficient;// 联副系数
     *//**
     * @param material the material to set
     */
    /*
     * public void setMaterial(String material) {
     * this.material = material;
     * }
     *//**
     * @return the material
     */
    /*
     * public String getMaterial() {
     * return this.material;
     * }
     *//**
     * @param cofficient the cofficient to set
     */
    /*
     * public void setCofficient(Double cofficient) {
     * this.Cofficient = cofficient;
     * }
     *//**
     * @return the cofficient
     */
    /*
     * public Double getCofficient() {
     * return this.Cofficient;
     * }
     * }
     */

    /**
     * @param 主产品
     */
    public void setM_material(String m_material) {
        this.m_material = m_material;
    }

    /**
     * @return 主产品
     */
    public String getM_material() {
        return this.m_material;
    }

    /**
     * @param 联产品
     */
    public void setL_material(List<QueryDetailinfo> l_material) {
        this.l_material = l_material;
    }

    /**
     * @return 联产品
     */
    public List<QueryDetailinfo> getL_material() {
        return this.l_material;
    }

    /**
     * @param 副产品
     */
    public void setF_material(List<QueryDetailinfo> f_material) {
        this.f_material = f_material;
    }

    /**
     * @return 副产品
     */
    public List<QueryDetailinfo> getF_material() {
        return this.f_material;
    }

    /**
     * @param 工厂
     */
    public void setPk_org(String pk_org) {
        this.pk_org = pk_org;
    }

    /**
     * @return 工厂
     */
    public String getPk_org() {
        return this.pk_org;
    }

    /**
     * @param 主产品
     */
    public void setM_Cofficient(List<QueryDetailinfo> m_Cofficient) {
        this.m_Cofficient = m_Cofficient;
    }

    /**
     * @return the 主产品
     */
    public List<QueryDetailinfo> getM_Cofficient() {
        return this.m_Cofficient;
    }

}
