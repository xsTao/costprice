package nc.vo.mapub.costpricebase.entity;

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
     * ��õ��ݱ�ͷVO
     *
     * @return ���ݱ�ͷVO
     */
    @Override
    public CostPriceHeadVO getParent() {
        return this.getParent();
    }

    /**
     * ��õ��ݱ���VO
     *
     * @return ���ݱ���VO����
     */
    public CostPriceBodyVO[] getItemVO() {
        return this.getItemVO();
    }
}
