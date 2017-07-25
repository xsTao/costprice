/**
 *
 */
package nc.ui.mapub.costpricebase.view;

import nc.ui.cm.stuff.scale.StuffScaleUtil;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

/**
 * @since v6.3
 * @version 2017年7月21日 下午4:54:54
 * @author Administrator
 */
public class CostPriceBillForm1 extends ShowUpableBillForm {

    private static final long serialVersionUID = 1L;

    @Override
    public void initUI() {
        super.initUI();
        new StuffScaleUtil().setCardScale(this.getModel().getContext().getPk_group(), this.getBillCardPanel());
    }
}
