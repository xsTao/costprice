/**
 *
 */
package nc.ui.mapub.costpricebase.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.costpricebase.ICostPriceBaseMaintainService;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * @since v6.3
 * @version 2017年7月19日 下午1:23:25
 * @author Administrator
 */
public class CostPriceBaseMaintainProxy implements IDataOperationService {

    private ICostPriceBaseMaintainService costPriceMaintainService;

    /*
     * (non-Javadoc)
     * @see nc.ui.pubapp.uif2app.actions.IDataOperationService#delete(nc.vo.pubapp.pattern.model.entity.bill.IBill[])
     */
    @Override
    public IBill[] delete(IBill[] bills) throws BusinessException {
        // 目前的删除并不是走这个方法，由于pubapp不支持从这个服务中执行删除操作
        // 单据的删除实际上使用的是：nc.ui.mapub.costpricebase.serviceproxy.CostPriceBaseDeleteProxy
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.pubapp.uif2app.actions.IDataOperationService#insert(nc.vo.pubapp.pattern.model.entity.bill.IBill[])
     */
    @Override
    public IBill[] insert(IBill[] bills) throws BusinessException {
        // TODO Auto-generated method stub
        CostPriceAggVO[] vos = this.getCostPriceMaintainService().insert((CostPriceAggVO[]) bills);
        return vos;
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.pubapp.uif2app.actions.IDataOperationService#update(nc.vo.pubapp.pattern.model.entity.bill.IBill[])
     */
    @Override
    public IBill[] update(IBill[] bills) throws BusinessException {
        CostPriceAggVO[] vos = this.getCostPriceMaintainService().update((CostPriceAggVO[]) bills);
        return vos;
    }

    /*
     * public IBill[] query(IQueryScheme queryScheme) throws BusinessException {
     * CostPriceAggVO[] vos = this.getCostPriceMaintainService().query(queryScheme);
     * return vos;
     * }
     */

    /**
     * 获取费用价格的服务
     *
     * @return IDataOperationService
     */
    private ICostPriceBaseMaintainService getCostPriceMaintainService() {
        // TODO Auto-generated method stub
        if (this.costPriceMaintainService == null) {
            this.costPriceMaintainService = NCLocator.getInstance().lookup(ICostPriceBaseMaintainService.class);
        }
        return this.costPriceMaintainService;
    }
}
