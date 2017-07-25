package nc.pubitf.coprodcoef.pub;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

@SuppressWarnings("serial")
public class QueryResultto implements Serializable {

    public static final String PK_ORG = "pk_org";

    public static final String M_MATERIAL = "m_material";

    public static final String M_MATERIAL_B = "m_material_b";

    public static final String CCOSTCENTERID = "ccostcenterid";

    public static final String CELEMENTID = "celementid";

    public static final String NRELCOEFFICIENT = "nrelcoefficient";

    public static final String IPRODUCTTYPE = "iproducttype";

    /**
     * ����Ʒ
     */
    private String pk_org;// ����

    private String m_material;// ���ϣ�����Ʒ��

    private String m_material_b;// ��Ʒ

    private String ccostcenterid;// �ɱ�����

    private String celementid;// ����Ҫ��

    private UFDouble nrelcoefficient;// ����ϵ��

    private UFDouble iproducttype;// ����������

    /**
     * @param pk_org
     */
    public void setPk_org(String pk_org) {
        this.pk_org = pk_org;
    }

    /**
     * @return pk_org
     */
    public String getPk_org() {
        return this.pk_org;
    }

    public void setM_material(String m_material) {
        this.m_material = m_material;
    }

    public String getM_material() {
        return this.m_material;
    }

    public void setM_material_b(String m_material_b) {
        this.m_material_b = m_material_b;
    }

    public String getM_material_b() {
        return this.m_material_b;
    }

    public void setCcostcenterid(String ccostcenterid) {
        this.ccostcenterid = ccostcenterid;
    }

    public String getCcostcenterid() {
        return this.ccostcenterid;
    }

    public void setCelementid(String celementid) {
        this.celementid = celementid;
    }

    public String getCelementid() {
        return this.celementid;
    }

    public void setOppositenum(UFDouble nrelcoefficient) {
        this.nrelcoefficient = nrelcoefficient;
    }

    public UFDouble getOppositenum() {
        return this.nrelcoefficient;
    }

    public void setProducttype(UFDouble iproducttype) {
        this.iproducttype = iproducttype;
    }

    public UFDouble getProducttype() {
        return this.iproducttype;
    }

}
