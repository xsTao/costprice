package nc.impl.mapub.materialpricebase;

import java.util.List;
import java.util.Map;

import nc.bd.framework.base.CMValueCheck;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.mapub.materialpricebase.bp.MaterialPriceBaseDeblockingBP;
import nc.bs.mapub.materialpricebase.bp.MaterialPriceBaseDeleteBP;
import nc.bs.mapub.materialpricebase.bp.MaterialPriceBaseImportInsertBP;
import nc.bs.mapub.materialpricebase.bp.MaterialPriceBaseInsertBP;
import nc.bs.mapub.materialpricebase.bp.MaterialPriceBaseLockBP;
import nc.bs.mapub.materialpricebase.bp.MaterialPriceBaseTakePriceBP;
import nc.bs.mapub.materialpricebase.bp.MaterialPriceBaseUpdateBP;
import nc.bs.mapub.materialpricebase.util.MaterialPullPriceParamUtil;
import nc.impl.pubapp.pattern.data.bill.EfficientBillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.mapub.materialpricebase.IMaterialPriceBaseMaintainService;
import nc.mapub.framework.acquireprice.pub.AcquirePriceFacade;
import nc.vo.cmpub.framework.assistant.CMAssInfoParamVO;
import nc.vo.mapub.framework.entity.acquireprice.AcquirePriceParam;
import nc.vo.mapub.framework.entity.acquireprice.AcquirePriceReturn;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceResult;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;

/**
 * ���ϼ۸��
 *
 * @since 6.36
 * @version 2014-11-7 ����4:31:56
 * @author zhangchd
 */
public class MaterialPriceBaseMaintainServiceImpl implements IMaterialPriceBaseMaintainService {
    @Override
    public void delete(MaterialPriceBaseAggVO[] vos) throws BusinessException {

        // ���� �Ƚ�ts
        BillTransferTool<MaterialPriceBaseAggVO> transferTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        MaterialPriceBaseAggVO[] fullBills = transferTool.getClientFullInfoBill();
        MaterialPriceBaseDeleteBP deleteBP = new MaterialPriceBaseDeleteBP();
        deleteBP.delete(fullBills);
    }

    @Override
    public MaterialPriceBaseAggVO[] insert(MaterialPriceBaseAggVO[] vos) throws BusinessException {

        // ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
        BillTransferTool<MaterialPriceBaseAggVO> transferTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        MaterialPriceBaseAggVO[] mergedVO = transferTool.getClientFullInfoBill();
        // ����BP
        MaterialPriceBaseInsertBP action = new MaterialPriceBaseInsertBP();
        MaterialPriceBaseAggVO[] retvos = action.insert(mergedVO);
        // ���췵������
        return transferTool.getBillForToClient(retvos);
    }

    @Override
    public MaterialPriceBaseAggVO[] importInsert(MaterialPriceBaseAggVO[] vos) throws BusinessException {
        // ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
        BillTransferTool<MaterialPriceBaseAggVO> transferTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        MaterialPriceBaseAggVO[] mergedVO = transferTool.getClientFullInfoBill();
        // ����BP
        MaterialPriceBaseImportInsertBP action = new MaterialPriceBaseImportInsertBP();
        MaterialPriceBaseAggVO[] retvos = action.insert(mergedVO);
        // ���췵������
        return transferTool.getBillForToClient(retvos);
    }

    @Override
    public MaterialPriceBaseAggVO[] update(MaterialPriceBaseAggVO[] vos) throws BusinessException {

        // ���� + ���ts
        BillTransferTool<MaterialPriceBaseAggVO> transTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        // ��ȫǰ̨VO
        MaterialPriceBaseAggVO[] fullBills = transTool.getClientFullInfoBill();
        // ����޸�ǰvo
        MaterialPriceBaseAggVO[] originBills = transTool.getOriginBills();
        // ����BP
        MaterialPriceBaseUpdateBP bp = new MaterialPriceBaseUpdateBP();
        MaterialPriceBaseAggVO[] retBills = bp.update(fullBills, originBills);
        // ���췵������
        retBills = transTool.getBillForToClient(retBills);
        return retBills;
    }

    // ����
    @Override
    public MaterialPriceBaseAggVO[] lock(MaterialPriceBaseAggVO[] vos) throws BusinessException {

        // ���� + ���ts
        BillTransferTool<MaterialPriceBaseAggVO> transTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        // ��ȫǰ̨VO
        MaterialPriceBaseAggVO[] fullBills = transTool.getClientFullInfoBill();
        // ����޸�ǰvo
        MaterialPriceBaseAggVO[] originBills = transTool.getOriginBills();
        // ����BP
        MaterialPriceBaseLockBP bp = new MaterialPriceBaseLockBP();
        MaterialPriceBaseAggVO[] retBills = bp.lock(fullBills, originBills);
        // ���췵������
        retBills = transTool.getBillForToClient(retBills);
        return retBills;
    }

