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
 * 材料价格库
 *
 * @since 6.36
 * @version 2014-11-7 下午4:31:56
 * @author zhangchd
 */
public class MaterialPriceBaseMaintainServiceImpl implements IMaterialPriceBaseMaintainService {
    @Override
    public void delete(MaterialPriceBaseAggVO[] vos) throws BusinessException {

        // 加锁 比较ts
        BillTransferTool<MaterialPriceBaseAggVO> transferTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        MaterialPriceBaseAggVO[] fullBills = transferTool.getClientFullInfoBill();
        MaterialPriceBaseDeleteBP deleteBP = new MaterialPriceBaseDeleteBP();
        deleteBP.delete(fullBills);
    }

    @Override
    public MaterialPriceBaseAggVO[] insert(MaterialPriceBaseAggVO[] vos) throws BusinessException {

        // 数据库中数据和前台传递过来的差异VO合并后的结果
        BillTransferTool<MaterialPriceBaseAggVO> transferTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        MaterialPriceBaseAggVO[] mergedVO = transferTool.getClientFullInfoBill();
        // 调用BP
        MaterialPriceBaseInsertBP action = new MaterialPriceBaseInsertBP();
        MaterialPriceBaseAggVO[] retvos = action.insert(mergedVO);
        // 构造返回数据
        return transferTool.getBillForToClient(retvos);
    }

    @Override
    public MaterialPriceBaseAggVO[] importInsert(MaterialPriceBaseAggVO[] vos) throws BusinessException {
        // 数据库中数据和前台传递过来的差异VO合并后的结果
        BillTransferTool<MaterialPriceBaseAggVO> transferTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        MaterialPriceBaseAggVO[] mergedVO = transferTool.getClientFullInfoBill();
        // 调用BP
        MaterialPriceBaseImportInsertBP action = new MaterialPriceBaseImportInsertBP();
        MaterialPriceBaseAggVO[] retvos = action.insert(mergedVO);
        // 构造返回数据
        return transferTool.getBillForToClient(retvos);
    }

    @Override
    public MaterialPriceBaseAggVO[] update(MaterialPriceBaseAggVO[] vos) throws BusinessException {

        // 加锁 + 检查ts
        BillTransferTool<MaterialPriceBaseAggVO> transTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        // 补全前台VO
        MaterialPriceBaseAggVO[] fullBills = transTool.getClientFullInfoBill();
        // 获得修改前vo
        MaterialPriceBaseAggVO[] originBills = transTool.getOriginBills();
        // 调用BP
        MaterialPriceBaseUpdateBP bp = new MaterialPriceBaseUpdateBP();
        MaterialPriceBaseAggVO[] retBills = bp.update(fullBills, originBills);
        // 构造返回数据
        retBills = transTool.getBillForToClient(retBills);
        return retBills;
    }

    // 锁定
    @Override
    public MaterialPriceBaseAggVO[] lock(MaterialPriceBaseAggVO[] vos) throws BusinessException {

        // 加锁 + 检查ts
        BillTransferTool<MaterialPriceBaseAggVO> transTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        // 补全前台VO
        MaterialPriceBaseAggVO[] fullBills = transTool.getClientFullInfoBill();
        // 获得修改前vo
        MaterialPriceBaseAggVO[] originBills = transTool.getOriginBills();
        // 调用BP
        MaterialPriceBaseLockBP bp = new MaterialPriceBaseLockBP();
        MaterialPriceBaseAggVO[] retBills = bp.lock(fullBills, originBills);
        // 构造返回数据
        retBills = transTool.getBillForToClient(retBills);
        return retBills;
    }

    // 解冻
    @Override
    public MaterialPriceBaseAggVO[] deblocking(MaterialPriceBaseAggVO[] vos) throws BusinessException {
        // 加锁 + 检查ts
        BillTransferTool<MaterialPriceBaseAggVO> transTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        // 补全前台VO
        MaterialPriceBaseAggVO[] fullBills = transTool.getClientFullInfoBill();
        // 获得修改前vo
        MaterialPriceBaseAggVO[] originBills = transTool.getOriginBills();
        // 调用BP
        MaterialPriceBaseDeblockingBP bp = new MaterialPriceBaseDeblockingBP();
        MaterialPriceBaseAggVO[] retBills = bp.deblocking(fullBills, originBills);
        // 构造返回数据
        retBills = transTool.getBillForToClient(retBills);
        return retBills;
    }

    // 取价
    // modify by zhangchdV636
    @Override
    public MaterialPriceBaseAggVO[] takePrice(MaterialPriceBaseAggVO[] vos) throws BusinessException {
        // 加锁 + 检查ts
        BillTransferTool<MaterialPriceBaseAggVO> transTool = new BillTransferTool<MaterialPriceBaseAggVO>(vos);
        // 补全前台VO
        MaterialPriceBaseAggVO[] fullBills = transTool.getClientFullInfoBill();
        // 获得修改前vo
        MaterialPriceBaseAggVO[] originBills = transTool.getOriginBills();
        // 调用BP
        MaterialPriceBaseTakePriceBP bp = new MaterialPriceBaseTakePriceBP();
        MaterialPriceBaseAggVO[] retBills = bp.takePrice(fullBills, originBills);
        // 构造返回数据
        retBills = transTool.getBillForToClient(retBills);
        return retBills;
    }

    /**
     * 取价服务
     *
     * @author zhangchd
     * @param priceParamVO 取价对话框参数vo
     * @return 界面vo
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
                // 1、获取取价参数
                matAssPriceSource = materialPullPriceParamUtil.getMaterialPullPriceParam(priceParamVO);

                // 2、 根据价格来源优先顺序取价
                AcquirePriceFacade priceFacade = new AcquirePriceFacade();
                Map<CMAssInfoParamVO, AcquirePriceReturn> acquirePriceCurrentResult =
                        priceFacade.getMaterialPrice(matAssPriceSource);

                // 3、返回更新后的AggVO
                MaterialPriceBaseAggVO[] updatematerialPriceBaseAggVO =
                        materialPullPriceParamUtil.getUpdatematerialPriceBaseAggVO(acquirePriceCurrentResult);

                // 4、更新数据库数据
                result = this.update(updatematerialPriceBaseAggVO);
                // clientVOs为界面上的数据，result为后台返回的差异数据
                new ClientBillCombinServer<IBill>().combine(updatematerialPriceBaseAggVO, result);

                // 5、返回结果
                materialPullPriceResult =
                        materialPullPriceParamUtil.getMaterialPullPriceResult(updatematerialPriceBaseAggVO);

                // 删除日志
                materialPullPriceParamUtil.deleteErrorLog();
            }
        }
        return materialPullPriceResult;
    }

    /**
     * 根据主键获取Aggvo
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
