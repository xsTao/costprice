package nc.vo.mapub.materialpricebase.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * 取价对话框
 * aggVO
 * 
 * @author
 * @version NCPrj ??
 */
@SuppressWarnings("serial")
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.mapub.materialpricebase.entity.MaterialPullPriceHeadVO")
public class MaterialPullPriceAggVO extends AbstractBill {

    /*
     * (non-Javadoc)
     * @see nc.vo.pubapp.pattern.model.entity.bill.IBill#getMetaData()
     */
    @Override
    public IBillMeta getMetaData() {
        return BillMetaFactory.getInstance().getBillMeta("mapub.mapub_materialpullprice");
    }

    /**
     * 获得单据表头VO
     * 
     * @return 单据表头VO
     */
    @Override
    public MaterialPullPriceHeadVO getParentVO() {
        return (MaterialPullPriceHeadVO) this.getParent();
    }

    /**
     * 获得单据表体VO
     * 
     * @return 单据表体VO数组
     */
    public MaterialPullPriceBodyVO[] getItemVO() {
        return (MaterialPullPriceBodyVO[]) this.getChildren(MaterialPullPriceBodyVO.class);
    }
}