    // �ⶳ
    @Override
    public MaterialPriceBaseAggVO[] deblocking(MaterialPriceBaseAggVO[] vos) throws BusinessException {
        // ���� + ���ts
        BillTransferTool<MaterialPriceBaseAggVO> transTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        // ��ȫǰ̨VO
        MaterialPriceBaseAggVO[] fullBills = transTool.getClientFullInfoBill();
        // ����޸�ǰvo
        MaterialPriceBaseAggVO[] originBills = transTool.getOriginBills();
        // ����BP
        MaterialPriceBaseDeblockingBP bp = new MaterialPriceBaseDeblockingBP();
        MaterialPriceBaseAggVO[] retBills = bp.deblocking(fullBills, originBills);
        // ���췵������
        retBills = transTool.getBillForToClient(retBills);
        return retBills;
    }

    // ȡ��
    // modify by zhangchdV636
    @Override
    public MaterialPriceBaseAggVO[] takePrice(MaterialPriceBaseAggVO[] vos) throws BusinessException {
        // ���� + ���ts
        BillTransferTool<MaterialPriceBaseAggVO> transTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        // ��ȫǰ̨VO
        MaterialPriceBaseAggVO[] fullBills = transTool.getClientFullInfoBill();
        // ����޸�ǰvo
        MaterialPriceBaseAggVO[] originBills = transTool.getOriginBills();
        // ����BP
        MaterialPriceBaseTakePriceBP bp = new MaterialPriceBaseTakePriceBP();
        MaterialPriceBaseAggVO[] retBills = bp.takePrice(fullBills, originBills);
        // ���췵������
        retBills = transTool.getBillForToClient(retBills);
        return retBills;
    }

    /**
     * ȡ�۷���
     *
     * @author zhangchd
     * @param priceParamVO ȡ�۶Ի������vo
     * @return ����vo
     * @throws BusinessException
     */
    @Override
    public MaterialPullPriceResult pullPrice(MaterialPullPriceAggVO priceParamVO, String[] primaryKey)
            throws BusinessException {
        MaterialPriceBaseAggVO[] result = null;
        MaterialPullPriceResult materialPullPriceResult = null;
        MaterialPriceBaseAggVO[] materialPriceBaseAggVO = null;

        if (CMValueCheck.isNotEmpty(primaryKey)) {
            List<AcquirePriceParam> matAssPriceSource;

            materialPriceBaseAggVO = this.getMaterialPriceBaseAggVO(primaryKey);
            MaterialPriceBaseHeadVO materialPriceBaseHeadVO = null;
            MaterialPriceBaseBodyVO[] materialPriceSourcesBodyVO = null;
            if (CMValueCheck.isNotEmpty(materialPriceBaseAggVO)) {
                materialPriceBaseHeadVO = materialPriceBaseAggVO[0].getParentVO();
                materialPriceSourcesBodyVO = (MaterialPriceBaseBodyVO[]) materialPriceBaseAggVO[0].getAllChildrenVO();
            }

            if (CMValueCheck.isNotEmpty(materialPriceSourcesBodyVO) && CMValueCheck.isNotEmpty(materialPriceBaseHeadVO)) {
                MaterialPullPriceParamUtil materialPullPriceParamUtil =
                        new MaterialPullPriceParamUtil(materialPriceBaseAggVO, materialPriceBaseHeadVO,
                                materialPriceSourcesBodyVO);
                // 1����ȡȡ�۲���
                matAssPriceSource = materialPullPriceParamUtil.getMaterialPullPriceParam(priceParamVO);

                // 2�� ���ݼ۸���Դ����˳��ȡ��
                AcquirePriceFacade priceFacade = new AcquirePriceFacade();
                Map<CMAssInfoParamVO, AcquirePriceReturn> acquirePriceCurrentResult =
                        priceFacade.getMaterialPrice(matAssPriceSource);

                // 3�����ظ��º��AggVO
                MaterialPriceBaseAggVO[] updatematerialPriceBaseAggVO =
                        materialPullPriceParamUtil.getUpdatematerialPriceBaseAggVO(acquirePriceCurrentResult);

                // 4���������ݿ�����
                result = this.update(updatematerialPriceBaseAggVO);
                // clientVOsΪ�����ϵ����ݣ�resultΪ��̨���صĲ�������
                new ClientBillCombinServer<IBill>().combine(updatematerialPriceBaseAggVO, result);

                // 5�����ؽ��
                materialPullPriceResult =
                        materialPullPriceParamUtil.getMaterialPullPriceResult(updatematerialPriceBaseAggVO);

                // ɾ����־
                materialPullPriceParamUtil.deleteErrorLog();
            }
        }
        return materialPullPriceResult;
    }

    /**
     * ����������ȡAggvo
     *
     * @param primaryKey
     * @return MaterialPriceBaseAggVO
     */
    private MaterialPriceBaseAggVO[] getMaterialPriceBaseAggVO(String[] primaryKey) {
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append(" from ");
        sql.append(" MAPUB_MATERIALPRICEBASE ");
        sql.append(" where dr=0 and ");
        sql.append("CMATERIALPRICEID", primaryKey);

        EfficientBillQuery<MaterialPriceBaseAggVO> query =
                new EfficientBillQuery<MaterialPriceBaseAggVO>(MaterialPriceBaseAggVO.class);
        return query.query(sql.toString());
    }

}
