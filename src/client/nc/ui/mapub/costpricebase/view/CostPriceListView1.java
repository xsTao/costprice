/**
 *
 */
package nc.ui.mapub.costpricebase.view;

import nc.ui.cm.stuff.scale.StuffScaleUtil;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.vo.cm.stuff.entity.StuffHeadVO;

/**
 * @since v6.3
 * @version 2017年7月21日 下午5:10:47
 * @author Administrator
 */
public class CostPriceListView1 extends ShowUpableBillListView {

    private static final long serialVersionUID = 1L;

    @Override
    public void initUI() {
        super.initUI();
        new StuffScaleUtil().setListScale(this.getModel().getContext().getPk_group(), this.getBillListPanel());
        this.getBillListPanel().getHeadBillModel().setSortColumn(new String[] {
            StuffHeadVO.PK_ORG, StuffHeadVO.VBILLCODE
        });
    }
}
