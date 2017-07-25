package nc.pubitf.coprodcoef.pub;

import java.io.Serializable;
import java.util.List;

/**
 * ����ϵ����ѯ���
 * 
 * @author ligq
 */

@SuppressWarnings("serial")
public class QueryCofficientInfoDTO implements Serializable {
    /**
     * ����Ʒ
     */
    private String pk_org;// ����

    private String m_material;// ���ϣ�����Ʒ��

    // private UFDouble m_Cofficient;// ����Ʒ����ϵ��
    private List<QueryDetailinfo> m_Cofficient;// ����Ʒ

    private List<QueryDetailinfo> l_material;// ����Ʒ

    private List<QueryDetailinfo> f_material;// ����Ʒ

    /*
     * public class QueryDetailinfo {
     * private String material;// ����
     * private Double Cofficient;// ����ϵ��
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
     * @param ����Ʒ
     */
    public void setM_material(String m_material) {
        this.m_material = m_material;
    }

    /**
     * @return ����Ʒ
     */
    public String getM_material() {
        return this.m_material;
    }

    /**
     * @param ����Ʒ
     */
    public void setL_material(List<QueryDetailinfo> l_material) {
        this.l_material = l_material;
    }

    /**
     * @return ����Ʒ
     */
    public List<QueryDetailinfo> getL_material() {
        return this.l_material;
    }

    /**
     * @param ����Ʒ
     */
    public void setF_material(List<QueryDetailinfo> f_material) {
        this.f_material = f_material;
    }

    /**
     * @return ����Ʒ
     */
    public List<QueryDetailinfo> getF_material() {
        return this.f_material;
    }

    /**
     * @param ����
     */
    public void setPk_org(String pk_org) {
        this.pk_org = pk_org;
    }

    /**
     * @return ����
     */
    public String getPk_org() {
        return this.pk_org;
    }

    /**
     * @param ����Ʒ
     */
    public void setM_Cofficient(List<QueryDetailinfo> m_Cofficient) {
        this.m_Cofficient = m_Cofficient;
    }

    /**
     * @return the ����Ʒ
     */
    public List<QueryDetailinfo> getM_Cofficient() {
        return this.m_Cofficient;
    }

}
