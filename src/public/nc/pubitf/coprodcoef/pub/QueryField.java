package nc.pubitf.coprodcoef.pub;

import java.io.Serializable;

@SuppressWarnings("serial")
public class QueryField implements Serializable {
    private String material;// 物料id

    private String CostCenter;// 成本中心

    private String factor;// 核算要素

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @return the material
     */
    public String getMaterial() {
        return this.material;
    }

    /**
     * @param costCenter the costCenter to set
     */
    public void setCostCenter(String costCenter) {
        this.CostCenter = costCenter;
    }

    /**
     * @return the costCenter
     */
    public String getCostCenter() {
        return this.CostCenter;
    }

    /**
     * @param factor the factor to set
     */
    public void setFactor(String factor) {
        this.factor = factor;
    }

    /**
     * @return the factor
     */
    public String getFactor() {
        return this.factor;
    }

}
