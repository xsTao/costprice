/**
 *
 */
package nc.bs.mapub.costpricebase.bp;

import nc.bs.mapub.materialpricebase.plugin.bpplugin.MaterialPriceBasePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;

/**
 * @since v6.3
 * @version 2017��7��20�� ����10:10:37
 * @author Administrator
 */
public class CostPriceBaseDeleteBP {

    public void delete(CostPriceAggVO[] bills) {
        DeleteBPTemplate<CostPriceAggVO> bp = new DeleteBPTemplate<CostPriceAggVO>(MaterialPriceBasePluginPoint.DELETE);
        // // ����ִ��ǰҵ�����
        // this.addBeforeRule(bp.getAroundProcesser());
        // // ����ִ�к�ҵ�����
        // this.addAfterRule(bp.getAroundProcesser());
        bp.delete(bills);
    }
}
