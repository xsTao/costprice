/**
 *
 */
package nc.impl.mapub.costpricebase;

import nc.bs.mapub.costpricebase.bp.CostPriceBaseDeleteBP;
import nc.bs.mapub.costpricebase.bp.CostPriceBaseInsertBP;
import nc.bs.mapub.costpricebase.bp.CostPriceBaseUpdateBP;
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
        // 前台界面和后台交互时的单据信息VO的传递处理
        BillTransferTool<CostPriceAggVO> billTransferTool = new BillTransferTool<CostPriceAggVO>(vos);
        CostPriceAggVO[] mergedVO = billTransferTool.getClientFullInfoBill();

        // 调用BP
        CostPriceBaseInsertBP insertBP = new CostPriceBaseInsertBP();
        CostPriceAggVO[] insertVO = insertBP.insert(mergedVO);
        // 构造返回数据
        return billTransferTool.getBillForToClient(insertVO);
    }

    /*
     * (non-Javadoc)
     * @see
     * nc.itf.mapub.costpricebase.ICostPriceBaseMaintainService#update(nc.vo.mapub.costpricebase.entity.CostPriceAggVO
     * [])
     */
    @Override
    public CostPriceAggVO[] update(CostPriceAggVO[] vos) throws BusinessException {
        //
        BillTransferTool<CostPriceAggVO> transferTool = new BillTransferTool<CostPriceAggVO>(vos);
        // 补全前台VO
        CostPriceAggVO[] fullBills = transferTool.getClientFullInfoBill();
        // 获取初始VO
        CostPriceAggVO[] originBills = transferTool.getOriginBills();
        CostPriceBaseUpdateBP updateBP = new CostPriceBaseUpdateBP();
        CostPriceAggVO[] retvos = updateBP.update(fullBills, originBills); // 进行更新操作
        // 返回修改后数据给前台
        return transferTool.getBillForToClient(retvos);
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
