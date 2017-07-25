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
    // 删除
    @Override
    public void deleteDriver(DriverAggVO[] vos) throws BusinessException {
        try {
            // 加锁 比较ts
            BillTransferTool<DriverAggVO> transferTool = new BillTransferTool<DriverAggVO>(vos);
            DriverAggVO[] fullBills = transferTool.getClientFullInfoBill();
            DriverDeleteBP deleteBP = new DriverDeleteBP();
            deleteBP.delete(fullBills);

            List<String> pkList = new ArrayList<String>();
            for (DriverAggVO vo : vos) {
                pkList.add(((DriverVO) vo.getParent()).getCdriverid());
            }
            // 通知缓存
            CacheProxy.fireDataDeletedBatch("cm_driver", pkList.toArray(new String[0]));
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }
    }

    // 新增
    @Override
    public DriverAggVO[] insertDriver(DriverAggVO[] vos) throws BusinessException {
        try {
            for (DriverAggVO vo : vos) {
                DriverVO driverVO = (DriverVO) vo.getParent();
                // 加锁
                boolean isLockable =
                        nc.bs.uap.lock.PKLock.getInstance().addDynamicLock(
                                "diver" + driverVO.getPk_org() + driverVO.getVcode());
                if (!isLockable) {
                    ExceptionUtils.wrappBusinessException(CMDriverLangConst.getERRO_DRIVERCODE_EXIT());
                }
            }

            // 数据库中数据和前台传递过来的差异VO合并后的结果
            BillTransferTool<DriverAggVO> transferTool = new BillTransferTool<DriverAggVO>(vos);
            DriverAggVO[] mergedVO = transferTool.getClientFullInfoBill();

            // 调用BP
            DriverInsertBP action = new DriverInsertBP();
            DriverAggVO[] retvos = action.insert(mergedVO);
            // 通知缓存
            CacheProxy.fireDataInserted("cm_driver");
            // 构造返回数据
            return transferTool.getBillForToClient(retvos);
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }

        return null;
    }

    // 修改
    @Override
    public DriverAggVO[] updateDriver(DriverAggVO[] vos) throws BusinessException {
        try {
            for (DriverAggVO vo : vos) {
                DriverVO driverVO = (DriverVO) vo.getParent();
                // 加锁
                boolean isLockable =
                        nc.bs.uap.lock.PKLock.getInstance().addDynamicLock(
                                "diver" + driverVO.getPk_org() + driverVO.getVcode());
                if (!isLockable) {
                    ExceptionUtils.wrappBusinessException(CMDriverLangConst.getERRO_DRIVERCODE_EXIT());
                }
            }
            // 加锁 + 检查ts
            BillTransferTool<DriverAggVO> transTool = new BillTransferTool<DriverAggVO>(vos);
            // 补全前台VO
            DriverAggVO[] fullBills = transTool.getClientFullInfoBill();
            // 获得修改前vo
            DriverAggVO[] originBills = transTool.getOriginBills();
            // 调用BP
            DriverUpdateBP bp = new DriverUpdateBP();
            DriverAggVO[] retBills = bp.update(fullBills, originBills);
            // 构造返回数据
            retBills = transTool.getBillForToClient(retBills);
            // 通知缓存
            CacheProxy.fireDataUpdated("cm_driver");
            return retBills;

        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }
        return null;
    }
}
