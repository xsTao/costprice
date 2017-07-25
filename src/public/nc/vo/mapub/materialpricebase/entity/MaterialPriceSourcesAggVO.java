package nc.vo.mapub.materialpricebase.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * �۸���Դ�Ի���
 * aggVO
 * ��������:
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
     * ��õ��ݱ�ͷVO
     * 
     * @return ���ݱ�ͷVO
     */
    @Override
    public MaterialPriceSourcesHeadVO getParentVO() {
        return (MaterialPriceSourcesHeadVO) this.getParent();
    }

    /**
     * ��õ��ݱ���VO
     * 
     * @return ���ݱ���VO����
     */
    public MaterialPriceSourcesBodyVO[] getItemVO() {
        return (MaterialPriceSourcesBodyVO[]) this.getChildren(MaterialPriceSourcesBodyVO.class);
    }
}
