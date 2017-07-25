package nc.ui.mapub.materialpricebase.scale;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;

/**
 * 材料价格库
 * 打印精度处理
 * 
 * @since 6.36
 * @version 2014-11-7 下午4:17:17
 * @author zhangchd
 */
public class MaterialPriceBasePrintScaleProcessor implements IBeforePrintDataProcess {
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
        MaterialPriceBaseAggVO[] vos = new MaterialPriceBaseAggVO[datas.length];
        for (int i = 0; i < datas.length; i++) {
            vos[i] = (MaterialPriceBaseAggVO) datas[i];
        }
        // 精度处理类
        MaterialPriceBaseScaleUtil handle = new MaterialPriceBaseScaleUtil();
        handle.setPrintScale(this.getModel().getContext().getPk_group(), vos);
        return vos;
    }
}
