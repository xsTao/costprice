package nc.vo.mapub.coprodcoef.entity;

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
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO")
public class CoprodcoefAggVO extends AbstractBill {

    @Override
    public IBillMeta getMetaData() {
        return BillMetaFactory.getInstance().getBillMeta("mapub.mapub_coprodcoef");

    }

    /**
     * 获得单据表头VO
     * 
     * @return 单据表头VO
     */
    @Override
    public CoprodcoefHeadVO getParentVO() {
        return (CoprodcoefHeadVO) this.getParent();
    }

    /**
     * 获得单据表体VO
     * 
     * @return 单据表体VO数组
     */
    public CoprodcoefItemVO[] getItemVO() {
        return (CoprodcoefItemVO[]) this.getChildren(CoprodcoefItemVO.class);
    }
}
