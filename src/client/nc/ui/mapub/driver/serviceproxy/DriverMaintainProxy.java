package nc.ui.mapub.driver.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.driver.IDriverMaintainService;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

public class DriverMaintainProxy implements IDataOperationService {
    @Override
    public IBill[] insert(IBill[] value) throws BusinessException {
        IDriverMaintainService operator = NCLocator.getInstance().lookup(IDriverMaintainService.class);
        DriverAggVO[] vos = operator.insertDriver((DriverAggVO[]) value);
        return vos;
    }

    @Override
    public IBill[] update(IBill[] value) throws BusinessException {
        IDriverMaintainService operator = NCLocator.getInstance().lookup(IDriverMaintainService.class);
        DriverAggVO[] vos = operator.updateDriver((DriverAggVO[]) value);
        return vos;
    }

    @Override
    public IBill[] delete(IBill[] value) throws BusinessException {
        // 目前的删除并不是走这个方法，由于pubapp不支持从这个服务中执行删除操作
        // 单据的删除实际上使用的是：nc.ui.mmpd.samplebill.serviceproxy.SampleDeleteProxy
        IDriverMaintainService operator = NCLocator.getInstance().lookup(IDriverMaintainService.class);
        operator.deleteDriver((DriverAggVO[]) value);
        return value;
    }
}
