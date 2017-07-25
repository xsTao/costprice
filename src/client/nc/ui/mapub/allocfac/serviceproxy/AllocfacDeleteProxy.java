/**
 * 
 */
package nc.ui.mapub.allocfac.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.allocfac.IAllocfacMaintainService;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;

/**
 * 示例单据的删除代理，支持批删除
 * 
 * @since 6.3
 * @version 2012-09-03
 * @author xionghuic
 */

public class AllocfacDeleteProxy implements ISingleBillService<AllocfacAggVO> {

    private IAllocfacMaintainService allocfacMaintainService;

    private IAllocfacMaintainService getAllocfacMaintainService() {
        if (this.allocfacMaintainService == null) {
            this.allocfacMaintainService = NCLocator.getInstance().lookup(IAllocfacMaintainService.class);
        }
        return this.allocfacMaintainService;
    }

    @Override
    public AllocfacAggVO operateBill(AllocfacAggVO bill) throws Exception {
        this.getAllocfacMaintainService().allocDelete(new AllocfacAggVO[] {
            bill
        });
        return bill;
    }
}
