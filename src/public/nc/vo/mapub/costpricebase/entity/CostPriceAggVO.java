package nc.vo.mapub.costpricebase.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * 单子表/单表头/单表体聚合VO
 * 创建日期:
 *
 * @author
 * @version NCPrj ??
 */
@SuppressWarnings("serial")
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.mapub.costpricebase.entity.CostPriceHeadVO")
public class CostPriceAggVO extends AbstractBill {

    /*
     * (non-Javadoc)
     * @see nc.vo.pubapp.pattern.model.entity.bill.IBill#getMetaData()
     */
    @Override
    public IBillMeta getMetaData() {
        return BillMetaFactory.getInstance().getBillMeta("mapub.mapub_costprice");
    }

    /**
     * 获得单据表头VO
     *
     * @return 单据表头VO
     */
    @Override
    public CostPriceHeadVO getParent() {
        return this.getParent();
    }

    /**
     * 获得单据表体VO
     *
     * @return 单据表体VO数组
     */
    public CostPriceBodyVO[] getItemVO() {
        return this.getItemVO();
    }
}
