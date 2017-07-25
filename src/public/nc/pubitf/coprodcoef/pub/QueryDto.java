package nc.pubitf.coprodcoef.pub;

import java.io.Serializable;
import java.util.List;

/**
 * ��ѯ����
 * 
 * @author ligq
 */

@SuppressWarnings("serial")
public class QueryDto implements Serializable {

    private String pk_org;// ����

    private boolean cmrule;// �Ƿ񰴳ɱ����ļ����׼�ɱ�

    private String m_material;// ������

    private List<QueryField> m_materials;// ����Ʒ+�ɱ�����+����Ҫ��

    private List<QueryField> l_material;// ����Ʒ+�ɱ�����+����Ҫ��

    private List<QueryField> f_material;// ����Ʒ+�ɱ�����+����Ҫ��

    /**
     * @param cmrule the cmrule to set
     */
    public void setPk_org(String pk_org) {
        this.pk_org = pk_org;
    }

    /**
     * @return the cmrule
     */
    public String getPk_org() {
        return this.pk_org;
    }

    /**
     * @param cmrule the cmrule to set
     */
    public void setCmrule(boolean cmrule) {
        this.cmrule = cmrule;
    }

    /**
     * @return the cmrule
     */
    public boolean isCmrule() {
        return this.cmrule;
    }

    /**
     * @param m_material the m_material to set
     */
    public void setM_material(String m_material) {
        this.m_material = m_material;
    }

    /**
     * @return the m_material
     */
    public String getM_material() {
        return this.m_material;
    }

    /**
     * @param m_materials the m_materials to set
     */
    public void setM_materials(List<QueryField> m_materials) {
        this.m_materials = m_materials;
    }

    /**
     * @return the m_materials
     */
    public List<QueryField> getM_materials() {
        return this.m_materials;
    }

    /**
     * @param l_material the l_material to set
     */
    public void setL_material(List<QueryField> l_material) {
        this.l_material = l_material;
    }

    /**
     * @return the l_material
     */
    public List<QueryField> getL_material() {
        return this.l_material;
    }

    /**
     * @param f_material the f_material to set
     */
    public void setF_material(List<QueryField> f_material) {
        this.f_material = f_material;
    }

    /**
     * @return the f_material
     */
    public List<QueryField> getF_material() {
        return this.f_material;
    }
}
