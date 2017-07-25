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
            // 加锁 比较ts
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
            // 数据库中数据和前台传递过来的差异VO合并后的结果
            BillTransferTool<CoprodcoefAggVO> transferTool = new BillTransferTool<CoprodcoefAggVO>(vos);
            CoprodcoefAggVO[] mergedVO = transferTool.getClientFullInfoBill();
            // 调用BP
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

            // 构造返回数据
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
            // 加锁 + 检查ts
            BillTransferTool<CoprodcoefAggVO> transTool = new BillTransferTool<CoprodcoefAggVO>(vos);
            // 补全前台VO
            CoprodcoefAggVO[] fullBills = transTool.getClientFullInfoBill();
            // 获得修改前vo
            CoprodcoefAggVO[] originBills = transTool.getOriginBills();
            // 调用BP
            CoprodcoefUpdateBP bp = new CoprodcoefUpdateBP();
            CoprodcoefAggVO[] retBills = bp.update(fullBills, originBills);
            // 构造返回数据
            retBills = transTool.getBillForToClient(retBills);
            return retBills;
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }
        return null;
    }

}
