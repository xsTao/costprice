/**
 *
 */
package nc.ui.mapub.costpricebase.view;

import nc.ui.mapub.costpricebase.scale.CostPriceBaseScaleUtil;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

/**
 * 费用价格库卡片界面初始化
 *
 * @since v6.3
 * @version 2017年7月19日 下午2:20:53
 * @author
 */
public class CostPriceBillForm extends ShowUpableBillForm {

    private static final long serialVersionUID = 1911037728443216074L;

    @Override
    public void initUI() {
        super.initUI();

        new CostPriceBaseScaleUtil().setCardScale(this.getModel().getContext().getPk_group(), this.getBillCardPanel());
    }

}
