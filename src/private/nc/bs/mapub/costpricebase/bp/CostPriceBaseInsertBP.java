/**
 *
 */
package nc.bs.mapub.costpricebase.bp;

import nc.bs.mapub.costpricebase.plugin.bpplugin.CostPriceBasePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;

/**
 * @since v6.3
 * @version 2017��7��26�� ����9:55:34
 * @author Administrator
 */
public class CostPriceBaseInsertBP {

    public CostPriceAggVO[] insert(CostPriceAggVO[] bills) {
        InsertBPTemplate<CostPriceAggVO> bp = new InsertBPTemplate<CostPriceAggVO>(CostPriceBasePluginPoint.INSERT);
        // ����֮ǰ����
        // this.beforeInsert(bills);
        // ����֮�����
        // this.afterInsert(bills);
        return bp.insert(bills);
    }
}
