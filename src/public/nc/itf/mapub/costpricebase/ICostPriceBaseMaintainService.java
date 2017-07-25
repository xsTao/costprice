/**
 *
 */
package nc.itf.mapub.costpricebase;

import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2017年7月19日 下午1:43:44
 * @author Administrator
 */
public interface ICostPriceBaseMaintainService {
    public void delete(CostPriceAggVO[] vos) throws BusinessException;

    public CostPriceAggVO[] insert(CostPriceAggVO[] vos) throws BusinessException;

    public CostPriceAggVO[] update(CostPriceAggVO[] vos) throws BusinessException;

    /* public CostPriceAggVO[] query(IQueryScheme queryScheme) throws BusinessException; */
}
