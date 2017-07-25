package nc.ui.mapub.materialpricebase.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.materialpricebase.IMaterialPriceBaseMaintainService;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * ���ϼ۸��
 *
 * @since 6.36
 * @version 2014-11-7 ����4:27:54
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
        // Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
        // ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�nc.ui.mmpd.samplebill.serviceproxy.SampleDeleteProxy
        return null;
    }

    // ����
    public IBill[] lock(IBill[] bills) throws BusinessException {
        MaterialPriceBaseAggVO[] aggvos = new MaterialPriceBaseAggVO[bills.length];

        for (int i = 0; i < bills.length; i++) {
            aggvos[i] = (MaterialPriceBaseAggVO) bills[i];
        }
        MaterialPriceBaseAggVO[] vos = this.getMaterialPriceBaseMaintainService().lock(aggvos);

        return vos;
    }

    // �ⶳ
    public IBill[] deblocking(IBill[] bills) throws BusinessException {
        MaterialPriceBaseAggVO[] aggvos = new MaterialPriceBaseAggVO[bills.length];

        for (int i = 0; i < bills.length; i++) {
            aggvos[i] = (MaterialPriceBaseAggVO) bills[i];
        }
        MaterialPriceBaseAggVO[] vos = this.getMaterialPriceBaseMaintainService().deblocking(aggvos);

        return vos;
    }

    // ȡ��
    public IBill[] takePrice(IBill[] bills) throws BusinessException {
        MaterialPriceBaseAggVO[] aggvos = new MaterialPriceBaseAggVO[bills.length];

        for (int i = 0; i < bills.length; i++) {
            aggvos[i] = (MaterialPriceBaseAggVO) bills[i];
        }
        MaterialPriceBaseAggVO[] vos = this.getMaterialPriceBaseMaintainService().takePrice(aggvos);
        return vos;
    }

    /**
     * ��ȡ�۸��ķ���
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
