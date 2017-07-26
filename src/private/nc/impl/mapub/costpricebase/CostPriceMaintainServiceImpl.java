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
 * @version 2017��7��20�� ����9:26:19
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
        // ǰ̨����ͺ�̨����ʱ�ĵ�����ϢVO�Ĵ��ݴ���
        BillTransferTool<CostPriceAggVO> billTransferTool = new BillTransferTool<CostPriceAggVO>(vos);
        CostPriceAggVO[] mergedVO = billTransferTool.getClientFullInfoBill();

        // ����BP
        CostPriceBaseInsertBP insertBP = new CostPriceBaseInsertBP();
        CostPriceAggVO[] insertVO = insertBP.insert(mergedVO);
        // ���췵������
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
        // ��ȫǰ̨VO
        CostPriceAggVO[] fullBills = transferTool.getClientFullInfoBill();
        // ��ȡ��ʼVO
        CostPriceAggVO[] originBills = transferTool.getOriginBills();
        CostPriceBaseUpdateBP updateBP = new CostPriceBaseUpdateBP();
        CostPriceAggVO[] retvos = updateBP.update(fullBills, originBills); // ���и��²���
        // �����޸ĺ����ݸ�ǰ̨
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
