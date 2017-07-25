/**
 * 
 */
package nc.ui.mapub.allocfac.action;

import nc.ui.mapub.allocfac.scale.AllocfacPrintScaleUtil;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;

/**
 * ���ȴ�����
 */

public class PrintDataProcess implements IBeforePrintDataProcess {
    private AbstractAppModel model;

    public AbstractAppModel getModel() {
        return this.model;
    }

    public void setModel(AbstractAppModel model) {
        this.model = model;
    }

    @Override
    public Object[] processData(Object[] datas) {
        // ת��Ϊ����ϵ��
        AllocfacAggVO[] vos = new AllocfacAggVO[datas.length];
        for (int i = 0; i < datas.length; i++) {
            vos[i] = (AllocfacAggVO) datas[i];
        }

        // ���ȴ���
        AllocfacPrintScaleUtil handler = new AllocfacPrintScaleUtil();
        handler.setPrintScale(this.getModel().getContext().getPk_group(), vos);
        return vos;
    }
}
