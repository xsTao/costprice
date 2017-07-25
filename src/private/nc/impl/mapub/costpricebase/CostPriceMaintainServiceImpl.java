/**
 *
 */
package nc.impl.mapub.costpricebase;

import nc.bs.mapub.costpricebase.bp.CostPriceBaseDeleteBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.mapub.costpricebase.ICostPriceBaseMaintainService;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2017年7月20日 上午9:26:19
 * @author Administrator
 */
public class CostPriceMaintainServiceImpl implements ICostPriceBaseMaintainService {

    /*
     * (non-Javadoc)
     * @see
     * nc.itf.mapub.costpricebase.ICostPriceBaseMaintainService#delete(nc.vo.mapub.costpricebase.entity.CostPriceAggVO
     * [])
     */
    @Override
    public void delete(CostPriceAggVO[] vos) throws BusinessException {
        // TODO Auto-generated method stub
        BillTransferTool<CostPriceAggVO> billTransferTool = new BillTransferTool<CostPriceAggVO>(vos);
        CostPriceAggVO[] fullBills = billTransferTool.getClientFullInfoBill();
        CostPriceBaseDeleteBP deleteBP = new CostPriceBaseDeleteBP();
        deleteBP.delete(fullBills);
    }

    /*
     * (non-Javadoc)
     * @see
     * nc.itf.mapub.costpricebase.ICostPriceBaseMaintainService#insert(nc.vo.mapub.costpricebase.entity.CostPriceAggVO
     * [])
     */
    @Override
    public CostPriceAggVO[] insert(CostPriceAggVO[] vos) throws BusinessException {
        // TODO Auto-generated method stub

        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * nc.itf.mapub.costpricebase.ICostPriceBaseMaintainService#update(nc.vo.mapub.costpricebase.entity.CostPriceAggVO
     * [])
     */
    @Override
    public CostPriceAggVO[] update(CostPriceAggVO[] vos) throws BusinessException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.itf.mapub.costpricebase.ICostPriceBaseMaintainService#query(nc.ui.querytemplate.querytree.IQueryScheme)
     */
    /*
     * @Override
     * public CostPriceAggVO[] query(IQueryScheme queryScheme) throws BusinessException {
     * // TODO Auto-generated method stub
     * return null;
     * }
     */

}
