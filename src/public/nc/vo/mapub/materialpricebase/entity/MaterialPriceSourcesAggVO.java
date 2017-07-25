package nc.vo.mapub.materialpricebase.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * 价格来源对话框
 * aggVO
 * 创建日期:
 * 
 * @author
 * @version NCPrj ??
 */
@SuppressWarnings("serial")
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.mapub.materialpricebase.entity.MaterialPriceSourcesHeadVO")
public class MaterialPriceSourcesAggVO extends AbstractBill {

    /*
     * (non-Javadoc)
     * @see nc.vo.pubapp.pattern.model.entity.bill.IBill#getMetaData()
     */
    @Override
    public IBillMeta getMetaData() {
        return BillMetaFactory.getInstance().getBillMeta("mapub.mapub_materialPriceSources");
    }

    /**
     * 获得单据表头VO
     * 
     * @return 单据表头VO
     */
    @Override
    public MaterialPriceSourcesHeadVO getParentVO() {
        return (MaterialPriceSourcesHeadVO) this.getParent();
    }

    /**
     * 获得单据表体VO
     * 
     * @return 单据表体VO数组
     */
    public MaterialPriceSourcesBodyVO[] getItemVO() {
        return (MaterialPriceSourcesBodyVO[]) this.getChildren(MaterialPriceSourcesBodyVO.class);
    }
}
