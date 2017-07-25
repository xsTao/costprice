/**
 *
 */
package nc.bs.mapub.costpricebase.bp;

import nc.bs.mapub.materialpricebase.plugin.bpplugin.MaterialPriceBasePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;

/**
 * @since v6.3
 * @version 2017年7月20日 上午10:10:37
 * @author Administrator
 */
public class CostPriceBaseDeleteBP {

    public void delete(CostPriceAggVO[] bills) {
        DeleteBPTemplate<CostPriceAggVO> bp = new DeleteBPTemplate<CostPriceAggVO>(MaterialPriceBasePluginPoint.DELETE);
        // // 增加执行前业务规则
        // this.addBeforeRule(bp.getAroundProcesser());
        // // 增加执行后业务规则
        // this.addAfterRule(bp.getAroundProcesser());
        bp.delete(bills);
    }
}
