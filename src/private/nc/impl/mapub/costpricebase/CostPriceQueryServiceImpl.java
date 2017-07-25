/**
 *
 */
package nc.impl.mapub.costpricebase;

import nc.cmpub.business.query.CMBillQuery;
import nc.cmpub.business.query.QueryTempletParam;
import nc.itf.mapub.costpricebase.ICostPriceBaseQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.costpricebase.entity.CMMCommonConstCostPriceBase;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2017年7月20日 下午8:36:50
 * @author Administrator
 */
public class CostPriceQueryServiceImpl implements ICostPriceBaseQueryService {

    /*
     * (non-Javadoc)
     * @see
     * nc.itf.mapub.costpricebase.ICostPriceBaseQueryService#queryByQueryScheme(nc.ui.querytemplate.querytree.IQueryScheme
     * )
     */
    @Override
    public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws BusinessException {
        // TODO Auto-generated method stub
        try {
            QueryTempletParam param = new QueryTempletParam();
            param.setPk_org(CMMCommonConstCostPriceBase.PK_ORG);
            param.setCperiod(CMMCommonConstCostPriceBase.VPERIOD);
            param.setCproductid(CMMCommonConstCostPriceBase.VPRODUCTCODE);
            CMBillQuery<CostPriceAggVO> qry = new CMBillQuery<CostPriceAggVO>(CostPriceAggVO.class, param);

            return qry.queryByQueryScheme(queryScheme);
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }
        return null;
    }

}
