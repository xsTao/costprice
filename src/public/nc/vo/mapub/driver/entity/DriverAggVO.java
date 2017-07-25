/**
 * 
 */
package nc.vo.mapub.driver.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@SuppressWarnings("serial")
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.mapub.driver.entity.DriverVO")
public class DriverAggVO extends AbstractBill {
    @Override
    public IBillMeta getMetaData() {
        return BillMetaFactory.getInstance().getBillMeta("cm.cm_driver");
    }

    /**
     * 获得单据表头VO
     * 
     * @return 单据表头VO
     */
    @Override
    public DriverVO getParentVO() {
        return (DriverVO) this.getParent();
    }
}
