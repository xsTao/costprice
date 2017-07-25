package nc.vo.mapub.materialpricebase.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * 材料价格库AggVO
 * 
 * @since 6.36
 * @version 2014-11-6 上午10:23:56
 * @author zhangchd
 */
@SuppressWarnings("serial")
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO")
public class MaterialPriceBaseAggVO extends AbstractBill {

    /*
     * (non-Javadoc)
     * @see nc.vo.pubapp.pattern.model.entity.bill.IBill#getMetaData()
     */
    @Override
    public IBillMeta getMetaData() {
        return BillMetaFactory.getInstance().getBillMeta("mapub.mapub_materialpricebase");
    }

    /**
     * 获得单据表头VO
     * 
     * @return 单据表头VO
     */
    @Override
    public MaterialPriceBaseHeadVO getParentVO() {
        return (MaterialPriceBaseHeadVO) this.getParent();
    }

    /**
     * 获得单据表体VO
     * 
     * @return 单据表体VO数组
     */
    public MaterialPriceBaseBodyVO[] getItemVO() {
        return (MaterialPriceBaseBodyVO[]) this.getChildren(MaterialPriceBaseBodyVO.class);
    }

}
