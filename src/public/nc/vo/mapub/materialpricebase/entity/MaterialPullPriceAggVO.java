package nc.vo.mapub.materialpricebase.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * ȡ�۶Ի���
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
     * ��õ��ݱ�ͷVO
     * 
     * @return ���ݱ�ͷVO
     */
    @Override
    public MaterialPullPriceHeadVO getParentVO() {
        return (MaterialPullPriceHeadVO) this.getParent();
    }

    /**
     * ��õ��ݱ���VO
     * 
     * @return ���ݱ���VO����
     */
    public MaterialPullPriceBodyVO[] getItemVO() {
        return (MaterialPullPriceBodyVO[]) this.getChildren(MaterialPullPriceBodyVO.class);
    }
}
