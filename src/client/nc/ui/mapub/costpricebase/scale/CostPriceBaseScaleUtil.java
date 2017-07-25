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
 * @version 2017��7��19�� ����9:05:30
 * @author Administrator
 */
public class CostPriceBaseScaleUtil {

    // ���ÿ�Ƭ����
    public void setCardScale(String pk_group, BillCardPanel bcp) {
        this.setScale(new CardPaneScaleProcessor(pk_group, bcp));
    }

    // �����б���
    public void setListScale(String pk_group, BillListPanel bcp) {
        this.setScale(new ListPaneScaleProcessor(pk_group, bcp));
    }

    // ���ô�ӡ����
    public void setPrintScale(String pk_group, CostPriceAggVO[] bills) {
        this.setScale(new BillVOScaleProcessor(pk_group, bills));
    }

    private void setScale(BillScaleProcessor scale) {

        // ���۾���
        scale.setCostPriceCtlInfo(new String[] {
                CostPriceBodyVO.DPRICE
        }, PosEnum.body, null);

        scale.process();
    }
}
