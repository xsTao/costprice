package nc.impl.mapub.driver;

import java.util.ArrayList;
import java.util.List;

import nc.bs.bd.cache.CacheProxy;
import nc.bs.mapub.driver.bp.DriverDeleteBP;
import nc.bs.mapub.driver.bp.DriverInsertBP;
import nc.bs.mapub.driver.bp.DriverUpdateBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.mapub.driver.IDriverMaintainService;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class DriverMaintainServiceImpl implements IDriverMaintainService {
    // ɾ��
    @Override
    public void deleteDriver(DriverAggVO[] vos) throws BusinessException {
        try {
            // ���� �Ƚ�ts
            BillTransferTool<DriverAggVO> transferTool = new BillTransferTool<DriverAggVO>(vos);
            DriverAggVO[] fullBills = transferTool.getClientFullInfoBill();
            DriverDeleteBP deleteBP = new DriverDeleteBP();
            deleteBP.delete(fullBills);

            List<String> pkList = new ArrayList<String>();
            for (DriverAggVO vo : vos) {
                pkList.add(((DriverVO) vo.getParent()).getCdriverid());
            }
            // ֪ͨ����
            CacheProxy.fireDataDeletedBatch("cm_driver", pkList.toArray(new String[0]));
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }
    }

    // ����
    @Override
    public DriverAggVO[] insertDriver(DriverAggVO[] vos) throws BusinessException {
        try {
            for (DriverAggVO vo : vos) {
                DriverVO driverVO = (DriverVO) vo.getParent();
                // ����
                boolean isLockable =
                        nc.bs.uap.lock.PKLock.getInstance().addDynamicLock(
                                "diver" + driverVO.getPk_org() + driverVO.getVcode());
                if (!isLockable) {
                    ExceptionUtils.wrappBusinessException(CMDriverLangConst.getERRO_DRIVERCODE_EXIT());
                }
            }

            // ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
            BillTransferTool<DriverAggVO> transferTool = new BillTransferTool<DriverAggVO>(vos);
            DriverAggVO[] mergedVO = transferTool.getClientFullInfoBill();

            // ����BP
            DriverInsertBP action = new DriverInsertBP();
            DriverAggVO[] retvos = action.insert(mergedVO);
            // ֪ͨ����
            CacheProxy.fireDataInserted("cm_driver");
            // ���췵������
            return transferTool.getBillForToClient(retvos);
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }

        return null;
    }

    // �޸�
    @Override
    public DriverAggVO[] updateDriver(DriverAggVO[] vos) throws BusinessException {
        try {
            for (DriverAggVO vo : vos) {
                DriverVO driverVO = (DriverVO) vo.getParent();
                // ����
                boolean isLockable =
                        nc.bs.uap.lock.PKLock.getInstance().addDynamicLock(
                                "diver" + driverVO.getPk_org() + driverVO.getVcode());
                if (!isLockable) {
                    ExceptionUtils.wrappBusinessException(CMDriverLangConst.getERRO_DRIVERCODE_EXIT());
                }
            }
            // ���� + ���ts
            BillTransferTool<DriverAggVO> transTool = new BillTransferTool<DriverAggVO>(vos);
            // ��ȫǰ̨VO
            DriverAggVO[] fullBills = transTool.getClientFullInfoBill();
            // ����޸�ǰvo
            DriverAggVO[] originBills = transTool.getOriginBills();
            // ����BP
            DriverUpdateBP bp = new DriverUpdateBP();
            DriverAggVO[] retBills = bp.update(fullBills, originBills);
            // ���췵������
            retBills = transTool.getBillForToClient(retBills);
            // ֪ͨ����
            CacheProxy.fireDataUpdated("cm_driver");
            return retBills;

        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }
        return null;
    }
}
