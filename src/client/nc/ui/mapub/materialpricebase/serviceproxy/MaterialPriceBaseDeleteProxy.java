package nc.ui.mapub.materialpricebase.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.materialpricebase.IMaterialPriceBaseMaintainService;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;

/**
 * 材料价格库
 * 
 * @since 6.36
 * @version 2014-11-7 下午4:26:23
 * @author zhangchd
 */
public class MaterialPriceBaseDeleteProxy implements ISingleBillService<MaterialPriceBaseAggVO> {
    @Override
    public MaterialPriceBaseAggVO operateBill(MaterialPriceBaseAggVO bill) throws Exception {
        IMaterialPriceBaseMaintainService operator =
                NCLocator.getInstance().lookup(IMaterialPriceBaseMaintainService.class);
        operator.delete(new MaterialPriceBaseAggVO[] {
            bill
        });
        return bill;
    }
}
