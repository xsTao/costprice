/**
 *
 */
package nc.bs.mapub.costpricebase.bp;

import nc.bs.mapub.costpricebase.plugin.bpplugin.CostPriceBasePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;

/**
 * @since v6.3
 * @version 2017��7��26�� ����1:56:45
 * @author Administrator
 */
public class CostPriceBaseUpdateBP {

    public CostPriceAggVO[] update(CostPriceAggVO[] bills, CostPriceAggVO[] originBills) {
        UpdateBPTemplate<CostPriceAggVO> bp = new UpdateBPTemplate<CostPriceAggVO>(CostPriceBasePluginPoint.UPDATE);
        // ����ǰ�Ĳ���
        // this.beforeUpdate();
        // ���º�Ĳ���
        // this.afterUpdate();
        return bp.update(bills, originBills);
    }
}
