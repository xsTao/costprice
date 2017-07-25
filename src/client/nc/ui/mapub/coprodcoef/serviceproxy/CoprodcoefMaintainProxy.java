package nc.ui.mapub.coprodcoef.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.coprodcoef.ICoprodcoefMaintain;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * 示例单据的操作代理
 * 
 * @since 6.0
 * @version 2014-10-11 下午3:06:14
 * @author zhangshyb
 */
public class CoprodcoefMaintainProxy implements IDataOperationService {

    @Override
    public IBill[] insert(IBill[] value) throws BusinessException {
        ICoprodcoefMaintain operator = NCLocator.getInstance().lookup(ICoprodcoefMaintain.class);
        CoprodcoefAggVO[] vos = operator.insert((CoprodcoefAggVO[]) value);
        return vos;
    }

    @Override
    public IBill[] update(IBill[] value) throws BusinessException {
        ICoprodcoefMaintain operator = NCLocator.getInstance().lookup(ICoprodcoefMaintain.class);
        CoprodcoefAggVO[] vos = operator.update((CoprodcoefAggVO[]) value);
        return vos;
    }

    @Override
    public IBill[] delete(IBill[] value) throws BusinessException {
        // 目前的删除并不是走这个方法，由于pubapp不支持从这个服务中执行删除操作
        // 单据的删除实际上使用的是：nc.ui.mmpd.samplebill.serviceproxy.SampleDeleteProxy
        ICoprodcoefMaintain operator = NCLocator.getInstance().lookup(ICoprodcoefMaintain.class);
        operator.delete((CoprodcoefAggVO[]) value);
        return value;

    }
}
