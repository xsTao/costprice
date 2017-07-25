/**
 *
 */
package nc.ui.mapub.costpricebase.view;

import nc.ui.mapub.costpricebase.scale.CostPriceBaseScaleUtil;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

/**
 * @since v6.3
 * @version 2017年7月19日 下午3:53:58
 * @author Administrator
 */
public class CostPriceListView extends ShowUpableBillListView {

    private static final long serialVersionUID = -8906686830841878902L;

    @Override
    public void initUI() {
        super.initUI();
        new CostPriceBaseScaleUtil().setListScale(this.getModel().getContext().getPk_group(), this.getBillListPanel());
    }

}
