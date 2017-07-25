package nc.impl.mapub.coprodcoef;

import nc.bs.mapub.coprodcoef.bp.CoprodcoefDeleteBP;
import nc.bs.mapub.coprodcoef.bp.CoprodcoefInsertBP;
import nc.bs.mapub.coprodcoef.bp.CoprodcoefUpdateBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.mapub.coprodcoef.ICoprodcoefMaintain;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class CoprodcoefMaintainImpl implements ICoprodcoefMaintain {

    @Override
    public void delete(CoprodcoefAggVO[] vos) throws BusinessException {
        try {
            // ���� �Ƚ�ts
            BillTransferTool<CoprodcoefAggVO> transferTool = new BillTransferTool<CoprodcoefAggVO>(vos);
            CoprodcoefAggVO[] fullBills = transferTool.getClientFullInfoBill();
            CoprodcoefDeleteBP deleteBP = new CoprodcoefDeleteBP();
            deleteBP.delete(fullBills);
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }
    }

    @Override
    public CoprodcoefAggVO[] insert(CoprodcoefAggVO[] vos) throws BusinessException {
        try {
            // ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
            BillTransferTool<CoprodcoefAggVO> transferTool = new BillTransferTool<CoprodcoefAggVO>(vos);
            CoprodcoefAggVO[] mergedVO = transferTool.getClientFullInfoBill();
            // ����BP
            CoprodcoefInsertBP action = new CoprodcoefInsertBP();
            for (CoprodcoefAggVO aggvo : mergedVO) {
                boolean isLockable =
                        nc.bs.uap.lock.PKLock.getInstance().addDynamicLock(
                                aggvo.getParentVO().getPk_org() + aggvo.getParentVO().getCmaterialid()
                                        + aggvo.getParentVO().getPk_factorchart());
                if (!isLockable) {
                    throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                            "03810006-0296"));
                }
            }
            CoprodcoefAggVO[] retvos = action.insert(mergedVO);

            // ���췵������
            return transferTool.getBillForToClient(retvos);
            // return retvos;
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }
        return null;
    }

    @Override
    public CoprodcoefAggVO[] update(CoprodcoefAggVO[] vos) throws BusinessException {
        try {
            // ���� + ���ts
            BillTransferTool<CoprodcoefAggVO> transTool = new BillTransferTool<CoprodcoefAggVO>(vos);
            // ��ȫǰ̨VO
            CoprodcoefAggVO[] fullBills = transTool.getClientFullInfoBill();
            // ����޸�ǰvo
            CoprodcoefAggVO[] originBills = transTool.getOriginBills();
            // ����BP
            CoprodcoefUpdateBP bp = new CoprodcoefUpdateBP();
            CoprodcoefAggVO[] retBills = bp.update(fullBills, originBills);
            // ���췵������
            retBills = transTool.getBillForToClient(retBills);
            return retBills;
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }
        return null;
    }

}
