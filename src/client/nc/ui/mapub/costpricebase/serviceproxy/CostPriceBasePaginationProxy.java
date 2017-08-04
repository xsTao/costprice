/**
 *
 */
package nc.ui.mapub.costpricebase.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.costpricebase.ICostPriceBasePaginationService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2017年8月2日 下午6:55:51
 * @author Administrator
 */
public class CostPriceBasePaginationProxy implements IPaginationQueryService {

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.components.pagination.IPaginationQueryService#queryObjectByPks(java.lang.String[])
     */
    @Override
    public Object[] queryObjectByPks(String[] pks) throws BusinessException {
        // TODO Auto-generated method stub
        CostPriceAggVO[] rets = null;
        ICostPriceBasePaginationService paginationQueryService =
                NCLocator.getInstance().lookup(ICostPriceBasePaginationService.class);
        try {
            rets = paginationQueryService.queryBillByPK(pks);
        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
        }
        return rets;
    }

}
