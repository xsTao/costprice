package nc.ui.mapub.materialpricebase.view;

import nc.ui.mapub.materialpricebase.scale.MaterialPriceBaseScaleUtil;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

/**
 * 材料价格库
 * 
 * @since 6.36
 * @version 2014-11-7 下午4:14:30
 * @author zhangchd
 */
public class ListView extends ShowUpableBillListView {
    private static final long serialVersionUID = 2891256327345468738L;

    @Override
    public void initUI() {
        super.initUI();
        new MaterialPriceBaseScaleUtil().setListScale(this.getModel().getContext().getPk_group(),
                this.getBillListPanel());
    }
}
