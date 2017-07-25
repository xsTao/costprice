package nc.ui.mapub.materialpricebase.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.materialpricebase.IMaterialPriceBaseMaintainService;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * 材料价格库
 *
 * @since 6.36
 * @version 2014-11-7 下午4:27:54
 * @author zhangchd
 */
public class MaterialPriceBaseMaintainProxy implements IDataOperationService {
    private IMaterialPriceBaseMaintainService materialPriceBaseMaintain;

    @Override
    public IBill[] insert(IBill[] bills) throws BusinessException {
        MaterialPriceBaseAggVO[] vos =
                this.getMaterialPriceBaseMaintainService().insert((MaterialPriceBaseAggVO[]) bills);
        return vos;
    }

    @Override
    public IBill[] update(IBill[] bills) throws BusinessException {
        MaterialPriceBaseAggVO[] vos =
                this.getMaterialPriceBaseMaintainService().update((MaterialPriceBaseAggVO[]) bills);
        return vos;
    }

    @Override
    public IBill[] delete(IBill[] bills) throws BusinessException {
        // 目前的删除并不是走这个方法，由于pubapp不支持从这个服务中执行删除操作
        // 单据的删除实际上使用的是：nc.ui.mmpd.samplebill.serviceproxy.SampleDeleteProxy
        return null;
    }

    // 锁定
    public IBill[] lock(IBill[] bills) throws BusinessException {
        MaterialPriceBaseAggVO[] aggvos = new MaterialPriceBaseAggVO[bills.length];

        for (int i = 0; i < bills.length; i++) {
            aggvos[i] = (MaterialPriceBaseAggVO) bills[i];
        }
        MaterialPriceBaseAggVO[] vos = this.getMaterialPriceBaseMaintainService().lock(aggvos);

        return vos;
    }

    // 解冻
    public IBill[] deblocking(IBill[] bills) throws BusinessException {
        MaterialPriceBaseAggVO[] aggvos = new MaterialPriceBaseAggVO[bills.length];

        for (int i = 0; i < bills.length; i++) {
            aggvos[i] = (MaterialPriceBaseAggVO) bills[i];
        }
        MaterialPriceBaseAggVO[] vos = this.getMaterialPriceBaseMaintainService().deblocking(aggvos);

        return vos;
    }

    // 取价
    public IBill[] takePrice(IBill[] bills) throws BusinessException {
        MaterialPriceBaseAggVO[] aggvos = new MaterialPriceBaseAggVO[bills.length];

        for (int i = 0; i < bills.length; i++) {
            aggvos[i] = (MaterialPriceBaseAggVO) bills[i];
        }
        MaterialPriceBaseAggVO[] vos = this.getMaterialPriceBaseMaintainService().takePrice(aggvos);
        return vos;
    }

    /**
     * 获取价格库的服务
     *
     * @return IMaterialPriceBaseMaintainService
     */
    private IMaterialPriceBaseMaintainService getMaterialPriceBaseMaintainService() {
        if (this.materialPriceBaseMaintain == null) {
            this.materialPriceBaseMaintain = NCLocator.getInstance().lookup(IMaterialPriceBaseMaintainService.class);
        }
        return this.materialPriceBaseMaintain;
    }
}
