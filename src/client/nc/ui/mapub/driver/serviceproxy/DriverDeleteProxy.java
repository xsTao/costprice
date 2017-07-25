package nc.ui.mapub.driver.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.driver.IDriverMaintainService;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.mapub.driver.entity.DriverAggVO;

public class DriverDeleteProxy implements ISingleBillService<DriverAggVO> {

    @Override
    public DriverAggVO operateBill(DriverAggVO bill) throws Exception {
        IDriverMaintainService operator = NCLocator.getInstance().lookup(IDriverMaintainService.class);
        operator.deleteDriver(new DriverAggVO[] {
            bill
        });
        return bill;
    }
}
