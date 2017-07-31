/**
 *
 */
package nc.ui.mapub.costpricebase.handler.head;

import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * @since v6.3
 * @version 2017年7月31日 下午9:13:10
 * @author Administrator
 */
public class VperidoHandler extends CMBasedocAbstractHandler {

    /*
     * (non-Javadoc)
     * @see nc.ui.bd.pub.handler.CMBasedocAbstractHandler#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardPanelEvent)
     */
    @Override
    public void beforeEdit(CardPanelEvent e) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see nc.ui.bd.pub.handler.CMBasedocAbstractHandler#afterEdit(nc.ui.pubapp.uif2app.event.card.CardPanelEvent)
     */
    @Override
    public void afterEdit(CardPanelEvent e) {
        // TODO Auto-generated method stub
        CardHeadTailAfterEditEvent beforeEdit = (CardHeadTailAfterEditEvent) e;
        BillCardPanel panel = beforeEdit.getBillCardPanel();
        BillItem billItem = panel.getHeadItem(beforeEdit.getKey());
        if (panel.getHeadItem(CostPriceHeadVO.VPERIOD).getValueObject() != null) {
            panel.getHeadItem(CostPriceHeadVO.ANNUAL).setEdit(false);
        }
        else {
            panel.getHeadItem(CostPriceHeadVO.ANNUAL).setEdit(true);
        }
    }

}
