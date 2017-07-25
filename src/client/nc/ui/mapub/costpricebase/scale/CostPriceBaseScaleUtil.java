/**
 *
 */
package nc.ui.mapub.costpricebase.scale;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.mapub.costpricebase.entity.CostPriceBodyVO;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;

/**
 * @since v6.3
 * @version 2017年7月19日 下午9:05:30
 * @author Administrator
 */
public class CostPriceBaseScaleUtil {

    // 设置卡片精度
    public void setCardScale(String pk_group, BillCardPanel bcp) {
        this.setScale(new CardPaneScaleProcessor(pk_group, bcp));
    }

    // 设置列表精度
    public void setListScale(String pk_group, BillListPanel bcp) {
        this.setScale(new ListPaneScaleProcessor(pk_group, bcp));
    }

    // 设置打印精度
    public void setPrintScale(String pk_group, CostPriceAggVO[] bills) {
        this.setScale(new BillVOScaleProcessor(pk_group, bills));
    }

    private void setScale(BillScaleProcessor scale) {

        // 单价精度
        scale.setCostPriceCtlInfo(new String[] {
                CostPriceBodyVO.DPRICE
        }, PosEnum.body, null);

        scale.process();
    }
}
