package nc.ui.mapub.coprodcoef.action;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefItemVO;
import nc.vo.pub.lang.UFDouble;

/**
 * @since 6.0
 * @version 2014-10-11 下午12:26:44
 * @author zhangshyb
 */
public class PrintDataProcessor implements IBeforePrintDataProcess {
    private AbstractAppModel model;

    /**
     * @return model
     */
    public AbstractAppModel getModel() {
        return this.model;
    }

    /**
     * @param model
     *            要设置的 model
     */
    public void setModel(AbstractAppModel model) {
        this.model = model;
    }

    @Override
    public Object[] processData(Object[] datas) {
        CoprodcoefAggVO[] vos = null;
        CoprodcoefAggVO agg = null;
        CoprodcoefItemVO[] items = null;
        CoprodcoefItemVO item = null;
        if (datas != null && datas.length > 0) {
            vos = new CoprodcoefAggVO[datas.length];
            for (int i = 0; i < datas.length; i++) {
                agg = (CoprodcoefAggVO) datas[i];
                items = (CoprodcoefItemVO[]) agg.getChildrenVO();
                for (int j = 0; j < items.length; j++) {
                    if (items[j] != null && items[j].getNrelcoefficient() != null) {
                        item = items[j];
                        item.setNrelcoefficient(item.getNrelcoefficient().setScale(8, UFDouble.ROUND_HALF_UP));
                    }
                }
                vos[i] = agg;
            }
        }
        return vos;
    }
}
