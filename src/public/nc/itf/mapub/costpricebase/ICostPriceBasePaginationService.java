/**
 *
 */
package nc.itf.mapub.costpricebase;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2017年8月2日 下午7:01:58
 * @author Administrator
 */
public interface ICostPriceBasePaginationService {

    public String[] queryPKs(IQueryScheme queryScheme) throws BusinessException;

    public CostPriceAggVO[] queryBillByPK(String[] pks) throws BusinessException;

}
