/**
 *
 */
package nc.bs.mapub.costpricebase.bp;

import nc.bs.mapub.costpricebase.plugin.bpplugin.CostPriceBasePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;

/**
 * @since v6.3
 * @version 2017年7月26日 上午9:55:34
 * @author Administrator
 */
public class CostPriceBaseInsertBP {

    public CostPriceAggVO[] insert(CostPriceAggVO[] bills) {
        InsertBPTemplate<CostPriceAggVO> bp = new InsertBPTemplate<CostPriceAggVO>(CostPriceBasePluginPoint.INSERT);
        // 插入之前操作
        // this.beforeInsert(bills);
        // 插入之后操作
        // this.afterInsert(bills);
        return bp.insert(bills);
    }
}
