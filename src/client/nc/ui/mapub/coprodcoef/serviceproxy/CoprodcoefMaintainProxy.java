package nc.ui.mapub.coprodcoef.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.coprodcoef.ICoprodcoefMaintain;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * ʾ�����ݵĲ�������
 * 
 * @since 6.0
 * @version 2014-10-11 ����3:06:14
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
        // Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
        // ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�nc.ui.mmpd.samplebill.serviceproxy.SampleDeleteProxy
        ICoprodcoefMaintain operator = NCLocator.getInstance().lookup(ICoprodcoefMaintain.class);
        operator.delete((CoprodcoefAggVO[]) value);
        return value;

    }
}
