/**
 * 
 */
package nc.ui.mapub.allocfac.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.allocfac.IAllocfacMaintainService;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * ʾ�����ݵĲ�������
 * 
 * @since 6.3
 * @version 2012-09-03
 * @author xionghuic
 */

public class AllocfacMaintainProxy implements IDataOperationService {

    private IAllocfacMaintainService allocfacMaintain;

    @Override
    public IBill[] insert(IBill[] bills) throws BusinessException {
        AllocfacAggVO[] vos = this.getallocfacMaintainService().insert((AllocfacAggVO[]) bills);
        return vos;
    }

    @Override
    public IBill[] update(IBill[] bills) throws BusinessException {
        AllocfacAggVO[] vos = this.getallocfacMaintainService().update((AllocfacAggVO[]) bills);
        return vos;
    }

    @Override
    public IBill[] delete(IBill[] value) throws BusinessException {
        // Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
        return null;
    }

    /**
     * ��ȡ����
     * 
     * @return ����
     */
    private IAllocfacMaintainService getallocfacMaintainService() {
        if (this.allocfacMaintain == null) {
            this.allocfacMaintain = NCLocator.getInstance().lookup(IAllocfacMaintainService.class);
        }
        return this.allocfacMaintain;
    }
}
