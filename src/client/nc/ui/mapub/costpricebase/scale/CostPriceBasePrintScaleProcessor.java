/**
 *
 */
package nc.ui.mapub.costpricebase.scale;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;

/**
 * @since v6.3
 * @version 2017年7月31日 上午1:56:19
 * @author yangtao
 */
public class CostPriceBasePrintScaleProcessor implements IBeforePrintDataProcess {

    /*
     * (non-Javadoc)
     * @see
     * nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess#processData(java.lang.Object[])
     */
    // TODO Auto-generated method stub
    private AbstractAppModel model;

    public AbstractAppModel getModel() {
        return this.model;
    }

    public void setModel(AbstractAppModel model) {
        this.model = model;
    }

    @Override
    public Object[] processData(Object[] datas) {
        if (datas == null) {
            return null;
        }
        CostPriceAggVO[] vos = new CostPriceAggVO[datas.length];
        for (int i = 0; i < datas.length; i++) {
            vos[i] = (CostPriceAggVO) datas[i];
        }
        // 精度处理类
        CostPriceBaseScaleUtil handle = new CostPriceBaseScaleUtil();
        handle.setPrintScale(this.getModel().getContext().getPk_group(), vos);
        return vos;
    }
}
