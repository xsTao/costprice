/**
 *
 */
package nc.ui.mapub.costpricebase.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.costpricebase.ICostPriceBaseMaintainService;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;

/**
 * @since v6.3
 * @version 2017年7月19日 下午3:51:59
 * @author Administrator
 */
public class CostPriceBaseDeleteProxy implements ISingleBillService<CostPriceAggVO> {

    /*
     * (non-Javadoc)
     * @see nc.ui.pubapp.pub.task.ISingleBillService#operateBill(java.lang.Object)
     */
    @Override
    public CostPriceAggVO operateBill(CostPriceAggVO costPriceAggVO) throws Exception {
        // TODO Auto-generated method stub
        ICostPriceBaseMaintainService operate = NCLocator.getInstance().lookup(ICostPriceBaseMaintainService.class);

        operate.delete(new CostPriceAggVO[] {
            costPriceAggVO
        });
        return costPriceAggVO;
    }

}
