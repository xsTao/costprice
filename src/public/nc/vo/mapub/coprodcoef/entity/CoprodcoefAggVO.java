package nc.vo.mapub.coprodcoef.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * ���ӱ�/����ͷ/������ۺ�VO
 * ��������:
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
     * ��õ��ݱ�ͷVO
     * 
     * @return ���ݱ�ͷVO
     */
    @Override
    public CoprodcoefHeadVO getParentVO() {
        return (CoprodcoefHeadVO) this.getParent();
    }

    /**
     * ��õ��ݱ���VO
     * 
     * @return ���ݱ���VO����
     */
    public CoprodcoefItemVO[] getItemVO() {
        return (CoprodcoefItemVO[]) this.getChildren(CoprodcoefItemVO.class);
    }
}
