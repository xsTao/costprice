package nc.bs.mapub.materialpricebase.util;

public class SourceParamVO {

    private String pk_group;

    private String pk_org;

    /**
     * 价格来源
     */
    private Integer priceType;

    public String getPk_group() {
        return this.pk_group;
    }

    public void setPk_group(String pk_group) {
        this.pk_group = pk_group;
    }

    public String getPk_org() {
        return this.pk_org;
    }

    public void setPk_org(String pk_org) {
        this.pk_org = pk_org;
    }

    public Integer getPriceType() {
        return this.priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

}
