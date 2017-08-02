/**
 *
 */
package nc.impl.mapub.costpricebase;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.mapub.costpricebase.ICostPriceBasePaginationService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.bill.pagination.util.PaginationUtils;

/**
 * @since v6.3
 * @version 2017年8月2日 下午7:11:46
 * @author Administrator
 */
public class CostPricePaginationServiceImpl implements ICostPriceBasePaginationService {

    /*
     * (non-Javadoc)
     * @see
     * nc.itf.mapub.costpricebase.ICostPriceBasePaginationService#queryPKs(nc.ui.querytemplate.querytree.IQueryScheme)
     */
    @Override
    public String[] queryPKs(IQueryScheme queryScheme) throws BusinessException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.itf.mapub.costpricebase.ICostPriceBasePaginationService#queryBillByPK(java.lang.String[])
     */
    @Override
    public CostPriceAggVO[] queryBillByPK(String[] pks) throws BusinessException {
        // TODO Auto-generated method stub
        CostPriceAggVO[] bills = null;
        BillQuery<CostPriceAggVO> billQuery = new BillQuery<CostPriceAggVO>(CostPriceAggVO.class);
        bills = billQuery.query(pks);
        return PaginationUtils.filterNotExistBills(bills, pks);
    }

}
