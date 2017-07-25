package nc.pubitf.coprodcoef.pub;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

@SuppressWarnings("serial")
public class QueryDetailinfo implements Serializable {
    private String material;// 物料

    private UFDouble Cofficient;// 联副系数

    private String qry_CostCenter;// 成本中心条件

    private String qry_factor;// 核算要素条件

    private String result_CostCenter;// 成本中心结果

    private String result_factor;// 核算要素结果

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
     * @param cofficient the cofficient to set
     */
    public void setCofficient(UFDouble cofficient) {
        this.Cofficient = cofficient;
    }

    /**
     * @return the cofficient
     */
    public UFDouble getCofficient() {
        return this.Cofficient;
    }

    /**
     * @param qry_CostCenter the qry_CostCenter to set
     */
    public void setQry_CostCenter(String qry_CostCenter) {
        this.qry_CostCenter = qry_CostCenter;
    }

    /**
     * @return the qry_CostCenter
     */
    public String getQry_CostCenter() {
        return this.qry_CostCenter;
    }

    /**
     * @param qry_factor the qry_factor to set
     */
    public void setQry_factor(String qry_factor) {
        this.qry_factor = qry_factor;
    }

    /**
     * @return the qry_factor
     */
    public String getQry_factor() {
        return this.qry_factor;
    }

    /**
     * @param result_CostCenter the result_CostCenter to set
     */
    public void setResult_CostCenter(String result_CostCenter) {
        this.result_CostCenter = result_CostCenter;
    }

    /**
     * @return the result_CostCenter
     */
    public String getResult_CostCenter() {
        return this.result_CostCenter;
    }

    /**
     * @param result_factor the result_factor to set
     */
    public void setResult_factor(String result_factor) {
        this.result_factor = result_factor;
    }

    /**
     * @return the result_factor
     */
    public String getResult_factor() {
        return this.result_factor;
    }
}
